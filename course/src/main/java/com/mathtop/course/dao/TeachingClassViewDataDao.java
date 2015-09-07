package com.mathtop.course.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.Course;
import com.mathtop.course.domain.CourseTeachingClass;
import com.mathtop.course.domain.Student;
import com.mathtop.course.domain.Teacher;
import com.mathtop.course.domain.TeacherViewData;
import com.mathtop.course.domain.TeachingClass;
import com.mathtop.course.domain.CourseTeachingClassStudent;
import com.mathtop.course.domain.CourseTeachingClassTeacher;
import com.mathtop.course.domain.CourseTeachingClassViewData;
import com.mathtop.course.domain.TeachingType;

@Repository
public class TeachingClassViewDataDao extends BaseDao<CourseTeachingClassViewData> {

	@Autowired
	TeacherDao teacherDao;
	
	
	@Autowired
	StudentDao studentDao;
	
	@Autowired
	TeachingClassDao teachingClassDao;

	@Autowired
	CourseDao courseDao;

	@Autowired
	TeacherViewDataDao teacherviewdataDao;

	@Autowired
	TeachingTypeDao teachingtypeDao;

	@Autowired
	CourseTeachingClassDao courseTeachingClassDao;

	@Autowired
	TeachingClassTeacherDao teachingclassteacherDao;
	
	@Autowired
	TeachingClassStudentDao teachingclassstudentDao;

	public List<CourseTeachingClassViewData> getTeacherViewByt_school_id() {
		List<CourseTeachingClassViewData> list = new ArrayList<CourseTeachingClassViewData>();

		return list;
	}
	
	/**根据用户id得到教学班信息
	 * */
	public List<CourseTeachingClassViewData> getCourseTeachingClassViewDataByUserId(String t_user_id) {
		List<CourseTeachingClassViewData> list = new ArrayList<CourseTeachingClassViewData>();

		Teacher teacher=teacherDao.getTeacherByt_user_id(t_user_id);
		if(teacher!=null){
			
			
			
			//根据教师id得到教学班
			List<CourseTeachingClassTeacher> teachingclassteacherlist=teachingclassteacherDao.getTeachingClassTeacherByTeacherId(teacher.getId());
			
			for(CourseTeachingClassTeacher tct:teachingclassteacherlist){
				//根据教学班得到教学班信息
				
				
				CourseTeachingClassViewData temp=GetTeachingClassViewDataByCourseTeachingClassId(tct.getT_course_teaching_class_id());
				
				
				list.add(temp);
			}
			
		}else{
			Student student=studentDao.getStudentByt_user_id(t_user_id);
			if(student!=null){
				//根据学生id得到教学班
				List<CourseTeachingClassStudent> teachingclassstudentlist=teachingclassstudentDao.getTeachingClassStudentByStudentId(student.getId());
				
				for(CourseTeachingClassStudent tct:teachingclassstudentlist){
					//根据教学班得到教学班信息
					List<CourseTeachingClassViewData> temp=GetTeachingClassViewDataByTeachingClassId(tct.getT_teaching_class_id());
					list.addAll(temp);
				}
			}
		}
		if(list.size()==0)
			return null;
		return list;
	}

	/**
	 * 根据教学班Id得到教学班相关信息
	 * */
	private List<CourseTeachingClassViewData> GetTeachingClassViewDataByTeachingClassId(String teachingclassid) {

		List<CourseTeachingClassViewData> list = new ArrayList<CourseTeachingClassViewData>();

		// 得到教学班

		TeachingClass tc = teachingClassDao.getByID(teachingclassid);

		// 根据教学班得到课程
		List<CourseTeachingClass> courseteachingclasslist = courseTeachingClassDao
				.getCourseTeachingClassByTeachingClassId(tc.getId());
		for (CourseTeachingClass ctc : courseteachingclasslist) {

			CourseTeachingClassViewData tcvd = new CourseTeachingClassViewData();
			tcvd.setTeachingclass(tc);

			tcvd.setCourseteachingclass(ctc);

			Course c = courseDao.getCourseById(ctc.getT_course_id());
			tcvd.setCourse(c);

			// 根据教学班得到教师信息
			List<CourseTeachingClassTeacher> teachingclassteacherlist = teachingclassteacherDao
					.getTeachingClassTeacherByTeachingClassId(ctc.getId());
			if (teachingclassteacherlist.size() > 0) {

				TeacherViewData[] tvd = new TeacherViewData[teachingclassteacherlist
						.size()];

				TeachingType[] tt = new TeachingType[teachingclassteacherlist
						.size()];

				int index = 0;
				for (CourseTeachingClassTeacher tct : teachingclassteacherlist) {
					tvd[index] = teacherviewdataDao
							.getTeacherViewDataById(tct.getT_teacher_id());
					tt[index] = teachingtypeDao.getByID(tct
							.getT_teaching_type_id());
					index++;

				}

				tcvd.setTeacher(tvd);
				tcvd.setTeachingtype(tt);

			}
			list.add(tcvd);
		}

		return list;
	}
	
	
	
	/**
	 * 根据课程-教学班Id得到教学班相关信息
	 * */
	public CourseTeachingClassViewData GetTeachingClassViewDataByCourseTeachingClassId(String t_course_teaching_class_id) {

		
		CourseTeachingClassViewData tcvd = new CourseTeachingClassViewData();
		

		CourseTeachingClass ctc=courseTeachingClassDao.getCourseTeachingClassById(t_course_teaching_class_id);
		if(ctc!=null){

			TeachingClass tc = teachingClassDao.getByID(ctc.getT_teaching_class_id());
			
			
			tcvd.setTeachingclass(tc);

			tcvd.setCourseteachingclass(ctc);
			
			

			Course c = courseDao.getCourseById(ctc.getT_course_id());
			tcvd.setCourse(c);

			// 根据教学班得到教师信息
			List<CourseTeachingClassTeacher> teachingclassteacherlist = teachingclassteacherDao
					.getTeachingClassTeacherByTeachingClassId(ctc.getId());
			if (teachingclassteacherlist.size() > 0) {

				TeacherViewData[] tvd = new TeacherViewData[teachingclassteacherlist
						.size()];

				TeachingType[] tt = new TeachingType[teachingclassteacherlist
						.size()];

				int index = 0;
				for (CourseTeachingClassTeacher tct : teachingclassteacherlist) {
					tvd[index] = teacherviewdataDao
							.getTeacherViewDataById(tct.getT_teacher_id());
					tt[index] = teachingtypeDao.getByID(tct
							.getT_teaching_type_id());
					index++;

				}
				
				tcvd.setTeacher(tvd);
				tcvd.setTeachingtype(tt);

			}
			
		}

		
		
		
		if(tcvd.getCourse()!=null)
			return tcvd;
		
		return null;
	}

	/*
	 * 
	 */
	private List<CourseTeachingClassViewData> PageQuery(int PageBegin, int PageSize) {

		List<CourseTeachingClassViewData> list = new ArrayList<CourseTeachingClassViewData>();

		Page<TeachingClass> pageTeachingClass = teachingClassDao.getPage(
				PageBegin, PageSize);

		if (pageTeachingClass != null) {

			// 得到教学班
			List<TeachingClass> lstTeachingClass = pageTeachingClass
					.getResult();

			for (TeachingClass tc : lstTeachingClass) {

				// 根据教学班得到课程
				List<CourseTeachingClass> courseteachingclasslist = courseTeachingClassDao
						.getCourseTeachingClassByTeachingClassId(tc.getId());
				for (CourseTeachingClass ctc : courseteachingclasslist) {

					CourseTeachingClassViewData tcvd = new CourseTeachingClassViewData();
					tcvd.setTeachingclass(tc);

					tcvd.setCourseteachingclass(ctc);

					Course c = courseDao.getCourseById(ctc.getT_course_id());
					tcvd.setCourse(c);

					// 根据教学班得到教师信息
					List<CourseTeachingClassTeacher> teachingclassteacherlist = teachingclassteacherDao
							.getTeachingClassTeacherByTeachingClassId(tc
									.getId());
					if (teachingclassteacherlist.size() > 0) {

						TeacherViewData[] tvd = new TeacherViewData[teachingclassteacherlist
								.size()];

						TeachingType[] tt = new TeachingType[teachingclassteacherlist
								.size()];

						int index = 0;
						for (CourseTeachingClassTeacher tct : teachingclassteacherlist) {
							
							tvd[index] = teacherviewdataDao
									.getTeacherViewDataById(tct
											.getT_teacher_id());
							tt[index] = teachingtypeDao.getByID(tct
									.getT_teaching_type_id());
							index++;

						}

						tcvd.setTeacher(tvd);
						tcvd.setTeachingtype(tt);

					}
					list.add(tcvd);
				}

			}
		}
		return list;
	}

	/**
	 * 
	 * 
	 * @param pageNo
	 *            页号，从1开始。
	 * @param pageSize
	 *            每页的记录数
	 * @return 包含分页信息的Page对象
	 */
	public Page<CourseTeachingClassViewData> getPage(int pageNo, int pageSize) {

		long totalCount = teachingClassDao.getCount();
		if (totalCount < 1)
			return new Page<CourseTeachingClassViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassViewData> data = PageQuery(pageNo, pageSize);

		return new Page<CourseTeachingClassViewData>(startIndex, totalCount,
				pageSize, data);

	}

	/**
	 * 
	 * 
	 * @param pageNo
	 *            页号，从1开始。
	 * @param pageSize
	 *            每页的记录数
	 * @return 包含分页信息的Page对象
	 */
	public Page<CourseTeachingClassViewData> getPage(String teachingclassid) {

		List<CourseTeachingClassViewData> data = GetTeachingClassViewDataByTeachingClassId(teachingclassid);

		return new Page<CourseTeachingClassViewData>(0, 1, 1, data);

	}

}
