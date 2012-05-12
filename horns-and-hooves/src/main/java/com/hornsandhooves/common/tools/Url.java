/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.common.tools;

/**
 * Класс с константами для ссылок
 * @author Alexander
 */
public class Url {
    
    public final static String INDEX_URL =                      "/";
    public final static String SEARCH_URL =                     "search";
    public final static String DELETE_URL =                     "delete";
    
    public final static String EMPLOYEE_CREATE_URL =            "employee/create";
    public final static String EMPLOYEE_READ_URL =              "employee/read";
    public final static String EMPLOYEE_UPDATE_URL =            "employee/update";
    public final static String SEARCH_EMPLOYEE_UPDATE_URL =     "search/employee/update";
    
    public final static String DIVISION_LIST_URL =              "division";
    public final static String DIVISION_CREATE_URL =            "create";
    public final static String DIVISION_READ_URL =              "read";
    public final static String DIVISION_UPDATE_URL =            "update";

    public static String getDELETE_URL() {
        return DELETE_URL;
    }

    public static String getDIVISION_CREATE_URL() {
        return DIVISION_CREATE_URL;
    }

    public static String getDIVISION_LIST_URL() {
        return DIVISION_LIST_URL;
    }

    public static String getDIVISION_READ_URL() {
        return DIVISION_READ_URL;
    }

    public static String getDIVISION_UPDATE_URL() {
        return DIVISION_UPDATE_URL;
    }

    public static String getEMPLOYEE_CREATE_URL() {
        return EMPLOYEE_CREATE_URL;
    }

    public static String getEMPLOYEE_READ_URL() {
        return EMPLOYEE_READ_URL;
    }

    public static String getEMPLOYEE_UPDATE_URL() {
        return EMPLOYEE_UPDATE_URL;
    }

    public static String getINDEX_URL() {
        return INDEX_URL;
    }

    public static String getSEARCH_EMPLOYEE_UPDATE_URL() {
        return SEARCH_EMPLOYEE_UPDATE_URL;
    }

    public static String getSEARCH_URL() {
        return SEARCH_URL;
    }
}
