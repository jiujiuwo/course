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
import com.mathtop.course.domain.School;
import com.mathtop.course.domain.Teacher;
import com.mathtop.course.domain.TeacherViewData;
import com.mathtop.course.domain.User;
import com.mathtop.course.domain.UserBasicInfo;
import com.mathtop.course.domain.UserContactInfoViewData;

@Repository
public class TeacherViewDataDao extends BaseDao<TeacherViewData> {

	@Autowired
	private TeacherDao teacherDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	UserContactInfoViewDataDao usercontactinfoviewDao;

	@Autowired
	private UserBasicInfoDao userbasicinfoDao;

	@Autowired
	DepartmentTeacherDao departmentteacherDao;

	@Autowired
	UserGroupDao usergroupDao;

	@Autowired
	SchoolDepartmentDao schooldepartmentDao;

	@Autowired
	SchoolDao schoolDao;

	private final String GET_TEACHERVIEWDATA_BY_t_school_id = "select id,t_user_id  from t_teacher  where id in("
			+ " select t_teacher_id from t_department_teacher where t_department_id in( "
			+ " select t_department_id from t_department_teacher where t_department_id in(select t_department_id from t_school_department where t_school_id=?)))"
			+ " order by teacher_num    limit ?,?";

	private final String GET_ALL_TEACHERVIEWDATA_BY_t_school_id = "select id,t_user_id from t_teacher  where id in("
			+ " select t_teacher_id from t_department_teacher where t_department_id in( "
			+ " select t_department_id from t_department_teacher where t_department_id in(select t_department_id from t_school_department where t_school_id=?)))";

	private final String GET_TEACHERVIEWDATA_COUNT_BY_t_school_id = "select count(*) from t_teacher  where id in("
			+ " select t_teacher_id from t_department_teacher where t_department_id in( "
			+ " select t_department_id from t_department_teacher where t_department_id in(select t_department_id from t_school_department where t_school_id=?)))";

	public TeacherViewData getTeacherViewDataById(String id) {
		Teacher teacher = teacherDao.getTeacherByID(id);
		if (teacher != null)
			return getTeacherViewDataByt_user_id(teacher.getT_user_id());
		return null;
	}

	public TeacherViewData getTeacherViewDataByt_user_id(String t_user_id) {
		User u = userDao.getUserByID(t_user_id);
		UserBasicInfo userbasicinfo = userbasicinfoDao.getUserBasicInfoByt_user_id(t_user_id);
		List<UserContactInfoViewData> usercontactinfoviewdata = usercontactinfoviewDao.getUserContactInfoViewDataByt_user_id(t_user_id);
		Teacher teacher = teacherDao.getTeacherByt_user_id(t_user_id);
		if (teacher != null) {
			Department department = departmentteacherDao.getDepartmentByTeacherId(teacher.getId());
			if (department != null) {
				School school = schooldepartmentDao.getSchoolByt_department_id(department.getId());

				TeacherViewData view = new TeacherViewData();
				view.setUser(u);
				view.setTeacher(teacher);
				view.setSchool(school);
				view.setDepartment(department);
				view.setUserbasicinfo(userbasicinfo);
				view.setUsercontactinfoviewdata(usercontactinfoviewdata);

				return view;
			} else
				return null;
		}

		return null;
	}

	public TeacherViewData getTeacherViewDataByTeacherNum(String teacher_num) {
		Teacher teacher = teacherDao.getTeacherByTeacherNum(teacher_num);
		if (teacher != null)
			return getTeacherViewDataByt_user_id(teacher.getT_user_id());
		return null;
	}
	
	public TeacherViewData getTeacherViewDataByTeacherId(String id) {
		Teacher teacher = teacherDao.getTeacherByID(id);
		if (teacher != null)
			return getTeacherViewDataByt_user_id(teacher.getT_user_id());
		return null;
	}

	public List<TeacherViewData> getTeacherViewByt_school_id(String t_school_id) {
		List<TeacherViewData> list = new ArrayList<TeacherViewData>();

		getJdbcTemplate().query(GET_ALL_TEACHERVIEWDATA_BY_t_school_id, new Object[] { t_school_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				TeacherViewData tvd = getTeacherViewDataByt_user_id(rs.getString("t_user_id"));

				list.add(tvd);

			}

		});

		return list;
	}

	/*
	 * 根据t_user_id得到用户
	 */
	private List<TeacherViewData> PageQuery(String t_school_id, int PageBegin, int PageSize) {

		List<TeacherViewData> list = new ArrayList<TeacherViewData>();

		getJdbcTemplate().query(GET_TEACHERVIEWDATA_BY_t_school_id, new Object[] { t_school_id, PageBegin * PageSize, PageSize, }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				TeacherViewData tvd = getTeacherViewDataByt_user_id(rs.getString("t_user_id"));
				list.add(tvd);

			}

		});

		return list;
	}

	/*
	 * 得到学院总数
	 */
	long getCount(String t_school_id) {

		return getJdbcTemplate().queryForObject(GET_TEACHERVIEWDATA_COUNT_BY_t_school_id, new Object[] { t_school_id }, new int[] { Types.VARCHAR }, Long.class);
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
	public Page<TeacherViewData> getPage(String t_school_id, int pageNo, int pageSize) {

		long totalCount = getCount(t_school_id);
		if (totalCount < 1)
			return new Page<TeacherViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<TeacherViewData> data = PageQuery(t_school_id, pageNo - 1, pageSize);

		return new Page<TeacherViewData>(startIndex, totalCount, pageSize, data);

	}
}
