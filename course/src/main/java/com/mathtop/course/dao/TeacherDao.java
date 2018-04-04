package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.Department;
import com.mathtop.course.domain.Teacher;
import com.mathtop.course.utility.GUID;

@Repository
public class TeacherDao extends BaseDao<Teacher> {

	private final String GET_t_department_id_BY_TEACHERID = "SELECT t_department_id FROM t_department_teacher WHERE t_teacher_id=?";
	private final String Get_Teacher_By_user_id = "SELECT id,teacher_num FROM t_teacher WHERE t_user_id=?";
	private final String GET_TEACHER_BY_ID = "SELECT t_user_id,teacher_num FROM t_teacher WHERE id=?";
	private final String GET_TEACHER_BY_TEACHERNUM = "SELECT id,t_user_id FROM t_teacher WHERE teacher_num=?";
	private final String GET_COUNT = "SELECT count(*) FROM t_teacher";
	private final String Get_Count_By_User_Id = "SELECT count(*) FROM t_teacher where t_user_id=?";
	private final String INSERT_TEACHER = "INSERT INTO t_teacher(id,t_user_id,teacher_num) VALUES(?,?,?)";
	private final String INSERT_DEPARTMENTTEACHER = "INSERT INTO t_department_teacher(id,t_department_id,t_teacher_id) VALUES(?,?,?)";

	private final String DELETE_BY_ID = "DELETE FROM t_teacher WHERE id=?";

	private final String UPDATE_TEACHERNUM_BY_ID = "update t_teacher set teacher_num=? WHERE id=?";

	public void UpdateTeacherNumById(String t_teacher_id, String teacher_num) {
		if (t_teacher_id == null || teacher_num == null)
			return;

		Object params[] = new Object[] { teacher_num, t_teacher_id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_TEACHERNUM_BY_ID, params, types);
	}

	/*
	 * 删除
	 */
	public void deleteById(String t_teacher_id) {
		Object params[] = new Object[] { t_teacher_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	public int getCount() {
		return getJdbcTemplate().queryForObject(GET_COUNT, Integer.class);
	}
	
	public boolean isExist(String t_user_id) {		
		return getJdbcTemplate().queryForObject(Get_Count_By_User_Id, Integer.class,t_user_id)>0;		
	}

	/**
	 * 根据教师id得到教师所在部门id
	 */
	public String getDepartmentIdByTeacherId(String t_teacher_id) {
		return getJdbcTemplate().queryForObject(GET_t_department_id_BY_TEACHERID, new Object[] { t_teacher_id, },
				new int[] { Types.VARCHAR }, String.class);
	}

	/*
	 * 根据用户ID得到用户
	 */
	public Teacher getTeacherByID(String t_teacher_id) {

		
		Teacher teacher = getJdbcTemplate().queryForObject(GET_TEACHER_BY_ID, new Object[] { t_teacher_id },
				new RowMapper<Teacher>() {
					@Override
					public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
						Teacher teacher = new Teacher();
						teacher.setId(t_teacher_id);
						teacher.setUserId(rs.getString("t_user_id"));
						teacher.setTeacherNum(rs.getString("teacher_num"));
						return teacher;
					}

				});

		return teacher;
	}

	/*
	 * 根据教师工号ID得到用户
	 */
	public Teacher getTeacherByTeacherNum(String teacher_num) {

		Teacher teacher = getJdbcTemplate().queryForObject(GET_TEACHER_BY_TEACHERNUM, new Object[] { teacher_num },
				new RowMapper<Teacher>() {

					@Override
					public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
						Teacher teacher = new Teacher();
						teacher.setId(rs.getString("id"));
						teacher.setUserId(rs.getString("t_user_id"));

						teacher.setTeacherNum(teacher_num);
						return teacher;

					}

				});

		return teacher;
	}

	/**
	 * 根据t_user_id得到用户
	 * 
	 * @param t_user_id
	 * @return
	 */
	public Teacher getTeacherByUserId(String t_user_id) {
		
		if(!isExist(t_user_id))
			return null; 

		Teacher teacher = getJdbcTemplate().queryForObject(Get_Teacher_By_user_id, new Object[] { t_user_id },
				new RowMapper<Teacher>() {

					@Override
					public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
						Teacher teacher = new Teacher();
						teacher.setId(rs.getString("id"));
						teacher.setUserId(t_user_id);

						teacher.setTeacherNum(rs.getString("teacher_num"));
						return teacher;

					}

				});

		return teacher;
	}

	/**
	 * 增加教师
	 * 
	 * @param department
	 * @param teacher
	 * @return
	 */
	public String add(Department department, Teacher teacher) {
		String id = GUID.getGUID();
		teacher.setId(id);
		Object params[] = new Object[] { id, teacher.getUserId(), teacher.getTeacherNum() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT_TEACHER, params, types);

		Object params1[] = new Object[] { GUID.getGUID(), department.getId(), id };
		int types1[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT_DEPARTMENTTEACHER, params1, types1);

		return id;
	}

}
