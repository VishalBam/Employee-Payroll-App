package com.employee.payroll.Dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Data
public class TimesheetDto {

	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private Long id;

    @NotNull(message = "Employee ID is required")
    @Positive(message = "Employee ID must be positive")
    private Long employeeId;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in future")
    private LocalDate date;


    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal hoursWorked;

    @Size(max = 500, message = "Notes max 500 characters")
    private String notes;
    
    @JsonFormat(pattern = "HH:mm")
    @Schema(example = "09:00")
    private LocalTime punchIn;
    
    @JsonFormat(pattern = "HH:mm")
    @Schema(example = "18:30")
    private LocalTime punchOut;
}