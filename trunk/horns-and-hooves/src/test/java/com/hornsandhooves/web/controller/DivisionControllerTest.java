package com.hornsandhooves.web.controller;

import static org.easymock.EasyMock.*;

import java.net.ContentHandler;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.apache.bcel.generic.GETSTATIC;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import static org.springframework.test.web.ModelAndViewAssert.*;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import com.hornsandhooves.db.service.DivisionServiceImpl;

@Ignore
public class DivisionControllerTest {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
//	private HandlerAdapter handlerAdapter;
//	private DivisionController divisionController;
//	private DivisionServiceImpl divisionServiceMock;
	
	/*@Inject
    private ApplicationContext applicationContext;*/
	
	/*@Autowired
	private DivisionController divisionController;*/
	
	@Before
    public void setUp() {
		request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
//	    handlerAdapter = applicationContext.getBean(HandlerAdapter.class);
//	    divisionController = new DivisionController();
        //divisionServiceMock = createMock(DivisionServiceImpl.class);
	}
	
	@Test
    public void testFindAll() throws Exception {
		System.out.println("findAll");
		
//		divisionController.handleRequest(request, response);
		
		/*request.setRequestURI("division");
		request.setMethod("GET");
		*/
//		final ModelAndView mav = handlerAdapter.handle(request, response, divisionController);
//   		assertViewName(mav, "division_list");
		System.out.println("findAll - OK");
	}
}
