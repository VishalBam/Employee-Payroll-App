package com.employee.payroll.Service;

import com.employee.payroll.Dto.EmployeeDto;
import com.employee.payroll.Entity.Employee;
import com.employee.payroll.Exception.EmployeeNotFoundException;
import com.employee.payroll.Repository.EmployeeRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findByActiveTrue();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public EmployeeDto createEmployee(EmployeeDto dto) {

        Employee employee = new Employee();

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setPosition(dto.getPosition());
        employee.setHourlyRate(dto.getHourlyRate());
        employee.setHireDate(dto.getHireDate());
        employee.setActive(dto.isActive());

        Employee saved = employeeRepository.save(employee);

        EmployeeDto response = new EmployeeDto();

        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setEmail(saved.getEmail());
        response.setDepartment(saved.getDepartment());
        response.setPosition(saved.getPosition());
        response.setHourlyRate(saved.getHourlyRate());
        response.setHireDate(saved.getHireDate());
        response.setActive(saved.isActive());

        return response;
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setPosition(employeeDetails.getPosition());
        employee.setHourlyRate(employeeDetails.getHourlyRate());
        employee.setHireDate(employeeDetails.getHireDate());
        employee.setActive(employeeDetails.isActive());

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employee.setActive(false);
        employeeRepository.save(employee);
    }

    public List<Employee> searchEmployees(String name, String department, LocalDate hireDate) {

        if (name != null && department != null) {
            return employeeRepository
                    .findByNameContainingIgnoreCaseAndDepartmentIgnoreCase(name, department);
        }

        if (name != null && hireDate != null) {
            return employeeRepository
                    .findByNameContainingIgnoreCaseAndHireDate(name, hireDate);
        }

        if (department != null && hireDate != null) {
            return employeeRepository
                    .findByDepartmentIgnoreCaseAndHireDate(department, hireDate);
        }

        if (name != null) {
            return employeeRepository.findByNameContainingIgnoreCase(name);
        }

        if (department != null) {
            return employeeRepository.findByDepartmentIgnoreCase(department);
        }

        if (hireDate != null) {
            return employeeRepository.findByHireDate(hireDate);
        }

        return employeeRepository.findAll();
    }
}