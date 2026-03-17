package com.employee.payroll.Service;

import com.employee.payroll.Entity.Employee;
import com.employee.payroll.Entity.Payroll;
import com.employee.payroll.Repository.PayrollRepository;
import com.employee.payroll.Util.PdfGenerator;

import org.springframework.stereotype.Service;

@Service
public class PayslipServiceImpl implements PayslipService {

    private final PayrollRepository payrollRepository;
    private final PdfGenerator pdfGenerator;

    public PayslipServiceImpl(PayrollRepository payrollRepository,
                              PdfGenerator pdfGenerator) {
        this.payrollRepository = payrollRepository;
        this.pdfGenerator = pdfGenerator;
    }

    @Override
    public byte[] generatePayslip(Long payrollId) {

        Payroll payroll = payrollRepository.findById(payrollId)
                .orElseThrow(() -> new RuntimeException("Payroll not found"));

        Employee employee = payroll.getEmployee();

        return pdfGenerator.generatePayslip(employee, payroll);
    }
}
