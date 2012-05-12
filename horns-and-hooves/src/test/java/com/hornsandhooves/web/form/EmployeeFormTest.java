/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.web.form;

import com.hornsandhooves.db.entity.DivisionImpl;
import com.hornsandhooves.db.entity.IDivision;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alexander
 */
public class EmployeeFormTest {
    
    private EmployeeForm instance;
    

    @Before
    public void setUp() {
        instance = new EmployeeForm();
    }

    /**
     * Test of getSetActive method, of class EmployeeForm.
     */
    @Test
    public void testGetSetActive() {
        System.out.println("getSetActive");
        Boolean expResult = true;
        instance.setActive(expResult);
        Boolean result = instance.getActive();
        assertEquals(expResult, result);
        System.out.println("getSetActive - OK");
    }

    /**
     * Test of getSetBirthdate method, of class EmployeeForm.
     */
    @Test
    public void testGetSetBirthdate() {
        System.out.println("getSetBirthdate");
        String expResult = "testData";
        instance.setBirthdate(expResult);
        String result = instance.getBirthdate();
        assertEquals(expResult, result);
        System.out.println("getSetBirthdate - OK");
    }

    /**
     * Test of getSetDivision method, of class EmployeeForm.
     */
    @Test
    public void testGetSetDivision() {
        System.out.println("getSetDivision");
        IDivision expResult = new DivisionImpl();
        instance.setDivision(expResult);
        IDivision result = instance.getDivision();
        assertEquals(expResult, result);
        System.out.println("getSetDivision - OK");
    }

    /**
     * Test of getSetDivisionId method, of class EmployeeForm.
     */
    @Test
    public void testGetSetDivisionId() {
        System.out.println("getSetDivisionId");
        Long expResult = new Long(3);
        instance.setDivisionId(expResult);
        Long result = instance.getDivisionId();
        assertEquals(expResult, result);
        System.out.println("getSetDivisionId - OK");
    }

    /**
     * Test of getSetFirstName method, of class EmployeeForm.
     */
    @Test
    public void testGetSetFirstName() {
        System.out.println("getSetFirstName");
        String expResult = "testData";
        instance.setFirstName(expResult);
        String result = instance.getFirstName();
        assertEquals(expResult, result);
        System.out.println("getSetFirstName - OK");
    }

    /**
     * Test of getSetId method, of class EmployeeForm.
     */
    @Test
    public void testGetSetId() {
        System.out.println("getSetId");
        Long expResult = new Long(3);
        instance.setId(expResult);
        Long result = instance.getId();
        assertEquals(expResult, result);
        System.out.println("getSetId - OK");
    }

    /**
     * Test of getSetLastName method, of class EmployeeForm.
     */
    @Test
    public void testGetSetLastName() {
        System.out.println("getSetLastName");
        String expResult = "testData";
        instance.setLastName(expResult);
        String result = instance.getLastName();
        assertEquals(expResult, result);
        System.out.println("getSetLastName - OK");
    }

    /**
     * Test of getSetSalary method, of class EmployeeForm.
     */
    @Test
    public void testGetSetSalary() {
        System.out.println("getSetSalary");
        String expResult = "testData";
        instance.setSalary(expResult);
        String result = instance.getSalary();
        assertEquals(expResult, result);
        System.out.println("getSetSalary - OK");
    }
}
