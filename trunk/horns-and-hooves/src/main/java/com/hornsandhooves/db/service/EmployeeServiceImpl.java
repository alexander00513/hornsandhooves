/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.db.service;

import com.hornsandhooves.db.dao.DivisionDao;
import com.hornsandhooves.db.dao.EmployeeDao;
import com.hornsandhooves.db.entity.EmployeeImpl;
import com.hornsandhooves.db.entity.IEmployee;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

/**
 * Service class
 * @author Alexander
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeDao employeeDao;
    
    @Autowired
    private DivisionDao divisionDao;
    
    
    public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void setDivisionDao(DivisionDao divisionDao) {
		this.divisionDao = divisionDao;
	}

	public boolean createEmployee(IEmployee employee, BindingResult result) {
        if(employee.getDivisionId().getId() == null || divisionDao.read(employee.getDivisionId().getId()) == null) {
            result.rejectValue("divisionId", "employeeForm.divisionId.notFound");
            return false;
        } else {
            IEmployee emp = new EmployeeImpl();
            emp.setActive(true);
            emp.setBirthdate(employee.getBirthdate());
            emp.setDivisionId(employee.getDivisionId());
            emp.setFirstName(employee.getFirstName());
            emp.setLastName(employee.getLastName());
            emp.setSalary(employee.getSalary());
            employeeDao.create((EmployeeImpl) emp);
            return true;
        }
    }
    
    public IEmployee readEmployee(Long id) {
        return employeeDao.read(id);
    }
    
    public boolean updateEmployee(IEmployee employee, BindingResult result) {
        IEmployee emp = employeeDao.read(employee.getId());
        if(emp == null) {
            return false;
        } else if(divisionDao.read(employee.getDivisionId().getId()) == null) {
            result.rejectValue("divisionId", "employeeForm.divisionId.notFound");
            return false;
        } else {
            emp.setActive(employee.getActive());
            emp.setBirthdate(employee.getBirthdate());
            emp.setDivisionId(employee.getDivisionId());
            emp.setFirstName(employee.getFirstName());
            emp.setLastName(employee.getLastName());
            emp.setSalary(employee.getSalary());
            employeeDao.update((EmployeeImpl) emp);
            return true;
        }
    }
    
    public boolean deleteEmployee(Long id) {
        IEmployee employee = employeeDao.read(id);
        if(employee == null) {
            return false;
        } else {
            employeeDao.delete((EmployeeImpl) employee);
            return true;
        } 
    }
    
    public List<IEmployee> findAll() {
        return new ArrayList<IEmployee>(employeeDao.findAll());
    }
    
    public List<IEmployee> search(String searchQuery) {
        return new ArrayList<IEmployee>(employeeDao.search(searchQuery));
    }
}
