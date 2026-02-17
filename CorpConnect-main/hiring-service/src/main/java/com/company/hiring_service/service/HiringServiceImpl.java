//package com.company.hiring_service.service;
//
//
//import java.time.Instant;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Service;
//
//import com.company.hiring_service.dto.ApplicationResponse;
//import com.company.hiring_service.dto.ApplyJobRequest;
//import com.company.hiring_service.dto.CreateJobPostRequest;
//import com.company.hiring_service.dto.JobPostResponse;
//import com.company.hiring_service.dto.ScheduleInterviewRequest;
//import com.company.hiring_service.entity.Application;
//import com.company.hiring_service.entity.JobPost;
//
//import com.company.hiring_service.repository.ApplicationRepository;
//import com.company.hiring_service.repository.JobPostRepository;
//import com.company.hiring_service.util.EmailService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import jakarta.transaction.Transactional;
//
//@Service
//public class HiringServiceImpl implements HiringService {
//
//    private final JobPostRepository jobPostRepository;
//    private final ApplicationRepository applicationRepository;
//   // private final HiringEventPublisher kafkaPublisher;
//    private final EmailService emailService;
//    private final ObjectMapper objectMapper;
//
//    public HiringServiceImpl(JobPostRepository jobPostRepository,
//                             ApplicationRepository applicationRepository,
//                             //HiringEventPublisher kafkaPublisher,
//                             EmailService emailService,
//                             ObjectMapper objectMapper) {
//        this.jobPostRepository = jobPostRepository;
//        this.applicationRepository = applicationRepository;
//       // this.kafkaPublisher = kafkaPublisher;
//        this.emailService = emailService;
//        this.objectMapper = objectMapper;
//    }
//
//    @Override
//    @Transactional
//    public JobPostResponse createJobPost(CreateJobPostRequest req, String createdByToken) {
//
//        JobPost job = JobPost.builder()
//                .id(UUID.randomUUID().toString())
//                .jobNumber("JOB-" + System.currentTimeMillis())
//                .title(req.getTitle())
//                .description(req.getDescription())
//                .requiredSkills(req.getRequiredSkills())
//                .experienceYears(req.getExperienceYears())
//                .location(req.getLocation())
//                .status("OPEN")
//                .createdAt(Instant.now())
//                .build();
//
//        jobPostRepository.save(job);
//
//        // Kafka Publish
////        try {
////            String payload = objectMapper.writeValueAsString(Map.of(
////                    "jobPostId", job.getId(),
////                    "jobNumber", job.getJobNumber()
////            ));
////            kafkaPublisher.publish(KafkaTopics.JOB_POSTED, job.getId(), payload);
////        } catch (Exception ignored) {}
//
//        return JobPostResponse.builder()
//                .id(job.getId())
//                .jobNumber(job.getJobNumber())
//                .title(job.getTitle())
//                .description(job.getDescription())
//                .requiredSkills(job.getRequiredSkills())
//                .experienceYears(job.getExperienceYears())
//                .location(job.getLocation())
//                .status(job.getStatus())
//                .createdBy(job.getCreatedBy())
//                .createdAt(job.getCreatedAt())
//                .build();
//    }
//
//    @Override
//    @PreAuthorize("hasAuthority('ROLE_CANDIDATE')")
//    @Transactional
//    public ApplicationResponse applyJob(ApplyJobRequest req, String candidateToken) {
//
//    	System.out.println("🔐 Candidate token received: " + candidateToken);
//        System.out.println("📄 ApplyJobRequest: " + req);
//
//        Application app = Application.builder()
//                .id(UUID.randomUUID().toString())
//                .jobPostId(req.getJobPostId())
//                .candidateId(req.getCandidateId())
//                .candidateName(req.getCandidateName())
//                .candidateEmail(req.getCandidateEmail())
//                .skills(req.getSkills())
//                .experienceYears(req.getExperienceYears())
//                .location(req.getLocation())
//                .resumeFileId(req.getResumeObjectKey())
//                .status("APPLIED")
//                .appliedAt(Instant.now())
//                .build();
//
//        applicationRepository.save(app);
//
//        // Kafka Event
////        try {
////            String payload = objectMapper.writeValueAsString(Map.of(
////                    "applicationId", app.getId(),
////                    "candidate", app.getCandidateEmail()
////            ));
////            kafkaPublisher.publish(KafkaTopics.APPLICATION_SUBMITTED, app.getId(), payload);
////        } catch (Exception ignored) {}
//
//        // Email Notification
//        emailService.sendPlainEmail(app.getCandidateEmail(),
//                "Application Submitted",
//                "Hi " + app.getCandidateName() + ", your application has been submitted successfully.");
//
//        return ApplicationResponse.builder()
//                .id(app.getId())
//                .jobPostId(app.getJobPostId())
//                .candidateId(app.getCandidateId())
//                .candidateName(app.getCandidateName())
//                .candidateEmail(app.getCandidateEmail())
//                .skills(app.getSkills())
//                .experienceYears(app.getExperienceYears())
//                .location(app.getLocation())
//                .resumeFileId(app.getResumeFileId())
//                .status(app.getStatus())
//                .appliedAt(app.getAppliedAt())
//                .build();
//    }
//
//    @Override
//    public List<ApplicationResponse> getApplicationsForJob(String jobPostId, String callerToken) {
//        return applicationRepository.findByJobPostId(jobPostId)
//                .stream()
//                .map(app -> ApplicationResponse.builder()
//                        .id(app.getId())
//                        .jobPostId(app.getJobPostId())
//                        .candidateId(app.getCandidateId())
//                        .candidateName(app.getCandidateName())
//                        .candidateEmail(app.getCandidateEmail())
//                        .skills(app.getSkills())
//                        .experienceYears(app.getExperienceYears())
//                        .location(app.getLocation())
//                        .resumeFileId(app.getResumeFileId())
//                        .status(app.getStatus())
//                        .appliedAt(app.getAppliedAt())
//                        .build())
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    @Transactional
//    public void scheduleInterview(ScheduleInterviewRequest req, String callerToken) {
//
////        try {
////            String payload = objectMapper.writeValueAsString(Map.of(
////                    "applicationId", req.getApplicationId(),
////                    "slotId", req.getInterviewSlotId()
////            ));
////            kafkaPublisher.publish(KafkaTopics.INTERVIEW_SCHEDULED, req.getApplicationId(), payload);
////        } catch (Exception ignored) {}
//    }
//
//    @Override
//    @Transactional
//    public void selectCandidate(String applicationId, String callerToken) {
//
//        Application app = applicationRepository.findById(applicationId).orElseThrow();
//        app.setStatus("SELECTED");
//        applicationRepository.save(app);
//
////        try {
////            String payload = objectMapper.writeValueAsString(Map.of(
////                    "applicationId", applicationId,
////                    "candidateEmail", app.getCandidateEmail()
////            ));
////            kafkaPublisher.publish(KafkaTopics.CANDIDATE_SELECTED, applicationId, payload);
////        } catch (Exception ignored) {}
////
////        emailService.sendPlainEmail(app.getCandidateEmail(),
////                "Congratulations! You are Selected 🎉",
////                "Hi " + app.getCandidateName() + ", you have been selected for the role!");
////    }
//    }
//}
