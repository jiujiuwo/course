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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.cons.SelectedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.Department;
import com.mathtop.course.domain.Group;
import com.mathtop.course.domain.School;
import com.mathtop.course.domain.Teacher;
import com.mathtop.course.domain.TeacherViewData;
import com.mathtop.course.domain.User;
import com.mathtop.course.domain.UserBasicInfo;
import com.mathtop.course.domain.UserContactInfo;
import com.mathtop.course.domain.UserContactType;
import com.mathtop.course.service.ContactTypeService;
import com.mathtop.course.service.DeleteService;
import com.mathtop.course.service.DepartmentService;
import com.mathtop.course.service.GroupService;
import com.mathtop.course.service.SchoolService;
import com.mathtop.course.service.TeacherService;
import com.mathtop.course.service.UserGroupService;
import com.mathtop.course.service.UserService;
import com.mathtop.course.utility.DateTimeSql;

@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController {

	/**
	 * 自动注入
	 */

	@Autowired
	private UserService userService;

	@Autowired
	private SchoolService schoolService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ContactTypeService contacttypeService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private UserGroupService usergroupService;

	@Autowired
	private DeleteService deleteService;

	private final String strPageURI = "teacher";

	@RequestMapping(value = "/info-{t_user_id}")
	public ModelAndView info(HttpServletRequest request, @PathVariable String t_user_id,
			RedirectAttributes redirectAttributes) {

		ModelAndView mav = new ModelAndView();

		TeacherViewData teacherviewdata = teacherService.getTeacherViewByt_user_id(t_user_id);

		mav.addObject(SelectedObjectConst.Selected_TeacherViewData, teacherviewdata);

		Page<Group> pagedGroup = usergroupService.getGroupPageByt_user_id(t_user_id);
		mav.addObject(PagedObjectConst.Paged_Group, pagedGroup);

		// 得到联系方式类型
		Page<UserContactType> pagedContactType = contacttypeService.getPage(1, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_ContactType, pagedContactType);

		mav.setViewName(strPageURI + "/info");

		return mav;
	}

	/**
	 * 更改教师信息
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/infoupdate")
	public ModelAndView infoupdate(HttpServletRequest request) {

		String t_user_id = request.getParameter("t_user_id");

		String user_basic_info_birthday = request.getParameter("user_basic_info_birthday");
		String user_basic_info_sex = request.getParameter("user_basic_info_sex");

		String[] contacttypeId = request.getParameterValues("contacttypeId");
		String[] user_contact_value = request.getParameterValues("user_contact_value");

		teacherService.updateTeacherInfo(t_user_id, user_basic_info_birthday, user_basic_info_sex, contacttypeId,
				user_contact_value);

		ModelAndView mav = new ModelAndView();
		// SetPage(mav, t_school_id, 1);

		mav.setViewName("redirect:/" + strPageURI + "/info-" + t_user_id + ".html");
		return mav;

	}

	/**
	 * 为特定学院添加教师
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add-{t_school_id}")
	public ModelAndView addTeacherForSchool(HttpServletRequest request, @PathVariable String t_school_id) {

		Page<School> pagedSchool = schoolService.getPage(1, CommonConstant.PAGE_SIZE);

		ModelAndView mav = new ModelAndView();
		mav.addObject(PagedObjectConst.Paged_School, pagedSchool);

		if (t_school_id == null) {
			List<School> schools = pagedSchool.getResult();
			if (schools.size() > 0)
				t_school_id = schools.get(0).getId();
		}

		SetPage(mav, t_school_id, 1);

		// 组

		Page<Group> pagedGroup = groupService.getPage(1, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_Group, pagedGroup);

		// 得到联系方式类型
		Page<UserContactType> pagedContactType = contacttypeService.getPage(1, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_ContactType, pagedContactType);

		mav.setViewName("/teacher/add");

		return mav;
	}

	/**
	 * 为学院添加教师
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView addTeacher(HttpServletRequest request) {

		Page<School> pagedSchool = schoolService.getPage(1, CommonConstant.PAGE_SIZE);

		ModelAndView mav = new ModelAndView();
		mav.addObject(PagedObjectConst.Paged_School, pagedSchool);

		String t_school_id = null;

		List<School> schools = pagedSchool.getResult();
		if (schools.size() > 0)
			t_school_id = schools.get(0).getId();

		SetPage(mav, t_school_id, 1);

		// 组

		Page<Group> pagedGroup = groupService.getPage(1, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_Group, pagedGroup);

		// 得到联系方式类型
		Page<UserContactType> pagedContactType = contacttypeService.getPage(1, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_ContactType, pagedContactType);

		mav.setViewName("/teacher/add");

		return mav;
	}

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addteacher")
	public ModelAndView addteacher(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String t_school_id = request.getParameter("t_school_id");
		String t_department_id = request.getParameter("t_department_id");
		String user_password = request.getParameter("user_password");
		String teacherNum = request.getParameter("teacherNum");
		String userBasicInfoName = request.getParameter("userBasicInfoName");
		String user_basic_info_birthday = request.getParameter("user_basic_info_birthday");
		String user_basic_info_sex = request.getParameter("user_basic_info_sex");
		String[] contacttypeId = request.getParameterValues("contacttypeId");
		String[] user_contact_value = request.getParameterValues("user_contact_value");

		String[] groupId = request.getParameterValues("groupId");

		int contactCount = 0;
		if (contacttypeId != null) {
			contactCount = contacttypeId.length;

		}

		Department department = departmentService.getByID(t_department_id);

		// user基本信息
		User user = new User();
		user.setUserName(teacherNum);
		user.setUserPassword(user_password);

		Teacher teacher = new Teacher();
		teacher.setTeacherNum(teacherNum);

		UserBasicInfo userbasicinfo = new UserBasicInfo();
		userbasicinfo.setUserBasicInfoName(userBasicInfoName);
		userbasicinfo.setUserBasicInfoBirthday(DateTimeSql.GetDate(user_basic_info_birthday));
		userbasicinfo.setUserBasicInfoSex(Integer.parseInt(user_basic_info_sex));

		if (contactCount > 0) {

			UserContactInfo[] usercontactinfos = new UserContactInfo[contactCount];

			for (int i = 0; i < contactCount; i++) {

				usercontactinfos[i] = new UserContactInfo();

				usercontactinfos[i].setUserContactTypeId(contacttypeId[i]);
				usercontactinfos[i].setUserContactValue(user_contact_value[i]);
			}

			teacherService.addTeacher(user, department, teacher, userbasicinfo, usercontactinfos, groupId);
		} else {
			teacherService.addTeacher(user, department, teacher, userbasicinfo, null, groupId);
		}

		ModelAndView mav = new ModelAndView();
		// SetPage(mav, t_school_id, 1);

		mav.setViewName("redirect:/teacher/list.html?t_school_id=" + t_school_id);
		return mav;

	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/delete-{t_user_id}", method = RequestMethod.GET)
	public ModelAndView DELETE(HttpServletRequest request, @PathVariable String t_user_id) {

		if (t_user_id != null)
			deleteService.deleteByUserId(request, t_user_id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/teacher/list.html");
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
		view.setViewName("teacher/list");

		if (departmentname != null && departmentname.length() > 0) {

			int pageNo = 1;
			Page<School> pagedSchool = schoolService.select(departmentname, pageNo, CommonConstant.PAGE_SIZE);

			view.addObject("pagedSchool", pagedSchool);
			view.setViewName("teacher/list");

		}
		return view;
	}

	/**
	 * 得到指定学院下的
	 * 
	 * @param request
	 * @param user
	 * @return
	 */

	@RequestMapping(value = "/selectbyschool-{t_school_id}", method = RequestMethod.GET)
	@ResponseBody
	public List<TeacherViewData> selectByt_school_id(HttpServletRequest request, @PathVariable String t_school_id) {

		return teacherService.getTeacherViewByt_school_id(t_school_id);

	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update-{t_user_id}")
	public ModelAndView update(HttpServletRequest request, @PathVariable String t_user_id) {
		ModelAndView mav = new ModelAndView();

		TeacherViewData selectedTeacherViewData = teacherService.getTeacherViewByt_user_id(t_user_id);
		mav.addObject(SelectedObjectConst.Selected_TeacherViewData, selectedTeacherViewData);

		String t_school_id = selectedTeacherViewData.getSchool().getId();

		SetPage(mav, t_school_id, 1);

		mav.addObject(SelectedObjectConst.Selected_Department_ID, selectedTeacherViewData.getDepartment().getId());

		// 组
		Page<Group> pagedGroup = groupService.getPage(1, CommonConstant.PAGE_SIZE);
		mav.addObject(PagedObjectConst.Paged_Group, pagedGroup);

		// 组-用户
		Page<Group> pagedGroupSpecificUserId = usergroupService.getGroupPageByt_user_id(t_user_id);
		mav.addObject(PagedObjectConst.Paged_GroupSpecificUserId, pagedGroupSpecificUserId);

		// 得到联系方式类型
		Page<UserContactType> pagedContactType = contacttypeService.getPage(1, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_ContactType, pagedContactType);

		mav.setViewName("/teacher/update");
		return mav;

	}

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updateteacher-{t_user_id}")
	public ModelAndView updateteacher(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_user_id) {
		String t_school_id = request.getParameter("t_school_id");
		// String t_department_id = request.getParameter("t_department_id");
		// String user_password = request.getParameter("user_password");
		String teacherNum = request.getParameter("teacherNum");
		String userBasicInfoName = request.getParameter("userBasicInfoName");
		String user_basic_info_birthday = request.getParameter("user_basic_info_birthday");
		String user_basic_info_sex = request.getParameter("user_basic_info_sex");
		String[] contacttypeId = request.getParameterValues("contacttypeId");
		String[] user_contact_value = request.getParameterValues("user_contact_value");

		String[] groupId = request.getParameterValues("groupId");

		int contactCount = 0;
		if (contacttypeId != null) {
			contactCount = contacttypeId.length;

		}

		// user基本信息
		User user = new User();
		user.setUserName(teacherNum);
		user.setId(t_user_id);
		// user.setUser_password(user_password);

		Teacher teacher = new Teacher();
		teacher.setTeacherNum(teacherNum);

		UserBasicInfo userbasicinfo = new UserBasicInfo();
		userbasicinfo.setUserBasicInfoName(userBasicInfoName);
		userbasicinfo.setUserBasicInfoBirthday(DateTimeSql.GetDate(user_basic_info_birthday));
		userbasicinfo.setUserBasicInfoSex(Integer.parseInt(user_basic_info_sex));

		if (contactCount > 0) {

			UserContactInfo[] usercontactinfos = new UserContactInfo[contactCount];

			for (int i = 0; i < contactCount; i++) {

				usercontactinfos[i] = new UserContactInfo();

				usercontactinfos[i].setUserContactTypeId(contacttypeId[i]);
				usercontactinfos[i].setUserContactValue(user_contact_value[i]);
			}

			teacherService.updateTeacher(user, teacher, userbasicinfo, usercontactinfos, groupId);
		} else {
			teacherService.updateTeacher(user, teacher, userbasicinfo, null, groupId);
		}

		ModelAndView mav = new ModelAndView();
		// SetPage(mav, t_school_id, 1);

		mav.setViewName("redirect:/teacher/list.html?t_school_id=" + t_school_id);
		return mav;

	}

	/**
	 * 修改教师密码
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/setteacherpwd-{t_school_id}-{t_user_id}-{pageNo}")
	public ModelAndView setteacherpwd(HttpServletRequest request, @PathVariable String t_school_id,
			@PathVariable String t_user_id, @PathVariable Integer pageNo) {

		ModelAndView mav = new ModelAndView();

		mav.addObject(SelectedObjectConst.Selected_School_ID, t_school_id);
		mav.addObject(SelectedObjectConst.Selected_PageNo, pageNo);

		// 教师
		TeacherViewData selectedTeacherViewData = teacherService.getTeacherViewByt_user_id(t_user_id);
		mav.addObject(SelectedObjectConst.Selected_TeacherViewData, selectedTeacherViewData);

		Page<Group> pagedGroup = usergroupService.getGroupPageByt_user_id(selectedTeacherViewData.getUser().getId());
		mav.addObject(PagedObjectConst.Paged_Group, pagedGroup);

		// 得到联系方式类型
		Page<UserContactType> pagedContactType = contacttypeService.getPage(1, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_ContactType, pagedContactType);

		mav.setViewName(strPageURI + "/updatepwd");

		return mav;
	}

	/**
	 * 修改学生密码
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updateteacherpwd")
	public ModelAndView updateteacherpwd(HttpServletRequest request) {

		String t_school_id = request.getParameter("t_school_id");

		String new_user_password = request.getParameter("user_password");
		String t_user_id = request.getParameter("t_user_id");
		String pageNo = request.getParameter("pageNo");

		User user = new User();

		user.setUserPassword(new_user_password);
		user.EncoderPassword();

		userService.updateUserPassword(t_user_id, user.getUserPassword());

		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/teacher/listwithpath-" + t_school_id + "-" + pageNo + ".html");
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
	public ModelAndView ListAll(@RequestParam(value = "t_school_id", required = false) String t_school_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		ModelAndView mav = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;

		if (t_school_id == null) {

			Page<School> pagedSchool = schoolService.getPage(pageNo, CommonConstant.PAGE_SIZE);

			List<School> schools = pagedSchool.getResult();
			if (schools.size() > 0) {
				t_school_id = schools.get(0).getId();
			}

		}

		SetPage(mav, t_school_id, pageNo);

		Page<TeacherViewData> pagedTeacherViewData = teacherService.getPage(t_school_id, pageNo,
				CommonConstant.PAGE_SIZE);
		mav.addObject(PagedObjectConst.Paged_TeacherViewData, pagedTeacherViewData);

		mav.setViewName("teacher/list");

		return mav;
	}

	@RequestMapping(value = "/listwithpath-{t_school_id}-{pageNo}")
	public ModelAndView listWithPath(@PathVariable String t_school_id, @PathVariable int pageNo) {

		ModelAndView mav = new ModelAndView();

		if (t_school_id == null) {

			List<School> schools = schoolService.getAll();
			if (schools.size() > 0) {
				t_school_id = schools.get(0).getId();
			}
		}

		SetPage(mav, t_school_id, pageNo);

		Page<TeacherViewData> pagedStudentViewData = teacherService.getPage(t_school_id, pageNo,
				CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_StudentViewData, pagedStudentViewData);

		mav.setViewName(strPageURI + "/list");

		return mav;
	}

	private void SetPage(ModelAndView mav, String t_school_id, Integer pageNo) {

		// 得到学院
		Page<School> pagedSchool = schoolService.getAllPage();

		mav.addObject(PagedObjectConst.Paged_School, pagedSchool);

		if (t_school_id != null) {
			Page<Department> pagedDepartment = departmentService.getPage(t_school_id);

			mav.addObject(PagedObjectConst.Paged_Department, pagedDepartment);
		}

		// 得到联系方式类型
		Page<UserContactType> pagedContactType = contacttypeService.getAllPage();

		mav.addObject(PagedObjectConst.Paged_ContactType, pagedContactType);

		if (t_school_id == null) {

			List<School> schools = pagedSchool.getResult();
			if (schools.size() > 0) {
				t_school_id = schools.get(0).getId();
			}

		}

		mav.addObject(SelectedObjectConst.Selected_School_ID, t_school_id);

	}

}
