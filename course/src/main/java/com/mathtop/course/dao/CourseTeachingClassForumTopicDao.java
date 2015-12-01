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

import com.mathtop.course.domain.CourseTeachingClassForumTopic;
import com.mathtop.course.domain.CourseTeachingClassForumTopicFile;
import com.mathtop.course.domain.CourseTeachingClassForumTopicViewData;
import com.mathtop.course.domain.UserBasicInfoViewData;
import com.mathtop.course.utility.DateTimeSql;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassForumTopicDao extends BaseDao<CourseTeachingClassForumTopic> {

	@Autowired
	CourseTeachingClassDao courseteachingclassDao;

	@Autowired
	CourseDao courseDao;

	
	
	@Autowired
	UserInfoViewDataDao userinfoDao;
	
	@Autowired
	CourseTeachingClassForumTopicFileDao forumTopicFileDao;

	private final String INSERT_FORUM_TOPIC = "INSERT INTO t_course_teaching_class_forum_topic(id,t_course_teaching_class_id,t_user_id ,title,content,created_date,last_modified_date,view_count,flag) VALUES(?,?,?,?,?,?,?,?,?)";

	private final String GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID = "SELECT id FROM t_course_teaching_class_forum_topic WHERE t_course_teaching_class_id=? order by created_date desc limit ?,?";

	private final String GET_COUNT_BY_COURSE_TEACHING_CLASS_ID = "SELECT count(*) FROM t_course_teaching_class_forum_topic WHERE t_course_teaching_class_id=?";
	private final String GET_VIEW_COUNT_BY_ID = "SELECT view_count FROM t_course_teaching_class_forum_topic WHERE id=?";
	private final String GET_FLAG_BY_ID = "SELECT flag FROM t_course_teaching_class_forum_topic WHERE id=?";
	private final String GET_BY_COURSE_TEACHING_CLASS_ID = "SELECT id,t_user_id ,title,content,created_date,last_modified_date,view_count,flag FROM t_course_teaching_class_forum_topic WHERE t_course_teaching_class_id=?";
	private final String GET_BY_ID = "SELECT t_course_teaching_class_id,t_user_id ,title,content,created_date,last_modified_date,view_count,flag FROM t_course_teaching_class_forum_topic WHERE id=?";
	private final String GET_BY_USER_ID = "SELECT t_course_teaching_class_id,title,content,created_date,last_modified_date,view_count,flag FROM t_course_teaching_class_forum_topic WHERE t_user_id=?";
	private final String GET_BY_COURSE_TEACHING_CLASS_ID_AND_USER_ID = "SELECT title,content,created_date,last_modified_date,view_count,flag FROM t_course_teaching_class_forum_topic WHERE t_course_teaching_class_id=? and t_user_id=?";
	
	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_class_forum_topic WHERE id=?";
	private String DELETE_BY_COURSE_TEACHING_CLASS_ID = "DELETE FROM t_course_teaching_class_forum_topic WHERE t_course_teaching_class_id=?";
	private String DELETE_BY_USER_ID = "DELETE FROM t_course_teaching_class_forum_topic WHERE t_user_id=?";

	private String UPDATE_BY_ID = "update t_course_teaching_class_forum_topic set t_user_id=?,title=?,content=? ,last_modified_date=? WHERE id=?";
	private String UPDATE_VIEW_COUNT_BY_ID = "update t_course_teaching_class_forum_topic set view_count=? WHERE id=?";
	private String UPDATE_FLAG_BY_ID = "update t_course_teaching_class_forum_topic set flag=? WHERE id=?";

	/**
	 * 将浏览次数增1
	 * */
	public void incViewCount(String id) {
		if (id == null)
			return;
		// System.out.println(id);
		long n = getViewCount(id);

		Object params[] = new Object[] { n + 1, id };
		int types[] = new int[] { Types.INTEGER, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_VIEW_COUNT_BY_ID, params, types);
	}

	/**
	 * 得到浏览次数
	 * */
	private long getViewCount(String id) {
		return getJdbcTemplate().queryForObject(GET_VIEW_COUNT_BY_ID, new Object[] { id }, new int[] { Types.VARCHAR }, Long.class);
	}

	/**
	 * 得到标记
	 * */
	public long getFlag(String id) {
		return getJdbcTemplate().queryForObject(GET_FLAG_BY_ID, new Object[] { id }, new int[] { Types.VARCHAR }, Long.class);
	}

	/**
	 * 设计标记
	 * */
	public void setFlag(String id, int flag) {
		Object params[] = new Object[] { flag, id };
		int types[] = new int[] { Types.INTEGER, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_FLAG_BY_ID, params, types);
	}

	/**
	 * 更新
	 * */
	public void update(String id, String t_user_id, String title, String content, String last_modified_date) {
		update(id, t_user_id, title, content, DateTimeSql.GetDateTime(last_modified_date));
	}

	public void update(String id, String t_user_id, String title, String content, Date last_modified_date) {
		if (id == null || t_user_id == null || title == null)
			return;

		Object params[] = new Object[] { t_user_id, title, content, last_modified_date, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);

		incViewCount(id);
	}

	
	
	public String add(String t_course_teaching_class_id, String t_user_id, String title, String content, String created_date,
			String last_modified_date) {
		return add(t_course_teaching_class_id, t_user_id, title, content, DateTimeSql.GetDateTime(created_date),
				DateTimeSql.GetDateTime(last_modified_date));
	}

	public String add(String t_course_teaching_class_id, String t_user_id, String title, String content, Date created_date,
			Date last_modified_date) {
		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_teaching_class_id, t_user_id, title, content, created_date, last_modified_date, 0, 0 };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP,
				Types.TIMESTAMP, Types.INTEGER, Types.INTEGER };
		getJdbcTemplate().update(INSERT_FORUM_TOPIC, params, types);
		return id;
	}

	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	public void deleteByCourseTeachingClassId(String t_course_teaching_class_id) {
		Object params[] = new Object[] { t_course_teaching_class_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_COURSE_TEACHING_CLASS_ID, params, types);
	}

	public void deleteByt_user_id(String t_user_id) {
		Object params[] = new Object[] { t_user_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_USER_ID, params, types);
	}

	long getCount(String t_group_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_group_id },
				new int[] { Types.VARCHAR }, Long.class);
	}
	
	/*
	 * 根据用户ID得到用户
	 */
	public CourseTeachingClassForumTopic getByID(String id) {

		CourseTeachingClassForumTopic topic = new CourseTeachingClassForumTopic();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				topic.setId(id);
				topic.setT_course_teaching_class_id(rs.getString("t_course_teaching_class_id"));
				topic.setT_user_id(rs.getString("t_user_id"));
				topic.setCreated_date(DateTimeSql.GetDateTime(rs.getString("created_date")));
				topic.setLast_modified_date(DateTimeSql.GetDateTime(rs.getString("last_modified_date")));
				topic.setTitle(rs.getString("title"));
				topic.setContent(rs.getString("content"));
				topic.setView_count(Integer.parseInt(rs.getString("view_count")));
				topic.setFlag(Integer.parseInt(rs.getString("flag")));

			}

		});

		if (topic.getId() == null)
			return null;
		return topic;
	}

	public CourseTeachingClassForumTopicViewData getForumTopicViewDataById(String id) {
		CourseTeachingClassForumTopic forumtopic = getByID(id);
		if (forumtopic == null)
			return null;
		
		UserBasicInfoViewData userbasicinfoviewdata = userinfoDao.getUserBasicInfoViewDataByt_user_id(forumtopic.getT_user_id());
		if (userbasicinfoviewdata == null)
			return null;
		
		List<CourseTeachingClassForumTopicFile> topicFileList=forumTopicFileDao.getByForumTopicID(forumtopic.getId());
		if (topicFileList == null)
			return null;

		CourseTeachingClassForumTopicViewData data = new CourseTeachingClassForumTopicViewData();
		

		data.setForumtopic(forumtopic);

		
		data.setUserbasicinfoviewdata(userbasicinfoviewdata);
		data.setTopicFileList(topicFileList);

		return data;
	}

	/**
	 * 根据教学班得到论坛id
	 * */
	public List<CourseTeachingClassForumTopic> getByCourseTeachingClassID(String t_course_teaching_class_id) {

		List<CourseTeachingClassForumTopic> list = new ArrayList<CourseTeachingClassForumTopic>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseTeachingClassForumTopic topic = new CourseTeachingClassForumTopic();
				topic.setId(rs.getString("id"));
				topic.setT_course_teaching_class_id(t_course_teaching_class_id);
				topic.setT_user_id(rs.getString("t_user_id"));
				topic.setCreated_date(DateTimeSql.GetDateTime(rs.getString("created_date")));
				topic.setLast_modified_date(DateTimeSql.GetDateTime(rs.getString("last_modified_date")));
				topic.setTitle(rs.getString("title"));
				topic.setContent(rs.getString("content"));
				topic.setView_count(Integer.parseInt(rs.getString("view_count")));
				topic.setFlag(Integer.parseInt(rs.getString("flag")));

				list.add(topic);

			}

		});

		return list;
	}

	/**
	 * 根据t_user_id得到论坛id
	 * */
	public List<CourseTeachingClassForumTopic> getByUserID(String t_user_id) {

		List<CourseTeachingClassForumTopic> list = new ArrayList<CourseTeachingClassForumTopic>();

		getJdbcTemplate().query(GET_BY_USER_ID, new Object[] { t_user_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseTeachingClassForumTopic topic = new CourseTeachingClassForumTopic();
				topic.setId(rs.getString("id"));
				topic.setT_course_teaching_class_id(rs.getString("t_course_teaching_class_id"));
				topic.setT_user_id(t_user_id);
				topic.setCreated_date(DateTimeSql.GetDateTime(rs.getString("created_date")));
				topic.setLast_modified_date(DateTimeSql.GetDateTime(rs.getString("last_modified_date")));
				topic.setTitle(rs.getString("title"));
				topic.setContent(rs.getString("content"));
				topic.setView_count(Integer.parseInt(rs.getString("view_count")));
				topic.setFlag(Integer.parseInt(rs.getString("flag")));

				list.add(topic);

			}

		});

		return list;
	}
	
	/**
	 * 根据t_course_teaching_class_id和t_user_id得到论坛id
	 * */
	public List<CourseTeachingClassForumTopic> getByCourseTeachingClassIDAndUserID(String t_course_teaching_class_id,String t_user_id) {

		List<CourseTeachingClassForumTopic> list = new ArrayList<CourseTeachingClassForumTopic>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID_AND_USER_ID, new Object[] {t_course_teaching_class_id, t_user_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseTeachingClassForumTopic topic = new CourseTeachingClassForumTopic();
				topic.setId(rs.getString("id"));
				topic.setT_course_teaching_class_id(t_course_teaching_class_id);
				topic.setT_user_id(t_user_id);
				topic.setCreated_date(DateTimeSql.GetDateTime(rs.getString("created_date")));
				topic.setLast_modified_date(DateTimeSql.GetDateTime(rs.getString("last_modified_date")));
				topic.setTitle(rs.getString("title"));
				topic.setContent(rs.getString("content"));
				topic.setView_count(Integer.parseInt(rs.getString("view_count")));
				topic.setFlag(Integer.parseInt(rs.getString("flag")));

				list.add(topic);

			}

		});

		return list;
	}


	private List<CourseTeachingClassForumTopicViewData> PageQuery(String t_course_teaching_class_id, int PageBegin, int PageSize) {


		List<CourseTeachingClassForumTopicViewData> list = new ArrayList<CourseTeachingClassForumTopicViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID,
				new Object[] { t_course_teaching_class_id, PageBegin * PageSize, PageSize }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassForumTopicViewData data = getForumTopicViewDataById(rs.getString("id"));

						if (data != null)
							list.add(data);
					
					}

				});
		return list;
	}

	public Page<CourseTeachingClassForumTopicViewData> getPage(String t_course_teaching_class_id, int pageNo, int pageSize) {
		long totalCount = getCount(t_course_teaching_class_id);
		if (totalCount < 1)
			return new Page<CourseTeachingClassForumTopicViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassForumTopicViewData> data = PageQuery(t_course_teaching_class_id, pageNo - 1, pageSize);

		return new Page<CourseTeachingClassForumTopicViewData>(startIndex, totalCount, pageSize, data);

	}
}
