/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.common.tools;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Alexander
 */
public class CommandTest {

    /**
     * Test of getDIVISION_CREATE_COMMAND method, of class Command.
     */
    @Test
    public void testGetDIVISION_CREATE_COMMAND() {
        System.out.println("getDIVISION_CREATE_COMMAND");
        String expResult = "divisionCreateForm";
        String result = Command.getDIVISION_CREATE_COMMAND();
        assertEquals(expResult, result);
        System.out.println("getDIVISION_CREATE_COMMAND - OK");
    }

    /**
     * Test of getDIVISION_UPDATE_COMMAND method, of class Command.
     */
    @Test
    public void testGetDIVISION_UPDATE_COMMAND() {
        System.out.println("getDIVISION_UPDATE_COMMAND");
        String expResult = "divisionUpdateForm";
        String result = Command.getDIVISION_UPDATE_COMMAND();
        assertEquals(expResult, result);
        System.out.println("getDIVISION_UPDATE_COMMAND - OK");
    }

    /**
     * Test of getEMPLOYEE_CREATE_COMMAND method, of class Command.
     */
    @Test
    public void testGetEMPLOYEE_CREATE_COMMAND() {
        System.out.println("getEMPLOYEE_CREATE_COMMAND");
        String expResult = "employeeCreateForm";
        String result = Command.getEMPLOYEE_CREATE_COMMAND();
        assertEquals(expResult, result);
        System.out.println("getEMPLOYEE_CREATE_COMMAND - OK");
    }

    /**
     * Test of getEMPLOYEE_UPDATE_COMMAND method, of class Command.
     */
    @Test
    public void testGetEMPLOYEE_UPDATE_COMMAND() {
        System.out.println("getEMPLOYEE_UPDATE_COMMAND");
        String expResult = "employeeUpdateForm";
        String result = Command.getEMPLOYEE_UPDATE_COMMAND();
        assertEquals(expResult, result);
        System.out.println("getEMPLOYEE_UPDATE_COMMAND - OK");
    }
}
