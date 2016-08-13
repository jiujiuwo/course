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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.cons.SelectedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.CourseTeachingClassViewData;
import com.mathtop.course.domain.CourseViewData;
import com.mathtop.course.domain.Department;
import com.mathtop.course.domain.School;
import com.mathtop.course.domain.StudentViewData;
import com.mathtop.course.domain.TeacherViewData;
import com.mathtop.course.domain.TeachingType;
import com.mathtop.course.service.CourseService;
import com.mathtop.course.service.CourseTeachingClassService;
import com.mathtop.course.service.DeleteService;
import com.mathtop.course.service.SchoolService;
import com.mathtop.course.service.StudentService;
import com.mathtop.course.service.TeacherService;
import com.mathtop.course.service.TeachingTypeService;

/**
 * 自然班管理
 */

@Controller
@RequestMapping("/teachingclass")
public class CourseTeachingClassController extends BaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private SchoolService schoolService;

	@Autowired
	CourseTeachingClassService courseTeachingClassService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	CourseService courseService;

	@Autowired
	private TeachingTypeService teachingtypeService;

	@Autowired
	private StudentService studentService;

	@Autowired
	DeleteService deleteService;

	private final String strPageURI = "teachingclass";

	// paged object

	private void SetPageURI(ModelAndView mav) {
		mav.addObject("pagedURI", strPageURI);
	}

	/**
	 * 添加教学班
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		Page<CourseViewData> pagedCourseViewData = courseService.getAllPage();
		mav.addObject(PagedObjectConst.Paged_CourseViewData, pagedCourseViewData);

		String t_school_id = null;
		int pageNo = 1;

		// 学院
		Page<School> pagedSchool = schoolService.getPage(pageNo, CommonConstant.PAGE_SIZE);

		List<School> schools = pagedSchool.getResult();
		if (schools.size() > 0) {
			t_school_id = schools.get(0).getId();
		}

		mav.addObject(PagedObjectConst.Paged_School, pagedSchool);

		// 教师
		Page<TeacherViewData> pagedTeacherViewData = teacherService.getPage(t_school_id, pageNo,
				CommonConstant.PAGE_SIZE);
		mav.addObject(PagedObjectConst.Paged_TeacherViewData, pagedTeacherViewData);

		// 授课类型
		Page<TeachingType> pagedTeachingType = teachingtypeService.getPage(pageNo, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_TeachingType, pagedTeachingType);

		mav.setViewName("teachingclass/add");
		return mav;
	}

	/**
	 * 添加教学班
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addteachingclass")
	public ModelAndView addteachingclass(HttpServletRequest request) {

		String courseId = request.getParameter("t_course_id");
		String name = request.getParameter("teachingclass_name");
		String t_course_teaching_term_id = request.getParameter("course_teaching_class_term_id");

		String[] teacherid = request.getParameterValues("teacherid");
		String[] teachingtypetypeId = request.getParameterValues("teachingtypetypeId");

		courseTeachingClassService.add(courseId, name, t_course_teaching_term_id, teacherid, teachingtypetypeId);

		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/teachingclass/update.html");
		return mav;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update-{t_course_teaching_class_id}")
	public ModelAndView update(HttpServletRequest request, @PathVariable String t_course_teaching_class_id) {

		ModelAndView mav = new ModelAndView();

		// 课程授课等信息
		CourseTeachingClassViewData selectedCourseTeachingClassViewData = courseTeachingClassService
				.GetTeachingClassViewDataByCourseTeachingClassId(t_course_teaching_class_id);

		mav.addObject(SelectedObjectConst.Selected_CourseTeachingClassViewData, selectedCourseTeachingClassViewData);

		String t_school_id = null;
		int pageNo = 1;

		// 学院
		Page<School> pagedSchool = schoolService.getPage(pageNo, CommonConstant.PAGE_SIZE);

		List<School> schools = pagedSchool.getResult();
		if (schools.size() > 0) {
			t_school_id = schools.get(0).getId();
		}

		mav.addObject(PagedObjectConst.Paged_School, pagedSchool);

		// 教师
		Page<TeacherViewData> pagedTeacherViewData = teacherService.getPage(t_school_id, pageNo,
				CommonConstant.PAGE_SIZE);
		mav.addObject(PagedObjectConst.Paged_TeacherViewData, pagedTeacherViewData);

		// 授课类型
		Page<TeachingType> pagedTeachingType = teachingtypeService.getPage(pageNo, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_TeachingType, pagedTeachingType);

		mav.setViewName("teachingclass/update");
		return mav;
	}

	/**
	 * 添加教学班
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updateteachingclass-{t_course_teaching_class_id}")
	public ModelAndView updateteachingclass(HttpServletRequest request,
			@PathVariable String t_course_teaching_class_id) {

		String courseId = request.getParameter("t_course_id");
		String name = request.getParameter("teachingclass_name");
		String t_course_teaching_term_id = request.getParameter("course_teaching_class_term_id");

		String[] teacherid = request.getParameterValues("teacherid");
		String[] teachingtypetypeId = request.getParameterValues("teachingtypetypeId");

		courseTeachingClassService.update(t_course_teaching_class_id, courseId, name, t_course_teaching_term_id,
				teacherid, teachingtypetypeId);

		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/teachingclass/list.html");
		return mav;
	}

	/**
	 * 为教学班添加学生
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addstudent2teachingclass")
	public ModelAndView addstudent2teachingclass(HttpServletRequest request) {

		String teachingclassid = request.getParameter("selectedTeachingClassID");

		String[] studentid = request.getParameterValues("studentid");

		courseTeachingClassService.add(teachingclassid, studentid);

		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/teachingclass/list.html");
		return mav;
	}

	/**
	 * 从excel添加学生
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addfromexcel-{t_course_teaching_class_id}")
	public ModelAndView addfromexcel(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id) {

		ModelAndView mav = new ModelAndView();

		// 课程授课等信息
		CourseTeachingClassViewData selectedCourseTeachingClassViewData = courseTeachingClassService
				.GetTeachingClassViewDataByCourseTeachingClassId(t_course_teaching_class_id);

		mav.addObject(SelectedObjectConst.Selected_CourseTeachingClassViewData, selectedCourseTeachingClassViewData);

		mav.setViewName("teachingclass/addfromexcel");

		return mav;
	}

	/**
	 * 从excel添加学生
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/uploadexcel-{t_course_teaching_class_id}")
	public ModelAndView uploadexcel(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@RequestParam("file") MultipartFile file) {
		ModelAndView view = new ModelAndView();

		view.setViewName("redirect:/teachingclass/list.html");

		try {
			courseTeachingClassService.UploadFromExcel(t_course_teaching_class_id, file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return view;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/delete-{t_course_teaching_class_id}", method = RequestMethod.GET)
	public ModelAndView deleteteachingclass(HttpServletRequest request,
			@PathVariable String t_course_teaching_class_id) {

		ModelAndView mav = new ModelAndView();
		if (t_course_teaching_class_id == null || t_course_teaching_class_id.trim().length() == 0) {
			mav.setViewName("redirect:/teachingclass/list.html");
		}

		courseTeachingClassService.deleteByCourseId(request, t_course_teaching_class_id);
		mav.setViewName("redirect:/teachingclass/list.html");
		return mav;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/deletestudent-{t_course_teaching_class_id}-{t_student_id}-{pageNo}", method = RequestMethod.GET)
	public ModelAndView deletestudent(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_student_id, @PathVariable Integer pageNo) {

		ModelAndView mav = new ModelAndView();
		mav.addObject(SelectedObjectConst.Selected_PageNo, pageNo);

		// 学生
		StudentViewData selectedStudentViewData = studentService.getStudentViewByStudentId(t_student_id);
		mav.addObject(SelectedObjectConst.Selected_StudentViewData, selectedStudentViewData);

		CourseTeachingClassViewData selectedCourseTeachingClassViewData = courseTeachingClassService
				.GetTeachingClassViewDataByCourseTeachingClassId(t_course_teaching_class_id);

		mav.addObject(SelectedObjectConst.Selected_CourseTeachingClassViewData, selectedCourseTeachingClassViewData);
		mav.setViewName("teachingclass/deletestudent");
		return mav;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dodeletestudent-{t_course_teaching_class_id}-{t_student_id}-{pageNo}")
	public ModelAndView dodeletestudent(HttpServletRequest request, RedirectAttributes redirectAttributes,
			HttpSession session, @PathVariable String t_course_teaching_class_id, @PathVariable String t_student_id,
			@PathVariable Integer pageNo) {
		ModelAndView mav = new ModelAndView();

		/*
		 * String user_verifycode=request.getParameter("user_verifycode"); if
		 * (!(user_verifycode.equalsIgnoreCase(session.getAttribute("code").
		 * toString()))) { //忽略验证码大小写
		 * 
		 * 
		 * redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg,
		 * "验证码不正确.");
		 * 
		 * mav.setViewName("redirect:/teachingclass/list.html-"+
		 * t_course_teaching_class_id+".html?pageNo="+pageNo);
		 * 
		 * return mav; }
		 */

		if (t_student_id != null)
			deleteService.deleteStudentFromCourseTeachingClass(request, t_course_teaching_class_id, t_student_id);

		mav.setViewName("redirect:/teachingclass/list.html-" + t_course_teaching_class_id + ".html?pageNo=" + pageNo);
		return mav;

	}
	
	/**
	 * 显示次序向上移动
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/showindexmoveup-{t_course_teaching_class_id}-{t_student_id}-{pageNo}", method = RequestMethod.GET)
	public ModelAndView showindexmoveup(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_student_id, @PathVariable Integer pageNo) {

		ModelAndView mav = new ModelAndView();

		/*
		 * String user_verifycode=request.getParameter("user_verifycode"); if
		 * (!(user_verifycode.equalsIgnoreCase(session.getAttribute("code").
		 * toString()))) { //忽略验证码大小写
		 * 
		 * 
		 * redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg,
		 * "验证码不正确.");
		 * 
		 * mav.setViewName("redirect:/teachingclass/list.html-"+
		 * t_course_teaching_class_id+".html?pageNo="+pageNo);
		 * 
		 * return mav; }
		 */

		if (t_course_teaching_class_id !=null && t_student_id != null)
			courseTeachingClassService.ShowIndexMoveUp(t_course_teaching_class_id, t_student_id);

		mav.setViewName("redirect:/teachingclass/student-" + t_course_teaching_class_id + ".html?pageNo=" + pageNo);
		return mav;
	}

	
	/**
	 * 显示次序向下移动
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/showindexmovedown-{t_course_teaching_class_id}-{t_student_id}-{pageNo}", method = RequestMethod.GET)
	public ModelAndView showindexmovedown(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_student_id, @PathVariable Integer pageNo) {

		ModelAndView mav = new ModelAndView();

		/*
		 * String user_verifycode=request.getParameter("user_verifycode"); if
		 * (!(user_verifycode.equalsIgnoreCase(session.getAttribute("code").
		 * toString()))) { //忽略验证码大小写
		 * 
		 * 
		 * redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg,
		 * "验证码不正确.");
		 * 
		 * mav.setViewName("redirect:/teachingclass/list.html-"+
		 * t_course_teaching_class_id+".html?pageNo="+pageNo);
		 * 
		 * return mav; }
		 */

		if (t_course_teaching_class_id !=null && t_student_id != null)
			courseTeachingClassService.ShowIndexMoveDown(t_course_teaching_class_id, t_student_id);

		mav.setViewName("redirect:/teachingclass/student-" + t_course_teaching_class_id + ".html?pageNo=" + pageNo);
		return mav;
	}

	
	/**
	 * 查找
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/select-{departmentname}", method = RequestMethod.GET)
	public ModelAndView select(@PathVariable String departmentname) {

		ModelAndView view = new ModelAndView();
		view.setViewName("teachingclass/list.html");

		if (departmentname != null && departmentname.length() > 0) {

			int pageNo = 1;
			Page<School> pagedSchool = schoolService.select(departmentname, pageNo, CommonConstant.PAGE_SIZE);

			view.addObject(PagedObjectConst.Paged_School, pagedSchool);
			view.setViewName("teachingclass/list.html");

		}
		return view;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update")
	public ModelAndView update(HttpServletRequest request, Department d) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("teachingclass/list");

		return mav;
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
	public ModelAndView student(@PathVariable String t_course_teaching_class_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		ModelAndView mav = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;

		CourseTeachingClassViewData selected_CourseTeachingClassViewData = courseTeachingClassService
				.GetTeachingClassViewDataByCourseTeachingClassId(t_course_teaching_class_id);

		mav.addObject(SelectedObjectConst.Selected_CourseTeachingClassID, t_course_teaching_class_id);

		mav.addObject(SelectedObjectConst.Selected_CourseTeachingClassViewData, selected_CourseTeachingClassViewData);

		// 学生信息
		Page<StudentViewData> pagedStudentViewData = studentService
				.getPageByCourseTeachingClassId(t_course_teaching_class_id, pageNo, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_StudentViewData, pagedStudentViewData);

		mav.setViewName("teachingclass/student");

		SetPageURI(mav);

		return mav;
	}

	/**
	 * 为教学班添加学生
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addstudent-{t_course_teaching_class_id}", method = RequestMethod.GET)
	public ModelAndView addstudent(@PathVariable String t_course_teaching_class_id) {

		ModelAndView mav = new ModelAndView();

	
		CourseTeachingClassViewData selected_CourseTeachingClassViewData = courseTeachingClassService
				.GetTeachingClassViewDataByCourseTeachingClassId(t_course_teaching_class_id);

		mav.addObject(SelectedObjectConst.Selected_CourseTeachingClassID, t_course_teaching_class_id);

		mav.addObject(SelectedObjectConst.Selected_CourseTeachingClassViewData, selected_CourseTeachingClassViewData);

		mav.setViewName("teachingclass/addstudent");

		SetPageURI(mav);

		return mav;
	}

	/**
	 * 根据教学班列出教师
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/teacher-{t_teaching_class_id}", method = RequestMethod.GET)
	public ModelAndView teacher(@PathVariable String t_teaching_class_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		ModelAndView mav = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;

		Page<CourseTeachingClassViewData> pagedTeachingClassViewData = courseTeachingClassService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_TeachingClassViewData, pagedTeachingClassViewData);

		mav.addObject(PagedObjectConst.Paged_TeacherCount, teacherService.getTeacherCount());

		// 学院
		Page<School> pagedSchool = schoolService.getPage(pageNo, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_School, pagedSchool);

		// 授课类型
		Page<TeachingType> pagedTeachingType = teachingtypeService.getPage(pageNo, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_TeachingType, pagedTeachingType);

		mav.setViewName("teachingclass/teacher");

		SetPageURI(mav);

		return mav;
	}

	/**
	 * 为教学班添加教师
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addteacher2teachingclass")
	public ModelAndView addteacher2teachingclass(HttpServletRequest request) {

		String teachingclassid = request.getParameter("selectedTeachingClassID");

		System.out.println(teachingclassid);

		String[] teacherid = request.getParameterValues("teacherid");
		String[] teachingtypeid = request.getParameterValues("teachingtypeid");

		courseTeachingClassService.add(teachingclassid, teacherid, teachingtypeid);

		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/teachingclass/list.html");
		return mav;
	}

	/**
	 * 列出全部学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView ListAll(@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		ModelAndView mav = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;

		Page<CourseTeachingClassViewData> pagedTeachingClassViewData = courseTeachingClassService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_TeachingClassViewData, pagedTeachingClassViewData);

		mav.setViewName("teachingclass/list");

		SetPageURI(mav);

		return mav;
	}

}
