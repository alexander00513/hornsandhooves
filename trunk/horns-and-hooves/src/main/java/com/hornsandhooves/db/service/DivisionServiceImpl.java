/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.db.service;

import com.hornsandhooves.db.dao.DivisionDao;
import com.hornsandhooves.db.entity.DivisionImpl;
import com.hornsandhooves.db.entity.IDivision;
import com.hornsandhooves.web.form.DivisionForm;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

/**
 *
 * @author Alexander
 */
@Service
public class DivisionServiceImpl implements IDivisionService {
    
    @Autowired
    private DivisionDao divisionDao;
    
    public void setDivisionDao(DivisionDao divisionDao) {
		this.divisionDao = divisionDao;
	}

    public boolean createDivision(DivisionForm divisionForm, BindingResult result) {
        if(divisionDao.checkTitle(divisionForm)) {
            result.rejectValue("title", "divisionForm.title.invalidTitle");
            return false;
        } else {
            IDivision division = new DivisionImpl();
            division.setTitle(divisionForm.getTitle());
            divisionDao.create((DivisionImpl) division);
            return true;
        }
    }
    
    public IDivision readDivision(Long id) {
        return divisionDao.read(id);
    }
    
    public boolean updateDivision(DivisionForm divisionForm, BindingResult result) {
        IDivision division = divisionDao.read(divisionForm.getId()); 
        if(division == null) {
            return false;
        } else if(divisionDao.checkTitle(divisionForm)) {
            result.rejectValue("title", "divisionForm.title.invalidTitle");
            return false;
        } else {
            division.setId(divisionForm.getId());
            division.setTitle(divisionForm.getTitle());
            divisionDao.update((DivisionImpl) division);
            return true;
        }
    }
    
    public boolean deleteDivision(Long id) {
        IDivision division = divisionDao.read(id);
        if(division == null) {
            return false;
        } else {
            divisionDao.delete((DivisionImpl) division);
            return true;
        } 
    }
    
    public List<IDivision> findAll() {
        return new ArrayList<IDivision>(divisionDao.findAll());
    }
}
