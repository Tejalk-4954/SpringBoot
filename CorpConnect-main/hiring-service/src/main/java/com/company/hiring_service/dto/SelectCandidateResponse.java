package com.company.hiring_service.dto;



import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SelectCandidateResponse {
    private String applicationId;
    private String message;
}
