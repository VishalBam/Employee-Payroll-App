# Employee Payroll App

---

## Project Overview

The **Employee Payroll App** is a backend application built using Spring Boot to automate payroll generation for employees. The system calculates salaries based on timesheet data within a given date range, reducing manual effort and improving accuracy.

This project is built with a focus on **real-world backend practices**, including layered architecture, proper exception handling, validation, and API documentation.

---

## Key Features

- Generate payroll for employees based on selected date range
- Timesheet-driven salary calculation logic
- Generate detailed payslips for employees
- Export payslips as PDF documents for download and record-keeping
- RESTful APIs for employee, payroll, and timesheet management
- Clean separation using DTO pattern
- Centralized global exception handling
- Input validation using Jakarta Validation
- API documentation using Swagger UI
- Logging for better debugging and traceability

---

## Tech Stack

- **Backend:** Java, Spring Boot
- **Database:** PostgreSQL
- **ORM:** Spring Data JPA (Hibernate)
- **Build Tool:** Maven

### Tools & Libraries

- Lombok (reducing boilerplate code)
- Jakarta Validation (data validation)
- Logging (SLF4J)
- Swagger (API documentation)
- Postman (API testing)

---

## How to Run the Project

### 1. Clone the Repository

```bash
git clone https://github.com/VishalBam/Employee-Payroll-App.git
cd Employee-Payroll-App
```

---

### 2. Configure Database

Update your `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/payroll_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 3. Run the Application

```bash
mvn spring-boot:run
```

Or run the main class:

```
EmployeePayrollAppApplication.java
```

---

### 4. Access Application

```
http://localhost:8080
```

---

## API Documentation

Swagger UI is integrated for easy API exploration:

```
http://localhost:8080/swagger-ui/index.html
```

<p align="center">
  <img src="screenshots/Swagger%201.png" width="300"/>
  <img src="screenshots/Swagger%202.png" width="300"/>
  <img src="screenshots/Swagger%203.png" width="300"/>
</p>
You can also test APIs using Postman.

---

## Application Workflow

The following steps outline the typical workflow for using the Employee Payroll App:

1. **Create Employee**
   Add a new employee to the system by providing necessary details such as name, role, and salary structure.

2. **Record Timesheets**
   Log working hours by creating timesheet entries for the employee.
   Multiple timesheets can be recorded for the same employee across different dates.

3. **Manage Leaves**
   Submit and manage employee leave requests. Leaves can be approved or rejected based on business rules.

4. **Generate Payroll**
   Generate payroll for a specific employee within a selected date range.
   The system calculates salary based on timesheets and approved leaves.

5. **Generate Payslip (PDF)**
   Generate a detailed payslip for the employee, including salary breakdown.
   The payslip can be downloaded as a **PDF document** for record-keeping and sharing.

---

This workflow demonstrates how the application handles end-to-end payroll processing, from employee onboarding to payslip generation.

---

## Project Structure

```
src/
 ├── controller        # REST API endpoints
 ├── service           # Business logic
 ├── repository        # Database access layer (JPA)
 ├── dto               # Data Transfer Objects
 ├── entity            # Database entities
 ├── exception         # Custom exceptions & global handler
 ├── util              # Helper / utility classes (includes PDF generator)
 └── config            # Application configuration
      └── swagger      # Swagger configuration
```

---

## Challenges & Learnings

While building this project, I encountered and resolved several real-world issues:

- Faced **500 INTERNAL_SERVER_ERROR** during API failures and resolved it by implementing proper exception handling and meaningful responses
- Handled validation errors using **MethodArgumentNotValidException** to return clear and structured validation messages
- Resolved issues related to incorrect JPA usage causing **InvalidDataAccessApiUsageException**
- Designed and implemented a **Global Exception Handler (@ControllerAdvice)** to standardize API error responses
- Managed invalid resource access using custom exceptions like **EmployeeNotFoundException** and **PayrollNotFoundException**
- Improved API reliability by converting internal errors into appropriate HTTP status codes (400, 404, etc.)
- Structured DTOs and entities properly to avoid exposing internal data
- Resolved Git issues such as merge conflicts and repository setup

These challenges helped me strengthen my understanding of **backend architecture and production-level error handling**.

---

## Future Enhancements

- Add authentication & authorization using Spring Security (JWT)
- Add pagination and filtering
- Deploy application to a cloud platform

---

## Author

**Vishal Bamrotwar**

---

## Note

This project demonstrates hands-on experience with backend development, focusing on scalable API design, real-world problem solving, and maintainable architecture.
