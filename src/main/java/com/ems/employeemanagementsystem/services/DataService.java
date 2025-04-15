/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ems.employeemanagementsystem.services;

import com.ems.employeemanagementsystem.entities.Employee;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

/**
 *
 * @author dennis
 */
@ApplicationScoped
public class DataService {
    List<Employee> employees;
    
   
    
    @PersistenceContext(unitName="EMS")
    EntityManager em;
    
    @PostConstruct
    public void init() {
        this.employees =  em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    
    public boolean employeeExist(int id){
         Long count = em.createNamedQuery("Employee.existsById", Long.class)
                       .setParameter("id", id)
                       .getSingleResult();
        return count > 0;
    }
    
    
    @Transactional
    public Employee createEmployee(String first_name, String last_name, String email, String department,Double salary ){
        Employee employee = new Employee();
        employee.setFirst_name(first_name);
        employee.setLast_name(last_name);
        employee.setEmail(email);
        employee.setDepartment(department);
        employee.setSalary(salary);

        em.persist(employee); 
        
        return employee;
    }
    
   

    public Employee getEmployeeById(int id) {
        try {
            return em.createNamedQuery("Employee.findById", Employee.class)
                     .setParameter("id", id)
                     .getSingleResult();
        } catch (Exception e) {
            return null; 
        }
    }

    @Transactional
    public boolean deleteEmployee(int id) {
        int deletedCount = em.createNamedQuery("Employee.deleteById")
                             .setParameter("id", id)
                             .executeUpdate();
        return deletedCount > 0;
    }

    @Transactional
    public Employee updateEmployee(int id, String first_name, String last_name, String email, String department, Double salary) {
         int updatedCount = em.createNamedQuery("Employee.updateById")
                             .setParameter("id", id)
                             .setParameter("first_name", first_name)
                             .setParameter("last_name", last_name)
                             .setParameter("email", email)
                             .setParameter("department", department)
                             .setParameter("salary", salary)
                             .executeUpdate();
         
        if (updatedCount > 0) {
        return em.createNamedQuery("Employee.findById", Employee.class)
                 .setParameter("id", id)
                 .getSingleResult();
        }
        
        return null;
    }
    
    
}
