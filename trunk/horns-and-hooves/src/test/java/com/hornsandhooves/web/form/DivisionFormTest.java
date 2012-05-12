/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.web.form;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alexander
 */
public class DivisionFormTest {
    
    private DivisionForm instance;
    
    
    @Before
    public void setUp() {
        instance = new DivisionForm();
    }

    /**
     * Test of getSetId method, of class DivisionForm.
     */
    @Test
    public void testGetId() {
        System.out.println("getSetId");
        Long expResult = new Long(3);
        instance.setId(expResult);
        Long result = instance.getId();
        assertEquals(expResult, result);
        System.out.println("getSetId - OK");
    }

    /**
     * Test of getSetTitle method, of class DivisionForm.
     */
    @Test
    public void testGetSetTitle() {
        System.out.println("getSetTitle");
        String expResult = "testData";
        instance.setTitle(expResult);
        String result = instance.getTitle();
        assertEquals(expResult, result);
        System.out.println("getSetTitle - OK");
    }
}
