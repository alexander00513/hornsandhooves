/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.web.validation;

import com.hornsandhooves.web.form.DivisionForm;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

/**
 * Класс валидации для Division
 * @author Alexander
 */
public class DivisionValidation {
    
    public void validate(DivisionForm divisionForm, Errors errors) {
        DivisionValidation dv = new DivisionValidation();
        dv.titleValidate(errors, divisionForm);
    }
    
    /** Метод отвечающий за валидацию поля title
     * @param errors
     * @param divisionForm
     * @param divisionService 
     */
    protected void titleValidate(Errors errors, DivisionForm divisionForm) {
        
        //Проверка поля на пустоту и наличие пробелов
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "divisionForm.title.empty");
        
        //Проверка поля на колличество символов
        if(!errors.hasFieldErrors("title")) {
            Pattern pattern = Pattern.compile(".{1,255}");
            Matcher matcher = pattern.matcher(divisionForm.getTitle());
            boolean didMatch = matcher.matches();
            if (!didMatch) {
                errors.rejectValue("title", "divisionForm.title.invalidPatternLenght");
            }
        }
    }
}
