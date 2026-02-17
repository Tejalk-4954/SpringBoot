package com.company.hiring_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.hiring_service.dto.JobPostCreatedEvent;
import com.company.hiring_service.dto.JobPostDTO;
import com.company.hiring_service.entity.JobPost;
import com.company.hiring_service.kafka.KafkaProducerService;
import com.company.hiring_service.repository.JobPostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JobPostService {

    private final JobPostRepository jobPostRepository;
    private final Optional<KafkaProducerService> kafkaProducerService;
    private final ObjectMapper objectMapper;

    public JobPostService(JobPostRepository jobPostRepository,
                          Optional<KafkaProducerService> kafkaProducerService,
                          ObjectMapper objectMapper) {
        this.jobPostRepository = jobPostRepository;
        this.kafkaProducerService = kafkaProducerService;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public JobPostDTO createJobPost(JobPostDTO dto, String createdBy) {
        JobPost entity = JobPost.builder()
                .id(UUID.randomUUID().toString())
                .jobNumber("JOB-" + System.currentTimeMillis())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .department(dto.getDepartment())
                .location(dto.getLocation())
                .status("OPEN")
                .createdBy(createdBy)
                .createdAt(LocalDateTime.now())
                .ticketId(dto.getTicketId())
                .build();

        JobPost saved = jobPostRepository.save(entity);

        // Send Kafka event only if producer bean exists
        kafkaProducerService.ifPresent(producer -> {
            try {
                String eventJson = objectMapper.writeValueAsString(
                        new JobPostCreatedEvent(saved.getId(), saved.getTitle(), saved.getDepartment())
                );
                producer.sendEvent(eventJson);
            } catch (Exception ignored) {
            }
        });

        return toDTO(saved);
    }

    public JobPostDTO getJobPostById(String id) {
        JobPost entity = jobPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Job post not found"));
        return toDTO(entity);
    }

    public List<JobPostDTO> getAllOpenJobPosts() {
        return jobPostRepository.findByStatus("OPEN")
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<JobPostDTO> getJobPostsByCreator(String createdBy) {
        return jobPostRepository.findByCreatedBy(createdBy)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateJobPostStatus(String id, String status) {
        JobPost jobPost = jobPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Job post not found"));

        String normalized = status.toUpperCase();
        if (!List.of("OPEN", "CLOSED").contains(normalized)) {
            throw new IllegalArgumentException("Invalid job post status: " + status);
        }

        jobPost.setStatus(normalized);
        jobPostRepository.save(jobPost);
    }

    private JobPostDTO toDTO(JobPost entity) {
        JobPostDTO dto = new JobPostDTO();
        dto.setId(entity.getId());
        dto.setJobNumber(entity.getJobNumber());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setDepartment(entity.getDepartment());
        dto.setLocation(entity.getLocation());
        dto.setStatus(entity.getStatus());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setTicketId(entity.getTicketId());
        return dto;
    }
}
