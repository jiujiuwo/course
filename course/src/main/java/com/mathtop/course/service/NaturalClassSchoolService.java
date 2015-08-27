package com.mathtop.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.NaturalClassDao;
import com.mathtop.course.dao.DepartmentNaturalClassViewDataDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.NaturalClass;
import com.mathtop.course.domain.DepartmentNaturalClassViewData;

@Service
public class NaturalClassSchoolService {
	
	@Autowired
	protected DepartmentNaturalClassViewDataDao naturalclassschooldao;

	@Autowired
	NaturalClassDao naturalclassDao;

	/**
	 * 
	 * 
	 * @param user
	 */
	public void add(String t_school_id,String deptid,String naturalclassname,String naturalclassnote){
		naturalclassschooldao.add(t_school_id, deptid, naturalclassname, naturalclassnote);
	}
	
	
	public void DELETE(String t_natural_class_id){
		naturalclassschooldao.DELETE(t_natural_class_id);
	}
	
	public Page<DepartmentNaturalClassViewData> getPage(String t_school_id,int pageNo, int pageSize) {
		return naturalclassschooldao.getPage(t_school_id,pageNo, pageSize);
	}
	
	public String gett_natural_class_idByNaturalClassName(String naturalclassname){
		NaturalClass n= naturalclassDao.getByName(naturalclassname);
		if(n!=null)
			return n.getId();
		return null;
	};
	
	/**
	 * 
	 * 
	 * @param boardId
	 * @return
	 */
	public List<NaturalClass> getNaturalClassByt_department_id(String t_department_id) {
		return naturalclassschooldao.getNaturalClassByt_department_id(t_department_id);
	}
	
	public Page<NaturalClass> getPageByt_department_id(String t_department_id,int pageNo, int pageSize) {
		return naturalclassschooldao.getPageByt_department_id(t_department_id,pageNo, pageSize);
	}
}
