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
import com.mathtop.course.domain.CourseTeachingClassStudent;
import com.mathtop.course.domain.CourseTeachingClassTeacher;
import com.mathtop.course.domain.CourseTeachingClassViewData;
import com.mathtop.course.domain.CourseTeachingTerm;
import com.mathtop.course.domain.TeachingType;

@Repository
public class CourseTeachingClassViewDataDao extends BaseDao<CourseTeachingClassViewData> {

	@Autowired
	TeacherDao teacherDao;

	@Autowired
	StudentDao studentDao;

	@Autowired
	CourseDao courseDao;

	@Autowired
	TeacherViewDataDao teacherviewdataDao;

	@Autowired
	TeachingTypeDao teachingtypeDao;
	
	@Autowired
	CourseTeachingTermDao courseTeachingTermDao;

	@Autowired
	CourseTeachingClassDao courseTeachingClassDao;

	@Autowired
	CourseTeachingClassTeacherDao courseTeachingClassTeacherDao;

	@Autowired
	CourseTeachingClassStudentDao courseTeachingClassStudentDao;

	public List<CourseTeachingClassViewData> getTeacherViewByt_school_id() {
		List<CourseTeachingClassViewData> list = new ArrayList<CourseTeachingClassViewData>();

		return list;
	}

	/**
	 * 根据用户id得到教学班信息
	 */
	public List<CourseTeachingClassViewData> getCourseTeachingClassViewDataByUserId(String t_user_id) {
		List<CourseTeachingClassViewData> list = new ArrayList<CourseTeachingClassViewData>();

		Teacher teacher = teacherDao.getTeacherByUserId(t_user_id);
		if (teacher != null) {

			// 根据教师id得到教学班
			List<CourseTeachingClassTeacher> teachingclassteacherlist = courseTeachingClassTeacherDao
					.getTeachingClassTeacherByTeacherId(teacher.getId());

			for (CourseTeachingClassTeacher tct : teachingclassteacherlist) {
				
				// 根据教学班得到教学班信息
				CourseTeachingClassViewData temp = GetTeachingClassViewDataByCourseTeachingClassId(
						tct.getCourseTeachingClassId());

				list.add(temp);
			}

		} else {
			Student student = studentDao.getStudentByUserId(t_user_id);
		
			if (student != null) {
				// 根据学生id得到教学班
				List<CourseTeachingClassStudent> teachingclassstudentlist = courseTeachingClassStudentDao
						.getTeachingClassStudentByStudentId(student);

				for (CourseTeachingClassStudent tct : teachingclassstudentlist) {					
					// 根据教学班得到教学班信息
					CourseTeachingClassViewData temp = GetTeachingClassViewDataByCourseTeachingClassId(
							tct.getCourseTeachingClassId());

					list.add(temp);
				}
			}
		}
		if (list.size() == 0)
			return null;
		return list;
	}

	/**
	 * 根据课程-教学班Id得到教学班相关信息
	 */
	public CourseTeachingClassViewData GetTeachingClassViewDataByCourseTeachingClassId(
			String t_course_teaching_class_id) {

		CourseTeachingClassViewData tcvd = new CourseTeachingClassViewData();

		CourseTeachingClass ctc = courseTeachingClassDao.getCourseTeachingClassById(t_course_teaching_class_id);
		if (ctc != null) {

			tcvd.setCourseTeachingClass(ctc);

			Course c = courseDao.getCourseById(ctc.getCourseId());
			tcvd.setCourse(c);
			
			CourseTeachingTerm term=courseTeachingTermDao.getByID(ctc.getCourseTeachingTermId());
			tcvd.setTerm(term);

			// 根据教学班得到教师信息
			List<CourseTeachingClassTeacher> teachingclassteacherlist = courseTeachingClassTeacherDao
					.getTeachingClassTeacherByTeachingClassId(ctc.getId());
			if (teachingclassteacherlist.size() > 0) {

				TeacherViewData[] tvd = new TeacherViewData[teachingclassteacherlist.size()];

				TeachingType[] tt = new TeachingType[teachingclassteacherlist.size()];

				int index = 0;
				for (CourseTeachingClassTeacher tct : teachingclassteacherlist) {
					tvd[index] = teacherviewdataDao.getTeacherViewDataById(tct.getTeacherId());
					tt[index] = teachingtypeDao.getByID(tct.getTeachingTypeId());
					index++;

				}

				tcvd.setTeacher(tvd);
				tcvd.setTeachingtype(tt);

			}

		}

		if (tcvd.getCourse() != null)
			return tcvd;

		return null;
	}

	/**
	 * 
	 * */
	private List<CourseTeachingClassViewData> PageQuery(int PageBegin, int PageSize) {

		List<CourseTeachingClassViewData> list = new ArrayList<CourseTeachingClassViewData>();

		Page<CourseTeachingClass> pageCourseTeachingClass = courseTeachingClassDao.getPage(PageBegin, PageSize);

		if (pageCourseTeachingClass != null) {

			// 得到教学班
			List<CourseTeachingClass> lstTeachingClass = pageCourseTeachingClass.getResult();

			for (CourseTeachingClass tc : lstTeachingClass) {

				// 根据教学班得到课程
				CourseTeachingClass courseteachingclass = courseTeachingClassDao
						.getCourseTeachingClassById(tc.getId());
				if(courseteachingclass!=null){

					CourseTeachingClassViewData tcvd = new CourseTeachingClassViewData();
					

					tcvd.setCourseTeachingClass(courseteachingclass);

					Course c = courseDao.getCourseById(courseteachingclass.getCourseId());
					tcvd.setCourse(c);
					
					CourseTeachingTerm term=courseTeachingTermDao.getByID(courseteachingclass.getCourseTeachingTermId());
					tcvd.setTerm(term);

					// 根据教学班得到教师信息
					List<CourseTeachingClassTeacher> teachingclassteacherlist = courseTeachingClassTeacherDao
							.getTeachingClassTeacherByTeachingClassId(tc.getId());
					if (teachingclassteacherlist.size() > 0) {

						TeacherViewData[] tvd = new TeacherViewData[teachingclassteacherlist.size()];

						TeachingType[] tt = new TeachingType[teachingclassteacherlist.size()];

						int index = 0;
						for (CourseTeachingClassTeacher tct : teachingclassteacherlist) {

							tvd[index] = teacherviewdataDao.getTeacherViewDataById(tct.getTeacherId());
							tt[index] = teachingtypeDao.getByID(tct.getTeachingTypeId());
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

		long totalCount = courseTeachingClassDao.getCount();

		if (totalCount < 1)
			return new Page<CourseTeachingClassViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassViewData> data = PageQuery(pageNo, pageSize);

		return new Page<CourseTeachingClassViewData>(startIndex, totalCount, pageSize, data);

	}


}
