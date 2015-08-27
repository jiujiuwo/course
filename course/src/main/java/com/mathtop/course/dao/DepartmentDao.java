package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.Department;

@Repository
public class DepartmentDao extends SimpleDao<Department> {
	
	DepartmentDao()	{
		super(Department.class,"t_department");
	}
	
	
}
