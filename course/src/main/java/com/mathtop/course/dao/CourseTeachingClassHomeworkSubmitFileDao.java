package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.CourseTeachingClassHomeworkSubmitBaseinfo;
import com.mathtop.course.domain.CourseTeachingClassHomeworkSubmitFile;
import com.mathtop.course.domain.CourseTeachingClassHomeworkSubmitFileViewData;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassHomeworkSubmitFileDao extends BaseDao<CourseTeachingClassHomeworkSubmitFile> {

	@Autowired
	CourseTeachingClassHomeworkSubmitBaseinfoDao courseTeachingClassHomeworkSubmitBaseinfoDao;

	// insert
	private final String INSERT = "INSERT INTO t_course_teaching_class_homework_submit_file( id,t_course_teaching_class_homework_submit_baseinfo_id,file_node_id,filename,filepath) VALUES(?,?,?,?,?)";

	// select
	private final String GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID = "SELECT id FROM t_course_teaching_class_homework_submit_file WHERE t_course_teaching_class_homework_submit_baseinfo_id=?   limit ?,?";
	private final String GET_COUNT_BY_COURSE_TEACHING_CLASS_ID = "SELECT count(*) FROM t_course_teaching_class_homework_submit_file WHERE t_course_teaching_class_homework_submit_baseinfo_id=?";
	private final String GET_BY_COURSE_TEACHING_CLASS_ID = "SELECT id,  file_node_id,filename,filepath FROM t_course_teaching_class_homework_submit_file WHERE t_course_teaching_class_homework_submit_baseinfo_id=?";
	private final String GET_BY_ID = "SELECT t_course_teaching_class_homework_submit_baseinfo_id,file_node_id, filename,filepath FROM t_course_teaching_class_homework_submit_file WHERE id=?";

	// DELETE
	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_class_homework_submit_file WHERE id=?";
	private String DELETE_BY_COURSE_TEACHING_CLASS_ID = "DELETE FROM t_course_teaching_class_homework_submit_file WHERE t_course_teaching_class_homework_submit_baseinfo_id=?";

	// update
	private String UPDATE_BY_ID = "update t_course_teaching_class_homework_submit_file set t_course_teaching_class_homework_submit_baseinfo_id=?, file_node_id=?,filename=?,filepath=? WHERE id=?";

	public void update(String id, String t_course_teaching_class_homework_submit_baseinfo_id, int fileNodeId,String filename, String filepath) {
		if (id == null || t_course_teaching_class_homework_submit_baseinfo_id == null || filename == null || filepath == null)
			return;

		Object params[] = new Object[] { t_course_teaching_class_homework_submit_baseinfo_id, fileNodeId,filename, filepath, id };
		int types[] = new int[] { Types.VARCHAR, Types.INTEGER,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}

	/*
	 * 根据用户ID得到用户
	 */
	public CourseTeachingClassHomeworkSubmitFile getByID(String id) {

		CourseTeachingClassHomeworkSubmitFile submitfile = new CourseTeachingClassHomeworkSubmitFile();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				submitfile.setId(id);
				submitfile.setCourseTeachingClassHomeworkSubmitBaseinfoId(rs.getString("t_course_teaching_class_homework_submit_baseinfo_id"));

				submitfile.setFilename(rs.getString("filename"));
				submitfile.setFilepath(rs.getString("filepath"));
				submitfile.setFileNodeId(rs.getInt("file_node_id"));

			}

		});

		if (submitfile.getId() == null)
			return null;
		return submitfile;
	}

	/**
	 * 根据教学班得到通知
	 * */
	public List<CourseTeachingClassHomeworkSubmitFile> getByCourseTeachingClassHomeworkBaseInfoID(String t_course_teaching_class_homework_submit_baseinfo_id) {

		List<CourseTeachingClassHomeworkSubmitFile> list = new ArrayList<CourseTeachingClassHomeworkSubmitFile>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_homework_submit_baseinfo_id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkSubmitFile submitfile = new CourseTeachingClassHomeworkSubmitFile();
						submitfile.setId(rs.getString("id"));

						submitfile.setCourseTeachingClassHomeworkSubmitBaseinfoId(t_course_teaching_class_homework_submit_baseinfo_id);

						submitfile.setFilename(rs.getString("filename"));
						submitfile.setFilepath(rs.getString("filepath"));
						submitfile.setFileNodeId(rs.getInt("file_node_id"));
						list.add(submitfile);

					}

				});

		return list;
	}

	/* 增加用户 */
	public String add(CourseTeachingClassHomeworkSubmitFile submitfile) {
		String id = GUID.getGUID();
		submitfile.setId(id);
		Object params[] = new Object[] { submitfile.getId(), submitfile.getCourseTeachingClassHomeworkSubmitBaseinfoId(), submitfile.getFilename(),
				submitfile.getFilepath() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT, params, types);
		return id;
	}

	public String add(String t_course_teaching_class_homework_baseinfo_id, int fileNodeId,String filename, String filepath) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_teaching_class_homework_baseinfo_id,fileNodeId, filename, filepath };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR,Types.INTEGER, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT, params, types);
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

	private List<CourseTeachingClassHomeworkSubmitFileViewData> PageQuery(String t_course_teaching_class_id, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<CourseTeachingClassHomeworkSubmitFileViewData> list = new ArrayList<CourseTeachingClassHomeworkSubmitFileViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_id, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkSubmitFileViewData data = new CourseTeachingClassHomeworkSubmitFileViewData();

						CourseTeachingClassHomeworkSubmitFile homeworksubmitfile = getByID(rs.getString("id"));
						data.setHomeworksubmitfile(homeworksubmitfile);

						CourseTeachingClassHomeworkSubmitBaseinfo homeworksubmitbaseinfo = courseTeachingClassHomeworkSubmitBaseinfoDao
								.getByID(homeworksubmitfile.getCourseTeachingClassHomeworkSubmitBaseinfoId());
						data.setHomeworksubmitbaseinfo(homeworksubmitbaseinfo);

						list.add(data);
						// System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}

	public Page<CourseTeachingClassHomeworkSubmitFileViewData> getPage(String t_course_teaching_class_id, int pageNo, int pageSize) {
		long totalCount = getCount(t_course_teaching_class_id);
		if (totalCount < 1)
			return new Page<CourseTeachingClassHomeworkSubmitFileViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassHomeworkSubmitFileViewData> data = PageQuery(t_course_teaching_class_id, pageNo - 1, pageSize);

		return new Page<CourseTeachingClassHomeworkSubmitFileViewData>(startIndex, totalCount, pageSize, data);

	}
}
