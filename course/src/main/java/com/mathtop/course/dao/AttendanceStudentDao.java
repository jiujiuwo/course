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
import com.mathtop.course.domain.AttendanceMode;
import com.mathtop.course.domain.AttendanceState;
import com.mathtop.course.domain.AttendanceStateModeViewData;
import com.mathtop.course.domain.AttendanceStudent;
import com.mathtop.course.domain.AttendanceStudentViewData;
import com.mathtop.course.domain.StudentViewData;
import com.mathtop.course.utility.GUID;

@Repository
public class AttendanceStudentDao extends BaseDao<AttendanceStudent> {

	@Autowired
	AttendanceDao attendanceDao;

	@Autowired
	AttendanceStateDao attendancestateDao;

	@Autowired
	AttendanceTypeDao attendancetypeDao;
	
	@Autowired
	AttendanceModeDao attendancemodeDao;

	@Autowired
	StudentViewDataDao studentviewdataDao;

	private final String INSERT_ATTENDANCE_STUDENT = "INSERT INTO t_attendance_student(id,t_attendance_id,t_student_id ,t_attendance_state_id,t_attendance_mode_id,checking_in_datetime) VALUES(?,?,?,?,?,?)";

	private final String GET_VIEWDATA_BY_ATTENDANCE_ID = "SELECT id FROM t_attendance_student WHERE t_attendance_id=?   limit ?,?";

	private final String GET_COUNT_BY_ATTENDANCE_ID = "SELECT count(*) FROM t_attendance_student WHERE t_attendance_id=?";
	private final String GET_COUNT_BY_ATTENDANCE_ID_ATTENDANCE_STATE_ID="select count(*) from t_attendance_student where t_attendance_id=? and t_attendance_state_id=?";
	private final String GET_BY_ATTENDANCE_ID = "SELECT id,t_student_id ,t_attendance_state_id,t_attendance_mode_id,checking_in_datetime FROM t_attendance_student WHERE t_attendance_id=?";
	private final String GET_BY_ID = "SELECT t_attendance_id,t_student_id ,t_attendance_state_id,t_attendance_mode_id,checking_in_datetime FROM t_attendance_student WHERE id=?";
	
	private final String GET_COUNT_BY_COURSE_TEACHING_CLASS_ID_STUDENT_ID = "SELECT count(*) from t_attendance_student " + " where t_attendance_id in "
			+ " (select id from t_attendance where t_course_teaching_class_id=? and t_attendance_type_id=? ) and t_student_id=?";
	
	private final String GET_BY_COURSE_TEACHING_CLASS_ID_STUDENT_ID = "SELECT id from t_attendance_student " + " where t_attendance_id in "
			+ " (select id from t_attendance where t_course_teaching_class_id=? and t_attendance_type_id=? order by begin_datetime ) and t_student_id=?  order by checking_in_datetime DESC limit ?,?";

	private String DELETE_BY_ID = "DELETE FROM t_attendance_student WHERE id=?";
	private String DELETE_BY_ATTENDANCE_ID = "DELETE FROM t_attendance_student WHERE t_attendance_id=?";
	private String DELETE_BY_STUDENT_ID = "DELETE FROM t_attendance_student WHERE t_student_id=?";
	private String DELETE_BY_ATTENDANCE_STATE_ID = "DELETE FROM t_attendance_student WHERE a_ttendance_state_id=?";
	private String DELETE_BY_ATTENDANCE_MODE_ID = "DELETE FROM t_attendance_student WHERE t_attendance_mode_id=?";
	private String DELETE_BY_ATTENDANCE_ID_STUDENT_ID = "DELETE FROM t_attendance_student WHERE t_attendance_id=? and t_student_id=?";
	private String UPDATE_BY_ID = "update t_attendance_student set t_student_id=?,a_ttendance_state_id=?,t_attendance_mode_id=?,checking_in_datetime=? WHERE id=?";

	public void update(String id, String t_student_id, String a_ttendance_state_id, String t_attendance_mode_id, Date checking_in_datetime) {
		if (id == null || t_student_id == null || a_ttendance_state_id == null ||t_attendance_mode_id==null)
			return;

		Object params[] = new Object[] { t_student_id, a_ttendance_state_id,  t_attendance_mode_id,checking_in_datetime, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,  Types.TIMESTAMP, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}

	/*
	 * 根据用户ID得到用户
	 */
	public AttendanceStudent getByID(String id) {

		AttendanceStudent as = new AttendanceStudent();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				as.setId(id);
				as.setAttendanceId(rs.getString("t_attendance_id"));
				as.setAttendanceStateId(rs.getString("t_attendance_state_id"));				
				as.setAttendanceModeId(rs.getString("t_attendance_mode_id"));
				as.setStudentId(rs.getString("t_student_id"));
				as.setCheckingInDatetime(rs.getTimestamp("checking_in_datetime"));

			}

		});

		if (as.getId() == null)
			return null;
		return as;
	}

	/**
	 * 根据教学班得到通知
	 * */
	public List<AttendanceStudent> getByCourseAttendanceID(String t_attendance_id) {

		List<AttendanceStudent> list = new ArrayList<AttendanceStudent>();

		getJdbcTemplate().query(GET_BY_ATTENDANCE_ID, new Object[] { t_attendance_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				AttendanceStudent as = new AttendanceStudent();

				as.setId(rs.getString("id"));
				as.setAttendanceId(t_attendance_id);
				as.setAttendanceStateId(rs.getString("t_attendance_state_id"));				
				as.setAttendanceModeId(rs.getString("t_attendance_mode_id"));
				as.setStudentId(rs.getString("t_student_id"));
				as.setCheckingInDatetime(rs.getTimestamp("checking_in_datetime"));
				list.add(as);

			}

		});

		return list;
	}

	/* 增加用户 */
	public String add(AttendanceStudent as) {
		String id = GUID.getGUID();
		as.setId(id);
		Object params[] = new Object[] { as.getId(), as.getAttendanceId(), as.getStudentId(), as.getAttendanceStateId(),
				 as.getAttendanceModeId(),as.getCheckingInDatetime() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.TIMESTAMP };
		getJdbcTemplate().update(INSERT_ATTENDANCE_STUDENT, params, types);
		return id;
	}

	public String add(String t_attendance_id, String t_student_id, String a_ttendance_state_id, String a_ttendance_mode_id,Date checking_in_datetime) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_attendance_id, t_student_id, a_ttendance_state_id, a_ttendance_mode_id, checking_in_datetime };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.TIMESTAMP };
		getJdbcTemplate().update(INSERT_ATTENDANCE_STUDENT, params, types);
		return id;
	}

	

	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	public void deleteByAttendanceId(String t_attendance_id) {
		Object params[] = new Object[] { t_attendance_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ATTENDANCE_ID, params, types);
	}

	public void deleteByStudentId(String t_student_id) {
		Object params[] = new Object[] { t_student_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_STUDENT_ID, params, types);
	}

	public void deleteByAttendanceIdStudentId(String t_attendance_id, String t_student_id) {
		Object params[] = new Object[] { t_attendance_id, t_student_id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ATTENDANCE_ID_STUDENT_ID, params, types);
	}
	
	/**
	 * 根据考勤状况删除
	 * */
	public void deleteByAttendanceStateId(String a_ttendance_state_id) {
		Object params[] = new Object[] { a_ttendance_state_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ATTENDANCE_STATE_ID, params, types);
	}
	
	/**
	 * 根据考勤方式删除
	 * */
	public void deleteByAttendanceModeId(String t_attendance_mode_id) {
		Object params[] = new Object[] { t_attendance_mode_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ATTENDANCE_MODE_ID, params, types);
	}

	public long getCount(String t_attendance_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_ATTENDANCE_ID, new Object[] { t_attendance_id }, new int[] { Types.VARCHAR }, Long.class);
	}

	private List<AttendanceStudentViewData> PageQuery(String t_attendance_id, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<AttendanceStudentViewData> list = new ArrayList<AttendanceStudentViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_ATTENDANCE_ID, new Object[] { t_attendance_id, PageBegin * PageSize, PageSize }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				AttendanceStudentViewData data = new AttendanceStudentViewData();

				AttendanceStudent as = getByID(rs.getString("id"));
				data.setAttendancestudent(as);

				Attendance attendance = attendanceDao.getByID(as.getAttendanceId());
				data.setAttendance(attendance);

				AttendanceState state = attendancestateDao.getByID(as.getAttendanceStateId());
				data.setState(state);

				
				
				AttendanceMode mode = attendancemodeDao.getByID(as.getAttendanceModeId());
				data.setMode(mode);

				StudentViewData studentviewdata = studentviewdataDao.getStudentViewDataById(as.getStudentId());
				data.setStudentviewdata(studentviewdata);

				list.add(data);
				// System.out.println(rs.getString("t_user_id"));
			}

		});
		return list;
	}

	public Page<AttendanceStudentViewData> getPage(String t_attendance_id, int pageNo, int pageSize) {
		long totalCount = getCount(t_attendance_id);
		if (totalCount < 1)
			return new Page<AttendanceStudentViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<AttendanceStudentViewData> data = PageQuery(t_attendance_id, pageNo - 1, pageSize);

		return new Page<AttendanceStudentViewData>(startIndex, totalCount, pageSize, data);

	}

	public Page<AttendanceStudentViewData> getPage(String t_attendance_id) {
		long totalCount = getCount(t_attendance_id);
		if (totalCount < 1)
			return new Page<AttendanceStudentViewData>();

		// 实际查询返回分页对象
		int startIndex = 0;

		List<AttendanceStudentViewData> data = PageQuery(t_attendance_id, 0, (int) totalCount);

		return new Page<AttendanceStudentViewData>(startIndex, totalCount, (int) totalCount, data);

	}
	
	/**
	 * 获得某次考勤的指定状态的人数 
	 * */
	public int getCountbyAttendanceIDAndStateId(String t_attendance_id,String t_attendance_state_id){
		return getJdbcTemplate().queryForObject(GET_COUNT_BY_ATTENDANCE_ID_ATTENDANCE_STATE_ID, new Object[] { t_attendance_id, t_attendance_state_id },
				new int[] { Types.VARCHAR, Types.VARCHAR }, Integer.class);
	}

	long getCount(String t_course_teaching_class_id, String t_attendance_type_id,String t_student_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_TEACHING_CLASS_ID_STUDENT_ID, new Object[] { t_course_teaching_class_id, t_attendance_type_id,t_student_id },
				new int[] { Types.VARCHAR, Types.VARCHAR ,Types.VARCHAR}, Long.class);
	}

	
	private List<AttendanceStateModeViewData> PageQuery(String t_course_teaching_class_id, String t_attendance_type_id,String t_student_id, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<AttendanceStateModeViewData> list = new ArrayList<AttendanceStateModeViewData>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID_STUDENT_ID, new Object[] { t_course_teaching_class_id,t_attendance_type_id,t_student_id, PageBegin * PageSize, PageSize }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				AttendanceStateModeViewData data = new AttendanceStateModeViewData();

				AttendanceStudent as = getByID(rs.getString("id"));
				data.setAttendancestudent(as);

				Attendance attendance = attendanceDao.getByID(as.getAttendanceId());
				data.setAttendance(attendance);

				AttendanceState state = attendancestateDao.getByID(as.getAttendanceStateId());
				data.setState(state);

				
				
				AttendanceMode mode = attendancemodeDao.getByID(as.getAttendanceModeId());
				data.setMode(mode);

				
				list.add(data);
				// System.out.println(rs.getString("t_user_id"));
			}

		});
		return list;
	}
	
	/**
	 * 得到指定学生、指定课程的考勤情况
	 * */
	public Page<AttendanceStateModeViewData> getPage(String t_course_teaching_class_id, String t_attendance_type_id,String t_student_id, int pageNo, int pageSize) {
		long totalCount = getCount(t_course_teaching_class_id,t_attendance_type_id,t_student_id);
		if (totalCount < 1)
			return new Page<AttendanceStateModeViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<AttendanceStateModeViewData> data = PageQuery(t_course_teaching_class_id,t_attendance_type_id,t_student_id, pageNo - 1, pageSize);

		return new Page<AttendanceStateModeViewData>(startIndex, totalCount, pageSize, data);

	}
	
	
}
