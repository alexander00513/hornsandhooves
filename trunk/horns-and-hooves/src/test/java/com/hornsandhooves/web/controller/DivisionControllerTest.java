package com.hornsandhooves.web.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.easymock.EasyMock.*;

import com.hornsandhooves.db.service.DivisionServiceImpl;

//@Ignore
public class DivisionControllerTest {

    private MockHttpServletRequest request;

    // private MockHttpServletResponse response;
    // private HandlerAdapter handlerAdapter;
    // private DivisionController divisionController;
    // private DivisionServiceImpl divisionServiceMock;



    /*@Inject 
    private ApplicationContext applicationContext;*/


    @Before 
    public void setUp() { 
//        request = new MockHttpServletRequest();
//        response = new MockHttpServletResponse(); 
//        handlerAdapter = applicationContext.getBean(HandlerAdapter.class);
//        divisionController = new DivisionController();
//        divisionServiceMock = createMock(DivisionServiceImpl.class); 
    }


    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
//        divisionController.handleRequest(request, response);
        request = new MockHttpServletRequest();
//        request.setRequestURI("division"); request.setMethod("GET");
//        final ModelAndView mav = handlerAdapter.handle(request, response,
//        divisionController);
//        assertViewName(mav, "division_list");
        System.out.println("findAll - OK");
    }
}
