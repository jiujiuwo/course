package com.mathtop.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.CourseTeachingClassHomeworkStudentScoreDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.dao.ScoreMarkingTypeDao;
import com.mathtop.course.dao.ScoreShowTypeDao;
import com.mathtop.course.domain.CourseTeachingClassHomeworkScoreInfoViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkStudentScore;
import com.mathtop.course.domain.CourseTeachingClassHomeworkStudentScoreSimpleView;
import com.mathtop.course.domain.CourseTeachingClassHomeworkStudentScoreViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkStudentScoresViewData;
import com.mathtop.course.domain.StudentViewData;

@Service
public class CourseTeachingClassHomeworkStudentScoreService {

	@Autowired
	CourseTeachingClassHomeworkStudentScoreDao homeworkscoreDao;

	@Autowired
	ScoreMarkingTypeDao scoreMarkingTypeDao;

	@Autowired
	ScoreShowTypeDao scoreShowTypeDao;

	@Autowired
	private StudentService studentService;

	@Autowired
	CourseTeachingClassHomeworkScoreInfoService courseTeachingClassHomeworkScoreInfoService;

	public CourseTeachingClassHomeworkStudentScore getByID(String id) {
		return homeworkscoreDao.getByID(id);
	}

	/**
	 * 根据id删除
	 */
	public void deleteById(HttpServletRequest request, String id) {
		homeworkscoreDao.deleteById(id);
	}

	/**
	 * 根据t_course_teaching_class_id删除
	 */
	public void deleteByScoreInfoId(HttpServletRequest request, String t_course_teaching_class_homework_score_info_id) {
		homeworkscoreDao.deleteByScoreInfoId(t_course_teaching_class_homework_score_info_id);
	}

	public String add(String t_course_teaching_class_homework_score_info_id, String t_student_id, String t_teacher_id,
			String score, String description, String note) {
		if (homeworkscoreDao.isStudentScoreExist(t_course_teaching_class_homework_score_info_id, t_student_id)) {
			CourseTeachingClassHomeworkStudentScore s = homeworkscoreDao
					.getByCourseTeachingClassHomeworkScoreInfoIdAndStudentId(
							t_course_teaching_class_homework_score_info_id, t_student_id);
			if (s == null)
				return null;
			update(s.getId(), t_course_teaching_class_homework_score_info_id, t_student_id, t_teacher_id, score,
					description, note);
			return s.getId();

		} else {
			return homeworkscoreDao.add(t_course_teaching_class_homework_score_info_id, t_student_id, t_teacher_id,
					score, description, new Date(), note);
		}
	}
	
	public void deleteByStudentID(HttpServletRequest request, String t_student_id) {
		homeworkscoreDao.deleteByStudentId(t_student_id);

	}

	/**
	 * update
	 */
	public void update(String id, String t_course_teaching_class_homework_score_info_id, String t_student_id,
			String t_teacher_id, String score, String description, String note) {

		homeworkscoreDao.update(id, t_course_teaching_class_homework_score_info_id, t_student_id, t_teacher_id, score,
				description, new Date(), note);
	}

	public void updateScore(String id, String score) {
		homeworkscoreDao.updateScore(id, score);
	}

	public void updateDescription(String id, String description) {
		homeworkscoreDao.updateDescription(id, description, new Date());
	}

	public void update(String id, String score, String description) {
		homeworkscoreDao.update(id, score, description, new Date());
	}

	public CourseTeachingClassHomeworkStudentScoreViewData getViewById(String id) {

		CourseTeachingClassHomeworkStudentScore score = homeworkscoreDao.getByID(id);
		if (score == null)
			return null;

		CourseTeachingClassHomeworkStudentScoreViewData data = new CourseTeachingClassHomeworkStudentScoreViewData();

		data.setScore(score);

		data.setStudentView(studentService.getStudentViewByStudentId(score.getStudentId()));

		data.setScoreInfo(courseTeachingClassHomeworkScoreInfoService
				.getViewById(score.getCourseTeachingClassHomeworkScoreInfoId()));

		return data;

	}

	public CourseTeachingClassHomeworkStudentScoresViewData getViewDataByCourseTeachingClassIdAndStudentId(
			String t_course_teaching_class_id, String t_course_teaching_class_homeworktype_id, String t_student_id,Integer homework_show_order) {

		CourseTeachingClassHomeworkStudentScoresViewData data = new CourseTeachingClassHomeworkStudentScoresViewData();

		data.setStudentView(studentService.getStudentViewByStudentId(t_student_id));

		List<CourseTeachingClassHomeworkScoreInfoViewData> lstCourseTeachingClassHomeworkScoreInfoViewData = courseTeachingClassHomeworkScoreInfoService
				.getAllByOrder(t_course_teaching_class_id, t_course_teaching_class_homeworktype_id,homework_show_order);

		if (lstCourseTeachingClassHomeworkScoreInfoViewData != null) {
			List<CourseTeachingClassHomeworkStudentScoreSimpleView> temp = new ArrayList<CourseTeachingClassHomeworkStudentScoreSimpleView>();
			for (CourseTeachingClassHomeworkScoreInfoViewData info : lstCourseTeachingClassHomeworkScoreInfoViewData) {
				CourseTeachingClassHomeworkStudentScoreSimpleView v = new CourseTeachingClassHomeworkStudentScoreSimpleView();

				v.setScoreInfo(info);
				v.setScore(homeworkscoreDao.getByCourseTeachingClassHomeworkScoreInfoIdAndStudentId(
						info.getScoreInfo().getId(), t_student_id));
				temp.add(v);

			}
			data.setLstScore(temp);
		}

		return data;

	}

	public CourseTeachingClassHomeworkStudentScoresViewData getViewDataByCourseTeachingClassIdAndStudentId(
			String t_course_teaching_class_id, String t_course_teaching_class_homeworktype_id,
			StudentViewData student,Integer homework_show_order) {

		if (student == null)
			return null;

		CourseTeachingClassHomeworkStudentScoresViewData data = new CourseTeachingClassHomeworkStudentScoresViewData();

		data.setStudentView(student);

		List<CourseTeachingClassHomeworkScoreInfoViewData> lstCourseTeachingClassHomeworkScoreInfoViewData =
		
			courseTeachingClassHomeworkScoreInfoService
				.getAllByOrder(t_course_teaching_class_id, t_course_teaching_class_homeworktype_id,homework_show_order);
		

		if (lstCourseTeachingClassHomeworkScoreInfoViewData != null) {
			List<CourseTeachingClassHomeworkStudentScoreSimpleView> temp=new ArrayList<CourseTeachingClassHomeworkStudentScoreSimpleView>();
			for (CourseTeachingClassHomeworkScoreInfoViewData info : lstCourseTeachingClassHomeworkScoreInfoViewData) {
				CourseTeachingClassHomeworkStudentScoreSimpleView v=new CourseTeachingClassHomeworkStudentScoreSimpleView();
				
				v.setScoreInfo(info);
				v.setScore(homeworkscoreDao.getByCourseTeachingClassHomeworkScoreInfoIdAndStudentId(
						info.getScoreInfo().getId(), student.getStudent().getId()));
				temp.add(v);
				
			}			
			data.setLstScore(temp);
		}

		return data;

	}

	public Page<CourseTeachingClassHomeworkStudentScoresViewData> getStudentScoresPage(
			String t_course_teaching_class_id, String t_course_teaching_class_homeworktype_id,
			Integer homework_show_order, int pageNo, int pageSize) {
		// 学生信息
		Page<StudentViewData> pagedStudentViewData = studentService
				.getPageByCourseTeachingClassId(t_course_teaching_class_id, pageNo, pageSize);

		if (pagedStudentViewData == null)
			return null;

		List<CourseTeachingClassHomeworkStudentScoresViewData> data = new ArrayList<CourseTeachingClassHomeworkStudentScoresViewData>();
		for (StudentViewData stu : pagedStudentViewData.getResult()) {
			CourseTeachingClassHomeworkStudentScoresViewData d = getViewDataByCourseTeachingClassIdAndStudentId(
					t_course_teaching_class_id, t_course_teaching_class_homeworktype_id, stu, homework_show_order);
			data.add(d);
		}

		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		return new Page<CourseTeachingClassHomeworkStudentScoresViewData>(startIndex,
				pagedStudentViewData.getTotalCount(), pageSize, data);

	}

	private List<CourseTeachingClassHomeworkStudentScoreViewData> PageQuery(
			String t_course_teaching_class_homework_score_info_id, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<CourseTeachingClassHomeworkStudentScore> listtemp = homeworkscoreDao
				.PageQuery(t_course_teaching_class_homework_score_info_id, PageBegin, PageSize);
		if (listtemp == null)
			return null;

		List<CourseTeachingClassHomeworkStudentScoreViewData> list = new ArrayList<CourseTeachingClassHomeworkStudentScoreViewData>();
		for (CourseTeachingClassHomeworkStudentScore score : listtemp) {
			CourseTeachingClassHomeworkStudentScoreViewData s = getViewById(score.getId());
			list.add(s);
		}

		return list;
	}

	public Page<CourseTeachingClassHomeworkStudentScoreViewData> getPage(
			String t_course_teaching_class_homework_baseinfo_id, int pageNo, int pageSize) {
		long totalCount = homeworkscoreDao.getCount(t_course_teaching_class_homework_baseinfo_id);
		if (totalCount < 1)
			return new Page<CourseTeachingClassHomeworkStudentScoreViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassHomeworkStudentScoreViewData> data = PageQuery(
				t_course_teaching_class_homework_baseinfo_id, pageNo - 1, pageSize);

		return new Page<CourseTeachingClassHomeworkStudentScoreViewData>(startIndex, totalCount, pageSize, data);

	}

}
