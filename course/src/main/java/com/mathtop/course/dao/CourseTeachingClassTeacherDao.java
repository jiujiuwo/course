package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.TeacherViewData;
import com.mathtop.course.domain.CourseTeachingClassTeacher;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassTeacherDao extends BaseDao<CourseTeachingClassTeacher> {

	@Autowired
	TeacherViewDataDao teacherviewdataDao;
	 
	private final String GET_TEACHING_CLASS_TEACHER_BY_ID = "SELECT t_course_teaching_class_id,t_teacher_id,t_teaching_type_id FROM t_teaching_class_teacher WHERE id=?";
	private final String GET_TEACHING_CLASS_TEACHER_BY_TEACHER_ID = "SELECT id,t_course_teaching_class_id,t_teaching_type_id FROM t_teaching_class_teacher WHERE t_teacher_id=?";
	private final String GET_TEACHING_CLASS_TEACHER_BY_TEACHING_CLASS_ID = "SELECT id,t_teacher_id,t_teaching_type_id FROM t_teaching_class_teacher WHERE t_course_teaching_class_id=?";

	private final String INSERT_TEACHING_CLASS_TEACHER = "INSERT INTO t_teaching_class_teacher(id,t_course_teaching_class_id,t_teacher_id,t_teaching_type_id) VALUES(?,?,?,?)";

	private final String IS_TEACHER_EXIST= "select count(*) from t_teaching_class_teacher where t_course_teaching_class_id=? and t_teacher_id=? and t_teaching_type_id=?";
	private final String DELETE_BY_ID = "DELETE FROM t_teaching_class_teacher WHERE id=?";
	private final String DELETE_BY_TEACHER_ID = "DELETE FROM t_teaching_class_teacher WHERE t_teacher_id=?";
	private final String DELETE_BY_TEACHINGTYPE_ID = "DELETE FROM t_teaching_class_teacher WHERE t_teaching_type_id=?";
	private final String DELETE_BY_TEACHING_CLASS_ID = "DELETE FROM t_teaching_class_teacher WHERE t_course_teaching_class_id=?";
	private final String DELETE_BY_COURSE_TEACHING_CLASS_ID_AND_TEACHER_ID_AND_TEACHING_TYPE_ID = "DELETE FROM t_teaching_class_teacher WHERE t_course_teaching_class_id=? and t_teacher_id=? and t_teaching_type_id=?";
	private final String DELETE_BY_COURSE_TEACHING_CLASS_ID_AND_TEACHER_ID = "DELETE FROM t_teaching_class_teacher WHERE t_course_teaching_class_id=? and t_teacher_id=?";
	/* 增加 */
	public String add(String t_course_teaching_class_id, String t_teacher_id,
			String t_teaching_type_id) {
		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_teaching_class_id, t_teacher_id,
				t_teaching_type_id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR };
		getJdbcTemplate().update(INSERT_TEACHING_CLASS_TEACHER, params, types);

		return id;
	}
	
	
	/* 根据id删除 */
	public void deleteById(String id) {
		Object params[]=new Object[]{id};
		int types[]=new int[]{Types.VARCHAR};
		getJdbcTemplate().update(DELETE_BY_ID, params, types);

		
	}
	/* 根据t_course_teaching_class_id删除 */
	public void deleteByCourseTeachingClassId(String t_course_teaching_class_id) {
		Object params[]=new Object[]{t_course_teaching_class_id};
		int types[]=new int[]{Types.VARCHAR};
		getJdbcTemplate().update(DELETE_BY_TEACHING_CLASS_ID, params, types);
	}
	
	/* 根据t_teaching_type_id删除 */
	public void deleteByTeachingTypeId(String t_teaching_type_id) {
		Object params[]=new Object[]{t_teaching_type_id};
		int types[]=new int[]{Types.VARCHAR};
		getJdbcTemplate().update(DELETE_BY_TEACHINGTYPE_ID, params, types);
	}
	
	/* 根据t_teacher_id删除 */
	public void deleteByTeacherId(String t_teacher_id) {
		Object params[]=new Object[]{t_teacher_id};
		int types[]=new int[]{Types.VARCHAR};
		getJdbcTemplate().update(DELETE_BY_TEACHER_ID, params, types);
	}
	
	/**
	 * 根据t_course_teaching_class_id、t_teacher_id 
	 * */
	public void deleteByCourseTeachingClassIdAndTeacherId(String t_course_teaching_class_id, String t_teacher_id
			) {
		Object params[]=new Object[]{t_course_teaching_class_id,t_teacher_id};
		int types[]=new int[]{Types.VARCHAR,Types.VARCHAR};
		getJdbcTemplate().update(DELETE_BY_COURSE_TEACHING_CLASS_ID_AND_TEACHER_ID, params, types);
	}
	
	/**
	 * 根据t_course_teaching_class_id、t_teacher_id、 t_teaching_type_id
	 * */
	public void deleteByCourseTeachingClassIdAndTeacherIdAndTeachingTypeId(String t_course_teaching_class_id, String t_teacher_id,
			String t_teaching_type_id) {
		Object params[]=new Object[]{t_course_teaching_class_id,t_teacher_id,t_teaching_type_id};
		int types[]=new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		getJdbcTemplate().update(DELETE_BY_COURSE_TEACHING_CLASS_ID_AND_TEACHER_ID_AND_TEACHING_TYPE_ID, params, types);
	}

	List<CourseTeachingClassTeacher> getTeachingClassTeacherById(String id) {
		List<CourseTeachingClassTeacher> list = new ArrayList<CourseTeachingClassTeacher>();
		getJdbcTemplate().query(GET_TEACHING_CLASS_TEACHER_BY_ID,
				new Object[] { id }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassTeacher tct = new CourseTeachingClassTeacher();
						tct.setId(id);
						tct.setT_teacher_id(rs.getString("t_teacher_id"));
						tct.setT_course_teaching_class_id(rs
								.getString("t_course_teaching_class_id"));
						tct.setT_teaching_type_id(rs
								.getString("t_teaching_type_id"));

						list.add(tct);

					}

				});

		return list;
	}

	List<CourseTeachingClassTeacher> getTeachingClassTeacherByTeachingClassId(
			String t_course_teaching_class_id) {
		List<CourseTeachingClassTeacher> list = new ArrayList<CourseTeachingClassTeacher>();
		getJdbcTemplate().query(
				GET_TEACHING_CLASS_TEACHER_BY_TEACHING_CLASS_ID,
				new Object[] { t_course_teaching_class_id }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassTeacher tct = new CourseTeachingClassTeacher();
						tct.setId(rs.getString("id"));
						tct.setT_teacher_id(rs.getString("t_teacher_id"));
						tct.setT_course_teaching_class_id(t_course_teaching_class_id);
						tct.setT_teaching_type_id(rs
								.getString("t_teaching_type_id"));

						list.add(tct);

					}

				});

		return list;
	}

	public List<CourseTeachingClassTeacher> getTeachingClassTeacherByTeacherId(
			String t_teacher_id) {
		List<CourseTeachingClassTeacher> list = new ArrayList<CourseTeachingClassTeacher>();
		getJdbcTemplate().query(GET_TEACHING_CLASS_TEACHER_BY_TEACHER_ID,
				new Object[] { t_teacher_id }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassTeacher tct = new CourseTeachingClassTeacher();
						tct.setId(rs.getString("id"));
						tct.setT_teacher_id(t_teacher_id);
						tct.setT_course_teaching_class_id(rs
								.getString("t_course_teaching_class_id"));
						tct.setT_teaching_type_id(rs
								.getString("t_teaching_type_id"));

						list.add(tct);

					}

				});

		return list;
	}
	
	
	/**
	 * 得到课程全体教师视图
	 * */	
	public Page<TeacherViewData> getTeacherPage(String t_course_teaching_class_id) {
		List<CourseTeachingClassTeacher> listteacher= getTeachingClassTeacherByTeachingClassId(t_course_teaching_class_id);
		
		
		List<TeacherViewData> data = new ArrayList<TeacherViewData>();
		for(CourseTeachingClassTeacher t:listteacher){
			TeacherViewData tvd=teacherviewdataDao.getTeacherViewDataById(t.getT_teacher_id());
			data.add(tvd);
		}

		return new Page<TeacherViewData>(0, listteacher.size(), listteacher.size(), data);
	}
	
	
	long IsTeacherExist(String t_course_teaching_class_id, String t_teacher_id, String t_teachingtype_id) {

		return getJdbcTemplate()
				.queryForObject(
						IS_TEACHER_EXIST,
						new Object[] { t_course_teaching_class_id, t_teacher_id,t_teachingtype_id },
						new int[] { Types.VARCHAR, Types.VARCHAR , Types.VARCHAR},
						Long.class);
	}
	
	/* 增加教师 */
	public String addTeacher(String t_course_teaching_class_id, String t_teacher_id, String t_teachingtype_id) {
		
		if(IsTeacherExist(t_course_teaching_class_id,t_teacher_id,t_teachingtype_id)>0)
			return null;
		
		deleteByCourseTeachingClassIdAndTeacherIdAndTeachingTypeId(t_course_teaching_class_id,t_teacher_id,t_teachingtype_id);

		return add(t_course_teaching_class_id,t_teacher_id,t_teachingtype_id);
	}
}



