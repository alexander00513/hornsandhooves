/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.web.validation;

import com.hornsandhooves.web.form.EmployeeForm;
import org.junit.*;
import static org.junit.Assert.*;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

/**
 *
 * @author Alexander
 */
public class EmployeeValidationTest {
    
    private EmployeeValidation employeeValidation;
    private EmployeeForm employeeForm;
    
    
    @Before
    public void setUp() {
        employeeValidation = new EmployeeValidation();
        employeeForm = new EmployeeForm();
    }
    
    /**
     * Test of firstNameValidate method, of class EmployeeValidation.
     */
    @Test
    public void testFirstNameValidate() {
        System.out.println("firstNameValidate");
        
        employeeForm.setFirstName("");
        Errors result = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.firstNameValidate(result, employeeForm);
        assertTrue(result.hasErrors());
        
        employeeForm.setFirstName("   ");
        Errors result_1 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.firstNameValidate(result_1, employeeForm);
        assertTrue(result_1.hasErrors());
        
        employeeForm.setFirstName("testDatatestDatatestDatatestDatatestDatatestData"
                + "testDatatestDatatestDatatestDatatestDatatestDatatestData"
                + "testDatatestDatatestDatatestDatatestDatatestDatatestData"
                + "testDatatestDatatestDatatestDatatestDatatestDatatestData"
                + "testDatatestDatatestDatatestDatatestDatatestDatatestData");
        Errors result_2 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.firstNameValidate(result_2, employeeForm);
        assertTrue(result_2.hasErrors());
        
        employeeForm.setFirstName("testData$#!");
        Errors result_3 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.firstNameValidate(result_3, employeeForm);
        assertTrue(result_3.hasErrors());
        System.out.println("firstNameValidate - OK");
        
        employeeForm.setFirstName("testData");
        Errors result_4 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.firstNameValidate(result_4, employeeForm);
        assertFalse(result_4.hasErrors());
        System.out.println("firstNameValidate - OK");
    }

    /**
     * Test of lastNameValidate method, of class EmployeeValidation.
     */
    @Test
    public void testLastNameValidate() {
        System.out.println("lastNameValidate");
        employeeForm.setLastName("");
        Errors result = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.lastNameValidate(result, employeeForm);
        assertTrue(result.hasErrors());
        
        employeeForm.setLastName("   ");
        Errors result_1 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.lastNameValidate(result_1, employeeForm);
        assertTrue(result_1.hasErrors());
        
        employeeForm.setLastName("testDatatestDatatestDatatestDatatestDatatestData"
                + "testDatatestDatatestDatatestDatatestDatatestDatatestData"
                + "testDatatestDatatestDatatestDatatestDatatestDatatestData"
                + "testDatatestDatatestDatatestDatatestDatatestDatatestData"
                + "testDatatestDatatestDatatestDatatestDatatestDatatestData");
        Errors result_2 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.lastNameValidate(result_2, employeeForm);
        assertTrue(result_2.hasErrors());
        
        employeeForm.setLastName("testData$#!");
        Errors result_3 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.lastNameValidate(result_3, employeeForm);
        assertTrue(result_3.hasErrors());
        System.out.println("firstNameValidate - OK");
        
        employeeForm.setLastName("testData");
        Errors result_4 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.lastNameValidate(result_4, employeeForm);
        assertFalse(result_4.hasErrors());
        System.out.println("lastNameValidate - OK");
    }

    /**
     * Test of salaryValidate method, of class EmployeeValidation.
     */
    @Test
    public void testSalaryValidate() {
        System.out.println("salaryValidate");
        employeeForm.setSalary("");
        Errors result = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.salaryValidate(result, employeeForm);
        assertTrue(result.hasErrors());
        
        employeeForm.setSalary("   ");
        Errors result_1 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.salaryValidate(result_1, employeeForm);
        assertTrue(result_1.hasErrors());
        
        employeeForm.setSalary("000");
        Errors result_2 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.salaryValidate(result_2, employeeForm);
        assertTrue(result_2.hasErrors());
        
        employeeForm.setSalary("testData$!#");
        Errors result_3 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.salaryValidate(result_3, employeeForm);
        assertTrue(result_3.hasErrors());
        
        employeeForm.setSalary("123456789123456.654");
        Errors result_4 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.salaryValidate(result_4, employeeForm);
        assertTrue(result_4.hasErrors());
        
        employeeForm.setSalary("1234567891234567.65");
        Errors result_5 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.salaryValidate(result_5, employeeForm);
        assertTrue(result_5.hasErrors());
        
        employeeForm.setSalary("123456789123456.65");
        Errors result_6 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.salaryValidate(result_6, employeeForm);
        assertFalse(result_6.hasErrors());
        System.out.println("salaryValidate - OK");
    }

    /**
     * Test of dateValidation method, of class EmployeeValidation.
     */
    @Test
    public void testDateValidation() throws Exception {
        System.out.println("birthdateValidation");
        employeeForm.setBirthdate("");
        Errors result = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.birthdateValidate(result, employeeForm);
        assertTrue(result.hasErrors());
        
        employeeForm.setBirthdate("   ");
        Errors result_1 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.birthdateValidate(result_1, employeeForm);
        assertTrue(result_1.hasErrors());
        
        employeeForm.setBirthdate("testData");
        Errors result_2 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.birthdateValidate(result_2, employeeForm);
        assertTrue(result_2.hasErrors());
        
        employeeForm.setBirthdate("01.01.2000testData");
        Errors result_3 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.birthdateValidate(result_3, employeeForm);
        assertTrue(result_3.hasErrors());
        
        employeeForm.setBirthdate("32.07.2000");
        Errors result_4 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.birthdateValidate(result_4, employeeForm);
        assertTrue(result_4.hasErrors());
        
        employeeForm.setBirthdate("1.01.9999");
        Errors result_5 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.birthdateValidate(result_5, employeeForm);
        assertTrue(result_5.hasErrors());
        
        employeeForm.setBirthdate("01.01.1800");
        Errors result_6 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.birthdateValidate(result_6, employeeForm);
        assertTrue(result_6.hasErrors());
        
        employeeForm.setBirthdate("01.01.2000");
        Errors result_7 = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        employeeValidation.birthdateValidate(result_7, employeeForm);
        assertFalse(result_7.hasErrors());
        System.out.println("birthdateValidation - OK");
    }
}
