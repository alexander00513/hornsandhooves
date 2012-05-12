/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.db.entity;

import java.io.Serializable;

/**
 * Интерфейс для entity Division
 * @author Alexander
 */
public interface IDivision extends Serializable {

    /**
     * Возвращает уникальный идентификатор отдела
     * @return Long
     */
    Long getId();

    /**
     * Возвращает название отдела
     * @return String
     */
    String getTitle();
    
    /**
     * Устанавливает уникальный идентификатор отдела
     * @param id 
     */
    void setId(Long id);

    /**
     * Устанавливает название отдела
     * @param title 
     */
    void setTitle(String title);
    
}
