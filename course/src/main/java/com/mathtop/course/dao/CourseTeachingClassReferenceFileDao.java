package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;





import com.mathtop.course.domain.CourseTeachingClassReference;
import com.mathtop.course.domain.CourseTeachingClassReferenceFile;
import com.mathtop.course.domain.CourseTeachingClassReferenceFileViewData;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassReferenceFileDao extends BaseDao<CourseTeachingClassReferenceFile> {

	

	@Autowired
	CourseTeachingClassReferenceDao courseTeachingClassReferenceDao;

	// insert
	private final String INSERT_PLAN = "INSERT INTO t_course_teaching_class_reference_file( id,t_course_teaching_class_reference_id,filename,filepath) VALUES(?,?,?,?)";

	// select
	private final String GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID = "SELECT id FROM t_course_teaching_class_reference_file WHERE t_course_teaching_class_reference_id=?   limit ?,?";
	private final String GET_COUNT_BY_COURSE_TEACHING_CLASS_ID = "SELECT count(*) FROM t_course_teaching_class_reference_file WHERE t_course_teaching_class_reference_id=?";
	private final String GET_BY_COURSE_TEACHING_CLASS_ID = "SELECT id,  filename,filepath FROM t_course_teaching_class_reference_file WHERE t_course_teaching_class_reference_id=?";
	private final String GET_BY_ID = "SELECT t_course_teaching_class_reference_id, filename,filepath FROM t_course_teaching_class_reference_file WHERE id=?";

	// DELETE
	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_class_reference_file WHERE id=?";
	private String DELETE_BY_COURSE_TEACHING_CLASS_ID = "DELETE FROM t_course_teaching_class_reference_file WHERE t_course_teaching_class_reference_id=?";
	

	// update
	private String UPDATE_BY_ID = "update t_course_teaching_class_reference_file set t_course_teaching_class_reference_id=?  filename=?,filepath=? WHERE id=?";

	public void update(String id, String t_course_teaching_class_reference_id,  String filename, String filepath) {
		if (id == null || t_course_teaching_class_reference_id == null || filename == null || filepath == null)
			return;

		Object params[] = new Object[] {  t_course_teaching_class_reference_id,	 filename,  filepath, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}

	/*
	 * 根据用户ID得到用户
	 */
	public CourseTeachingClassReferenceFile getByID(String id) {

		CourseTeachingClassReferenceFile file = new CourseTeachingClassReferenceFile();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				file.setId(id);
				file.setT_course_teaching_class_reference_id(rs.getString("t_course_teaching_class_reference_id"));				
				file.setFilename(rs.getString("filename"));
				file.setFilepath(rs.getString("filepath"));

			}

		});

		if (file.getId() == null)
			return null;
		return file;
	}

	/**
	 * 根据课程-作业基本信息得到对应的文件列表
	 * */
	public List<CourseTeachingClassReferenceFile> getByCourseTeachingClassHomeworkBaseInfoID(String t_course_teaching_class_reference_id) {

		List<CourseTeachingClassReferenceFile> list = new ArrayList<CourseTeachingClassReferenceFile>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_reference_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseTeachingClassReferenceFile file = new CourseTeachingClassReferenceFile();
				file.setId(rs.getString("id"));				
				file.setT_course_teaching_class_reference_id(t_course_teaching_class_reference_id);				
				file.setFilename(rs.getString("filename"));
				file.setFilepath(rs.getString("filepath"));
				list.add(file);

			}

		});

		return list;
	}

	/* 增加用户 */
	public String add(CourseTeachingClassReferenceFile file) {
		String id = GUID.getGUID();
		file.setId(id);
		Object params[] = new Object[] { file.getId(), file.getT_course_teaching_class_reference_id(),file.getFilename(), file.getFilepath() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
		getJdbcTemplate().update(INSERT_PLAN, params, types);
		return id;
	}

	public String add(String t_course_teaching_class_reference_id, String filename, String filepath) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_teaching_class_reference_id,  filename, filepath };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT_PLAN, params, types);
		return id;
	}

	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	public void deleteByCourseTeachingClassId(String t_course_teaching_class_reference_id) {
		Object params[] = new Object[] { t_course_teaching_class_reference_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_COURSE_TEACHING_CLASS_ID, params, types);
	}

	

	long getCount(String t_group_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_group_id }, new int[] { Types.VARCHAR }, Long.class);
	}

	private List<CourseTeachingClassReferenceFileViewData> PageQuery(String t_course_teaching_class_id, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<CourseTeachingClassReferenceFileViewData> list = new ArrayList<CourseTeachingClassReferenceFileViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_id, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassReferenceFileViewData data = new CourseTeachingClassReferenceFileViewData();

						CourseTeachingClassReferenceFile file = getByID(rs.getString("id"));
						data.setFile(file);

						CourseTeachingClassReference reference = courseTeachingClassReferenceDao.getByID(file.getT_course_teaching_class_reference_id());
						data.setReference(reference);

						

						list.add(data);
						// System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}

	public Page<CourseTeachingClassReferenceFileViewData> getPage(String t_course_teaching_class_id, int pageNo, int pageSize) {
		long totalCount = getCount(t_course_teaching_class_id);
		if (totalCount < 1)
			return new Page<CourseTeachingClassReferenceFileViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassReferenceFileViewData> data = PageQuery(t_course_teaching_class_id, pageNo - 1, pageSize);

		return new Page<CourseTeachingClassReferenceFileViewData>(startIndex, totalCount, pageSize, data);

	}
}
