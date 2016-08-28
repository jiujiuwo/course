package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.Department;
import com.mathtop.course.domain.DepartmentTeacher;
import com.mathtop.course.utility.GUID;

@Repository
public class DepartmentTeacherDao extends BaseDao<DepartmentTeacher> {
	
	@Autowired
	private DepartmentDao departmentDao;
	
	private final String GET_t_department_id_BY_TEACHERID = "SELECT t_department_id FROM t_department_teacher WHERE t_teacher_id=?";
	private final String GET_TEACHERID_BY_t_department_id = "SELECT t_teacher_id FROM t_department_teacher WHERE t_department_id=?";
	private final String GET_DEPARTMENTTEACHER_BY_ID = "SELECT t_department_id,t_teacher_id FROM t_department_teacher WHERE id=?";
	
	private final String INSERT_DEPARTMENTTEACHER = "INSERT INTO t_department_teacher(id,t_department_id,t_teacher_id) VALUES(?,?,?)";
	
	private final String DELETE_BY_ID = "DELETE FROM t_department_teacher WHERE id=?";
	private final String DELETE_BY_DEPARTMENT_ID = "DELETE FROM t_department_teacher WHERE t_department_id=?";
	private final String DELETE_BY_TEACHER_ID = "DELETE FROM t_department_teacher WHERE t_teacher_id=?";
	
	
	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	
	
	public void deleteByTeacherId(String t_teacher_id) {
		Object params[] = new Object[] { t_teacher_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_TEACHER_ID, params, types);
	}

	
	public void deleteByDepartmentId(String t_department_id) {
		Object params[] = new Object[] { t_department_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_DEPARTMENT_ID, params, types);
	}

	/**
	 * */
	public String gett_department_idByTeacherId(String t_teacher_id){
		return getJdbcTemplate().queryForObject(
				GET_t_department_id_BY_TEACHERID,
				new Object[] { t_teacher_id, },
				new int[] { Types.VARCHAR}, String.class);
	}
	
	
	public Department getDepartmentByTeacherId(String t_teacher_id){		
		
		String id= gett_department_idByTeacherId(t_teacher_id);
		
		if(id!=null)
			return departmentDao.getByID(id);			
		
		return null;
	}
	
	
	/*根据用户ID得到用户
	 * */
	public DepartmentTeacher getById(String id){
		
		DepartmentTeacher dt=new DepartmentTeacher();
		
		
		
		getJdbcTemplate().query(GET_DEPARTMENTTEACHER_BY_ID, new Object[]{id},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
			
				dt.setId(id);	
				dt.setDepartmentId(rs.getString("t_department_id"));
				dt.setTeacherId(rs.getString("t_teacher_id"));
			
			}
			
		});
		if(dt.getId()==null)
			return null;
		return dt;
	}
	
	/**
	 * 得到指定部门的全部教师id
	 * */
	public List<String> getByTeacherIdByt_department_id(String t_department_id){
		
		List<String> list=new ArrayList<String>();
		
		
		
		getJdbcTemplate().query(GET_TEACHERID_BY_t_department_id, new Object[]{t_department_id},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
			
				
				list.add(rs.getString("t_teacher_id"));
			
			}
			
		});
		
		return list;
	}
	
	
	/*增加*/
	public String add(String t_department_id,String t_teacher_id){
		String id=GUID.getGUID();
		
		Object params[]=new Object[]{id,t_department_id,t_teacher_id};
		int types[]=new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		getJdbcTemplate().update(INSERT_DEPARTMENTTEACHER, params, types);
		
		
		
		
		
		return id;
	}

}
