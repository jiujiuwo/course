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

import com.mathtop.course.domain.CourseTeachingClass;
import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfo;
import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfoViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkFile;
import com.mathtop.course.domain.CourseTeachingClassHomeworkType;
import com.mathtop.course.domain.TeacherViewData;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassHomeworkBaseinfoDao extends BaseDao<CourseTeachingClassHomeworkBaseinfo> {

	@Autowired
	CourseTeachingClassDao courseteachingclassDao;

	@Autowired
	CourseDao courseDao;

	@Autowired
	TeacherViewDataDao teacherviewdataDao;

	@Autowired
	CourseTeachingClassHomeworkFileDao homeworkfileDao;

	@Autowired
	CourseTeachingClassHomeworkTypeDao courseTeachingClassHomeworkTypeDao;

	// insert
	private final String INSERT_HOMEWORKBASEINFO = "INSERT INTO t_course_teaching_class_homework_baseinfo( id,t_course_teaching_class_id, t_teacher_id, t_course_teaching_class_homework_type_id, filetype,filenameformat,filecount,title, content, pubdate, enddate) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

	// select
	private final String GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID = "SELECT id FROM t_course_teaching_class_homework_baseinfo WHERE t_course_teaching_class_id=? and t_course_teaching_class_homework_type_id=? order by pubdate desc limit ?,?";
	private final String GET_COUNT_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID = "SELECT count(*) FROM t_course_teaching_class_homework_baseinfo WHERE t_course_teaching_class_id=? and t_course_teaching_class_homework_type_id=?";
	private final String GET_BY_COURSE_TEACHING_CLASS_ID = "SELECT id, t_teacher_id, t_course_teaching_class_homework_type_id, filetype,filenameformat,filecount,title, content, pubdate, enddate FROM t_course_teaching_class_homework_baseinfo WHERE t_course_teaching_class_id=?";
	private final String GET_BY_ID = "SELECT t_course_teaching_class_id, t_teacher_id, t_course_teaching_class_homework_type_id,filetype,filenameformat,filecount, title, content, pubdate, enddate FROM t_course_teaching_class_homework_baseinfo WHERE id=?";

	// DELETE
	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_class_homework_baseinfo WHERE id=?";
	private String DELETE_BY_COURSE_TEACHING_CLASS_ID = "DELETE FROM t_course_teaching_class_homework_baseinfo WHERE t_course_teaching_class_id=?";
	private String DELETE_BY_TEACHER_ID = "DELETE FROM t_course_teaching_class_homework_baseinfo WHERE t_teacher_id=?";

	// update
	private String UPDATE_BY_ID = "update t_course_teaching_class_homework_baseinfo set t_course_teaching_class_id=?, t_teacher_id=?, t_course_teaching_class_homework_type_id=?, filetype=?,filenameformat=?,filecount=?,title=?, content=?, pubdate=?, enddate=?  WHERE id=?";
	private String UPDATE_SIMPLE_BY_ID = "update t_course_teaching_class_homework_baseinfo set t_teacher_id=?,  filetype=?,filenameformat=?,filecount=?,title=?, content=?,  enddate=?  WHERE id=?";
	private String UPDATE_SIMPLE0_BY_ID = "update t_course_teaching_class_homework_baseinfo set t_teacher_id=?,  title=?, content=?,  enddate=?  WHERE id=?";

	public void update(String id, String t_course_teaching_class_id, String t_teacher_id, String t_course_teaching_class_homework_type_id,
			String filetype, String filenameformat, Integer filecount, String title, String content, Date pubdate, Date enddate) {
		if (id == null || t_teacher_id == null)
			return;

		Object params[] = new Object[] { t_course_teaching_class_id, t_teacher_id, t_course_teaching_class_homework_type_id, filetype,
				filenameformat, filecount, title, content, pubdate, enddate, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.VARCHAR,
				Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}

	public void update(String id, String t_teacher_id, String filetype, String filenameformat, Integer filecount, String title,
			String content, Date enddate) {
		if (id == null || t_teacher_id == null)
			return;

		Object params[] = new Object[] { t_teacher_id, filetype, filenameformat, filecount, title, content, enddate, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR,
				Types.TIMESTAMP, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_SIMPLE_BY_ID, params, types);
	}

	public void update(String id, String t_teacher_id, String title, String content, Date enddate) {
		if (id == null || t_teacher_id == null)
			return;

		Object params[] = new Object[] { t_teacher_id, title, content, enddate, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_SIMPLE0_BY_ID, params, types);
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
				homeworkbaseinfo.setT_course_teaching_class_id(rs.getString("t_course_teaching_class_id"));
				homeworkbaseinfo.setT_teacher_id(rs.getString("t_teacher_id"));
				homeworkbaseinfo.setT_course_teaching_class_homework_type_id(rs.getString("t_course_teaching_class_homework_type_id"));
				homeworkbaseinfo.setEnddate(rs.getTimestamp("enddate"));
				homeworkbaseinfo.setPubdate(rs.getTimestamp("pubdate"));
				homeworkbaseinfo.setFiletype(rs.getString("filetype"));
				homeworkbaseinfo.setFilenameformat(rs.getString("filenameformat"));
				homeworkbaseinfo.setFilecount(rs.getInt("filecount"));
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
	 * */
	public List<CourseTeachingClassHomeworkBaseinfo> getByCourseTeachingClassID(String t_course_teaching_class_id) {

		List<CourseTeachingClassHomeworkBaseinfo> list = new ArrayList<CourseTeachingClassHomeworkBaseinfo>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo = new CourseTeachingClassHomeworkBaseinfo();
				homeworkbaseinfo.setId(rs.getString("id"));
				homeworkbaseinfo.setT_course_teaching_class_id(t_course_teaching_class_id);
				homeworkbaseinfo.setT_teacher_id(rs.getString("t_teacher_id"));
				homeworkbaseinfo.setT_course_teaching_class_homework_type_id(rs.getString("t_course_teaching_class_homework_type_id"));
				homeworkbaseinfo.setEnddate(rs.getTimestamp("enddate"));
				homeworkbaseinfo.setPubdate(rs.getTimestamp("pubdate"));
				homeworkbaseinfo.setFiletype(rs.getString("filetype"));
				homeworkbaseinfo.setFilenameformat(rs.getString("filenameformat"));
				homeworkbaseinfo.setFilecount(rs.getInt("filecount"));
				homeworkbaseinfo.setTitle(rs.getString("title"));
				homeworkbaseinfo.setContent(rs.getString("content"));

				list.add(homeworkbaseinfo);

			}

		});

		return list;
	}

	/* 增加用户 */
	public String add(CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo) {
		String id = GUID.getGUID();
		homeworkbaseinfo.setId(id);
		Object params[] = new Object[] { homeworkbaseinfo.getId(), homeworkbaseinfo.getT_course_teaching_class_id(),
				homeworkbaseinfo.getT_teacher_id(), homeworkbaseinfo.getT_course_teaching_class_homework_type_id(),
				homeworkbaseinfo.getFiletype(), homeworkbaseinfo.getFilenameformat(), homeworkbaseinfo.getFilecount(),
				homeworkbaseinfo.getTitle(), homeworkbaseinfo.getContent(), homeworkbaseinfo.getPubdate(), homeworkbaseinfo.getEnddate(), };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER,
				Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP };
		getJdbcTemplate().update(INSERT_HOMEWORKBASEINFO, params, types);
		return id;
	}

	public String add(String t_course_teaching_class_id, String t_teacher_id, String t_course_teaching_class_homework_type_id,
			String filetype, String filenameformat, Integer filecount, String title, String content, Date pubdate, Date enddate) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_teaching_class_id, t_teacher_id, t_course_teaching_class_homework_type_id, filetype,
				filenameformat, filecount, title, content, pubdate, enddate };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER,
				Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP };
		getJdbcTemplate().update(INSERT_HOMEWORKBASEINFO, params, types);
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

	public void deleteByTeacherId(String t_teacher_id) {
		Object params[] = new Object[] { t_teacher_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_TEACHER_ID, params, types);
	}

	long getCount(String t_course_teaching_class_id, String t_course_teaching_class_homeworktype_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID,
				new Object[] { t_course_teaching_class_id, t_course_teaching_class_homeworktype_id },
				new int[] { Types.VARCHAR, Types.VARCHAR }, Long.class);
	}

	public CourseTeachingClassHomeworkBaseinfoViewData getCourseTeachingClassHomeworkBaseinfoViewDataByID(String id) {
		CourseTeachingClassHomeworkBaseinfoViewData data = new CourseTeachingClassHomeworkBaseinfoViewData();

		CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo = getByID(id);
		data.setHomeworkbaseinfo(homeworkbaseinfo);

		CourseTeachingClass courseteachingclass = courseteachingclassDao.getCourseTeachingClassById(homeworkbaseinfo
				.getT_course_teaching_class_id());
		data.setCourseteachingclass(courseteachingclass);

		TeacherViewData teacherviewdata = teacherviewdataDao.getTeacherViewDataByTeacherId(homeworkbaseinfo.getT_teacher_id());
		data.setTeacher(teacherviewdata);

		CourseTeachingClassHomeworkType homeworkType = courseTeachingClassHomeworkTypeDao.getByID(homeworkbaseinfo
				.getT_course_teaching_class_homework_type_id());
		data.setHomeworkType(homeworkType);

		List<CourseTeachingClassHomeworkFile> homeworkFileList = homeworkfileDao
				.getByCourseTeachingClassHomeworkBaseInfoID(homeworkbaseinfo.getId());
		data.setHomeworkFileList(homeworkFileList);

		return data;
	}

	private List<CourseTeachingClassHomeworkBaseinfoViewData> PageQuery(String t_course_teaching_class_id,
			String t_course_teaching_class_homeworktype_id, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<CourseTeachingClassHomeworkBaseinfoViewData> list = new ArrayList<CourseTeachingClassHomeworkBaseinfoViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID,
				new Object[] { t_course_teaching_class_id, t_course_teaching_class_homeworktype_id, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkBaseinfoViewData data = getCourseTeachingClassHomeworkBaseinfoViewDataByID(rs
								.getString("id"));

						if (data != null)
							list.add(data);
						// System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}

	public Page<CourseTeachingClassHomeworkBaseinfoViewData> getPage(String t_course_teaching_class_id,
			String t_course_teaching_class_homeworktype_id, int pageNo, int pageSize) {
		long totalCount = getCount(t_course_teaching_class_id, t_course_teaching_class_homeworktype_id);
		if (totalCount < 1)
			return new Page<CourseTeachingClassHomeworkBaseinfoViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassHomeworkBaseinfoViewData> data = PageQuery(t_course_teaching_class_id,
				t_course_teaching_class_homeworktype_id, pageNo - 1, pageSize);

		return new Page<CourseTeachingClassHomeworkBaseinfoViewData>(startIndex, totalCount, pageSize, data);

	}
}
