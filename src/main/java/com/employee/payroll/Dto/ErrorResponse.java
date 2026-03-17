package com.employee.payroll.Dto;

import lombok.Data;
import java.time.Instant;
import java.util.Map;

@Data
public class ErrorResponse {

    private Instant timestamp;
    private int status;
    private String code;
    private String error;
    private String message;
    private String path;

    // For validation errors (DTO validation)
    private Map<String, String> errors;

    // Generic constructor
    public ErrorResponse(String message, String path) {
        this.timestamp = Instant.now();
        this.status = 400;
        this.code = "BAD_REQUEST";
        this.error = "Bad Request";
        this.message = message;
        this.path = path;
    }

    // Business-specific constructor
    public ErrorResponse(int status, String code, String message, String path) {
        this.timestamp = Instant.now();
        this.status = status;
        this.code = code;
        this.error = getErrorName(status);
        this.message = message;
        this.path = path;
    }

    // Constructor for validation errors
    public ErrorResponse(int status, String code, String message, String path, Map<String, String> errors) {
        this.timestamp = Instant.now();
        this.status = status;
        this.code = code;
        this.error = getErrorName(status);
        this.message = message;
        this.path = path;
        this.errors = errors;
    }

    private String getErrorName(int status) {
        return switch (status) {
            case 400 -> "Bad Request";
            case 401 -> "Unauthorized";
            case 403 -> "Forbidden";
            case 404 -> "Not Found";
            case 409 -> "Conflict";
            case 422 -> "Validation Error";
            case 500 -> "Internal Server Error";
            default -> "Error";
        };
    }
}
