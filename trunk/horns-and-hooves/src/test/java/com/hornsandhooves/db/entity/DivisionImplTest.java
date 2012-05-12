/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.db.entity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alexander
 */
public class DivisionImplTest {
    
    private DivisionImpl instance;
    
    
    @Before
    public void setUp() {
        instance = new DivisionImpl();
    }

    /**
     * Test of getSetId and setId method, of class DivisionImpl.
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
     * Test of getSetTitle method, of class DivisionImpl.
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
