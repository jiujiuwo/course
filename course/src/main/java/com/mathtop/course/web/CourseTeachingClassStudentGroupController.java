package com.mathtop.course.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.mathtop.course.cons.ListObjectConst;
import com.mathtop.course.cons.SelectedObjectConst;
import com.mathtop.course.domain.CourseTeachingClassStudentGroupViewData;
import com.mathtop.course.domain.Student;
import com.mathtop.course.domain.StudentViewData;
import com.mathtop.course.domain.UserSessionInfo;
import com.mathtop.course.service.CourseTeachingClassStudentGroupService;
import com.mathtop.course.service.GroupService;

@Controller
@RequestMapping("/coursegroup")
public class CourseTeachingClassStudentGroupController extends CourseTeachingClassBaseController {

	@Autowired
	CourseTeachingClassStudentGroupService courseTeachingClassStudentGroupService;

	@Autowired
	GroupService groupService;

	/**
	 * 列出指定学院、系部、班级的学生
	 * 
	 * @param t_school_id
	 *            ：学院
	 * @param t_department_id
	 *            ：系部
	 * @param t_natural_class_id
	 *            ：自然班
	 * @param pageNo
	 *            ：分页
	 * @return
	 */
	@RequestMapping(value = "/list-{t_course_teaching_class_id}")
	public ModelAndView list(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session,
			@PathVariable String t_course_teaching_class_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {

		ModelAndView mav = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = getPageSize(request, pageSize);
		

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(mav, t_course_teaching_class_id);
		
		List<CourseTeachingClassStudentGroupViewData> lstCourseTeachingClassStudentGroupViewData = courseTeachingClassStudentGroupService
				.getViewData(t_course_teaching_class_id);
		mav.addObject(ListObjectConst.List_CourseTeachingClassStudentGroupViewData,
				lstCourseTeachingClassStudentGroupViewData);

		mav.setViewName("coursegroup/list");

		return mav;
	}

	
	@RequestMapping(value = "/studentlist-{t_course_teaching_class_id}")
	public ModelAndView studentlist(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session,
			@PathVariable String t_course_teaching_class_id) {

		ModelAndView mav = new ModelAndView();

		UserSessionInfo userinfo = getSessionUser(request);
		String t_student_id = null;

		if (userinfo != null) {
			Student student = userinfo.getStudent();
			if (student != null) {
				t_student_id = student.getId();
			}
		}


		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(mav, t_course_teaching_class_id);
		
		if(t_student_id!=null){
		CourseTeachingClassStudentGroupViewData selectedCourseTeachingClassStudentGroupViewData = courseTeachingClassStudentGroupService
				.getViewDataByStudentId(t_course_teaching_class_id,t_student_id);
		mav.addObject(SelectedObjectConst.Selected_CourseTeachingClassStudentGroupViewData,
				selectedCourseTeachingClassStudentGroupViewData);
		}

		mav.setViewName("coursegroup/studentlist");

		return mav;
	}
	
	@RequestMapping(value = "/manualgroup-{t_course_teaching_class_id}")
	public ModelAndView manualgroup(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id) {

		ModelAndView mav = new ModelAndView();

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(mav, t_course_teaching_class_id);

		List<CourseTeachingClassStudentGroupViewData> lstCourseTeachingClassStudentGroupViewData = courseTeachingClassStudentGroupService
				.getViewData(t_course_teaching_class_id);
		mav.addObject(ListObjectConst.List_CourseTeachingClassStudentGroupViewData,
				lstCourseTeachingClassStudentGroupViewData);

		mav.setViewName("coursegroup/manual");

		return mav;
	}

	@RequestMapping(value = "/addgroup-{t_course_teaching_class_id}")
	public ModelAndView addgroup(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/coursegroup/manualgroup-" + t_course_teaching_class_id + ".html");

		String group_name = request.getParameter("name");
		String group_note = request.getParameter("note");

		if (group_name == null || group_name.isEmpty()) {
			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "组名不能为空");
			return mav;
		}

		courseTeachingClassStudentGroupService.addGroup(t_course_teaching_class_id, group_name, group_note);

		return mav;
	}

	@RequestMapping(value = "/updategroup-{t_course_teaching_class_id}")
	public ModelAndView updategroup(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/coursegroup/manualgroup-" + t_course_teaching_class_id + ".html");

		String group_name = request.getParameter("name");
		String group_note = request.getParameter("note");
		String t_group_id = request.getParameter("t_group_id");

		if (group_name == null || group_name.isEmpty()) {
			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "组名不能为空");
			return mav;
		}

		groupService.update(t_group_id, group_name, group_note);

		return mav;
	}

	@RequestMapping(value = "/deletegroup-{t_course_teaching_class_id}-{t_course_teaching_class_student_group_id}")
	public ModelAndView deletegroup(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_student_group_id) {

		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/coursegroup/manualgroup-" + t_course_teaching_class_id + ".html");

		// delete
		courseTeachingClassStudentGroupService.deleteGroup(t_course_teaching_class_id,
				t_course_teaching_class_student_group_id);

		return mav;
	}

	/**
	 * 显示次序向上移动
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/showindexmoveupatmannal-{t_course_teaching_class_id}-{t_group_id}", method = RequestMethod.GET)
	public ModelAndView showindexmoveupatmannal(HttpServletRequest request,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_group_id) {

		ModelAndView mav = new ModelAndView();

		if (t_course_teaching_class_id != null && t_group_id != null)
			courseTeachingClassStudentGroupService.ShowIndexMoveUp(t_course_teaching_class_id, t_group_id);

		mav.setViewName("redirect:/coursegroup/manualgroup-" + t_course_teaching_class_id + ".html");

		return mav;
	}

	/**
	 * 显示次序向下移动
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/showindexmovedownatmannal-{t_course_teaching_class_id}-{t_group_id}", method = RequestMethod.GET)
	public ModelAndView showindexmovedownatmannal(HttpServletRequest request,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_group_id) {

		ModelAndView mav = new ModelAndView();

		if (t_course_teaching_class_id != null && t_group_id != null)
			courseTeachingClassStudentGroupService.ShowIndexMoveDown(t_course_teaching_class_id, t_group_id);

		mav.setViewName("redirect:/coursegroup/manualgroup-" + t_course_teaching_class_id + ".html");

		return mav;
	}

	@RequestMapping(value = "/addstudent2group-{t_course_teaching_class_id}")
	public ModelAndView addstudent2group(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/coursegroup/manualgroup-" + t_course_teaching_class_id + ".html");

		String userids = request.getParameter("userids");
		String t_group_id = request.getParameter("t_group_id");

		if (userids == null || userids.isEmpty()) {
			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "学生不能为空");
			return mav;
		}

		String[] userid = userids.split(";");

		if (userid.length == 0) {
			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "组名不能为空");
			return mav;
		}

		if (t_group_id == null || t_group_id.isEmpty()) {
			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "组名不能为空");
			return mav;
		}

		courseTeachingClassStudentGroupService.AddStudent2Group(t_course_teaching_class_id, t_group_id, userid);

		return mav;
	}

	@RequestMapping(value = "/deletestudentfromgroup-{t_course_teaching_class_id}-{t_group_id}-{t_user_id}")
	public ModelAndView deletestudentfromgroup(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_group_id,
			@PathVariable String t_user_id) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/coursegroup/manualgroup-" + t_course_teaching_class_id + ".html");

		if (t_user_id == null || t_user_id.isEmpty()) {
			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "学生不能为空");
			return mav;
		}

		if (t_group_id == null || t_group_id.isEmpty()) {
			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "组名不能为空");
			return mav;
		}

		courseTeachingClassStudentGroupService.DeleteStudentFromGroup(t_course_teaching_class_id, t_group_id,
				t_user_id);

		return mav;
	}

	/**
	 * 得到未分组学生
	 * 
	 * @param request
	 * @param user
	 * @return
	 */

	@RequestMapping(value = "/selectnotgrouped-{t_course_teaching_class_id}", method = RequestMethod.GET)
	@ResponseBody
	public List<StudentViewData> getNotGroupedStudent(@PathVariable String t_course_teaching_class_id) {
		if (t_course_teaching_class_id != null)
			return courseTeachingClassStudentGroupService.getNotGroupedStudent(t_course_teaching_class_id);
		return null;
	}

}
