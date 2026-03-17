package com.employee.payroll.Service;

import com.employee.payroll.Dto.PayrollDto;
import com.employee.payroll.Dto.PayrollRequestDto;
import com.employee.payroll.Entity.Employee;
import com.employee.payroll.Entity.LeaveRequest;
import com.employee.payroll.Entity.LeaveStatus;
import com.employee.payroll.Entity.Payroll;
import com.employee.payroll.Exception.EmployeeNotFoundException;
import com.employee.payroll.Exception.InvalidPayrollDataException;
import com.employee.payroll.Exception.ResourceNotFoundException;
import com.employee.payroll.Repository.EmployeeRepository;
import com.employee.payroll.Repository.LeaveRepository;
import com.employee.payroll.Repository.PayrollRepository;
import com.employee.payroll.Repository.TimesheetRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class PayrollService {

    private final EmployeeRepository employeeRepository;
    private final PayrollRepository payrollRepository;
    private final TimesheetRepository timesheetRepository;
    private final LeaveRepository leaveRepository;

    public PayrollService(EmployeeRepository employeeRepository,
                          PayrollRepository payrollRepository,
                          TimesheetRepository timesheetRepository, LeaveRepository leaveRepository) {
        this.employeeRepository = employeeRepository;
        this.payrollRepository = payrollRepository;
        this.timesheetRepository = timesheetRepository;
		this.leaveRepository = leaveRepository;
    }

    public PayrollDto generatePayroll(PayrollRequestDto request) {

        Long employeeId = request.getEmployeeId();
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        if (payrollRepository.existsByEmployeeIdAndStartDateAndEndDate(
                employeeId, startDate, endDate)) {

            throw new InvalidPayrollDataException(
                    "Payroll already generated for this period");
        }

        BigDecimal totalHours = timesheetRepository
                .getTotalHoursWorked(employeeId, startDate, endDate);

        if (totalHours == null || totalHours.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidPayrollDataException("No timesheet data found");
        }

        BigDecimal hourlyRate = employee.getHourlyRate();

        if (hourlyRate == null || hourlyRate.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidPayrollDataException("Invalid hourly rate");
        }

        BigDecimal grossPay = totalHours.multiply(hourlyRate)
                .setScale(2, RoundingMode.HALF_UP);


        List<LeaveRequest> leaves =
                leaveRepository.findByEmployeeIdAndStatusAndStartDateBetween(
                        employeeId,
                        LeaveStatus.APPROVED,
                        startDate,
                        endDate
                );

        int leaveDays = leaves.size();

        BigDecimal perDaySalary = grossPay
                .divide(BigDecimal.valueOf(30), 2, RoundingMode.HALF_UP);

        BigDecimal leaveDeduction =
                perDaySalary.multiply(BigDecimal.valueOf(leaveDays));



        BigDecimal taxRate = new BigDecimal("0.10");

        BigDecimal taxAmount = grossPay.multiply(taxRate)
                .setScale(2, RoundingMode.HALF_UP);


        BigDecimal netPay = grossPay
                .subtract(taxAmount)
                .subtract(leaveDeduction)
                .setScale(2, RoundingMode.HALF_UP);


        Payroll payroll = new Payroll();

        payroll.setEmployee(employee);
        payroll.setStartDate(startDate);
        payroll.setEndDate(endDate);
        payroll.setHoursWorked(totalHours);
        payroll.setGrossPay(grossPay);
        payroll.setTaxAmount(taxAmount);
        
        payroll.setLeaveDeduction(leaveDeduction);

        payroll.setNetPay(netPay);

        Payroll savedPayroll = payrollRepository.save(payroll);

        PayrollDto response = new PayrollDto();

        response.setId(savedPayroll.getId());
        response.setEmployeeId(employee.getId());
        response.setEmployeeName(employee.getName());
        response.setStartDate(startDate);
        response.setEndDate(endDate);
        response.setHoursWorked(totalHours);
        response.setGrossPay(grossPay);
        response.setTaxAmount(taxAmount);

    
        response.setLeaveDeduction(leaveDeduction);

        response.setNetPay(netPay);
        response.setPayslipUrl(savedPayroll.getPayslipUrl());

        return response;
    }

    public List<Payroll> getEmployeePayrolls(Long employeeId) {
        return payrollRepository
                .findByEmployeeIdOrderByStartDateDesc(employeeId);
    }

    public Payroll getPayroll(Long id) {
        return payrollRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payroll Not Foun"));
    }
}