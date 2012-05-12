/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.db.service;

import com.hornsandhooves.db.dao.DivisionDao;
import com.hornsandhooves.db.entity.IDivision;
import com.hornsandhooves.web.form.DivisionForm;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

/**
 * Интерфейс для service Division
 * @author Alexander
 */
public interface IDivisionService {

	/**
	 * Метод устанавливает значение поля divisionDao
	 * @param divisionDao
	 */
	public void setDivisionDao(DivisionDao divisionDao);
	
    /**
     * Метод отвечающий за создание отдела
     * @param divisionForm
     * @param result
     * @return boolean
     */
    @Transactional
    boolean createDivision(DivisionForm divisionForm, BindingResult result);
    
    /**
     * Метод отвечающий за удаление отдела
     * @param id 
     */
    @Transactional
    boolean deleteDivision(Long id);

    /**
     * Метод отвечающий за чтение отдела
     * @param id
     * @return IDivision
     */
    @Transactional
    IDivision readDivision(Long id);
    
    /**
     * Метод отвечающий за обновление отдела
     * @param divisionForm
     * @param result
     * @return boolean
     */
    @Transactional
    boolean updateDivision(DivisionForm divisionForm, BindingResult result);
    
    /**
     * Метод возвращает все отделы
     * @return List<IDivision>
     */
    @Transactional
    List<IDivision> findAll();
}
