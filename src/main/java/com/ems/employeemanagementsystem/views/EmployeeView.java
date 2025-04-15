/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ems.employeemanagementsystem.views;

import com.ems.employeemanagementsystem.entities.Employee;
import com.ems.employeemanagementsystem.services.DataService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.primefaces.PrimeFaces;

/**
 *
 * @author dennis
 */
@Named
@ViewScoped
public class EmployeeView implements Serializable{
    
    private List<Employee> allEmployees;
    
    private Employee selectedEmployee;
    
    private List<Employee> selectedEmployees;
    
    @Inject 
    DataService dataService;


    
    
    @PostConstruct
    public void initialze(){
        this.allEmployees = dataService.getEmployees();
        this.selectedEmployees = new ArrayList<Employee>();
    }

    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    public List<Employee> getAllEmployees() {
        return allEmployees;
    }
    
    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public List<Employee> getSelectedEmployees() {
        return selectedEmployees;
    }

    public void setSelectedEmployees(List<Employee> selectedEmployees) {
        this.selectedEmployees = selectedEmployees;
    }
    
    
    public void openNew() {
        this.selectedEmployee = new Employee();
    }
    
    public boolean hasSelectedEmployees(){
       
         return this.selectedEmployees != null && !this.selectedEmployees.isEmpty();
         
    }
    
    
    
     public void saveEmployee() {
        if(this.selectedEmployee.getId() == null){
            Employee employee = dataService.createEmployee(this.selectedEmployee.getFirst_name(),
                this.selectedEmployee.getLast_name(),this.selectedEmployee.getEmail(), this.selectedEmployee.getDepartment(),this.selectedEmployee.getSalary());
            this.allEmployees.add(employee);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Employee Added"));
            
        }else{
            Employee result = dataService.updateEmployee(this.selectedEmployee.getId(),this.selectedEmployee.getFirst_name(),
                this.selectedEmployee.getLast_name(),this.selectedEmployee.getEmail(), this.selectedEmployee.getDepartment(),this.selectedEmployee.getSalary());
            
            if (result != null) {
                for (int i = 0; i < allEmployees.size(); i++) {
                    if (allEmployees.get(i).getId().equals(result.getId())) {
                        allEmployees.set(i, result);
                        break;
                        }
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Updated successfully","Employee updated"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update failed", "Employee not found"));
            }
            
        }
        
      
        PrimeFaces.current().executeScript("PF('manageEmployeeDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-employees");
        
    }
     
     
     
    public void deleteEmployee(){
        Boolean result = dataService.deleteEmployee(this.selectedEmployee.getId());
        
        if(result){
            for (int i = 0; i < allEmployees.size(); i++) {
                    if (allEmployees.get(i).getId().equals(this.selectedEmployee.getId())) {
                        allEmployees.remove(this.selectedEmployee);
                        break;
                        }
             }
            this.selectedEmployee = null;
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Deleted successfully","Employee deleted"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete failed", "An error occured"));
        }
        
        
        
        
        
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Employee deleted"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-employees");
        
    }
    
    public void validateEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String email = (String) value;

        if (email == null || email.isEmpty()) {
            return;
        }

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(email).matches()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Invalid email format", "Please enter a valid email address"));
        }
    }
    
}
