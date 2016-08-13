package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.CourseTeachingClass;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassDao extends BaseDao<CourseTeachingClass> {
	private final String GET_COUNT = "SELECT count(*) FROM t_course_teaching_class";
	private final String GET_PAGE = "SELECT id FROM t_course_teaching_class limit ?,?";
	private final String GET_COURSE_TEACHING_CLASS_BY_ID = "SELECT t_course_id,name,t_course_teaching_term_id FROM t_course_teaching_class WHERE id=?";
	private final String GET_COURSE_TEACHING_CLASS_BY_COURSE_ID = "SELECT id,name,t_course_teaching_term_id FROM t_course_teaching_class WHERE t_course_id=?";
	private final String GET_COURSE_TEACHING_CLASS_BY_COURSE_ID_AND_ID = "SELECT name,t_course_teaching_term_id FROM t_course_teaching_class WHERE t_course_id=? and id=?";
	private final String GET_COURSE_TEACHING_CLASS_BY_COURSE_TEACHING_TERM_ID = "SELECT id,t_course_id,name FROM t_course_teaching_class WHERE t_course_teaching_term_id =?";

	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_class WHERE id=?";
	private String DELETE_BY_COURSE_ID = "DELETE FROM t_course_teaching_class WHERE t_course_id=?";


	private final String INSERT_COURSE_TEACHING_CLASS = "INSERT INTO t_course_teaching_class(id,t_course_id,name,t_course_teaching_term_id) VALUES(?,?,?,?)";
	private final String UPDATE_ALL_COURSE_TEACHING_CLASS = "update  t_course_teaching_class set t_course_id=?,name=?,t_course_teaching_term_id=?  where id=?";
	private final String UPDATE_NAME_COURSE_TEACHING_CLASS = "update  t_course_teaching_class set name=?  where id=?";

	/**
	 * 增加
	 * */
	public String add(String t_course_id, String name, String t_course_teaching_term_id) {
		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_id, name,t_course_teaching_term_id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR  };
		getJdbcTemplate().update(INSERT_COURSE_TEACHING_CLASS, params, types);

		return id;
	}

	/**
	 * 更新
	 * */
	public String update(String id, String t_course_id, String name, String t_course_teaching_term_id) {

		Object params[] = new Object[] { t_course_id, name, t_course_teaching_term_id, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_ALL_COURSE_TEACHING_CLASS, params, types);

		return id;
	}
	
	/**
	 * 更新
	 * */
	public String update(String id,  String name ) {

		Object params[] = new Object[] {  name,  id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_NAME_COURSE_TEACHING_CLASS, params, types);

		return id;
	}

	/**
	 * 删除
	 * */
	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	/**
	 * 删除
	 * */
	public void deleteByCourseId(String t_course_id) {
		Object params[] = new Object[] { t_course_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_COURSE_ID, params, types);
	}



	// 根据id
	public CourseTeachingClass getCourseTeachingClassById(String id) {

		CourseTeachingClass ctc = new CourseTeachingClass();
		getJdbcTemplate().query(GET_COURSE_TEACHING_CLASS_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {

				ctc.setId(id);
				ctc.setCourseId(rs.getString("t_course_id"));
				ctc.setName(rs.getString("name"));
				ctc.setCourseTeachingTermId(rs.getString("t_course_teaching_term_id"));
			}

		});

		if (ctc.getId() != null)
			return ctc;
		return null;
	}

	/**
	 * 根据课程id
	 */
	public List<CourseTeachingClass> getCourseTeachingClassByCourseId(String t_course_id) {
		List<CourseTeachingClass> list = new ArrayList<CourseTeachingClass>();
		getJdbcTemplate().query(GET_COURSE_TEACHING_CLASS_BY_COURSE_ID, new Object[] { t_course_id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClass ctc = new CourseTeachingClass();
						ctc.setId(rs.getString("id"));
						ctc.setCourseId(t_course_id);
						ctc.setName(rs.getString("name"));
						ctc.setCourseTeachingTermId(rs.getString("t_course_teaching_term_id"));
						list.add(ctc);

					}

				});

		return list;
	}

	

	/**
	 * 根据课程id和教学班id
	 */
	public List<CourseTeachingClass> getCourseTeachingClassByCourseIdTeachingClassId(String t_course_id,
			String id) {
		List<CourseTeachingClass> list = new ArrayList<CourseTeachingClass>();
		getJdbcTemplate().query(GET_COURSE_TEACHING_CLASS_BY_COURSE_ID_AND_ID,
				new Object[] { t_course_id, id }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClass ctc = new CourseTeachingClass();
						ctc.setId(id);
						ctc.setCourseId(t_course_id);
						ctc.setName(rs.getString("name"));
						ctc.setCourseTeachingTermId(rs.getString("t_course_teaching_term_id"));

						list.add(ctc);

					}

				});

		return list;
	}

	/**
	 * 根据学年学期
	 * */
	public List<CourseTeachingClass> getCourseTeachingClassByCourseTeachingTermId(String t_course_teaching_term_id) {
		List<CourseTeachingClass> list = new ArrayList<CourseTeachingClass>();
		getJdbcTemplate().query(GET_COURSE_TEACHING_CLASS_BY_COURSE_TEACHING_TERM_ID, new Object[] { t_course_teaching_term_id},
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClass ctc = new CourseTeachingClass();
						ctc.setId(rs.getString("id"));
						ctc.setCourseId(rs.getString("t_course_id"));
						ctc.setName(rs.getString("name"));
						ctc.setCourseTeachingTermId(t_course_teaching_term_id);

						list.add(ctc);

					}

				});

		return list;
	}

	/**
	 * */
	long getCount() {

		return getJdbcTemplate().queryForObject(GET_COUNT, null, null, Long.class);
	}

	/**
	 * */
	private List<CourseTeachingClass> PageQuery(int PageBegin, int PageSize) {

		List<CourseTeachingClass> list = new ArrayList<CourseTeachingClass>();

		getJdbcTemplate().query(GET_PAGE, new Object[] { PageBegin * PageSize, PageSize }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseTeachingClass data = getCourseTeachingClassById(rs.getString("id"));

				if (data != null)
					list.add(data);
				// System.out.println(rs.getString("t_user_id"));
			}

		});
		return list;
	}

	/**
	 * 
	 * */
	public Page<CourseTeachingClass> getPage(int pageNo, int pageSize) {
		long totalCount = getCount();
		if (totalCount < 1)
			return new Page<CourseTeachingClass>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClass> data = PageQuery(pageNo - 1, pageSize);

		return new Page<CourseTeachingClass>(startIndex, totalCount, pageSize, data);

	}

}
