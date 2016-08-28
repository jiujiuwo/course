package com.mathtop.course.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.dao.DepartmentDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.dao.SchoolDepartmentDao;
import com.mathtop.course.domain.Department;

@Service
public class DepartmentService extends SimpleService<DepartmentDao, Department> {

	@Autowired
	protected DepartmentDao departmentDao;

	@Autowired
	private SchoolDepartmentDao schooldepartmentdao;

	@Autowired
	SchoolService schoolService;

	@Override
	public DepartmentDao getBaseDao() {
		// TODO Auto-generated method stub
		return departmentDao;
	}

	public String add(String t_school_id, String departmentName, String departmentNote) {
		Department department = new Department();
		department.setName(departmentName);
		department.setNote(departmentNote);
		String t_department_id = departmentDao.add(department);
		if (t_department_id == null)
			return null;

		//
		if (schooldepartmentdao.add(t_school_id, t_department_id) != null)
			return t_department_id;
		return null;
	}

	public String add(String t_school_id, String departmentName) {
		// 没有找到，创建默认系部
		Department department = new Department();
		department.setName(departmentName);
		String t_department_id = departmentDao.add(department);
		if (t_department_id == null)
			return null;

		//
		if (schooldepartmentdao.add(t_school_id, t_department_id) != null)
			return t_department_id;
		return null;

	}

	public void delete(String t_school_id, String t_department_id) {
		schooldepartmentdao.DELETE(t_school_id, t_department_id);
	}

	public String getSchoolIdByDepartmentId(String t_department_id) {
		return schooldepartmentdao.getSchoolIdByDepartmentId(t_department_id);
	}

	/**
	 * 得到默认学院下的默认系部
	 */
	public String getDefaultDepartmentIdInDefaultSchoolId() {
		String t_school_id = schoolService.getDefaultSchoolId();
		if (t_school_id == null)
			return null;
		

		// 在默认学院下面查找默认系部
		return getDefaultDepartmentIdInSchoolId(t_school_id);

	}

	/**
	 * 得到指定学院下的默认系部
	 */
	public String getDefaultDepartmentIdInSchoolId(String t_school_id) {

		if (t_school_id == null)
			return null;

		// 在学院下面查找默认系部
		List<Department> list = getAll(t_school_id);
		for (Department d : list) {
			if (d.getName().equals(CommonConstant.DEFAULT_DEPARTMENT_NAME)) {
				return d.getId();
			}
		}
		
		return add(t_school_id,CommonConstant.DEFAULT_DEPARTMENT_NAME);

	}

	/**
	 * 
	 * 
	 * @param t_school_id:学校id
	 * @param pageNo:页码
	 * @param pageSize:页大小
	 * @return
	 */
	public Page<Department> getPage(String t_school_id, int pageNo, int pageSize) {
		List<String> schools = schooldepartmentdao.getDepartmentIdBySchoolId(t_school_id);
		List<Department> data = new ArrayList<Department>();

		if(schools==null)
			return null;
		
		for (String tmp : schools) {
			Department d = departmentDao.getByID(tmp);
			data.add(d);
		}

		return new Page<Department>(0, data.size(), pageSize, data);
	}

	/**
	 * 
	 * 
	 * @param boardId
	 * @return
	 */
	public Page<Department> getPage(String t_school_id) {
		List<String> schools = schooldepartmentdao.getDepartmentIdBySchoolId(t_school_id);
		List<Department> data = new ArrayList<Department>();

		for (String tmp : schools) {
			Department d = departmentDao.getByID(tmp);
			data.add(d);
		}

		return new Page<Department>(0, data.size(), data.size(), data);
	}

	/**
	 * 
	 * 
	 * @param boardId
	 * @return
	 */
	public List<Department> getAll(String t_school_id) {
		List<String> schools = schooldepartmentdao.getDepartmentIdBySchoolId(t_school_id);
		List<Department> data = new ArrayList<Department>();

		for (String tmp : schools) {
			Department d = departmentDao.getByID(tmp);

			data.add(d);
		}

		return data;
	}

	/**
	 * 
	 * */
	public long getDepartmentCount(String t_school_id, String t_department_id) {
		return schooldepartmentdao.getDepartmentCount(t_school_id, t_department_id);
	}

}
