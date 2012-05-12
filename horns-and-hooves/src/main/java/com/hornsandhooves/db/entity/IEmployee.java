/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.db.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Интерфейс для entity Employee
 * @author Alexander
 */
public interface IEmployee extends Serializable {

    /**
     * Возвращает статус сотрудника
     * @return Boolean
     */
    Boolean getActive();

    /**
     * Возвращает дату рождения сотрудника
     * @return Date
     */
    Date getBirthdate();

    /**
     * Возвращает обьект Division
     * @return DivisionImpl
     */
    DivisionImpl getDivisionId();

    /**
     * Возвращает имя сотрудника
     * @return String
     */
    String getFirstName();

    /**
     * Возвращает уникальный идентификатор сотрудника
     * @return Long
     */
    Long getId();

    /**
     * Возвращает фамилию сотрудника
     * @return String
     */
    String getLastName();

    /**
     * Возвращает зарплату сотрудника
     * @return double
     */
    double getSalary();

    /**
     * Устанавливает статус сотрудника
     * @param active 
     */
    void setActive(Boolean active);

    /**
     * Устанавливает день рождения сотрудника
     * @param birthdate 
     */
    void setBirthdate(Date birthdate);

    /**
     * Устанавливает объект Division
     * @param divisionId 
     */
    void setDivisionId(DivisionImpl divisionId);

    /**
     * Устанавливает имя сотрудника
     * @param firstName 
     */
    void setFirstName(String firstName);

    /**
     * Устанавливает уникальный идентификатор сотрудника
     * @param id 
     */
    void setId(Long id);

    /**
     * Устанавливает фамилию сотрудника
     * @param lastName 
     */
    void setLastName(String lastName);

    /**
     * Устанавливает зарплату сотрудника
     * @param salary 
     */
    void setSalary(double salary);
    
}
