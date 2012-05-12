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
public class UrlTest {
    
    /**
     * Test of getDELETE_URL method, of class Url.
     */
    @Test
    public void testGetDELETE_URL() {
        System.out.println("getDELETE_URL");
        String expResult = "delete";
        String result = Url.getDELETE_URL();
        assertEquals(expResult, result);
        System.out.println("getDELETE_URL - OK");
    }

    /**
     * Test of getDIVISION_CREATE_URL method, of class Url.
     */
    @Test
    public void testGetDIVISION_CREATE_URL() {
        System.out.println("getDIVISION_CREATE_URL");
        String expResult = "create";
        String result = Url.getDIVISION_CREATE_URL();
        assertEquals(expResult, result);
        System.out.println("getDIVISION_CREATE_URL - OK");
    }

    /**
     * Test of getDIVISION_LIST_URL method, of class Url.
     */
    @Test
    public void testGetDIVISION_LIST_URL() {
        System.out.println("getDIVISION_LIST_URL");
        String expResult = "division";
        String result = Url.getDIVISION_LIST_URL();
        assertEquals(expResult, result);
        System.out.println("getDIVISION_LIST_URL - OK");
    }

    /**
     * Test of getDIVISION_READ_URL method, of class Url.
     */
    @Test
    public void testGetDIVISION_READ_URL() {
        System.out.println("getDIVISION_READ_URL");
        String expResult = "read";
        String result = Url.getDIVISION_READ_URL();
        assertEquals(expResult, result);
        System.out.println("getDIVISION_READ_URL - OK");
    }

    /**
     * Test of getDIVISION_UPDATE_URL method, of class Url.
     */
    @Test
    public void testGetDIVISION_UPDATE_URL() {
        System.out.println("getDIVISION_UPDATE_URL");
        String expResult = "update";
        String result = Url.getDIVISION_UPDATE_URL();
        assertEquals(expResult, result);
        System.out.println("getDIVISION_UPDATE_URL - OK");
    }

    /**
     * Test of getEMPLOYEE_CREATE_URL method, of class Url.
     */
    @Test
    public void testGetEMPLOYEE_CREATE_URL() {
        System.out.println("getEMPLOYEE_CREATE_URL");
        String expResult = "employee/create";
        String result = Url.getEMPLOYEE_CREATE_URL();
        assertEquals(expResult, result);
        System.out.println("getEMPLOYEE_CREATE_URL - OK");
    }

    /**
     * Test of getEMPLOYEE_READ_URL method, of class Url.
     */
    @Test
    public void testGetEMPLOYEE_READ_URL() {
        System.out.println("getEMPLOYEE_READ_URL");
        String expResult = "employee/read";
        String result = Url.getEMPLOYEE_READ_URL();
        assertEquals(expResult, result);
        System.out.println("getEMPLOYEE_READ_URL - OK");
    }

    /**
     * Test of getEMPLOYEE_UPDATE_URL method, of class Url.
     */
    @Test
    public void testGetEMPLOYEE_UPDATE_URL() {
        System.out.println("getEMPLOYEE_UPDATE_URL");
        String expResult = "employee/update";
        String result = Url.getEMPLOYEE_UPDATE_URL();
        assertEquals(expResult, result);
        System.out.println("getEMPLOYEE_UPDATE_URL - OK");
    }

    /**
     * Test of getINDEX_URL method, of class Url.
     */
    @Test
    public void testGetINDEX_URL() {
        System.out.println("getINDEX_URL");
        String expResult = "/";
        String result = Url.getINDEX_URL();
        assertEquals(expResult, result);
        System.out.println("getINDEX_URL - OK");
    }

    /**
     * Test of getSEARCH_URL method, of class Url.
     */
    @Test
    public void testGetSEARCH_URL() {
        System.out.println("getSEARCH_URL");
        String expResult = "search";
        String result = Url.getSEARCH_URL();
        assertEquals(expResult, result);
        System.out.println("getSEARCH_URL - OK");
    }
}
