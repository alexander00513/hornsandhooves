/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.db.service;

import com.hornsandhooves.db.dao.DivisionDao;
import com.hornsandhooves.db.dao.EmployeeDao;
import com.hornsandhooves.db.entity.IEmployee;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

/**
 * Интерфейс для service Employee
 * @author Alexander
 */
public interface IEmployeeService {
	
	/**
	 * Метод устанавливает значение поля employeeDao
	 * @param employeeDao
	 */
	public void setEmployeeDao(EmployeeDao employeeDao);
	
	/**
	 * Метод устанавливает значение поля divisionDao
	 * @param divisionDao
	 */
	public void setDivisionDao(DivisionDao divisionDao);

    /**
     * Метод отвечающий за создание сотрудника
     * @param employee
     * @param result
     * @return boolean
     */
    @Transactional
    boolean createEmployee(IEmployee employee, BindingResult result);

    /**
     * Метод отвечающий за удаление сотрудника
     * @param id
     * @return boolean
     */
    @Transactional
    boolean deleteEmployee(Long id);

    /**
     * Метод отвечающий за чтение сотрудника
     * @param id
     * @return IEmployee
     */
    @Transactional
    IEmployee readEmployee(Long id);

    /**
     * Метод отвечающий за обновление сотрудника
     * @param employee
     * @param result
     * @return boolean
     */
    @Transactional
    boolean updateEmployee(IEmployee employee, BindingResult result);
    
    /**
     * Метод возвращает всех сотрудников
     * @return List<IEmployee>
     */
    @Transactional
    List<IEmployee> findAll();
    
    /**
     * Метод отвечающий за поиск сотрудников
     * @param searchQuery
     * @return List<IEmployee>
     */
    @Transactional
    List<IEmployee> search(String searchQuery);
    
}
