package com.employee.payroll.Dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Data
public class EmployeeDto {
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be 2-50 characters")
    private String name;

    @Email(message = "Valid email required")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Department is required")
    @Size(max = 30, message = "Department max 30 characters")
    private String department;

    @NotBlank(message = "Position is required")
    @Size(max = 30, message = "Position max 30 characters")
    private String position;

    @NotNull(message = "Hourly rate required")
    @DecimalMin(value = "50.0", message = "Minimum ₹50/hour")
    @DecimalMax(value = "5000.0", message = "Maximum ₹5000/hour")
    private BigDecimal hourlyRate;

    @NotNull(message = "Hire date required")
    @PastOrPresent(message = "Hire date cannot be in future")
    private LocalDate hireDate;

    private boolean active;
}