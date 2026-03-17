package com.employee.payroll.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.payroll.Entity.Payroll;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    // Get payroll history of employee
    List<Payroll> findByEmployeeIdOrderByStartDateDesc(Long employeeId);

    // Find payroll for employee within specific period
    Optional<Payroll> findByEmployeeIdAndStartDateAndEndDate(Long employeeId,
                                                             LocalDate startDate,
                                                             LocalDate endDate);

    // Payrolls between date ranges
    List<Payroll> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

    // Prevent duplicate payroll
    boolean existsByEmployeeIdAndStartDateAndEndDate(Long employeeId,
                                                     LocalDate startDate,
                                                     LocalDate endDate);
}