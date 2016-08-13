package com.mathtop.course.domain;


/**
 * 教学班分组的信息
 * */
public class CourseTeachingClassStudentGroupStatisticsViewData extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6039981333450033892L;

	private int nGroupCount;//小组个数

	public int getnGroupCount() {
		return nGroupCount;
	}

	public void setnGroupCount(int nGroupCount) {
		this.nGroupCount = nGroupCount;
	}
}
