package com.mathtop.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.dao.DepartmentNaturalClassDao;
import com.mathtop.course.dao.NaturalClassDao;
import com.mathtop.course.domain.Department;
import com.mathtop.course.domain.NaturalClass;
import com.mathtop.course.domain.School;

@Service
public class NaturalClassService extends SimpleService<NaturalClassDao, NaturalClass> {

	@Autowired
	protected NaturalClassDao naturalClassDao;

	@Autowired
	DepartmentNaturalClassDao departmentNaturalClassDao;

	@Autowired
	DepartmentService departmentService;

	@Autowired
	SchoolService schoolService;

	@Override
	public NaturalClassDao getBaseDao() {
		// TODO Auto-generated method stub
		return naturalClassDao;
	}

	/**
	 * 在指定学院增加班级
	 */
	public String Add(String t_department_id, String strNaturalClassName) {
		NaturalClass n = new NaturalClass();
		n.setName(strNaturalClassName);

		String t_natural_class_id = naturalClassDao.add(n);
		
		System.out.println("t_department_id:"+t_department_id);
		System.out.println("t_natural_class_id:"+t_natural_class_id);
		
		if (t_natural_class_id != null) {
			if (departmentNaturalClassDao.add(t_department_id, t_natural_class_id) == null)
				return null;
		}
		
		
		return t_natural_class_id;
	}

	/**
	 * 在指定学院增加班级
	 */
	public String Add(String t_department_id, String strNaturalClassName, String strNaturalClassNote) {
		NaturalClass n = new NaturalClass();
		n.setName(strNaturalClassName);
		n.setNote(strNaturalClassNote);

		String t_natural_class_id = naturalClassDao.add(n);
		if (t_natural_class_id != null) {
			if (departmentNaturalClassDao.add(t_department_id, t_natural_class_id) == null)
				return null;
		}
		return t_natural_class_id;
	}

	/**
	 * 在默认学院默认系部中增加班级
	 */

	public String Add2DefaultSchoolAndDefaultDepartment(String strNaturalClassName, String strNaturalClassNote) {
		String t_department_id = departmentService.getDefaultDepartmentIdInDefaultSchoolId();
		return Add(t_department_id, strNaturalClassName, strNaturalClassNote);
	}

	/**
	 * 在默认学院默认系部中增加班级
	 */

	public String Add2DefaultSchoolAndDefaultDepartment(String strNaturalClassName) {
		String t_department_id = departmentService.getDefaultDepartmentIdInDefaultSchoolId();
		
		return Add(t_department_id, strNaturalClassName);
	}

	/**
	 * 在学院默认系部中增加班级
	 */

	public String Add2SchoolDefaultDepartment(String t_school_id, String strNaturalClassName) {
		String t_department_id = departmentService.getDefaultDepartmentIdInSchoolId(t_school_id);
		return Add(t_department_id, strNaturalClassName);
	}

	/**
	 * 得到默认学院默认系部下的班级,如果没有则创建该班级
	 */
	public String getDefaultNaturalClassId(String strNaturalClassName) {

		
		String t_department_id = departmentService.getDefaultDepartmentIdInDefaultSchoolId();
		if(t_department_id==null)
			return null;
		System.out.println("t_department_id B :"+t_department_id);

		List<NaturalClass> list = departmentNaturalClassDao.getNaturalClassByDepartmentId(t_department_id);

		if (list != null) {
			for (NaturalClass n : list) {
				if (n.equals(strNaturalClassName))
				
					if (departmentNaturalClassDao.getIdByDepartmentNaturalclassId(t_department_id, n.getId()) == null)
						return null;
					else
						return n.getId();

			}
		}

		
		return Add2DefaultSchoolAndDefaultDepartment(strNaturalClassName);
	}

	/**
	 * 得到指定系部下的班级,如果没有则创建该班级
	 */
	public String getDefaultNaturalClassIdByDepartmentId(String t_department_id, String strNaturalClassName) {
		if (t_department_id == null)
			return null;

		List<NaturalClass> list = departmentNaturalClassDao.getNaturalClassByDepartmentId(t_department_id);

		if (list != null) {
			for (NaturalClass n : list) {
				if (n.equals(strNaturalClassName))
					if (departmentNaturalClassDao.getIdByDepartmentNaturalclassId(t_department_id, n.getId()) == null)
						return null;
					else
						return n.getId();

			}
		}

		return Add(t_department_id, strNaturalClassName);
	}

	/**
	 * 得到指定系学院的班级,如果没有则创建该班级
	 */
	public String getDefaultNaturalClassIdBySchoolId(String t_school_id, String strNaturalClassName) {
		if (t_school_id == null)
			return null;

		List<NaturalClass> list = departmentNaturalClassDao.getNaturalClassBySchoolId(t_school_id);

		if (list != null) {
			for (NaturalClass n : list) {
				if (n.equals(strNaturalClassName))
					return n.getId();
			}
		}

		return Add2SchoolDefaultDepartment(t_school_id, strNaturalClassName);
	}

	/**
	 * 得到指定系部下的默认班级
	 */
	public String getDefaultNaturalClassIdByDepartmentId(String t_department_id) {

		if (t_department_id == null)
			return null;

		List<NaturalClass> list = departmentNaturalClassDao.getNaturalClassByDepartmentId(t_department_id);

		if (list != null) {
			for (NaturalClass n : list) {
				if (n.equals(CommonConstant.DEFAULT_NATURALCLASS_NAME))
					if (departmentNaturalClassDao.getIdByDepartmentNaturalclassId(t_department_id, n.getId()) == null)
						return null;
					else
						return n.getId();

			}
		}

		return Add(t_department_id, CommonConstant.DEFAULT_NATURALCLASS_NAME);

	}

	/**
	 * 得到默认学院下的默认系部下的默认班级
	 */
	public String getDefaultNaturalClassId() {
		String t_department_id = departmentService.getDefaultDepartmentIdInDefaultSchoolId();
		return getDefaultNaturalClassIdByDepartmentId(t_department_id);
	}

	/**
	 * 得到指定学院下的默认系部下的默认班级
	 */
	public String getDefaultNaturalClassIdBySchoolId(String t_school_id) {
		if (schoolService.getByID(t_school_id) == null)
			return null;

		String t_department_id = departmentService.getDefaultDepartmentIdInSchoolId(t_school_id);

		return getDefaultNaturalClassIdByDepartmentId(t_department_id);

	}

	/**
	 * 得到指定学院下的指定系部下的默认班级
	 */
	public String getDefaultNaturalClassIdBySchoolIdAndDepartmentId(String t_school_id, String t_department_id) {

		// 系部位于学院下
		if (!t_school_id.equals(departmentService.getSchoolIdByDepartmentId(t_department_id)))
			return null;

		return getDefaultNaturalClassIdByDepartmentId(t_department_id);

	}

	/**
	 * 得到指定学院下的指定系部下的默认班级
	 */
	public String getDefaultNaturalClassIdBySchoolNameAndDepartmentName(String school_name, String department_name) {

		School s = schoolService.getByName(school_name);
		if (s == null)
			return null;

		List<Department> list = departmentService.getAll(s.getId());
		if (list == null)
			return null;

		for (Department d : list) {
			if (d.getName().equals(department_name)) {
				return getDefaultNaturalClassIdBySchoolIdAndDepartmentId(s.getId(), d.getId());
			}
		}

		return null;

	}

	/**
	 * 得到指定学院下的指定系部下的指定班级
	 */
	public String getDefaultNaturalClassIdBySchoolNameAndDepartmentNameAndNaturallClassName(String school_name,
			String department_name,String natural_class_name) {

		School s = schoolService.getByName(school_name);
		if (s == null)
			return null;

		List<Department> list = departmentService.getAll(s.getId());
		if (list == null)
			return null;

		for (Department d : list) {
			if (d.getName().equals(department_name)) {
				return departmentNaturalClassDao.getIdByDepartmentIdNaturalclassName(d.getId(),natural_class_name);
			}
		}

		return null;

	}
	
	
	/**
	 * 添加学生
	 */
	public String getNaturalClassId(String schoolName, String departmentName, String naturalclassname) {

		
		
		if (schoolName !=null && schoolName.trim().length() == 0)
			schoolName = null;

		if (departmentName !=null &&  departmentName.trim().length() == 0)
			departmentName = null;

		if (naturalclassname !=null &&  naturalclassname.trim().length() == 0)
			naturalclassname = null;

		String t_natural_class_id = null;
		String t_department_id = null;
		String t_school_id = null;

		if (schoolName == null && departmentName == null && naturalclassname == null) {
			// 000
			// 放到默认学院的默认系部的默认班级中
			t_natural_class_id = getDefaultNaturalClassId();

		} else if (schoolName == null && departmentName == null && naturalclassname != null) {
			// 001
			
			// 查找班级，如果存在，则放到该班级中
			NaturalClass naturalclass = getByName(naturalclassname);
			if (naturalclass != null){
				
				t_natural_class_id = naturalclass.getId();
			}
			else {
				
				// 不存在班级，则放到默认学院的默认系部的指定班级中
				t_natural_class_id = getDefaultNaturalClassId(naturalclassname);

			}
		} else if (schoolName == null && departmentName != null && naturalclassname == null) {
			// 010
			// 放到默认学院的默认系部的默认班级中
			Department d = departmentService.getByName(departmentName);
			if (d != null) {
				t_department_id = d.getId();
				t_natural_class_id = getDefaultNaturalClassIdByDepartmentId(t_department_id);
			}

		} else if (schoolName == null && departmentName != null && naturalclassname != null) {
			// 011
			// 放到默认学院的指定系部的指定班级中
			Department d = departmentService.getByName(departmentName);
			if (d != null) {
				t_department_id = d.getId();
				t_natural_class_id = getDefaultNaturalClassIdByDepartmentId(t_department_id,
						naturalclassname);
			}

		} else if (schoolName != null && departmentName == null && naturalclassname == null) {
			// 100
			// 放到指定学院的默认系部的默认班级中
			School s = schoolService.getByName(schoolName);
			if (s != null) {
				t_school_id = s.getId();
				t_natural_class_id = getDefaultNaturalClassIdBySchoolId(t_school_id);
			}

		} else if (schoolName != null && departmentName == null && naturalclassname != null) {
			// 101
			// 放到指定学院的默认系部的指定班级中
			School s = schoolService.getByName(schoolName);
			if (s != null) {
				t_school_id = s.getId();
				t_natural_class_id = getDefaultNaturalClassIdBySchoolId(t_school_id,
						naturalclassname);
			}

		} else if (schoolName != null && departmentName != null && naturalclassname == null) {
			// 110
			// 放到指定学院的指定系部的默认班级中

			t_natural_class_id = getDefaultNaturalClassIdBySchoolNameAndDepartmentName(schoolName,
					departmentName);
		} else if (schoolName != null && departmentName != null && naturalclassname != null) {
			// 111
			// 放到指定学院的指定系部的指定班级中
			t_natural_class_id = getDefaultNaturalClassIdBySchoolNameAndDepartmentNameAndNaturallClassName(schoolName,
							departmentName, naturalclassname);
		}
		
		return t_natural_class_id;
	}


}
