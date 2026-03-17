package com.employee.payroll.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Entity
@Table(name = "timesheets")
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many timesheets belong to one employee
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonBackReference
    private Employee employee;

    @Column(nullable = false)
    private LocalDate date;
    
    @JsonFormat(pattern = "HH:mm")
    @Schema(example = "09:00")
    private LocalTime punchIn;
    
    @JsonFormat(pattern = "HH:mm")
    @Schema(example = "18:30")
    private LocalTime punchOut;
    
    private BigDecimal hoursWorked;

    private String notes;
}
