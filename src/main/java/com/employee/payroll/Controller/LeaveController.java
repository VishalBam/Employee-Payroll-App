package com.employee.payroll.Controller;

import com.employee.payroll.Dto.LeaveRequestDto;
import com.employee.payroll.Entity.LeaveRequest;
import com.employee.payroll.Service.LeaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaves")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;

    @PostMapping("/request")
    public LeaveRequest requestLeave(
            @RequestBody @Valid LeaveRequestDto dto) {

        return leaveService.requestLeave(dto);
    }

    @PutMapping("/{id}/approve")
    public LeaveRequest approveLeave(@PathVariable Long id) {

        return leaveService.approveLeave(id);
    }

    @PutMapping("/{id}/reject")
    public LeaveRequest rejectLeave(@PathVariable Long id) {

        return leaveService.rejectLeave(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<LeaveRequest> getEmployeeLeaves(
            @PathVariable Long employeeId) {

        return leaveService.getEmployeeLeaves(employeeId);
    }
}