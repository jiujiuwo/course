package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.CourseTeachingClass;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassDao extends BaseDao<CourseTeachingClass> {
	
	private final String GET_COURSE_TEACHING_CLASS_BY_ID = "SELECT t_course_id,t_teaching_class_id,teaching_year_begin,teaching_year_end,teaching_term FROM t_course_teaching_class WHERE id=?";
	private final String GET_COURSE_TEACHING_CLASS_BY_COURSE_ID = "SELECT id,t_teaching_class_id,teaching_year_begin,teaching_year_end,teaching_term FROM t_course_teaching_class WHERE t_course_id=?";
	private final String GET_COURSE_TEACHING_CLASS_BY_TEACHINGCLASS_ID = "SELECT id,t_course_id,teaching_year_begin,teaching_year_end,teaching_term FROM t_course_teaching_class WHERE  t_teaching_class_id=?";
	private final String GET_COURSE_TEACHING_CLASS_BY_COURSE_ID_TEACHINGCLASS_ID = "SELECT id,teaching_year_begin,teaching_year_end,teaching_term FROM t_course_teaching_class WHERE t_course_id=? and t_teaching_class_id=?";
	private final String GET_COURSE_TEACHING_CLASS_BY_YEAR = "SELECT id,t_course_id,t_teaching_class_id FROM t_course_teaching_class WHERE teaching_year_begin =? and teaching_term=?";
	
	
	private final String INSERT_COURSE_TEACHING_CLASS = "INSERT INTO t_course_teaching_class(id,t_course_id,t_teaching_class_id,teaching_year_begin,teaching_year_end,teaching_term) VALUES(?,?,?,?,?,?)";

	/* 增加 */
	public String add(String t_course_id, String t_teaching_class_id,
			int teaching_year_begin, int teaching_year_end, int teaching_term) {
		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_id, t_teaching_class_id,
				teaching_year_begin, teaching_year_end, teaching_term };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.INTEGER, Types.INTEGER, Types.INTEGER };
		getJdbcTemplate().update(INSERT_COURSE_TEACHING_CLASS, params, types);

		return id;
	}

	//根据id
	public CourseTeachingClass getCourseTeachingClassById(String id) {
		
		CourseTeachingClass ctc=new CourseTeachingClass();
		getJdbcTemplate().query(GET_COURSE_TEACHING_CLASS_BY_ID,
				new Object[] { id }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						
						ctc.setId(id);
						ctc.setT_course_id(rs.getString("t_course_id"));
						ctc.setT_teaching_class_id(rs.getString("t_teaching_class_id"));
						ctc.setTeaching_year_begin(Integer.parseInt(rs.getString("teaching_year_begin")));
						ctc.setTeaching_year_end(Integer.parseInt(rs.getString("teaching_year_end")));
						ctc.setTeaching_term(Integer.parseInt(rs.getString("teaching_term")));
									

					}

				});
		
		if(ctc.getId()!=null)
			return ctc;
		return null;
	}

	//根据课程id
	public List<CourseTeachingClass> getCourseTeachingClassByCourseId(String courseId) {
		List<CourseTeachingClass> list=new ArrayList<CourseTeachingClass>();
		getJdbcTemplate().query(GET_COURSE_TEACHING_CLASS_BY_COURSE_ID,
				new Object[] { courseId }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClass ctc=new CourseTeachingClass();
						ctc.setId(rs.getString("id"));
						ctc.setT_course_id(courseId);
						ctc.setT_teaching_class_id(rs.getString("t_teaching_class_id"));
						ctc.setTeaching_year_begin(Integer.parseInt(rs.getString("teaching_year_begin")));
						ctc.setTeaching_year_end(Integer.parseInt(rs.getString("teaching_year_end")));
						ctc.setTeaching_term(Integer.parseInt(rs.getString("teaching_term")));
						list.add(ctc);				

					}

				});
		
		return list;
	}

	//根据教学班id
	public List<CourseTeachingClass> getCourseTeachingClassByTeachingClassId(
			String teachingclassId) {
		List<CourseTeachingClass> list=new ArrayList<CourseTeachingClass>();
		getJdbcTemplate().query(GET_COURSE_TEACHING_CLASS_BY_TEACHINGCLASS_ID,
				new Object[] { teachingclassId }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClass ctc=new CourseTeachingClass();
						ctc.setId(rs.getString("id"));
						ctc.setT_course_id(rs.getString("t_course_id"));
						ctc.setT_teaching_class_id(teachingclassId);
						ctc.setTeaching_year_begin(Integer.parseInt(rs.getString("teaching_year_begin")));
						ctc.setTeaching_year_end(Integer.parseInt(rs.getString("teaching_year_end")));
						ctc.setTeaching_term(Integer.parseInt(rs.getString("teaching_term")));
						list.add(ctc);				

					}

				});
		
		return list;
	}

	//根据课程id和教学班id
	public List<CourseTeachingClass> getCourseTeachingClassByCourseIdTeachingClassId(
			String courseId, String teachingclassId) {
		List<CourseTeachingClass> list=new ArrayList<CourseTeachingClass>();
		getJdbcTemplate().query(GET_COURSE_TEACHING_CLASS_BY_COURSE_ID_TEACHINGCLASS_ID,
				new Object[] { courseId ,teachingclassId}, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClass ctc=new CourseTeachingClass();
						ctc.setId(rs.getString("id"));
						ctc.setT_course_id(courseId);
						ctc.setT_teaching_class_id(teachingclassId);
						ctc.setTeaching_year_begin(Integer.parseInt(rs.getString("teaching_year_begin")));
						ctc.setTeaching_year_end(Integer.parseInt(rs.getString("teaching_year_end")));
						ctc.setTeaching_term(Integer.parseInt(rs.getString("teaching_term")));
						list.add(ctc);				

					}

				});
		
		return list;
	}
	
	//根据学年学期
	public List<CourseTeachingClass> getCourseTeachingClassByTeachingYear(int yearbeing,int term) {
		List<CourseTeachingClass> list=new ArrayList<CourseTeachingClass>();
		getJdbcTemplate().query(GET_COURSE_TEACHING_CLASS_BY_YEAR,
				new Object[] { yearbeing,term }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClass ctc=new CourseTeachingClass();
						ctc.setId(rs.getString("id"));
						ctc.setT_course_id(rs.getString("t_course_id"));
						ctc.setT_teaching_class_id(rs.getString("t_teaching_class_id"));
						ctc.setTeaching_year_begin(yearbeing);
						ctc.setTeaching_year_end(yearbeing+1);
						ctc.setTeaching_term(term);
						list.add(ctc);				

					}

				});
		
		return list;
	}
	
	
}
