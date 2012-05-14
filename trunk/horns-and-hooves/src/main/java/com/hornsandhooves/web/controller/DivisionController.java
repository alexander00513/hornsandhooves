/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.web.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.hornsandhooves.db.entity.IDivision;
import com.hornsandhooves.web.form.DivisionForm;
import java.util.Map;
import java.util.HashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;
import com.hornsandhooves.db.service.IDivisionService;
import com.hornsandhooves.web.validation.DivisionValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static com.hornsandhooves.common.tools.Url.*;
import static com.hornsandhooves.common.tools.View.*;
import static com.hornsandhooves.common.tools.Command.*;

/**
 *
 * @author Alexander
 */
@Controller
@RequestMapping(DIVISION_LIST_URL)
public class DivisionController {

    @Autowired
    private IDivisionService divisionService;
    
    public void setDivisionService(IDivisionService divisionService) {
        this.divisionService = divisionService;
    }

    /**
     * Выводит на страницу список отделов
     * @return ModelAndView
     */
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView findAll() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("divisionList", divisionService.findAll());
        return new ModelAndView(DIVISION_LIST_VIEW, model);
    }
    
    /**
     * Выводит на страницу форму создания отдела
     * @return ModelAndView
     */
    @RequestMapping(value=DIVISION_CREATE_URL, method=RequestMethod.GET)
    public ModelAndView create() {
        DivisionForm divisionForm = new DivisionForm();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put(DIVISION_CREATE_COMMAND, divisionForm);
        return new ModelAndView(DIVISION_CREATE_VIEW, model);
    }
    
    /**
     * Создаёт отдел
     * @param divisionForm
     * @param result
     * @return ModelAndView
     */
    @RequestMapping(value=DIVISION_CREATE_URL, method=RequestMethod.POST)
    public ModelAndView create(@ModelAttribute(DIVISION_CREATE_COMMAND) DivisionForm divisionForm, BindingResult result) {
        new DivisionValidation().validate(divisionForm, result);
        Map<String, Object> model = new HashMap<String, Object>();
        divisionForm.getTitle().trim().replaceAll("<", "&#60;").replaceAll(">", "&#62;");
        if(result.hasErrors()) {
            model.put(DIVISION_CREATE_COMMAND, divisionForm);
        } else {
            boolean resultService = divisionService.createDivision(divisionForm, result);
            if(resultService) {
                return new ModelAndView("redirect:../" + DIVISION_LIST_URL);
            } else {
                model.put(DIVISION_CREATE_COMMAND, divisionForm);
            }
        }
        return new ModelAndView(DIVISION_CREATE_VIEW, model);
    }
    
    /**
     * Выводит на страницу карточку отдела
     * @param id
     * @return ModelAndView
     */
    @RequestMapping(value=DIVISION_READ_URL + "/{id}", method=RequestMethod.GET)
    public ModelAndView read(@PathVariable("id") Long id) {
        IDivision division = divisionService.readDivision(id);
        Map<String, Object> model = new HashMap<String, Object>();
        if(division != null) {
            model.put("division", division);
        } else {
            model.put("empty", 1);
        }
        return new ModelAndView(DIVISION_READ_VIEW, model);
    }
    
    /**
     * Выводит на страницу форму редактирования отдела
     * @param id
     * @return ModelAndView
     */
    @RequestMapping(value=DIVISION_UPDATE_URL + "/{id}", method=RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") Long id) {
        IDivision division = divisionService.readDivision(id);
        Map<String, Object> model = new HashMap<String, Object>();
        if(division != null) {
            model.put(DIVISION_UPDATE_COMMAND, division);
        } else {
            model.put("empty", 1); 
        }
        return new ModelAndView(DIVISION_UPDATE_VIEW, model);
    }
    
    /**
     * Обновляет отдел
     * @param divisionForm
     * @param result
     * @return ModelAndView
     */
    @RequestMapping(value=DIVISION_UPDATE_URL + "/{id}", method=RequestMethod.POST)
    public ModelAndView update(@ModelAttribute(DIVISION_UPDATE_COMMAND) DivisionForm divisionForm, BindingResult result) {
        new DivisionValidation().validate(divisionForm, result);
        Map<String, Object> model = new HashMap<String, Object>();
        divisionForm.getTitle().trim().replaceAll("<", "&#60;").replaceAll(">", "&#62;");
        if(result.hasErrors()) {
            model.put(DIVISION_UPDATE_COMMAND, divisionForm);
        } else {
            boolean resultService = divisionService.updateDivision(divisionForm, result);
            if(resultService) {
                return new ModelAndView("redirect:../../" + DIVISION_LIST_URL);
            } else if(!resultService && result.hasErrors()) {
                model.put(DIVISION_UPDATE_COMMAND, divisionForm);
            } else {
                model.put("empty", 1);
            }
        }
        return new ModelAndView(DIVISION_UPDATE_VIEW, model);
    }
    
    /**
     * Удаляет отдел
     * @param id
     * @return ModelAndView
     */
    @RequestMapping(value=DELETE_URL + "/{id}", method=RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") Long id) {
        divisionService.deleteDivision(id);
        return new ModelAndView("redirect:../../" + DIVISION_LIST_URL);
    }
}
