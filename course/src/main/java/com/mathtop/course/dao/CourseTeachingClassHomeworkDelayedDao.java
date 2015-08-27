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

import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfo;
import com.mathtop.course.domain.CourseTeachingClassHomeworkDelayed;
import com.mathtop.course.domain.CourseTeachingClassHomeworkDelayedViewData;
import com.mathtop.course.domain.TeacherViewData;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassHomeworkDelayedDao extends BaseDao<CourseTeachingClassHomeworkDelayed> {

	@Autowired
	CourseTeachingClassDao courseteachingclassDao;

	@Autowired
	CourseDao courseDao;

	@Autowired
	TeacherViewDataDao teacherviewdataDao;

	@Autowired
	CourseTeachingClassHomeworkBaseinfoDao homeworkbaseinfoDao;

	// insert
	private final String INSERT_PLAN = "INSERT INTO t_course_teaching_class_homework_delayed( id,t_course_teaching_class_homework_baseinfo_id, t_teacher_id,  pubdate, enddate) VALUES(?,?,?,?,?)";

	// select
	private final String GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID = "SELECT id FROM t_course_teaching_class_homework_delayed WHERE t_course_teaching_class_homework_baseinfo_id=?   limit ?,?";
	private final String GET_COUNT_BY_COURSE_TEACHING_CLASS_ID = "SELECT count(*) FROM t_course_teaching_class_homework_delayed WHERE t_course_teaching_class_homework_baseinfo_id=?";
	private final String GET_BY_COURSE_TEACHING_CLASS_HOMEWORK_BASEINFO_ID = "SELECT id, t_teacher_id pubdate, enddate FROM t_course_teaching_class_homework_delayed WHERE t_course_teaching_class_homework_baseinfo_id=?";
	private final String GET_BY_ID = "SELECT t_course_teaching_class_homework_baseinfo_id, t_teacher_id,  pubdate, enddate FROM t_course_teaching_class_homework_delayed WHERE id=?";

	// DELETE
	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_class_homework_delayed WHERE id=?";
	private String DELETE_BY_COURSE_TEACHING_CLASS_HOMEWORK_BASEINFO_ID = "DELETE FROM t_course_teaching_class_homework_delayed WHERE t_course_teaching_class_homework_baseinfo_id=?";
	private String DELETE_BY_TEACHER_ID = "DELETE FROM t_course_teaching_class_homework_delayed WHERE t_teacher_id=?";

	// update
	private String UPDATE_BY_ID = "update t_course_teaching_class_homework_delayed set t_course_teaching_class_homework_baseinfo_id=?, t_teacher_id=?,  pubdate=?, enddate=? WHERE id=?";

	public void update(String id, String t_course_teaching_class_homework_baseinfo_id, String t_teacher_id, Date pubdate,Date enddateh) {
		if (id == null || t_teacher_id == null || t_course_teaching_class_homework_baseinfo_id == null )
			return;

		Object params[] = new Object[] {  t_course_teaching_class_homework_baseinfo_id,  t_teacher_id,   pubdate,
				id };
		int types[] = new int[] {Types.VARCHAR, Types.VARCHAR,  Types.TIMESTAMP, Types.TIMESTAMP, Types.VARCHAR
				 };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}

	/*
	 * 根据用户ID得到用户
	 */
	public CourseTeachingClassHomeworkDelayed getByID(String id) {

		CourseTeachingClassHomeworkDelayed expriment = new CourseTeachingClassHomeworkDelayed();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				expriment.setId(id);
				expriment.setT_course_teaching_class_homework_baseinfo_id(rs.getString("t_course_teaching_class_homework_baseinfo_id"));
				expriment.setT_teacher_id(rs.getString("t_teacher_id"));
				
				expriment.setEnddate(rs.getTimestamp("enddate"));
				expriment.setPubdate(rs.getTimestamp("pubdate"));
				

			}

		});

		if (expriment.getId() == null)
			return null;
		return expriment;
	}

	/**
	 * 根据教学班得到通知
	 * */
	public List<CourseTeachingClassHomeworkDelayed> getByCourseTeachingClassHomeworkBaseInfoID(String t_course_teaching_class_homework_baseinfo_id) {

		List<CourseTeachingClassHomeworkDelayed> list = new ArrayList<CourseTeachingClassHomeworkDelayed>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_HOMEWORK_BASEINFO_ID, new Object[] { t_course_teaching_class_homework_baseinfo_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseTeachingClassHomeworkDelayed expriment = new CourseTeachingClassHomeworkDelayed();
				expriment.setId(rs.getString("id"));
				expriment.setT_course_teaching_class_homework_baseinfo_id(t_course_teaching_class_homework_baseinfo_id);
				expriment.setT_teacher_id(rs.getString("t_teacher_id"));
				
				expriment.setEnddate(rs.getTimestamp("enddate"));
				expriment.setPubdate(rs.getTimestamp("pubdate"));
				
				list.add(expriment);

			}

		});

		return list;
	}

	/* 增加用户 */
	public String add(CourseTeachingClassHomeworkDelayed expriment) {
		String id = GUID.getGUID();
		expriment.setId(id);
		Object params[] = new Object[] { expriment.getId(), expriment.getT_course_teaching_class_homework_baseinfo_id(), expriment.getT_teacher_id(),
				 expriment.getPubdate(), expriment.getEnddate()};
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,  Types.TIMESTAMP, Types.TIMESTAMP};
		getJdbcTemplate().update(INSERT_PLAN, params, types);
		return id;
	}

	public String add(String t_course_teaching_class_homework_baseinfo_id, String t_teacher_id, Date pubdate,
			Date enddate) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_teaching_class_homework_baseinfo_id, t_teacher_id, pubdate, enddate };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP };
		getJdbcTemplate().update(INSERT_PLAN, params, types);
		return id;
	}

	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	public void deleteByCourseTeachingClassHomeworkBaseInfoId(String t_course_teaching_class_homework_baseinfo_id) {
		Object params[] = new Object[] { t_course_teaching_class_homework_baseinfo_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_COURSE_TEACHING_CLASS_HOMEWORK_BASEINFO_ID, params, types);
	}

	public void deleteByTeacherId(String t_teacher_id) {
		Object params[] = new Object[] { t_teacher_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_TEACHER_ID, params, types);
	}

	long getCount(String t_group_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_group_id }, new int[] { Types.VARCHAR }, Long.class);
	}

	private List<CourseTeachingClassHomeworkDelayedViewData> PageQuery(String t_course_teaching_class_id, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<CourseTeachingClassHomeworkDelayedViewData> list = new ArrayList<CourseTeachingClassHomeworkDelayedViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_id, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkDelayedViewData data = new CourseTeachingClassHomeworkDelayedViewData();

						CourseTeachingClassHomeworkDelayed homeworkdelayed = getByID(rs.getString("id"));
						data.setHomeworkdelayed(homeworkdelayed);

					

						TeacherViewData teacher = teacherviewdataDao.getTeacherViewDataByTeacherId(homeworkdelayed.getT_teacher_id());
						data.setTeacher(teacher);
						
						CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo=homeworkbaseinfoDao.getByID(homeworkdelayed.getT_course_teaching_class_homework_baseinfo_id());

						data.setHomeworkbaseinfo(homeworkbaseinfo);

						list.add(data);
						// System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}

	public Page<CourseTeachingClassHomeworkDelayedViewData> getPage(String t_course_teaching_class_id, int pageNo, int pageSize) {
		long totalCount = getCount(t_course_teaching_class_id);
		if (totalCount < 1)
			return new Page<CourseTeachingClassHomeworkDelayedViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassHomeworkDelayedViewData> data = PageQuery(t_course_teaching_class_id, pageNo - 1, pageSize);

		return new Page<CourseTeachingClassHomeworkDelayedViewData>(startIndex, totalCount, pageSize, data);

	}
}
