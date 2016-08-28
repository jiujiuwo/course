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
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.cons.SelectedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.Department;
import com.mathtop.course.domain.NaturalClass;
import com.mathtop.course.domain.DepartmentNaturalClassViewData;
import com.mathtop.course.domain.School;
import com.mathtop.course.service.DepartmentService;
import com.mathtop.course.service.DepartmentNaturalClassService;
import com.mathtop.course.service.SchoolService;


/**
 * 自然班管理
 * */

@Controller
@RequestMapping("/naturalclass")
public class NaturalClassController extends BaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private SchoolService schoolService;

	@Autowired
	private DepartmentService departmentService;
	
	
	@Autowired
	DepartmentNaturalClassService departmentNaturalClassService;
	
	
	private final String strPageURI = "/naturalclass";
	
	//paged object
	

	private void SetPageURI(ModelAndView mav) {
		mav.addObject("pagedURI", strPageURI);
	}

	/**
	 * 添加自然班级
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request, Department d) {
		
		String t_department_id = request.getParameter("t_department_id");
		String naturalclassname = request.getParameter("naturalclassname");
		String naturalclassnote = request.getParameter("naturalclassnote");
		
		departmentNaturalClassService.add( t_department_id, naturalclassname, naturalclassnote);

		ModelAndView mav = new ModelAndView();
		

		mav.setViewName("redirect:" + strPageURI+"/list.html?t_depratment_id="+t_department_id);
		return mav;
	}

	/**
	 * 删除自然班级
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/DELETE-{t_natural_class_id}", method = RequestMethod.GET)
	public ModelAndView DELETE(@PathVariable String t_natural_class_id) {

		System.out.println(t_natural_class_id);
		String t_school_id=null;
		
		if (t_natural_class_id != null )
			departmentNaturalClassService.delete(t_natural_class_id);

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
	
	@RequestMapping(value = "/selectbyt_department_id-{t_department_id}", method = RequestMethod.GET)
	@ResponseBody
	public List<NaturalClass> androidLogin(HttpServletRequest request,@PathVariable String t_department_id) {		
		
		 return departmentNaturalClassService.getNaturalClassByt_department_id(t_department_id);
		
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

			view.addObject(PagedObjectConst.Paged_School, pagedSchool);
			view.setViewName(strPageURI+"/list.html");

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
		mav.setViewName(strPageURI+"/list");

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
			@RequestParam(value = "t_depratment_id", required = false) String t_depratment_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();
		
		
		
	//	System.out.println("NaturalClassControler:listall 0");

		pageNo = pageNo == null ? 1 : pageNo;

		Page<School> pagedSchool = schoolService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);

		view.addObject(PagedObjectConst.Paged_School, pagedSchool);
		
		String t_school_id=null;
		
		if(t_depratment_id==null){
		
			List<School> schools = pagedSchool.getResult();
			if (schools.size() > 0) {
				t_school_id = schools.get(0).getId();
				
			}
		}else{
			t_school_id=departmentService.getSchoolIdByDepartmentId(t_depratment_id);
		}

		
		
		
		
		Page<Department> pagedDeparment = departmentService.getPage(t_school_id,
				pageNo, CommonConstant.PAGE_SIZE);

		view.addObject(PagedObjectConst.Paged_Department, pagedDeparment);
		
		
		if(t_depratment_id==null && pagedDeparment!=null){
			
			List<Department> departments = pagedDeparment.getResult();
			if (departments.size() > 0) {
				t_depratment_id = departments.get(0).getId();
				
			}
		}
		
		

		view.addObject(SelectedObjectConst.Selected_School_ID, t_school_id);
		view.addObject(SelectedObjectConst.Selected_Department_ID, t_depratment_id);
		
		
		Page<DepartmentNaturalClassViewData> pagedDepartmentNaturalClassViewData = departmentNaturalClassService.getPage(t_depratment_id,pageNo,
				CommonConstant.PAGE_SIZE);

		view.addObject(PagedObjectConst.Paged_DepartmentNaturalClassViewData, pagedDepartmentNaturalClassViewData);
		

		view.setViewName("naturalclass/list");
		
		SetPageURI(view);
		
		return view;
	}

}
