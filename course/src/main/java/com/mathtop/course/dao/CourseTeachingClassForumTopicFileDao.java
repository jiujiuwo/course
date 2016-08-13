package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.CourseTeachingClassForumTopic;
import com.mathtop.course.domain.CourseTeachingClassForumTopicFile;
import com.mathtop.course.domain.CourseTeachingClassForumTopicFileViewData;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassForumTopicFileDao extends BaseDao<CourseTeachingClassForumTopicFile> {

	@Autowired
	CourseTeachingClassForumTopicDao forumTopicDao;

	// insert
	private final String INSERT_PLAN = "INSERT INTO t_course_teaching_class_forum_topic_file( id,t_course_teaching_class_forum_topic_id,filename,filepath) VALUES(?,?,?,?)";

	// select
	private final String GET_VIEWDATA_BY_FORUM_TOPIC_ID = "SELECT id FROM t_course_teaching_class_forum_topic_file WHERE t_course_teaching_class_forum_topic_id=?   limit ?,?";
	private final String GET_COUNT_BY_FORUM_TOPIC_ID = "SELECT count(*) FROM t_course_teaching_class_forum_topic_file WHERE t_course_teaching_class_forum_topic_id=?";
	private final String GET_BY_FORUM_TOPIC_ID = "SELECT id,  filename,filepath FROM t_course_teaching_class_forum_topic_file WHERE t_course_teaching_class_forum_topic_id=?";
	private final String GET_BY_ID = "SELECT t_course_teaching_class_forum_topic_id, filename,filepath FROM t_course_teaching_class_forum_topic_file WHERE id=?";

	// DELETE
	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_class_forum_topic_file WHERE id=?";
	private String DELETE_BY_FORUM_TOPIC_ID = "DELETE FROM t_course_teaching_class_forum_topic_file WHERE t_course_teaching_class_forum_topic_id=?";

	// update
	private String UPDATE_BY_ID = "update t_course_teaching_class_forum_topic_file set t_course_teaching_class_forum_topic_id=?, filename=?,filepath=? WHERE id=?";

	public void update(String id, String t_forum_topic_id, String filename, String filepath) {
		if (id == null || t_forum_topic_id == null || filename == null || filepath == null)
			return;

		Object params[] = new Object[] { t_forum_topic_id, filename, filepath, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}

	/*
	 * 根据用户ID得到用户
	 */
	public CourseTeachingClassForumTopicFile getByID(String id) {

		CourseTeachingClassForumTopicFile submitfile = new CourseTeachingClassForumTopicFile();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				submitfile.setId(id);
				submitfile.setCourseTeachingClassForumTopicId(rs.getString("t_course_teaching_class_forum_topic_id"));

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
	public List<CourseTeachingClassForumTopicFile> getByForumTopicID(String t_course_teaching_class_forum_topic_id) {

		List<CourseTeachingClassForumTopicFile> list = new ArrayList<CourseTeachingClassForumTopicFile>();

		getJdbcTemplate().query(GET_BY_FORUM_TOPIC_ID, new Object[] { t_course_teaching_class_forum_topic_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseTeachingClassForumTopicFile submitfile = new CourseTeachingClassForumTopicFile();
				submitfile.setId(rs.getString("id"));

				submitfile.setCourseTeachingClassForumTopicId(t_course_teaching_class_forum_topic_id);

				submitfile.setFilename(rs.getString("filename"));
				submitfile.setFilepath(rs.getString("filepath"));
				list.add(submitfile);

			}

		});

		return list;
	}

	/* 增加用户 */
	public String add(CourseTeachingClassForumTopicFile submitfile) {
		String id = GUID.getGUID();
		submitfile.setId(id);
		Object params[] = new Object[] { submitfile.getId(), submitfile.getCourseTeachingClassForumTopicId(), submitfile.getFilename(),
				submitfile.getFilepath() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT_PLAN, params, types);
		return id;
	}

	public String add(String t_course_teaching_class_forum_topic_id, String filename, String filepath) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_teaching_class_forum_topic_id, filename, filepath };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT_PLAN, params, types);
		return id;
	}

	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	public void deleteByCourseTeachingClassId(String t_forum_topic_id) {
		Object params[] = new Object[] { t_forum_topic_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_FORUM_TOPIC_ID, params, types);
	}

	long getCount(String t_course_teaching_class_forum_topic_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_FORUM_TOPIC_ID, new Object[] { t_course_teaching_class_forum_topic_id },
				new int[] { Types.VARCHAR }, Long.class);
	}

	private List<CourseTeachingClassForumTopicFileViewData> PageQuery(String t_course_teaching_class_id, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<CourseTeachingClassForumTopicFileViewData> list = new ArrayList<CourseTeachingClassForumTopicFileViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_FORUM_TOPIC_ID,
				new Object[] { t_course_teaching_class_id, PageBegin * PageSize, PageSize }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassForumTopicFileViewData data = new CourseTeachingClassForumTopicFileViewData();

						CourseTeachingClassForumTopicFile topicFile = getByID(rs.getString("id"));
						data.setTopicFile(topicFile);

						CourseTeachingClassForumTopic topic = forumTopicDao.getByID(topicFile.getCourseTeachingClassForumTopicId());
						data.setTopic(topic);

						list.add(data);
						// System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}

	public Page<CourseTeachingClassForumTopicFileViewData> getPage(String t_course_teaching_class_forum_topic_id, int pageNo, int pageSize) {
		long totalCount = getCount(t_course_teaching_class_forum_topic_id);
		if (totalCount < 1)
			return new Page<CourseTeachingClassForumTopicFileViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassForumTopicFileViewData> data = PageQuery(t_course_teaching_class_forum_topic_id, pageNo - 1, pageSize);

		return new Page<CourseTeachingClassForumTopicFileViewData>(startIndex, totalCount, pageSize, data);

	}
}
