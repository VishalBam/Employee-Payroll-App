package com.employee.payroll.Dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

@Data
public class PayrollDto {
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "Employee ID is required")
    @Positive(message = "Employee ID must be positive")
    private Long employeeId;

    
    private String employeeName;

    @NotNull(message = "Start date is required")
    @PastOrPresent(message = "Start date cannot be in future")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @PastOrPresent(message = "End date cannot be in future")
    private LocalDate endDate;

    // Calculated fields
    private BigDecimal hoursWorked;

    private BigDecimal grossPay;

    private BigDecimal taxAmount;
    
    private BigDecimal leaveDeduction;

    private BigDecimal netPay;

    private String payslipUrl;
}