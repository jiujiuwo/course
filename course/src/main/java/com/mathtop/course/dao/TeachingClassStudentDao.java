package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.CourseTeachingClassStudent;
import com.mathtop.course.utility.GUID;

@Repository
public class TeachingClassStudentDao extends BaseDao<CourseTeachingClassStudent> {

	private final String INSERT_TEACHING_CLASS_STUDENT = "INSERT INTO t_teaching_class_student(id,t_teaching_class_id,t_student_id) VALUES(?,?,?)";
	
	private final String IS_STUDENT_EXIST = "select count(*) from t_teaching_class_student where t_teaching_class_id=? and t_student_id=?";
	private final String GET_TEACHING_CLASS_STUDENT_BY_STUDENT_ID = "SELECT id,t_teaching_class_id FROM t_teaching_class_student WHERE t_student_id=?";
	private final String GET_TEACHING_CLASS_STUDENT_BY_TEACHING_CLASS_ID = "SELECT t_teaching_class_student.id,t_student_id "
			+ " FROM t_teaching_class_student right join t_student on t_student.id=t_teaching_class_student.t_student_id"
			+ " WHERE t_teaching_class_id=? order by student_num";
	private final String GET_STUDENT_COUNT_TEACHING_CLASS_STUDENT_BY_TEACHING_CLASS_ID = "SELECT count(*) FROM t_teaching_class_student WHERE t_teaching_class_id=?";
	
	private String DELETE_BY_ID = "DELETE FROM t_teaching_class_student WHERE id=?";
	private String DELETE_BY_TEACHING_CLASS_ID = "DELETE FROM t_teaching_class_student WHERE t_teaching_class_id=?";
	private String DELETE_BY_STUDENT_ID = "DELETE FROM t_teaching_class_student WHERE t_student_id=?";
	private String DELETE_BY_TEACHING_CLASS_ID_AND_STUDENT_ID = "DELETE FROM t_teaching_class_student WHERE t_teaching_class_id=? and t_student_id=?";

	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}
	
	public void deleteByTeachingClassId(String t_teaching_class_id) {
		Object params[] = new Object[] { t_teaching_class_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_TEACHING_CLASS_ID, params, types);
	}
	
	public void deleteByStudentId(String t_student_id) {
		Object params[] = new Object[] { t_student_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_STUDENT_ID, params, types);
	}
	
	public void deleteByTeachingClassIdAndStudentId(String t_teaching_class_id,String t_student_id) {
		Object params[] = new Object[] { t_teaching_class_id ,t_student_id};
		int types[] = new int[] { Types.VARCHAR,Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_TEACHING_CLASS_ID_AND_STUDENT_ID, params, types);
	}
	
	public int getStudentCountByTeachingClassId(String t_teaching_class_id) {

		return getJdbcTemplate().queryForObject(GET_STUDENT_COUNT_TEACHING_CLASS_STUDENT_BY_TEACHING_CLASS_ID,
				new Object[] { t_teaching_class_id }, new int[] { Types.VARCHAR }, Integer.class);
	}

	long IsStudentExist(String t_teaching_class_id, String t_student_id) {

		return getJdbcTemplate().queryForObject(IS_STUDENT_EXIST, new Object[] { t_teaching_class_id, t_student_id },
				new int[] { Types.VARCHAR, Types.VARCHAR }, Long.class);
	}

	/* 增加学生 */
	public String add(String t_teaching_class_id, String t_student_id) {

		if (IsStudentExist(t_teaching_class_id, t_student_id) > 0)
			return null;

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_teaching_class_id, t_student_id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT_TEACHING_CLASS_STUDENT, params, types);

		return id;
	}

	public List<CourseTeachingClassStudent> getTeachingClassStudentByStudentId(String t_student_id) {
		List<CourseTeachingClassStudent> list = new ArrayList<CourseTeachingClassStudent>();
		getJdbcTemplate().query(GET_TEACHING_CLASS_STUDENT_BY_STUDENT_ID, new Object[] { t_student_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseTeachingClassStudent stu = new CourseTeachingClassStudent();
				stu.setId(rs.getString("id"));
				stu.setT_student_id(t_student_id);
				stu.setT_teaching_class_id(rs.getString("t_teaching_class_id"));
				list.add(stu);
			}

		});
		return list;
	}

	public List<CourseTeachingClassStudent> getTeachingClassStudentByTeachingClassId(String t_teaching_class_id) {
		List<CourseTeachingClassStudent> list = new ArrayList<CourseTeachingClassStudent>();
		getJdbcTemplate().query(GET_TEACHING_CLASS_STUDENT_BY_TEACHING_CLASS_ID, new Object[] { t_teaching_class_id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassStudent stu = new CourseTeachingClassStudent();
						stu.setId(rs.getString("id"));
						stu.setT_student_id(rs.getString("t_student_id"));
						stu.setT_teaching_class_id(t_teaching_class_id);
						list.add(stu);
					}

				});
		return list;
	}
	


}
