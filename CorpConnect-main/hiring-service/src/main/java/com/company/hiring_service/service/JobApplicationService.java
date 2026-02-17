package com.company.hiring_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.hiring_service.config.MinioUtil;
import com.company.hiring_service.dto.JobApplicationDTO;
import com.company.hiring_service.dto.PresignResponse;
import com.company.hiring_service.entity.JobApplication;
import com.company.hiring_service.entity.JobPost;
import com.company.hiring_service.repository.JobApplicationRepository;
import com.company.hiring_service.repository.JobPostRepository;
import com.company.hiring_service.util.EmailService;

@Service
public class JobApplicationService {

    private static final List<String> VALID_STATUSES =
            List.of("SHORTLISTED", "REJECTED", "APPLIED", "INTERVIEW_SCHEDULED", "SELECTED");

    private final JobApplicationRepository jobApplicationRepository;
    private final JobPostRepository jobPostRepository;
    private final MinioUtil minioUtil;
    private final EmailService emailService;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository,
                                 JobPostRepository jobPostRepository,
                                 MinioUtil minioUtil,
                                 EmailService emailService) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobPostRepository = jobPostRepository;
        this.minioUtil = minioUtil;
        this.emailService = emailService;
    }

    @Transactional
    public JobApplicationDTO applyForJob(JobApplicationDTO dto, String candidateEmailOrId) {
        JobPost jobPost = jobPostRepository.findById(dto.getJobId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid job post ID"));

        if (!"OPEN".equalsIgnoreCase(jobPost.getStatus())) {
            throw new IllegalStateException("Job is not open for applications");
        }

        // Ensure createdBy is derived from authenticated principal
        JobApplication entity = JobApplication.builder()
                .id(UUID.randomUUID().toString())
                .jobPost(jobPost)
                .candidateName(dto.getCandidateName())
                .candidateEmail(dto.getCandidateEmail())
                .resumeFileId(dto.getResumeFileId())
                .skills(dto.getSkills())
                .experienceYears(dto.getExperienceYears())
                .location(dto.getLocation())
                .status("APPLIED")
                .appliedAt(LocalDateTime.now())
                .createdBy(candidateEmailOrId)
                .build();

        JobApplication saved = jobApplicationRepository.save(entity);

        try {
            emailService.sendPlainEmail(
                    saved.getCandidateEmail(),
                    "Application Submitted",
                    "Hi " + saved.getCandidateName() + ", your application has been submitted successfully."
            );
        } catch (Exception ignored) {}

        return toDTO(saved);
    }

    public JobApplicationDTO getApplicationById(String id) {
        JobApplication app = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));
        return toDTO(app);
    }

    public List<JobApplicationDTO> getApplicationsForJob(String jobId) {
        return jobApplicationRepository.findByJobPostId(jobId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<JobApplicationDTO> getApplicationsForCandidate(String candidateEmailOrId) {
        return jobApplicationRepository.findByCreatedBy(candidateEmailOrId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateStatus(String id, String status, String performedBy) {
        JobApplication app = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        String normalized = status.toUpperCase();
        if (!VALID_STATUSES.contains(normalized)) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }

        app.setStatus(normalized);
        jobApplicationRepository.save(app);

        if ("SHORTLISTED".equals(normalized)) {
            try {
                emailService.sendPlainEmail(
                        app.getCandidateEmail(),
                        "You are shortlisted",
                        "Hi " + app.getCandidateName() +
                                ", you have been shortlisted for the next round. We will contact you for interview scheduling."
                );
            } catch (Exception ignored) {}
        }

        if ("REJECTED".equals(normalized)) {
            try {
                emailService.sendPlainEmail(
                        app.getCandidateEmail(),
                        "Application Update",
                        "Hi " + app.getCandidateName() +
                                ", thank you for applying. At this time, we won't be moving forward with your profile."
                );
            } catch (Exception ignored) {}
        }
    }

    public PresignResponse presignResumeUpload(String fileName, String candidateEmailOrId) {
        try {
            String objectKey = "resumes/" + candidateEmailOrId.replace("@", "_") +
                    "/" + System.currentTimeMillis() + "-" + fileName;

            String putUrl = minioUtil.presignPut(objectKey, 60 * 10);   // 10 min
            String getUrl = minioUtil.presignGet(objectKey, 60 * 60);   // 1 hour

            PresignResponse resp = new PresignResponse();
            resp.setObjectKey(objectKey);
            resp.setPresignedUrl(putUrl);
            resp.setDownloadUrl(getUrl);
            return resp;
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate resume presigned URL", e);
        }
    }

    private JobApplicationDTO toDTO(JobApplication entity) {
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setId(entity.getId());
        dto.setJobId(entity.getJobPost().getId());
        dto.setCandidateName(entity.getCandidateName());
        dto.setCandidateEmail(entity.getCandidateEmail());
        dto.setResumeFileId(entity.getResumeFileId());
        dto.setStatus(entity.getStatus());
        dto.setSkills(entity.getSkills());
        dto.setExperienceYears(entity.getExperienceYears());
        dto.setLocation(entity.getLocation());
        dto.setAppliedAt(entity.getAppliedAt());
        return dto;
    }
}