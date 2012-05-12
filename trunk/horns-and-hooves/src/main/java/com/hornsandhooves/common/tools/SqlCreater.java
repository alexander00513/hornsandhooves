/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.common.tools;

import java.util.Random;

/**
 * Класс для создания тестовых данных
 * @author Alexander
 */

public class SqlCreater {
    
    //Требуемое колличество отделов
    private int division = 56;
    //Требуемое колличество сотрудников
    private int employee = 1001;
    
    /**
     * Выводит в консоль тестовые данные
     * @param args 
     */
    public static void main(String[] args) {
        SqlCreater sqlCreater = new SqlCreater();
               
        for (int i = 1; i < sqlCreater.division; i++) {
            System.out.println(sqlCreater.getSqlDivision(i));
        }
        
        for (int i = 1; i < sqlCreater.employee; i++) {
            int rand = sqlCreater.getRandom();
            System.out.println(sqlCreater.getSqlEmployee(i, rand));
        }
    }
    
    /**
     * Возвращает sql запрос для БД с тестовыми данными для таблицы division
     * @param i
     * @return sql
     */
    private String getSqlDivision(int i) {    
        String sql = ("INSERT INTO division "
            + "(title) "
            + "VALUES ('Division" + i +"');");
        return sql;
    }
    
    /**
     * Возвращает sql запрос для БД с тестовыми данными для таблицы employee
     * @param i
     * @param rand
     * @return sql
     */
    private String getSqlEmployee(int i, int rand) {    
        String sql = ("INSERT INTO employee "
                + "(first_name, last_name, salary, birthdate, active, division_id) "
                + "VALUES ('FirstName" + i +"' , 'LastName" + i +"', '1300', '14.05.1990', '1', '"+ rand +"');");
        return sql;
    }
    
    /**
     * Генератор случайных чисел для поля divisionId в Employee
     * @return randomValue
     */
    private int getRandom() {
        Random random = new Random();
        int randomValue = random.nextInt(division - 1) + 1;
        return randomValue;
    }
}
