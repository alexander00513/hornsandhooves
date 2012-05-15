/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.db.service;

import com.hornsandhooves.db.dao.DivisionDao;
import com.hornsandhooves.db.entity.DivisionImpl;
import com.hornsandhooves.db.entity.IDivision;
import com.hornsandhooves.web.form.DivisionForm;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.easymock.EasyMock.*;
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
@ContextConfiguration(locations = { "classpath:data.hibernate.xml" })
public class DivisionServiceImplTest {

    private DivisionForm divisionForm;
    private BindingResult result;
    private DivisionImpl division;
    private DivisionDao mockDivisionDao;

    @Autowired
    private IDivisionService divisionService;

    @Before
    public void setUp() {
        divisionForm = new DivisionForm();
        divisionForm.setId(new Long(3));
        divisionForm.setTitle("testData");
        result = new BeanPropertyBindingResult(divisionForm, "divisionForm");
        division = new DivisionImpl();
        division.setId(divisionForm.getId());
        division.setTitle(divisionForm.getTitle());
        mockDivisionDao = createMock(DivisionDao.class);
        divisionService.setDivisionDao(mockDivisionDao);
    }

    /**
     * Test of createDivision method, of class DivisionServiceImpl.
     */
    @Test
    @Transactional
    public void testCreateDivision() {
        System.out.println("createDivision");
        expect(mockDivisionDao.checkTitle(divisionForm)).andReturn(true);
        replay(mockDivisionDao);
        boolean result_1 = divisionService.createDivision(divisionForm, result);
        assertEquals(false, result_1);
        verify(mockDivisionDao);
        resetToNice(mockDivisionDao);

        expect(mockDivisionDao.checkTitle(divisionForm)).andReturn(false);
        replay(mockDivisionDao);
        boolean result_2 = divisionService.createDivision(divisionForm, result);
        assertEquals(true, result_2);
        verify(mockDivisionDao);
        System.out.println("createDivision - OK");
    }

    /**
     * Test of readDivision method, of class DivisionServiceImpl.
     */
    @Test
    @Transactional
    public void testReadDivision() {
        System.out.println("readDivision");
        expect(mockDivisionDao.read(division.getId())).andReturn(division);
        replay(mockDivisionDao);
        IDivision result_1 = divisionService.readDivision(division.getId());
        assertEquals(division.getTitle(), result_1.getTitle());
        verify(mockDivisionDao);
        System.out.println("readDivision - OK");
    }

    /**
     * Test of updateDivision method, of class DivisionServiceImpl.
     */
    @Test
    @Transactional
    public void testUpdateDivision() {
        System.out.println("updateDivision");
        expect(mockDivisionDao.read(division.getId())).andReturn(null);
        replay(mockDivisionDao);
        boolean result_1 = divisionService.updateDivision(divisionForm, result);
        assertEquals(false, result_1);
        verify(mockDivisionDao);
        resetToNice(mockDivisionDao);

        expect(mockDivisionDao.read(division.getId())).andReturn(division);
        expect(mockDivisionDao.checkTitle(divisionForm)).andReturn(true);
        replay(mockDivisionDao);
        boolean result_2 = divisionService.updateDivision(divisionForm, result);
        assertEquals(false, result_2);
        verify(mockDivisionDao);
        resetToNice(mockDivisionDao);

        expect(mockDivisionDao.read(division.getId())).andReturn(division);
        expect(mockDivisionDao.checkTitle(divisionForm)).andReturn(false);
        replay(mockDivisionDao);
        boolean result_3 = divisionService.updateDivision(divisionForm, result);
        assertEquals(true, result_3);
        verify(mockDivisionDao);
        System.out.println("updateDivision - OK");
    }

    /**
     * Test of deleteDivision method, of class DivisionServiceImpl.
     */
    @Test
    @Transactional
    public void testDeleteDivision() {
        System.out.println("deleteDivision");
        expect(mockDivisionDao.read(division.getId())).andReturn(null);
        replay(mockDivisionDao);
        boolean result_1 = divisionService.deleteDivision(division.getId());
        assertEquals(false, result_1);
        verify(mockDivisionDao);
        resetToNice(mockDivisionDao);

        expect(mockDivisionDao.read(division.getId())).andReturn(division);
        replay(mockDivisionDao);
        boolean result_2 = divisionService.deleteDivision(division.getId());
        assertEquals(true, result_2);
        verify(mockDivisionDao);
        System.out.println("deleteDivision - OK");
    }

    /**
     * Test of findAll method, of class DivisionServiceImpl.
     */
    @Test
    @Transactional
    public void testFindAll() {
        System.out.println("findAll");
        List<DivisionImpl> collection = new ArrayList<DivisionImpl>();
        collection.add(division);
        collection.add(division);

        expect(mockDivisionDao.findAll()).andReturn(collection);
        replay(mockDivisionDao);
        List<IDivision> result_1 = divisionService.findAll();
        verify(mockDivisionDao);
        resetToNice(mockDivisionDao);

        collection.add(division);
        expect(mockDivisionDao.findAll()).andReturn(collection);
        replay(mockDivisionDao);
        List<IDivision> result_2 = divisionService.findAll();
        assertEquals(result_1.size() + 1, result_2.size());
        verify(mockDivisionDao);
        System.out.println("findAll - OK");
    }
}
