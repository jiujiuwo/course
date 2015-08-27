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

import com.mathtop.course.domain.CourseTeachingClassForumReply;
import com.mathtop.course.domain.CourseTeachingClassForumReplyFile;
import com.mathtop.course.domain.CourseTeachingClassForumReplyViewData;
import com.mathtop.course.domain.UserBasicInfoViewData;
import com.mathtop.course.utility.DateTimeSql;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassForumReplyDao extends BaseDao<CourseTeachingClassForumReply> {

	@Autowired
	CourseDao courseDao;

	@Autowired
	UserInfoViewDataDao usersessioninfoDao;
	
	@Autowired
	CourseTeachingClassForumReplyFileDao forumReplyfileDao;

	private final String INSERT_FORUM_TOPIC = "INSERT INTO t_course_teaching_class_forum_reply(id,t_course_teaching_class_forum_topic_id,t_user_id ,title,content,created_date,last_modified_date) VALUES(?,?,?,?,?,?,?)";

	private final String GET_VIEWDATA_BY_FORUM_TOPIC_ID = "SELECT id FROM t_course_teaching_class_forum_reply WHERE t_course_teaching_class_forum_topic_id=? order by created_date asc limit ?,?";

	private final String GET_COUNT_BY_COURSE_FORUM_TOPIC_ID = "SELECT count(*) FROM t_course_teaching_class_forum_reply WHERE t_course_teaching_class_forum_topic_id=?";

	private final String GET_BY_COURSE_FORUM_TOPIC_ID = "SELECT id,t_user_id ,title,content,created_date,last_modified_date FROM t_course_teaching_class_forum_reply WHERE t_course_teaching_class_forum_topic_id=?";
	private final String GET_BY_ID = "SELECT t_course_teaching_class_forum_topic_id,t_user_id ,title,content,created_date,last_modified_date FROM t_course_teaching_class_forum_reply WHERE id=?";

	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_class_forum_reply WHERE id=?";
	private String DELETE_BY_COURSE_FORUM_TOPIC_ID = "DELETE FROM t_course_teaching_class_forum_reply WHERE t_course_teaching_class_forum_topic_id=?";
	private String DELETE_BY_USER_ID = "DELETE FROM t_course_teaching_class_forum_reply WHERE t_user_id=?";

	private String UPDATE_BY_ID = "UPDATE t_course_teaching_class_forum_reply set t_user_id=?,title=?,content=? ,last_modified_date=? WHERE id=?";

	/**
	 * 更新
	 * */
	public void update(String id, String t_user_id, String title, String content, Date last_modified_date) {
		if (id == null || t_user_id == null || title == null)
			return;

		Object params[] = new Object[] { t_user_id, title, content, last_modified_date, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);

	}

	/*
	 * 根据用户ID得到用户
	 */
	public CourseTeachingClassForumReply getByID(String id) {

		CourseTeachingClassForumReply topic = new CourseTeachingClassForumReply();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				topic.setId(id);
				topic.setT_course_teaching_class_forum_topic_id(rs.getString("t_course_teaching_class_forum_topic_id"));
				topic.setT_user_id(rs.getString("t_user_id"));
				topic.setCreated_date(DateTimeSql.GetDateTime(rs.getString("created_date")));
				topic.setLast_modified_date(DateTimeSql.GetDateTime(rs.getString("last_modified_date")));
				topic.setTitle(rs.getString("title"));
				topic.setContent(rs.getString("content"));

			}

		});

		if (topic.getId() == null)
			return null;
		return topic;
	}

	/**
	 * 根据教学班得到论坛id
	 * */
	public List<CourseTeachingClassForumReply> getByForumTopicID(String t_course_teaching_class_forum_topic_id) {

		List<CourseTeachingClassForumReply> list = new ArrayList<CourseTeachingClassForumReply>();

		getJdbcTemplate().query(GET_BY_COURSE_FORUM_TOPIC_ID, new Object[] { t_course_teaching_class_forum_topic_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseTeachingClassForumReply topic = new CourseTeachingClassForumReply();
				topic.setId(rs.getString("id"));
				topic.setT_course_teaching_class_forum_topic_id(t_course_teaching_class_forum_topic_id);
				topic.setT_user_id(rs.getString("t_user_id"));
				topic.setCreated_date(DateTimeSql.GetDateTime(rs.getString("created_date")));
				topic.setLast_modified_date(DateTimeSql.GetDateTime(rs.getString("last_modified_date")));
				topic.setTitle(rs.getString("title"));
				topic.setContent(rs.getString("content"));

				list.add(topic);

			}

		});

		return list;
	}

	public String add(String t_course_teaching_class_forum_topic_id, String t_user_id, String title, String content, String created_date,
			String last_modified_date) {
		return add(t_course_teaching_class_forum_topic_id, t_user_id, title, content, DateTimeSql.GetDateTime(created_date),
				DateTimeSql.GetDateTime(last_modified_date));
	}

	public String add(String t_course_teaching_class_forum_topic_id, String t_user_id, String title, String content, Date created_date, Date last_modified_date) {
		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_teaching_class_forum_topic_id, t_user_id, title, content, created_date, last_modified_date };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP,
				Types.TIMESTAMP };
		getJdbcTemplate().update(INSERT_FORUM_TOPIC, params, types);
		return id;
	}

	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	public void deleteByForumTopicId(String t_forum_topic_id) {
		Object params[] = new Object[] { t_forum_topic_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_COURSE_FORUM_TOPIC_ID, params, types);
	}

	public void deleteByt_user_id(String t_user_id) {
		Object params[] = new Object[] { t_user_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_USER_ID, params, types);
	}

	long getCount(String t_course_teaching_class_forum_topic_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_FORUM_TOPIC_ID, new Object[] { t_course_teaching_class_forum_topic_id },
				new int[] { Types.VARCHAR }, Long.class);
	}

	public CourseTeachingClassForumReplyViewData getForumReplyViewDataByID(String id) {

		CourseTeachingClassForumReply forumreply = getByID(id);
		if (forumreply == null)
			return null;

		UserBasicInfoViewData userbasicinfoviewdata = usersessioninfoDao.getUserBasicInfoViewDataByt_user_id(forumreply.getT_user_id());
		if (userbasicinfoviewdata == null)
			return null;
		
		List<CourseTeachingClassForumReplyFile> replyFileList=forumReplyfileDao.getByForumReplyID(forumreply.getId());
		if (replyFileList == null)
			return null;

		CourseTeachingClassForumReplyViewData data = new CourseTeachingClassForumReplyViewData();
		data.setForumreply(forumreply);
		data.setUserbasicinfoviewdata(userbasicinfoviewdata);
		data.setReplyFileList(replyFileList);

		return data;
	}

	private List<CourseTeachingClassForumReplyViewData> PageQuery(String t_course_teaching_class_forum_topic_id, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<CourseTeachingClassForumReplyViewData> list = new ArrayList<CourseTeachingClassForumReplyViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_FORUM_TOPIC_ID, new Object[] { t_course_teaching_class_forum_topic_id, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassForumReplyViewData data = getForumReplyViewDataByID(rs.getString("id"));

						if (data != null)
							list.add(data);
						// System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}

	public Page<CourseTeachingClassForumReplyViewData> getPage(String t_course_teaching_class_forum_topic_id, int pageNo, int pageSize) {
		long totalCount = getCount(t_course_teaching_class_forum_topic_id);
		if (totalCount < 1)
			return new Page<CourseTeachingClassForumReplyViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassForumReplyViewData> data = PageQuery(t_course_teaching_class_forum_topic_id, pageNo - 1, pageSize);

		return new Page<CourseTeachingClassForumReplyViewData>(startIndex, totalCount, pageSize, data);

	}
}
