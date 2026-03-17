package com.employee.payroll.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.employee.payroll.Dto.ErrorResponse;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFound(EmployeeNotFoundException e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse(e.getMessage(), getRequestPath(request)));
    }
    
    @ExceptionHandler(InvalidPayrollDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidData(InvalidPayrollDataException e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(e.getMessage(), getRequestPath(request)));
    }
    
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException e, WebRequest request) {
        String message = "Invalid parameter: " + e.getName() + " = " + e.getValue();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(message, getRequestPath(request)));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse("Server error: " + e.getClass().getSimpleName(), getRequestPath(request)));
    }
    
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResource(
            DuplicateResourceException e, WebRequest request) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(e.getMessage(), getRequestPath(request)));
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            org.springframework.web.bind.MethodArgumentNotValidException ex,
            WebRequest request) {

        String message = ex.getBindingResult()
                           .getFieldError()
                           .getDefaultMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(message, getRequestPath(request)));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            ResourceNotFoundException e,
            WebRequest request) {

        ErrorResponse response = new ErrorResponse(
                404,
                "RESOURCE_NOT_FOUND",
                e.getMessage(),
                getRequestPath(request)
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    private String getRequestPath(WebRequest request) {
        String description = request.getDescription(false);
        return description.replace("uri=", "");
    }

}

