package com.company.tickert_service.exception;

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

/**
 * Global exception handler for ticket-service.
 * Always returns a simple JSON: {"message":"..."} with the right HTTP status.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Simple DTO used for all error responses
    public static class ApiError {
        private final String message;

        public ApiError(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    // ---- 400: validation errors on @Valid @RequestBody / @PathVariable / @RequestParam ----
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

    // ---- 400: custom bad request (you already use jakarta.ws.rs.BadRequestException in user-service; you can use it here too) ----
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex) {
        String msg = Objects.requireNonNullElse(ex.getMessage(), "Bad request");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(msg));
    }

    // ---- 404: not found (if you use jakarta.ws.rs.NotFoundException) ----
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(NotFoundException ex) {
        String msg = Objects.requireNonNullElse(ex.getMessage(), "Resource not found");
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(msg));
    }

    // ---- 403: access denied by Spring Security ----
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ApiError("Access denied"));
    }

    // ---- 405: wrong HTTP method ----
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        String msg = "Method not allowed";
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ApiError(msg));
    }

    // ---- 415: unsupported media type ----
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiError> handleMediaType(HttpMediaTypeNotSupportedException ex) {
        String msg = "Unsupported media type";
        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(new ApiError(msg));
    }

    // ---- 500: any other RuntimeException (including your 'Ticket not found' RuntimeException) ----
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntime(RuntimeException ex) {
        // For internal errors you might NOT want to expose raw message.
        // If you prefer generic text, change to: "Unexpected error".
        String msg = Objects.requireNonNullElse(ex.getMessage(), "Unexpected error");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError(msg));
    }

    // ---- 500: fallback for any other uncaught Exception ----
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex) {
        String msg = Objects.requireNonNullElse(ex.getMessage(), "Unexpected error");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError(msg));
    }
}