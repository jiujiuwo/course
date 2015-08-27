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
import com.mathtop.course.utility.GUID;

@Repository
public class DepartmentNaturalClassDao extends BaseDao<DepartmentNaturalClass> {

	private final String GET_BY_t_natural_class_id = "SELECT id,t_department_id FROM t_natural_class_department WHERE t_natural_class_id=?";
	private final String GET_t_natural_class_id_BY_t_department_id = "SELECT id,t_natural_class_id FROM t_natural_class_department WHERE t_department_id=?";

	private final String GET_BY_ID = "SELECT t_department_id,t_natural_class_id FROM t_natural_class_department WHERE id=?";

	private final String INSERT = "INSERT INTO t_natural_class_department(id,t_natural_class_id,t_department_id) VALUES(?,?,?)";

	private final String DELETE_BY_ID = "DELETE FROM t_natural_class_department WHERE id=?";
	private final String DELETE_BY_t_department_id = "DELETE FROM t_natural_class_department WHERE t_department_id=?";
	private final String DELETE_BY_NATURALID = "DELETE FROM t_natural_class_department WHERE t_natural_class_id=?";

	@Autowired
	private DepartmentDao departmentDao;

	@Autowired
	private NaturalClassDao naturalclassDao;

	/**
	 * 增加记录
	 * */
	public String add(String t_school_id, String t_department_id,
			String t_natural_class_id) {
		String newid = GUID.getGUID();

		Object params[] = new Object[] { newid, t_school_id, t_department_id,
				t_natural_class_id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR };
		getJdbcTemplate().update(INSERT, params, types);

		return newid;
	}

	/**
	 * 删除指定id
	 * */
	public void deleteById(String id) {

		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);

	}

	

	/**
	 * 删除
	 * */

	public void deleteByt_department_id(String t_department_id) {

		Object params[] = new Object[] { t_department_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_t_department_id, params, types);

	}

	/**
	 * 删除
	 * */
	public void deleteByt_natural_class_id(String t_natural_class_id) {

		Object params[] = new Object[] { t_natural_class_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_NATURALID, params, types);

	}

	public DepartmentNaturalClass getNaturalClassDepartmentById(String id) {
		DepartmentNaturalClass n = new DepartmentNaturalClass();

		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };

		getJdbcTemplate().query(GET_BY_ID, params, types,
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						n.setId(id);
						
						n.setT_department_id(rs.getString("t_department_id"));
						n.setT_natural_class_id(rs
								.getString("t_natural_class_id"));

					}

				});

		if (n.getId() != null)
			return n;
		return null;
	}

	/**
	 * 根据t_natural_class_id得到记录
	 * */
	public DepartmentNaturalClass getNaturalClassStudentByt_natural_class_id(
			String t_natural_class_id) {
		DepartmentNaturalClass n = new DepartmentNaturalClass();

		Object params[] = new Object[] { t_natural_class_id };
		int types[] = new int[] { Types.VARCHAR };

		getJdbcTemplate().query(GET_BY_t_natural_class_id, params, types,
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						n.setId(rs.getString("id"));
						
						n.setT_department_id(rs.getString("t_department_id"));
						n.setT_natural_class_id(t_natural_class_id);

					}

				});

		if (n.getId() != null)
			return n;
		return null;
	}

	/**
	 * 根据
	 * */
	public List<String> gett_natural_class_idByt_department_id(String t_department_id) {

		List<String> list = new ArrayList<String>();

		Object params[] = new Object[] { t_department_id };
		int types[] = new int[] { Types.VARCHAR };

		getJdbcTemplate().query(GET_t_natural_class_id_BY_t_department_id, params,
				types, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						list.add(rs.getString("t_natural_class_id"));

					}

				});

		return list;
	}

	/**
	 * 根据
	 * */
	public Department getDepartmentByt_natural_class_id(String t_natural_class_id) {
		DepartmentNaturalClass ncs = getNaturalClassStudentByt_natural_class_id(t_natural_class_id);
		if (ncs != null) {
			return departmentDao.getByID(ncs.getT_department_id());
		}
		return null;
	}
	
	

	/**
	 * 根据
	 * */
	public NaturalClass getNaturalClassById(String id) {
		DepartmentNaturalClass ncs = getNaturalClassDepartmentById(id);
		if (ncs != null) {
			return naturalclassDao.getByID(ncs.getT_natural_class_id());
		}
		return null;
	}

	/**
	 * 根据学院id得到该学院下所有的自然班
	 * */
	public List<NaturalClass> getNaturalClassByt_department_id(
			String t_department_id) {

		List<String> ids = gett_natural_class_idByt_department_id(t_department_id);
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

	

}
