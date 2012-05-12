/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.db.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * Employee entity class
 * @author Alexander
 */
@Entity
@Table(name = "employee")
public class EmployeeImpl implements IEmployee {
    
    /**
     * Поле id с типом {@link Long}
     * Уникальный илентификатор сотрудника
     */
    @Id
    @SequenceGenerator(name = "id", sequenceName = "employee_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    @Column(name = "id")
    private Long id;
    
    /**
     * Поле firstName с типом {@link String}
     * Имя сотрудника
     */
    @Column(name = "first_name")
    private String firstName;
    
    /**
     * Поле lastName с типом {@link String}
     * Фамилия сотрудника
     */
    @Column(name = "last_name")
    private String lastName; 
    
    /**
     * Поле salary с типом {@link Double}
     * Зарплата сотрудника
     */
    @Column(name = "salary")
    private double salary;
    
    /**
     * Поле birthdate с типом {@link Date}
     * Дата рождения сотрудника
     */
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    
    /**
     * Поле active с типом {@link Boolean}
     * Статус сотрудника
     */
    @Column(name = "active")
    private Boolean active;
    
    /**
     * Поле divisionId с типом {@link DivisionImpl}
     * Принадлежность к Division
     */
    @JoinColumn(name = "division_id", referencedColumnName = "id")
    @ManyToOne(targetEntity=DivisionImpl.class, fetch = FetchType.EAGER)
    private DivisionImpl divisionId;

    public EmployeeImpl() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public DivisionImpl getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(DivisionImpl divisionId) {
        this.divisionId = divisionId;
    }    
}
