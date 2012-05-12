/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.db.entity;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alexander
 */
public class EmployeeImplTest {
    
    private EmployeeImpl instance;
    
    
    @Before
    public void setUp() {
        instance = new EmployeeImpl();
    }

    /**
     * Test of getSetId method, of class EmployeeImpl.
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
     * Test of getSetFirstName method, of class EmployeeImpl.
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
     * Test of getSetLastName method, of class EmployeeImpl.
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
     * Test of getSetSalary method, of class EmployeeImpl.
     */
    @Test
    public void testGetSetSalary() {
        System.out.println("getSetSalary");
        double expResult = 3.3;
        instance.setSalary(expResult);
        double result = instance.getSalary();
        assertEquals(expResult, result, 0.0);
        System.out.println("getSetSalary - OK");
    }

    /**
     * Test of getSetBirthdate method, of class EmployeeImpl.
     */
    @Test
    public void testGetSetBirthdate() {
        System.out.println("getSetBirthdate");
        Date expResult = new Date();
        instance.setBirthdate(expResult);
        Date result = instance.getBirthdate();
        assertEquals(expResult, result);
        System.out.println("getSetBirthdate - OK");
    }

    /**
     * Test of getSetActive method, of class EmployeeImpl.
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
     * Test of getSetDivisionId method, of class EmployeeImpl.
     */
    @Test
    public void testGetDivisionId() {
        System.out.println("getSetDivisionId");
        DivisionImpl expResult = new DivisionImpl();
        instance.setDivisionId(expResult);
        DivisionImpl result = instance.getDivisionId();
        assertEquals(expResult, result);
        System.out.println("getSetDivisionId - OK");
    }
}
