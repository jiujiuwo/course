package com.mathtop.course.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mathtop.course.cons.CourseMessage;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.cons.SelectedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.AttendanceMode;
import com.mathtop.course.domain.AttendanceSpecificStatistics;
import com.mathtop.course.domain.AttendanceState;
import com.mathtop.course.domain.AttendanceStateModeViewData;
import com.mathtop.course.domain.AttendanceStudentViewData;
import com.mathtop.course.domain.AttendanceType;
import com.mathtop.course.domain.AttendanceViewData;
import com.mathtop.course.domain.CourseTeachingClassViewData;
import com.mathtop.course.domain.Student;
import com.mathtop.course.domain.StudentViewData;
import com.mathtop.course.domain.Teacher;
import com.mathtop.course.domain.UserSessionInfo;
import com.mathtop.course.service.AttendanceModeService;
import com.mathtop.course.service.AttendanceService;
import com.mathtop.course.service.AttendanceStateService;
import com.mathtop.course.service.AttendanceStudentService;
import com.mathtop.course.service.AttendanceTypeService;
import com.mathtop.course.service.CourseTeachingClassService;
import com.mathtop.course.service.StudentService;
import com.mathtop.course.service.chartDataValue;
import com.mathtop.course.utility.DateTimeSql;

@Controller
@RequestMapping("/courseattendance")
public class AttendanceController extends CourseTeachingClassBaseController {

	/**
	 * 自动注入
	 */

	@Autowired
	private AttendanceTypeService attendancetypeService;

	@Autowired
	private AttendanceModeService attendancemodeService;

	@Autowired
	private AttendanceStateService attendancestateService;

	@Autowired
	private AttendanceService attendanceService;

	@Autowired
	AttendanceStudentService attendancestudentService;

	@Autowired
	CourseTeachingClassService teachingclassService;

	@Autowired
	private StudentService studentService;

	@Autowired
	CourseTeachingClassService courseTeachingClassService;

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add-{t_course_teaching_class_id}-{t_attendance_type_id}")
	public ModelAndView add(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_attendance_type_id) {

		String begin_datetime = request.getParameter("begin_datetime");
		String end_datetime = request.getParameter("end_datetime");

		ModelAndView mav = new ModelAndView();

		if (DateTimeSql.GetDateTimeNotIncludingSecond(begin_datetime).getTime() > DateTimeSql
				.GetDateTimeNotIncludingSecond(end_datetime).getTime()) {
			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "结束时间不能小于开始时间，请修改.");

			mav.setViewName("redirect:/courseattendance/list-" + t_course_teaching_class_id + "-" + t_attendance_type_id
					+ ".html");
			return mav;
		}

		UserSessionInfo userinfo = getSessionUser(request);

		if (userinfo != null) {
			Teacher teacher = userinfo.getTeacher();
			if (teacher != null) {
				attendanceService.add(t_course_teaching_class_id, t_attendance_type_id, teacher.getId(), begin_datetime,
						end_datetime);
			}
		}

		mav.setViewName(
				"redirect:/courseattendance/list-" + t_course_teaching_class_id + "-" + t_attendance_type_id + ".html");

		return mav;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/DELETE-{t_course_teaching_class_id}-{t_attendance_type_id}-{t_attendance_id}", method = RequestMethod.GET)
	public ModelAndView DELETE(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_attendance_type_id, @PathVariable String t_attendance_id) {
		if (t_attendance_id != null)
			attendancestudentService.deleteByAttendanceId(t_attendance_id);
		{
			attendanceService.deleteById(t_attendance_id);
		}

		ModelAndView mav = new ModelAndView();

		mav.setViewName(
				"redirect:/courseattendance/list-" + t_course_teaching_class_id + "-" + t_attendance_type_id + ".html");

		return mav;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update-{t_course_teaching_class_id}-{t_attendance_type_id}")
	public ModelAndView update(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_attendance_type_id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("courseattendance/list");

		String updateattendanceid = request.getParameter("updateattendanceid");
		String update_begin_datetime = request.getParameter("update_begin_datetime");
		String update_end_datetime = request.getParameter("update_end_datetime");

		if (DateTimeSql.GetDateTimeNotIncludingSecond(update_begin_datetime).getTime() > DateTimeSql
				.GetDateTimeNotIncludingSecond(update_end_datetime).getTime()) {
			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "结束时间不能小于开始时间，请修改.");

			mav.setViewName("redirect:/courseattendance/list-" + t_course_teaching_class_id + "-" + t_attendance_type_id
					+ ".html");
			return mav;
		}

		String t_teacher_id = null;

		UserSessionInfo userinfo = getSessionUser(request);
		if (userinfo != null) {
			Teacher teacher = userinfo.getTeacher();
			if (teacher != null)
				t_teacher_id = teacher.getId();
		}

		if (t_teacher_id != null)
			attendanceService.update(updateattendanceid, t_teacher_id,
					DateTimeSql.GetDateTimeNotIncludingSecond(update_begin_datetime),
					DateTimeSql.GetDateTimeNotIncludingSecond(update_end_datetime));

		mav.setViewName(
				"redirect:/courseattendance/list-" + t_course_teaching_class_id + "-" + t_attendance_type_id + ".html");

		return mav;

	}

	/**
	 * 增加考勤
	 */
	@RequestMapping(value = "/addstudentattendance-{t_course_teaching_class_id}-{t_attendance_type_id}-{t_attendance_id}")
	public ModelAndView addstudentattendance(HttpServletRequest request,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_attendance_type_id,
			@PathVariable String t_attendance_id) {
		ModelAndView mav = new ModelAndView();

		// 学生信息
		Page<StudentViewData> pagedStudentViewData = studentService
				.getPageByCourseTeachingClassId(t_course_teaching_class_id);
		for (StudentViewData s : pagedStudentViewData.getResult()) {
			String t_student_id = s.getStudent().getId();
			String a_ttendance_state_id = request.getParameter("stateoption" + t_student_id);

			String a_ttendance_mode_id = request.getParameter("modeoption" + t_student_id);

			attendancestudentService.deleteByAttendanceIdStudentId(t_attendance_id, t_student_id);
			attendancestudentService.add(t_attendance_id, t_student_id, a_ttendance_state_id, a_ttendance_mode_id,
					new Date());

		}

		mav.setViewName(
				"redirect:/courseattendance/list-" + t_course_teaching_class_id + "-" + t_attendance_type_id + ".html");
		return mav;
	}

	/**
	 * 列出某次考勤的全部学生考勤情况
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/studentattendance-{t_course_teaching_class_id}-{t_attendance_type_id}-{t_attendance_id}")
	public ModelAndView studentattendance(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_attendance_type_id, @PathVariable String t_attendance_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		ModelAndView mav = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = getPageSize(request, pageSize);

		Page<AttendanceStudentViewData> pagedAttendanceStudentViewData = attendancestudentService
				.getPage(t_attendance_id);
		mav.addObject(PagedObjectConst.Paged_CourseAttendanceStudentViewData, pagedAttendanceStudentViewData);

		// 学生信息
		Page<StudentViewData> pagedStudentViewData = studentService
				.getPageByCourseTeachingClassId(t_course_teaching_class_id);

		mav.addObject(PagedObjectConst.Paged_StudentViewData, pagedStudentViewData);

		// 签到方式
		Page<AttendanceMode> pagedAttendanceMode = attendancemodeService.getAllPage();

		mav.addObject(PagedObjectConst.Paged_AttendanceMode, pagedAttendanceMode);

		// 签到情况
		Page<AttendanceState> pagedAttendanceState = attendancestateService.getAllPage();

		mav.addObject(PagedObjectConst.Paged_AttendanceState, pagedAttendanceState);

		mav.addObject(SelectedObjectConst.Selected_CourseAttendanceID, t_attendance_id);

		// 签到信息
		AttendanceViewData selectedCourseAttendanceViewData = attendanceService
				.getAttendanceViewDataByAttendanceId(t_attendance_id);
		mav.addObject(SelectedObjectConst.Selected_CourseAttendanceViewData, selectedCourseAttendanceViewData);

		// 课程-考勤类型
		AttendanceType selectedCourseAttendanceTypeData = attendancetypeService.getByID(t_attendance_type_id);
		mav.addObject(SelectedObjectConst.Selected_CourseAttendanceTypeData, selectedCourseAttendanceTypeData);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(mav, t_course_teaching_class_id);

		mav.setViewName("courseattendance/attendance");
		return mav;
	}

	/**
	 * 列出某次考勤的全部学生的考勤统计情况
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/statistics-{t_course_teaching_class_id}-{t_attendance_type_id}-{t_attendance_id}")
	public ModelAndView statistics(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_attendance_type_id, @PathVariable String t_attendance_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		ModelAndView mav = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = getPageSize(request, pageSize);

		Page<AttendanceStudentViewData> pagedAttendanceStudentViewData = attendancestudentService
				.getPage(t_attendance_id);
		mav.addObject(PagedObjectConst.Paged_CourseAttendanceStudentViewData, pagedAttendanceStudentViewData);

		// 本次签到统计
		List<AttendanceSpecificStatistics> SelectedAttendanceSpecificStatistics = attendancestudentService
				.getAttendanceSpecificStatistics(t_attendance_id);
		mav.addObject(SelectedObjectConst.Selected_AttendanceSpecificStatistics, SelectedAttendanceSpecificStatistics);

		// 签到ID
		mav.addObject(SelectedObjectConst.Selected_CourseAttendanceID, t_attendance_id);

		// 签到信息
		AttendanceViewData selectedCourseAttendanceViewData = attendanceService
				.getAttendanceViewDataByAttendanceId(t_attendance_id);
		mav.addObject(SelectedObjectConst.Selected_CourseAttendanceViewData, selectedCourseAttendanceViewData);

		// 课程-考勤类型
		AttendanceType selectedCourseAttendanceTypeData = attendancetypeService.getByID(t_attendance_type_id);
		mav.addObject(SelectedObjectConst.Selected_CourseAttendanceTypeData, selectedCourseAttendanceTypeData);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(mav, t_course_teaching_class_id);

		mav.setViewName("courseattendance/singlestudentstatistics");
		return mav;
	}

	@RequestMapping(value = "/getChartDataValueofAttendanceSpecificStatistics-{t_attendance_id}", method = RequestMethod.GET)
	@ResponseBody
	public List<chartDataValue> getChartDataValueofAttendanceSpecificStatistics(HttpServletRequest request,
			@PathVariable String t_attendance_id) {

		return attendancestudentService.getChartDataValueofAttendanceSpecificStatistics(t_attendance_id);

	}

	/**
	 * 列出单个学生全部考勤情况
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/singlestudentattendance-{t_course_teaching_class_id}-{t_attendance_type_id}-{t_student_id}")
	public ModelAndView singlestudentattendance(HttpServletRequest request,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_attendance_type_id,
			@PathVariable String t_student_id, @RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		ModelAndView mav = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = getPageSize(request, pageSize);

		Page<AttendanceStateModeViewData> pagedAttendanceStateModeViewData = attendancestudentService
				.getPage(t_course_teaching_class_id, t_attendance_type_id, t_student_id, pageNo, pageSize);
		mav.addObject(PagedObjectConst.Paged_CourseAttendanceStateModeViewData, pagedAttendanceStateModeViewData);

		mav.addObject(SelectedObjectConst.Selected_Student_ID, t_student_id);

		// 课程-考勤类型
		AttendanceType selectedCourseAttendanceTypeData = attendancetypeService.getByID(t_attendance_type_id);
		mav.addObject(SelectedObjectConst.Selected_CourseAttendanceTypeData, selectedCourseAttendanceTypeData);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(mav, t_course_teaching_class_id);

		mav.setViewName("courseattendance/studentshowlist");
		return mav;
	}

	/**
	 * 列出全部
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/list-{t_course_teaching_class_id}-{t_attendance_type_id}")
	public ModelAndView ListAll(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_attendance_type_id, @RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		ModelAndView view = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = getPageSize(request, pageSize);

		Page<AttendanceViewData> pagedAttendanceViewData = attendanceService.getPage(t_course_teaching_class_id,
				t_attendance_type_id, pageNo, pageSize);
		view.addObject(PagedObjectConst.Paged_CourseAttendanceViewData, pagedAttendanceViewData);

		// 课程-考勤类型
		AttendanceType selectedCourseAttendanceTypeData = attendancetypeService.getByID(t_attendance_type_id);
		view.addObject(SelectedObjectConst.Selected_CourseAttendanceTypeData, selectedCourseAttendanceTypeData);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		UserSessionInfo userinfo = getSessionUser(request);
		if (userinfo != null) {
			Teacher teacher = userinfo.getTeacher();
			if (teacher != null)
				view.setViewName("courseattendance/list");
			else {
				Student student = userinfo.getStudent();
				if (student != null)
					view.setViewName("redirect:/courseattendance/singlestudentattendance-" + t_course_teaching_class_id
							+ "-" + t_attendance_type_id + "-" + student.getId() + ".html");
			}
		} else
			view.setViewName("courseattendance/list");
		return view;
	}

	/**
	 * 根据教学班列出学生
	 * 
	 * @param t_course_teaching_class_id:课程-教学班-id
	 * @param pageNo
	 *            页码
	 * @return
	 */
	@RequestMapping(value = "/student-{t_course_teaching_class_id}", method = RequestMethod.GET)
	public ModelAndView student(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {

		ModelAndView mav = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = getPageSize(request, pageSize);

		CourseTeachingClassViewData selected_CourseTeachingClassViewData = courseTeachingClassService
				.GetTeachingClassViewDataByCourseTeachingClassId(t_course_teaching_class_id);

		mav.addObject(SelectedObjectConst.Selected_CourseTeachingClassID, t_course_teaching_class_id);

		mav.addObject(SelectedObjectConst.Selected_CourseTeachingClassViewData, selected_CourseTeachingClassViewData);

		// 学生信息
		Page<StudentViewData> pagedStudentViewData = studentService
				.getPageByCourseTeachingClassId(t_course_teaching_class_id, pageNo, pageSize);

		mav.addObject(PagedObjectConst.Paged_StudentViewData, pagedStudentViewData);

		mav.setViewName("courseattendance/student");

		return mav;
	}

}
