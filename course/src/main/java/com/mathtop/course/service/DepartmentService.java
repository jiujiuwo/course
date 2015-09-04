package com.mathtop.course.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.DepartmentDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.dao.SchoolDepartmentDao;
import com.mathtop.course.domain.Department;

@Service
public class DepartmentService extends SimpleService<DepartmentDao,Department> {
	
	@Autowired
	protected DepartmentDao dao;
	
	@Override
	public DepartmentDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	@Autowired
	private SchoolDepartmentDao schooldepartmentdao;
	
	public void Add(String t_school_id,String departmentName,String departmentNote){
		String t_department_id=Add(departmentName, departmentNote);
		
		schooldepartmentdao.add(t_school_id, t_department_id);
	}
	
	public void  DELETE(String t_school_id,String t_department_id){
		schooldepartmentdao.DELETE(t_school_id, t_department_id);
	}
	
	public String getSchoolIdByDepartmentId(String t_department_id){
		return schooldepartmentdao.gett_school_idByt_department_id(t_department_id);
	}
	
	
	
	/**
	 * 
	 * 
	 * @param boardId
	 * @return
	 */
	public Page<Department> getPage(String t_school_id,int pageNo, int pageSize) {
		List<String> schools=schooldepartmentdao.get_department_idByt_school_id(t_school_id);
		List<Department> data=new  ArrayList<Department>();
		
		for(String tmp:schools){			
			Department d=dao.getByID(tmp);
			data.add(d);	
		}
	
		return new Page<Department>(0, data.size(), pageSize,data );
	}
	
	/**
	 * 
	 * 
	 * @param boardId
	 * @return
	 */
	public Page<Department> getPage(String t_school_id) {
		List<String> schools=schooldepartmentdao.get_department_idByt_school_id(t_school_id);
		List<Department> data=new  ArrayList<Department>();
		
		for(String tmp:schools){			
			Department d=dao.getByID(tmp);
			data.add(d);	
		}
	
		return new Page<Department>(0, data.size(), data.size(),data );
	}
	
	/**
	 * 
	 * 
	 * @param boardId
	 * @return
	 */
	public List<Department> getAll(String t_school_id) {
		List<String> schools=schooldepartmentdao.get_department_idByt_school_id(t_school_id);
		List<Department> data=new  ArrayList<Department>();
		
		for(String tmp:schools){			
			Department d=dao.getByID(tmp);
		
			data.add(d);	
		}
	
		return data;
	}
	
	/**
	 * 
	 * */
	public long getDepartmentCount(String t_school_id,String t_department_id){
		return schooldepartmentdao.getDepartmentCount(t_school_id,t_department_id);
	}

	
    
}
