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
import com.mathtop.course.domain.DepartmentNaturalClass;
import com.mathtop.course.domain.DepartmentNaturalClassViewData;
import com.mathtop.course.utility.GUID;

@Repository
public class DepartmentNaturalClassDao extends BaseDao<DepartmentNaturalClass> {

	@Autowired
	private DepartmentDao departmentDao;

	@Autowired
	private NaturalClassDao naturalclassDao;

	@Autowired
	SchoolDepartmentDao schoolDepartmentDao;

	private final String GET_BY_NATURAL_CLASS_ID = "SELECT id,t_department_id " + " FROM t_natural_class_department "
			+ " WHERE t_natural_class_id=?";

	private final String GET_NATURAL_CLASS_ID_BY_DEPARTMENT_ID = "SELECT id,t_natural_class_id FROM t_natural_class_department "
			+ " WHERE t_department_id=?";

	private final String GET_ID_BY_NATURAL_CLASS_ID_DEPARTMENT_ID = "SELECT id FROM t_natural_class_department "
			+ " WHERE t_department_id=? and t_natural_class_id=?";

	private final String GET_NATURAL_CLASS_ID_BY_SCHOOL_ID = "SELECT t_natural_class_id "
			+ "FROM t_natural_class_department A "
			+ "left outer join t_school_department B on A.t_department_id=B.t_department_id "
			+ " WHERE B.t_school_id=?";

	private final String GET_NATURAL_CLASS_ID_BY_DEPARTMENT_ID_AND_NATURAL_NAME = "SELECT A.id "
			+ "FROM t_natural_class A " + "left outer join t_natural_class_department B on B.t_natural_class_id=A.id "
			+ " WHERE B.t_department_id=? and A.name=?";

	private final String GET_BY_ID = "SELECT t_department_id,t_natural_class_id "
			+ "FROM t_natural_class_department WHERE id=?";

	private final String INSERT = "INSERT INTO t_natural_class_department(id,t_natural_class_id,t_department_id) VALUES(?,?,?)";

	private final String DELETE_BY_ID = "DELETE FROM t_natural_class_department " + "WHERE id=?";

	private final String DELETE_BY_t_department_id = "DELETE FROM t_natural_class_department "
			+ "WHERE t_department_id=?";

	private final String DELETE_BY_NATURALID = "DELETE FROM t_natural_class_department " + "WHERE t_natural_class_id=?";

	private final String GET_NATURALCLASSSCHOOLVIEWDATA_COUNT_BY_t_department_id = "SELECT count(*) "
			+ "FROM t_natural_class_department " + "WHERE t_department_id=?";

	/**
	 * 增加记录
	 */
	public String add(String t_department_id, String t_natural_class_id) {

		if (t_department_id == null || t_natural_class_id == null)
			return null;

		String newid = GUID.getGUID();

		Object params[] = new Object[] { newid, t_natural_class_id, t_department_id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };

		try {
			getJdbcTemplate().update(INSERT, params, types);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return newid;
	}

	/**
	 * 删除指定id
	 */
	public void deleteById(String id) {

		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);

	}

	/**
	 * 删除
	 */

	public void deleteByDepartmentId(String t_department_id) {

		Object params[] = new Object[] { t_department_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_t_department_id, params, types);

	}

	/**
	 * 删除
	 */
	public void deleteByNaturalclassId(String t_natural_class_id) {

		Object params[] = new Object[] { t_natural_class_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_NATURALID, params, types);

	}

	public DepartmentNaturalClass getById(String id) {
		DepartmentNaturalClass n = new DepartmentNaturalClass();

		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };

		getJdbcTemplate().query(GET_BY_ID, params, types, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				n.setId(id);

				n.setDepartmentId(rs.getString("t_department_id"));
				n.setNaturalClassId(rs.getString("t_natural_class_id"));

			}

		});

		if (n.getId() != null)
			return n;
		return null;
	}

	/**
	 * 根据t_natural_class_id得到记录
	 */
	public DepartmentNaturalClass getByNaturalclassId(String t_natural_class_id) {
		DepartmentNaturalClass n = new DepartmentNaturalClass();

		Object params[] = new Object[] { t_natural_class_id };
		int types[] = new int[] { Types.VARCHAR };

		getJdbcTemplate().query(GET_BY_NATURAL_CLASS_ID, params, types, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				n.setId(rs.getString("id"));

				n.setDepartmentId(rs.getString("t_department_id"));
				n.setNaturalClassId(t_natural_class_id);

			}

		});

		if (n.getId() != null)
			return n;
		return null;
	}

	/*
	 * 得到id
	 */
	public String getIdByDepartmentNaturalclassId(String t_department_id, String t_natural_class_id) {

		List<String> list = new ArrayList<String>();
		getJdbcTemplate().query(GET_ID_BY_NATURAL_CLASS_ID_DEPARTMENT_ID,
				new Object[] { t_department_id, t_natural_class_id }, new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						list.add(rs.getString("id"));
					}
				});

		if (list.size() == 0)
			return null;

		return list.get(0);

	}

	/*
	 * 得到id
	 */
	public String getIdByDepartmentIdNaturalclassName(String t_department_id, String t_natural_class_name) {
		List<String> list = new ArrayList<String>();
		getJdbcTemplate().query(GET_NATURAL_CLASS_ID_BY_DEPARTMENT_ID_AND_NATURAL_NAME,
				new Object[] { t_department_id, t_natural_class_name }, new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						list.add(rs.getString("id"));
					}
				});

		if (list.size() == 0)
			return null;
		return list.get(0);

	}

	/*
	 * 得到
	 */
	public DepartmentNaturalClass getByDepartmentNaturalclassId(String t_department_id, String t_natural_class_id) {

		String id = getIdByDepartmentNaturalclassId(t_department_id, t_natural_class_id);
		if (id == null)
			return null;

		return getById(id);
	}

	/**
	 * 根据
	 */
	public List<String> getNaturalClassIdByDepartmentId(String t_department_id) {

		List<String> list = new ArrayList<String>();

		Object params[] = new Object[] { t_department_id };
		int types[] = new int[] { Types.VARCHAR };

		getJdbcTemplate().query(GET_NATURAL_CLASS_ID_BY_DEPARTMENT_ID, params, types, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				list.add(rs.getString("t_natural_class_id"));

			}

		});

		return list;
	}

	/**
	 * 根据
	 */
	public Department getDepartmentByt_natural_class_id(String t_natural_class_id) {
		DepartmentNaturalClass ncs = getByNaturalclassId(t_natural_class_id);
		if (ncs != null) {
			return departmentDao.getByID(ncs.getDepartmentId());
		}
		return null;
	}

	/**
	 * 根据
	 */
	public NaturalClass getNaturalClassById(String id) {
		DepartmentNaturalClass ncs = getById(id);
		if (ncs != null) {
			return naturalclassDao.getByID(ncs.getNaturalClassId());
		}
		return null;
	}

	/**
	 * 根据系部id得到该学院下所有的自然班
	 */
	public List<NaturalClass> getNaturalClassByDepartmentId(String t_department_id) {

		List<String> ids = getNaturalClassIdByDepartmentId(t_department_id);
		if (ids != null) {

			List<NaturalClass> list = new ArrayList<NaturalClass>();

			for (String id : ids) {
				NaturalClass nc = naturalclassDao.getByID(id);
				list.add(nc);
			}

			return list;
		}
		return null;
	}

	/**
	 * 根据学院id得到该学院下所有的自然班
	 */
	public List<NaturalClass> getNaturalClassBySchoolId(String t_school_id) {

		Object params[] = new Object[] { t_school_id };
		int types[] = new int[] { Types.VARCHAR };

		List<NaturalClass> list = new ArrayList<NaturalClass>();

		getJdbcTemplate().query(GET_NATURAL_CLASS_ID_BY_SCHOOL_ID, params, types, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				NaturalClass nc = naturalclassDao.getByID(rs.getString("t_natural_class_id"));
				if (nc != null)
					list.add(nc);

			}

		});

		return list;
	}

	/**
	 * 根据系部id得到该学院下所有的自然班
	 */
	public List<DepartmentNaturalClass> getDepartmentNaturalClassByt_department_id(String t_department_id) {

		List<String> ids = getNaturalClassIdByDepartmentId(t_department_id);
		if (ids != null) {

			List<DepartmentNaturalClass> list = new ArrayList<DepartmentNaturalClass>();

			for (String id : ids) {
				DepartmentNaturalClass nc = getByNaturalclassId(id);
				list.add(nc);
			}

			return list;
		}
		return null;
	}

	/*
	 * 根据ID得到
	 */
	public DepartmentNaturalClassViewData getNaturalClassSchoolViewDataByID(String id) {

		DepartmentNaturalClass dc = getById(id);

		if (dc == null)
			return null;

		DepartmentNaturalClassViewData data = new DepartmentNaturalClassViewData();
		data.setDepartmentNaturalClass(dc);

		data.setNaturalclass(naturalclassDao.getByID(dc.getNaturalClassId()));
		data.setDepartment(departmentDao.getByID(dc.getDepartmentId()));

		data.setSchool(schoolDepartmentDao.getSchoolByDepartmentId(dc.getDepartmentId()));

		return data;
	}

	/*
	 * 根据t_department_id得到
	 */
	private List<DepartmentNaturalClassViewData> PageQuery(String t_department_id, int PageBegin, int PageSize) {

		List<DepartmentNaturalClassViewData> list = new ArrayList<DepartmentNaturalClassViewData>();

		List<DepartmentNaturalClass> temp = getDepartmentNaturalClassByt_department_id(t_department_id);
		for (DepartmentNaturalClass t : temp) {
			DepartmentNaturalClassViewData d = getNaturalClassSchoolViewDataByID(t.getId());
			list.add(d);
		}

		return list;
	}

	/*
	 * 得到总数
	 */
	long getCount(String t_department_id) {

		return getJdbcTemplate().queryForObject(GET_NATURALCLASSSCHOOLVIEWDATA_COUNT_BY_t_department_id,
				new Object[] { t_department_id }, new int[] { Types.VARCHAR }, Long.class);
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
	public Page<DepartmentNaturalClassViewData> getPage(String t_department_id, int pageNo, int pageSize) {

		long totalCount = getCount(t_department_id);
		if (totalCount < 1)
			return new Page<DepartmentNaturalClassViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<DepartmentNaturalClassViewData> data = PageQuery(t_department_id, pageNo - 1, pageSize);

		return new Page<DepartmentNaturalClassViewData>(startIndex, totalCount, pageSize, data);

	}

	/*
	 * 根据t_department_id得到
	 */
	private List<NaturalClass> NaturalClassPageQuery(String t_department_id, int PageBegin, int PageSize) {

		List<NaturalClass> list = new ArrayList<NaturalClass>();

		List<DepartmentNaturalClass> temp = getDepartmentNaturalClassByt_department_id(t_department_id);
		for (DepartmentNaturalClass t : temp) {
			NaturalClass d = naturalclassDao.getByID(t.getNaturalClassId());
			list.add(d);
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
	public Page<NaturalClass> getNaturalClassPage(String t_department_id, int pageNo, int pageSize) {

		long totalCount = getCount(t_department_id);
		if (totalCount < 1)
			return new Page<NaturalClass>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<NaturalClass> data = NaturalClassPageQuery(t_department_id, pageNo - 1, pageSize);

		return new Page<NaturalClass>(startIndex, totalCount, pageSize, data);

	}

}
