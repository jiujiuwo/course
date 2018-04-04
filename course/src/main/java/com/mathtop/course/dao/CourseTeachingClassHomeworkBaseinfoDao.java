package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfo;
import com.mathtop.course.domain.FileRequirementManager;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassHomeworkBaseinfoDao extends BaseDao<CourseTeachingClassHomeworkBaseinfo> {

	// insert
	private final String INSERT_HOMEWORKBASEINFO = "INSERT INTO t_course_teaching_class_homework_baseinfo( id,t_course_teaching_class_id, t_teacher_id, t_course_teaching_class_homework_type_id, flag,file_requirement,title, content, pubdate, enddate) VALUES(?,?,?,?,?,?,?,?,?,?)";

	// select
	private final String GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID = "SELECT id FROM t_course_teaching_class_homework_baseinfo WHERE t_course_teaching_class_id=? and t_course_teaching_class_homework_type_id=? order by pubdate desc limit ?,?";
	private final String GET_COUNT_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID = "SELECT count(*) FROM t_course_teaching_class_homework_baseinfo WHERE t_course_teaching_class_id=? and t_course_teaching_class_homework_type_id=?";
	private final String GET_BY_COURSE_TEACHING_CLASS_ID = "SELECT id, t_teacher_id, t_course_teaching_class_homework_type_id, flag,file_requirement,title, content, pubdate, enddate FROM t_course_teaching_class_homework_baseinfo WHERE t_course_teaching_class_id=?";
	private final String GET_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID = "SELECT id, t_teacher_id,  flag,file_requirement,title, content, pubdate, enddate FROM t_course_teaching_class_homework_baseinfo WHERE t_course_teaching_class_id=? and t_course_teaching_class_homework_type_id=?";
	private final String GET_BY_ID = "SELECT t_course_teaching_class_id, t_teacher_id, t_course_teaching_class_homework_type_id,flag,file_requirement, title, content, pubdate, enddate FROM t_course_teaching_class_homework_baseinfo WHERE id=?";
	private final String GET_BY_TEACHER_ID = "SELECT id,t_course_teaching_class_id,  t_course_teaching_class_homework_type_id,flag,file_requirement, title, content, pubdate, enddate FROM t_course_teaching_class_homework_baseinfo WHERE t_teacher_id=?";
	private final String GET_BY_HOMEWORK_TYPE_ID = "SELECT id,t_course_teaching_class_id,  t_teacher_id,flag,file_requirement, title, content, pubdate, enddate FROM t_course_teaching_class_homework_baseinfo WHERE t_course_teaching_class_homework_type_id=?";

	// DELETE
	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_class_homework_baseinfo WHERE id=?";
	private String DELETE_BY_COURSE_TEACHING_CLASS_ID = "DELETE FROM t_course_teaching_class_homework_baseinfo WHERE t_course_teaching_class_id=?";
	private String DELETE_BY_TEACHER_ID = "DELETE FROM t_course_teaching_class_homework_baseinfo WHERE t_teacher_id=?";

	// update
	private String UPDATE_BY_ID = "update t_course_teaching_class_homework_baseinfo set t_course_teaching_class_id=?, t_teacher_id=?, t_course_teaching_class_homework_type_id=?, flag=?,file_requirement=?,title=?, content=?, pubdate=?, enddate=?  WHERE id=?";
	private String UPDATE_SIMPLE_BY_ID = "update t_course_teaching_class_homework_baseinfo set t_teacher_id=?,  flag=?,file_requirement=?,title=?, content=?,  enddate=?  WHERE id=?";
	private String UPDATE_SIMPLE0_BY_ID = "update t_course_teaching_class_homework_baseinfo set t_teacher_id=?,  title=?, content=?,  enddate=?  WHERE id=?";

	public void update(String id, String t_course_teaching_class_id, String t_teacher_id,
			String t_course_teaching_class_homework_type_id, Integer flag, String file_requirement, String title,
			String content, Date pubdate, Date enddate) {
		if (id == null || t_teacher_id == null)
			return;
		
		getJdbcTemplate().update(UPDATE_BY_ID,  t_course_teaching_class_id, t_teacher_id,
				t_course_teaching_class_homework_type_id, flag, file_requirement, title, content, pubdate, enddate,
				id );
	}

	public void update(String id, String t_teacher_id, Integer flag, String file_requirement, String title,
			String content, Date enddate) {
		if (id == null || t_teacher_id == null)
			return;

		getJdbcTemplate().update(UPDATE_SIMPLE_BY_ID,  t_teacher_id, flag, file_requirement, title, content, enddate, id);
	}

	public void update(String id, String t_teacher_id, String title, String content, Date enddate) {
		if (id == null || t_teacher_id == null)
			return;

		
		getJdbcTemplate().update(UPDATE_SIMPLE0_BY_ID, t_teacher_id, title, content, enddate, id);
	}

	/* 增加用户 */
	public String add(CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo) {
		String id = GUID.getGUID();
		homeworkbaseinfo.setId(id);
		Object params[] = new Object[] { homeworkbaseinfo.getId(), homeworkbaseinfo.getCourseTeachingClassId(),
				homeworkbaseinfo.getTeacherId(), homeworkbaseinfo.getCourseTeachingClassHomeworkTypeId(),
				homeworkbaseinfo.getFlag(), homeworkbaseinfo.getFileRequirement(), homeworkbaseinfo.getTitle(),
				homeworkbaseinfo.getContent(), homeworkbaseinfo.getPubdate(), homeworkbaseinfo.getEnddate(), };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP };
		getJdbcTemplate().update(INSERT_HOMEWORKBASEINFO, params, types);
		return id;
	}

	public String add(String t_course_teaching_class_id, String t_teacher_id,
			String t_course_teaching_class_homework_type_id, Integer flag, String file_requirement, String title,
			String content, Date pubdate, Date enddate) {

		String id = GUID.getGUID();

		
		getJdbcTemplate().update(INSERT_HOMEWORKBASEINFO, id, t_course_teaching_class_id, t_teacher_id,
				t_course_teaching_class_homework_type_id, flag, file_requirement, title, content, pubdate, enddate);
		return id;
	}

	public void deleteById(String id) {
		getJdbcTemplate().update(DELETE_BY_ID, id);
	}

	public void deleteByCourseTeachingClassId(String t_course_teaching_class_id) {
		
		getJdbcTemplate().update(DELETE_BY_COURSE_TEACHING_CLASS_ID,t_course_teaching_class_id);
	}

	public void deleteByTeacherId(String t_teacher_id) {
		Object params[] = new Object[] { t_teacher_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_TEACHER_ID, params, types);
	}

	public long getCount(String t_course_teaching_class_id, String t_course_teaching_class_homeworktype_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID,
				new Object[] { t_course_teaching_class_id, t_course_teaching_class_homeworktype_id },
				new int[] { Types.VARCHAR, Types.VARCHAR }, Long.class);
	}

	/*
	 * 根据用户ID得到用户
	 */
	public CourseTeachingClassHomeworkBaseinfo getByID(String id) {

		CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo = new CourseTeachingClassHomeworkBaseinfo();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				homeworkbaseinfo.setId(id);
				homeworkbaseinfo.setCourseTeachingClassId(rs.getString("t_course_teaching_class_id"));
				homeworkbaseinfo.setTeacherId(rs.getString("t_teacher_id"));
				homeworkbaseinfo
						.setCourseTeachingClassHomeworkTypeId(rs.getString("t_course_teaching_class_homework_type_id"));
				homeworkbaseinfo.setEnddate(rs.getTimestamp("enddate"));
				homeworkbaseinfo.setPubdate(rs.getTimestamp("pubdate"));
				homeworkbaseinfo.setFlag(rs.getInt("flag"));

				FileRequirementManager fileRequirementManager = new FileRequirementManager();
				fileRequirementManager.ParseJson(rs.getString("file_requirement"));
				homeworkbaseinfo.setFileRequirement(fileRequirementManager);

				homeworkbaseinfo.setTitle(rs.getString("title"));
				homeworkbaseinfo.setContent(rs.getString("content"));

			}

		});

		if (homeworkbaseinfo.getId() == null)
			return null;
		return homeworkbaseinfo;
	}

	/**
	 * 根据教学班得到通知
	 */
	public List<CourseTeachingClassHomeworkBaseinfo> getByHomeWorkTypeID(
			String t_course_teaching_class_homework_type_id) {

		List<CourseTeachingClassHomeworkBaseinfo> list = new ArrayList<CourseTeachingClassHomeworkBaseinfo>();

		getJdbcTemplate().query(GET_BY_HOMEWORK_TYPE_ID, new Object[] { t_course_teaching_class_homework_type_id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo = new CourseTeachingClassHomeworkBaseinfo();
						homeworkbaseinfo.setId(rs.getString("id"));
						homeworkbaseinfo.setCourseTeachingClassId(rs.getString("t_course_teaching_class_id"));
						homeworkbaseinfo.setTeacherId(rs.getString("t_teacher_id"));
						homeworkbaseinfo.setCourseTeachingClassHomeworkTypeId(t_course_teaching_class_homework_type_id);
						homeworkbaseinfo.setEnddate(rs.getTimestamp("enddate"));
						homeworkbaseinfo.setPubdate(rs.getTimestamp("pubdate"));
						homeworkbaseinfo.setFlag(rs.getInt("flag"));

						FileRequirementManager fileRequirementManager = new FileRequirementManager();
						fileRequirementManager.ParseJson(rs.getString("file_requirement"));
						homeworkbaseinfo.setFileRequirement(fileRequirementManager);

						homeworkbaseinfo.setTitle(rs.getString("title"));
						homeworkbaseinfo.setContent(rs.getString("content"));

						list.add(homeworkbaseinfo);

					}

				});

		return list;
	}

	/**
	 * 根据教学班得到通知
	 */
	public List<CourseTeachingClassHomeworkBaseinfo> getByCourseTeachingClassID(String t_course_teaching_class_id) {

		List<CourseTeachingClassHomeworkBaseinfo> list = new ArrayList<CourseTeachingClassHomeworkBaseinfo>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo = new CourseTeachingClassHomeworkBaseinfo();
						homeworkbaseinfo.setId(rs.getString("id"));
						homeworkbaseinfo.setCourseTeachingClassId(t_course_teaching_class_id);
						homeworkbaseinfo.setTeacherId(rs.getString("t_teacher_id"));
						homeworkbaseinfo.setCourseTeachingClassHomeworkTypeId(
								rs.getString("t_course_teaching_class_homework_type_id"));
						homeworkbaseinfo.setEnddate(rs.getTimestamp("enddate"));
						homeworkbaseinfo.setPubdate(rs.getTimestamp("pubdate"));
						homeworkbaseinfo.setFlag(rs.getInt("flag"));

						FileRequirementManager fileRequirementManager = new FileRequirementManager();
						fileRequirementManager.ParseJson(rs.getString("file_requirement"));
						homeworkbaseinfo.setFileRequirement(fileRequirementManager);

						homeworkbaseinfo.setTitle(rs.getString("title"));
						homeworkbaseinfo.setContent(rs.getString("content"));

						list.add(homeworkbaseinfo);

					}

				});

		return list;
	}

	/**
	 * 根据教学班得到通知
	 */
	public List<CourseTeachingClassHomeworkBaseinfo> getByCourseTeachingClassIDAndHomeworkTypeId(
			String t_course_teaching_class_id, String t_course_teaching_class_homeworktype_id) {

		List<CourseTeachingClassHomeworkBaseinfo> list = new ArrayList<CourseTeachingClassHomeworkBaseinfo>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID,
				new Object[] { t_course_teaching_class_id, t_course_teaching_class_homeworktype_id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo = new CourseTeachingClassHomeworkBaseinfo();
						homeworkbaseinfo.setId(rs.getString("id"));
						homeworkbaseinfo.setCourseTeachingClassId(t_course_teaching_class_id);
						homeworkbaseinfo.setTeacherId(rs.getString("t_teacher_id"));
						homeworkbaseinfo.setCourseTeachingClassHomeworkTypeId(t_course_teaching_class_homeworktype_id);
						homeworkbaseinfo.setEnddate(rs.getTimestamp("enddate"));
						homeworkbaseinfo.setPubdate(rs.getTimestamp("pubdate"));
						homeworkbaseinfo.setFlag(rs.getInt("flag"));

						FileRequirementManager fileRequirementManager = new FileRequirementManager();
						fileRequirementManager.ParseJson(rs.getString("file_requirement"));
						homeworkbaseinfo.setFileRequirement(fileRequirementManager);

						homeworkbaseinfo.setTitle(rs.getString("title"));
						homeworkbaseinfo.setContent(rs.getString("content"));

						list.add(homeworkbaseinfo);

					}

				});

		return list;
	}

	/**
	 * 根据
	 */
	public List<CourseTeachingClassHomeworkBaseinfo> getByTeacherID(String t_teacher_id) {

		List<CourseTeachingClassHomeworkBaseinfo> list = new ArrayList<CourseTeachingClassHomeworkBaseinfo>();

		getJdbcTemplate().query(GET_BY_TEACHER_ID, new Object[] { t_teacher_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo = new CourseTeachingClassHomeworkBaseinfo();
				homeworkbaseinfo.setId(rs.getString("id"));
				homeworkbaseinfo.setCourseTeachingClassId(rs.getString("t_course_teaching_class_id"));
				homeworkbaseinfo.setTeacherId(t_teacher_id);
				homeworkbaseinfo
						.setCourseTeachingClassHomeworkTypeId(rs.getString("t_course_teaching_class_homework_type_id"));
				homeworkbaseinfo.setEnddate(rs.getTimestamp("enddate"));
				homeworkbaseinfo.setPubdate(rs.getTimestamp("pubdate"));
				homeworkbaseinfo.setFlag(rs.getInt("flag"));

				FileRequirementManager fileRequirementManager = new FileRequirementManager();
				fileRequirementManager.ParseJson(rs.getString("file_requirement"));
				homeworkbaseinfo.setFileRequirement(fileRequirementManager);

				homeworkbaseinfo.setTitle(rs.getString("title"));
				homeworkbaseinfo.setContent(rs.getString("content"));

				list.add(homeworkbaseinfo);

			}

		});

		return list;
	}

	public List<String> PageQuery(String t_course_teaching_class_id, String t_course_teaching_class_homeworktype_id,
			int PageBegin, int PageSize) {

		List<String> list = new ArrayList<String>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID, new Object[] {
				t_course_teaching_class_id, t_course_teaching_class_homeworktype_id, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {

						list.add(rs.getString("id"));
						// System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}

}
