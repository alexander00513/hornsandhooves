/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.common.tools;

/**
 * Класс с константами для форм
 * @author Alexander
 */
public class Command {
    
    public final static String EMPLOYEE_CREATE_COMMAND = "employeeCreateForm";
    public final static String EMPLOYEE_UPDATE_COMMAND = "employeeUpdateForm";
    
    public final static String DIVISION_CREATE_COMMAND = "divisionCreateForm";
    public final static String DIVISION_UPDATE_COMMAND = "divisionUpdateForm";

    public static String getDIVISION_CREATE_COMMAND() {
        return DIVISION_CREATE_COMMAND;
    }

    public static String getDIVISION_UPDATE_COMMAND() {
        return DIVISION_UPDATE_COMMAND;
    }

    public static String getEMPLOYEE_CREATE_COMMAND() {
        return EMPLOYEE_CREATE_COMMAND;
    }

    public static String getEMPLOYEE_UPDATE_COMMAND() {
        return EMPLOYEE_UPDATE_COMMAND;
    }
}
