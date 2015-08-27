package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.Course;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseDao extends BaseDao<Course> {
	private final String GET_COURSE_BY_ID = "select t_course.name as t_course_name,english_name,num,class_hours,experiment_hours,t_course_type_id,t_course_style_id,"
			+ " t_course_type.name as t_course_type_name,t_course_style.name as t_course_style_name"
			+ " from t_course left join t_course_type on t_course.t_course_type_id=t_course_type.id"
			+ " left join t_course_style on t_course.t_course_style_id =t_course_style.id"
			+ " where t_course.id=?";

	private final String GET_COURSE_BY_NUM = "select t_course.id as t_course_id,t_course.name as t_course_name,english_name,class_hours,experiment_hours,t_course_type_id,t_course_style_id,"
			+ " t_course_type.name as t_course_type_name,t_course_style.name as t_course_style_name"
			+ " from t_course left join t_course_type on t_course.t_course_type_id=t_course_type.id"
			+ " left join t_course_style on t_course.t_course_style_id =t_course_style.id"
			+ " where t_course.num=?";

	private final String GET_COURSE_COUNT_BY_NUM = "select count(*) from t_course where t_course.num=?";

	private final String GET_COURSE = "select t_course.id as t_course_id,t_course.name as t_course_name,english_name,num,class_hours,experiment_hours,t_course_type_id,t_course_style_id,"
			+ " t_course_type.name as t_course_type_name,t_course_style.name as t_course_style_name"
			+ " from t_course left join t_course_type on t_course.t_course_type_id=t_course_type.id"
			+ " left join t_course_style on t_course.t_course_style_id =t_course_style.id"
			+ " order by t_course.name" + " limit ?,?";

	private final String GET_COURSE_COUNT = "select count(*) from t_course";
	private final String GET_COURSE_COUNT_BY_COURSE_STYLE_ID = "select count(*) from t_course where t_course_style_id=?";
	private final String GET_COURSE_COUNT_BY_COURSE_TYPE_ID = "select count(*) from t_course where t_course_type_id=?";
	private final String GET_COURSE_COUNT_BY_DEPARTMENT_ID = "select count(*)"
			+ " from t_course " + " where id in" + " (" + " select t_course_id"
			+ " from t_course_department" + " where t_department_id=?" + " )";
	private final String INSERT_COURSE = "INSERT INTO t_course(id,name,english_name,num,class_hours,experiment_hours,t_course_type_id,t_course_style_id) VALUES(?,?,?,?,?,?,?,?)";

	/**
	 * 增加
	 * */
	public String add(String name, String englishname, String num,
			int class_hours, int experiment_hours, String t_course_type_id,
			String t_course_style_id) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, name, englishname, num,
				class_hours, experiment_hours, t_course_type_id,
				t_course_style_id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.VARCHAR,
				Types.VARCHAR };
		getJdbcTemplate().update(INSERT_COURSE, params, types);

		return id;
	}

	/**
	 * 根据课程id查找课程
	 * */
	public Course getCourseById(String id) {
		Course c = new Course();

		getJdbcTemplate().query(GET_COURSE_BY_ID, new Object[] { id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						c.setId(id);
						c.setName(rs.getString("t_course_name"));
						c.setEnglishname(rs.getString("english_name"));
						c.setNum(rs.getString("num"));
						c.setClass_hours(rs.getInt("class_hours"));
						c.setExperiment_hours(rs.getInt("experiment_hours"));

						c.setT_course_style_id(rs
								.getString("t_course_style_id"));
						c.setT_course_style_name(rs
								.getString("t_course_style_name"));

						c.setT_course_type_id(rs.getString("t_course_type_id"));
						c.setT_course_type_name(rs
								.getString("t_course_type_name"));

					}

				});

		if (c.getId() == null)
			return null;
		return c;
	}

	/**
	 * 根据课程编号查找课程
	 * */
	public Course getCourseByCourseNum(String num) {
		Course c = new Course();

		getJdbcTemplate().query(GET_COURSE_BY_NUM, new Object[] { num },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						c.setId(rs.getString("t_course_id"));
						c.setName(rs.getString("t_course_name"));
						c.setEnglishname(rs.getString("english_name"));
						c.setNum(num);
						c.setClass_hours(rs.getInt("class_hours"));
						c.setExperiment_hours(rs.getInt("experiment_hours"));

						c.setT_course_style_id(rs
								.getString("t_course_style_id"));
						c.setT_course_style_name(rs
								.getString("t_course_style_name"));

						c.setT_course_type_id(rs.getString("t_course_type_id"));
						c.setT_course_type_name(rs
								.getString("t_course_type_name"));

					}

				});

		if (c.getId() == null)
			return null;
		return c;
	}

	/**
	 * 得到所有课程数目
	 * */
	public long getCount() {
		return getJdbcTemplate().queryForObject(GET_COURSE_COUNT, null, null,
				Long.class);
	}

	/**
	 * 得到
	 * */
	public long getCountByCourseStyle(String coursestyleId) {
		return getJdbcTemplate().queryForObject(
				GET_COURSE_COUNT_BY_COURSE_STYLE_ID,
				new Object[] { coursestyleId }, new int[] { Types.VARCHAR },
				Long.class);
	}

	/**
	 * 得到
	 * */
	public long getCountByCourseNum(String coursenum) {
		return getJdbcTemplate().queryForObject(GET_COURSE_COUNT_BY_NUM,
				new Object[] { coursenum }, new int[] { Types.VARCHAR },
				Long.class);
	}

	/**
	 * 得到
	 * */
	public long getCountByCourseType(String coursetypeId) {
		return getJdbcTemplate().queryForObject(
				GET_COURSE_COUNT_BY_COURSE_TYPE_ID,
				new Object[] { coursetypeId }, new int[] { Types.VARCHAR },
				Long.class);
	}

	/**
	 * 得到
	 * */
	public long getCountByCourset_department_id(String t_department_id) {
		return getJdbcTemplate().queryForObject(
				GET_COURSE_COUNT_BY_DEPARTMENT_ID,
				new Object[] { t_department_id }, new int[] { Types.VARCHAR },
				Long.class);
	}

	private List<Course> PageQuery(int PageBegin, int PageSize) {

		List<Course> list = new ArrayList<Course>();

		getJdbcTemplate().query(GET_COURSE,
				new Object[] { PageBegin * PageSize, PageSize, },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Course c = new Course();
						c.setId(rs.getString("t_course_id"));
						c.setName(rs.getString("t_course_name"));
						c.setEnglishname(rs.getString("english_name"));
						c.setNum(rs.getString("num"));
						c.setClass_hours(rs.getInt("class_hours"));
						c.setExperiment_hours(rs.getInt("experiment_hours"));

						c.setT_course_style_id(rs
								.getString("t_course_style_id"));
						c.setT_course_style_name(rs
								.getString("t_course_style_name"));

						c.setT_course_type_id(rs.getString("t_course_type_id"));
						c.setT_course_type_name(rs
								.getString("t_course_type_name"));

						list.add(c);

					}

				});

		return list;
	}

	/**
	 * 
	 * 
	 * @param pageNo
	 *            页号，从1开始。
	 * @param pageSize
	 *            每页的记录数
	 * @return 包含分页信息的Page对象
	 */
	public Page<Course> getPage(int pageNo, int pageSize) {

		long totalCount = getCount();
		if (totalCount < 1)
			return new Page<Course>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<Course> data = PageQuery(pageNo - 1, pageSize);

		return new Page<Course>(startIndex, totalCount, pageSize, data);

	}

}
