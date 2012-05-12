/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.web.controller;

import java.util.List;
import com.hornsandhooves.db.entity.EmployeeImpl;
import javax.servlet.http.HttpServletRequest;
import com.hornsandhooves.common.tools.Conversions;
import org.springframework.validation.BindingResult;
import java.text.ParseException;
import com.hornsandhooves.db.entity.IEmployee;
import com.hornsandhooves.db.service.IDivisionService;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.HashMap;
import com.hornsandhooves.db.service.IEmployeeService;
import com.hornsandhooves.web.form.EmployeeForm;
import com.hornsandhooves.web.validation.EmployeeValidation;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import static com.hornsandhooves.common.tools.Url.*;
import static com.hornsandhooves.common.tools.View.*;
import static com.hornsandhooves.common.tools.Command.*;

/**
 *
 * @author Alexander
 */
@Controller
@RequestMapping(INDEX_URL)
public class EmployeeController {
    
    @Autowired
    private IEmployeeService employeeService;
    
    @Autowired
    private IDivisionService divisionService;
    
    /**
     * Выводит на страницу список сотрудников
     * @return ModelAndView
     */
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView findAll() {
        Map<String, Object> model = new HashMap<String, Object>();   
        model.put("employeeList", employeeService.findAll());
        model.put("divisionList", divisionService.findAll());
        return new ModelAndView(INDEX_VIEW, model);
    }
    
    /**
     * Выводит на страницу форуму создания сотрудника
     * @return ModelAndView
     */
    @RequestMapping(value=EMPLOYEE_CREATE_URL, method=RequestMethod.GET)
    public ModelAndView create() {
        EmployeeForm employeeForm = new EmployeeForm();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put(EMPLOYEE_CREATE_COMMAND, employeeForm);
        model.put("divisionList", divisionService.findAll());
        return new ModelAndView(EMPLOYEE_CREATE_VIEW, model);
    }
    
    /**
     * Создаёт сотрудника
     * @param employeeForm
     * @param result
     * @return ModelAndView
     * @throws ParseException 
     */
    @RequestMapping(value=EMPLOYEE_CREATE_URL, method=RequestMethod.POST)
    public ModelAndView create(@ModelAttribute(EMPLOYEE_CREATE_COMMAND) EmployeeForm employeeForm, BindingResult result) throws ParseException {
        new EmployeeValidation().validate(employeeForm, result);
        Map<String, Object> model = new HashMap<String, Object>();
        if(result.hasErrors()) {
            model.put(EMPLOYEE_CREATE_COMMAND, employeeForm);
            model.put("divisionList", divisionService.findAll());
        } else {
            IEmployee employee = new Conversions().getInstanceEmployee(employeeForm);
            boolean resultService = employeeService.createEmployee(employee, result);
            if(resultService) {
                return new ModelAndView("redirect:" + INDEX_URL);
            } else {
                model.put(EMPLOYEE_CREATE_COMMAND, employeeForm);
                model.put("divisionList", divisionService.findAll());
            }
        }
        return new ModelAndView(EMPLOYEE_CREATE_VIEW, model);
    }
    
    /**
     * Выводит на страницу карточку сотрудника
     * @param id
     * @return ModelAndView
     * @throws ParseException 
     */
    @RequestMapping(value=EMPLOYEE_READ_URL + "/{id}", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView read(@PathVariable("id") Long id) throws ParseException {   
        IEmployee employee = (EmployeeImpl) employeeService.readEmployee(id);
        Map<String, Object> model = new HashMap<String, Object>();
        if(employee != null) {
            Conversions conversions = new Conversions();
            EmployeeForm employeeC = conversions.getInstanceEmployeeForm(employee);           
            model.put("employee", employeeC);
        } else {
            model.put("empty", 1);
        }
        return new ModelAndView(EMPLOYEE_READ_VIEW, model);          
    }
    
    /**
     * Выводит на страницу форму редактирования сотрудника
     * @param id
     * @return ModelAndView
     * @throws ParseException 
     */
    @RequestMapping(value={EMPLOYEE_UPDATE_URL + "/{id}", SEARCH_EMPLOYEE_UPDATE_URL + "/{id}"}, method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView update(@PathVariable("id") Long id) throws ParseException {
        IEmployee employee = employeeService.readEmployee(id);
        Map<String, Object> model = new HashMap<String, Object>();
        if(employee != null) {
            Conversions conversions = new Conversions();
            EmployeeForm employeeFormC = conversions.getInstanceEmployeeForm(employee);
            model.put(EMPLOYEE_UPDATE_COMMAND, employeeFormC);
            model.put("divisionList", divisionService.findAll());
        } else {
            model.put("empty", 1);
        }
        return new ModelAndView(EMPLOYEE_UPDATE_VIEW, model);
    }
    
    /**
     * Обновляет сотрудника
     * @param employeeForm
     * @param result
     * @return ModelAndView
     * @throws ParseException 
     */
    @RequestMapping(value=EMPLOYEE_UPDATE_URL + "/{id}", method=RequestMethod.POST)
    public ModelAndView update(@ModelAttribute(EMPLOYEE_UPDATE_COMMAND) EmployeeForm employeeForm, BindingResult result) throws ParseException {
        new EmployeeValidation().validate(employeeForm, result);
        Map<String, Object> model = new HashMap<String, Object>();
        if(result.hasErrors()) {
            model.put(EMPLOYEE_UPDATE_COMMAND, employeeForm);
            model.put("divisionList", divisionService.findAll());
        } else {
            IEmployee employee = new Conversions().getInstanceEmployee(employeeForm);
            boolean resultService = employeeService.updateEmployee(employee, result);
            if(resultService) {
                return new ModelAndView("redirect:" + INDEX_URL);
            } else if(!resultService && result.hasErrors()) {
                model.put(EMPLOYEE_UPDATE_COMMAND, employeeForm);
                model.put("divisionList", divisionService.findAll());
            } else {
                model.put("empty", 1);
            }
        }
        return new ModelAndView(EMPLOYEE_UPDATE_VIEW, model);
    }
    
    /**
     * Удаляет сотрудника
     * @param id
     * @return ModelAndView
     */
    @RequestMapping(value=DELETE_URL + "/{id}", method=RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return new ModelAndView("redirect:" + INDEX_URL);
    }
    
    /**
     * Отвечает за поиск по сотрудникам
     * @param request
     * @return 
     */
    @RequestMapping(value=INDEX_URL + SEARCH_URL, method=RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request) {
        try {
            String searchQuery = request.getParameter("searchQuery").trim();
            Map<String, Object> model = new HashMap<String, Object>();
            if(!searchQuery.isEmpty()) {
                List <IEmployee> employee = (List <IEmployee>) employeeService.search(searchQuery);
                if(!employee.isEmpty()) {
                     model.put("employeeListSearch", employee);
                     model.put("searchQuery", searchQuery);
                     return new ModelAndView(INDEX_VIEW, model);
                } else {
                     model.put("searchQuery", searchQuery);
                     model.put("empty", 1);
                     return new ModelAndView(INDEX_VIEW, model);
                }
            }
        } catch(NullPointerException npe) {}
        return new ModelAndView("redirect:" + INDEX_URL);
    }
}
