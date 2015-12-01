package com.mathtop.course.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.CourseTeachingClassDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.dao.StudentViewDataDao;
import com.mathtop.course.dao.TeachingClassDao;
import com.mathtop.course.dao.TeachingClassStudentDao;
import com.mathtop.course.dao.CourseTeachingClassTeacherDao;
import com.mathtop.course.dao.TeachingClassViewDataDao;
import com.mathtop.course.domain.TeacherViewData;
import com.mathtop.course.domain.CourseTeachingClass;
import com.mathtop.course.domain.CourseTeachingClassViewData;
import com.mathtop.course.domain.StudentViewData;

@Service
public class CourseTeachingClassService {

	@Autowired
	private TeachingClassDao teachingclassDao;

	@Autowired
	CourseTeachingClassDao courseTeachingClassDao;

	@Autowired
	CourseTeachingClassTeacherDao courseTeachingClassTeacherDao;

	@Autowired
	TeachingClassStudentDao teachingclassstudentDao;

	@Autowired
	TeachingClassViewDataDao teachingclassviewdataDao;

	@Autowired
	StudentViewDataDao studentViewDataDao;

	@Autowired
	AttendanceService attendanceService;

	@Autowired
	CourseTeachingClassForumTopicService courseTeachingClassForumTopicService;

	@Autowired
	CourseTeachingClassReferenceService courseTeachingClassReferenceService;

	@Autowired
	CourseTeachingClassHomeworkService courseTeachingClassHomeworkService;

	@Autowired
	CourseTeachingClassHomeworkTypeService courseTeachingClassHomeworkTypeService;

	@Autowired
	CourseTeachingClassTeacherService courseTeachingClassTeacherService;

	/**
	 * 
	 * 
	 * @param user
	 */
	public String add(String courseId, int teaching_year_begin, int teaching_year_end, int teaching_term, String teachingclass_name,
			String[] teacherid, String[] teachingtypetypeId) {

		String teachingclassid = teachingclassDao.add(teachingclass_name, "");

		String t_course_teaching_class_id = courseTeachingClassDao.add(courseId, teachingclassid, teaching_year_begin, teaching_year_end,
				teaching_term);

		if (teacherid != null && teacherid.length > 0) {
			for (int i = 0; i < teacherid.length; i++) {
				courseTeachingClassTeacherDao.add(t_course_teaching_class_id, teacherid[i], teachingtypetypeId[i]);
			}
		}

		return t_course_teaching_class_id;

	}

	/**
	 * 将学生加入到教学班中
	 * 
	 * @param user
	 */
	public void add(String teachingclassid, String[] studentid) {

		for (String stuid : studentid) {
			teachingclassstudentDao.add(teachingclassid, stuid);
		}
	}

	/**
	 * 将教师加入到教学班中
	 * 
	 * @param user
	 */
	public void add(String t_course_teaching_class_id, String[] teacherid, String[] teachingtypeid) {

		// 1.删除旧的该课程所有授课教师信息
		courseTeachingClassTeacherDao.deleteByCourseTeachingClassId(t_course_teaching_class_id);

		if (teacherid == null || teachingtypeid == null)
			return;

		if (teacherid.length != teachingtypeid.length)
			return;

		// 2.增加新的授课教师信息
		for (int i = 0; i < teacherid.length; i++) {
			courseTeachingClassTeacherDao.add(t_course_teaching_class_id, teacherid[i], teachingtypeid[i]);
		}
	}

	/**
	 * 根据t_course_id删除教学-课程信息
	 */
	public void deleteByCourseId(HttpServletRequest request, String t_course_id) {
		List<CourseTeachingClass> listCourses = courseTeachingClassDao.getCourseTeachingClassByCourseId(t_course_id);
		if (listCourses == null)
			return;
		for (CourseTeachingClass c : listCourses) {
			String t_course_teaching_class_id = c.getId();
			deleteById(request, t_course_teaching_class_id);
		}
	}

	/**
	 * 根据t_teaching_class_id删除教学-课程信息
	 */
	public void deleteByTeachingClassId(HttpServletRequest request, String t_teaching_class_id) {
		List<CourseTeachingClass> listCourses = courseTeachingClassDao.getCourseTeachingClassByTeachingClassId(t_teaching_class_id);
		if (listCourses == null)
			return;
		for (CourseTeachingClass c : listCourses) {
			String t_course_teaching_class_id = c.getId();
			deleteById(request, t_course_teaching_class_id);
		}
	}
	
	/**
	 * 根据t_teaching_class_id删除教学-课程信息
	 */
	public void deleteByCourseTeachingClassIdAndStudentId(HttpServletRequest request, String t_course_teaching_class_id,String t_student_id) {
		
	}

	
	
	/**
	 * 根据t_course_id、t_teaching_class_id删除教学-课程信息
	 */
	public void deleteByCourseIdAndTeachingClassId(HttpServletRequest request, String t_course_id, String t_teaching_class_id) {
		List<CourseTeachingClass> listCourses = courseTeachingClassDao.getCourseTeachingClassByCourseIdTeachingClassId(t_course_id,
				t_teaching_class_id);
		if (listCourses == null)
			return;
		for (CourseTeachingClass c : listCourses) {
			String t_course_teaching_class_id = c.getId();
			deleteById(request, t_course_teaching_class_id);
		}
	}

	/**
	 * 根据t_course_teaching_class_id删除教学-课程信息
	 */
	public void deleteById(HttpServletRequest request, String t_course_teaching_class_id) {
		// 教学班级-教师
		courseTeachingClassTeacherDao.deleteByCourseTeachingClassId(t_course_teaching_class_id);

		// 相关论坛
		courseTeachingClassForumTopicService.deleteByCourseTeachingClassID(request, t_course_teaching_class_id);

		// 课程相关资料
		courseTeachingClassReferenceService.deleteByCourseTeachingClassId(request, t_course_teaching_class_id);

		// 课程相关作业类型
		courseTeachingClassHomeworkService.deleteByCourseTeachingClassId(request, t_course_teaching_class_id);

		// 课程相关作业类型
		courseTeachingClassHomeworkTypeService.deleteByCourseTeachingClassId(request, t_course_teaching_class_id);

		// 课程-教师
		courseTeachingClassTeacherService.deleteByCourseTeachingClassId(request, t_course_teaching_class_id);

		// 课程相关考勤
		attendanceService.deleteByCourseTeachingClassId(t_course_teaching_class_id);

		// 删除自己
		courseTeachingClassDao.deleteById(t_course_teaching_class_id);
	}

	/**
	 * 
	 * 
	 * @param user
	 */
	public String update(String t_course_teaching_class_id, String courseId, int teaching_year_begin, int teaching_year_end,
			int teaching_term, String teachingclass_name, String[] teacherid, String[] teachingtypetypeId) {

		CourseTeachingClass ctc = courseTeachingClassDao.getCourseTeachingClassById(t_course_teaching_class_id);
		if (ctc == null)
			return null;

		// 课程信息
		String teachingclassid = ctc.getT_teaching_class_id();
		teachingclassDao.update(teachingclassid, teachingclass_name, "");

		// 课程授课信息
		courseTeachingClassDao.update(t_course_teaching_class_id, courseId, teachingclassid, teaching_year_begin, teaching_year_end,
				teaching_term);

		// 授课教师信息
		add(t_course_teaching_class_id, teacherid, teachingtypetypeId);

		return t_course_teaching_class_id;

	}

	/**
	 * 得到
	 */
	public Page<CourseTeachingClassViewData> getPage(int pageNo, int pageSize) {
		return teachingclassviewdataDao.getPage(pageNo, pageSize);
	}

	/**
	 * 得到
	 */
	public Page<CourseTeachingClassViewData> getPage(String teachingclassid) {
		return teachingclassviewdataDao.getPage(teachingclassid);
	}

	/**
	 * 得到课程全体教师视图
	 */
	public Page<TeacherViewData> getTeacherPage(String t_course_teaching_class_id) {
		return courseTeachingClassTeacherDao.getTeacherPage(t_course_teaching_class_id);
	}

	public CourseTeachingClassViewData GetTeachingClassViewDataByCourseTeachingClassId(String t_course_teaching_class_id) {
		return teachingclassviewdataDao.GetTeachingClassViewDataByCourseTeachingClassId(t_course_teaching_class_id);
	}

	/**
	 * 得到课程全体教师视图 如果是学生的话，则会得到所有同他上课的授课教师 如果是教师的话，则会得到所有同他所教授课程相关的授课教师
	 */
	public Page<TeacherViewData> getAddTeacherPageByUserInfo(String t_user_id) {
		if (t_user_id == null)
			return null;

		List<TeacherViewData> data = new ArrayList<TeacherViewData>();

		// 得到该教师所有的教学班
		List<CourseTeachingClassViewData> listCourseTeachingClass = teachingclassviewdataDao
				.getCourseTeachingClassViewDataByUserId(t_user_id);

		if (listCourseTeachingClass == null)
			return null;

		// 将每个教学班的教师添加到列表中，注意教师不能重复
		for (CourseTeachingClassViewData t : listCourseTeachingClass) {

			for (TeacherViewData temp : t.getTeacher()) {

				boolean bflag = true;
				for (TeacherViewData d : data) {
					if (d.getTeacher().getId() == temp.getTeacher().getId()) {
						bflag = false;
						break;
					}
				}

				if (bflag)
					data.add(temp);
			}
		}
		return new Page<TeacherViewData>(0, data.size(), data.size(), data);
	}

	public Page<StudentViewData> getAddStudentPageByUserInfo(String t_user_id) {
		if (t_user_id == null)
			return null;

		List<StudentViewData> data = new ArrayList<StudentViewData>();

		// 得到该教师所有的教学班,然后将每个教学班的教师添加到列表中
		List<CourseTeachingClassViewData> listCourseTeachingClass = teachingclassviewdataDao
				.getCourseTeachingClassViewDataByUserId(t_user_id);

		if (listCourseTeachingClass == null)
			return null;

		for (CourseTeachingClassViewData c : listCourseTeachingClass) {

			// 得到每个教学班的列表
			List<StudentViewData> listStudentViewData = studentViewDataDao.getStudentViewByTeachingClassId(c.getTeachingclass().getId());

			for (StudentViewData s : listStudentViewData) {
				boolean bflag = true;
				for (StudentViewData temp : data) {
					if (temp.getStudent().getId() == s.getStudent().getId()) {
						bflag = false;
						break;
					}
				}
				if (bflag)
					data.add(s);
			}
		}
		return new Page<StudentViewData>(0, data.size(), data.size(), data);
	}

}
