package com.company.hiring_service.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.company.hiring_service.dto.InterviewDTO;
import com.company.hiring_service.service.InterviewService;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    /**
     * Schedule interview for an application (MANAGER / HR).
     */
   // @PreAuthorize("hasAnyRole('MANAGER','HR')")
    @PostMapping("/schedule")
    public ResponseEntity<InterviewDTO> schedule(@RequestBody InterviewDTO dto,
                                                 Principal principal) {
        InterviewDTO scheduled = interviewService.scheduleInterview(dto, principal.getName());
        return ResponseEntity.ok(scheduled);
    }

    /**
     * Get interview by ID (MANAGER / HR).
     */
    @PreAuthorize("hasAnyRole('MANAGER','HR')")
    @GetMapping("/{id}")
    public ResponseEntity<InterviewDTO> getInterviewById(@PathVariable String id) {
        return ResponseEntity.ok(interviewService.getInterviewById(id));
    }

    /**
     * View interviews by interviewer.
     */
    //@PreAuthorize("hasAnyRole('MANAGER','HR')")
    @GetMapping("/interviewer/{interviewerId}")
    public ResponseEntity<List<InterviewDTO>> getInterviewsByInterviewer(@PathVariable String interviewerId) {
        List<InterviewDTO> interviews = interviewService.getInterviewsByInterviewer(interviewerId);
        return ResponseEntity.ok(interviews);
    }

    /**
     * Decide interview result (SELECTED / REJECTED).
     */
    //@PreAuthorize("hasAnyRole('MANAGER','HR')")
    @PostMapping("/{id}/decision")
    public ResponseEntity<Void> decide(@PathVariable String id,
                                       @RequestParam String decision,
                                       Principal principal) {
        interviewService.decideInterview(id, decision, principal.getName());
        return ResponseEntity.noContent().build();
    }
}