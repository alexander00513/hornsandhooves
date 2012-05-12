/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.web.controller;

import com.hornsandhooves.web.form.EmployeeForm;
import org.junit.*;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import static com.hornsandhooves.common.tools.Url.*;
import static com.hornsandhooves.common.tools.View.*;
import com.hornsandhooves.db.dao.DivisionDao;
import com.hornsandhooves.db.dao.EmployeeDao;
import com.hornsandhooves.db.entity.DivisionImpl;
import com.hornsandhooves.db.entity.EmployeeImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;

/**
 *
 * @author Alexander
 */

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:data.hibernate.xml"})
public class EmployeeControllerTest {
    
    private EmployeeForm employeeForm;
    private BindingResult result;
    private EmployeeImpl employee;
    
    
    @Autowired
    private EmployeeController employeeController;
    
    @Autowired
    private EmployeeDao emloyeeDao;
    
    @Autowired
    private DivisionDao divisionDao;
    
    @Before
    public void setUp() {
        employeeForm = new EmployeeForm();
        employeeForm.setBirthdate("01.01.2000");
        employeeForm.setFirstName("Testdata");
        employeeForm.setLastName("Testdata");
        employeeForm.setSalary("1000.50");
        result = new BeanPropertyBindingResult(employeeForm, "employeeForm");
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
            employee.setSalary(Double.valueOf("1000.50"));
        } catch (ParseException ex) {}
    }

    /**
     * Test of findAll method, of class EmployeeController.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        ModelAndView result_1 = employeeController.findAll();
        assertEquals(INDEX_VIEW, result_1.getViewName());
        System.out.println("findAll - OK");
    }

    /**
     * Test of create method, of class EmployeeController.
     */
    @Test
    public void testCreate_0args() {
        System.out.println("create");
        ModelAndView result_1 = employeeController.create();
        assertEquals(EMPLOYEE_CREATE_VIEW, result_1.getViewName());
        System.out.println("create - OK");
    }

    /**
     * Test of create method, of class EmployeeController.
     */
    @Test
    @Transactional
    public void testCreate_EmployeeForm_BindingResult() throws Exception {
        System.out.println("create");
        
        DivisionImpl division = new DivisionImpl();
        division.setTitle("testData");
        Long divId = divisionDao.create(division);
        employeeForm.setDivisionId(divId);
        ModelAndView result_1 = employeeController.create(employeeForm, result);
        assertEquals("redirect:" + INDEX_URL, result_1.getViewName());
        
        employeeForm.setDivisionId(divId+1);
        ModelAndView result_2 = employeeController.create(employeeForm, result);
        assertEquals(EMPLOYEE_CREATE_VIEW, result_2.getViewName());
        System.out.println("create - OK");
    }

    /**
     * Test of read method, of class EmployeeController.
     */
    @Test
    @Transactional
    public void testRead() throws Exception {
        System.out.println("read");
        
        Long empId = emloyeeDao.create(employee);
        ModelAndView result_1 = employeeController.read(empId);
        assertEquals(EMPLOYEE_READ_VIEW, result_1.getViewName());
        
        ModelAndView result_2 = employeeController.read(empId+1);
        assertEquals(EMPLOYEE_READ_VIEW, result_2.getViewName());
        System.out.println("read - OK");
    }

    /**
     * Test of update method, of class EmployeeController.
     */
    @Test
    @Transactional
    public void testUpdate_Long() throws Exception {
        System.out.println("update");
        
        Long empId = emloyeeDao.create(employee);
        ModelAndView result_1 = employeeController.update(empId);
        assertEquals(EMPLOYEE_UPDATE_VIEW, result_1.getViewName());
        
        ModelAndView result_2 = employeeController.update(empId+1);
        assertEquals(EMPLOYEE_UPDATE_VIEW, result_2.getViewName());
        System.out.println("update - OK");
    }

    /**
     * Test of update method, of class EmployeeController.
     */
    @Test
    @Transactional
    public void testUpdate_EmployeeForm_BindingResult() throws Exception {
        System.out.println("update");
        
        Long empId = emloyeeDao.create(employee);
        employeeForm.setId(empId);
        employeeForm.setActive(true);
        employeeForm.setFirstName("testDataNew");
        DivisionImpl division = new DivisionImpl();
        division.setTitle("testData");
        Long divId = divisionDao.create(division);
        employeeForm.setDivisionId(divId);
        ModelAndView result_1 = employeeController.update(employeeForm, result);
        assertEquals("redirect:" + INDEX_URL, result_1.getViewName());
        
        employeeForm.setId(divId+1);
        ModelAndView result_2 = employeeController.update(employeeForm, result);
        assertEquals(EMPLOYEE_UPDATE_VIEW, result_2.getViewName());
        System.out.println("update - OK");
    }

    /**
     * Test of delete method, of class EmployeeController.
     */
    @Test
    @Transactional
    public void testDelete() {
        System.out.println("delete");
        Long empId = emloyeeDao.create(employee);
        ModelAndView result_1 = employeeController.delete(empId);
        assertEquals("redirect:" + INDEX_URL, result_1.getViewName());
        System.out.println("delete - OK");
    }
}
