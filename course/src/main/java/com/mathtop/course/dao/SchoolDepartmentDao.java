package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.School;
import com.mathtop.course.domain.SchoolDepartment;
import com.mathtop.course.utility.GUID;

@Repository
public class SchoolDepartmentDao extends BaseDao<SchoolDepartment> {
	
	@Autowired
	private SchoolDao schoolDao;

	private final String GET_SCHOOL_ID_BY_DEPARTMENT_ID = "SELECT t_school_id FROM t_school_department WHERE t_department_id=?";
	private final String GET_DEPARTMENT_ID_BY_SCHOOL_ID = "SELECT t_department_id FROM t_school_department WHERE t_school_id=?";
	private final String GET_DEPARTMENT_COUNT_BY_SCHOOL_ID_DEPARTMENT_NAME = "select count(*) from t_school_department,t_department where  t_school_department.t_department_id=t_department.id and t_school_department.t_school_id=? and t_department.name=?";
	private final String INSERT = "INSERT INTO t_school_department(id,t_school_id,t_department_id) VALUES(?,?,?)";
	private final String DELETE = "DELETE FROM t_school_department WHERE t_school_id=? and t_department_id=?";
	private final String DELETE_BY_DEPARTMENT_ID = "DELETE FROM t_school_department WHERE t_department_id=?";
	
	public String add(String t_school_id,String t_department_id){
		String newid=GUID.getGUID();
		
		Object params[]=new Object[]{newid,t_school_id,t_department_id};
		int types[]=new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		getJdbcTemplate().update(INSERT, params, types);
		
		return newid;
	}
	
	public void deleteByDepartmentId(String t_department_id){
		Object params[]=new Object[]{t_department_id};
		int types[]=new int[]{Types.VARCHAR};
		getJdbcTemplate().update(DELETE_BY_DEPARTMENT_ID, params, types);
	}
	
	
	public void DELETE(String t_school_id,String t_department_id){
		Object params[]=new Object[]{t_school_id,t_department_id};
		int types[]=new int[]{Types.VARCHAR,Types.VARCHAR};
		getJdbcTemplate().update(DELETE, params, types);
	}
	
	
	public String gett_school_idByt_department_id(String t_department_id){
		return getJdbcTemplate().queryForObject(
				GET_SCHOOL_ID_BY_DEPARTMENT_ID,
				new Object[] { t_department_id, },
				new int[] { Types.VARCHAR}, String.class);
	}
	
	public School getSchoolByt_department_id(String t_department_id){
		String id= gett_school_idByt_department_id(t_department_id);
		
		if(id!=null)
			return schoolDao.getByID(id);
		return null;
					
	}
	
	/*
	 * 
	 */
	public List<String> get_department_idByt_school_id(String t_school_id) {
		if (t_school_id == null || t_school_id.length() == 0)
			return null;

		List<String> list = new ArrayList<String>();
		Object params[] = new Object[] { t_school_id };
		int types[] = new int[] { Types.VARCHAR };

		getJdbcTemplate().query(GET_DEPARTMENT_ID_BY_SCHOOL_ID, params, types,
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						list.add(rs.getString("t_department_id"));

					}

				});

		return list;
	}

	/**
	 * */
	public long getDepartmentCount(String t_school_id, String departmentName) {
		Object params[] = new Object[] { t_school_id, departmentName };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR };
		return getJdbcTemplate().queryForObject(
				GET_DEPARTMENT_COUNT_BY_SCHOOL_ID_DEPARTMENT_NAME, params,
				types, Long.class);
	}

}
