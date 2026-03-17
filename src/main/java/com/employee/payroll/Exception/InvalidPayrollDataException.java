package com.employee.payroll.Exception;

public class InvalidPayrollDataException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPayrollDataException(String message) {
        super(message);
    }
}
