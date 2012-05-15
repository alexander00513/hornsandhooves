package com.hornsandhooves.web.controller;

import static com.hornsandhooves.common.tools.Command.DIVISION_CREATE_COMMAND;
import static com.hornsandhooves.common.tools.Command.DIVISION_UPDATE_COMMAND;
import static com.hornsandhooves.common.tools.Url.DIVISION_LIST_URL;
import static com.hornsandhooves.common.tools.View.DIVISION_CREATE_VIEW;
import static com.hornsandhooves.common.tools.View.DIVISION_LIST_VIEW;
import static com.hornsandhooves.common.tools.View.DIVISION_READ_VIEW;
import static com.hornsandhooves.common.tools.View.DIVISION_UPDATE_VIEW;
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
import com.hornsandhooves.db.entity.IDivision;
import com.hornsandhooves.db.service.DivisionServiceImpl;
import com.hornsandhooves.web.form.DivisionForm;

/**
*
* @author Alexander
*/
public class DivisionControllerTest {
    
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private AnnotationMethodHandlerAdapter handleAdapter;
    private DivisionServiceImpl divisionServiceMock;
    private DivisionImpl division;
    private DivisionForm divisionForm;
    private DivisionController divisionController;
    
    
    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        handleAdapter = new AnnotationMethodHandlerAdapter();
        divisionServiceMock = createMock(DivisionServiceImpl.class);
        division = new DivisionImpl();
        divisionForm = new DivisionForm();
        divisionController = new DivisionController();
    }
    
    /**
     * Test of findAll method, of class DivisionController.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        request.setMethod("GET");
        request.setRequestURI("division");
        
        List<IDivision> collection = new ArrayList<IDivision>();
        collection.add(division);
        expect(divisionServiceMock.findAll()).andReturn(collection);
        divisionController.setDivisionService(divisionServiceMock);
        replay(divisionServiceMock);
        ModelAndView mav = handleAdapter.handle(request, response, divisionController);
        assertViewName(mav, DIVISION_LIST_VIEW);
        assertCompareListModelAttribute(mav, "divisionList", collection);
        verify(divisionServiceMock);
        System.out.println("findAll - OK");
    }
    
    /**
     * Test of create method, of class DivisionController.
     */
    @Test
    public void testCreate_0args() throws Exception {
        System.out.println("create");
        request.setRequestURI("/division/create");
        request.setMethod("GET");
        ModelAndView mav = handleAdapter.handle(request, response, divisionController);
        assertViewName(mav, DIVISION_CREATE_VIEW);
        assertModelAttributeAvailable(mav, DIVISION_CREATE_COMMAND);
        System.out.println("create - OK");
    }
    
    /**
     * Test of create method, of class DivisionController.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        request.setRequestURI("/division/create");
        request.setMethod("POST");
        
        divisionForm.setTitle("   ");
        request.setParameter("title", divisionForm.getTitle());
        ModelAndView mav = handleAdapter.handle(request, response, divisionController);
        assertViewName(mav, DIVISION_CREATE_VIEW);
        assertModelAttributeAvailable(mav, DIVISION_CREATE_COMMAND);
        
        divisionForm.setTitle("testData");
        request.setParameter("title", divisionForm.getTitle());
        expect(divisionServiceMock.createDivision(anyObject(DivisionForm.class), anyObject(BindingResult.class))).andReturn(true);
        divisionController.setDivisionService(divisionServiceMock);
        replay(divisionServiceMock);
        ModelAndView mav1 = handleAdapter.handle(request, response, divisionController);
        assertEquals(mav1.getViewName(), "redirect:../" + DIVISION_LIST_URL);
        verify(divisionServiceMock);
        resetToNice(divisionServiceMock);
        
        expect(divisionServiceMock.createDivision(anyObject(DivisionForm.class), anyObject(BindingResult.class))).andReturn(false);
        divisionController.setDivisionService(divisionServiceMock);
        replay(divisionServiceMock);
        ModelAndView mav2 = handleAdapter.handle(request, response, divisionController);
        assertViewName(mav2, DIVISION_CREATE_VIEW);
        assertModelAttributeAvailable(mav2, DIVISION_CREATE_COMMAND);
        verify(divisionServiceMock);
        System.out.println("create - OK");
    }
    
    /**
     * Test of read method, of class DivisionController.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("create");
        request.setRequestURI("/division/read/3");
        request.setMethod("GET");
        
        division.setId(new Long(3));
        division.setTitle("testData");
        expect(divisionServiceMock.readDivision(new Long(3))).andReturn(division);
        divisionController.setDivisionService(divisionServiceMock);
        replay(divisionServiceMock);
        ModelAndView mav = handleAdapter.handle(request, response, divisionController);
        assertViewName(mav, DIVISION_READ_VIEW);
        assertModelAttributeAvailable(mav, "division");
        verify(divisionServiceMock);
        resetToNice(divisionServiceMock);
        
        expect(divisionServiceMock.readDivision(new Long(3))).andReturn(null);
        divisionController.setDivisionService(divisionServiceMock);
        replay(divisionServiceMock);
        ModelAndView mav1 = handleAdapter.handle(request, response, divisionController);
        assertViewName(mav1, DIVISION_READ_VIEW);
        assertModelAttributeAvailable(mav1, "empty");
        verify(divisionServiceMock);
        System.out.println("create - OK");
    }
    
    /**
     * Test of update method, of class DivisionController.
     */
    @Test
    public void testUpdate_0args() throws Exception {
        System.out.println("update");
        request.setRequestURI("/division/update/3");
        request.setMethod("GET");
        
        division.setId(new Long(3));
        division.setTitle("testData");
        expect(divisionServiceMock.readDivision(new Long(3))).andReturn(division);
        divisionController.setDivisionService(divisionServiceMock);
        replay(divisionServiceMock);
        ModelAndView mav = handleAdapter.handle(request, response, divisionController);
        assertViewName(mav, DIVISION_UPDATE_VIEW);
        assertModelAttributeAvailable(mav, DIVISION_UPDATE_COMMAND);
        verify(divisionServiceMock);
        resetToNice(divisionServiceMock);
        
        expect(divisionServiceMock.readDivision(new Long(3))).andReturn(null);
        divisionController.setDivisionService(divisionServiceMock);
        replay(divisionServiceMock);
        ModelAndView mav1 = handleAdapter.handle(request, response, divisionController);
        assertViewName(mav1, DIVISION_UPDATE_VIEW);
        assertModelAttributeAvailable(mav1, "empty");
        verify(divisionServiceMock);
        System.out.println("update - OK");
    }
    
    /**
     * Test of update method, of class DivisionController.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        request.setRequestURI("/division/update/3");
        request.setMethod("POST");
        
        divisionForm.setTitle("   ");
        request.setParameter("id", "3");
        request.setParameter("title", divisionForm.getTitle());
        ModelAndView mav = handleAdapter.handle(request, response, divisionController);
        assertViewName(mav, DIVISION_UPDATE_VIEW);
        assertModelAttributeAvailable(mav, DIVISION_UPDATE_COMMAND);
        
        divisionForm.setTitle("testData");
        request.setParameter("title", divisionForm.getTitle());
        expect(divisionServiceMock.updateDivision(anyObject(DivisionForm.class), anyObject(BindingResult.class))).andReturn(true);
        divisionController.setDivisionService(divisionServiceMock);
        replay(divisionServiceMock);
        ModelAndView mav1 = handleAdapter.handle(request, response, divisionController);
        assertEquals(mav1.getViewName(), "redirect:../../" + DIVISION_LIST_URL);
        verify(divisionServiceMock);
        resetToNice(divisionServiceMock);
        
        expect(divisionServiceMock.updateDivision(anyObject(DivisionForm.class), anyObject(BindingResult.class))).andReturn(false);
        divisionController.setDivisionService(divisionServiceMock);
        replay(divisionServiceMock);
        ModelAndView mav2 = handleAdapter.handle(request, response, divisionController);
        assertViewName(mav2, DIVISION_UPDATE_VIEW);
        assertModelAttributeAvailable(mav2, DIVISION_UPDATE_COMMAND);
        verify(divisionServiceMock);
        System.out.println("update - OK");
    }
    
    /**
     * Test of delete method, of class DivisionController.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        request.setRequestURI("/division/delete/3");
        request.setMethod("GET");
        
        expect(divisionServiceMock.deleteDivision(new Long(3))).andReturn(true);
        divisionController.setDivisionService(divisionServiceMock);
        replay(divisionServiceMock);
        ModelAndView mav = handleAdapter.handle(request, response, divisionController);
        assertEquals(mav.getViewName(), "redirect:../../" + DIVISION_LIST_URL);
        System.out.println("delete");
    }
}
