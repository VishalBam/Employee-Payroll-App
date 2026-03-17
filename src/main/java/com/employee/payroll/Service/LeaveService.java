package com.employee.payroll.Service;

import com.employee.payroll.Dto.LeaveRequestDto;
import com.employee.payroll.Entity.LeaveRequest;

import java.util.List;

public interface LeaveService {

    LeaveRequest requestLeave(LeaveRequestDto dto);

    LeaveRequest approveLeave(Long id);

    LeaveRequest rejectLeave(Long id);

    List<LeaveRequest> getEmployeeLeaves(Long employeeId);
}