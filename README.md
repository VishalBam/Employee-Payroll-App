# Employee Payroll App

## Project Overview

The **Employee Payroll App** is a backend application built using Spring Boot to automate payroll generation for employees. The system calculates salaries based on timesheet data within a given date range, reducing manual effort and improving accuracy.

This project was developed with a focus on **real-world backend practices**, including layered architecture, proper exception handling, validation, and API documentation.

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

### 🔹 Tools & Libraries

- Lombok (reducing boilerplate code)
- Jakarta Validation (data validation)
- Logging (SLF4J )
- Swagger (API documentation)
- Postman (API testing)

---

## How to Run the Project

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/Employee-Payroll-App.git
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

You can also test APIs using Postman.

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
 ├── util              # Helper / utility classes (include PDF generator)
 └── config            # Application configuration
      └── swagger      # Swagger configuration
---

---

## Challenges & Learnings

While building this project, I encountered and resolved several real-world issues:

- Faced 500 INTERNAL_SERVER_ERROR during API failures and resolved it by implementing proper exception handling and meaningful responses
- Handled validation errors using MethodArgumentNotValidException to return clear and structured validation messages
- Resolved issues related to incorrect JPA usage and query handling causing InvalidDataAccessApiUsageException
- Designed and implemented a Global Exception Handler (@ControllerAdvice) to avoid generic server errors and standardize API responses
- Managed invalid resource access using custom exceptions like EmployeeNotFoundException and PayrollNotFoundException
- Improved API reliability by converting internal errors into appropriate HTTP status codes (400, 404, etc.)
- Structured DTOs and entities properly to prevent exposing internal data and reduce coupling
- Debugged and fixed Git-related issues such as merge conflicts and repository setup during development
- These challenges helped me improve my understanding of **backend architecture and error handling in production-like systems**.

---

## Future Enhancements

- Add authentication & authorization using Spring Security (JWT)
- Add pagination and filtering
- Deploy application to cloud platform

---

## Author

**Vishal Bamrotwar**

---

## Note

This project reflects my hands-on experience with backend development, focusing on building scalable APIs, handling real-world scenarios, and writing maintainable code.
```
