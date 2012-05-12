/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.web.form;

import com.hornsandhooves.db.entity.IDivision;

/**
 * Класс формы для Employee
 * @author Alexander
 */
public class EmployeeForm {
    
    private Long id;
    
    private String firstName;
    
    private String lastName;
    
    private String salary;
    
    private String birthdate;
    
    private Boolean active;
    
    private Long divisionId;
    
    private IDivision division;
    

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public IDivision getDivision() {
        return division;
    }

    public void setDivision(IDivision division) {
        this.division = division;
    }

    public Long getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Long divisionId) {
        this.divisionId = divisionId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
