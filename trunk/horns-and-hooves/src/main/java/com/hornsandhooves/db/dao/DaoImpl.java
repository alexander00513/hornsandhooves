/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.db.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Общий DAO class
 * @author Alexander
 */
public class DaoImpl <T, PK extends Serializable> implements IDao <T, PK> {
    
    private Class<T> type;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    
    public DaoImpl(Class<T> type) {
        this.type = type;
    }
    
    public PK create(T o) {
        return (PK) getSession().save(o);
    }
    
    public T read(PK id) {
        return (T) getSession().get(type, id);
    }
    
    public void update(T o) {
        getSession().update(o);
    }
    
    public void delete(T o) {
        getSession().delete(o);
    }

    
    public List<T> findAll() {
        return  getSession().createQuery("from " + type.getName()).list();
    }
    
    /**
     * Возвращает текущую сессию hibernate
     * @return sessionFactory.getCurrentSession()
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    public T getInstace() throws java.lang.InstantiationException, IllegalAccessException {
        return type.newInstance();
    }
}
