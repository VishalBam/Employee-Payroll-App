package com.employee.payroll.Service;

import com.employee.payroll.Dto.TimesheetDto;
import com.employee.payroll.Entity.Employee;
import com.employee.payroll.Entity.Timesheet;
import com.employee.payroll.Repository.EmployeeRepository;
import com.employee.payroll.Repository.TimesheetRepository;

import java.time.Duration;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class TimesheetService {

    private final TimesheetRepository timesheetRepository;
    private final EmployeeRepository employeeRepository;

    public TimesheetService(TimesheetRepository timesheetRepository,
                            EmployeeRepository employeeRepository) {
        this.timesheetRepository = timesheetRepository;
        this.employeeRepository = employeeRepository;
    }

    public TimesheetDto createTimesheet(TimesheetDto dto) {

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        
        //Validation for Time
        if (dto.getPunchIn() == null || dto.getPunchOut() == null) {
            throw new RuntimeException("Punch-in and Punch-out are required");
        }

        if (dto.getPunchOut().isBefore(dto.getPunchIn())) {
            throw new RuntimeException("Punch-out cannot be before punch-in");
        }

        Duration timeDuration = Duration.between(dto.getPunchIn(), dto.getPunchOut());

        if (timeDuration.toMinutes() > 16 * 60) {
            throw new RuntimeException("Working hours exceed allowed limit");
        }

        // Prevent duplicate attendance
        Optional<Timesheet> existing =
                timesheetRepository.findByEmployeeIdAndDate(dto.getEmployeeId(), dto.getDate());

        if(existing.isPresent()){
            throw new RuntimeException("Attendance already marked for this date");
        }

        Timesheet timesheet = new Timesheet();

        timesheet.setEmployee(employee);
        timesheet.setDate(dto.getDate());
        timesheet.setPunchIn(dto.getPunchIn());
        timesheet.setPunchOut(dto.getPunchOut());

        // Calculate hours automatically
        if(dto.getPunchIn() != null && dto.getPunchOut() != null) {

            Duration duration = Duration.between(
                    dto.getPunchIn(),
                    dto.getPunchOut()
            );

            double hours = duration.toMinutes() / 60.0;

            BigDecimal hoursWorked = BigDecimal.valueOf(hours);

            timesheet.setHoursWorked(hoursWorked);
        }

        timesheet.setNotes(dto.getNotes());

        Timesheet saved = timesheetRepository.save(timesheet);

        dto.setId(saved.getId());
        dto.setHoursWorked(saved.getHoursWorked());

        return dto;
    }
}