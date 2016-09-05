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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.cons.CourseMessage;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.cons.SelectedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.Department;
import com.mathtop.course.domain.Group;
import com.mathtop.course.domain.NaturalClass;
import com.mathtop.course.domain.School;
import com.mathtop.course.domain.Student;
import com.mathtop.course.domain.StudentViewData;
import com.mathtop.course.domain.User;
import com.mathtop.course.domain.UserBasicInfo;
import com.mathtop.course.domain.UserContactInfo;
import com.mathtop.course.domain.UserContactType;
import com.mathtop.course.exception.StudentExistException;
import com.mathtop.course.service.ContactTypeService;
import com.mathtop.course.service.DeleteService;
import com.mathtop.course.service.DepartmentNaturalClassService;
import com.mathtop.course.service.DepartmentService;
import com.mathtop.course.service.GroupService;
import com.mathtop.course.service.SchoolService;
import com.mathtop.course.service.StudentService;
import com.mathtop.course.service.UserGroupService;
import com.mathtop.course.service.UserService;
import com.mathtop.course.utility.DateTimeSql;

@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {

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
	private StudentService studentService;

	@Autowired
	DepartmentNaturalClassService departmentNaturalClassService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private UserGroupService usergroupService;
	
	@Autowired
	DeleteService deleteService;

	private final String strPageURI = "student";

	private void SetPageURI(ModelAndView mav) {
		mav.addObject("pagedURI", strPageURI);
	}

	/**
	 * 取得学生信息
	 * 
	 * @param request
	 *            上下文
	 * @param redirectAttributes
	 *            上下文
	 * @param t_user_id
	 *            学生t_user_id，注意不是t_student_id
	 * @return
	 */
	@RequestMapping(value = "/info-{t_user_id}")
	public ModelAndView info(HttpServletRequest request, RedirectAttributes redirectAttributes, @PathVariable String t_user_id) {

		ModelAndView mav = new ModelAndView();

		StudentViewData studentviewdata = studentService.getStudentViewByUserId(t_user_id);

		mav.addObject(SelectedObjectConst.Selected_StudentViewData, studentviewdata);

		Page<Group> pagedGroup = usergroupService.getGroupPageByt_user_id(t_user_id);
		mav.addObject(PagedObjectConst.Paged_Group, pagedGroup);

		// 得到联系方式类型
		Page<UserContactType> pagedContactType = contacttypeService.getPage(1, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_ContactType, pagedContactType);

		mav.setViewName(strPageURI + "/info");

		return mav;
	}

	/**
	 * 更改学生信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/infoupdate")
	public ModelAndView infoupdate(HttpServletRequest request) {

		String t_user_id = request.getParameter("t_user_id");

		String user_basic_info_birthday = request.getParameter("user_basic_info_birthday");
		String user_basic_info_sex = request.getParameter("user_basic_info_sex");

		String[] contacttypeId = request.getParameterValues("contacttypeId");
		String[] user_contact_value = request.getParameterValues("user_contact_value");

		studentService.UpdateStudentInfo(t_user_id, user_basic_info_birthday, user_basic_info_sex, contacttypeId, user_contact_value);

		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/" + strPageURI + "/info-" + t_user_id + ".html");
		return mav;

	}

	/**
	 * 添加学生
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add-{t_school_id}-{t_department_id}-{t_natural_class_id}")
	public ModelAndView add(HttpServletRequest request, RedirectAttributes redirectAttributes, @PathVariable String t_school_id,
			@PathVariable String t_department_id, @PathVariable String t_natural_class_id) {

		if (t_school_id == null) {
			List<School> schools = schoolService.getAll();
			if (schools.size() > 0) {
				t_school_id = schools.get(0).getId();
			}

			if (t_school_id != null && t_department_id == null) {

				List<Department> departments = departmentService.getAll(t_school_id);
				if (departments.size() > 0) {
					t_department_id = departments.get(0).getId();
				}

				if (t_department_id != null && t_natural_class_id == null) {

					List<NaturalClass> naturalclasss = departmentNaturalClassService.getNaturalClassByt_department_id(t_department_id);
					if (naturalclasss.size() > 0) {
						t_natural_class_id = naturalclasss.get(0).getId();
					}
				}
			}
		}

		ModelAndView mav = new ModelAndView();

		if (t_school_id == null || t_department_id == null || t_natural_class_id == null) {

			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "不存在自然班，请先添加自然班才能够添加学生.");
			mav.setViewName("redirect:/" + strPageURI + "/list.html");
			return mav;
		}

		// 学院
		Page<School> pagedSchool = schoolService.getPage(1, CommonConstant.PAGE_SIZE);
		mav.addObject(PagedObjectConst.Paged_School, pagedSchool);

		SetPage(mav, t_school_id, t_department_id, t_natural_class_id, 1);

		// 组
		Page<Group> pagedGroup = groupService.getPage(1, CommonConstant.PAGE_SIZE);
		mav.addObject(PagedObjectConst.Paged_Group, pagedGroup);

		// 得到联系方式类型
		Page<UserContactType> pagedContactType = contacttypeService.getPage(1, CommonConstant.PAGE_SIZE);
		mav.addObject(PagedObjectConst.Paged_ContactType, pagedContactType);

		mav.setViewName(strPageURI + "/add");

		return mav;
	}

	/**
	 * 从excel添加学生
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addfromexcel")
	public ModelAndView addfromexcel(HttpServletRequest request, RedirectAttributes redirectAttributes) {

		ModelAndView mav = new ModelAndView();

		
		// 组
		Page<Group> pagedGroup = groupService.getPage(1, CommonConstant.PAGE_SIZE);
		mav.addObject(PagedObjectConst.Paged_Group, pagedGroup);

		mav.setViewName(strPageURI + "/addfromexcel");

		return mav;
	}

	/**
	 * 从excel添加学生
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/uploadexcel")
	public ModelAndView uploadexcel(HttpServletRequest request, @RequestParam("file") MultipartFile file) {

		String[] groupId = request.getParameterValues("groupId");
		

		ModelAndView view = new ModelAndView();
	

		view.setViewName("redirect:/" + strPageURI + "/list.html");		

		try {
			studentService.UploadFromExcel(groupId, file);
		} catch (StudentExistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			view.addObject(CourseMessage.Message_errorMsg, "发生了错误，学生已经存在");
			view.setViewName("forward:/student/list.html");
		} catch (Exception e) {
			view.addObject(CourseMessage.Message_errorMsg, "发生了错误");
			view.setViewName("forward:/student/list.html");
			e.printStackTrace();
		}

		return view;
	}

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addstudent")
	public ModelAndView addstudent(HttpServletRequest request) {
		String t_school_id = request.getParameter("t_school_id");
		String t_department_id = request.getParameter("t_department_id");
		String t_natural_class_id = request.getParameter("t_natural_class_id");
		String user_password = request.getParameter("user_password");
		String student_num = request.getParameter("student_num");
		String userBasicInfoName = request.getParameter("userBasicInfoName");
		String user_basic_info_birthday = request.getParameter("user_basic_info_birthday");
		String user_basic_info_sex = request.getParameter("user_basic_info_sex");
		String[] contacttypeId = request.getParameterValues("contacttypeId");
		String[] user_contact_value = request.getParameterValues("user_contact_value");
		String[] groupId = request.getParameterValues("groupId");

		studentService.AddStudent(t_natural_class_id, user_password, student_num, userBasicInfoName, user_basic_info_birthday,
				user_basic_info_sex, contacttypeId, user_contact_value, groupId);

		ModelAndView mav = new ModelAndView();
		// SetPage(mav, t_school_id, 1);

		mav.setViewName("redirect:/" + strPageURI + "/list.html?t_school_id=" + t_school_id + "&t_department_id=" + t_department_id
				+ "&t_natural_class_id=" + t_natural_class_id);
		return mav;

	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/delete-{t_school_id}-{t_department_id}-{t_natural_class_id}-{t_student_id}-{pageNo}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, @PathVariable String t_school_id, @PathVariable String t_department_id,
			@PathVariable String t_natural_class_id, @PathVariable String t_student_id, @PathVariable Integer pageNo) {

		ModelAndView mav = new ModelAndView();

		mav.addObject(SelectedObjectConst.Selected_School_ID, t_school_id);
		mav.addObject(SelectedObjectConst.Selected_Department_ID, t_department_id);
		mav.addObject(SelectedObjectConst.Selected_Naturalclass_ID, t_natural_class_id);
		mav.addObject(SelectedObjectConst.Selected_PageNo, pageNo);

		// 学生
		StudentViewData selectedStudentViewData = studentService.getStudentViewByStudentId(t_student_id);
		mav.addObject(SelectedObjectConst.Selected_StudentViewData, selectedStudentViewData);

		Page<Group> pagedGroup = usergroupService.getGroupPageByt_user_id(selectedStudentViewData.getUser().getId());
		mav.addObject(PagedObjectConst.Paged_Group, pagedGroup);

		// 得到联系方式类型
		Page<UserContactType> pagedContactType = contacttypeService.getPage(1, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_ContactType, pagedContactType);

		mav.setViewName(strPageURI + "/delete");

		return mav;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/deletestudent-{t_school_id}-{t_department_id}-{t_natural_class_id}-{t_student_id}")
	public ModelAndView deletestudent(HttpServletRequest request,RedirectAttributes redirectAttributes,HttpSession session,
			@PathVariable String t_school_id, @PathVariable String t_department_id,
			@PathVariable String t_natural_class_id, @PathVariable String t_student_id) {

		ModelAndView mav = new ModelAndView();
		
		String user_verifycode=request.getParameter("user_verifycode");
		if (!(user_verifycode.equalsIgnoreCase(session.getAttribute("code").toString()))) {  //忽略验证码大小写
           
            
            redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "验证码不正确.");

			mav.setViewName("redirect:/student/list.html?t_school_id=" + t_school_id + "&t_department_id="
					+ t_department_id + "&t_natural_class_id-" + t_natural_class_id + "");
			
            return mav;
        }
		
		
		if (t_student_id != null)
			deleteService.deleteStudentById(request,t_student_id);

		
		mav.setViewName("redirect:/student/list.html?t_school_id=" + t_school_id + "&t_department_id="
				+ t_department_id + "&t_natural_class_id-" + t_natural_class_id + "");
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
		view.setViewName(strPageURI + "/list");

		if (departmentname != null && departmentname.length() > 0) {

			int pageNo = 1;
			Page<School> pagedSchool = schoolService.select(departmentname, pageNo, CommonConstant.PAGE_SIZE);

			view.addObject(PagedObjectConst.Paged_School, pagedSchool);
			view.setViewName(strPageURI + "/list");

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
	@RequestMapping(value = "/update-{t_school_id}-{t_department_id}-{t_natural_class_id}-{t_student_id}")
	public ModelAndView update(HttpServletRequest request, @PathVariable String t_school_id, @PathVariable String t_department_id,
			@PathVariable String t_natural_class_id, @PathVariable String t_student_id) {
		if (t_school_id == null) {

			List<School> schools = schoolService.getAll();
			if (schools.size() > 0) {
				t_school_id = schools.get(0).getId();
			}

			if (t_school_id != null && t_department_id == null) {

				List<Department> departments = departmentService.getAll(t_school_id);
				if (departments.size() > 0) {
					t_department_id = departments.get(0).getId();
				}

				if (t_department_id != null && t_natural_class_id == null) {

					List<NaturalClass> naturalclasss = departmentNaturalClassService.getNaturalClassByt_department_id(t_department_id);
					if (naturalclasss.size() > 0) {
						t_natural_class_id = naturalclasss.get(0).getId();
					}
				}
			}
		}

		ModelAndView mav = new ModelAndView();

		SetPage(mav, t_school_id, t_department_id, t_natural_class_id, 1);

		// 学生
		StudentViewData selectedStudentViewData = studentService.getStudentViewByStudentId(t_student_id);
		mav.addObject(SelectedObjectConst.Selected_StudentViewData, selectedStudentViewData);

		// 组
		Page<Group> pagedGroup = groupService.getPage(1, CommonConstant.PAGE_SIZE);
		mav.addObject(PagedObjectConst.Paged_Group, pagedGroup);

		// 组-用户
		Page<Group> pagedGroupSpecificUserId = usergroupService.getGroupPageByt_user_id(selectedStudentViewData.getUser().getId());
		mav.addObject(PagedObjectConst.Paged_GroupSpecificUserId, pagedGroupSpecificUserId);

		// 得到联系方式类型
		Page<UserContactType> pagedContactType = contacttypeService.getPage(1, CommonConstant.PAGE_SIZE);
		mav.addObject(PagedObjectConst.Paged_ContactType, pagedContactType);

		mav.setViewName(strPageURI + "/update");

		return mav;
	}

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updatestudent-{t_student_id}")
	public ModelAndView updatestudent(HttpServletRequest request, @PathVariable String t_student_id) {
		String t_school_id = request.getParameter("t_school_id");
		String t_department_id = request.getParameter("t_department_id");
		String t_natural_class_id = request.getParameter("t_natural_class_id");

		String student_num = request.getParameter("student_num");
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

		Student student = new Student();
		student.setStudentNum(student_num);
		student.setNaturalClassId(t_natural_class_id);
		student.setId(t_student_id);

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

			studentService.updateStudent(student, userbasicinfo, usercontactinfos, groupId);
		} else {
			studentService.updateStudent(student, userbasicinfo, null, groupId);
		}

		ModelAndView mav = new ModelAndView();
		// SetPage(mav, t_school_id, 1);

		mav.setViewName("redirect:/" + strPageURI + "/list.html?t_school_id=" + t_school_id + "&t_department_id=" + t_department_id
				+ "&t_natural_class_id=" + t_natural_class_id);
		return mav;

	}

	/**
	 * 修改学生密码
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/setstudentpwd-{t_school_id}-{t_department_id}-{t_natural_class_id}-{t_student_id}-{pageNo}")
	public ModelAndView updateStudentPasswor(HttpServletRequest request, @PathVariable String t_school_id,
			@PathVariable String t_department_id, @PathVariable String t_natural_class_id, @PathVariable String t_student_id,
			@PathVariable Integer pageNo) {

		ModelAndView mav = new ModelAndView();

		mav.addObject(SelectedObjectConst.Selected_School_ID, t_school_id);
		mav.addObject(SelectedObjectConst.Selected_Department_ID, t_department_id);
		mav.addObject(SelectedObjectConst.Selected_Naturalclass_ID, t_natural_class_id);
		mav.addObject(SelectedObjectConst.Selected_PageNo, pageNo);

		// 学生
		StudentViewData selectedStudentViewData = studentService.getStudentViewByStudentId(t_student_id);
		mav.addObject(SelectedObjectConst.Selected_StudentViewData, selectedStudentViewData);

		Page<Group> pagedGroup = usergroupService.getGroupPageByt_user_id(selectedStudentViewData.getUser().getId());
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
	@RequestMapping(value = "/updatestudentpwd")
	public ModelAndView updatestudentpwd(HttpServletRequest request) {

		String t_school_id = request.getParameter("t_school_id");
		String t_department_id = request.getParameter("t_department_id");
		;
		String t_natural_class_id = request.getParameter("t_natural_class_id");

		String new_user_password = request.getParameter("user_password");
		String t_user_id = request.getParameter("t_user_id");
		String pageNo = request.getParameter("pageNo");

		User user = new User();

		user.setUserPassword(new_user_password);
		user.EncoderPassword();

		userService.updateUserPassword(t_user_id, user.getUserPassword());

		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/student/listwithpath-" + t_school_id + "-" + t_department_id + "-" + t_natural_class_id + "-" + pageNo
				+ ".html");
		return mav;

	}

	/**
	 * 列出指定学院、系部、班级的学生
	 * 
	 * @param t_school_id
	 *            ：学院
	 * @param t_department_id
	 *            ：系部
	 * @param t_natural_class_id
	 *            ：自然班
	 * @param pageNo
	 *            ：分页
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam(value = "t_school_id", required = false) String t_school_id,
			@RequestParam(value = "t_department_id", required = false) String t_department_id,
			@RequestParam(value = "t_natural_class_id", required = false) String t_natural_class_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		ModelAndView mav = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;

		if (t_school_id == null) {

			List<School> schools = schoolService.getAll();
			if (schools.size() > 0) {
				t_school_id = schools.get(0).getId();
			}
		}

		if (t_school_id != null && t_department_id == null) {

			List<Department> departments = departmentService.getAll(t_school_id);
			if (departments.size() > 0) {
				t_department_id = departments.get(0).getId();
			}

			if (t_department_id != null && t_natural_class_id == null) {

				List<NaturalClass> ListNaturalclass = departmentNaturalClassService.getNaturalClassByt_department_id(t_department_id);

				if (ListNaturalclass.size() > 0) {
					t_natural_class_id = ListNaturalclass.get(0).getId();
				}
			}
		}

		SetPage(mav, t_school_id, t_department_id, t_natural_class_id, pageNo);

		Page<StudentViewData> pagedStudentViewData = studentService.getPageByt_natural_class_id(t_natural_class_id, pageNo,
				CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_StudentViewData, pagedStudentViewData);

		SetPageURI(mav);

		mav.setViewName(strPageURI + "/list");

		return mav;
	}

	@RequestMapping(value = "/listwithpath-{t_school_id}-{t_department_id}-{t_natural_class_id}-{pageNo}")
	public ModelAndView listWithPath(@PathVariable String t_school_id, @PathVariable String t_department_id,
			@PathVariable String t_natural_class_id, @PathVariable int pageNo) {

		ModelAndView mav = new ModelAndView();

		if (t_school_id == null) {

			List<School> schools = schoolService.getAll();
			if (schools.size() > 0) {
				t_school_id = schools.get(0).getId();
			}
		}

		if (t_school_id != null && t_department_id == null) {

			List<Department> departments = departmentService.getAll(t_school_id);
			if (departments.size() > 0) {
				t_department_id = departments.get(0).getId();
			}

			if (t_department_id != null && t_natural_class_id == null) {

				List<NaturalClass> ListNaturalclass = departmentNaturalClassService.getNaturalClassByt_department_id(t_department_id);

				if (ListNaturalclass.size() > 0) {
					t_natural_class_id = ListNaturalclass.get(0).getId();
				}
			}
		}

		SetPage(mav, t_school_id, t_department_id, t_natural_class_id, pageNo);

		Page<StudentViewData> pagedStudentViewData = studentService.getPageByt_natural_class_id(t_natural_class_id, pageNo,
				CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_StudentViewData, pagedStudentViewData);

		SetPageURI(mav);

		mav.setViewName(strPageURI + "/list");

		return mav;
	}

	private void SetPage(ModelAndView mav, String t_school_id, String t_department_id, String t_natural_class_id, Integer pageNo) {
		// 得到学院
		Page<School> pagedSchool = schoolService.getAllPage();

		mav.addObject(PagedObjectConst.Paged_School, pagedSchool);

		if (t_school_id != null) {
			Page<Department> pagedDepartment = departmentService.getPage(t_school_id);
			mav.addObject(PagedObjectConst.Paged_Department, pagedDepartment);
		}

		if (t_department_id != null) {
			Page<NaturalClass> pagedNaturalclass = departmentNaturalClassService.getNaturalClassPage(t_department_id, 0, 0);
			mav.addObject(PagedObjectConst.Paged_Naturalclass, pagedNaturalclass);
		}

		// 得到联系方式类型
		Page<UserContactType> pagedContactType = contacttypeService.getPage(pageNo, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_ContactType, pagedContactType);

		if (t_school_id == null) {

			List<School> schools = pagedSchool.getResult();
			if (schools.size() > 0) {
				t_school_id = schools.get(0).getId();
			}

		}

		mav.addObject(SelectedObjectConst.Selected_School_ID, t_school_id);
		mav.addObject(SelectedObjectConst.Selected_Department_ID, t_department_id);
		mav.addObject(SelectedObjectConst.Selected_Naturalclass_ID, t_natural_class_id);

	}

	/**
	 * 得到某学院某系部某班级的学生
	 * 
	 * @param request
	 * @param user
	 * @return
	 */

	@RequestMapping(value = "/selectbyt_natural_class_id-{t_natural_class_id}", method = RequestMethod.GET)
	@ResponseBody
	public List<StudentViewData> selectByt_natural_class_id(@PathVariable String t_natural_class_id) {

		return studentService.getStudentViewByt_natural_class_id(t_natural_class_id);

	}

}
