/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.web.validation;

import com.hornsandhooves.web.form.DivisionForm;
import org.junit.*;
import static org.junit.Assert.*;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

/**
 *
 * @author Alexander
 */
public class DivisionValidationTest {
    
    private DivisionValidation divisionValidation;
    private DivisionForm divisionForm;

    
    @Before
    public void setUp() {
        divisionValidation = new DivisionValidation();
        divisionForm = new DivisionForm();
    }
    
    /**
     * Test of titleValidate method, of class DivisionValidation.
     */
    @Test
    public void testTitleValidate() {
        System.out.println("titleValidate");
        
        divisionForm.setTitle("");
        Errors result = new BeanPropertyBindingResult(divisionForm, "divisionForm");
        divisionValidation.titleValidate(result, divisionForm);
        assertTrue(result.hasErrors());
        
        divisionForm.setTitle("   ");
        Errors result_1 = new BeanPropertyBindingResult(divisionForm, "divisionForm");
        divisionValidation.titleValidate(result_1, divisionForm);
        assertTrue(result_1.hasErrors());
        
        divisionForm.setTitle("testDatatestDatatestDatatestDatatestDatatestData"
                + "testDatatestDatatestDatatestDatatestDatatestDatatestData"
                + "testDatatestDatatestDatatestDatatestDatatestDatatestData"
                + "testDatatestDatatestDatatestDatatestDatatestDatatestData"
                + "testDatatestDatatestDatatestDatatestDatatestDatatestData");
        Errors result_2 = new BeanPropertyBindingResult(divisionForm, "divisionForm");
        divisionValidation.titleValidate(result_2, divisionForm);
        assertTrue(result_2.hasErrors());
        
        divisionForm.setTitle("testData");
        Errors result_3 = new BeanPropertyBindingResult(divisionForm, "divisionForm");
        divisionValidation.titleValidate(result_3, divisionForm);
        assertFalse(result_3.hasErrors());
        System.out.println("titleValidate - OK");
    }
}
