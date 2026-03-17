package com.employee.payroll.Dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PayrollRequestDto {

    @NotNull(message = "Employee ID is required")
    @Positive(message = "Employee ID must be positive")
    private Long employeeId;

    @NotNull(message = "Start date is required")
    @PastOrPresent(message = "Start date cannot be in future")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @PastOrPresent(message = "End date cannot be in future")
    private LocalDate endDate;
}