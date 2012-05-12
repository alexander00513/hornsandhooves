/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.db.dao;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;
import com.hornsandhooves.db.entity.DivisionImpl;
import com.hornsandhooves.db.entity.IDivision;
import com.hornsandhooves.web.form.DivisionForm;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;

/**
 *
 * @author Alexander
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:data.hibernate.xml"})
public class DivisionDaoTest {
    
    private DivisionImpl division;
    
    @Autowired
    private DivisionDao divisionDao;

    @Before
    public void setUp() {
        division = new DivisionImpl();
        division.setId(new Long(3));
        division.setTitle("testData");
    }
    
    /**
     * Test of create and read method, of class DaoImpl.
     */
    @Test
    @Transactional
    public void testCreateRead() {
        System.out.println("createReadDivision");
        divisionDao.create(division);
        IDivision result = divisionDao.read(division.getId());
        assertEquals(division.getTitle(), result.getTitle());
        System.out.println("createReadDivision - OK");
    }
    
    /**
     * Test of update method, of class DaoImpl.
     */
    @Test
    @Transactional
    public void testUpdate() {
        System.out.println("updateDivision");
        divisionDao.create(division);
        DivisionImpl divisionUpd = divisionDao.read(division.getId());
        divisionUpd.setTitle("testDataUpd");
        divisionDao.update(divisionUpd);
        IDivision result = divisionDao.read(divisionUpd.getId());
        assertEquals(divisionUpd.getTitle(), result.getTitle());
        System.out.println("updateDivision - OK");
    }
    
    /**
     * Test of delete method, of class DaoImpl.
     */
    @Test
    @Transactional
    public void testDelete() {
        System.out.println("deleteDivision");
        divisionDao.delete(division);
        assertNull(divisionDao.read(division.getId()));
        System.out.println("deleteDivision - OK");
    }
    
    /**
     * Test of checkTitle method, of class DivisionDao.
     */
    @Test
    @Transactional
    public void testCheckTitle() {
        System.out.println("checkTitle");
        divisionDao.create(division);
        DivisionForm divisionForm = new DivisionForm();
        
        divisionForm.setTitle(division.getTitle());
        boolean result = divisionDao.checkTitle(divisionForm);
        assertEquals(true, result);
        
        divisionForm.setTitle("testDataNew");
        boolean result_1 = divisionDao.checkTitle(divisionForm);
        assertEquals(false, result_1);
        System.out.println("checkTitle - OK");
    }
}
