package com.mathtop.course.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.CourseTeachingClassHomeworkScoreInfoDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.dao.ScoreMarkingTypeDao;
import com.mathtop.course.dao.ScoreShowTypeDao;
import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfo;
import com.mathtop.course.domain.CourseTeachingClassHomeworkScoreInfo;
import com.mathtop.course.domain.CourseTeachingClassHomeworkScoreInfoViewData;
import com.mathtop.course.domain.ScoreMarkingType;
import com.mathtop.course.domain.ScoreShowType;

@Service
public class CourseTeachingClassHomeworkScoreInfoService {

	@Autowired
	CourseTeachingClassHomeworkScoreInfoDao scoreInfoDao;

	@Autowired
	ScoreMarkingTypeDao scoreMarkingTypeDao;

	@Autowired
	ScoreShowTypeDao scoreShowTypeDao;

	CourseTeachingClassHomeworkStudentScoreService courseTeachingClassHomeworkStudentScoreService;

	@Autowired
	private CourseTeachingClassHomeworkService homeworkService;

	public CourseTeachingClassHomeworkScoreInfo getByID(String id) {
		return scoreInfoDao.getByID(id);
	}

	/**
	 * 根据id删除
	 */
	public void deleteById(HttpServletRequest request, String id) {
		CourseTeachingClassHomeworkScoreInfo info = getByID(id);
		if (info == null)
			return;

		courseTeachingClassHomeworkStudentScoreService.deleteByScoreInfoId(request,
				info.getCourseTeachingClassHomeworkBaseinfoId());
		;
		scoreInfoDao.deleteById(id);
	}
	
	

	/**
	 * 根据t_course_teaching_class_id删除
	 */
	public void deleteByCourseTeachingClassHomeworkInfoId(HttpServletRequest request,
			String t_course_teaching_class_homework_baseinfo_id) {
		CourseTeachingClassHomeworkScoreInfo info = scoreInfoDao
				.getByCourseTeachingClassHomeworkBaseinfoID(t_course_teaching_class_homework_baseinfo_id);
		if (info == null)
			return;
		scoreInfoDao.deleteByCourseTeachingClassHomeworkInfoId(t_course_teaching_class_homework_baseinfo_id);
	}

	public void deleteByScoreMarkingTypeId(String t_score_marking_type_id) {
		List<CourseTeachingClassHomeworkScoreInfo> list = scoreInfoDao.getByScoreMarkingTypeID(t_score_marking_type_id);
		if (list == null || list.isEmpty())
			return;
		for (CourseTeachingClassHomeworkScoreInfo info : list) {
			scoreInfoDao.deleteById(info.getId());
		}
		scoreInfoDao.deleteByScoreMarkingTypeId(t_score_marking_type_id);
	}

	public void deleteByScoreShowTypeId(String t_score_show_type_id) {
		List<CourseTeachingClassHomeworkScoreInfo> list = scoreInfoDao.getByScoreShowTypeID(t_score_show_type_id);
		if (list == null || list.isEmpty())
			return;
		for (CourseTeachingClassHomeworkScoreInfo info : list) {
			scoreInfoDao.deleteById(info.getId());
		}
		scoreInfoDao.deleteByScoreShowTypeId(t_score_show_type_id);
	}

	public String add(String t_course_teaching_class_homework_baseinfo_id, String t_score_marking_type_id,
			String t_score_show_type_id) {
		return scoreInfoDao.add(t_course_teaching_class_homework_baseinfo_id, t_score_marking_type_id,
				t_score_show_type_id);
	}

	/**
	 * update
	 */
	public void update(String id, String t_course_teaching_class_homework_baseinfo_id, String t_score_marking_type_id,
			String t_score_show_type_id) {

		scoreInfoDao.update(id, t_course_teaching_class_homework_baseinfo_id, t_score_marking_type_id,
				t_score_show_type_id);
	}

	public void updateByCourseTeachingClassIdAndHomeworkTypeId(String t_course_teaching_class_id,
			String t_course_teaching_class_homeworktype_id, String t_score_marking_type_id,
			String t_score_show_type_id) {
		scoreInfoDao.updateByCourseTeachingClassIdAndHomeworkTypeId(t_course_teaching_class_id,
				t_course_teaching_class_homeworktype_id, t_score_marking_type_id, t_score_show_type_id);
	}

	public CourseTeachingClassHomeworkScoreInfoViewData getViewById(
			String t_course_teaching_class_homework_score_info_id) {
		CourseTeachingClassHomeworkScoreInfoViewData data = new CourseTeachingClassHomeworkScoreInfoViewData();

		CourseTeachingClassHomeworkScoreInfo scoreInfo = getByID(t_course_teaching_class_homework_score_info_id);
		data.setScoreInfo(scoreInfo);

		CourseTeachingClassHomeworkBaseinfo homeworkBaseinfo = homeworkService
				.getByID(scoreInfo.getCourseTeachingClassHomeworkBaseinfoId());
		data.setHomeworkBaseinfo(homeworkBaseinfo);

		ScoreMarkingType markingType = scoreMarkingTypeDao.getByID(scoreInfo.getScoreMarkingTypeId());
		data.setScoreMarkingType(markingType);

		ScoreShowType showType = scoreShowTypeDao.getByID(scoreInfo.getScoreShowTypeId());
		data.setScoreShowType(showType);

		return data;
	}

	public CourseTeachingClassHomeworkScoreInfoViewData getViewByHomeworkBaseinfoId(
			String t_course_teaching_class_homework_baseinfo_id) {
		CourseTeachingClassHomeworkScoreInfo scoreInfo = scoreInfoDao
				.getByCourseTeachingClassHomeworkBaseinfoID(t_course_teaching_class_homework_baseinfo_id);
		if (scoreInfo == null)
			return null;
		return getViewById(scoreInfo.getId());
	}

	private List<CourseTeachingClassHomeworkScoreInfoViewData> PageQuery(String t_course_teaching_class_id,
			String t_course_teaching_class_homeworktype_id, int PageBegin, int PageSize) {

		if (PageBegin < 0)
			PageBegin = 0;

		List<CourseTeachingClassHomeworkScoreInfo> list = scoreInfoDao.PageQuery(t_course_teaching_class_id,
				t_course_teaching_class_homeworktype_id, PageBegin, PageSize);

		if (list == null)
			return null;

		List<CourseTeachingClassHomeworkScoreInfoViewData> listResult = new ArrayList<CourseTeachingClassHomeworkScoreInfoViewData>();

		for (CourseTeachingClassHomeworkScoreInfo info : list) {
			CourseTeachingClassHomeworkScoreInfoViewData data = getViewById(info.getId());
			listResult.add(data);
		}
		return listResult;
	}

	public Page<CourseTeachingClassHomeworkScoreInfoViewData> getPage(String t_course_teaching_class_id,
			String t_course_teaching_class_homeworktype_id, int pageNo, int pageSize) {
		long totalCount = scoreInfoDao.getCount(t_course_teaching_class_id, t_course_teaching_class_homeworktype_id);
		if (totalCount < 1)
			return new Page<CourseTeachingClassHomeworkScoreInfoViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassHomeworkScoreInfoViewData> data = PageQuery(t_course_teaching_class_id,
				t_course_teaching_class_homeworktype_id, pageNo - 1, pageSize);

		return new Page<CourseTeachingClassHomeworkScoreInfoViewData>(startIndex, totalCount, pageSize, data);

	}

	
	/**
	 * 
	 * */
	public List<CourseTeachingClassHomeworkScoreInfoViewData> getAllByOrder(String t_course_teaching_class_id,
			String t_course_teaching_class_homeworktype_id, Integer homework_show_order) {

		List<CourseTeachingClassHomeworkScoreInfo> list = null;
		if (homework_show_order == 0)
			list = scoreInfoDao.getAllOrderByDesc(t_course_teaching_class_id, t_course_teaching_class_homeworktype_id);
		else
			list = scoreInfoDao.getAllOrderByAsc(t_course_teaching_class_id, t_course_teaching_class_homeworktype_id);

		List<CourseTeachingClassHomeworkScoreInfoViewData> listResult = new ArrayList<CourseTeachingClassHomeworkScoreInfoViewData>();

		for (CourseTeachingClassHomeworkScoreInfo info : list) {
			CourseTeachingClassHomeworkScoreInfoViewData data = getViewById(info.getId());
			listResult.add(data);
		}
		return listResult;

	}

	public void addHomeworkBaseInfo(String t_course_teaching_class_homework_baseinfo_id) {

		if (t_course_teaching_class_homework_baseinfo_id == null)
			return;

		if (null == scoreInfoDao
				.getByCourseTeachingClassHomeworkBaseinfoID(t_course_teaching_class_homework_baseinfo_id)) {
			scoreInfoDao.add(t_course_teaching_class_homework_baseinfo_id);
		}

	}

	public void addHomeworkBaseInfo(String t_course_teaching_class_id, String t_course_teaching_class_homeworktype_id) {
		List<CourseTeachingClassHomeworkBaseinfo> list = homeworkService.getByCourseTeachingClassIDAndHomeworkTypeId(
				t_course_teaching_class_id, t_course_teaching_class_homeworktype_id);
		if (list == null)
			return;

		for (CourseTeachingClassHomeworkBaseinfo info : list) {
			addHomeworkBaseInfo(info.getId());

		}

	}
}
