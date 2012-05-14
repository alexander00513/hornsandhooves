package com.hornsandhooves.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import static com.hornsandhooves.common.tools.Url.*;
import static com.hornsandhooves.common.tools.View.*;
import static com.hornsandhooves.common.tools.Command.*;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;
import static org.springframework.test.web.ModelAndViewAssert.*;

import com.hornsandhooves.db.entity.DivisionImpl;
import com.hornsandhooves.db.entity.IDivision;
import com.hornsandhooves.db.service.DivisionServiceImpl;
import com.hornsandhooves.web.form.DivisionForm;
import com.hornsandhooves.web.validation.DivisionValidation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:data.hibernate.xml"})
public class DivisionControllerTest extends TestCase{
    
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private AnnotationMethodHandlerAdapter handleAdapter;
    private DivisionServiceImpl divisionServiceMock;
    private DivisionImpl division;
    private DivisionForm divisionForm;
    private BindingResult result;

    @Autowired
    private DivisionController divisionController;
    
    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        handleAdapter = new AnnotationMethodHandlerAdapter();
        divisionServiceMock = createMock(DivisionServiceImpl.class);
        divisionController.setDivisionService(divisionServiceMock);
        division = new DivisionImpl();
        divisionForm = new DivisionForm();
        result = new BeanPropertyBindingResult(divisionForm, "divisionForm"); 
    }
    
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        List<IDivision> collection = new ArrayList<IDivision>();
        collection.add(division);
        expect(divisionServiceMock.findAll()).andReturn(collection);
        replay(divisionServiceMock);
        request.setMethod("GET");
        request.setRequestURI("division");
        ModelAndView mav = handleAdapter.handle(request, response, divisionController);
        verify(divisionServiceMock);
        assertViewName(mav, DIVISION_LIST_VIEW);
        assertCompareListModelAttribute(mav, "divisionList", collection);
        verify(divisionServiceMock);
        System.out.println("findAll - OK");
    }
    
    @Test
    public void testCreate_0args() throws Exception {
        System.out.println("create");
        request.setRequestURI("/division/create");
        request.setMethod("GET");
        ModelAndView mav = handleAdapter.handle(request, response, divisionController);
        assertViewName(mav, DIVISION_CREATE_VIEW);
        
        /*Map<String, Object> model = new HashMap<String, Object>();
        model.put(DIVISION_CREATE_COMMAND, divisionForm);
        assertModelAttributeValues(mav, model);*/
        System.out.println("create - OK");
    }
    
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        
        /*divisionForm.setTitle("   ");
        request.setRequestURI("/division/create");
        request.setMethod("POST");
        request.setParameter("title", divisionForm.getTitle());
        ModelAndView mav = handleAdapter.handle(request, response, divisionController);
        assertViewName(mav, DIVISION_CREATE_VIEW);*/
        request.removeAllParameters();
        response.reset();
        
        /*divisionForm.setTitle("testData");
        request.setRequestURI("/division/create");
        request.setMethod("POST");
        request.setParameter("title", divisionForm.getTitle());
        expect(divisionServiceMock.createDivision(divisionForm, result)).andReturn(true);
        replay(divisionServiceMock);
        
        ModelAndView mav1 = handleAdapter.handle(request, response, divisionController);
//        assertViewName(mav1, "../division");
        
        assertEquals(mav1.getViewName(), "redirect:../" + DIVISION_LIST_URL);
        
        verify(divisionServiceMock);*/
        
        divisionForm.setTitle("testData");
        request.setRequestURI("/division/create");
        request.setMethod("POST");
        request.setParameter("title", divisionForm.getTitle());
        expect(divisionServiceMock.createDivision(divisionForm, result)).andStubReturn(true);
        replay(divisionServiceMock);
        ModelAndView mav1 = handleAdapter.handle(request, response, divisionController);
        assertViewName(mav1, DIVISION_CREATE_VIEW);
        verify(divisionServiceMock);
        System.out.println("create - OK");
    }
}
