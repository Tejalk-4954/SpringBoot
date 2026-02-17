package com.company.hiring_service.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.company.hiring_service.dto.JobApplicationDTO;
import com.company.hiring_service.dto.PresignResponse;
import com.company.hiring_service.service.JobApplicationService;

@RestController
@RequestMapping("/api/job-applications")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    /**
     * Candidate applies for job (requires ROLE_CANDIDATE).
     */
    @PreAuthorize("hasRole('CANDIDATE')")
    @PostMapping
    public ResponseEntity<JobApplicationDTO> applyForJob(@RequestBody JobApplicationDTO jobApplicationDTO,
                                                         Principal principal) {
        String candidateEmailOrId = principal.getName();
        JobApplicationDTO applied = jobApplicationService.applyForJob(jobApplicationDTO, candidateEmailOrId);
        return ResponseEntity.ok(applied);
    }

    /**
     * Get single application by ID (MANAGER / HR).
     */
    @PreAuthorize("hasAnyRole('MANAGER','HR')")
    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationDTO> getApplicationById(@PathVariable String id) {
        return ResponseEntity.ok(jobApplicationService.getApplicationById(id));
    }

    /**
     * Candidate sees their own applications.
     */
    @PreAuthorize("hasRole('CANDIDATE')")
    @GetMapping("/my")
    public ResponseEntity<List<JobApplicationDTO>> getMyApplications(Principal principal) {
        String candidateEmailOrId = principal.getName();
        List<JobApplicationDTO> apps = jobApplicationService.getApplicationsForCandidate(candidateEmailOrId);
        return ResponseEntity.ok(apps);
    }

    /**
     * Manager / HR sees applications for a given job.
     */
    @PreAuthorize("hasAnyRole('MANAGER','HR')")
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<JobApplicationDTO>> getApplicationsForJob(@PathVariable String jobId) {
        List<JobApplicationDTO> applications = jobApplicationService.getApplicationsForJob(jobId);
        return ResponseEntity.ok(applications);
    }

    /**
     * Manager / HR updates status of application (SHORTLISTED / REJECTED / SELECTED / etc).
     */
    @PreAuthorize("hasAnyRole('MANAGER','HR')")
    @PostMapping("/{id}/status")
    public ResponseEntity<Void> updateApplicationStatus(@PathVariable String id,
                                                        @RequestParam String status,
                                                        Principal principal) {
        jobApplicationService.updateStatus(id, status, principal.getName());
        return ResponseEntity.noContent().build();
    }

    /**
     * Presign URL for resume upload (candidate).
     */
    @PreAuthorize("hasRole('CANDIDATE')")
    @PostMapping("/resume/presign")
    public ResponseEntity<PresignResponse> presignResume(@RequestParam String fileName,
                                                         Principal principal) {
        PresignResponse resp = jobApplicationService.presignResumeUpload(fileName, principal.getName());
        return ResponseEntity.ok(resp);
    }
}