/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  dennis
 * Created: Apr 15, 2025
 */

CREATE DATABASE IF NOT EXISTS employeedb;
USE employeedb;

CREATE TABLE IF NOT EXISTS employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    department VARCHAR(255),
    salary DECIMAL(10, 2) NOT NULL
);


INSERT INTO employee (first_name, last_name, email, department, salary) VALUES
('Rono', 'Chebet', 'evelyn.chebet@example.com', 'Legal', 75000.00),
('Grace', 'Wanjiku', 'grace.wanjiku@example.com', 'HR', 65000.00),
('Dennis', 'Kariuki', 'dennis@gmail.com', 'Finance', 85000.00);
