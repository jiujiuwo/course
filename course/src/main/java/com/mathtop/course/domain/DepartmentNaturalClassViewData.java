package com.mathtop.course.domain;


public class DepartmentNaturalClassViewData  extends BaseDomain{

	


	/**
	 * 
	 */
	private static final long serialVersionUID = -1682374614815934739L;

	private DepartmentNaturalClass departmentNaturalClass;
	private School school;
	private Department department;
	private NaturalClass naturalclass;
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public NaturalClass getNaturalclass() {
		return naturalclass;
	}
	public void setNaturalclass(NaturalClass naturalclass) {
		this.naturalclass = naturalclass;
	}
	public DepartmentNaturalClass getDepartmentNaturalClass() {
		return departmentNaturalClass;
	}
	public void setDepartmentNaturalClass(DepartmentNaturalClass departmentNaturalClass) {
		this.departmentNaturalClass = departmentNaturalClass;
	}
}
