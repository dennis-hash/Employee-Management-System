# Employee Management System


## Overview

A modern web application for managing employee records, built with Java EE technologies and PrimeFaces for the UI.

## Features

- **CRUD Operations**:
  - Create new employee records
  - Read/display all employees
  - Update existing employee information
  - Delete employees with confirmation

- **Validation**:
  - Required field validation
  - Email format validation
  - Unique email enforcement

- **User Interface**:
  - Responsive PrimeFaces components
  - Data tables with sorting/filtering
  - Modal dialogs for forms

## Technology Stack

| Component          | Technology       |
|--------------------|------------------|
| Language           | Java 11          |
| Web Framework      | Jakarta EE 9+    |
| UI Framework       | PrimeFaces 10+   |
| Application Server | Payara 6+        |
| Database           | MySQL 8+         |
| Build Tool         | Maven            |
| IDE                | NetBeans         |

## Setup Instructions

### Prerequisites

1. Java 11 JDK
2. Payara Server 6+
3. MySQL 8+
4. NetBeans IDE 

### Database Setup

1. Create MySQL database:
   ```sql
   CREATE DATABASE employeedb;
   USE employeedb;
   ```

2. Execute schema script:
   ```sql
   CREATE TABLE employee (
       id INT AUTO_INCREMENT PRIMARY KEY,
       first_name VARCHAR(255) NOT NULL,
       last_name VARCHAR(255) NOT NULL,
       email VARCHAR(255) NOT NULL UNIQUE,
       department VARCHAR(255),
       salary DECIMAL(10, 2) NOT NULL
   );
   ```

*(space for database table screenshot)*

### Payara Configuration

1. Create JDBC connection pool for MySQL
2. Set JNDI name to `jdbc/employeedb`
3. Test connection

*(space for Payara connection configuration screenshot)*

### Application Deployment

1. Clone the repository
2. Build with Maven: `mvn clean package`
3. Deploy WAR file to Payara
4. Access at: `http://localhost:8080/ems

## Application Walkthrough

1. **Employee Listing**  
   ![Employee List](https://example.com/employee-list.png)

2. **Add Employee**
   
   https://github.com/user-attachments/assets/263f4c71-a0a6-4c43-a4f4-383d2f80d4c4

3.  **Error Hndling**
   
    https://github.com/user-attachments/assets/331b7c53-c1ba-4f46-a1ee-65b2c8c99180

5. **Edit Employee**
     
   [![Edit Employee](https://example.com/edit-employee.png)](https://github.com/user-attachments/assets/9a6e624c-f7d6-4630-9e36-ef917bca7432)

7. **Delete Confirmation**
   
   https://github.com/user-attachments/assets/1e244917-ecba-49fa-af2a-94b5389b52ff


```
## Key Components

- **Employee Entity**: JPA entity mapping to database
- **DataService (DAO)**: Business logic and database operations
- **EmployeeView**: Managed bean for UI interaction
- **PrimeFaces UI**: XHTML pages with modern components

## License

MIT License - Free to use and modify
