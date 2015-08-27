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
import com.mathtop.course.domain.NaturalClassStudent;
import com.mathtop.course.domain.School;
import com.mathtop.course.utility.GUID;

@Repository
public class NaturalClassStudentDao extends BaseDao<NaturalClassStudent> {

	private final String GET_STUDENTID_BY_t_natural_class_id = "SELECT t_student_id FROM t_natural_class_student WHERE t_natural_class_id=?";
	private final String GET_t_natural_class_id_BY_STUDENTID = "SELECT id,t_natural_class_id FROM t_natural_class_student WHERE t_student_id=?";
	private final String GET_BY_ID = "SELECT t_natural_class_id，t_student_id FROM t_natural_class_student WHERE id=?";
	private final String INSERT = "INSERT INTO t_natural_class_student(id,t_natural_class_id,t_student_id) VALUES(?,?,?)";
	private final String DELETE_BY_ID = "DELETE FROM t_natural_class_student WHERE id=?";
	private final String DELETE_BY_NATURALID = "DELETE FROM t_natural_class_student WHERE t_natural_class_id=?";

	@Autowired
	private SchoolDao schoolDao;

	@Autowired
	private DepartmentDao departmentDao;

	@Autowired
	private NaturalClassDao naturalclassDao;

	@Autowired
	DepartmentNaturalClassDao naturalclassschoolDao;
	
	@Autowired
	SchoolDepartmentDao schooldepartmentDao;

	/**
	 * 增加记录
	 * */
	public String add(String t_natural_class_id, String t_student_id) {
		String newid = GUID.getGUID();

		Object params[] = new Object[] { newid, t_natural_class_id,
				t_student_id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
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
	 * 删除指定自然班学生
	 * */
	public void deleteByt_natural_class_id(String t_natural_class_id) {

		Object params[] = new Object[] { t_natural_class_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_NATURALID, params, types);

	}

	public NaturalClassStudent getNaturalClassStudentById(String id) {
		NaturalClassStudent n = new NaturalClassStudent();

		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };

		getJdbcTemplate().query(GET_BY_ID, params, types,
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						n.setId(id);
						n.setT_natural_clas_id(rs
								.getString("t_natural_class_id"));
						n.setT_student_id(rs.getString("t_student_id"));

					}

				});

		if (n.getId() != null)
			return n;
		return null;
	}

	/**
	 * 根据学生id得到记录，注意，一个学生只能存在一个自然班中
	 * */
	public NaturalClassStudent getNaturalClassStudentByStudentId(
			String t_student_id) {
		NaturalClassStudent n = new NaturalClassStudent();

		Object params[] = new Object[] { t_student_id };
		int types[] = new int[] { Types.VARCHAR };

		getJdbcTemplate().query(GET_t_natural_class_id_BY_STUDENTID, params, types,
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						n.setId(rs.getString("id"));
						n.setT_natural_clas_id(rs
								.getString("t_natural_class_id"));
						n.setT_student_id(t_student_id);

					}

				});

		if (n.getId() != null)
			return n;
		return null;
	}

	/**
	 * 根据自然班id得到该班的学生id
	 * */
	public List<String> getStudentIdByt_natural_class_id(String t_natural_class_id) {

		List<String> list = new ArrayList<String>();

		Object params[] = new Object[] { t_natural_class_id };
		int types[] = new int[] { Types.VARCHAR };

		getJdbcTemplate().query(GET_STUDENTID_BY_t_natural_class_id, params, types,
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						list.add(rs.getString("t_natural_class_id"));

					}

				});

		return list;
	}

	/**
	 * 根据学生id得到学生所在学院
	 * */
	public School getSchoolByStudentId(String t_student_id) {

		NaturalClassStudent ncs = getNaturalClassStudentByStudentId(t_student_id);

		if (ncs != null) {
			DepartmentNaturalClass ncst = naturalclassschoolDao
					.getNaturalClassStudentByt_natural_class_id(ncs
							.getT_natural_clas_id());
			if (ncst != null){
				Department department=getDepartmentByStudentId(ncst.getT_department_id());
				return schoolDao.getByID(schooldepartmentDao.gett_school_idByt_department_id(department.getId()));
			}
		}

		return null;
	}

	/**
	 * 根据学生id得到学生所在系部
	 * */
	public Department getDepartmentByStudentId(String t_student_id) {
		NaturalClassStudent ncs = getNaturalClassStudentByStudentId(t_student_id);

		if (ncs != null) {
			DepartmentNaturalClass ncst = naturalclassschoolDao
					.getNaturalClassStudentByt_natural_class_id(ncs
							.getT_natural_clas_id());
			if (ncst != null)
				return departmentDao.getByID(ncst.getT_department_id());
		}

		return null;
	}

	/**
	 * 根据学生id得到学生所在自然班
	 * */
	public NaturalClass getNaturalClassByStudentId(String t_student_id) {
		NaturalClassStudent ncs = getNaturalClassStudentByStudentId(t_student_id);

		if (ncs != null) {		
			
				return naturalclassDao.getByID(ncs.getT_natural_clas_id());
		}

		return null;
	}

	

}
