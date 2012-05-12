/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.db.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Интерфейс для DAO
 * @author Alexander
 */
public interface  IDao <T, PK extends Serializable> {
    
    /**
     * Метод отвечает за создание записи в БД
     * @param newInstance
     * @return PK
     */
    @SuppressWarnings("unchecked")
    public PK create(T newInstance);
    
    /**
     * Метод отвечает за чтение записи из БД
     * @param id
     * @return T
     */
    @SuppressWarnings("unchecked")
    public T read(PK id);

    /**
     * Метод отвечает за обновление записи в БД
     * @param transientObject 
     */
    public void update(T transientObject);

    /**
     * Метод отвечает за удаление записи из БД
     * @param persistentObject 
     */
    public void delete(T persistentObject);
    
    /**
     * Возвращает все записи из таблицы БД опеределённого типа 
     * @return List<T>
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll();
    
    /**
     * Возвращает новый экземпляр типа
     * @return Новый экземпляр типа
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    T getInstace() throws InstantiationException, IllegalAccessException;
}
