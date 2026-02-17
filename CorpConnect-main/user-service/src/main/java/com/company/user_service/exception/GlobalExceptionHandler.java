package com.company.user_service.exception;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public static class ApiError {
        private final String message;

        public ApiError(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String msg;
        if (fieldError != null) {
            msg = fieldError.getField() + " " + fieldError.getDefaultMessage();
        } else {
            msg = "Validation failed";
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(msg));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex) {
        String msg = Objects.requireNonNullElse(ex.getMessage(), "Bad request");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(msg));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(NotFoundException ex) {
        String msg = Objects.requireNonNullElse(ex.getMessage(), "Resource not found");
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(msg));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ApiError("Access denied"));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ApiError("Method not allowed"));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiError> handleMediaType(HttpMediaTypeNotSupportedException ex) {
        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(new ApiError("Unsupported media type"));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntime(RuntimeException ex) {
        String msg = Objects.requireNonNullElse(ex.getMessage(), "Unexpected error");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError(msg));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex) {
        String msg = Objects.requireNonNullElse(ex.getMessage(), "Unexpected error");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError(msg));
    }
}