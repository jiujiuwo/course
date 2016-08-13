package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.CourseTeachingClassForumReply;
import com.mathtop.course.domain.CourseTeachingClassForumReplyFile;
import com.mathtop.course.domain.CourseTeachingClassForumReplyFileViewData;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassForumReplyFileDao extends BaseDao<CourseTeachingClassForumReplyFile> {

	@Autowired
	CourseTeachingClassForumReplyDao forumReplyDao;

	// insert
	private final String INSERT_PLAN = "INSERT INTO t_course_teaching_class_forum_reply_file( id,t_course_teaching_class_forum_reply_id,filename,filepath) VALUES(?,?,?,?)";

	// select
	private final String GET_VIEWDATA_BY_FORUM_REPLY_ID = "SELECT id FROM t_course_teaching_class_forum_reply_file WHERE t_course_teaching_class_forum_reply_id=?   limit ?,?";
	private final String GET_COUNT_BY_FORUM_REPLY_ID = "SELECT count(*) FROM t_course_teaching_class_forum_reply_file WHERE t_course_teaching_class_forum_reply_id=?";
	private final String GET_BY_COURSE_TEACHING_CLASS_ID = "SELECT id,  filename,filepath FROM t_course_teaching_class_forum_reply_file WHERE t_course_teaching_class_forum_reply_id=?";
	private final String GET_BY_ID = "SELECT t_course_teaching_class_forum_reply_id, filename,filepath FROM t_course_teaching_class_forum_reply_file WHERE id=?";

	// DELETE
	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_class_forum_reply_file WHERE id=?";
	private String DELETE_BY_FORUM_REPLY_ID = "DELETE FROM t_course_teaching_class_forum_reply_file WHERE t_course_teaching_class_forum_reply_id=?";

	// update
	private String UPDATE_BY_ID = "UPDATE t_course_teaching_class_forum_reply_file set t_course_teaching_class_forum_reply_id=?, filename=?,filepath=? WHERE id=?";

	public void update(String id, String t_forum_reply_id, String filename, String filepath) {
		if (id == null || t_forum_reply_id == null || filename == null || filepath == null)
			return;

		Object params[] = new Object[] { t_forum_reply_id, filename, filepath, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}

	/*
	 * 根据用户ID得到用户
	 */
	public CourseTeachingClassForumReplyFile getByID(String id) {

		CourseTeachingClassForumReplyFile submitfile = new CourseTeachingClassForumReplyFile();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				submitfile.setId(id);
				submitfile.setForumReplyId(rs.getString("t_forum_reply_id"));

				submitfile.setFilename(rs.getString("filename"));
				submitfile.setFilepath(rs.getString("filepath"));

			}

		});

		if (submitfile.getId() == null)
			return null;
		return submitfile;
	}

	/**
	 * 根据教学班得到通知
	 * */
	public List<CourseTeachingClassForumReplyFile> getByForumReplyID(String t_forum_reply_id) {

		List<CourseTeachingClassForumReplyFile> list = new ArrayList<CourseTeachingClassForumReplyFile>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_forum_reply_id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassForumReplyFile submitfile = new CourseTeachingClassForumReplyFile();
						submitfile.setId(rs.getString("id"));

						submitfile.setForumReplyId(t_forum_reply_id);

						submitfile.setFilename(rs.getString("filename"));
						submitfile.setFilepath(rs.getString("filepath"));
						list.add(submitfile);

					}

				});

		return list;
	}

	/* 增加用户 */
	public String add(CourseTeachingClassForumReplyFile submitfile) {
		String id = GUID.getGUID();
		submitfile.setId(id);
		Object params[] = new Object[] { submitfile.getId(), submitfile.getForumReplyId(), submitfile.getFilename(),
				submitfile.getFilepath() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT_PLAN, params, types);
		return id;
	}

	public String add(String t_forum_reply_id, String filename, String filepath) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_forum_reply_id, filename, filepath };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT_PLAN, params, types);
		return id;
	}

	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	public void deleteByForumReplyId(String t_forum_reply_id) {
		Object params[] = new Object[] { t_forum_reply_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_FORUM_REPLY_ID, params, types);
	}

	long getCount(String t_forum_reply_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_FORUM_REPLY_ID, new Object[] { t_forum_reply_id }, new int[] { Types.VARCHAR }, Long.class);
	}

	private List<CourseTeachingClassForumReplyFileViewData> PageQuery(String t_forum_reply_id, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<CourseTeachingClassForumReplyFileViewData> list = new ArrayList<CourseTeachingClassForumReplyFileViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_FORUM_REPLY_ID, new Object[] { t_forum_reply_id, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassForumReplyFileViewData data = new CourseTeachingClassForumReplyFileViewData();

						CourseTeachingClassForumReplyFile replyFile = getByID(rs.getString("id"));
						data.setReplyFile(replyFile);

						CourseTeachingClassForumReply reply = forumReplyDao
								.getByID(replyFile.getForumReplyId());
						data.setReply(reply);

						list.add(data);
						// System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}

	public Page<CourseTeachingClassForumReplyFileViewData> getPage(String t_forum_reply_id, int pageNo, int pageSize) {
		long totalCount = getCount(t_forum_reply_id);
		if (totalCount < 1)
			return new Page<CourseTeachingClassForumReplyFileViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassForumReplyFileViewData> data = PageQuery(t_forum_reply_id, pageNo - 1, pageSize);

		return new Page<CourseTeachingClassForumReplyFileViewData>(startIndex, totalCount, pageSize, data);

	}
}
