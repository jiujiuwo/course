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
import com.mathtop.course.domain.CourseDepartmentViewData;
import com.mathtop.course.domain.CoursePrecourseViewData;
import com.mathtop.course.domain.CourseStyle;
import com.mathtop.course.domain.CourseType;
import com.mathtop.course.domain.CourseViewData;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseDao extends BaseDao<Course> {
	
	@Autowired
	CourseStyleDao courseStyleDao;
	
	@Autowired
	CourseTypeDao courseTypeDao;
	
	@Autowired
	CoursePrecourseDao coursePrecourseDao;
	
	@Autowired
	CourseDepartmentDao courseDepartmentDao;
	
	private final String GET_COURSE_BY_ID = "select name ,english_name,num,class_hours,experiment_hours,t_course_type_id,t_course_style_id "
			+ " from t_course where id=?";
	
	private final String GET_COURSE_BY_COURSE_TYPE_ID = "select id,name ,english_name,num,class_hours,experiment_hours,t_course_style_id "
			+ " from t_course where t_course_type_id=?";
	
	private final String GET_COURSE_BY_COURSE_STYLE_ID = "select id,name ,english_name,num,class_hours,experiment_hours,t_course_type_id "
			+ " from t_course where t_course_style_id=?";
	
	private final String GET_COURSE_BY_COURSE_TYPE_ID_AND_STYLE_ID = "select id,name ,english_name,num,class_hours,experiment_hours "
			+ " from t_course where t_course_type_id=? and t_course_style_id=?";

	private final String GET_COURSE_BY_NUM = "select id ,name ,english_name,class_hours,experiment_hours,t_course_type_id,t_course_style_id"			
			+ " from t_course "
			+ " where t_course.num=?";

	private final String GET_COURSE_COUNT_BY_NUM = "select count(*) from t_course where t_course.num=?";

	private final String GET_ALL_COURSE_PAGEED = "select id,name,english_name,num,class_hours,experiment_hours,t_course_type_id,t_course_style_id"			
			+ " from t_course   order by t_course.name limit ?,?";
	
	private final String GET_ALL_COURSE = "select id,name,english_name,num,class_hours,experiment_hours,t_course_type_id,t_course_style_id"			
			+ " from t_course   order by t_course.name ";

	private final String GET_COURSE_COUNT = "select count(*) from t_course";
	private final String GET_COURSE_COUNT_BY_COURSE_STYLE_ID = "select count(*) from t_course where t_course_style_id=?";
	private final String GET_COURSE_COUNT_BY_COURSE_TYPE_ID = "select count(*) from t_course where t_course_type_id=?";
	private final String GET_COURSE_COUNT_BY_DEPARTMENT_ID = "select count(*)"
			+ " from t_course   where id in  ( select t_course_id"
			+ " from t_course_department" + " where t_department_id=?" + " )";
	private final String INSERT_COURSE = "INSERT INTO t_course(id,name,english_name,num,class_hours,experiment_hours,t_course_type_id,t_course_style_id) VALUES(?,?,?,?,?,?,?,?)";
	
	private final String UPDATE_COURSE = "update t_course set name=?,english_name=?,num=?,class_hours=?,experiment_hours=?,t_course_type_id=?,t_course_style_id=? where id=?";

	
	/**
	 * 修改
	 * */
	public String update(String id,String name, String englishname, String num,
			int class_hours, int experiment_hours, String t_course_type_id,
			String t_course_style_id) {

		

		Object params[] = new Object[] {  name, englishname, num,
				class_hours, experiment_hours, t_course_type_id,
				t_course_style_id ,id};
		int types[] = new int[] {  Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.VARCHAR,
				Types.VARCHAR,Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_COURSE, params, types);

		return id;
	}
	
	/**
	 * 增加
	 * */
	public String add(String name, String englishname, String num,
			int class_hours, int experiment_hours, String t_course_type_id,
			String t_course_style_id) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, name, englishname, num,
				class_hours, experiment_hours, t_course_type_id,
				t_course_style_id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.VARCHAR,
				Types.VARCHAR };
		getJdbcTemplate().update(INSERT_COURSE, params, types);

		return id;
	}

	/**
	 * 根据课程id查找课程
	 * */
	public Course getCourseById(String id) {
		Course c = new Course();

		getJdbcTemplate().query(GET_COURSE_BY_ID, new Object[] { id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						c.setId(id);
						c.setName(rs.getString("name"));
						c.setEnglishname(rs.getString("english_name"));
						c.setNum(rs.getString("num"));
						c.setClass_hours(rs.getInt("class_hours"));
						c.setExperiment_hours(rs.getInt("experiment_hours"));

						c.setCourseStyleId(rs
								.getString("t_course_style_id"));
						

						c.setCourseTypeId(rs.getString("t_course_type_id"));
						

					}

				});

		if (c.getId() == null)
			return null;
		return c;
	}
	
	/**
	 * 根据t_course_type_id查找课程
	 * */
	public List<Course> getByCourseTypeId(String t_course_type_id) {
		List<Course> list=new ArrayList<Course>();

		getJdbcTemplate().query(GET_COURSE_BY_COURSE_TYPE_ID, new Object[] { t_course_type_id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Course c = new Course();
						c.setId(rs.getString("id"));
						c.setName(rs.getString("name"));
						c.setEnglishname(rs.getString("english_name"));
						c.setNum(rs.getString("num"));
						c.setClass_hours(rs.getInt("class_hours"));
						c.setExperiment_hours(rs.getInt("experiment_hours"));

						c.setCourseStyleId(rs
								.getString("t_course_style_id"));
						

						c.setCourseTypeId(t_course_type_id);
						list.add(c);

					}

				});

		
		return list;
	}
	
	/**
	 * 根据t_course_style_id查找课程
	 * */
	public List<Course> getByCourseStyleId(String t_course_style_id) {
		List<Course> list=new ArrayList<Course>();

		getJdbcTemplate().query(GET_COURSE_BY_COURSE_STYLE_ID, new Object[] { t_course_style_id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Course c = new Course();
						c.setId(rs.getString("id"));
						c.setName(rs.getString("name"));
						c.setEnglishname(rs.getString("english_name"));
						c.setNum(rs.getString("num"));
						c.setClass_hours(rs.getInt("class_hours"));
						c.setExperiment_hours(rs.getInt("experiment_hours"));

						c.setCourseStyleId(t_course_style_id);
						

						c.setCourseTypeId(rs.getString("t_course_type_id"));
						list.add(c);

					}

				});

		
		return list;
	}
	
	/**
	 * 根据t_course_type_id,t_course_style_id查找课程
	 * */
	public List<Course> getByCourseTypeIdAndStyleId(String t_course_type_id,String t_course_style_id) {
		List<Course> list=new ArrayList<Course>();

		getJdbcTemplate().query(GET_COURSE_BY_COURSE_TYPE_ID_AND_STYLE_ID, new Object[] { t_course_type_id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Course c = new Course();
						c.setId(rs.getString("id"));
						c.setName(rs.getString("name"));
						c.setEnglishname(rs.getString("english_name"));
						c.setNum(rs.getString("num"));
						c.setClass_hours(rs.getInt("class_hours"));
						c.setExperiment_hours(rs.getInt("experiment_hours"));
						c.setCourseStyleId(t_course_style_id);						

						c.setCourseTypeId(t_course_type_id);
						list.add(c);

					}

				});

		
		return list;
	}
	
	public CourseViewData getCourseViewDataByCourseId(String t_course_id){
		Course course=getCourseById(t_course_id);
		if(course==null)
			return null;
		
		CourseViewData courseViewData=new CourseViewData();
		courseViewData.setCourse(course);
		
		CourseStyle courseStyle=courseStyleDao.getByID(course.getCourseStyleId());
		courseViewData.setCourseStyle(courseStyle);
		
		CourseType courseType=courseTypeDao.getByID(course.getCourseTypeId());
		courseViewData.setCourseType(courseType);
		
		List<CoursePrecourseViewData> listpre=coursePrecourseDao.getPreCourseViewData(t_course_id);
		courseViewData.setListCoursePrecourse(listpre);
		
		List<CourseDepartmentViewData> listdept=courseDepartmentDao.getCourseDepartmentViewDataByCourseId(t_course_id);
		courseViewData.setListCourseDepartment(listdept);
		
		
		return courseViewData;
		
	}

	/**
	 * 根据课程编号查找课程
	 * */
	public Course getCourseByCourseNum(String num) {
		Course c = new Course();

		getJdbcTemplate().query(GET_COURSE_BY_NUM, new Object[] { num },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						c.setId(rs.getString("id"));
						c.setName(rs.getString("name"));
						c.setEnglishname(rs.getString("english_name"));
						c.setNum(num);
						c.setClass_hours(rs.getInt("class_hours"));
						c.setExperiment_hours(rs.getInt("experiment_hours"));

						c.setCourseStyleId(rs
								.getString("t_course_style_id"));
					

						c.setCourseTypeId(rs.getString("t_course_type_id"));
						

					}

				});

		if (c.getId() == null)
			return null;
		return c;
	}

	/**
	 * 得到所有课程数目
	 * */
	public long getCount() {
		return getJdbcTemplate().queryForObject(GET_COURSE_COUNT, null, null,
				Long.class);
	}

	/**
	 * 得到
	 * */
	public long getCountByCourseStyle(String coursestyleId) {
		return getJdbcTemplate().queryForObject(
				GET_COURSE_COUNT_BY_COURSE_STYLE_ID,
				new Object[] { coursestyleId }, new int[] { Types.VARCHAR },
				Long.class);
	}

	/**
	 * 得到
	 * */
	public long getCountByCourseNum(String coursenum) {
		return getJdbcTemplate().queryForObject(GET_COURSE_COUNT_BY_NUM,
				new Object[] { coursenum }, new int[] { Types.VARCHAR },
				Long.class);
	}

	/**
	 * 得到
	 * */
	public long getCountByCourseType(String coursetypeId) {
		return getJdbcTemplate().queryForObject(
				GET_COURSE_COUNT_BY_COURSE_TYPE_ID,
				new Object[] { coursetypeId }, new int[] { Types.VARCHAR },
				Long.class);
	}

	/**
	 * 得到
	 * */
	public long getCountByCourset_department_id(String t_department_id) {
		return getJdbcTemplate().queryForObject(
				GET_COURSE_COUNT_BY_DEPARTMENT_ID,
				new Object[] { t_department_id }, new int[] { Types.VARCHAR },
				Long.class);
	}

	private List<CourseViewData> PageQuery(int PageBegin, int PageSize) {

		List<CourseViewData> list = new ArrayList<CourseViewData>();

		getJdbcTemplate().query(GET_ALL_COURSE_PAGEED,
				new Object[] { PageBegin * PageSize, PageSize, },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						
						CourseViewData c = getCourseViewDataByCourseId(rs.getString("id"));
						

						list.add(c);

					}

				});

		return list;
	}
	
	
	private List<CourseViewData> PageAllQuery() {

		List<CourseViewData> list = new ArrayList<CourseViewData>();

		getJdbcTemplate().query(GET_ALL_COURSE,
				new Object[] {  },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						
						CourseViewData c = getCourseViewDataByCourseId(rs.getString("id"));
						

						list.add(c);

					}

				});

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
	public Page<CourseViewData> getPage(int pageNo, int pageSize) {

		long totalCount = getCount();
		if (totalCount < 1)
			return new Page<CourseViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseViewData> data = PageQuery(pageNo - 1, pageSize);

		return new Page<CourseViewData>(startIndex, totalCount, pageSize, data);

	}
	
	/**
	 * 
	 */
	public Page<CourseViewData> getAllPage() {

		long totalCount = getCount();
		if (totalCount < 1)
			return new Page<CourseViewData>();


		List<CourseViewData> data = PageAllQuery();

		return new Page<CourseViewData>(0, totalCount, (int)totalCount, data);

	}
	
	

}
