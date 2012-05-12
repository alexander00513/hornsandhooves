/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.db.entity;

import javax.persistence.*;

/**
 * Division entity class
 * @author Alexander
 */
@Entity
@Table(name = "division")
public class DivisionImpl implements IDivision {
    
    /**
     * Поле id с типом {@link Long}:
     * Уникальный илентификатор отдела
     */
    @Id
    @SequenceGenerator(name = "id", sequenceName = "division_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    @Column(name = "id")
    private Long id;
    
    /**
     * Поле title с типом {@link String}
     */
    @Column(name = "title")
    private String title;

    public DivisionImpl() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
