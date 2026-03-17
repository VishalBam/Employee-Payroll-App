package com.employee.payroll.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.payroll.Entity.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Active employees
    List<Employee> findByActiveTrue();

    // Search by name
    List<Employee> findByNameContainingIgnoreCase(String name);

    // Department filter
    List<Employee> findByDepartmentIgnoreCase(String department);

    // Hire date
    List<Employee> findByHireDate(LocalDate hireDate);

    // Combined filters
    List<Employee> findByNameContainingIgnoreCaseAndDepartmentIgnoreCase(String name, String department);

    List<Employee> findByNameContainingIgnoreCaseAndHireDate(String name, LocalDate hireDate);

    List<Employee> findByDepartmentIgnoreCaseAndHireDate(String department, LocalDate hireDate);

    // Unique email
    Optional<Employee> findByEmail(String email);

    boolean existsByEmail(String email);
}