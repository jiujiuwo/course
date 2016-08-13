package com.mathtop.course.domain;


/**
 * 教学班每个分组的详细信息，包括该小组信息、小组学生信息等
 * */
public class CourseTeachingClassStudentGroupViewData extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1170646854209713861L;

	private CourseTeachingClassStudentGroup courseTeachingClassStudentGroup;
	private Group group;
	private StudentViewData[] studentViewDatas;

	public CourseTeachingClassStudentGroup getCourseTeachingClassStudentGroup() {
		return courseTeachingClassStudentGroup;
	}

	public void setCourseTeachingClassStudentGroup(CourseTeachingClassStudentGroup courseTeachingClassStudentGroup) {
		this.courseTeachingClassStudentGroup = courseTeachingClassStudentGroup;
	}

	public StudentViewData[] getStudentViewDatas() {
		return studentViewDatas;
	}

	public void setStudentViewDatas(StudentViewData[] studentViewDatas) {
		this.studentViewDatas = studentViewDatas;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
}
