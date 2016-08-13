package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.CourseTeachingClassStudent;
import com.mathtop.course.domain.Student;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassStudentDao extends BaseDao<CourseTeachingClassStudent> {

	private final String INSERT_TEACHING_CLASS_STUDENT = "INSERT INTO t_course_teaching_class_student(id,t_course_teaching_class_id,t_student_id,show_index) VALUES(?,?,?,?)";

	private final String IS_STUDENT_EXIST = "select count(*) from t_course_teaching_class_student where t_course_teaching_class_id=? and t_student_id=?";
	private final String GET_COURSE_TEACHING_CLASS_STUDENT_BY_STUDENT_ID = "SELECT id,t_course_teaching_class_id,show_index FROM t_course_teaching_class_student WHERE t_student_id=?";

	private final String GET_TEACHING_CLASS_STUDENT_BY_COURSE_TEACHING_CLASS_ID = "SELECT t_course_teaching_class_student.id,t_student_id "
			+ " FROM t_course_teaching_class_student right join t_student on t_student.id=t_course_teaching_class_student.t_student_id"
			+ " WHERE t_course_teaching_class_id=? order by show_idex,student_num";

	private final String GET_TEACHING_CLASS_STUDENT_BY_COURSE_TEACHING_CLASS_ID_STUDENT_ID = "SELECT id ,show_index "
			+ " FROM t_course_teaching_class_student " + " WHERE t_course_teaching_class_id=? and t_student_id=?";
	
	private final String GET_TEACHING_CLASS_STUDENT_BY_COURSE_TEACHING_CLASS_ID_SHOW_INDEX = "SELECT id,t_student_id "
			+ " FROM t_course_teaching_class_student " + " WHERE t_course_teaching_class_id=?  and show_index=?";

	private final String GET_STUDENT_COUNT_TEACHING_CLASS_STUDENT_BY_TEACHING_CLASS_ID = "SELECT count(*) FROM t_course_teaching_class_student WHERE t_course_teaching_class_id=?";

	private final String GET_MAX_SHOW_INDEX_BY_COURSE_TEACHING_CLASS_ID = "SELECT max(show_index) FROM t_course_teaching_class_student WHERE t_course_teaching_class_id=?";
	private final String GET_MIN_SHOW_INDEX_BY_COURSE_TEACHING_CLASS_ID = "SELECT min(show_index) FROM t_course_teaching_class_student WHERE t_course_teaching_class_id=?";
	private final String GET_MAX_SHOW_INDEX_BY_COURSE_TEACHING_CLASS_ID_STUDENT_ID = "SELECT max(show_index) "
			+ "FROM t_course_teaching_class_student "
			+ "WHERE show_index<(select show_index FROM t_course_teaching_class_student where t_course_teaching_class_id=? and t_student_id=?)";

	private final String GET_MIN_SHOW_INDEX_BY_COURSE_TEACHING_CLASS_ID_STUDENT_ID = "SELECT min(show_index) "
			+ "FROM t_course_teaching_class_student "
			+ "WHERE show_index>(select show_index FROM t_course_teaching_class_student where t_course_teaching_class_id=? and t_student_id=?)";

	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_class_student WHERE id=?";
	private String DELETE_BY_TEACHING_CLASS_ID = "DELETE FROM t_course_teaching_class_student WHERE t_course_teaching_class_id=?";
	private String DELETE_BY_STUDENT_ID = "DELETE FROM t_course_teaching_class_student WHERE t_student_id=?";
	private String DELETE_BY_TEACHING_CLASS_ID_AND_STUDENT_ID = "DELETE FROM t_course_teaching_class_student WHERE t_course_teaching_class_id=? and t_student_id=?";

	private final String UPDATE = "update t_course_teaching_class_student"
			+ " set t_course_teaching_class_id=?,t_student_id=?,show_index=? where id=?";

	private final String UPDATE_SHOW_INDEX_BY_ID = "update t_course_teaching_class_student"
			+ " set show_index=? where id=?";

	private final String UPDATE_SHOW_INDEX_BY_COURSE_TEACHING_ID_AND_STUDENT_ID = "update t_course_teaching_class_student"
			+ " set show_index=? where t_course_teaching_class_id=?,t_student_id=?";

	/**
	 * 修改
	 */
	public String update(String id, String t_course_teaching_class_id, String t_student_id, int show_index) {

		Object params[] = new Object[] { t_course_teaching_class_id, t_student_id, show_index, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE, params, types);

		return id;
	}

	/**
	 * 修改show_index
	 */
	public void update(String id, int show_index) {

		Object params[] = new Object[] { show_index, id };
		int types[] = new int[] { Types.INTEGER, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_SHOW_INDEX_BY_ID, params, types);

	}

	/**
	 * 修改show_index
	 */
	public void update(String t_course_teaching_class_id, String t_student_id, int show_index) {

		Object params[] = new Object[] { show_index, t_course_teaching_class_id, t_student_id };
		int types[] = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_SHOW_INDEX_BY_COURSE_TEACHING_ID_AND_STUDENT_ID, params, types);

	}

	/**
	 * 根据id删除
	 */
	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	/**
	 * 根据课程删除所有的学生
	 */
	public void deleteByCourseTeachingClassId(String t_course_teaching_class_id) {
		Object params[] = new Object[] { t_course_teaching_class_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_TEACHING_CLASS_ID, params, types);
	}

	/**
	 * 删除学生
	 */
	public void deleteByStudentId(String t_student_id) {
		Object params[] = new Object[] { t_student_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_STUDENT_ID, params, types);
	}

	/**
	 * 删除指定教学班级中的学生
	 */
	public void deleteByCourseTeachingClassIdAndStudentId(String t_course_teaching_class_id, String t_student_id) {
		Object params[] = new Object[] { t_course_teaching_class_id, t_student_id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_TEACHING_CLASS_ID_AND_STUDENT_ID, params, types);
	}

	/**
	 * 得到教学班人数
	 */
	public int getStudentCountByCourseTeachingClassId(String t_course_teaching_class_id) {

		return getJdbcTemplate().queryForObject(GET_STUDENT_COUNT_TEACHING_CLASS_STUDENT_BY_TEACHING_CLASS_ID,
				new Object[] { t_course_teaching_class_id }, new int[] { Types.VARCHAR }, Integer.class);
	}

	/**
	 * */
	public int getShowIndexMaxByCourseTeachingClassId(String t_course_teaching_class_id) {

		Integer i = getJdbcTemplate().queryForObject(GET_MAX_SHOW_INDEX_BY_COURSE_TEACHING_CLASS_ID,
				new Object[] { t_course_teaching_class_id }, new int[] { Types.VARCHAR }, Integer.class);
		if (i == null)
			return 0;
		return i;

	}

	/**
	 * 得到比指定的学生小一个次序
	 */
	public int getShowIndexMaxLessthanByCourseTeachingClassId(String t_course_teaching_class_id, String t_student_id) {

		Integer i = getJdbcTemplate().queryForObject(GET_MAX_SHOW_INDEX_BY_COURSE_TEACHING_CLASS_ID_STUDENT_ID,
				new Object[] { t_course_teaching_class_id, t_student_id }, new int[] { Types.VARCHAR, Types.VARCHAR },
				Integer.class);
		if (i == null)
			return 0;
		return i;

	}
	
	/**
	 * 得到比指定的学生大一个次序
	 */
	public int getShowIndexMinMorethanByCourseTeachingClassId(String t_course_teaching_class_id, String t_student_id) {

		Integer i = getJdbcTemplate().queryForObject(GET_MIN_SHOW_INDEX_BY_COURSE_TEACHING_CLASS_ID_STUDENT_ID,
				new Object[] { t_course_teaching_class_id, t_student_id }, new int[] { Types.VARCHAR, Types.VARCHAR },
				Integer.class);
		if (i == null)
			return 0;
		return i;

	}
	
	

	/**
	 * */
	public int getShowIndexMinByCourseTeachingClassId(String t_course_teaching_class_id) {

		Integer i = getJdbcTemplate().queryForObject(GET_MIN_SHOW_INDEX_BY_COURSE_TEACHING_CLASS_ID,
				new Object[] { t_course_teaching_class_id }, new int[] { Types.VARCHAR }, Integer.class);
		if (i == null)
			return 0;
		return i;

	}

	/**
	 * 学生是否在指定教学班级中存在
	 */
	public boolean IsStudentExist(String t_course_teaching_class_id, String t_student_id) {

		return getJdbcTemplate().queryForObject(IS_STUDENT_EXIST,
				new Object[] { t_course_teaching_class_id, t_student_id }, new int[] { Types.VARCHAR, Types.VARCHAR },
				Long.class) > 0;
	}

	/**
	 * 增加学生
	 */
	public String add(String t_course_teaching_class_id, String t_student_id, int show_index) {

		if (IsStudentExist(t_course_teaching_class_id, t_student_id))
			return null;

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_teaching_class_id, t_student_id, show_index };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
		getJdbcTemplate().update(INSERT_TEACHING_CLASS_STUDENT, params, types);

		return id;
	}

	/**
	 * 获得制定学生的教学班级信息
	 */
	public List<CourseTeachingClassStudent> getTeachingClassStudentByStudentId(String t_student_id) {
		List<CourseTeachingClassStudent> list = new ArrayList<CourseTeachingClassStudent>();
		getJdbcTemplate().query(GET_COURSE_TEACHING_CLASS_STUDENT_BY_STUDENT_ID, new Object[] { t_student_id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassStudent stu = new CourseTeachingClassStudent();
						stu.setId(rs.getString("id"));
						stu.setStudentId(t_student_id);
						stu.setCourseTeachingClassId(rs.getString("t_course_teaching_class_id"));
						stu.setShowIndex(rs.getInt("show_index"));
						list.add(stu);
					}

				});
		return list;
	}

	/**
	 * 获得制定学生的教学班级信息
	 */
	public List<CourseTeachingClassStudent> getTeachingClassStudentByStudentId(Student student) {
		if (student == null || student.getId() == null)
			return null;
		return getTeachingClassStudentByStudentId(student.getId());

	}

	/**
	 * 获得教学班级学生信息
	 */
	public List<CourseTeachingClassStudent> getTeachingClassStudentByTeachingClassId(
			String t_course_teaching_class_id) {
		List<CourseTeachingClassStudent> list = new ArrayList<CourseTeachingClassStudent>();
		getJdbcTemplate().query(GET_TEACHING_CLASS_STUDENT_BY_COURSE_TEACHING_CLASS_ID,
				new Object[] { t_course_teaching_class_id }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassStudent stu = new CourseTeachingClassStudent();
						stu.setId(rs.getString("id"));
						stu.setStudentId(rs.getString("t_student_id"));
						stu.setCourseTeachingClassId(t_course_teaching_class_id);
						stu.setShowIndex(rs.getInt("show_index"));
						list.add(stu);
					}

				});
		return list;
	}

	public CourseTeachingClassStudent getTeachingClassStudentByTeachingClassIdAndStudentId(
			String t_course_teaching_class_id, String t_student_id) {
		CourseTeachingClassStudent stu = new CourseTeachingClassStudent();
		getJdbcTemplate().query(GET_TEACHING_CLASS_STUDENT_BY_COURSE_TEACHING_CLASS_ID_STUDENT_ID,
				new Object[] { t_course_teaching_class_id, t_student_id }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {

						stu.setId(rs.getString("id"));
						stu.setStudentId(t_student_id);
						stu.setCourseTeachingClassId(t_course_teaching_class_id);
						stu.setShowIndex(rs.getInt("show_index"));

					}

				});
		if (stu.getId() == null)
			return null;
		return stu;
	}
	
	public CourseTeachingClassStudent getTeachingClassStudentByTeachingClassIdAndShowIndex(
			String t_course_teaching_class_id, int show_index) {
		CourseTeachingClassStudent stu = new CourseTeachingClassStudent();
		getJdbcTemplate().query(GET_TEACHING_CLASS_STUDENT_BY_COURSE_TEACHING_CLASS_ID_SHOW_INDEX,
				new Object[] { t_course_teaching_class_id, show_index }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {

						stu.setId(rs.getString("id"));
						stu.setStudentId(rs.getString("t_student_id"));
						stu.setCourseTeachingClassId(t_course_teaching_class_id);
						stu.setShowIndex(show_index);

					}

				});
		if (stu.getId() == null)
			return null;
		return stu;
	}
	
	

}
