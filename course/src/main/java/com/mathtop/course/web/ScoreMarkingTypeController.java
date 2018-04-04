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

import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.ScoreMarkingType;
import com.mathtop.course.service.ScoreMarkingTypeService;

@Controller
@RequestMapping("/scoremarkingtype")
public class ScoreMarkingTypeController extends BaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private ScoreMarkingTypeService scoreMarkingTypeService;

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request, ScoreMarkingType type) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/scoremarkingtype/list");

		scoreMarkingTypeService.add(type);

		String toUrl = ("list.html");
		mav.setViewName("redirect:" + toUrl);

		return mav;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/delete-{t_score_marking_type_Id}", method = RequestMethod.GET)
	public ModelAndView DELETE(@PathVariable String t_score_marking_type_Id) {
		
		if (t_score_marking_type_Id != null)
			scoreMarkingTypeService.deleteById(t_score_marking_type_Id);

		ModelAndView mav = new ModelAndView();
		String toUrl = ("list.html");
		mav.setViewName("redirect:" + toUrl);
		return mav;
	}

	/**
	 * 查找
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/select-{name}", method = RequestMethod.GET)
	public ModelAndView select(@PathVariable int name) {
		ModelAndView view = new ModelAndView();
		view.setViewName("scoremarkingtype/list");

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
	public ModelAndView update(HttpServletRequest request, ScoreMarkingType item) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/scoremarkingtype/list");

		scoreMarkingTypeService.update(item.getId(), item.getName(), item.getNote());

		
		mav.setViewName("redirect:list.html");
		return mav;

	}

	/**
	 * 列出全部联系方式类型
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView ListAll(HttpServletRequest request,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		ModelAndView view = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = getPageSize(request, pageSize);

		Page<ScoreMarkingType> pagedCourseTeachingClassHomeworkScoreMarkingTypeData = scoreMarkingTypeService
				.getPage(pageNo, pageSize);

		view.addObject(PagedObjectConst.Paged_CourseTeachingClassHomeworkScoreMarkingTypeData,
				pagedCourseTeachingClassHomeworkScoreMarkingTypeData);

		view.setViewName("scoremarkingtype/list");
		return view;
	}

	// 得到所有学期
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	@ResponseBody
	public List<ScoreMarkingType> getAllScoreMarkingTypeList() {
		return scoreMarkingTypeService.getAll();
	}

}
