package com.company.hiring_service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorResponse {
    private String code;
    private String message;
}