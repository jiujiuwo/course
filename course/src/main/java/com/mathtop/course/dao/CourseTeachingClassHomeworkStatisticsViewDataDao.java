package com.mathtop.course.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.CourseTeachingClass;
import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfo;
import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfoViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkReply;
import com.mathtop.course.domain.CourseTeachingClassHomeworkStatisticsViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkSubmitBaseinfo;
import com.mathtop.course.domain.CourseTeachingClassHomeworkSubmitBaseinfoViewData;
import com.mathtop.course.domain.StudentViewData;
import com.mathtop.course.domain.CourseTeachingClassStudent;

@Repository
public class CourseTeachingClassHomeworkStatisticsViewDataDao extends BaseDao<CourseTeachingClassHomeworkStatisticsViewData> {

	@Autowired
	TeachingClassStudentDao teachingClassStudentDao;

	@Autowired
	CourseTeachingClassHomeworkSubmitBaseinfoDao submitDao;

	@Autowired
	CourseTeachingClassHomeworkBaseinfoDao homeworkbaseinfoDao;

	@Autowired
	CourseTeachingClassHomeworkSubmitFileDao submitfileDao;

	@Autowired
	TeachingClassStudentDao tachingclassstudentDao;

	@Autowired
	CourseTeachingClassDao courseteachingclassDao;

	@Autowired
	CourseTeachingClassHomeworkReplyDao replyDao;

	@Autowired
	StudentViewDataDao studentDao;

	private List<CourseTeachingClassHomeworkStatisticsViewData> PageQuery(CourseTeachingClassHomeworkBaseinfo baseinfo,
			CourseTeachingClass ctc, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<CourseTeachingClassHomeworkStatisticsViewData> list = new ArrayList<CourseTeachingClassHomeworkStatisticsViewData>();

		List<CourseTeachingClassStudent> liststudent = teachingClassStudentDao.getTeachingClassStudentByTeachingClassId(ctc
				.getT_teaching_class_id());

		for (CourseTeachingClassStudent stu : liststudent) {
			CourseTeachingClassHomeworkStatisticsViewData data = new CourseTeachingClassHomeworkStatisticsViewData();

			// 作业信息
			CourseTeachingClassHomeworkBaseinfoViewData homeworkbaseinfo = homeworkbaseinfoDao
					.getCourseTeachingClassHomeworkBaseinfoViewDataByID(baseinfo.getId());
			data.setHomeworkbaseinfo(homeworkbaseinfo);

			// 作业提交情况
			List<CourseTeachingClassHomeworkSubmitBaseinfoViewData> homeworksubmit = submitDao
					.getViewDataByCourseTeachingClassHomeworkBaseinfoIDAndStudentID(baseinfo.getId(), stu.getT_student_id());
			data.setHomeworksubmit(homeworksubmit);

			// 作业批复情况
			List<CourseTeachingClassHomeworkReply> homeworkreply = new ArrayList<CourseTeachingClassHomeworkReply>();
			for (CourseTeachingClassHomeworkSubmitBaseinfoViewData sub : homeworksubmit) {

				List<CourseTeachingClassHomeworkReply> r = replyDao.getByCourseTeachingClassHomeworkSubmitBaseInfoID(sub
						.getHomeworksubmitbaseinfo().getId());

				if (r != null)
					homeworkreply.addAll(r);
			}
			data.setHomeworkreply(homeworkreply);

			// 学生基本信息
			StudentViewData student = studentDao.getStudentViewDataById(stu.getT_student_id());
			data.setStudent(student);

			list.add(data);
		}

		return list;
	}

	public Page<CourseTeachingClassHomeworkStatisticsViewData> getPage(String t_course_teaching_class_homework_baseinfo_id, int pageNo,
			int pageSize) {

		CourseTeachingClassHomeworkBaseinfo baseinfo = homeworkbaseinfoDao.getByID(t_course_teaching_class_homework_baseinfo_id);

		if (baseinfo == null)
			return null;

		List<CourseTeachingClassHomeworkSubmitBaseinfo> submitlist = submitDao
				.getByCourseTeachingClassHomeworkBaseinfoID(t_course_teaching_class_homework_baseinfo_id);
		if (submitlist == null)
			return null;

		CourseTeachingClass ctc = courseteachingclassDao.getCourseTeachingClassById(baseinfo.getT_course_teaching_class_id());
		int totalCount = teachingClassStudentDao.getStudentCountByTeachingClassId(ctc.getT_teaching_class_id());

		if (totalCount < 1)
			return new Page<CourseTeachingClassHomeworkStatisticsViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassHomeworkStatisticsViewData> data = PageQuery(baseinfo, ctc, pageNo - 1, totalCount);

		return new Page<CourseTeachingClassHomeworkStatisticsViewData>(startIndex, totalCount, totalCount, data);

	}
}
