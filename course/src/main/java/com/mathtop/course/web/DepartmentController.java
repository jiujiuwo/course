package com.mathtop.course.web;

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

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.cons.CourseMessage;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.Department;
import com.mathtop.course.domain.School;
import com.mathtop.course.service.DepartmentService;
import com.mathtop.course.service.SchoolService;

@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private SchoolService schoolService;

	@Autowired
	private DepartmentService departmentService;

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request, Department d) {
		String t_school_id = request.getParameter("t_school_id");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/department/list");

		long n = departmentService.getDepartmentCount(t_school_id, d.getName());

		if (n > 0) {
			mav.addObject(CourseMessage.Message_errorMsg, "学院系部已经存在");
			Integer pageNo = 1;
			return ListAll(t_school_id, pageNo);

		} else {

			departmentService.Add(t_school_id, d.getName(), d.getNote());

			Integer pageNo = 1;
			return ListAll(t_school_id, pageNo);

		}
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

		if (t_school_id != null && t_department_id != null)
			departmentService.DELETE(t_school_id, t_department_id);

		Integer pageNo = 1;
		return ListAll(t_school_id, pageNo);
	}
	
	/**
	 * 得到指定学院下的系部
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	
	@RequestMapping(value = "/selectbyschool-{t_school_id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Department> androidLogin(HttpServletRequest request,@PathVariable String t_school_id) {		
		
		 return departmentService.getAll(t_school_id);
		
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
		view.setViewName("department/list");

		if (departmentname != null && departmentname.length() > 0) {

			int pageNo = 1;
			Page<School> pagedSchool = schoolService.select(departmentname,
					pageNo, CommonConstant.PAGE_SIZE);

			view.addObject("pagedSchool", pagedSchool);
			view.setViewName("department/list");

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
		mav.setViewName("/department/list");

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
		ModelAndView view = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;

		Page<School> pagedSchool = schoolService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);

		view.addObject("pagedSchool", pagedSchool);

		if (t_school_id == null) {

			List<School> schools = pagedSchool.getResult();
			if (schools.size() > 0) {
				t_school_id = schools.get(0).getId();
			}

		}

		Page<Department> pagedDeparment = departmentService.getPage(t_school_id,
				pageNo, CommonConstant.PAGE_SIZE);

		view.addObject("pagedDeparment", pagedDeparment);

		view.addObject("selectedt_school_id", t_school_id);

		view.setViewName("department/list");
		return view;
	}

}
