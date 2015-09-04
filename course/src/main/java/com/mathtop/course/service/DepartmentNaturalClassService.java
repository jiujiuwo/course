package com.mathtop.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.DepartmentNaturalClassDao;
import com.mathtop.course.dao.NaturalClassDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.DepartmentNaturalClassViewData;
import com.mathtop.course.domain.NaturalClass;

@Service
public class DepartmentNaturalClassService {
	
	@Autowired
	protected DepartmentNaturalClassDao departmentNaturalClassDao;

	@Autowired
	NaturalClassDao naturalclassDao;

	/**
	 * 
	 * 
	 * @param user
	 */
	public void add(String t_department_id,String naturalclassname,String naturalclassnote){
		String t_natural_class_id=naturalclassDao.add(naturalclassname, naturalclassnote);
		departmentNaturalClassDao.add(t_department_id, t_natural_class_id);
	}
	
	public void delete(String t_natural_class_id){
		
	}
	
	
	
	public Page<DepartmentNaturalClassViewData> getPage(String t_department_id,int pageNo, int pageSize) {
		return departmentNaturalClassDao.getPage(t_department_id,pageNo, pageSize);
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
		return departmentNaturalClassDao.getNaturalClassByt_department_id(t_department_id);
	}
	
	public Page<NaturalClass> getNaturalClassPage(String t_department_id,int pageNo, int pageSize) {
		return departmentNaturalClassDao.getNaturalClassPage(t_department_id,pageNo,pageSize);
	}
	
}
