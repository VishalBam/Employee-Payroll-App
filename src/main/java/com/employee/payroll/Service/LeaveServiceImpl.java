package com.employee.payroll.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.payroll.Dto.LeaveRequestDto;
import com.employee.payroll.Entity.LeaveRequest;
import com.employee.payroll.Entity.LeaveStatus;
import com.employee.payroll.Repository.LeaveRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;

    @Override
    public LeaveRequest requestLeave(LeaveRequestDto dto) {

        LeaveRequest leave = new LeaveRequest();

        leave.setEmployeeId(dto.getEmployeeId());
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setType(dto.getType());
        leave.setStatus(LeaveStatus.PENDING);
        leave.setRequestDate(LocalDate.now());

        return leaveRepository.save(leave);
    }

    @Override
    public LeaveRequest approveLeave(Long id) {

        LeaveRequest leave = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leave.setStatus(LeaveStatus.APPROVED);

        return leaveRepository.save(leave);
    }

    @Override
    public LeaveRequest rejectLeave(Long id) {

        LeaveRequest leave = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leave.setStatus(LeaveStatus.REJECTED);

        return leaveRepository.save(leave);
    }

    @Override
    public List<LeaveRequest> getEmployeeLeaves(Long employeeId) {

        return leaveRepository.findByEmployeeId(employeeId);
    }
}
