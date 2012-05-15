/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.web.controller;

import static com.hornsandhooves.common.tools.Command.EMPLOYEE_CREATE_COMMAND;
import static com.hornsandhooves.common.tools.Command.EMPLOYEE_UPDATE_COMMAND;
import static com.hornsandhooves.common.tools.Url.INDEX_URL;
import static com.hornsandhooves.common.tools.View.EMPLOYEE_CREATE_VIEW;
import static com.hornsandhooves.common.tools.View.EMPLOYEE_READ_VIEW;
import static com.hornsandhooves.common.tools.View.EMPLOYEE_UPDATE_VIEW;
import static com.hornsandhooves.common.tools.View.INDEX_VIEW;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.resetToNice;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.ModelAndViewAssert.assertCompareListModelAttribute;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeAvailable;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import com.hornsandhooves.db.entity.DivisionImpl;
import com.hornsandhooves.db.entity.EmployeeImpl;
import com.hornsandhooves.db.entity.IDivision;
import com.hornsandhooves.db.entity.IEmployee;
import com.hornsandhooves.db.service.DivisionServiceImpl;
import com.hornsandhooves.db.service.EmployeeServiceImpl;

/**
 *
 * @author Alexander
 */
public class EmployeeControllerTest {
    
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private AnnotationMethodHandlerAdapter handleAdapter;
    private DivisionServiceImpl divisionServiceMock;
    private EmployeeServiceImpl employeeServiceMock;
    private DivisionImpl division;
    private EmployeeImpl employee;
    private EmployeeController employeeController;
    
    
    @Before
    public void setUp() throws ParseException {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        handleAdapter = new AnnotationMethodHandlerAdapter();
        divisionServiceMock = createMock(DivisionServiceImpl.class);
        employeeServiceMock = createMock(EmployeeServiceImpl.class);
        division = new DivisionImpl();
        
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
        employeeController = new EmployeeController();
    }

    /**
     * Test of findAll method, of class EmployeeController.
     * @throws Exception 
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        request.setMethod("GET");
        request.setRequestURI("/");
        
        List<IEmployee> collection = new ArrayList<IEmployee>();
        collection.add(employee);
        List<IDivision> collection1 = new ArrayList<IDivision>();
        collection1.add(division);
        expect(employeeServiceMock.findAll()).andReturn(collection);
        expect(divisionServiceMock.findAll()).andReturn(collection1);
        employeeController.setEmployeeService(employeeServiceMock);
        employeeController.setDivisionService(divisionServiceMock);
        replay(employeeServiceMock);
        replay(divisionServiceMock);
        
        ModelAndView mav = handleAdapter.handle(request, response, employeeController);
        assertViewName(mav, INDEX_VIEW);
        assertCompareListModelAttribute(mav, "employeeList", collection);
        assertCompareListModelAttribute(mav, "divisionList", collection1);
        verify(employeeServiceMock);
        verify(divisionServiceMock);
        System.out.println("findAll - OK");
    }

    /**
     * Test of create method, of class EmployeeController.
     * @throws Exception 
     */
    @Test
    public void testCreate_0args() throws Exception {
        System.out.println("create");
        request.setRequestURI("/employee/create");
        request.setMethod("GET");
        
        List<IDivision> collection = new ArrayList<IDivision>();
        collection.add(division);
        expect(divisionServiceMock.findAll()).andReturn(collection);
        employeeController.setDivisionService(divisionServiceMock);
        replay(divisionServiceMock);
        ModelAndView mav = handleAdapter.handle(request, response, employeeController);
        assertViewName(mav, EMPLOYEE_CREATE_VIEW);
        assertModelAttributeAvailable(mav, EMPLOYEE_CREATE_COMMAND);
        assertCompareListModelAttribute(mav, "divisionList", collection);
        verify(divisionServiceMock);
        System.out.println("create - OK");
    }

    /**
     * Test of create method, of class EmployeeController.
     */
    @Test
    public void testCreate_EmployeeForm_BindingResult() throws Exception {
        System.out.println("create");
        request.setRequestURI("/employee/create");
        request.setMethod("POST");
        
        request.setParameter("firstName", "   ");
        request.setParameter("lastName", "testData");
        request.setParameter("active", "true");
        request.setParameter("birthdate", "01.01.2000");
        request.setParameter("divisionId", "3");
        request.setParameter("salary", "1000");
        expect(divisionServiceMock.findAll()).andReturn(new ArrayList<IDivision>());
        employeeController.setDivisionService(divisionServiceMock);
        replay(divisionServiceMock);
        ModelAndView mav = handleAdapter.handle(request, response, employeeController);
        assertViewName(mav, EMPLOYEE_CREATE_VIEW);
        assertModelAttributeAvailable(mav, EMPLOYEE_CREATE_COMMAND);
        assertModelAttributeAvailable(mav, "divisionList");
        verify(divisionServiceMock);
        resetToNice(divisionServiceMock);
        
        request.setParameter("firstName", "testData");
        expect(employeeServiceMock.createEmployee(anyObject(EmployeeImpl.class), anyObject(BindingResult.class))).andReturn(true);
        employeeController.setEmployeeService(employeeServiceMock);
        replay(employeeServiceMock);
        ModelAndView mav1 = handleAdapter.handle(request, response, employeeController);
        assertEquals(mav1.getViewName(), "redirect:" + INDEX_URL);
        verify(employeeServiceMock);
        resetToNice(employeeServiceMock);
        
        expect(employeeServiceMock.createEmployee(anyObject(EmployeeImpl.class), anyObject(BindingResult.class))).andReturn(false);
        employeeController.setEmployeeService(employeeServiceMock);
        replay(employeeServiceMock);
        expect(divisionServiceMock.findAll()).andReturn(new ArrayList<IDivision>());
        employeeController.setDivisionService(divisionServiceMock);
        replay(divisionServiceMock);
        ModelAndView mav2 = handleAdapter.handle(request, response, employeeController);
        assertViewName(mav2, EMPLOYEE_CREATE_VIEW);
        assertModelAttributeAvailable(mav2, EMPLOYEE_CREATE_COMMAND);
        verify(divisionServiceMock);
        verify(employeeServiceMock);
        System.out.println("create - OK");
    }

    /**
     * Test of read method, of class EmployeeController.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        request.setRequestURI("/employee/read/3");
        request.setMethod("GET");
        
        expect(employeeServiceMock.readEmployee(new Long(3))).andReturn(employee);
        employeeController.setEmployeeService(employeeServiceMock);
        replay(employeeServiceMock);
        ModelAndView mav = handleAdapter.handle(request, response, employeeController);
        assertViewName(mav, EMPLOYEE_READ_VIEW);
        assertModelAttributeAvailable(mav, "employee");
        verify(employeeServiceMock);
        resetToNice(employeeServiceMock);
        
        expect(employeeServiceMock.readEmployee(new Long(3))).andReturn(null);
        employeeController.setEmployeeService(employeeServiceMock);
        replay(employeeServiceMock);
        ModelAndView mav1 = handleAdapter.handle(request, response, employeeController);
        assertViewName(mav1, EMPLOYEE_READ_VIEW);
        assertModelAttributeAvailable(mav1, "empty");
        verify(employeeServiceMock);
        System.out.println("read - OK");
    }

    /**
     * Test of update method, of class EmployeeController.
     */
    @Test
    public void testUpdate_Long() throws Exception {
        System.out.println("update");
        request.setRequestURI("/employee/update/3");
        request.setMethod("GET");
        
        expect(employeeServiceMock.readEmployee(new Long(3))).andReturn(employee);
        employeeController.setEmployeeService(employeeServiceMock);
        replay(employeeServiceMock);
        expect(divisionServiceMock.findAll()).andReturn(new ArrayList<IDivision>());
        employeeController.setDivisionService(divisionServiceMock);
        replay(divisionServiceMock);
        ModelAndView mav = handleAdapter.handle(request, response, employeeController);
        assertViewName(mav, EMPLOYEE_UPDATE_VIEW);
        assertModelAttributeAvailable(mav, EMPLOYEE_UPDATE_COMMAND);
        assertModelAttributeAvailable(mav, "divisionList");
        verify(employeeServiceMock);
        verify(divisionServiceMock);
        resetToNice(employeeServiceMock);
        
        expect(employeeServiceMock.readEmployee(new Long(3))).andReturn(null);
        employeeController.setEmployeeService(employeeServiceMock);
        replay(employeeServiceMock);
        ModelAndView mav1 = handleAdapter.handle(request, response, employeeController);
        assertViewName(mav1, EMPLOYEE_UPDATE_VIEW);
        assertModelAttributeAvailable(mav1, "empty");
        verify(employeeServiceMock);
        System.out.println("update - OK");
    }

    /**
     * Test of update method, of class EmployeeController.
     */
    @Test
    public void testUpdate_EmployeeForm_BindingResult() throws Exception {
        System.out.println("update");
        request.setRequestURI("/employee/update/3");
        request.setMethod("POST");
        
        request.setParameter("firstName", "   ");
        request.setParameter("lastName", "testData");
        request.setParameter("active", "true");
        request.setParameter("birthdate", "01.01.2000");
        request.setParameter("divisionId", "3");
        request.setParameter("salary", "1000");
        request.setParameter("id", "3");
        expect(divisionServiceMock.findAll()).andReturn(new ArrayList<IDivision>());
        employeeController.setDivisionService(divisionServiceMock);
        replay(divisionServiceMock);
        ModelAndView mav = handleAdapter.handle(request, response, employeeController);
        assertViewName(mav, EMPLOYEE_UPDATE_VIEW);
        assertModelAttributeAvailable(mav, EMPLOYEE_UPDATE_COMMAND);
        assertModelAttributeAvailable(mav, "divisionList");
        verify(divisionServiceMock);
        resetToNice(divisionServiceMock);
        
        request.setParameter("firstName", "testData");
        expect(employeeServiceMock.updateEmployee(anyObject(EmployeeImpl.class), anyObject(BindingResult.class))).andReturn(true);
        employeeController.setEmployeeService(employeeServiceMock);
        replay(employeeServiceMock);
        ModelAndView mav1 = handleAdapter.handle(request, response, employeeController);
        assertEquals(mav1.getViewName(), "redirect:" + INDEX_URL);
        verify(employeeServiceMock);
        resetToNice(employeeServiceMock);
        
        expect(employeeServiceMock.updateEmployee(anyObject(EmployeeImpl.class), anyObject(BindingResult.class))).andReturn(false);
        employeeController.setEmployeeService(employeeServiceMock);
        replay(employeeServiceMock);
        ModelAndView mav2 = handleAdapter.handle(request, response, employeeController);
        assertViewName(mav2, EMPLOYEE_UPDATE_VIEW);
        assertModelAttributeAvailable(mav2, "empty");
        verify(employeeServiceMock);
        System.out.println("update - OK");
    }

    /**
     * Test of delete method, of class EmployeeController.
     * @throws Exception 
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        request.setRequestURI("/delete/3");
        request.setMethod("GET");
        
        expect(employeeServiceMock.deleteEmployee(new Long(3))).andReturn(true);
        employeeController.setEmployeeService(employeeServiceMock);
        replay(employeeServiceMock);
        ModelAndView mav = handleAdapter.handle(request, response, employeeController);
        assertEquals(mav.getViewName(), "redirect:" + INDEX_URL);
        verify(employeeServiceMock);
        System.out.println("delete - OK");
    }
}
