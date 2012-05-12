/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.db.dao;

import com.hornsandhooves.db.entity.EmployeeImpl;
import com.hornsandhooves.db.entity.IEmployee;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

/**
 * Employee DAO class
 * @author Alexander
 */
@Component
public class EmployeeDao extends DaoImpl<EmployeeImpl, Long> {
    
    /**
     * Конструктор инициализирует супер класс
     */
    public EmployeeDao() {
        super(EmployeeImpl.class);
    }
    
    /**
     * Метод отвечающий за поиск сотрудников
     * @param searchQuery
     * @return List<IEmployee>
     */
    public List<IEmployee> search(String searchQuery) {            
        String correctedString = searchQuery.replaceAll("\\*", "%").replaceAll("\\?", "_");
        Query q = getSession().createQuery("from EmployeeImpl where "
                + "lower(firstName) like lower(:correctedString) OR "
                + "lower(lastName) like lower(:correctedString) OR "
                + "lower(firstName || lastName) like lower(:correctedString) OR "
                + "lower(lastName || firstName) like lower(:correctedString)");
        q.setString("correctedString", correctedString);
        return (List<IEmployee>) q.list();
    }
}
