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
import com.mathtop.course.domain.ExaminationConstitutionType;
import com.mathtop.course.domain.Simple;
import com.mathtop.course.domain.UserContactType;
import com.mathtop.course.service.ExaminationConstitutionTypeService;

@Controller
@RequestMapping("/examinationconstitutiontype")
public class ExaminationConstitutionTypeController extends BaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private ExaminationConstitutionTypeService examinationconstitutiontypeService;

	private final String strPageURI = "examinationconstitutiontype";

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
	public ModelAndView add(HttpServletRequest request, UserContactType ct) {

		ModelAndView mav = new ModelAndView();
		SetPageURI(mav);
		mav.setViewName(strPageURI+"/list");

		Simple dbdata = examinationconstitutiontypeService.getByName(ct.getName());

		if (dbdata == null) {
			return mav;
		} else if (dbdata.getName() != null) {
			mav.addObject(CourseMessage.Message_errorMsg, "联系类型名已经存在");

			Integer pageNo = 1;
			Page<ExaminationConstitutionType> pagedExaminationType = examinationconstitutiontypeService
					.getPage(pageNo, CommonConstant.PAGE_SIZE);

			mav.addObject("pagedExaminationType", pagedExaminationType);
			String toUrl = ("list.html");
			mav.setViewName("redirect:" + toUrl);
			

		} else {
			examinationconstitutiontypeService.add(ct.getName(), ct.getNote());

			String toUrl = ("list.html");
			mav.setViewName("redirect:" + toUrl);

		}
		return mav;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/DELETE-{ctId}", method = RequestMethod.GET)
	public ModelAndView DELETE(@PathVariable String ctId) {
		if (ctId != null)
			examinationconstitutiontypeService.deleteById(ctId);

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
	@RequestMapping(value = "/select-{ctIdname}", method = RequestMethod.GET)
	public ModelAndView select(@PathVariable String ctIdname) {
		ModelAndView view = new ModelAndView();
		SetPageURI(view);
		view.setViewName(strPageURI+"/list");

		if (ctIdname != null && ctIdname.length() > 0) {

			int pageNo = 1;
			Page<ExaminationConstitutionType> pagedExaminationConstitutionType = examinationconstitutiontypeService
					.select(ctIdname, pageNo, CommonConstant.PAGE_SIZE);

			view.addObject("pagedExaminationConstitutionType", pagedExaminationConstitutionType);
			view.setViewName(strPageURI+"/list");

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
	public ModelAndView update(HttpServletRequest request, ExaminationConstitutionType item) {
		ModelAndView mav = new ModelAndView();
		SetPageURI(mav);
		mav.setViewName(strPageURI+"/list");

		ExaminationConstitutionType dbitem = examinationconstitutiontypeService.getByName(item
				.getName());

		if (dbitem == null) {
			return mav;
		} else if (dbitem.getName() != null) {
			mav.addObject(CourseMessage.Message_errorMsg, "联系类型名已经存在");

			String toUrl = ("list");
			mav.setViewName("redirect:" + toUrl);
			return mav;

		} else {

			examinationconstitutiontypeService.update(item.getId(), item.getName(),
					item.getNote());

			String toUrl = ("list");
			mav.setViewName("redirect:" + toUrl);
			return mav;

		}
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
		SetPageURI(view);

		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = getPageSize(request, pageSize);
		
		Page<ExaminationConstitutionType> pagedExaminationConstitutionType = examinationconstitutiontypeService
				.getPage(pageNo, CommonConstant.PAGE_SIZE);

		view.addObject("pagedExaminationConstitutionType", pagedExaminationConstitutionType);

		view.setViewName(strPageURI+"/list");
		return view;
	}

	// 得到所有学院
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	@ResponseBody
	public List<ExaminationConstitutionType> getAllSchoolList() {

		return examinationconstitutiontypeService.getAll();
				
	}

}
