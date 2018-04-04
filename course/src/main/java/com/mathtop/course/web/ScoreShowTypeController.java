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
import com.mathtop.course.domain.ScoreShowType;
import com.mathtop.course.service.ScoreShowTypeService;

@Controller
@RequestMapping("/scoreshowtype")
public class ScoreShowTypeController extends BaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private ScoreShowTypeService scoreShowTypeService;

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request, ScoreShowType type) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/scoreshowtype/list");

		scoreShowTypeService.add(type);

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
			scoreShowTypeService.deleteById(t_score_marking_type_Id);

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
		view.setViewName("scoreshowtype/list");

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
	public ModelAndView update(HttpServletRequest request, ScoreShowType item) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/scoreshowtype/list");

		scoreShowTypeService.update(item.getId(), item.getName(), item.getNote());

		
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

		Page<ScoreShowType> pagedCourseTeachingClassHomeworkScoreShowTypeData = scoreShowTypeService
				.getPage(pageNo, pageSize);

		view.addObject(PagedObjectConst.Paged_CourseTeachingClassHomeworkScoreShowTypeData,
				pagedCourseTeachingClassHomeworkScoreShowTypeData);

		view.setViewName("scoreshowtype/list");
		return view;
	}

	// 得到所有学期
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	@ResponseBody
	public List<ScoreShowType> getAllScoreMarkingTypeList() {
		return scoreShowTypeService.getAll();
	}

}
