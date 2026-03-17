package com.employee.payroll.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.payroll.Entity.Timesheet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    // Get timesheets between dates
    List<Timesheet> findByEmployeeIdAndDateBetween(Long employeeId,
                                                   LocalDate startDate,
                                                   LocalDate endDate);

    // Get timesheets for specific day
    Optional<Timesheet> findByEmployeeIdAndDate(Long employeeId, LocalDate date);

    // Calculate total hours for payroll
    @Query("SELECT COALESCE(SUM(t.hoursWorked),0) FROM Timesheet t " +
           "WHERE t.employee.id = :employeeId " +
           "AND t.date BETWEEN :startDate AND :endDate")
    BigDecimal getTotalHoursWorked(Long employeeId,
                                   LocalDate startDate,
                                   LocalDate endDate);
    
//    Optional<Timesheet> findByEmployeeAndDate(Long employeeId, LocalDate date);
}