package com.employee.payroll.Controller;

import com.employee.payroll.Dto.PayrollDto;
import com.employee.payroll.Dto.PayrollRequestDto;
import com.employee.payroll.Entity.Payroll;
import com.employee.payroll.Service.PayrollService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payroll")
@CrossOrigin(origins = "*")
public class PayrollController {

    private final PayrollService payrollService;

    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    @PostMapping("/generate")
    public ResponseEntity<PayrollDto> generatePayroll(
            @Valid @RequestBody PayrollRequestDto request) {

        PayrollDto payroll = payrollService.generatePayroll(request);

        return ResponseEntity.ok(payroll);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Payroll>> getEmployeePayrolls(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                payrollService.getEmployeePayrolls(employeeId)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payroll> getPayroll(@PathVariable Long id) {

        return ResponseEntity.ok(
                payrollService.getPayroll(id)
        );
    }
}