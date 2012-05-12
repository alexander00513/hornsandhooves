/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.common.tools;

import com.hornsandhooves.db.entity.DivisionImpl;
import com.hornsandhooves.db.entity.EmployeeImpl;
import com.hornsandhooves.db.entity.IEmployee;
import com.hornsandhooves.web.form.EmployeeForm;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс преобразования типов Employee и EmployeeForm
 * @author Alexander
 */
public class Conversions {
    
    /**
     * Метод преобразует объект employeeForm в employee с некоторыми преобразованиями
     * @param employeeForm
     * @param divisionService
     * @return employee
     * @throws ParseException 
     */
    public IEmployee getInstanceEmployee(EmployeeForm employeeForm) throws ParseException {
        IEmployee employee = new EmployeeImpl();
        employee.setActive(employeeForm.getActive());   
        Date birthdate = new SimpleDateFormat("dd.MM.yyyy").parse(employeeForm.getBirthdate());
        employee.setBirthdate(birthdate);
        DivisionImpl division = new DivisionImpl();
        division.setId(employeeForm.getDivisionId());
        employee.setDivisionId(division);
        employee.setFirstName((
                employeeForm.getFirstName().trim().substring(0,1).toUpperCase() 
                + employeeForm.getFirstName().trim().substring(1).toLowerCase())
            );
        employee.setId(employeeForm.getId());
        employee.setLastName(
                employeeForm.getLastName().trim().substring(0,1).toUpperCase()
                + employeeForm.getLastName().trim().substring(1).toLowerCase()
            );
        employee.setSalary(Double.valueOf(employeeForm.getSalary().trim()));
        return employee;
    }   
    /**
     * Метод преобразует объект employee в employeeForm с некоторыми преобразованиями
     * @param employee
     * @return employeeForm
     * @throws ParseException 
     */
    public EmployeeForm getInstanceEmployeeForm(IEmployee employee) throws ParseException {
        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setActive(employee.getActive());
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        employeeForm.setBirthdate(sdf.format(employee.getBirthdate()).toString());
        employeeForm.setDivisionId(employee.getDivisionId().getId());
        //Нужно для того, что бы вывести корректно страницу employee_read
        employeeForm.setDivision(employee.getDivisionId());
        employeeForm.setFirstName(employee.getFirstName());
        employeeForm.setId(employee.getId());
        employeeForm.setLastName(employee.getLastName());
        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        unusualSymbols.setDecimalSeparator('.');
        DecimalFormat format = new DecimalFormat("##############0.00", unusualSymbols);
        employeeForm.setSalary(format.format(employee.getSalary())); 
        return employeeForm;
    }   
}
