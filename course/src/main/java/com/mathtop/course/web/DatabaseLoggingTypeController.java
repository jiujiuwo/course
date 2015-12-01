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
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.ExaminationType;
import com.mathtop.course.domain.DatabaseLoggingType;
import com.mathtop.course.domain.UserContactType;
import com.mathtop.course.service.DatabaseLoggingTypeService;

@Controller
@RequestMapping("/databaseloggingtype")
public class DatabaseLoggingTypeController extends BaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private DatabaseLoggingTypeService databaseloggingtypeService;

	private final String strPageURI = "databaseloggingtype";
	

	private void SetPageURI(ModelAndView mav) {
		mav.addObject("pagedURI", strPageURI);
	}
	
	private void SetPageObject(ModelAndView mav,Page<DatabaseLoggingType> obj){
		mav.addObject(PagedObjectConst.Paged_DatabaseLoggingType, obj);
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

		DatabaseLoggingType dbdata = databaseloggingtypeService.getByName(ct.getName());

		if (dbdata == null) {
			return mav;
		} else if (dbdata.getName() != null) {
			mav.addObject(CourseMessage.Message_errorMsg, "联系类型名已经存在");

			Integer pageNo = 1;
			Page<DatabaseLoggingType> pagedDatabaseLoggingType = databaseloggingtypeService
					.getPage(pageNo, CommonConstant.PAGE_SIZE);

			SetPageObject(mav,pagedDatabaseLoggingType);
			
			String toUrl = ("list.html");
			mav.setViewName("redirect:" + toUrl);
			

		} else {
			databaseloggingtypeService.add(ct.getName(), ct.getNote());

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
			databaseloggingtypeService.deleteById(ctId);

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
		ModelAndView mav = new ModelAndView();
		SetPageURI(mav);
		mav.setViewName(strPageURI+"/list");

		if (ctIdname != null && ctIdname.length() > 0) {

			int pageNo = 1;
			Page<DatabaseLoggingType> pagedDatabaseLoggingType = databaseloggingtypeService
					.select(ctIdname, pageNo, CommonConstant.PAGE_SIZE);

			SetPageObject(mav,pagedDatabaseLoggingType);
			mav.setViewName(strPageURI+"/list");

		}
		return mav;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update")
	public ModelAndView update(HttpServletRequest request, ExaminationType item) {
		ModelAndView mav = new ModelAndView();
		SetPageURI(mav);
		mav.setViewName(strPageURI+"/list");

		DatabaseLoggingType dbitem = databaseloggingtypeService.getByName(item
				.getName());

		if (dbitem == null) {
			return mav;
		} else if (dbitem.getName() != null) {
			mav.addObject(CourseMessage.Message_errorMsg, "联系类型名已经存在");

			String toUrl = ("list");
			mav.setViewName("redirect:" + toUrl);
			return mav;

		} else {

			databaseloggingtypeService.update(item.getId(), item.getName(),
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
	public ModelAndView ListAll(
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView mav = new ModelAndView();
		SetPageURI(mav);

		pageNo = pageNo == null ? 1 : pageNo;
		
		Page<DatabaseLoggingType> pagedDatabaseLoggingType = databaseloggingtypeService
				.getPage(pageNo, CommonConstant.PAGE_SIZE);

		SetPageObject(mav,pagedDatabaseLoggingType);

		mav.setViewName(strPageURI+"/list");
		return mav;
	}

	// 得到所有学院
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	@ResponseBody
	public List<DatabaseLoggingType> getAllSchoolList(
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		pageNo = pageNo == null ? 1 : pageNo;
		Page<DatabaseLoggingType> pagedDatabaseLoggingType = databaseloggingtypeService
				.getPage(pageNo, CommonConstant.PAGE_SIZE);
		return pagedDatabaseLoggingType.getResult();
	}

}
