/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.db.service;

import com.hornsandhooves.db.dao.DivisionDao;
import com.hornsandhooves.db.dao.EmployeeDao;
import com.hornsandhooves.db.entity.DivisionImpl;
import com.hornsandhooves.db.entity.EmployeeImpl;
import com.hornsandhooves.db.entity.IEmployee;
import com.hornsandhooves.web.form.EmployeeForm;

import java.util.ArrayList;
import java.util.List;
import static org.easymock.EasyMock.*;

import org.junit.*;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

/**
 *
 * @author Alexander
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:data.hibernate.xml"})
@Transactional
public class EmployeeServiceImplTest {

    private EmployeeImpl employee;
    private EmployeeForm employeeForm;
    private BindingResult result;
    private EmployeeDao mockEmployeeDao;
    private DivisionDao mockDivisionDao;
    
    
    @Autowired
    private IEmployeeService employeeService;
    
    @Before
    public void setUp() {
        employee = new EmployeeImpl();
        employee.setId(new Long(3));
        employee.setFirstName("testData");
        employeeForm = new EmployeeForm();
        result = new BeanPropertyBindingResult(employeeForm, "employeeForm");
        mockEmployeeDao = createMock(EmployeeDao.class);
        employeeService.setEmployeeDao(mockEmployeeDao);
        mockDivisionDao = createMock(DivisionDao.class);
        employeeService.setDivisionDao(mockDivisionDao);
    }
    
    /**
     * Test of createEmployee method, of class EmployeeServiceImpl.
     */
    @Test
    public void testCreateEmployee() {
    	System.out.println("createEmployee");
    	employee.setDivisionId(new DivisionImpl());
        boolean result_1 = employeeService.createEmployee(employee, result);
        assertEquals(false, result_1);
        
        employee.setDivisionId(new DivisionImpl());
        employee.getDivisionId().setId(employee.getId());
        
        expect(mockDivisionDao.read(employee.getId())).andReturn(null);
    	replay(mockDivisionDao);
        boolean result_2 = employeeService.createEmployee(employee, result);
        assertEquals(false, result_2);
        resetToNice(mockDivisionDao);
        
        expect(mockDivisionDao.read(employee.getId())).andReturn(employee.getDivisionId());
    	replay(mockDivisionDao);
        boolean result_3 = employeeService.createEmployee(employee, result);
        assertEquals(true, result_3);
        verify(mockDivisionDao);
    }

    /**
     * Test of readEmployee method, of class EmployeeServiceImpl.
     */
    @Test
    public void testReadEmployee() {
    	System.out.println("readEmployee");
    	expect(mockEmployeeDao.read(employee.getId())).andReturn(employee);
    	replay(mockEmployeeDao);
        IEmployee result_1 = employeeService.readEmployee(employee.getId());
        assertEquals(employee.getFirstName(), result_1.getFirstName());
        verify(mockEmployeeDao);
        System.out.println("readEmployee - OK");
    }

    /**
     * Test of updateEmployee method, of class EmployeeServiceImpl.
     */
    @Test
    public void testUpdateEmployee() {
    	System.out.println("updateEmployee");
    	expect(mockEmployeeDao.read(employee.getId())).andReturn(null);
    	replay(mockEmployeeDao);
    	boolean result_1 = employeeService.updateEmployee(employee, result);
        assertEquals(false, result_1);
        verify(mockEmployeeDao);
        resetToNice(mockEmployeeDao);
        
        employee.setDivisionId(new DivisionImpl());
        employee.getDivisionId().setId(employee.getId());
        
        expect(mockEmployeeDao.read(employee.getId())).andReturn(employee);
        expect(mockDivisionDao.read(employee.getDivisionId().getId())).andReturn(null);
        replay(mockEmployeeDao);
    	replay(mockDivisionDao);
    	boolean result_2 = employeeService.updateEmployee(employee, result);
        assertEquals(false, result_2);
        verify(mockDivisionDao);
        verify(mockEmployeeDao);
        resetToNice(mockDivisionDao);
        resetToNice(mockEmployeeDao);
        
        expect(mockEmployeeDao.read(employee.getId())).andReturn(employee);
        expect(mockDivisionDao.read(employee.getDivisionId().getId())).andReturn(employee.getDivisionId());
        replay(mockEmployeeDao);
    	replay(mockDivisionDao);
    	boolean result_3 = employeeService.updateEmployee(employee, result);
        assertEquals(true, result_3);
        verify(mockDivisionDao);
        verify(mockEmployeeDao);
        System.out.println("updateEmployee - OK");
    }

    /**
     * Test of deleteEmployee method, of class EmployeeServiceImpl.
     */
    @Test
    public void testDeleteEmployee() {
    	System.out.println("deleteEmployee");
    	expect(mockEmployeeDao.read(employee.getId())).andReturn(null);
    	replay(mockEmployeeDao);
        boolean result_1 = employeeService.deleteEmployee(employee.getId());
        assertEquals(false, result_1);
        verify(mockEmployeeDao);
        resetToNice(mockEmployeeDao);
        
        System.out.println("deleteEmployee");
    	expect(mockEmployeeDao.read(employee.getId())).andReturn(employee);
    	replay(mockEmployeeDao);
        boolean result_2 = employeeService.deleteEmployee(employee.getId());
        assertEquals(true, result_2);
        verify(mockEmployeeDao);
        System.out.println("deleteEmployee - OK");
    }

    /**
     * Test of findAll method, of class EmployeeServiceImpl.
     */
    @Test
    public void testFindAll() {
    	System.out.println("findAll");
    	List<EmployeeImpl> collection = new ArrayList<EmployeeImpl>();
    	collection.add(employee);
    	collection.add(employee);
    	
    	expect(mockEmployeeDao.findAll()).andReturn(collection);
    	replay(mockEmployeeDao);
    	List<IEmployee> result_1 = employeeService.findAll();
    	resetToNice(mockEmployeeDao);
    	
    	collection.add(employee);
    	expect(mockEmployeeDao.findAll()).andReturn(collection);
    	replay(mockEmployeeDao);
        List<IEmployee> result_2 = employeeService.findAll();
        
    	assertEquals(result_1.size()+1, result_2.size());
    	verify(mockEmployeeDao);
        System.out.println("findAll - OK");
    }

    /**
     * Test of search method, of class EmployeeServiceImpl.
     */
    @Test
    public void testSearch() {
    	System.out.println("search");
    	String searchQuery = employee.getFirstName();
    	List<IEmployee> collection = new ArrayList<IEmployee>();
    	collection.add(employee);
    	expect(mockEmployeeDao.search(searchQuery)).andReturn(collection);
    	replay(mockEmployeeDao);
    	List<IEmployee> result_1 = employeeService.search(searchQuery);
    	assertEquals(searchQuery, result_1.get(0).getFirstName());
    	verify(mockEmployeeDao);
    	System.out.println("search - OK");
    }
}
