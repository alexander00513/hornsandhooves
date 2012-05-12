/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.common.tools;

import com.hornsandhooves.db.entity.DivisionImpl;
import com.hornsandhooves.db.entity.EmployeeImpl;
import com.hornsandhooves.db.entity.IEmployee;
import com.hornsandhooves.web.form.EmployeeForm;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Alexander
 */
public class ConversionsTest {
    
    private Conversions conversions;
    private IEmployee employee;
    
    
    @Before
    public void setUp() {
        conversions = new Conversions();
        try {
            employee = new EmployeeImpl();
            employee.setActive(true);
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            employee.setBirthdate(sdf.parse("01.01.2000"));
            DivisionImpl division = new DivisionImpl();
            division.setId(new Long(3));
            employee.setDivisionId(division);
            employee.setFirstName("Testdata");
            employee.setId(new Long(3));
            employee.setLastName("Testdata");
            employee.setSalary(Double.valueOf("  1000.50"));
        } catch (ParseException ex) {}
    }
    
    /**
     * Test of getInstanceEmployee method, of class Conversions.
     */
    @Test
    public void testGetInstanceEmployee() throws Exception {
        System.out.println("getInstanceEmployee");
        
        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setActive(true);
        employeeForm.setBirthdate("01.01.2000");
        employeeForm.setDivisionId(new Long(3));
        employeeForm.setFirstName("TEStdata   ");
        employeeForm.setId(new Long(3));
        employeeForm.setLastName("   testdata");
        employeeForm.setSalary("1000.50");
        IEmployee result = conversions.getInstanceEmployee(employeeForm);
        
        assertTrue(result.getActive());
        assertEquals(employee.getBirthdate().toString(), result.getBirthdate().toString());
        assertEquals(employee.getDivisionId().getId(), result.getDivisionId().getId());
        assertEquals(employee.getFirstName(), result.getFirstName());
        assertEquals(employee.getId(), result.getId());
        assertEquals(employee.getLastName(), result.getLastName());
        assertEquals(employee.getSalary(), result.getSalary(), 0.0);
        System.out.println("getInstanceEmployee - OK");
    }

    /**
     * Test of getInstanceEmployeeForm method, of class Conversions.
     */
    @Test
    public void testGetInstanceEmployeeForm() throws Exception {
        System.out.println("getInstanceEmployeeForm");
        EmployeeForm result = conversions.getInstanceEmployeeForm(employee);
        
        assertTrue(result.getActive());
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        assertEquals(sdf.format(employee.getBirthdate()).toString(), result.getBirthdate().toString());
        assertEquals(employee.getDivisionId().getId(), result.getDivisionId());
        assertEquals(employee.getDivisionId().getId(), result.getDivision().getId());
        assertEquals(employee.getFirstName(), result.getFirstName());
        assertEquals(employee.getId(), result.getId());
        assertEquals(employee.getLastName(), result.getLastName());
        assertEquals(employee.getSalary(), Double.valueOf(result.getSalary()), 0.0);
        System.out.println("getInstanceEmployeeForm - OK");
    }
}
