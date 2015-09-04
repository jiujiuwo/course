package com.mathtop.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.AttendanceDao;
import com.mathtop.course.dao.TeachingClassStudentDao;
import com.mathtop.course.dao.TeachingClassTeacherDao;
import com.mathtop.course.dao.DepartmentDao;
import com.mathtop.course.dao.DepartmentNaturalClassDao;
import com.mathtop.course.dao.DepartmentTeacherDao;
import com.mathtop.course.dao.LoginDao;
import com.mathtop.course.dao.NaturalClassDao;
import com.mathtop.course.dao.NaturalClassStudentDao;
import com.mathtop.course.dao.SchoolDao;
import com.mathtop.course.dao.SchoolDepartmentDao;
import com.mathtop.course.dao.StudentDao;
import com.mathtop.course.dao.TeacherDao;
import com.mathtop.course.dao.UserBasicInfoDao;
import com.mathtop.course.dao.UserContactInfoDao;
import com.mathtop.course.dao.UserContactTypeDao;
import com.mathtop.course.dao.UserDao;
import com.mathtop.course.domain.CourseTeachingClassStudent;
import com.mathtop.course.domain.CourseTeachingClassTeacher;
import com.mathtop.course.domain.Student;
import com.mathtop.course.domain.Teacher;

/**
 * 删除服务，需要注意数据之间存在关联，为了保证数据完整，必须注意删除次序
 * */

@Service
public class DeleteService {

	@Autowired
	AttendanceDao attendancedao;

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
	UserDao userDao;

	@Autowired
	LoginDao loginDao;

	@Autowired
	StudentDao studentDao;

	@Autowired
	TeacherDao teacherDao;

	@Autowired
	TeachingClassTeacherDao courseTeachingClassTeacherDao;

	@Autowired
	TeachingClassStudentDao courseTeachingClassStudentDao;

	@Autowired
	UserBasicInfoDao userBasicInfoDao;

	@Autowired
	UserContactInfoDao userContactInfoDao;
	
	@Autowired
	UserContactTypeDao userContactTypeDao;

	/**
	 * 删除学院
	 */
	void deleteSchoolById(String t_school_id) {

		// .删除
		// .删除
		// .删除
		// .删除
		// .删除

		// .删除本学院所有的系部
		List<String> t_department_ids = schoolDepartmentDao.get_department_idByt_school_id(t_school_id);
		for (String t_department_id : t_department_ids) {
			deleteDepartmentById(t_department_id);
		}

		// 删除自己
		schoolDao.DELETE(t_school_id);

	}

	/**
	 * 删除系部
	 * */
	void deleteDepartmentById(String t_department_id) {

		// 删除所有教师
		List<String> t_teacher_ids = departmentTeacherDao.getByTeacherIdByt_department_id(t_department_id);
		for (String t_teacher_id : t_teacher_ids) {
			deleteTeacherById(t_teacher_id);
		}

		// 删除所有的自然班
		List<String> t_natural_class_ids = naturalClassDepartmentDao.gett_natural_class_idByt_department_id(t_department_id);
		for (String t_natural_class_id : t_natural_class_ids) {
			deleteNaturalClassById(t_natural_class_id);
		}

		// 删除学院-系部关系
		schoolDepartmentDao.deleteByDepartmentId(t_department_id);

		// 删除自己
		departmentDao.DELETE(t_department_id);
	}

	/**
	 * 删除自然班
	 * */
	void deleteNaturalClassById(String t_natural_class_id) {
		// 删除所有学生
		List<String> t_student_ids = naturalClassStudentDao.getStudentIdByt_natural_class_id(t_natural_class_id);
		for (String t_student_id : t_student_ids) {
			deleteStudentById(t_student_id);
		}

		// 删除自己
		naturalClassDao.DELETE(t_natural_class_id);
	}

	/**
	 * 删除教师
	 * */
	void deleteTeacherById(String t_teacher_id) {

		// 删除教学班-教师
		List<CourseTeachingClassTeacher> listCourseTeachingClassTeacher = courseTeachingClassTeacherDao
				.getTeachingClassTeacherByTeacherId(t_teacher_id);
		for (CourseTeachingClassTeacher c : listCourseTeachingClassTeacher) {
			deleteCourseTeachingClassTeacher(c.getId());
		}

		// 删除教师
		Teacher teacher = teacherDao.getTeacherByID(t_teacher_id);
		teacherDao.deleteById(t_teacher_id);

		String t_user_id = teacher.getT_user_id();
		// 删除用户基本信息
		userBasicInfoDao.deleteByUserId(t_user_id);

		// 联系方式
		userContactInfoDao.deleteByUserId(t_user_id);

		// 删除登录信息

		loginDao.deleteById(t_user_id);

		// 删除用户
		userDao.deleteById(t_user_id);

	}

	/**
	 * 删除学生
	 * */
	void deleteStudentById(String t_student_id) {

		// 删除教学班-学生
		List<CourseTeachingClassStudent> listCourseTeachingClassStudent = courseTeachingClassStudentDao
				.getTeachingClassStudentByStudentId(t_student_id);
		for (CourseTeachingClassStudent c : listCourseTeachingClassStudent) {
			deleteCourseTeachingClassStudent(c.getId());
		}

		// 删除学生
		Student stu = studentDao.getStudentByID(t_student_id);
		studentDao.deleteById(t_student_id);

		String t_user_id = stu.getT_user_id();

		// 删除用户基本信息
		userBasicInfoDao.deleteByUserId(t_user_id);

		// 联系方式
		userContactInfoDao.deleteByUserId(t_user_id);

		// 删除登录信息

		loginDao.deleteById(t_user_id);

		// 删除用户
		userDao.deleteById(t_user_id);
	}

	/**
	 * 删除用户基本信息
	 * */
	void deleteUserBasicInfoByID(String t_user_basic_info_id) {

	}

	/**
	 * 删除联系方式
	 * */
	void deleteUserContactInfoById(String t_user_contact_info_id) {

	}
	
	/**
	 * 删除联系类型
	 * */
	void deleteUserContactTypeById(String t_user_contact_type_id){
		//删除联系方式
		userContactInfoDao.deleteByUserContactTypeId(t_user_contact_type_id);
		
		//删除自己
		userContactTypeDao.DELETE(t_user_contact_type_id);
	}

	/**
	 * 删除教学班级-教师
	 * */
	void deleteCourseTeachingClassTeacher(String t_course_teaching_class_teacher_id) {

	}

	/**
	 * 删除教学班级-学生
	 * */
	void deleteCourseTeachingClassStudent(String t_course_teaching_class_student_id) {

	}
}
