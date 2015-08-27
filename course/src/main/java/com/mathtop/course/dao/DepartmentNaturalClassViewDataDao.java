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
import com.mathtop.course.domain.NaturalClass;
import com.mathtop.course.domain.DepartmentNaturalClassViewData;
import com.mathtop.course.domain.School;
import com.mathtop.course.utility.GUID;

@Repository
public class DepartmentNaturalClassViewDataDao extends BaseDao<DepartmentNaturalClassViewData> {

	private final String GET_NATURALCLASSSCHOOLVIEWDATA_COUNT_BY_t_school_id = "SELECT count(*) FROM t_natural_class_department WHERE t_school_id=?";
	private final String GET_NATURALCLASSSCHOOLVIEWDATA_COUNT_BY_t_department_id = "SELECT count(*) FROM t_natural_class_department WHERE t_department_id=?";
	
	private final String GET_NATURALCLASSSCHOOLVIEWDATA_BY_t_school_id = "SELECT t_natural_class_school.id as t_natural_class_school_id, "
			+ " t_school.name as t_school_name,"
			+ " t_natural_class_school.t_department_id as t_department_id, t_department.name as t_department_name ,"
			+ " t_natural_class.id as t_natural_class_id,t_natural_class.name as t_natural_class_name"
			+ " FROM t_natural_class_school left join t_school on t_natural_class_school.t_school_id  =t_school.id"
			+ " left join t_natural_class on t_natural_class_school.t_natural_class_id= t_natural_class.id"
			+ " left join t_department on t_natural_class_school.t_department_id= t_department.id"
			+ " WHERE t_school_id=?"
			+ " order by t_natural_class.name"
			+ " limit ?,?";

	private final String GET_NATURALCLASS_SCHOOL_BY_ID = "SELECT t_department_id,t_natural_class_id FROM t_natural_class_department WHERE id=?";
	private final String INSERT_NATURALCLASS_SCHOOL = "INSERT INTO t_natural_class_department(id,t_department_id,t_natural_class_id) VALUES(?,?,?,?)";

	private final String GET_NATURALCLASS_BY_t_department_id = "select t_natural_class.id,t_natural_class.name,t_natural_class.note"
			+ " from t_natural_class left join t_natural_class_department on t_natural_class.id=t_natural_class_department.t_natural_class_id"
			+ " where t_natural_class_department.t_department_id=?" 
			+ " order by t_natural_class.name";
	
	
	private String DELETE_NATURALCLASSSCHOOL_BY_t_natural_class_id = "DELETE FROM t_natural_class_department WHERE t_natural_class_id=?";
	private String DELETE_NATURALCLASSSTUDENT_BY_t_natural_class_id = "DELETE FROM t_natural_class_student WHERE t_natural_class_id=?";
	private String DELETE_NATURALCLASSTEACHER_BY_t_natural_class_id = "DELETE FROM t_natural_class_teacher WHERE t_natural_class_id=?";
	
	
	
	
	@Autowired
	private SchoolDao schoolDao;

	@Autowired
	private DepartmentDao departmentDao;

	@Autowired
	private NaturalClassDao naturalclassDao;

	public List<NaturalClass> getNaturalClassByt_department_id(String t_department_id) {
		List<NaturalClass> list = new ArrayList<NaturalClass>();

		getJdbcTemplate().query(GET_NATURALCLASS_BY_t_department_id,
				new Object[] { t_department_id }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						NaturalClass nc = new NaturalClass();

						nc.setId(rs.getString("id"));
						nc.setName(rs.getString("name"));
						nc.setNote(rs.getString("note"));
						
						list.add(nc);

					}

				});

		return list;
	}
	
	

	/*
	 * 根据ID得到
	 */
	public DepartmentNaturalClassViewData getNaturalClassSchoolViewDataByID(
			String id) {

		DepartmentNaturalClassViewData ncsvd = new DepartmentNaturalClassViewData();

		getJdbcTemplate().query(GET_NATURALCLASS_SCHOOL_BY_ID,
				new Object[] { id }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {

						ncsvd.setId(id);

						ncsvd.setT_school_id(rs.getString("t_t_school_id"));

						School s = schoolDao.getByID(rs.getString("t_t_school_id"));
						if (s != null)
							ncsvd.setT_school_name(s.getName());

						ncsvd.setT_department_id(rs
								.getString("t_department_id"));

						Department d = departmentDao.getByID(rs
								.getString("t_department_id"));
						if (d != null)
							ncsvd.setT_department_name(d.getName());

						ncsvd.setT_natural_class_id(rs
								.getString("t_naturalclass_id"));
						NaturalClass c = naturalclassDao.getByID(rs
								.getString("t_naturalclass_id"));
						if (c != null)
							ncsvd.setT_natural_class_name(c.getName());

					}

				});
		if (ncsvd.getId() == null)
			return null;
		return ncsvd;
	}

	/*
	 * 根据t_school_id
	 */
	public DepartmentNaturalClassViewData getNaturalClassSchoolViewDataByt_school_id(
			String t_school_id) {

		DepartmentNaturalClassViewData ncsvd = new DepartmentNaturalClassViewData();

		getJdbcTemplate().query(GET_NATURALCLASS_SCHOOL_BY_ID,
				new Object[] { t_school_id }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {

						// ncsvd.setId(id);

						ncsvd.setT_school_id(rs.getString("t_t_school_id"));

						School s = schoolDao.getByID(rs.getString("t_t_school_id"));
						if (s != null)
							ncsvd.setT_school_name(s.getName());

						ncsvd.setT_department_id(rs
								.getString("t_department_id"));

						Department d = departmentDao.getByID(rs
								.getString("t_department_id"));
						if (d != null)
							ncsvd.setT_department_name(d.getName());

						ncsvd.setT_natural_class_id(rs
								.getString("t_naturalclass_id"));
						NaturalClass c = naturalclassDao.getByID(rs
								.getString("t_naturalclass_id"));
						if (c != null)
							ncsvd.setT_natural_class_name(c.getName());

					}

				});
		if (ncsvd.getId() == null)
			return null;
		return ncsvd;
	}

	/*
	 * 根据
	 */
	public DepartmentNaturalClassViewData getNaturalClassSchoolViewDataByt_department_id(
			String t_department_id) {

		DepartmentNaturalClassViewData teacher = new DepartmentNaturalClassViewData();

		if (teacher.getId() == null)
			return null;
		return teacher;
	}

	/* 增加 */
	public String add(String t_school_id, String deptid, String naturalclassname,
			String naturalclassnote) {

		String id = naturalclassDao.add(naturalclassname, naturalclassnote);

		Object params1[] = new Object[] { GUID.getGUID(), t_school_id, deptid, id };
		int types1[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR };
		getJdbcTemplate().update(INSERT_NATURALCLASS_SCHOOL, params1, types1);

		return id;
	}

	/*
	 * 根据t_school_id得到
	 */
	private List<DepartmentNaturalClassViewData> PageQuery(String t_school_id,
			int PageBegin, int PageSize) {

		List<DepartmentNaturalClassViewData> list = new ArrayList<DepartmentNaturalClassViewData>();

		getJdbcTemplate().query(GET_NATURALCLASSSCHOOLVIEWDATA_BY_t_school_id,
				new Object[] { t_school_id, PageBegin * PageSize, PageSize, },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						DepartmentNaturalClassViewData ncsvd = new DepartmentNaturalClassViewData();
						ncsvd.setId(rs.getString("t_natural_class_school_id"));
						ncsvd.setT_school_id(t_school_id);
						ncsvd.setT_school_name(rs.getString("t_school_name"));
						ncsvd.setT_department_id(rs
								.getString("t_department_id"));
						ncsvd.setT_department_name(rs
								.getString("t_department_name"));
						ncsvd.setT_natural_class_id(rs
								.getString("t_natural_class_id"));
						ncsvd.setT_natural_class_name(rs
								.getString("t_natural_class_name"));

						list.add(ncsvd);
						
						

					}

				});

		return list;
	}

	/*
	 * 得到总数
	 */
	long getCount(String t_school_id) {

		return getJdbcTemplate().queryForObject(
				GET_NATURALCLASSSCHOOLVIEWDATA_COUNT_BY_t_school_id,
				new Object[] { t_school_id }, new int[] { Types.VARCHAR },
				Long.class);
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
	public Page<DepartmentNaturalClassViewData> getPage(String t_school_id,
			int pageNo, int pageSize) {

		long totalCount = getCount(t_school_id);
		if (totalCount < 1)
			return new Page<DepartmentNaturalClassViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<DepartmentNaturalClassViewData> data = PageQuery(t_school_id, pageNo - 1,
				pageSize);

		return new Page<DepartmentNaturalClassViewData>(startIndex, totalCount,
				pageSize, data);

	}
	
	/*
	 * 得到总数
	 */
	long getCountByt_department_id(String t_department_id) {

		return getJdbcTemplate().queryForObject(
				GET_NATURALCLASSSCHOOLVIEWDATA_COUNT_BY_t_department_id,
				new Object[] { t_department_id }, new int[] { Types.VARCHAR },
				Long.class);
	}
	
	public Page<NaturalClass> getPageByt_department_id(String t_department_id,int pageNo, int pageSize) {
		long totalCount = getCountByt_department_id(t_department_id);
		if (totalCount < 1)
			return new Page<NaturalClass>();

		// 实际查询返回分页对象
	//	int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<NaturalClass> data = getNaturalClassByt_department_id(t_department_id);

		return new Page<NaturalClass>(0, totalCount,(int)totalCount, data);
	}
	
	
	/**删除班级
	 * */
	public void DELETE(String t_natural_class_id){
		//删除自然班-学院
		
		Object params[]=new Object[]{t_natural_class_id};
		int types[]=new int[]{Types.VARCHAR};
		getJdbcTemplate().update(DELETE_NATURALCLASSSCHOOL_BY_t_natural_class_id, params, types);
		
		//删除自然班-学生		
		getJdbcTemplate().update(DELETE_NATURALCLASSSTUDENT_BY_t_natural_class_id, params, types);
		
		//删除自然班-教师		
		getJdbcTemplate().update(DELETE_NATURALCLASSTEACHER_BY_t_natural_class_id, params, types);
		
		//删除自然班
		naturalclassDao.DELETE(t_natural_class_id);
	}

}
