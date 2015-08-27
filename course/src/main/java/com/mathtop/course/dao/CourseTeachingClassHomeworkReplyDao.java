package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.CourseTeachingClassHomeworkReply;
import com.mathtop.course.domain.CourseTeachingClassHomeworkReplyFile;
import com.mathtop.course.domain.CourseTeachingClassHomeworkReplyViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkSubmitBaseinfo;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassHomeworkReplyDao extends BaseDao<CourseTeachingClassHomeworkReply> {

	@Autowired
	CourseTeachingClassHomeworkSubmitBaseinfoDao homeworksubmitBaseinfoDao;

	@Autowired
	CourseTeachingClassHomeworkReplyFileDao homeworkreplyfileDao;

	// insert
	private final String INSERT_HOMEWORK_REPLY = "INSERT INTO t_course_teaching_class_homework_reply( id,t_course_teaching_class_homework_submit_baseinfo_id, t_teacher_id, title, content, submitdate, modifieddate) VALUES(?,?,?,?,?,?,?)";

	// select
	private final String GET_VIEWDATA_BY_COURSE_TEACHING_CALSS_HOMEWORK_SUBMIT_BASEINFO_ID_AND_STUDENT_ID = "SELECT t_course_teaching_class_homework_reply.id FROM t_course_teaching_class_homework_reply right join t_course_teaching_class_homework_submit_baseinfo on t_course_teaching_class_homework_reply.t_course_teaching_class_homework_submit_baseinfo_id=t_course_teaching_class_homework_submit_baseinfo.id WHERE t_course_teaching_class_homework_submit_baseinfo_id=? and t_student_id=?  limit ?,?";
	private final String GET_COUNT_BY_COURSE_TEACHING_CALSS_HOMEWORK_BASEINFO_ID_AND_STUDENT_ID = "SELECT count(*) FROM t_course_teaching_class_homework_reply right join t_course_teaching_class_homework_submit_baseinfo on t_course_teaching_class_homework_reply.t_course_teaching_class_homework_submit_baseinfo_id=t_course_teaching_class_homework_submit_baseinfo.id  WHERE t_course_teaching_class_homework_submit_baseinfo_id=? and t_student_id=?";
	private final String GET_BY_BY_COURSE_TEACHING_CALSS_HOMEWORK_SUBMIT_BASEINFO_ID = "SELECT id, t_teacher_id,  title, content, submitdate, modifieddate FROM t_course_teaching_class_homework_reply WHERE t_course_teaching_class_homework_submit_baseinfo_id=?";
	private final String GET_BY_ID = "SELECT t_course_teaching_class_homework_submit_baseinfo_id, t_teacher_id,  title, content, submitdate, modifieddate FROM t_course_teaching_class_homework_reply WHERE id=?";

	// DELETE
	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_class_homework_reply WHERE id=?";
	private String DELETE_BY_COURSE_TEACHING_CLASS_HOMEWORK_SUBMIT_BASEINFO_ID = "DELETE FROM t_course_teaching_class_homework_reply WHERE t_course_teaching_class_homework_submit_baseinfo_id=?";
	private String DELETE_BY_TEACHER_ID = "DELETE FROM t_course_teaching_class_homework_reply WHERE t_teacher_id=?";

	// update
	private String UPDATE_BY_ID = "update t_course_teaching_class_homework_reply set t_course_teaching_class_homework_submit_baseinfo_id=?, t_teacher_id=?,  title=?, content=?, submitdate=?, modifieddate=?  WHERE id=?";
	private String UPDATE_SIMPLE_BY_ID = "update t_course_teaching_class_homework_reply set title=?, content=?,  modifieddate=?  WHERE id=?";

	public void update(String id, String t_course_teaching_class_homework_submit_baseinfo_id, String t_teacher_id, String title,
			String content, Date submitdate, Date modifieddate) {
		if (id == null || t_teacher_id == null)
			return;

		Object params[] = new Object[] { t_course_teaching_class_homework_submit_baseinfo_id, t_teacher_id, title, content, submitdate,
				modifieddate, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP,
				Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}

	public void update(String id, String title, String content, Date modifieddate) {
		if (id == null)
			return;

		Object params[] = new Object[] { title, content, modifieddate, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_SIMPLE_BY_ID, params, types);
	}

	/*
	 * 根据用户ID得到用户
	 */
	public CourseTeachingClassHomeworkReply getByID(String id) {

		CourseTeachingClassHomeworkReply reply = new CourseTeachingClassHomeworkReply();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				reply.setId(id);
				reply.setT_course_teaching_class_homework_submit_baseinfo_id(rs
						.getString("t_course_teaching_class_homework_submit_baseinfo_id"));
				reply.setT_teacher_id(rs.getString("t_teacher_id"));
				reply.setSubmitdate(rs.getTimestamp("submitdate"));
				reply.setModifieddate(rs.getTimestamp("modifieddate"));
				reply.setTitle(rs.getString("title"));
				reply.setContent(rs.getString("content"));

			}

		});

		if (reply.getId() == null)
			return null;
		return reply;
	}

	/**
	 * 根据教学班得到通知
	 * */
	public List<CourseTeachingClassHomeworkReply> getByCourseTeachingClassHomeworkSubmitBaseInfoID(
			String t_course_teaching_class_homework_submit_baseinfo_id) {

		List<CourseTeachingClassHomeworkReply> list = new ArrayList<CourseTeachingClassHomeworkReply>();

		getJdbcTemplate().query(GET_BY_BY_COURSE_TEACHING_CALSS_HOMEWORK_SUBMIT_BASEINFO_ID,
				new Object[] { t_course_teaching_class_homework_submit_baseinfo_id }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkReply reply = new CourseTeachingClassHomeworkReply();
						reply.setId(rs.getString("id"));
						reply.setT_course_teaching_class_homework_submit_baseinfo_id(t_course_teaching_class_homework_submit_baseinfo_id);
						reply.setT_teacher_id(rs.getString("t_teacher_id"));
						reply.setSubmitdate(rs.getTimestamp("submitdate"));
						reply.setModifieddate(rs.getTimestamp("modifieddate"));
						reply.setTitle(rs.getString("title"));
						reply.setContent(rs.getString("content"));
						list.add(reply);

					}

				});

		return list;
	}

	/* 增加用户 */
	public String add(CourseTeachingClassHomeworkReply expriment) {
		String id = GUID.getGUID();
		expriment.setId(id);
		Object params[] = new Object[] { expriment.getId(), expriment.getT_course_teaching_class_homework_submit_baseinfo_id(),
				expriment.getT_teacher_id(), expriment.getTitle(), expriment.getContent(), expriment.getSubmitdate(),
				expriment.getModifieddate() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP,
				Types.TIMESTAMP };
		getJdbcTemplate().update(INSERT_HOMEWORK_REPLY, params, types);
		return id;
	}

	public String add(String t_course_teaching_class_homework_submit_baseinfo_id, String t_teacher_id, String title, String content,
			Date submitdate, Date modifieddate) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_teaching_class_homework_submit_baseinfo_id, t_teacher_id, title, content, submitdate,
				modifieddate };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP,
				Types.TIMESTAMP };
		getJdbcTemplate().update(INSERT_HOMEWORK_REPLY, params, types);
		return id;
	}

	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	public void deleteByCourseTeachingClassHomeworkSubmitId(String t_course_teaching_class_homework_submit_baseinfo_id) {
		Object params[] = new Object[] { t_course_teaching_class_homework_submit_baseinfo_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_COURSE_TEACHING_CLASS_HOMEWORK_SUBMIT_BASEINFO_ID, params, types);
	}

	public void deleteByTeacherId(String t_teacher_id) {
		Object params[] = new Object[] { t_teacher_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_TEACHER_ID, params, types);
	}

	public int getCount(String t_course_teaching_class_homework_submit_baseinfo_id, String t_student_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_TEACHING_CALSS_HOMEWORK_BASEINFO_ID_AND_STUDENT_ID,
				new Object[] { t_course_teaching_class_homework_submit_baseinfo_id,t_student_id }, new int[] { Types.VARCHAR,Types.VARCHAR }, Integer.class);
	}

	public CourseTeachingClassHomeworkReplyViewData getCourseTeachingClassHomeworkReplyViewDataByID(String id) {
		CourseTeachingClassHomeworkReplyViewData data = new CourseTeachingClassHomeworkReplyViewData();

		CourseTeachingClassHomeworkReply reply = getByID(id);
		data.setReply(reply);

		CourseTeachingClassHomeworkSubmitBaseinfo homeworksubmitbaseinfo = homeworksubmitBaseinfoDao.getByID(reply
				.getT_course_teaching_class_homework_submit_baseinfo_id());
		data.setHomeworksubmitbaseinfo(homeworksubmitbaseinfo);

		List<CourseTeachingClassHomeworkReplyFile> repplyFileList = homeworkreplyfileDao.getByCourseTeachingClassHomeworkReplyID(reply
				.getId());
		data.setRepplyFileList(repplyFileList);
		return data;
	}

	private List<CourseTeachingClassHomeworkReplyViewData> PageQuery(String t_course_teaching_class_homework_submit_baseinfo_id,
			String t_student_id,int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<CourseTeachingClassHomeworkReplyViewData> list = new ArrayList<CourseTeachingClassHomeworkReplyViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_COURSE_TEACHING_CALSS_HOMEWORK_SUBMIT_BASEINFO_ID_AND_STUDENT_ID,
				new Object[] { t_course_teaching_class_homework_submit_baseinfo_id, t_student_id,PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkReplyViewData data = getCourseTeachingClassHomeworkReplyViewDataByID(rs.getString("id"));

						if(data!=null)
						list.add(data);
						// System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}

	public Page<CourseTeachingClassHomeworkReplyViewData> getPage(String t_course_teaching_class_homework_submit_baseinfo_id,
			String t_student_id, int pageNo, int pageSize) {
		long totalCount = getCount(t_course_teaching_class_homework_submit_baseinfo_id, t_student_id);
		if (totalCount < 1)
			return new Page<CourseTeachingClassHomeworkReplyViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassHomeworkReplyViewData> data = PageQuery(t_course_teaching_class_homework_submit_baseinfo_id, t_student_id,
				pageNo - 1, pageSize);

		return new Page<CourseTeachingClassHomeworkReplyViewData>(startIndex, totalCount, pageSize, data);

	}
}
