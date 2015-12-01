package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.Attendance;
import com.mathtop.course.domain.AttendanceViewData;
import com.mathtop.course.domain.Course;
import com.mathtop.course.domain.CourseTeachingClass;
import com.mathtop.course.domain.TeacherViewData;
import com.mathtop.course.domain.TeachingClass;
import com.mathtop.course.utility.DateTimeSql;
import com.mathtop.course.utility.GUID;

@Repository
public class AttendanceDao extends BaseDao<Attendance> {

	@Autowired
	CourseTeachingClassDao courseteachingclassDao;

	@Autowired
	TeachingClassDao teachingclassDao;

	@Autowired
	CourseDao courseDao;

	@Autowired
	TeacherViewDataDao teacherviewdataDao;
	
	@Autowired
	AttendanceStudentDao attendanceStudentDao;

	// insert
	private final String INSERT_ATTENDANCE = "INSERT INTO t_attendance(id,t_course_teaching_class_id,t_attendance_type_id,t_teacher_id ,begin_datetime,end_datetime) VALUES(?,?,?,?,?,?)";

	// select
	private final String GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID_AND_ATTENDANCE_TYPE_ID = "SELECT id FROM t_attendance WHERE t_course_teaching_class_id=? and t_attendance_type_id=? order by begin_datetime desc limit ?,?";
	private final String GET_COUNT_BY_COURSE_TEACHING_CLASS_ID = "SELECT count(*) FROM t_attendance WHERE t_course_teaching_class_id=?";
	private final String GET_COUNT_BY_COURSE_TEACHING_CLASS_ID_AND_ATTENDANCE_TYPE_ID = "SELECT count(*) FROM t_attendance WHERE t_course_teaching_class_id=? and t_attendance_type_id=?";
	private final String GET_BY_COURSE_TEACHING_CLASS_ID = "SELECT id,t_attendance_type_id,t_teacher_id ,begin_datetime,end_datetime FROM t_attendance WHERE t_course_teaching_class_id=?";
	private final String GET_BY_TEACHER_ID = "SELECT id,t_attendance_type_id,t_course_teaching_class_id ,begin_datetime,end_datetime FROM t_attendance WHERE t_teacher_id=?";
	private final String GET_BY_ATTENDANCE_ID = "SELECT id,t_teacher_id,t_course_teaching_class_id ,begin_datetime,end_datetime FROM t_attendance WHERE t_attendance_type_id=?";
	private final String GET_BY_ID = "SELECT t_course_teaching_class_id,t_attendance_type_id,t_teacher_id ,begin_datetime,end_datetime FROM t_attendance WHERE id=?";

	// DELETE
	private String DELETE_BY_ID = "DELETE FROM t_attendance WHERE id=?";
	private String DELETE_BY_COURSE_TEACHING_CLASS_ID = "DELETE FROM t_attendance WHERE t_course_teaching_class_id=?";
	private String DELETE_BY_TEACHER_ID = "DELETE FROM t_attendance WHERE t_teacher_id=?";
	private String DELETE_BY_ATTENDANCE_TYPE_ID = "DELETE FROM t_attendance WHERE t_attendance_type_id=?";

	// update
	private String UPDATE_BY_ID = "update t_attendance set t_teacher_id=?,begin_datetime=?,end_datetime=? WHERE id=?";

	public void update(String id, String t_teacher_id, Date begin_datetime, Date end_datetime) {
		if (id == null || t_teacher_id == null || begin_datetime == null || end_datetime == null)
			return;

		Object params[] = new Object[] { t_teacher_id, begin_datetime, end_datetime, id };
		int types[] = new int[] { Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}

	/*
	 * 根据用户ID得到用户
	 */
	public Attendance getByID(String id) {

		Attendance atte = new Attendance();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				atte.setId(id);
				atte.setT_course_teacing_class_id(rs.getString("t_course_teaching_class_id"));
				atte.setT_attendance_type_id(rs.getString("t_attendance_type_id"));
				atte.setT_teacher_id(rs.getString("t_teacher_id"));
				atte.setBegin_datetime(rs.getTimestamp("begin_datetime"));
				atte.setEnd_datetime(rs.getTimestamp("end_datetime"));

			}

		});

		if (atte.getId() == null)
			return null;
		return atte;
	}

	/**
	 * 根据教学班得到通知
	 * */
	public List<Attendance> getByCourseTeachingClassID(String t_course_teaching_class_id) {

		List<Attendance> list = new ArrayList<Attendance>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Attendance atte = new Attendance();
				atte.setId(rs.getString("id"));
				atte.setT_course_teacing_class_id(t_course_teaching_class_id);
				atte.setT_attendance_type_id(rs.getString("t_attendance_type_id"));
				atte.setT_teacher_id(rs.getString("t_teacher_id"));
				atte.setBegin_datetime(rs.getTimestamp("begin_datetime"));
				atte.setEnd_datetime(rs.getTimestamp("end_datetime"));
				list.add(atte);

			}

		});

		return list;
	}
	
	
	/**
	 * 根据教师得到考勤信息
	 * */
	public List<Attendance> getByTeacherID(String t_teacher_id) {

		List<Attendance> list = new ArrayList<Attendance>();

		getJdbcTemplate().query(GET_BY_TEACHER_ID, new Object[] { t_teacher_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Attendance atte = new Attendance();
				atte.setId(rs.getString("id"));
				atte.setT_course_teacing_class_id(rs.getString("t_course_teaching_class_id"));
				atte.setT_attendance_type_id(rs.getString("t_attendance_type_id"));
				atte.setT_teacher_id(t_teacher_id);
				atte.setBegin_datetime(rs.getTimestamp("begin_datetime"));
				atte.setEnd_datetime(rs.getTimestamp("end_datetime"));
				list.add(atte);

			}

		});

		return list;
	}
	
	/**
	 * 根据考勤类型得到考勤信息
	 * */
	public List<Attendance> getByAttendanceTypeId(String t_attendance_type_id) {

		List<Attendance> list = new ArrayList<Attendance>();

		getJdbcTemplate().query(GET_BY_ATTENDANCE_ID, new Object[] { t_attendance_type_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Attendance atte = new Attendance();
				atte.setId(rs.getString("id"));
				atte.setT_course_teacing_class_id(rs.getString("t_course_teaching_class_id"));
				atte.setT_attendance_type_id(t_attendance_type_id);
				atte.setT_teacher_id(rs.getString("t_teacher_id"));
				atte.setBegin_datetime(rs.getTimestamp("begin_datetime"));
				atte.setEnd_datetime(rs.getTimestamp("end_datetime"));
				list.add(atte);

			}

		});

		return list;
	}


	/* 增加用户 */
	public String add(Attendance atte) {
		String id = GUID.getGUID();
		atte.setId(id);
		Object params[] = new Object[] { atte.getId(), atte.getT_course_teacing_class_id(), atte.getT_attendance_type_id(),
				atte.getT_teacher_id(), atte.getBegin_datetime(), atte.getEnd_datetime() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP };
		getJdbcTemplate().update(INSERT_ATTENDANCE, params, types);
		return id;
	}

	public String add(String t_course_teaching_class_id, String t_attendance_type_id, String t_teacher_id, String begin_datetime,
			String end_datetime) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_teaching_class_id, t_attendance_type_id, t_teacher_id,
				DateTimeSql.GetDateTimeNotIncludingSecond(begin_datetime), DateTimeSql.GetDateTimeNotIncludingSecond(end_datetime) };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP };
		getJdbcTemplate().update(INSERT_ATTENDANCE, params, types);
		return id;
	}

	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	public void deleteByCourseTeachingClassId(String t_course_teaching_class_id) {
		Object params[] = new Object[] { t_course_teaching_class_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_COURSE_TEACHING_CLASS_ID, params, types);
	}

	public void deleteByTeacherId(String t_teacher_id) {
		Object params[] = new Object[] { t_teacher_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_TEACHER_ID, params, types);
	}
	
	public void deleteByAttendanceTypeId(String t_attendance_type_id) {
		Object params[] = new Object[] { t_attendance_type_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ATTENDANCE_TYPE_ID, params, types);
	}

	int getCount(String t_course_teaching_class_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_id },
				new int[] { Types.VARCHAR }, Integer.class);
	}
	
	int getCount(String t_course_teaching_class_id,String t_attendance_type_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_TEACHING_CLASS_ID_AND_ATTENDANCE_TYPE_ID, new Object[] { t_course_teaching_class_id ,t_attendance_type_id},
				new int[] { Types.VARCHAR,Types.VARCHAR  }, Integer.class);
	}

	public AttendanceViewData getAttendanceViewDataByAttendanceId(String t_attendance_id) {

		Attendance attendance = getByID(t_attendance_id);

		if (attendance == null)
			return null;

		AttendanceViewData data = new AttendanceViewData();
		data.setAttendance(attendance);

		CourseTeachingClass courseteachingclass = courseteachingclassDao.getCourseTeachingClassById(attendance
				.getT_course_teacing_class_id());
		data.setCourseteachingclass(courseteachingclass);

		TeachingClass teachingclass = teachingclassDao.getByID(courseteachingclass.getT_teaching_class_id());
		data.setTeachingclass(teachingclass);

		Course course = courseDao.getCourseById(courseteachingclass.getT_course_id());
		data.setCourse(course);

		TeacherViewData teacherviewdata = teacherviewdataDao.getTeacherViewDataByTeacherId(attendance.getT_teacher_id());
		data.setTeacherviewdata(teacherviewdata);
		
		data.setHasCheckin(attendanceStudentDao.getCount(t_attendance_id)>0);

		return data;
	}

	private List<AttendanceViewData> PageQuery(String t_course_teaching_class_id,String t_attendance_id, int PageBegin, int PageSize) {

	
		List<AttendanceViewData> list = new ArrayList<AttendanceViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID_AND_ATTENDANCE_TYPE_ID,
				new Object[] { t_course_teaching_class_id,t_attendance_id, PageBegin * PageSize, PageSize }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						AttendanceViewData data = getAttendanceViewDataByAttendanceId(rs.getString("id"));

						list.add(data);
						// System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}

	public Page<AttendanceViewData> getPage(String t_course_teaching_class_id, String t_attendance_id,int pageNo, int pageSize) {
		long totalCount = getCount(t_course_teaching_class_id,t_attendance_id);
		if (totalCount < 1)
			return new Page<AttendanceViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<AttendanceViewData> data = PageQuery(t_course_teaching_class_id, t_attendance_id,pageNo - 1, pageSize);

		return new Page<AttendanceViewData>(startIndex, totalCount, pageSize, data);

	}
}
