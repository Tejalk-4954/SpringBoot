package com.company.tickert_service.controller;

import com.company.tickert_service.dto.PresignRequest;
import com.company.tickert_service.dto.PresignResponse;
import com.company.tickert_service.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets/{ticketId}/attachments")
public class AttachmentController {

    private final ChatService chatService;

    public AttachmentController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * Request a presigned PUT URL.
     * Client should upload file using returned presignedUrl with PUT
     * and then send chat message referencing objectKey.
     */
    @PostMapping("/presign")
    public ResponseEntity<PresignResponse> presign(@PathVariable String ticketId,
                                                   @Valid @RequestBody PresignRequest req) {

        String combined = chatService.generatePresignedPut(
                ticketId,
                req.getFileName(),
                req.getContentType(),
                req.getSize(),
                60 * 60
        );

        String[] parts = combined.split("\\|\\|", 2);
        String presigned = parts[0];
        String objectKey = parts[1];

        PresignResponse resp = new PresignResponse();
        resp.setObjectKey(objectKey);
        resp.setPresignedUrl(presigned);

        try {
            String downloadUrl = chatService.generatePresignedGet(objectKey, 60 * 60);
            resp.setDownloadUrl(downloadUrl);
        } catch (Exception ignore) {
            resp.setDownloadUrl(null);
        }

        return ResponseEntity.ok(resp);
    }

    /**
     * Generate a presigned GET URL for an EXISTING attachment objectKey.
     * Useful when loading old chat history: you keep objectKey in DB
     * and call this endpoint to get a fresh download link.
     */
    @GetMapping("/download-url")
    public ResponseEntity<PresignResponse> getDownloadUrl(@PathVariable String ticketId,
                                                          @RequestParam String objectKey) {
        // Optional: verify objectKey starts with "tickets/{ticketId}/"
        if (!chatService.checkObjectExists(objectKey)) {
            return ResponseEntity.notFound().build();
        }

        String url = chatService.generatePresignedGet(objectKey, 60 * 60);

        PresignResponse resp = new PresignResponse();
        resp.setObjectKey(objectKey);
        resp.setPresignedUrl(null);
        resp.setDownloadUrl(url);

        return ResponseEntity.ok(resp);
    }
}