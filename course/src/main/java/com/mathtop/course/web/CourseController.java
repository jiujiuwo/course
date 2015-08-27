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
import com.mathtop.course.cons.CourseMessage;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.Course;
import com.mathtop.course.domain.CourseStyle;
import com.mathtop.course.domain.CourseType;
import com.mathtop.course.domain.Department;
import com.mathtop.course.domain.School;
import com.mathtop.course.exception.CourseExistException;
import com.mathtop.course.service.CourseService;
import com.mathtop.course.service.CourseStyleService;
import com.mathtop.course.service.CourseTypeService;
import com.mathtop.course.service.DepartmentService;
import com.mathtop.course.service.SchoolService;

@Controller
@RequestMapping("/course")
public class CourseController extends BaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private SchoolService schoolService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private CourseStyleService coursestyleService;

	@Autowired
	private CourseTypeService coursetypeService;

	@Autowired
	private CourseService courseService;

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request) {

		Page<School> pagedSchool = schoolService.getPage(1,
				CommonConstant.PAGE_SIZE);

		ModelAndView mav = new ModelAndView();
		mav.addObject("pagedSchool", pagedSchool);

		String t_school_id = null;
		List<School> schools = pagedSchool.getResult();
		if (schools.size() > 0)
			t_school_id = schools.get(0).getId();

		SetPage(mav, t_school_id, 1);

		mav.setViewName("/course/add");

		return mav;
	}

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addcourse")
	public ModelAndView addteacher(HttpServletRequest request) {
		// String t_school_id = request.getParameter("t_school_id");
		String course_num = request.getParameter("course_num");
		String course_name = request.getParameter("course_name");
		String course_english_name = request
				.getParameter("course_english_name");
		String t_course_style_id = request.getParameter("coursestyleID");
		String t_course_type_id = request.getParameter("coursetypeID");
		String class_hours = request.getParameter("class_hours");
		String experiment_hours = request.getParameter("experiment_hours");
		String[] t_department_id = request.getParameterValues("t_department_id");
		String[] precourseId = request.getParameterValues("precourseId");

		try {
			int n_class_hours=Integer.parseInt(class_hours);
			int n_experiment_hours=Integer.parseInt(experiment_hours);
			courseService.add(course_name, course_english_name, course_num, n_class_hours,n_experiment_hours , t_course_type_id, t_course_style_id,t_department_id,precourseId);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CourseExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();
		// SetPage(mav, t_school_id, 1);

		mav.setViewName("redirect:/course/list.html");
		return mav;

	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/DELETE-{t_school_id}-{t_department_id}", method = RequestMethod.GET)
	public ModelAndView DELETE(@PathVariable String t_school_id,
			@PathVariable String t_department_id) {

		// if (t_school_id != null && t_department_id != null)
		// departmentService.DELETE(t_school_id, t_department_id);

		Integer pageNo = 1;
		return ListAll(t_school_id, pageNo);
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
		view.setViewName("course/list");

		if (departmentname != null && departmentname.length() > 0) {

			int pageNo = 1;
			Page<School> pagedSchool = schoolService.select(departmentname,
					pageNo, CommonConstant.PAGE_SIZE);

			view.addObject("pagedSchool", pagedSchool);
			view.setViewName("course/list");

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
		String t_school_id = request.getParameter("t_school_id");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/course/list");

		long n = departmentService.getDepartmentCount(t_school_id, d.getName());

		if (n > 0) {
			mav.addObject(CourseMessage.Message_errorMsg, "学院系部已经存在");
			Integer pageNo = 1;
			return ListAll(t_school_id, pageNo);

		} else {

			departmentService.update(d.getId(), d.getName(), d.getNote());

			Integer pageNo = 1;
			return ListAll(t_school_id, pageNo);

		}
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
			@RequestParam(value = "t_school_id", required = false) String t_school_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		ModelAndView mav = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;

		if (t_school_id == null) {

			Page<School> pagedSchool = schoolService.getPage(pageNo,
					CommonConstant.PAGE_SIZE);

			List<School> schools = pagedSchool.getResult();
			if (schools.size() > 0) {
				t_school_id = schools.get(0).getId();
			}

		}
		
		Page<Course> pagedCourse = courseService.getPage(pageNo, CommonConstant.PAGE_SIZE);
		mav.addObject(PagedObjectConst.Paged_Course, pagedCourse);
		

		SetPage(mav, t_school_id, pageNo);

		// Page<TeacherViewData> pagedTeacherViewData = teacherService.getPage(
		// t_school_id, pageNo, CommonConstant.PAGE_SIZE);
		// mav.addObject(strPagedTeacherViewData, pagedTeacherViewData);

		mav.setViewName("course/list");

		return mav;
	}

	private void SetPage(ModelAndView mav, String t_school_id, Integer pageNo) {
		// 得到学院
		Page<School> pagedSchool = schoolService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);

		mav.addObject("pagedSchool", pagedSchool);

		if (t_school_id != null) {
			Page<Department> pagedDepartment = departmentService
					.getPage(t_school_id);

			mav.addObject(PagedObjectConst.Paged_Department, pagedDepartment);
		}

		Page<CourseStyle> pagedCourseStyle = coursestyleService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_CourseStyle, pagedCourseStyle);

		Page<CourseType> pagedCourseType = coursetypeService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_CourseType, pagedCourseType);

		Page<Course> pagedPreCourse = courseService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_PreCourse, pagedPreCourse);

		if (t_school_id == null) {

			List<School> schools = pagedSchool.getResult();
			if (schools.size() > 0) {
				t_school_id = schools.get(0).getId();
			}

		}

		Page<Department> pagedDeparment = departmentService.getPage(t_school_id,
				pageNo, CommonConstant.PAGE_SIZE);

		mav.addObject("pagedDeparment", pagedDeparment);

		mav.addObject("selectedt_school_id", t_school_id);

	}

}
