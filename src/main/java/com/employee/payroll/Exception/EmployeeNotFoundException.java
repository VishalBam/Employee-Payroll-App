package com.employee.payroll.Exception;

public class EmployeeNotFoundException extends ResourceNotFoundException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(Long id) {
        super("Employee not found with id: " + id);
    }
}
