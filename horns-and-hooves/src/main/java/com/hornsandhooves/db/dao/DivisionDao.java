/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.db.dao;

import com.hornsandhooves.db.entity.DivisionImpl;
import com.hornsandhooves.web.form.DivisionForm;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

/**
 * Division DAO class
 * @author Alexander
 */
@Component
public class DivisionDao extends DaoImpl<DivisionImpl, Long> {
    
    /**
     * Конструктор инициализирует супер класс
     */
    public DivisionDao() {
        super(DivisionImpl.class);
    }
    
    /**
     * Проверяет наличие в БД названия Division
     * @param divisionForm
     * @return false - соответствий не найдено, true - Division с таким именем существует
     */
    public boolean checkTitle(DivisionForm divisionForm) {  
        Query q = getSession().createQuery("from DivisionImpl where "
                + "lower(title) like lower(:title)");
        q.setString("title", divisionForm.getTitle());
        if(q.list().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
