package com.mathtop.course.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.DepartmentDao;
import com.mathtop.course.dao.DepartmentNaturalClassDao;
import com.mathtop.course.dao.DepartmentTeacherDao;
import com.mathtop.course.dao.NaturalClassDao;
import com.mathtop.course.dao.NaturalClassStudentDao;
import com.mathtop.course.dao.SchoolDao;
import com.mathtop.course.dao.SchoolDepartmentDao;
import com.mathtop.course.dao.StudentDao;
import com.mathtop.course.dao.TeacherDao;
import com.mathtop.course.dao.CourseTeachingClassStudentDao;
import com.mathtop.course.dao.CourseDepartmentDao;
import com.mathtop.course.dao.CourseTeachingClassTeacherDao;
import com.mathtop.course.dao.UserContactTypeDao;
import com.mathtop.course.domain.CourseTeachingClassStudent;
import com.mathtop.course.domain.CourseTeachingClassTeacher;
import com.mathtop.course.domain.Student;
import com.mathtop.course.domain.Teacher;

/**
 * 删除服务，需要注意数据之间存在关联，为了保证数据完整，必须注意删除次序
 */

@Service
public class DeleteService {

	@Autowired
	AttendanceService attendanceService;

	@Autowired
	AttendanceStudentService attendanceStudentService;

	@Autowired
	AttendanceTypeService attendanceTypeService;

	@Autowired
	SchoolDao schoolDao;

	@Autowired
	DepartmentDao departmentDao;

	@Autowired
	SchoolDepartmentDao schoolDepartmentDao;

	@Autowired
	NaturalClassDao naturalClassDao;

	@Autowired
	DepartmentNaturalClassDao naturalClassDepartmentDao;

	@Autowired
	DepartmentTeacherDao departmentTeacherDao;

	@Autowired
	NaturalClassStudentDao naturalClassStudentDao;

	@Autowired
	UserService userService;
	
	@Autowired
	UserConfigService userConfigService;

	@Autowired
	LoginService loginService;

	@Autowired
	StudentDao studentDao;

	@Autowired
	TeacherDao teacherDao;

	@Autowired
	CourseTeachingClassTeacherDao courseTeachingClassTeacherDao;

	@Autowired
	CourseTeachingClassStudentDao courseTeachingClassStudentDao;

	@Autowired
	UserBasicInfoService userBasicInfoService;

	@Autowired
	UserContactInfoService userContactInfoService;

	@Autowired
	UserContactTypeDao userContactTypeDao;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	CourseDepartmentDao courseDepartmentDao;
	
	@Autowired
	CourseTeachingClassService CourseTeachingClassService;

	@Autowired
	CourseTeachingClassHomeworkService courseTeachingClassHomeworkService;

	@Autowired
	CourseTeachingClassHomeworkSubmitService courseTeachingClassHomeworkSubmitService;

	@Autowired
	CourseTeachingClassForumTopicService courseTeachingClassForumTopicService;

	@Autowired
	CourseTeachingClassForumReplyService courseTeachingClassForumReplyService;

	@Autowired
	MailBoxReceivedService mailBoxReceivedService;

	@Autowired
	MailBoxSendService mailBoxSendService;
	
	@Autowired
	TeachingTypeService teachingTypeService;
	
	@Autowired
	UserGroupService userGroupService;
	
	@Autowired
	CourseTeachingClassStudentService  teachingClassStudentService;

	/**
	 * 删除学院
	 */
	public void deleteSchoolById(HttpServletRequest request, String t_school_id) {
		// .删除本学院所有的系部
		List<String> t_department_ids = schoolDepartmentDao.getDepartmentIdBySchoolId(t_school_id);
		for (String t_department_id : t_department_ids) {
			deleteDepartmentById(request, t_department_id);
		}

		// 删除自己
		schoolDao.deleteById(t_school_id);

	}

	/**
	 * 删除系部
	 */
	public void deleteDepartmentById(HttpServletRequest request, String t_department_id) {

		// 删除所有教师
		List<String> t_teacher_ids = departmentTeacherDao.getByTeacherIdByt_department_id(t_department_id);
		for (String t_teacher_id : t_teacher_ids) {
			deleteTeacherById(request, t_teacher_id);
		}

		// 删除所有的自然班
		List<String> t_natural_class_ids = naturalClassDepartmentDao.getNaturalClassIdByDepartmentId(t_department_id);
		for (String t_natural_class_id : t_natural_class_ids) {
			deleteNaturalClassById(request, t_natural_class_id);
		}

		// 删除学院-系部关系
		schoolDepartmentDao.deleteByDepartmentId(t_department_id);
		
		//课程-系部
		courseDepartmentDao.deleteByDepartmentId(t_department_id);

		// 删除自己
		departmentDao.deleteById(t_department_id);
	}

	/**
	 * 删除自然班
	 */
	public void deleteNaturalClassById(HttpServletRequest request, String t_natural_class_id) {
		// 删除所有学生
		List<String> t_student_ids = naturalClassStudentDao.getStudentIdByNaturalClassId(t_natural_class_id);
		for (String t_student_id : t_student_ids) {
			deleteStudentById(request, t_student_id);
		}
		
		//删除自然班-学生
		naturalClassStudentDao.deleteByNaturalClassId(t_natural_class_id);
		
		//删除班级-系部关系
		naturalClassDepartmentDao.deleteByNaturalclassId(t_natural_class_id);

		// 删除自己
		naturalClassDao.deleteById(t_natural_class_id);
	}

	/**
	 * 删除论坛系统
	 */
	private void deleteForumTopicAndReplyByUserId(HttpServletRequest request, String t_user_id) {
//		courseTeachingClassForumReplyService.deleteByUserId(request, t_user_id);
		courseTeachingClassForumTopicService.deleteByUserId(request, t_user_id);		
	}
	
	/**
	 * 删除论坛系统
	 */
	private void deleteForumTopicAndReplyByUserId(HttpServletRequest request, String t_course_teaching_class_id,String t_user_id) {
//		courseTeachingClassForumReplyService.deleteByCourseTeachingClassIDAndUserID(request, t_course_teaching_class_id,t_user_id);
		courseTeachingClassForumTopicService.deleteByCourseTeachingClassIDAndUserID(request, t_course_teaching_class_id,t_user_id);		
	}

	/**
	 * 将用户t_user_id从邮件系统中彻底删除
	 */
	private void deleteUserInMail(HttpServletRequest request, String t_user_id) {
		mailBoxReceivedService.deleteByUserId(request, t_user_id);
		mailBoxSendService.deleteByUserId(request, t_user_id);
	}

	/**
	 * 将用户登录、基本信息等彻底删除
	 */
	private void deleteUserBasic(String t_user_id) {
		// 删除用户基本信息
		userBasicInfoService.deleteByUserId(t_user_id);

		// 联系方式
		userContactInfoService.deleteByUserId(t_user_id);

		// 删除登录信息
		loginService.deleteByUserId(t_user_id);
		
		//删除用户配置
		userConfigService.deleteByUserId(t_user_id);

		// 删除用户
		userService.deleteByUserId(t_user_id);
	}

	/**
	 * 根据t_user_id删除用户,将该用户从系统中彻底删除
	 */
	public void deleteByUserId(HttpServletRequest request, String t_user_id) {
		// 学生或教师？
		Student stu = studentDao.getStudentByUserId(t_user_id);
		if (stu != null) {
			deleteStudentById(request, stu.getId());
		} else {
			Teacher t = teacherDao.getTeacherByUserId(t_user_id);
			if (t != null) {
				deleteTeacherById(request, t.getId());
			}
		}		
	}

	/**
	 * 删除教师
	 */
	public void deleteTeacherById(HttpServletRequest request, String t_teacher_id) {

		Teacher teacher = teacherDao.getTeacherByID(t_teacher_id);
		if (teacher == null)
			return;

		String t_user_id = teacher.getUserId();

		// 删除提交作业系统
		courseTeachingClassHomeworkService.deleteByTeacherId(request, t_teacher_id);
		courseTeachingClassHomeworkService.deleteDelayedByTeacherId(request, t_teacher_id);

		// 删除论坛系统
		deleteForumTopicAndReplyByUserId(request, t_user_id);

		// 删除教师的课堂考勤
		attendanceService.deleteByTeacherId(t_teacher_id);

		// 删除教学班-教师
		List<CourseTeachingClassTeacher> listCourseTeachingClassTeacher = courseTeachingClassTeacherDao
				.getTeachingClassTeacherByTeacherId(t_teacher_id);
		for (CourseTeachingClassTeacher c : listCourseTeachingClassTeacher) {
			deleteCourseTeachingClassTeacher(c.getId());
		}
		
		//删除教师-部门关系表
		departmentTeacherDao.deleteByTeacherId(t_teacher_id);
		
		//删除教师-授课关系表
		courseTeachingClassTeacherDao.deleteByTeacherId(t_teacher_id);

		
		// 删除教师
		teacherDao.deleteById(t_teacher_id);

		// 删除邮件
		deleteUserInMail(request, t_user_id);
		
		//权限
		userGroupService.deleteByUserId( t_user_id);

		// 删除用户基本信息
		deleteUserBasic(t_user_id);

	}

	/**
	 * 删除学生
	 */
	public void deleteStudentById(HttpServletRequest request, String t_student_id) {

		Student stu = studentDao.getStudentByID(t_student_id);
		if (stu == null)
			return;

		String t_user_id = stu.getUserId();

		// 删除提交作业系统
		courseTeachingClassHomeworkSubmitService.deleteByStudentID(request, t_student_id);

		// 删除论坛系统
		deleteForumTopicAndReplyByUserId(request, t_user_id);

		// 删除学生的考勤情况
		attendanceStudentService.deleteByStudentId(t_student_id);

		// 删除教学班-学生
		List<CourseTeachingClassStudent> listCourseTeachingClassStudent = courseTeachingClassStudentDao
				.getTeachingClassStudentByStudentId(t_student_id);
		for (CourseTeachingClassStudent c : listCourseTeachingClassStudent) {
			deleteStudentFromCourseTeachingClass(request,c.getId(),t_student_id);
		}
		//从教学班中删除学生
		teachingClassStudentService.deleteByStudentId(request, t_student_id);
		
		//删除自然班-学生
		naturalClassStudentDao.deleteByStudentId(t_student_id);

		// 删除学生
		studentDao.deleteById(t_student_id);

		// 删除邮件
		deleteUserInMail(request, t_user_id);
		
		//权限
		userGroupService.deleteByUserId( t_user_id);

		// 删除用户基本信息
		deleteUserBasic(t_user_id);
	}

	/**
	 * 删除用户基本信息
	 */
	public void deleteUserBasicInfoByID(String t_user_basic_info_id) {

	}

	/**
	 * 删除联系方式
	 */
	public void deleteUserContactInfoById(String t_user_contact_info_id) {

	}

	/**
	 * 删除联系类型
	 */
	public void deleteUserContactTypeById(String t_user_contact_type_id) {

	}

	/**
	 * 删除教学班级-教师
	 */
	public void deleteCourseTeachingClassTeacher(String t_course_teaching_class_teacher_id) {

	}

	

	/**
	 * 删除课程
	 */
	public void deleteCourseById(HttpServletRequest request, String t_course_id) {
		courseService.deleteById(request, t_course_id);		
		
	}

	/**
	 * 删除教学班
	 */
	public void deleteTeachingClassById(HttpServletRequest request, String t_teaching_class_id) {

	}

	/**
	 * 删除课程-教学班
	 */
	public void deleteCourseTeachingClassById(HttpServletRequest request, String t_course_teaching_class_id) {

	}
	
	

	/**
	 * 从课程教学班中删除一个学生
	 */
	public void deleteStudentFromCourseTeachingClass(HttpServletRequest request, String t_course_teaching_class_id, String t_student_id) {
		Student stu = studentDao.getStudentByID(t_student_id);
		if (stu == null)
			return;
		
	

		String t_user_id = stu.getUserId();

		// 1.删除考勤
		attendanceStudentService.deleteByCourseTeachingClassIdAndStudentId(t_course_teaching_class_id,t_student_id);

		// 2.删除考试等

		// 3.删除论坛
		deleteForumTopicAndReplyByUserId(request, t_course_teaching_class_id,t_user_id);

		// 3.删除作业等
		courseTeachingClassHomeworkSubmitService.deleteByCourseTeachingClassIdAndStudentID(request, t_course_teaching_class_id, t_student_id);
		
		//4.从教学班级中删除该学生
		CourseTeachingClassService.deleteByCourseTeachingClassIdAndStudentId(request, t_course_teaching_class_id,t_student_id);
		

	}

	/**
	 * 删除考勤类型
	 */
	public void deleteAttanceTypeById(String t_attendance_type_id) {
		attendanceTypeService.deleteById(t_attendance_type_id);
	}
	
	public void deleteTeachingTypeById(HttpServletRequest request,  String t_teaching_type_id){
		teachingTypeService.deleteById(request, t_teaching_type_id);
	}
}
