package com.mathtop.course.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.CourseTeachingClassDao;
import com.mathtop.course.dao.CourseTeachingClassHomeworkReplyDao;
import com.mathtop.course.dao.CourseTeachingClassHomeworkSubmitBaseinfoDao;
import com.mathtop.course.dao.CourseTeachingClassHomeworkSubmitFileDao;
import com.mathtop.course.dao.CourseTeachingClassStudentDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.dao.StudentViewDataDao;
import com.mathtop.course.domain.CourseTeachingClass;
import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfo;
import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfoViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkReply;
import com.mathtop.course.domain.CourseTeachingClassHomeworkStatisticsViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkSubmitBaseinfo;
import com.mathtop.course.domain.CourseTeachingClassHomeworkSubmitBaseinfoViewData;
import com.mathtop.course.domain.CourseTeachingClassStudent;
import com.mathtop.course.domain.StudentViewData;



/**
 * 课程相关信息统计服务
 * */

@Service
public class CourseTeachingClassHomeworkStatisticsService {
	@Autowired
	CourseTeachingClassStudentDao teachingClassStudentDao;

	@Autowired
	CourseTeachingClassHomeworkSubmitBaseinfoDao submitDao;

	@Autowired
	CourseTeachingClassHomeworkService courseTeachingClassHomeworkService;

	@Autowired
	CourseTeachingClassHomeworkSubmitFileDao submitfileDao;

	@Autowired
	CourseTeachingClassStudentDao tachingclassstudentDao;

	@Autowired
	CourseTeachingClassDao courseteachingclassDao;

	@Autowired
	CourseTeachingClassHomeworkReplyDao replyDao;

	@Autowired
	StudentViewDataDao studentDao;
	
	@Autowired
	CourseTeachingClassHomeworkSubmitService courseTeachingClassHomeworkSubmitService;

	/**
	 * 
	 * */
	private List<CourseTeachingClassHomeworkStatisticsViewData> PageQuery(CourseTeachingClassHomeworkBaseinfo baseinfo,
			CourseTeachingClass ctc, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<CourseTeachingClassHomeworkStatisticsViewData> list = new ArrayList<CourseTeachingClassHomeworkStatisticsViewData>();

		List<CourseTeachingClassStudent> liststudent = teachingClassStudentDao
				.getTeachingClassStudentByTeachingClassId(ctc.getId());

		for (CourseTeachingClassStudent stu : liststudent) {
			CourseTeachingClassHomeworkStatisticsViewData data = new CourseTeachingClassHomeworkStatisticsViewData();

			// 作业信息
			CourseTeachingClassHomeworkBaseinfoViewData homeworkbaseinfo = courseTeachingClassHomeworkService
					.getCourseTeachingClassHomeworkBaseinfoViewDataByID(baseinfo.getId());
			data.setHomeworkbaseinfo(homeworkbaseinfo);

			// 作业提交情况
			List<CourseTeachingClassHomeworkSubmitBaseinfoViewData> homeworksubmit = courseTeachingClassHomeworkSubmitService
					.getViewDataByCourseTeachingClassHomeworkBaseinfoIDAndStudentID(baseinfo.getId(),
							stu.getStudentId());
			data.setHomeworksubmit(homeworksubmit);

			// 作业批复情况
			List<CourseTeachingClassHomeworkReply> homeworkreply = new ArrayList<CourseTeachingClassHomeworkReply>();
			for (CourseTeachingClassHomeworkSubmitBaseinfoViewData sub : homeworksubmit) {

				List<CourseTeachingClassHomeworkReply> r = replyDao
						.getByCourseTeachingClassHomeworkSubmitBaseInfoID(sub.getHomeworksubmitbaseinfo().getId());

				if (r != null)
					homeworkreply.addAll(r);
			}
			data.setHomeworkreply(homeworkreply);

			// 学生基本信息
			StudentViewData student = studentDao.getStudentViewDataById(stu.getStudentId());
			data.setStudent(student);

			list.add(data);
		}

		return list;
	}

	/**
	 * 作业的统计情况
	 * */
	public Page<CourseTeachingClassHomeworkStatisticsViewData> getPage(
			String t_course_teaching_class_homework_baseinfo_id, int pageNo, int pageSize) {

		CourseTeachingClassHomeworkBaseinfo baseinfo = courseTeachingClassHomeworkService
				.getByID(t_course_teaching_class_homework_baseinfo_id);

		if (baseinfo == null)
			return null;

		List<CourseTeachingClassHomeworkSubmitBaseinfo> submitlist = submitDao
				.getByCourseTeachingClassHomeworkBaseinfoID(t_course_teaching_class_homework_baseinfo_id);
		if (submitlist == null)
			return null;

		CourseTeachingClass ctc = courseteachingclassDao
				.getCourseTeachingClassById(baseinfo.getCourseTeachingClassId());
		int totalCount = teachingClassStudentDao.getStudentCountByCourseTeachingClassId(ctc.getId());

		if (totalCount < 1)
			return new Page<CourseTeachingClassHomeworkStatisticsViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassHomeworkStatisticsViewData> data = PageQuery(baseinfo, ctc, pageNo - 1, totalCount);

		return new Page<CourseTeachingClassHomeworkStatisticsViewData>(startIndex, totalCount, totalCount, data);

	}
	
}
