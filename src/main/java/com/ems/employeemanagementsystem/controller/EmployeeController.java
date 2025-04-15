/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ems.employeemanagementsystem.controller;

import com.ems.employeemanagementsystem.entities.Employee;
import com.ems.employeemanagementsystem.services.DataService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

/**
 *
 * @author dennis
 */
@RequestScoped
@Named
public class EmployeeController {
    
    @Inject 
    DataService dataservice;
    
    private List<Employee> allEmployees;
    
    @PostConstruct
    public void initialze(){
        this.allEmployees = dataservice.getEmployees();
    }

    public List<Employee> getAllEmployees() {
        return allEmployees;
    }
    
}
