package com.mathtop.course.service;

import java.util.ArrayList;
import java.util.List;

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
import com.mathtop.course.domain.CourseTeachingClassViewData;
import com.mathtop.course.domain.StudentViewData;

@Service
public class TeachingClassService {

	@Autowired
	private TeachingClassDao teachingclassDao;

	@Autowired
	CourseTeachingClassDao courseteachingclassDao;

	@Autowired
	CourseTeachingClassTeacherDao teachingclassteacherDao;

	@Autowired
	TeachingClassStudentDao teachingclassstudentDao;

	@Autowired
	TeachingClassViewDataDao teachingclassviewdataDao;

	@Autowired
	StudentViewDataDao studentViewDataDao;
	/**
	 * 
	 * 
	 * @param user
	 */
	public String add(String courseId, int teaching_year_begin, int teaching_year_end, int teaching_term, String teachingclass_name,
			String[] teacherid, String[] teachingtypetypeId) {

		String teachingclassid = teachingclassDao.add(teachingclass_name, "");

		String id = courseteachingclassDao.add(courseId, teachingclassid, teaching_year_begin, teaching_year_end, teaching_term);

		if (teacherid != null && teacherid.length > 0) {
			for (int i = 0; i < teacherid.length; i++) {
				teachingclassteacherDao.add(teachingclassid, teacherid[i], teachingtypetypeId[i]);
			}
		}

		return id;

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
	public void add(String teachingclassid, String[] teacherid, String[] teachingtypeid) {

		// 1.删除旧的该课程所有授课教师信息
		teachingclassteacherDao.deleteByTeachingClassId(teachingclassid);

		if (teacherid == null || teachingtypeid == null)
			return;

		if (teacherid.length != teachingtypeid.length)
			return;

		// 2.增加新的授课教师信息
		for (int i = 0; i < teacherid.length; i++) {
			teachingclassteacherDao.add(teachingclassid, teacherid[i], teachingtypeid[i]);
		}
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
		return teachingclassteacherDao.getTeacherPage(t_course_teaching_class_id);
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
		
		//将每个教学班的教师添加到列表中，注意教师不能重复
		for (CourseTeachingClassViewData t : listCourseTeachingClass) {
			
			for(TeacherViewData temp:t.getTeacher()){
				
				boolean bflag=true;
				for(TeacherViewData d:data){
					if(d.getTeacher().getId()==temp.getTeacher().getId()){
						bflag=false;
					break;
					}
				}
				
				if(bflag)
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
		for (CourseTeachingClassViewData c : listCourseTeachingClass) {
			
			//得到每个教学班的列表
			List<StudentViewData> listStudentViewData = studentViewDataDao
					.getStudentViewByTeachingClassId(c.getTeachingclass().getId());
			
			
			
			for(StudentViewData s:listStudentViewData){
				boolean bflag=true;
				for(StudentViewData temp:data){
					if(temp.getStudent().getId()==s.getStudent().getId()){
						bflag=false;
						break;
						}					
				}
				if(bflag)
					data.add(s);
			}			
		}
		return new Page<StudentViewData>(0, data.size(), data.size(), data);
	}

}
