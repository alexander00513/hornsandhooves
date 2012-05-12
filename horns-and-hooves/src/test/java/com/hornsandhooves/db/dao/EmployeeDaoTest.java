/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.db.dao;

import com.hornsandhooves.db.entity.EmployeeImpl;
import com.hornsandhooves.db.entity.IEmployee;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alexander
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:data.hibernate.xml"})
public class EmployeeDaoTest {
    
    @Autowired
    private EmployeeDao employeeDao;
    
    /**
     * Test of search method, of class EmployeeDao.
     */
    @Test
    @Transactional
    public void testSearch() {
        System.out.println("search");
        EmployeeImpl employee = new EmployeeImpl();
        employee.setFirstName("testData");
        employee.setLastName("testData");
        employeeDao.create(employee);
        
        String searchQuery = employee.getFirstName();
        List<IEmployee> result = employeeDao.search(searchQuery);
        assertEquals(employee.getFirstName(), result.get(0).getFirstName());
        
        String searchQuery_1 = employee.getLastName();
        List<IEmployee> result_1 = employeeDao.search(searchQuery_1);
        assertEquals(employee.getLastName(), result_1.get(0).getLastName());
        
        String searchQuery_2 = "";
        searchQuery_2 += employee.getFirstName();
        searchQuery_2 += employee.getLastName();
        List<IEmployee> result_2 = employeeDao.search(searchQuery_2);
        assertEquals(employee.getFirstName(), result_2.get(0).getFirstName());
        assertEquals(employee.getLastName(), result_2.get(0).getLastName());
        
        String searchQuery_3 = "";
        searchQuery_3 += employee.getLastName();
        searchQuery_3 += employee.getFirstName();
        List<IEmployee> result_3 = employeeDao.search(searchQuery_3);
        assertEquals(employee.getFirstName(), result_3.get(0).getFirstName());
        assertEquals(employee.getLastName(), result_3.get(0).getLastName());
        
        String searchQuery_4 = "testD*";
        List<IEmployee> result_4 = employeeDao.search(searchQuery_4);
        assertEquals(employee.getFirstName(), result_4.get(0).getFirstName());
        
        String searchQuery_5 = "te???ata";
        List<IEmployee> result_5 = employeeDao.search(searchQuery_5);
        assertEquals(employee.getFirstName(), result_5.get(0).getFirstName());
        System.out.println("search - OK");
    }
}
