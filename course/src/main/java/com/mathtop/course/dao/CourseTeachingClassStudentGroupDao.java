package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.CourseTeachingClassStudentGroup;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassStudentGroupDao extends BaseDao<CourseTeachingClassStudentGroup> {

	private final String INSERT = "INSERT INTO "
			+ "t_course_teaching_class_student_group(id,t_group_id,t_course_teaching_class_id,show_index) VALUES(?,?,?,?)";
	
	private final String GET_GROUPS_BY_COURSE_TEACHING_CLASS_ID = "SELECT id, t_group_id, show_index "
			+ "FROM t_course_teaching_class_student_group "
			+ "WHERE t_course_teaching_class_id=? order by show_index ASC";
	
	private final String IS_GROUP_EXIST = "select count(*) "
			+ "from t_course_teaching_class_student_group "
			+ "where t_course_teaching_class_id=? and t_group_id=?";

	private final String GET_GROUP_COUNT_BY_TEACHING_CLASS_ID = "SELECT count(*) "
			+ "FROM t_course_teaching_class_student_group "
			+ "WHERE t_course_teaching_class_id=?";

	
	private final String GET_BY_ID = "SELECT t_group_id,t_course_teaching_class_id,show_index "
			+ " FROM t_course_teaching_class_student_group " 
			+ " WHERE id=?";
	
	private final String GET_BY_COURSE_TEACHING_CLASS_ID_GROUP_ID = "SELECT id ,show_index "
			+ " FROM t_course_teaching_class_student_group " + " WHERE t_course_teaching_class_id=? and t_group_id=?";
	
	private final String GET_BY_COURSE_TEACHING_CLASS_ID_SHOW_INDEX = "SELECT id,t_group_id "
			+ " FROM t_course_teaching_class_student_group " + " WHERE t_course_teaching_class_id=?  and show_index=?";

	private final String GET_MAX_SHOW_INDEX_BY_COURSE_TEACHING_CLASS_ID = "SELECT max(show_index) "
			+ "FROM t_course_teaching_class_student_group "
			+ "WHERE t_course_teaching_class_id=?";
	
	private final String GET_MIN_SHOW_INDEX_BY_COURSE_TEACHING_CLASS_ID = "SELECT min(show_index) "
			+ "FROM t_course_teaching_class_student_group "
			+ "WHERE t_course_teaching_class_id=?";
	
	private final String GET_MAX_SHOW_INDEX_BY_COURSE_TEACHING_CLASS_ID_STUDENT_ID = "SELECT max(show_index) "
			+ "FROM t_course_teaching_class_student_group "
			+ "WHERE show_index<"
			+ "(select show_index FROM t_course_teaching_class_student_group where t_course_teaching_class_id=? and t_group_id=?)";

	private final String GET_MIN_SHOW_INDEX_BY_COURSE_TEACHING_CLASS_ID_STUDENT_ID = "SELECT min(show_index) "
			+ "FROM t_course_teaching_class_student_group "
			+ "WHERE show_index>(select show_index FROM t_course_teaching_class_student_group where t_course_teaching_class_id=? and t_group_id=?)";

	private final String GET_BY_COURSE_TEACHING_CLASS_ID_USER_ID = "SELECT id,t_group_id,show_index "
			+ "FROM t_course_teaching_class_student_group "
			+ "WHERE t_course_teaching_class_id=? and t_group_id in (select t_group_id FROM t_user_group where t_user_id=?)";

	
	//delete
	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_class_student_group WHERE id=?";
	
	private String DELETE_BY_TEACHING_CLASS_ID = "DELETE FROM t_course_teaching_class_student_group "
			+ "WHERE t_course_teaching_class_id=?";
	
	private String DELETE_BY_GROUP_ID = "DELETE FROM t_course_teaching_class_student_group WHERE t_group_id=?";
	
	private String DELETE_BY_TEACHING_CLASS_ID_AND_GROUP_ID = "DELETE FROM t_course_teaching_class_student_group "
			+ "WHERE t_course_teaching_class_id=? and t_group_id=?";

	
	//update
	private final String UPDATE = "update t_course_teaching_class_student_group"
			+ " set t_course_teaching_class_id=?,t_group_id=?,show_index=? where id=?";

	private final String UPDATE_SHOW_INDEX_BY_ID = "update t_course_teaching_class_student_group"
			+ " set show_index=? where id=?";

	private final String UPDATE_SHOW_INDEX_BY_COURSE_TEACHING_ID_AND_GROUP_ID = "update t_course_teaching_class_student"
			+ " set show_index=? where t_course_teaching_class_student_group=?,t_group_id=?";

	/**
	 * 修改
	 */
	public String update(String id, String t_course_teaching_class_id, String t_group_id, int show_index) {

		Object params[] = new Object[] { t_course_teaching_class_id, t_group_id, show_index, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE, params, types);

		return id;
	}

	/**
	 * 修改show_index
	 */
	public void update(String id, int show_index) {

		Object params[] = new Object[] { show_index, id };
		int types[] = new int[] { Types.INTEGER, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_SHOW_INDEX_BY_ID, params, types);

	}

	/**
	 * 修改show_index
	 */
	public void update(String t_course_teaching_class_id, String t_group_id, int show_index) {

		Object params[] = new Object[] { show_index, t_course_teaching_class_id, t_group_id };
		int types[] = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_SHOW_INDEX_BY_COURSE_TEACHING_ID_AND_GROUP_ID, params, types);

	}

	/**
	 * 根据id删除
	 */
	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	/**
	 * 根据课程删除所有的学生
	 */
	public void deleteByCourseTeachingClassId(String t_course_teaching_class_id) {
		Object params[] = new Object[] { t_course_teaching_class_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_TEACHING_CLASS_ID, params, types);
	}

	
	/**
	 * 根据组删除
	 */
	public void deleteByGroupId(String t_group_id) {
		Object params[] = new Object[] { t_group_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_GROUP_ID, params, types);
	}
	
	/**
	 * 根据课程和组id删除
	 */
	public void deleteByCourseTeachingClassIdAndGroupId(String t_course_teaching_class_id,String t_group_id) {
		Object params[] = new Object[] { t_course_teaching_class_id,t_group_id };
		int types[] = new int[] { Types.VARCHAR , Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_TEACHING_CLASS_ID_AND_GROUP_ID, params, types);
	}



	/**
	 * 增加
	 */
	public String add(String t_course_teaching_class_id, String t_group_id, int show_index) {

		

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_group_id,t_course_teaching_class_id,  show_index };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
		getJdbcTemplate().update(INSERT, params, types);

		return id;
	}
	
	/**
	 * 学生是否在指定教学班级中存在
	 */
	public boolean IsGroupExist(String t_course_teaching_class_id, String t_student_id) {

		return getJdbcTemplate().queryForObject(IS_GROUP_EXIST,
				new Object[] { t_course_teaching_class_id, t_student_id }, new int[] { Types.VARCHAR, Types.VARCHAR },
				Long.class) > 0;
	}
	
	/**
	 * 得到教学班人数
	 */
	public int getGroupCountByCourseTeachingClassId(String t_course_teaching_class_id) {

		return getJdbcTemplate().queryForObject(GET_GROUP_COUNT_BY_TEACHING_CLASS_ID,
				new Object[] { t_course_teaching_class_id }, new int[] { Types.VARCHAR }, Integer.class);
	}

	/**
	 * */
	public int getShowIndexMaxByCourseTeachingClassId(String t_course_teaching_class_id) {

		Integer i = getJdbcTemplate().queryForObject(GET_MAX_SHOW_INDEX_BY_COURSE_TEACHING_CLASS_ID,
				new Object[] { t_course_teaching_class_id }, new int[] { Types.VARCHAR }, Integer.class);
		if (i == null)
			return 0;
		return i;

	}

	/**
	 * 得到比指定的学生小一个次序
	 */
	public int getShowIndexMaxLessthanByCourseTeachingClassId(String t_course_teaching_class_id, String t_group_id) {

		Integer i = getJdbcTemplate().queryForObject(GET_MAX_SHOW_INDEX_BY_COURSE_TEACHING_CLASS_ID_STUDENT_ID,
				new Object[] { t_course_teaching_class_id, t_group_id }, new int[] { Types.VARCHAR, Types.VARCHAR },
				Integer.class);
		if (i == null)
			return 0;
		return i;

	}
	
	/**
	 * 得到比指定的学生大一个次序
	 */
	public int getShowIndexMinMorethanByCourseTeachingClassId(String t_course_teaching_class_id, String t_group_id) {

		Integer i = getJdbcTemplate().queryForObject(GET_MIN_SHOW_INDEX_BY_COURSE_TEACHING_CLASS_ID_STUDENT_ID,
				new Object[] { t_course_teaching_class_id, t_group_id }, new int[] { Types.VARCHAR, Types.VARCHAR },
				Integer.class);
		if (i == null)
			return 0;
		return i;

	}
	
	

	/**
	 * */
	public int getShowIndexMinByCourseTeachingClassId(String t_course_teaching_class_id) {

		Integer i = getJdbcTemplate().queryForObject(GET_MIN_SHOW_INDEX_BY_COURSE_TEACHING_CLASS_ID,
				new Object[] { t_course_teaching_class_id }, new int[] { Types.VARCHAR }, Integer.class);
		if (i == null)
			return 0;
		return i;

	}

	public CourseTeachingClassStudentGroup getById(
			String id) {
		CourseTeachingClassStudentGroup stu = new CourseTeachingClassStudentGroup();
		getJdbcTemplate().query(GET_BY_ID,
				new Object[] {id}, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {

						stu.setId(id);
						stu.setGroupId(rs.getString("t_group_id"));
						stu.setCourseTeachingClassId(rs.getString("t_course_teaching_class_id"));
						stu.setShowIndex(rs.getInt("show_index"));

					}

				});
		if (stu.getId() == null)
			return null;
		return stu;
	}
	
	
	
	

	public CourseTeachingClassStudentGroup getByTeachingClassIdAndGroupId(
			String t_course_teaching_class_id, String t_group_id) {
		CourseTeachingClassStudentGroup stu = new CourseTeachingClassStudentGroup();
		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID_GROUP_ID,
				new Object[] { t_course_teaching_class_id, t_group_id }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {

						stu.setId(rs.getString("id"));
						stu.setGroupId(t_group_id);
						stu.setCourseTeachingClassId(t_course_teaching_class_id);
						stu.setShowIndex(rs.getInt("show_index"));

					}

				});
		if (stu.getId() == null)
			return null;
		return stu;
	}
	
	public CourseTeachingClassStudentGroup getByTeachingClassIdAndUserId(
			String t_course_teaching_class_id, String t_user_id) {
		CourseTeachingClassStudentGroup stu = new CourseTeachingClassStudentGroup();
		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID_USER_ID,
				new Object[] { t_course_teaching_class_id, t_user_id }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {

						stu.setId(rs.getString("id"));
						stu.setGroupId(rs.getString("t_group_id"));
						stu.setCourseTeachingClassId(t_course_teaching_class_id);
						stu.setShowIndex(rs.getInt("show_index"));

					}

				});
		if (stu.getId() == null)
			return null;
		return stu;
	}
	
	public CourseTeachingClassStudentGroup getByTeachingClassIdAndShowIndex(
			String t_course_teaching_class_id, int show_index) {
		CourseTeachingClassStudentGroup stu = new CourseTeachingClassStudentGroup();
		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID_SHOW_INDEX,
				new Object[] { t_course_teaching_class_id, show_index }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {

						stu.setId(rs.getString("id"));
						stu.setGroupId(rs.getString("t_group_id"));
						stu.setCourseTeachingClassId(t_course_teaching_class_id);
						stu.setShowIndex(show_index);

					}

				});
		if (stu.getId() == null)
			return null;
		return stu;
	}
	
	public List<CourseTeachingClassStudentGroup> getByCourseTeachingClassId(
			String t_course_teaching_class_id) {
		List<CourseTeachingClassStudentGroup> list=new ArrayList<>();
		
		
		getJdbcTemplate().query(GET_GROUPS_BY_COURSE_TEACHING_CLASS_ID,
				new Object[] { t_course_teaching_class_id, }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassStudentGroup stu = new CourseTeachingClassStudentGroup();

						stu.setId(rs.getString("id"));
						stu.setGroupId(rs.getString("t_group_id"));
						stu.setCourseTeachingClassId(t_course_teaching_class_id);
						stu.setShowIndex(rs.getInt("show_index"));

						list.add(stu);
					}

				});
		
		return list;
	}
	

}
