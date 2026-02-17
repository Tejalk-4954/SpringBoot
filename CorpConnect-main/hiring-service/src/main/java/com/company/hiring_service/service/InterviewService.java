package com.company.hiring_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.company.hiring_service.dto.InterviewDTO;
import com.company.hiring_service.entity.Interview;
import com.company.hiring_service.entity.JobApplication;
import com.company.hiring_service.entity.JobPost;
import com.company.hiring_service.repository.InterviewRepository;
import com.company.hiring_service.repository.JobApplicationRepository;
import com.company.hiring_service.repository.JobPostRepository;
import com.company.hiring_service.util.EmailService;

@Service
public class InterviewService {

    private static final List<String> VALID_DECISIONS = List.of("SELECTED", "REJECTED");

    private final InterviewRepository interviewRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final JobPostRepository jobPostRepository;
    private final EmailService emailService;
    private final RestTemplate restTemplate;

    @Value("${services.ticket}")
    private String ticketServiceBaseUrl; // e.g. http://localhost:8090/api/tickets

    public InterviewService(InterviewRepository interviewRepository,
                            JobApplicationRepository jobApplicationRepository,
                            JobPostRepository jobPostRepository,
                            EmailService emailService,
                            RestTemplate restTemplate) {
        this.interviewRepository = interviewRepository;
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobPostRepository = jobPostRepository;
        this.emailService = emailService;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public InterviewDTO scheduleInterview(InterviewDTO dto, String scheduledBy) {
        JobApplication application = jobApplicationRepository.findById(dto.getApplicationId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid application ID"));

        Interview entity = Interview.builder()
                .id(UUID.randomUUID().toString())
                .application(application)
                .interviewDate(dto.getInterviewDate())   // LocalDateTime
                .interviewerId(dto.getInterviewerId())
                .mode(dto.getMode())
                .status("SCHEDULED")
                .notes(dto.getNotes())
                .meetLink(dto.getMeetLink())
                .createdAt(LocalDateTime.now())
                .createdBy(scheduledBy)
                .build();

        Interview saved = interviewRepository.save(entity);

        // update application status
        application.setStatus("INTERVIEW_SCHEDULED");
        jobApplicationRepository.save(application);

        // send emails
        try {
            // candidate email
            emailService.sendPlainEmail(
                    application.getCandidateEmail(),
                    "Interview Scheduled",
                    "Hi " + application.getCandidateName() +
                            ", your interview is scheduled on " + dto.getInterviewDate() +
                            ". Mode: " + dto.getMode() +
                            (dto.getMeetLink() != null ? (", Link: " + dto.getMeetLink()) : "")
            );
        } catch (Exception ignored) {}

        return toDTO(saved);
    }

    public InterviewDTO getInterviewById(String id) {
        Interview entity = interviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Interview not found"));
        return toDTO(entity);
    }

    public List<InterviewDTO> getInterviewsByInterviewer(String interviewerId) {
        return interviewRepository.findByInterviewerId(interviewerId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void decideInterview(String interviewId, String decision, String performedBy) {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new IllegalArgumentException("Interview not found"));

        JobApplication app = interview.getApplication();
        JobPost jobPost = app.getJobPost();

        String normalized = decision.toUpperCase();
        if (!VALID_DECISIONS.contains(normalized)) {
            throw new IllegalArgumentException("Invalid decision: " + decision);
        }

        interview.setStatus(normalized);
        interview.setDecidedAt(LocalDateTime.now());
        interviewRepository.save(interview);

        app.setStatus(normalized);
        jobApplicationRepository.save(app);

        if ("SELECTED".equals(normalized)) {
            try {
                emailService.sendPlainEmail(
                        app.getCandidateEmail(),
                        "You are selected!",
                        "Hi " + app.getCandidateName() +
                                ", congratulations! You have been selected for the role."
                );
            } catch (Exception ignored) {}

            // Close linked ticket (if any)
            if (jobPost.getTicketId() != null && !jobPost.getTicketId().isBlank()) {
                try {
                    String url = ticketServiceBaseUrl + "/" + jobPost.getTicketId() + "/close";
                    // For secured ticket-service you may need to propagate JWT here.
                    restTemplate.postForEntity(url, null, Void.class);
                } catch (Exception ignored) {}
            }
        } else {
            // REJECTED
            try {
                emailService.sendPlainEmail(
                        app.getCandidateEmail(),
                        "Interview Result",
                        "Hi " + app.getCandidateName() +
                                ", thanks for interviewing with us. We won't be moving forward at this time."
                );
            } catch (Exception ignored) {}
        }
    }

    private InterviewDTO toDTO(Interview entity) {
        InterviewDTO dto = new InterviewDTO();
        dto.setId(entity.getId());
        dto.setApplicationId(entity.getApplication().getId());
        dto.setInterviewDate(entity.getInterviewDate());
        dto.setInterviewerId(entity.getInterviewerId());
        dto.setMode(entity.getMode());
        dto.setStatus(entity.getStatus());
        dto.setNotes(entity.getNotes());
        dto.setMeetLink(entity.getMeetLink());
        return dto;
    }
}