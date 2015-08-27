package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfo;
import com.mathtop.course.domain.CourseTeachingClassHomeworkFile;
import com.mathtop.course.domain.CourseTeachingClassHomeworkFileViewData;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassHomeworkFileDao extends BaseDao<CourseTeachingClassHomeworkFile> {

	

	@Autowired
	CourseTeachingClassHomeworkBaseinfoDao courseTeachingClassHomeworkBaseinfoDao;

	// insert
	private final String INSERT_PLAN = "INSERT INTO t_course_teaching_class_homework_file( id,t_course_teaching_class_homework_baseinfo_id,filename,filepath) VALUES(?,?,?,?)";

	// select
	private final String GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID = "SELECT id FROM t_course_teaching_class_homework_file WHERE t_course_teaching_class_homework_baseinfo_id=?   limit ?,?";
	private final String GET_COUNT_BY_COURSE_TEACHING_CLASS_ID = "SELECT count(*) FROM t_course_teaching_class_homework_file WHERE t_course_teaching_class_homework_baseinfo_id=?";
	private final String GET_BY_COURSE_TEACHING_CLASS_ID = "SELECT id,  filename,filepath FROM t_course_teaching_class_homework_file WHERE t_course_teaching_class_homework_baseinfo_id=?";
	private final String GET_BY_ID = "SELECT t_course_teaching_class_homework_baseinfo_id, filename,filepath FROM t_course_teaching_class_homework_file WHERE id=?";

	// DELETE
	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_class_homework_file WHERE id=?";
	private String DELETE_BY_COURSE_TEACHING_CLASS_ID = "DELETE FROM t_course_teaching_class_homework_file WHERE t_course_teaching_class_homework_baseinfo_id=?";
	

	// update
	private String UPDATE_BY_ID = "update t_course_teaching_class_homework_file set t_course_teaching_class_homework_baseinfo_id=?  filename=?,filepath=? WHERE id=?";

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
	public CourseTeachingClassHomeworkFile getByID(String id) {

		CourseTeachingClassHomeworkFile expriment = new CourseTeachingClassHomeworkFile();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				expriment.setId(id);
				expriment.setT_course_teaching_class_homework_baseinfo_id(rs.getString("t_course_teaching_class_homework_baseinfo_id"));
				
				expriment.setFilename(rs.getString("filename"));
				expriment.setFilepath(rs.getString("filepath"));

			}

		});

		if (expriment.getId() == null)
			return null;
		return expriment;
	}

	/**
	 * 根据课程-作业基本信息得到对应的文件列表
	 * */
	public List<CourseTeachingClassHomeworkFile> getByCourseTeachingClassHomeworkBaseInfoID(String t_course_teaching_class_homework_baseinfo_id) {

		List<CourseTeachingClassHomeworkFile> list = new ArrayList<CourseTeachingClassHomeworkFile>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_homework_baseinfo_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseTeachingClassHomeworkFile expriment = new CourseTeachingClassHomeworkFile();
				expriment.setId(rs.getString("id"));
				
				expriment.setT_course_teaching_class_homework_baseinfo_id(t_course_teaching_class_homework_baseinfo_id);
				
				expriment.setFilename(rs.getString("filename"));
				expriment.setFilepath(rs.getString("filepath"));
				list.add(expriment);

			}

		});

		return list;
	}

	/* 增加用户 */
	public String add(CourseTeachingClassHomeworkFile expriment) {
		String id = GUID.getGUID();
		expriment.setId(id);
		Object params[] = new Object[] { expriment.getId(), expriment.getT_course_teaching_class_homework_baseinfo_id(),expriment.getFilename(), expriment.getFilepath() };
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

	private List<CourseTeachingClassHomeworkFileViewData> PageQuery(String t_course_teaching_class_id, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<CourseTeachingClassHomeworkFileViewData> list = new ArrayList<CourseTeachingClassHomeworkFileViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_id, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkFileViewData data = new CourseTeachingClassHomeworkFileViewData();

						CourseTeachingClassHomeworkFile homeworkfile = getByID(rs.getString("id"));
						data.setHomeworkfile(homeworkfile);

						CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo = courseTeachingClassHomeworkBaseinfoDao.getByID(homeworkfile.getT_course_teaching_class_homework_baseinfo_id());
						data.setHomeworkbaseinfo(homeworkbaseinfo);

						

						list.add(data);
						// System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}

	public Page<CourseTeachingClassHomeworkFileViewData> getPage(String t_course_teaching_class_id, int pageNo, int pageSize) {
		long totalCount = getCount(t_course_teaching_class_id);
		if (totalCount < 1)
			return new Page<CourseTeachingClassHomeworkFileViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassHomeworkFileViewData> data = PageQuery(t_course_teaching_class_id, pageNo - 1, pageSize);

		return new Page<CourseTeachingClassHomeworkFileViewData>(startIndex, totalCount, pageSize, data);

	}
}
