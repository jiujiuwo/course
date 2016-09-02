package com.mathtop.course.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.cons.SelectedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.AttendanceType;
import com.mathtop.course.domain.CourseTeachingClassHomeworkType;
import com.mathtop.course.domain.CourseTeachingClassReferenceType;
import com.mathtop.course.domain.CourseTeachingClassStudentGroupStatisticsViewData;
import com.mathtop.course.domain.CourseTeachingClassViewData;
import com.mathtop.course.service.AttendanceTypeService;
import com.mathtop.course.service.CourseTeachingClassHomeworkTypeService;
import com.mathtop.course.service.CourseTeachingClassReferenceTypeService;
import com.mathtop.course.service.CourseTeachingClassService;
import com.mathtop.course.service.CourseTeachingClassStudentGroupService;

public class CourseTeachingClassBaseController extends BaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	CourseTeachingClassService teachingclassService;

	@Autowired
	CourseTeachingClassHomeworkTypeService homeworkTypeService;

	@Autowired
	private AttendanceTypeService attendancetypeService;

	@Autowired
	private CourseTeachingClassReferenceTypeService referencektypeService;

	@Autowired
	CourseTeachingClassStudentGroupService courseTeachingClassStudentGroupService;
	
	

	// 课程信息
	protected void SetCourseTeachingClassBaseInfo(ModelAndView mav, String t_course_teaching_class_id) {

		// 设置页面对象
		mav.addObject(SelectedObjectConst.Selected_CourseTeachingClassID, t_course_teaching_class_id);

		// 课程信息
		SetCourseTeachingClassInfo(mav, t_course_teaching_class_id);

		// 作业类型
		SetHomeworkInfo(mav, t_course_teaching_class_id);

		// 资料类型列表
		SetReferenceInfo(mav, t_course_teaching_class_id);

		// 课程-考勤类型
		SetAttendance(mav, t_course_teaching_class_id);
	}

	// 课程信息
	private void SetCourseTeachingClassInfo(ModelAndView mav, String t_course_teaching_class_id) {

		mav.addObject(SelectedObjectConst.Selected_CourseTeachingClassID, t_course_teaching_class_id);

		// 课程授课等信息
		CourseTeachingClassViewData selectedCourseTeachingClassViewData = teachingclassService
				.GetTeachingClassViewDataByCourseTeachingClassId(t_course_teaching_class_id);

		mav.addObject(SelectedObjectConst.Selected_CourseTeachingClassViewData, selectedCourseTeachingClassViewData);

		// 教学班分组信息
		CourseTeachingClassStudentGroupStatisticsViewData selectedCourseTeachingClassStudentGroupStatisticsViewData = courseTeachingClassStudentGroupService
				.getGroupInfo(t_course_teaching_class_id);
		mav.addObject(SelectedObjectConst.Selected_CourseTeachingClassStudentGroupStatisticsViewData,
				selectedCourseTeachingClassStudentGroupStatisticsViewData);

		SetHomeworkInfo(mav, t_course_teaching_class_id);
	}

	// 课程作业类型
	private void SetHomeworkInfo(ModelAndView mav, String t_course_teaching_class_id) {
		Page<CourseTeachingClassHomeworkType> pagedHomeworkTypeData = homeworkTypeService
				.getPage(t_course_teaching_class_id);
		mav.addObject(PagedObjectConst.Paged_CourseHomeworkTypeData, pagedHomeworkTypeData);
	}

	// 资料类型列表
	private void SetReferenceInfo(ModelAndView mav, String t_course_teaching_class_id) {
		Page<CourseTeachingClassReferenceType> pagedReferenceTypeData = referencektypeService
				.getPage(t_course_teaching_class_id);
		mav.addObject(PagedObjectConst.Paged_CourseReferenceTypeData, pagedReferenceTypeData);
	}

	// 课程-考勤类型
	private void SetAttendance(ModelAndView mav, String t_course_teaching_class_id) {
		Page<AttendanceType> pagedAttendanceTypeData = attendancetypeService.getPage(t_course_teaching_class_id);
		mav.addObject(PagedObjectConst.Paged_CourseAttendanceTypeData, pagedAttendanceTypeData);
	}

	
}
