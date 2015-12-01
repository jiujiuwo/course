package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.Course;
import com.mathtop.course.domain.CoursePrecourse;
import com.mathtop.course.domain.CoursePrecourseViewData;
import com.mathtop.course.utility.GUID;

@Repository
public class CoursePrecourseDao extends BaseDao<CoursePrecourse> {

	@Autowired
	CourseDao courseDao;

	// insert
	private final String INSERT = "INSERT INTO t_precourse( id,t_course_id_this,t_course_id_pre) VALUES(?,?,?)";

	// select
	private final String GET_BY_COURSE_ID = "SELECT id,  t_course_id_pre  FROM t_precourse WHERE t_course_id_this=?";
	private final String GET_BY_ID = "SELECT t_course_id_this, t_course_id_pre FROM t_precourse WHERE id=?";

	// DELETE
	private String DELETE_BY_ID = "DELETE FROM t_precourse WHERE id=?";

	// update
	private String UPDATE_BY_ID = "update t_precourse set  t_course_id_this=?, t_course_id_pre=?  WHERE id=?";

	public void update(String id, String t_course_id_this, String t_course_id_pre) {
		if (id == null)
			return;

		Object params[] = new Object[] { t_course_id_this, t_course_id_pre, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}

	/**
	 * 增加
	 */
	public String add(String t_course_id_this, String t_course_id_pre) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_id_this, t_course_id_pre };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT, params, types);

		return id;
	}

	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	public CoursePrecourse getById(String id) {
		CoursePrecourse cp = new CoursePrecourse();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				cp.setId(id);
				cp.setT_course_id_this(rs.getString("t_course_id_this"));
				cp.setT_course_id_pre(rs.getString("t_course_id_pre"));

			}

		});

		if (cp.getId() == null)
			return null;
		return cp;

	}

	public List<CoursePrecourse> getPreCourse(String t_course_id) {
		List<CoursePrecourse> list = new ArrayList<CoursePrecourse>();

		getJdbcTemplate().query(GET_BY_COURSE_ID, new Object[] { t_course_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {

				CoursePrecourse cp = getById(rs.getString("id"));
				list.add(cp);
			}

		});
		return list;
	}

	public List<CoursePrecourseViewData> getPreCourseViewData(String t_course_id) {
		List<CoursePrecourseViewData> list = new ArrayList<CoursePrecourseViewData>();

		getJdbcTemplate().query(GET_BY_COURSE_ID, new Object[] { t_course_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {

				CoursePrecourse courseprecourse = getById(rs.getString("id"));
				Course coursethis = courseDao.getCourseById(courseprecourse.getT_course_id_this());
				Course coursepre = courseDao.getCourseById(courseprecourse.getT_course_id_pre());

				CoursePrecourseViewData cpvd = new CoursePrecourseViewData();
				cpvd.setCourseprecourse(courseprecourse);
				cpvd.setCoursethis(coursethis);
				cpvd.setCoursepre(coursepre);
				list.add(cpvd);
			}

		});
		return list;
	}

}
