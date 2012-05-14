package com.hornsandhooves.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import static com.hornsandhooves.common.tools.Url.*;
import static com.hornsandhooves.common.tools.View.*;
import static com.hornsandhooves.common.tools.Command.*;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.ModelAndViewAssert.*;

import com.hornsandhooves.db.entity.DivisionImpl;
import com.hornsandhooves.db.entity.IDivision;
import com.hornsandhooves.db.service.DivisionServiceImpl;
import com.hornsandhooves.web.form.DivisionForm;
import com.hornsandhooves.web.validation.DivisionValidation;

public class DivisionControllerTest {
    
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private AnnotationMethodHandlerAdapter handleAdapter;
    private DivisionServiceImpl divisionServiceMock;
    private DivisionValidation divisionValidationMock;
    private DivisionController divisionController;
    private DivisionImpl division;
    private DivisionForm divisionForm;
    private BeanPropertyBindingResult result;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        handleAdapter = new AnnotationMethodHandlerAdapter();
        divisionServiceMock = createMock(DivisionServiceImpl.class);
        divisionValidationMock = createMock(DivisionValidation.class);
        divisionController = new DivisionController();
        divisionController.setDivisionService(divisionServiceMock);
        division = new DivisionImpl();
//        division.setId(new Long(3));
//        division.setTitle("testData");
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
        divisionForm.setTitle("   ");
        divisionValidationMock.validate(divisionForm, result);
        replay(divisionValidationMock);
        request.setRequestURI("/division/create");
        request.setMethod("POST");
        request.setAttribute(DIVISION_CREATE_COMMAND, divisionForm);
        ModelAndView mav = handleAdapter.handle(request, response, divisionController);
        assertViewName(mav, DIVISION_CREATE_VIEW);
        
        /*Map<String, Object> model = new HashMap<String, Object>();
        model.put(DIVISION_CREATE_COMMAND, divisionForm);
        assertModelAttributeValues(mav, model);*/
        System.out.println("create - OK");
    }
}
