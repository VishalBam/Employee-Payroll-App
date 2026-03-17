package com.employee.payroll.Controller;

import com.employee.payroll.Dto.TimesheetDto;
import com.employee.payroll.Service.TimesheetService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/timesheets")
@CrossOrigin(origins = "*")
public class TimesheetController {

    private final TimesheetService timesheetService;

    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    @PostMapping
    public ResponseEntity<TimesheetDto> createTimesheet(
            @Valid @RequestBody TimesheetDto dto) {

        TimesheetDto saved = timesheetService.createTimesheet(dto);

        return ResponseEntity.ok(saved);
    }
}