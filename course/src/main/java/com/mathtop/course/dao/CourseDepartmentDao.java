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
import com.mathtop.course.domain.CourseDepartment;
import com.mathtop.course.domain.CourseDepartmentViewData;
import com.mathtop.course.domain.Department;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseDepartmentDao extends BaseDao<CourseDepartment> {

	@Autowired
	CourseDao courseDao;
	
	
	@Autowired
	DepartmentDao departmentDao;
	
	private final String GET_BY_ID = "select t_course_id,t_department_id from t_course_department   where  id=?";
	private final String GET_BY_COURSE_ID = "select id,t_department_id from t_course_department   where  t_course_id=?";
	private final String GET_BY_DEPARTMENT_ID = "select id,t_course_id from t_course_department   where  t_department_id=?";
	
	private final String GET_COUNT_BY_COURSE_ID = "select count(*) from t_course_department   where  t_course_id=?";

	private final String INSERT_COURSE_DEPARTMENT = "INSERT INTO t_course_department(id,t_course_id,t_department_id) VALUES(?,?,?)";

	
	//delete
	
	private String DELETE_BY_ID = "DELETE FROM t_course_department WHERE id=?";
	private String DELETE_BY_COURSE_ID = "DELETE FROM t_course_department WHERE t_course_id=?";
	private String DELETE_BY_DEPARTMENT_ID = "DELETE FROM t_course_department WHERE t_department_id=?";
	
	// update
	private String UPDATE_BY_ID = "update t_course_department set  t_course_id=?, t_department_id=?  WHERE id=?";

	public void update(String id, String t_course_id_pre, String t_department_id) {
		if (id == null)
			return;

		Object params[] = new Object[] { t_course_id_pre, t_department_id, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}

	/* 增加 */
	public String add(String t_course_id, String t_department_id) {
		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_id, t_department_id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT_COURSE_DEPARTMENT, params, types);

		return id;
	}
	
	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	
	public void deleteByCourseId(String t_course_id) {
		Object params[] = new Object[] { t_course_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_COURSE_ID, params, types);
	}

	
	public void deleteByDepartmentId(String t_department_id) {
		Object params[] = new Object[] { t_department_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_DEPARTMENT_ID, params, types);
	}


	/**
	 * */
	long getCount(String t_course_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_ID, new Object[] { t_course_id },
				new int[] { Types.VARCHAR }, Long.class);
	}
	
	public CourseDepartment getById(String id){
		CourseDepartment cd=new CourseDepartment();
		
		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {

				cd.setId(id);
				cd.setT_course_id(rs.getString("t_course_id"));
				cd.setT_department_id(rs.getString("t_department_id"));
			}

		});
		
		if(cd.getId()==null)
			return null;
		
		return cd;
	}
	
	public CourseDepartmentViewData getCourseDepartmentViewDataById(String id){
		CourseDepartmentViewData cdvd=new CourseDepartmentViewData();
		CourseDepartment cd=getById(id);
		cdvd.setCourseDepartment(cd);
		
		Course course=courseDao.getCourseById(cd.getT_course_id());
		cdvd.setCourse(course);
		
		Department department=departmentDao.getByID(cd.getT_department_id());
		cdvd.setDepartment(department);
		
		return cdvd;
	}

	public List<CourseDepartment> getByCourseId(String t_course_id) {

		List<CourseDepartment> list = new ArrayList<CourseDepartment>();		

		getJdbcTemplate().query(GET_BY_COURSE_ID, new Object[] { t_course_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseDepartment cd=getById(rs.getString("id"));
				list.add(cd);			
			}

		});
		
		return list;
	}
	
	public List<CourseDepartment> getByDepartmentId(String t_department_id) {

		List<CourseDepartment> list = new ArrayList<CourseDepartment>();		

		getJdbcTemplate().query(GET_BY_DEPARTMENT_ID, new Object[] { t_department_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseDepartment cd=getById(rs.getString("id"));
				list.add(cd);			
			}

		});
		
		return list;
	}
	
	public List<CourseDepartmentViewData> getCourseDepartmentViewDataByCourseId(String t_course_id) {

		List<CourseDepartmentViewData> list = new ArrayList<CourseDepartmentViewData>();		

		getJdbcTemplate().query(GET_BY_COURSE_ID, new Object[] { t_course_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseDepartmentViewData cd=getCourseDepartmentViewDataById(rs.getString("id"));
				list.add(cd);			
			}

		});
		
		return list;
	}

}
