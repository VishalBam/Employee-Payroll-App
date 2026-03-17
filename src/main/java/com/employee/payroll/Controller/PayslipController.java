package com.employee.payroll.Controller;

import com.employee.payroll.Service.PayslipService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payslip")
public class PayslipController {

    private final PayslipService payslipService;

    public PayslipController(PayslipService payslipService) {
        this.payslipService = payslipService;
    }

    @GetMapping("/{payrollId}")
    public ResponseEntity<byte[]> downloadPayslip(@PathVariable Long payrollId) {

        byte[] pdf = payslipService.generatePayslip(payrollId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=payslip.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
