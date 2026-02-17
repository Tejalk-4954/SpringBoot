//package com.company.hiring_service.service;
//
//import java.util.List;
//
//import com.company.hiring_service.dto.ApplicationResponse;
//import com.company.hiring_service.dto.ApplyJobRequest;
//import com.company.hiring_service.dto.CreateJobPostRequest;
//import com.company.hiring_service.dto.JobPostResponse;
//import com.company.hiring_service.dto.ScheduleInterviewRequest;
//
//public interface HiringService {
//    JobPostResponse createJobPost(CreateJobPostRequest req, String createdByToken);
//    ApplicationResponse applyJob(ApplyJobRequest req, String candidateToken);
//    List<ApplicationResponse> getApplicationsForJob(String jobPostId, String callerToken);
//    void scheduleInterview(ScheduleInterviewRequest req, String callerToken);
//    void selectCandidate(String applicationId, String callerToken);
//}