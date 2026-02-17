package com.company.hiring_service.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.company.hiring_service.dto.JobPostDTO;
import com.company.hiring_service.service.JobPostService;

@RestController
@RequestMapping("/api/job-posts")
public class JobPostController {

    private final JobPostService jobPostService;

    public JobPostController(JobPostService jobPostService) {
        this.jobPostService = jobPostService;
    }

    /**
     * Create a job post (MANAGER / HR).
     */
    @PreAuthorize("hasAnyRole('MANAGER','HR')")
    @PostMapping
    public ResponseEntity<JobPostDTO> createJobPost(@RequestBody JobPostDTO dto,
                                                    Principal principal) {
        String createdBy = principal.getName();
        JobPostDTO saved = jobPostService.createJobPost(dto, createdBy);
        return ResponseEntity.ok(saved);
    }

    /**
     * Public list of open job posts (no auth).
     * This path is permitted in SecurityConfig.
     */
    @GetMapping("/public")
    public ResponseEntity<List<JobPostDTO>> getOpenJobPosts() {
        return ResponseEntity.ok(jobPostService.getAllOpenJobPosts());
    }

    /**
     * Get job post by ID (authenticated users – candidate / manager / HR).
     */
    @GetMapping("/{id}")
    public ResponseEntity<JobPostDTO> getJobPostById(@PathVariable String id) {
        return ResponseEntity.ok(jobPostService.getJobPostById(id));
    }

    /**
     * Posts created by current user (MANAGER / HR).
     */
    @PreAuthorize("hasAnyRole('MANAGER','HR')")
    @GetMapping("/my")
    public ResponseEntity<List<JobPostDTO>> getMyJobPosts(Principal principal) {
        return ResponseEntity.ok(jobPostService.getJobPostsByCreator(principal.getName()));
    }

    /**
     * Update job post status (e.g. OPEN / CLOSED) (MANAGER / HR).
     */
    @PreAuthorize("hasAnyRole('MANAGER','HR')")
    @PostMapping("/{id}/status")
    public ResponseEntity<Void> updateJobPostStatus(@PathVariable String id,
                                                    @RequestParam String status) {
        jobPostService.updateJobPostStatus(id, status);
        return ResponseEntity.noContent().build();
    }
}