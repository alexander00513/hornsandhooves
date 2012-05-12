/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.web.validation;

import com.hornsandhooves.web.form.EmployeeForm;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

/**
 * Класс валидации для Employee
 * @author Alexander
 */
public class EmployeeValidation {
    
    public void validate(EmployeeForm employeeForm, Errors errors) throws ParseException {     
        EmployeeValidation ev = new EmployeeValidation();
        ev.firstNameValidate(errors, employeeForm);
        ev.lastNameValidate(errors, employeeForm);
        ev.birthdateValidate(errors, employeeForm);
        ev.salaryValidate(errors, employeeForm);
    }
    
    /**
     * Метод отвечающий за валидацию поля firstName
     * @param errors
     * @param employeeForm 
     */
    protected void firstNameValidate(Errors errors, EmployeeForm employeeForm) {
        
        //Проверка поля на пустоту и наличие пробелов
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "employeeForm.firstName.empty");
        
        //Проверка поля на наличие некорректных символов
        if(!errors.hasFieldErrors("firstName")) {
            Pattern pattern = Pattern.compile("[a-zA-Zа-яА-яёЁ]*");
            Matcher matcher = pattern.matcher(employeeForm.getFirstName().trim());
            boolean didMatch = matcher.matches();
            if (!didMatch) {
                errors.rejectValue("firstName", "employeeForm.firstName.invalidPattern");
            }
        }
        
        //Проверка поля на колличество символов
        if(!errors.hasFieldErrors("firstName")) {
            Pattern pattern = Pattern.compile(".{0,255}");
            Matcher matcher = pattern.matcher(employeeForm.getFirstName());
            boolean didMatch = matcher.matches();
            if (!didMatch) {
                errors.rejectValue("firstName", "employeeForm.firstName.invalidPatternLenght");
            }
        }
    }

    /**
     * Метод отвечающий за валидацию поля lastName
     * @param errors
     * @param employeeForm 
     */
    protected void lastNameValidate(Errors errors, EmployeeForm employeeForm) {
        
        //Проверка поля на пустоту и наличие пробелов
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "employeeForm.lastName.empty");
        
        //Проверка поля на наличие некорректных символов
        if(!errors.hasFieldErrors("lastName")) {
            Pattern pattern = Pattern.compile("[a-zA-Zа-яА-яёЁ]*");
            Matcher matcher = pattern.matcher(employeeForm.getLastName().trim());
            boolean didMatch = matcher.matches();
            if (!didMatch) {
                errors.rejectValue("lastName", "employeeForm.lastName.invalidPattern");
            }
        }
        
        //Проверка поля на колличество символов
        if(!errors.hasFieldErrors("lastName")) {
            Pattern pattern = Pattern.compile(".{0,255}");
            Matcher matcher = pattern.matcher(employeeForm.getLastName());
            boolean didMatch = matcher.matches();
            if (!didMatch) {
                errors.rejectValue("lastName", "employeeForm.lastName.invalidPatternLenght");
            }
        }
    }

    /**
     * Метод отвечающий за валидацию поля salary
     * @param errors
     * @param employeeForm 
     */
    protected void salaryValidate(Errors errors, EmployeeForm employeeForm) {
        
        //Проверка поля на пустоту и наличие пробелов
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "salary", "employeeForm.salary.empty");
        
        String salary = employeeForm.getSalary().trim();
        
        //Проверка поля на наличие нулей
        if (!errors.hasFieldErrors("salary")) {
            Pattern pattern = Pattern.compile("0*\\.*0*");
            Matcher matcher = pattern.matcher(salary);
            boolean didMatch = matcher.matches();
            if (didMatch) {
                errors.rejectValue("salary", "employeeForm.salary.invalidPatternZero");
            }
        }
        
        //Проверка поля на наличие неккоректных символов
        if (!errors.hasFieldErrors("salary")) {
            Pattern pattern = Pattern.compile("\\.*[0-9]*\\.*[0-9]*\\.*");
            Matcher matcher = pattern.matcher(salary);
            boolean didMatch = matcher.matches();
            if (!didMatch) {
                errors.rejectValue("salary", "employeeForm.salary.invalidPattern");
            }
        }
        
        //Проверка поля на соответствие формату
        if (!errors.hasFieldErrors("salary")) {
            Pattern pattern = Pattern.compile("([0-9]{1,15})|([0-9]{1,15}\\.[0-9]{1,2})");
            Matcher matcher = pattern.matcher(salary);
            boolean didMatch = matcher.matches();
            if (!didMatch) {
                errors.rejectValue("salary", "employeeForm.salary.invalidPatternFormat");
            }
        }
    }
    
    /**
     * Метод отвечающий за валидацию поля date 
     * @param errors
     * @param employeeForm
     * @throws ParseException
     */
    protected void birthdateValidate(Errors errors, EmployeeForm employeeForm) throws ParseException {
        
        //Проверка поля даты на пустоту и наличие пробелов
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthdate", "employeeForm.birthdate.empty");
        
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        
        //Проверка поля даты на правильность формата
        if (!errors.hasFieldErrors("birthdate")) {
            try {
                df.parse(employeeForm.getBirthdate());
            } catch (ParseException e) {
                errors.rejectValue("birthdate", "employeeForm.birthdate.format");
            }
            //Поправить в тесте
            Pattern pattern = Pattern.compile("([0-9]*\\.[0-9]*\\.[0-9]*)");
            Matcher matcher = pattern.matcher(employeeForm.getBirthdate());
            boolean didMatch = matcher.matches();
            if (!didMatch) {
                errors.rejectValue("birthdate", "employeeForm.birthdate.format");
            }
        }
        
        //Проверка поля даты на действительность введённых значений
        if (!errors.hasFieldErrors("birthdate")) {
            try {                                   
                df.setLenient(false);
                df.parse(employeeForm.getBirthdate());
            } catch(ParseException e) {
                errors.rejectValue("birthdate", "employeeForm.birthdate.isFalse");
            } 
        }
        
        //Проверка поля даты на то, что бы она не была больше текущего года
        if (!errors.hasFieldErrors("birthdate")) {
            Date birthdate = df.parse(employeeForm.getBirthdate());
            Date date = new Date();
            if (birthdate.after(date)) {
                errors.rejectValue("birthdate", "employeeForm.birthdate.isMore");
            }    
        }
        
        //Проверка поля даты на на то, что бы она не была меньше 1900 года
        if (!errors.hasFieldErrors("birthdate")) {
            Date birthdate = df.parse(employeeForm.getBirthdate());
            Date date = df.parse("01.01.1900");
            if (birthdate.before(date)) {
                errors.rejectValue("birthdate", "employeeForm.birthdate.isLess");
            }    
        } 
    }
}
