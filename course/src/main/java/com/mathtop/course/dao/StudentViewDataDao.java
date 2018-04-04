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
import com.mathtop.course.domain.Student;
import com.mathtop.course.domain.StudentViewData;
import com.mathtop.course.domain.TeacherViewData;
import com.mathtop.course.domain.User;
import com.mathtop.course.domain.UserBasicInfo;
import com.mathtop.course.domain.UserContactInfoViewData;

@Repository
public class StudentViewDataDao extends BaseDao<TeacherViewData> {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	UserContactInfoViewDataDao usercontactinfoviewDao;

	@Autowired
	private UserBasicInfoDao userbasicinfoDao;

	@Autowired
	NaturalClassDao naturalclassDao;

	@Autowired
	UserGroupDao usergroupDao;

	@Autowired
	NaturalClassStudentDao naturalclassstudentDao;

	@Autowired
	DepartmentNaturalClassDao naturalclassschoolDao;

	@Autowired
	SchoolDao schoolDao;

	@Autowired
	SchoolDepartmentDao schooldepartmentDao;

	private final String GET_STUDENTVIEWDATA_BY_NATUAL_CLASS_ID = "select id,t_user_id "
			+ " from t_student where id in(select t_student_id from t_natural_class_student where t_natural_class_id=?) order by student_num";

	private final String GET_PAGED_STUDENTVIEWDATA_BY_NATURAL_CLASS_ID = "select id,t_user_id "
			+ " from t_student where id in(select t_student_id from t_natural_class_student where t_natural_class_id=?) order by student_num limit ?,?";

	private final String GET_STUDENTVIEWDATA_BY_COURSE_TEACHING_CLASS_ID = "select A.id,A.t_user_id  from t_student A "
			+ "LEFT OUTER JOIN t_course_teaching_class_student  B ON B.t_student_id= A.id "
			+ "where B.t_course_teaching_class_id =? " + "order by show_index " + "limit ?,?";

	private final String GET_ALL_STUDENTVIEWDATA_BY_COURSE_TEACHING_CLASS_ID = "select A.id,t_user_id " 
			 +"from t_student A "
			 +"left outer join t_course_teaching_class_student C on A.id=C.t_student_id "
			 +"left outer join t_natural_class_student D on A.id=D.t_student_id "
			 +"left outer join t_natural_class B on B.id=D.t_natural_class_id "			 
			 +"where C.t_course_teaching_class_id =? "
		 +"order by show_index,name,student_num";

	private final String GET_STUDENTRVIEWDATA_COUNT_NATURAL_CLASS_ID = "select count(*)" + " from t_student"
			+ " where id in(select t_student_id from t_natural_class_student where t_natural_class_id=?)";

	private final String GET_STUDENTRVIEWDATA_COUNT_BY_TEACHINGCLASSID = "select count(*)"
			+ " from t_course_teaching_class_student" + " where t_course_teaching_class_id=?";

	private final String GET_STUDENTRVIEWDATA_NOT_GROUPED_BY_COURSE_TEACHING_CLASS_ID = "select A.t_student_id ,B.t_user_id "
			+ "from t_course_teaching_class_student A LEFT OUTER JOIN t_student  B ON B.id= A.t_student_id "
			+ "where A.t_course_teaching_class_id=? and B.t_user_id not in (  select t_user_id  from t_user_group "
			+ "where t_group_id in ( select t_group_id  from t_course_teaching_class_student_group "
			+ "where t_course_teaching_class_id=? )) order by A.show_index";

	/**
	 * 根据id得到学生视图
	 */
	public StudentViewData getStudentViewDataById(String id) {

		Student student = studentDao.getStudentByID(id);
		if (student != null)
			return getStudentViewDataByUserId(student.getUserId());
		return null;
	}

	/**
	 * 根据学号得到学生视图
	 */
	public StudentViewData getStudentViewDataByStudentNum(String student_num) {

		Student student = studentDao.getStudentByStudentNum(student_num);
		if (student != null)
			return getStudentViewDataByUserId(student.getUserId());
		return null;
	}

	/**
	 * 根据t_user_id得到学生视图
	 */
	public StudentViewData getStudentViewDataByUserId(String t_user_id) {

		User u = userDao.getUserByID(t_user_id);
		UserBasicInfo userbasicinfo = userbasicinfoDao.getUserBasicInfoByt_user_id(t_user_id);
		List<UserContactInfoViewData> usercontactinfoviewdata = usercontactinfoviewDao
				.getUserContactInfoViewDataByt_user_id(t_user_id);
		Student student = studentDao.getStudentByUserId(t_user_id);
		if (student != null) {
			NaturalClassStudent naturalclassstudent = naturalclassstudentDao
					.getNaturalClassStudentByStudentId(student.getId());
			if (naturalclassstudent != null) {
				DepartmentNaturalClass naturalclassDepartment = naturalclassschoolDao
						.getByNaturalclassId(naturalclassstudent.getNaturalClassId());
				if (naturalclassDepartment != null) {
					Department department = naturalclassschoolDao
							.getDepartmentByt_natural_class_id(naturalclassDepartment.getNaturalClassId());

					School school = schoolDao
							.getByID(schooldepartmentDao.getSchoolIdByDepartmentId(department.getId()));
					NaturalClass naturalclass = naturalclassDao.getByID(naturalclassDepartment.getNaturalClassId());
					StudentViewData view = new StudentViewData();
					view.setUser(u);
					view.setStudent(student);
					view.setSchool(school);
					view.setDepartment(department);
					view.setNaturalclass(naturalclass);
					view.setUserbasicinfo(userbasicinfo);
					view.setUsercontactinfoviewdata(usercontactinfoviewdata);

					return view;
				} else
					return null;
			} else
				return null;
		}

		return null;
	}

	/*
	 * 根据t_natural_class_id得到用户
	 */
	public List<StudentViewData> getStudentViewByt_natural_class_id(String t_natural_class_id) {

		List<StudentViewData> list = new ArrayList<StudentViewData>();

		getJdbcTemplate().query(GET_STUDENTVIEWDATA_BY_NATUAL_CLASS_ID, new Object[] { t_natural_class_id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						StudentViewData tvd = getStudentViewDataByUserId(rs.getString("t_user_id"));

						list.add(tvd);

					}

				});

		return list;
	}

	/*
	 * 根据t_natural_class_id得到用户
	 */
	public List<StudentViewData> PageQuery(String t_natural_class_id, int PageBegin, int PageSize) {

		List<StudentViewData> list = new ArrayList<StudentViewData>();

		getJdbcTemplate().query(GET_PAGED_STUDENTVIEWDATA_BY_NATURAL_CLASS_ID,
				new Object[] { t_natural_class_id, PageBegin * PageSize, PageSize }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						StudentViewData tvd = getStudentViewDataByUserId(rs.getString("t_user_id"));

						list.add(tvd);

					}

				});

		return list;
	}

	/*
	 * 根据教学班id得到用户
	 */
	public List<StudentViewData> getStudentViewByCourseTeachingClassId(String t_course_teaching_class_id, int PageBegin,
			int PageSize) {

		List<StudentViewData> list = new ArrayList<StudentViewData>();

	
		
		getJdbcTemplate().query(GET_STUDENTVIEWDATA_BY_COURSE_TEACHING_CLASS_ID,
				new Object[] { t_course_teaching_class_id, PageBegin * PageSize, PageSize }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {

						StudentViewData tvd = getStudentViewDataByUserId(rs.getString("t_user_id"));
						if (tvd != null)
							list.add(tvd);

					}

				});

		return list;
	}

	/*
	 * 根据教学班id得到用户
	 */
	public List<StudentViewData> getStudentViewByCourseTeachingClassId(String t_course_teaching_class_id) {
		System.out.println(t_course_teaching_class_id);
		List<StudentViewData> list = new ArrayList<StudentViewData>();

		getJdbcTemplate().query(GET_ALL_STUDENTVIEWDATA_BY_COURSE_TEACHING_CLASS_ID,
				new Object[] { t_course_teaching_class_id }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						StudentViewData tvd = getStudentViewDataByUserId(rs.getString("t_user_id"));
						
						if (tvd != null)
							list.add(tvd);

					}

				});

		return list;
	}

	/**
	 * 得到所有未分组的学生
	 */
	public List<StudentViewData> getNotGroupedStudentViewByCourseTeachingClassId(String t_course_teaching_class_id) {

		List<StudentViewData> list = new ArrayList<StudentViewData>();

		getJdbcTemplate().query(GET_STUDENTRVIEWDATA_NOT_GROUPED_BY_COURSE_TEACHING_CLASS_ID,
				new Object[] { t_course_teaching_class_id, t_course_teaching_class_id }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						StudentViewData tvd = getStudentViewDataByUserId(rs.getString("t_user_id"));

						list.add(tvd);

					}

				});

		return list;
	}

	/**
	 * 得到指定自然班学生人数
	 */
	long getCountByNaturalClassId(String t_natural_class_id) {

		return getJdbcTemplate().queryForObject(GET_STUDENTRVIEWDATA_COUNT_NATURAL_CLASS_ID,
				new Object[] { t_natural_class_id }, new int[] { Types.VARCHAR }, Long.class);
	}

	/**
	 * 得到指定教学班人数
	 */
	long getCountByCourseTeachingClassId(String t_course_teaching_class_id) {

		return getJdbcTemplate().queryForObject(GET_STUDENTRVIEWDATA_COUNT_BY_TEACHINGCLASSID,
				new Object[] { t_course_teaching_class_id }, new int[] { Types.VARCHAR }, Long.class);
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
	public Page<StudentViewData> getPageByt_natural_class_id(String t_natural_class_id, int pageNo, int pageSize) {

		long totalCount = getCountByNaturalClassId(t_natural_class_id);
		if (totalCount < 1)
			return new Page<StudentViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<StudentViewData> data = PageQuery(t_natural_class_id, pageNo - 1, pageSize);

		return new Page<StudentViewData>(startIndex, totalCount, pageSize, data);

	}

	/**
	 * 分页取得教学班（t_course_teaching_class_id）的全部学生
	 */
	public Page<StudentViewData> getPageByCourseTeachingClassId(String t_course_teaching_class_id, int pageNo,
			int pageSize) {
		long totalCount = getCountByCourseTeachingClassId(t_course_teaching_class_id);

		if (totalCount < 1)
			return new Page<StudentViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<StudentViewData> data = getStudentViewByCourseTeachingClassId(t_course_teaching_class_id, pageNo - 1,
				pageSize);

		return new Page<StudentViewData>(startIndex, totalCount, pageSize, data);
	}

	/**
	 * 取得教学班（t_course_teaching_class_id）的全部学生
	 */
	public Page<StudentViewData> getPageByCourseTeachingClassId(String t_course_teaching_class_id) {
		long totalCount = getCountByCourseTeachingClassId(t_course_teaching_class_id);
		if (totalCount < 1)
			return new Page<StudentViewData>();

		// 实际查询返回分页对象
		int startIndex = 0;

		List<StudentViewData> data = getStudentViewByCourseTeachingClassId(t_course_teaching_class_id, 0,
				(int) totalCount);

		return new Page<StudentViewData>(startIndex, totalCount, (int) totalCount, data);
	}

}
