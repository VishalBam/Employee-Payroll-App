package com.employee.payroll.Repository;

import com.employee.payroll.Entity.LeaveRequest;
import com.employee.payroll.Entity.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRepository extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployeeId(Long employeeId);

    List<LeaveRequest> findByEmployeeIdAndStatus(Long employeeId, LeaveStatus status);

    List<LeaveRequest> findByEmployeeIdAndStartDateBetween(
            Long employeeId,
            LocalDate startDate,
            LocalDate endDate
    );
    List<LeaveRequest> findByEmployeeIdAndStatusAndStartDateBetween(
    		Long employeeId,
            LeaveStatus leaveStatus,
            LocalDate startDate,
            LocalDate endDate
    );
}