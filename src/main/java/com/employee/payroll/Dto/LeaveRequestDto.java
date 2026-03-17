package com.employee.payroll.Dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LeaveRequestDto {

	 @NotNull(message = "Employee ID is required")
	    private Long employeeId;

	    @NotNull(message = "Start date is required")
	    private LocalDate startDate;

	    @NotNull(message = "End date is required")
	    private LocalDate endDate;

	    private String type;
}
