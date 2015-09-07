package com.mathtop.course.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.cons.SelectedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.Course;
import com.mathtop.course.domain.Department;
import com.mathtop.course.domain.School;
import com.mathtop.course.domain.StudentViewData;
import com.mathtop.course.domain.TeacherViewData;
import com.mathtop.course.domain.CourseTeachingClassViewData;
import com.mathtop.course.domain.TeachingType;
import com.mathtop.course.service.CourseService;
import com.mathtop.course.service.DepartmentService;
import com.mathtop.course.service.SchoolService;
import com.mathtop.course.service.StudentService;
import com.mathtop.course.service.TeacherService;
import com.mathtop.course.service.TeachingClassService;
import com.mathtop.course.service.TeachingTypeService;

/**
 * 自然班管理
 * */

@Controller
@RequestMapping("/teachingclass")
public class TeachingClassController extends BaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private SchoolService schoolService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	TeachingClassService teachingclassService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	CourseService courseService;

	@Autowired
	private TeachingTypeService teachingtypeService;

	@Autowired
	private StudentService studentService;

	private final String strPageURI = "teachingclass";

	// paged object

	private void SetPageURI(ModelAndView mav) {
		mav.addObject("pagedURI", strPageURI);
	}

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request, Department d) {

		ModelAndView mav = new ModelAndView();

		Page<Course> pagedCourse = courseService.getPage(1,
				CommonConstant.PAGE_SIZE);
		mav.addObject(PagedObjectConst.Paged_Course, pagedCourse);

		String t_school_id = null;
		int pageNo = 1;

		// 学院
		Page<School> pagedSchool = schoolService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);

		List<School> schools = pagedSchool.getResult();
		if (schools.size() > 0) {
			t_school_id = schools.get(0).getId();
		}

		mav.addObject(PagedObjectConst.Paged_School, pagedSchool);

		// 教师
		Page<TeacherViewData> pagedTeacherViewData = teacherService.getPage(
				t_school_id, pageNo, CommonConstant.PAGE_SIZE);
		mav.addObject(PagedObjectConst.Paged_TeacherViewData,
				pagedTeacherViewData);

		// 授课类型
		Page<TeachingType> pagedTeachingType = teachingtypeService.getPage(
				pageNo, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_TeachingType, pagedTeachingType);

		mav.setViewName(strPageURI + "/add");
		return mav;
	}

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addteachingclass")
	public ModelAndView addteachingclass(HttpServletRequest request) {

		String courseId = request.getParameter("courseId");
		int teaching_year_begin = Integer.parseInt(request
				.getParameter("teaching_year_begin"));
		int teaching_year_end = Integer.parseInt(request
				.getParameter("teaching_year_end"));
		int teaching_term = Integer.parseInt(request
				.getParameter("teaching_term"));
		String teachingclass_name = request.getParameter("teachingclass_name");
		String[] teacherid = request.getParameterValues("teacherid");
		String[] teachingtypetypeId = request
				.getParameterValues("teachingtypetypeId");

		teachingclassService.add(courseId, teaching_year_begin,
				teaching_year_end, teaching_term, teachingclass_name,
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

		String teachingclassid = request
				.getParameter("selectedTeachingClassID");

		String[] studentid = request.getParameterValues("studentid");

		teachingclassService.add(teachingclassid, studentid);

		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/" + strPageURI + "/list.html");
		return mav;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/DELETE-{t_teachingclass_id}", method = RequestMethod.GET)
	public ModelAndView DELETE(@PathVariable String t_teachingclass_id) {

		
	//	System.out.println(t_teachingclass_id);
		
		
		ModelAndView mav = new ModelAndView();
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
	@RequestMapping(value = "/deletestudent-{t_teachingclass_id}-{t_student_id}", method = RequestMethod.GET)
	public ModelAndView deletestudent(@PathVariable String t_teachingclass_id,@PathVariable String t_student_id) {

		
	//	System.out.println(t_teachingclass_id);
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/teachingclass/list.html");

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
		view.setViewName(strPageURI + "/list.html");

		if (departmentname != null && departmentname.length() > 0) {

			int pageNo = 1;
			Page<School> pagedSchool = schoolService.select(departmentname,
					pageNo, CommonConstant.PAGE_SIZE);

			view.addObject(PagedObjectConst.Paged_School, pagedSchool);
			view.setViewName(strPageURI + "/list.html");

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
		mav.setViewName(strPageURI + "/list");

		return mav;
	}

	/**
	 * 根据教学班列出学生
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/student-{teachingclassid}", method = RequestMethod.GET)
	public ModelAndView student(@PathVariable String teachingclassid,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		ModelAndView mav = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		
		
		

		// 学生信息
		Page<StudentViewData> pagedStudentViewData = studentService
				.getPageByTeachingClassId(teachingclassid, pageNo,
						CommonConstant.PAGE_SIZE);

		

		mav.addObject(PagedObjectConst.Paged_StudentViewData,
				pagedStudentViewData);

		Page<CourseTeachingClassViewData> pagedTeachingClassViewData = teachingclassService
				.getPage(teachingclassid);

		mav.addObject(PagedObjectConst.Paged_TeachingClassViewData,
				pagedTeachingClassViewData);

		mav.addObject(SelectedObjectConst.Selected_TeachingClass_ID,
				teachingclassid);

		mav.setViewName(strPageURI + "/student");

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
	@RequestMapping(value = "/addstudent-{teachingclassid}", method = RequestMethod.GET)
	public ModelAndView addstudent(@PathVariable String teachingclassid) {

		ModelAndView mav = new ModelAndView();

		String t_school_id = null;
		int pageNo = 1;

		Page<School> pagedSchool = schoolService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_School, pagedSchool);

		List<School> schools = pagedSchool.getResult();
		if (schools.size() > 0) {
			t_school_id = schools.get(0).getId();
		}

		if (t_school_id != null) {
			Page<Department> pagedDeparment = departmentService.getPage(
					t_school_id, pageNo, CommonConstant.PAGE_SIZE);

			mav.addObject(PagedObjectConst.Paged_Department, pagedDeparment);

			mav.addObject(SelectedObjectConst.Selected_School_ID, t_school_id);
		}

		Page<CourseTeachingClassViewData> pagedTeachingClassViewData = teachingclassService
				.getPage(teachingclassid);

		mav.addObject(PagedObjectConst.Paged_TeachingClassViewData,
				pagedTeachingClassViewData);

		mav.addObject(SelectedObjectConst.Selected_TeachingClass_ID,
				teachingclassid);

		mav.setViewName(strPageURI + "/addstudent");

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
	@RequestMapping(value = "/teacher-{teachingclassid}", method = RequestMethod.GET)
	public ModelAndView teacher(@PathVariable String teachingclassid,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		ModelAndView mav = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;

		Page<CourseTeachingClassViewData> pagedTeachingClassViewData = teachingclassService
				.getPage(teachingclassid);

		mav.addObject(PagedObjectConst.Paged_TeachingClassViewData,
				pagedTeachingClassViewData);

		mav.addObject(SelectedObjectConst.Selected_TeachingClass_ID,
				teachingclassid);
		
		
		mav.addObject(PagedObjectConst.Paged_TeacherCount,teacherService.getTeacherCount());

		// 学院
		Page<School> pagedSchool = schoolService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_School, pagedSchool);
		

		// 授课类型
		Page<TeachingType> pagedTeachingType = teachingtypeService.getPage(
				pageNo, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_TeachingType, pagedTeachingType);

		mav.setViewName(strPageURI + "/teacher");

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

		String teachingclassid = request
				.getParameter("selectedTeachingClassID");
		
		System.out.println(teachingclassid);

		String[] teacherid = request.getParameterValues("teacherid");
		String[] teachingtypeid = request.getParameterValues("teachingtypeid");

		teachingclassService.add(teachingclassid, teacherid,teachingtypeid);

		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/" + strPageURI + "/list.html");
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
	public ModelAndView ListAll(
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		ModelAndView mav = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;

		Page<CourseTeachingClassViewData> pagedTeachingClassViewData = teachingclassService
				.getPage(pageNo, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_TeachingClassViewData,
				pagedTeachingClassViewData);

		mav.setViewName(strPageURI + "/list");

		SetPageURI(mav);

		return mav;
	}

}
