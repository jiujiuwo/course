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
import com.mathtop.course.domain.CourseTeachingClassReference;
import com.mathtop.course.domain.CourseTeachingClassReferenceFile;
import com.mathtop.course.domain.CourseTeachingClassReferenceType;
import com.mathtop.course.domain.CourseTeachingClassReferenceViewData;
import com.mathtop.course.domain.TeacherViewData;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassReferenceDao extends BaseDao<CourseTeachingClassReference> {

	@Autowired
	CourseTeachingClassDao courseteachingclassDao;

	@Autowired
	TeacherViewDataDao teacherviewdataDao;

	@Autowired
	CourseTeachingClassReferenceFileDao referencefileDao;

	@Autowired
	CourseTeachingClassReferenceTypeDao courseTeachingClassReferenceTypeDao;

	// insert
	private final String INSERT_HOMEWORKBASEINFO = "INSERT INTO t_course_teaching_class_reference( id,t_course_teaching_class_id, t_teacher_id, t_course_teaching_class_reference_type_id,title, content, pubdate, modifieddate) VALUES(?,?,?,?,?,?,?,?)";

	// select
	private final String GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID_AND_REFERENCE_TYPE_ID = "SELECT id FROM t_course_teaching_class_reference WHERE t_course_teaching_class_id=? and t_course_teaching_class_reference_type_id=? order by pubdate desc limit ?,?";
	private final String GET_COUNT_BY_COURSE_TEACHING_CLASS_ID_AND_REFERENCE_TYPE_ID = "SELECT count(*) FROM t_course_teaching_class_reference WHERE t_course_teaching_class_id=? and t_course_teaching_class_reference_type_id=?";
	private final String GET_BY_COURSE_TEACHING_CLASS_ID = "SELECT id, t_teacher_id, t_course_teaching_class_reference_type_id,title, content, pubdate, modifieddate FROM t_course_teaching_class_reference WHERE t_course_teaching_class_id=?";
	private final String GET_BY_ID = "SELECT t_course_teaching_class_id, t_teacher_id, t_course_teaching_class_reference_type_id, title, content, pubdate, modifieddate FROM t_course_teaching_class_reference WHERE id=?";

	// DELETE
	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_class_reference WHERE id=?";
	private String DELETE_BY_COURSE_TEACHING_CLASS_ID = "DELETE FROM t_course_teaching_class_reference"
			+ " WHERE t_course_teaching_class_id=?";
	private String DELETE_BY_TEACHER_ID = "DELETE FROM t_course_teaching_class_reference WHERE t_teacher_id=?";

	// update
	private String UPDATE_BY_ID = "update t_course_teaching_class_reference set t_course_teaching_class_id=?, t_teacher_id=?, t_course_teaching_class_reference_type_id=?, title=?, content=?, pubdate=?, modifieddate=?  WHERE id=?";
	private String UPDATE_SIMPLE_BY_ID = "update t_course_teaching_class_reference set t_teacher_id=?,  title=?, content=?,  modifieddate=?  WHERE id=?";

	public void update(String id, String t_course_teaching_class_id, String t_teacher_id, String t_course_teaching_class_reference_type_id,
			String title, String content, Date pubdate, Date modifieddate) {
		if (id == null || t_teacher_id == null)
			return;

		Object params[] = new Object[] { t_course_teaching_class_id, t_teacher_id, t_course_teaching_class_reference_type_id, title,
				content, pubdate, modifieddate, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP,
				Types.TIMESTAMP, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}

	public void update(String id, String t_teacher_id, String title, String content, Date modifieddate) {
		if (id == null || t_teacher_id == null)
			return;

		Object params[] = new Object[] { t_teacher_id, title, content, modifieddate, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_SIMPLE_BY_ID, params, types);
	}

	/*
	 * 根据用户ID得到用户
	 */
	public CourseTeachingClassReference getByID(String id) {

		CourseTeachingClassReference reference = new CourseTeachingClassReference();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				reference.setId(id);
				reference.setT_course_teaching_class_id(rs.getString("t_course_teaching_class_id"));
				reference.setT_teacher_id(rs.getString("t_teacher_id"));
				reference.setT_course_teaching_class_reference_type_id(rs.getString("t_course_teaching_class_reference_type_id"));
				reference.setModifieddate(rs.getTimestamp("modifieddate"));
				reference.setPubdate(rs.getTimestamp("pubdate"));
				reference.setTitle(rs.getString("title"));
				reference.setContent(rs.getString("content"));

			}

		});

		if (reference.getId() == null)
			return null;
		return reference;
	}

	/**
	 * 根据教学班得到通知
	 * */
	public List<CourseTeachingClassReference> getByCourseTeachingClassID(String t_course_teaching_class_id) {

		List<CourseTeachingClassReference> list = new ArrayList<CourseTeachingClassReference>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseTeachingClassReference reference = new CourseTeachingClassReference();
				reference.setId(rs.getString("id"));
				reference.setT_course_teaching_class_id(t_course_teaching_class_id);
				reference.setT_teacher_id(rs.getString("t_teacher_id"));
				reference.setT_course_teaching_class_reference_type_id(rs.getString("t_course_teaching_class_reference_type_id"));
				reference.setModifieddate(rs.getTimestamp("modifieddate"));
				reference.setPubdate(rs.getTimestamp("pubdate"));
				reference.setTitle(rs.getString("title"));
				reference.setContent(rs.getString("content"));

				list.add(reference);

			}

		});

		return list;
	}

	/* 增加用户 */
	public String add(CourseTeachingClassReference reference) {
		String id = GUID.getGUID();
		reference.setId(id);
		Object params[] = new Object[] { reference.getId(), reference.getT_course_teaching_class_id(), reference.getT_teacher_id(),
				reference.getT_course_teaching_class_reference_type_id(), reference.getTitle(), reference.getContent(),
				reference.getPubdate(), reference.getModifieddate() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.TIMESTAMP, Types.TIMESTAMP };
		getJdbcTemplate().update(INSERT_HOMEWORKBASEINFO, params, types);
		return id;
	}

	public String add(String t_course_teaching_class_id, String t_teacher_id, String t_course_teaching_class_homework_type_id,
			String title, String content, Date pubdate, Date enddate) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_teaching_class_id, t_teacher_id, t_course_teaching_class_homework_type_id, title,
				content, pubdate, enddate };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.TIMESTAMP, Types.TIMESTAMP };
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

	long getCount(String t_course_teaching_class_id, String t_course_teaching_class_reference_type_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_TEACHING_CLASS_ID_AND_REFERENCE_TYPE_ID,
				new Object[] { t_course_teaching_class_id, t_course_teaching_class_reference_type_id },
				new int[] { Types.VARCHAR, Types.VARCHAR }, Long.class);
	}

	public CourseTeachingClassReferenceViewData getCourseTeachingClassHomeworkBaseinfoViewDataByID(String id) {
		CourseTeachingClassReferenceViewData data = new CourseTeachingClassReferenceViewData();

		CourseTeachingClassReference reference = getByID(id);
		data.setReference(reference);

		CourseTeachingClass courseteachingclass = courseteachingclassDao.getCourseTeachingClassById(reference
				.getT_course_teaching_class_id());
		data.setCourseteachingclass(courseteachingclass);

		TeacherViewData teacherviewdata = teacherviewdataDao.getTeacherViewDataByTeacherId(reference.getT_teacher_id());
		data.setTeacher(teacherviewdata);

		CourseTeachingClassReferenceType type = courseTeachingClassReferenceTypeDao.getByID(reference
				.getT_course_teaching_class_reference_type_id());
		data.setType(type);

		List<CourseTeachingClassReferenceFile> fileList = referencefileDao.getByCourseTeachingClassHomeworkBaseInfoID(reference.getId());
		
		data.setFileList(fileList);

		return data;
	}
	
	

	private List<CourseTeachingClassReferenceViewData> PageQuery(String t_course_teaching_class_id,
			String t_course_teaching_class_homeworktype_id, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<CourseTeachingClassReferenceViewData> list = new ArrayList<CourseTeachingClassReferenceViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID_AND_REFERENCE_TYPE_ID,
				new Object[] { t_course_teaching_class_id, t_course_teaching_class_homeworktype_id, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassReferenceViewData data = getCourseTeachingClassHomeworkBaseinfoViewDataByID(rs.getString("id"));

						if (data != null)
							list.add(data);
					}

				});
		return list;
	}

	public Page<CourseTeachingClassReferenceViewData> getPage(String t_course_teaching_class_id,
			String t_course_teaching_class_reference_type_id, int pageNo, int pageSize) {
		long totalCount = getCount(t_course_teaching_class_id, t_course_teaching_class_reference_type_id);
		if (totalCount < 1)
			return new Page<CourseTeachingClassReferenceViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassReferenceViewData> data = PageQuery(t_course_teaching_class_id, t_course_teaching_class_reference_type_id,
				pageNo - 1, pageSize);

		return new Page<CourseTeachingClassReferenceViewData>(startIndex, totalCount, pageSize, data);

	}
}
