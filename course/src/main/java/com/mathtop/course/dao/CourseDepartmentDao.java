package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.CourseDepartment;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseDepartmentDao extends BaseDao<CourseDepartment> {

	private final String GET_PRECOURSE_BY_COURSE_ID_THIS = "select id,name"
			+ " from t_course " + " where t_course.id in" + " ("
			+ " select t_course_id" + " from t_department"
			+ " where t_precourse.t_course_id_this=?)" ;
	private final String GET_PRECOURSE_COUNT_BY_COURSE_ID_THIS = "SELECT t_user_id,student_num FROM t_student WHERE id=?";

	private final String INSERT_PRECOURSE = "INSERT INTO t_precourse(id,t_course_id_this,t_course_id_pre) VALUES(?,?,?)";

	/**
	 * */
	long getCount(String t_course_id_this) {

		return getJdbcTemplate().queryForObject(
				GET_PRECOURSE_COUNT_BY_COURSE_ID_THIS,
				new Object[] { t_course_id_this }, new int[] { Types.VARCHAR },
				Long.class);
	}

	private List<CourseDepartment> getData(String t_course_id) {

		List<CourseDepartment> list = new ArrayList<CourseDepartment>();

		CourseDepartment pc = new CourseDepartment();
		pc.setT_course_id(t_course_id);
		
		List<String> deptid = new ArrayList<String>();
		List<String> deptname = new ArrayList<String>();
		
		

		getJdbcTemplate().query(GET_PRECOURSE_BY_COURSE_ID_THIS,
				new Object[] { t_course_id }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {

						String id=rs.getString("id");
						deptid.add(id);
						String name=rs.getString("name");
						deptname.add(name);
					}

				});
		
		
		int nPreIdSize = deptid.size();
		if(nPreIdSize>0){
		
			
			pc.setT_department_id(deptid
						.toArray(new String[nPreIdSize]));
			pc.setT_department_name(deptname
						.toArray(new String[nPreIdSize]));
				
			
			
			list.add(pc);
		}

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
	public Page<CourseDepartment> getPage(String t_course_id) {

		long totalCount = getCount(t_course_id);
		if (totalCount < 1)
			return new Page<CourseDepartment>();

		List<CourseDepartment> data = getData(t_course_id);

		return new Page<CourseDepartment>(0, totalCount, (int) totalCount, data);

	}

	/* 增加 */
	public String add(String t_course_id, String t_department_id) {
		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_id, t_department_id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT_PRECOURSE, params, types);

		return id;
	}

}
