package com.employee.payroll.Util;

import com.employee.payroll.Entity.Employee;
import com.employee.payroll.Entity.Payroll;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

@Component
public class PdfGenerator {

    public byte[] generatePayslip(Employee employee, Payroll payroll) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            Document document = new Document();

            PdfWriter.getInstance(document, out);

            document.open();

            // Title
            Paragraph title = new Paragraph("EMPLOYEE PAYSLIP");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Company: ABC Pvt Ltd"));
            document.add(new Paragraph("-------------------------------------------"));

            // Employee Details
            document.add(new Paragraph("Employee Name: " + employee.getName()));
            document.add(new Paragraph("Employee ID: " + employee.getId()));
            document.add(new Paragraph("Pay Period: "
                    + payroll.getStartDate() + " to "
                    + payroll.getEndDate()));

            document.add(new Paragraph(" "));

            // Salary Table
            Paragraph salaryHeading = new Paragraph("Salary Breakdown");
            salaryHeading.setAlignment(Element.ALIGN_CENTER);
            document.add(salaryHeading);

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);

            PdfPCell header1 = new PdfPCell(new Phrase("Salary Component"));
            PdfPCell header2 = new PdfPCell(new Phrase("Amount"));

            table.addCell(header1);
            table.addCell(header2);

            table.addCell("Hours Worked");
            table.addCell(payroll.getHoursWorked().toString());

            table.addCell("Gross Pay");
            table.addCell(payroll.getGrossPay().toString());

            table.addCell("Tax Amount");
            table.addCell(payroll.getTaxAmount().toString());

            table.addCell("Leave Deduction");
            table.addCell(payroll.getLeaveDeduction().toString());

            table.addCell("Net Pay");
            table.addCell(payroll.getNetPay().toString());

            document.add(table);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Generated Date: " + LocalDate.now()));

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Authorized Signature"));

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }
}
