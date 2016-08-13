package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.CourseTeachingClassHomeworkReply;
import com.mathtop.course.domain.CourseTeachingClassHomeworkReplyFile;
import com.mathtop.course.domain.CourseTeachingClassHomeworkReplyFileViewData;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassHomeworkReplyFileDao extends BaseDao<CourseTeachingClassHomeworkReplyFile> {

	@Autowired
	CourseTeachingClassHomeworkReplyDao courseTeachingClassHomeworkReplyDao;

	// insert
	private final String INSERT_PLAN = "INSERT INTO t_course_teaching_class_homework_reply_file( id,t_course_teaching_class_homework_reply_id,filename,filepath) VALUES(?,?,?,?)";

	// select
	private final String GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID = "SELECT id FROM t_course_teaching_class_homework_reply_file WHERE t_course_teaching_class_homework_reply_id=?   limit ?,?";
	private final String GET_COUNT_BY_COURSE_TEACHING_CLASS_ID = "SELECT count(*) FROM t_course_teaching_class_homework_reply_file WHERE t_course_teaching_class_homework_reply_id=?";
	private final String GET_BY_COURSE_TEACHING_CLASS_ID = "SELECT id,  filename,filepath FROM t_course_teaching_class_homework_reply_file WHERE t_course_teaching_class_homework_reply_id=?";
	private final String GET_BY_ID = "SELECT t_course_teaching_class_homework_reply_id, filename,filepath FROM t_course_teaching_class_homework_reply_file WHERE id=?";

	// DELETE
	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_class_homework_reply_file WHERE id=?";
	private String DELETE_BY_COURSE_TEACHING_CLASS_ID = "DELETE FROM t_course_teaching_class_homework_reply_file WHERE t_course_teaching_class_homework_reply_id=?";
	

	// update
	private String UPDATE_BY_ID = "update t_course_teaching_class_homework_reply_file set t_course_teaching_class_homework_reply_id=?,  filename=?,filepath=? WHERE id=?";

	public void update(String id, String t_course_teaching_class_homework_baseinfo_id,  String filename, String filepath) {
		if (id == null || t_course_teaching_class_homework_baseinfo_id == null || filename == null || filepath == null)
			return;

		Object params[] = new Object[] {  t_course_teaching_class_homework_baseinfo_id,	 filename,  filepath, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}

	/*
	 * 根据用户ID得到用户
	 */
	public CourseTeachingClassHomeworkReplyFile getByID(String id) {

		CourseTeachingClassHomeworkReplyFile expriment = new CourseTeachingClassHomeworkReplyFile();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				expriment.setId(id);
				expriment.setCourseTeachingClassHomeworkReplyId(rs.getString("t_course_teaching_class_homework_reply_id"));
				
				expriment.setFilename(rs.getString("filename"));
				expriment.setFilepath(rs.getString("filepath"));

			}

		});

		if (expriment.getId() == null)
			return null;
		return expriment;
	}

	/**
	 * 根据
	 * */
	public List<CourseTeachingClassHomeworkReplyFile> getByCourseTeachingClassHomeworkReplyID(String t_course_teaching_class_homework_reply_id) {

		List<CourseTeachingClassHomeworkReplyFile> list = new ArrayList<CourseTeachingClassHomeworkReplyFile>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_homework_reply_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseTeachingClassHomeworkReplyFile expriment = new CourseTeachingClassHomeworkReplyFile();
				expriment.setId(rs.getString("id"));
				
				expriment.setCourseTeachingClassHomeworkReplyId(t_course_teaching_class_homework_reply_id);
				
				expriment.setFilename(rs.getString("filename"));
				expriment.setFilepath(rs.getString("filepath"));
				list.add(expriment);

			}

		});

		return list;
	}

	/* 增加用户 */
	public String add(CourseTeachingClassHomeworkReplyFile expriment) {
		String id = GUID.getGUID();
		expriment.setId(id);
		Object params[] = new Object[] { expriment.getId(), expriment.getCourseTeachingClassHomeworkReplyId(),expriment.getFilename(), expriment.getFilepath() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
		getJdbcTemplate().update(INSERT_PLAN, params, types);
		return id;
	}

	public String add(String t_course_teaching_class_homework_baseinfo_id, String filename, String filepath) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_teaching_class_homework_baseinfo_id,  filename, filepath };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT_PLAN, params, types);
		return id;
	}

	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	public void deleteByCourseTeachingClassId(String t_course_teaching_class_homework_baseinfo_id) {
		Object params[] = new Object[] { t_course_teaching_class_homework_baseinfo_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_COURSE_TEACHING_CLASS_ID, params, types);
	}

	

	long getCount(String t_group_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_group_id }, new int[] { Types.VARCHAR }, Long.class);
	}

	private List<CourseTeachingClassHomeworkReplyFileViewData> PageQuery(String t_course_teaching_class_id, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<CourseTeachingClassHomeworkReplyFileViewData> list = new ArrayList<CourseTeachingClassHomeworkReplyFileViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_id, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkReplyFileViewData data = new CourseTeachingClassHomeworkReplyFileViewData();

						CourseTeachingClassHomeworkReplyFile replyfile = getByID(rs.getString("id"));
						data.setReplyfile(replyfile);

						CourseTeachingClassHomeworkReply reply = courseTeachingClassHomeworkReplyDao.getByID(replyfile.getCourseTeachingClassHomeworkReplyId());
						data.setReply(reply);

						

						list.add(data);
						// System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}

	public Page<CourseTeachingClassHomeworkReplyFileViewData> getPage(String t_course_teaching_class_id, int pageNo, int pageSize) {
		long totalCount = getCount(t_course_teaching_class_id);
		if (totalCount < 1)
			return new Page<CourseTeachingClassHomeworkReplyFileViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassHomeworkReplyFileViewData> data = PageQuery(t_course_teaching_class_id, pageNo - 1, pageSize);

		return new Page<CourseTeachingClassHomeworkReplyFileViewData>(startIndex, totalCount, pageSize, data);

	}
}
