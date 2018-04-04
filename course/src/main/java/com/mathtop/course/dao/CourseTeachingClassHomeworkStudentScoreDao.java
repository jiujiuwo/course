package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.CourseTeachingClassHomeworkStudentScore;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassHomeworkStudentScoreDao extends BaseDao<CourseTeachingClassHomeworkStudentScore> {

	// insert
	private final String INSERT_STUDENT_SCORE = "INSERT INTO "
			+ " t_course_teaching_class_homework_student_score( id,t_course_teaching_class_homework_score_info_id, t_student_id,t_teacher_id, score,description,record_date,note) "
			+ " VALUES(?,?,?,?,?,?,?,?)";

	// select
	private final String GET_VIEWDATA_BY_SCORE_INFO_ID = "SELECT id FROM t_course_teaching_class_homework_student_score WHERE t_course_teaching_class_homework_score_info_id=?   limit ?,?";
	private final String GET_COUNT_BY_COURSE_TEACHING_CLASS_SCORE_INFO_ID = "SELECT count(*) FROM t_course_teaching_class_homework_student_score WHERE t_course_teaching_class_homework_score_info_id=?";
	private final String GET_COUNT_BY_COURSE_TEACHING_CLASS_SCORE_INFO_ID_AND_STUDENT_ID = "SELECT count(*) FROM t_course_teaching_class_homework_student_score WHERE t_course_teaching_class_homework_score_info_id=? and t_student_id=?";
	private final String GET_BY_COURSE_TEACHING_CLASS_HOMEWORK_SCORE_INFO_ID = "SELECT id, t_student_id,t_teacher_id,score,description,record_date,note  FROM t_course_teaching_class_homework_student_score WHERE t_course_teaching_class_homework_score_info_id=?";
	private final String GET_BY_COURSE_TEACHING_CLASS_SCORE_INFO_ID_AND_STUDENT_ID = "SELECT id, t_student_id,t_teacher_id,score,description,record_date,note  FROM t_course_teaching_class_homework_student_score WHERE t_course_teaching_class_homework_score_info_id=? and t_student_id=?";

	private final String GET_BY_COURSE_TEACHING_CLASS_ID = "SELECT id,t_course_teaching_class_homework_score_info_id, t_student_id,t_teacher_id,score,description,record_date,note  "
			+ " FROM t_course_teaching_class_homework_student_score "
			+ " WHERE t_course_teaching_class_homework_score_info_id in"
			+ " (select id from t_course_teaching_class_homework_score_info where t_course_teaching_class_homework_baseinfo_id in "
			+ " (select id from t_course_teaching_class_homework_baseinfo " + " where t_course_teaching_class_id=?))";

	private final String GET_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID = "SELECT id,t_course_teaching_class_homework_score_info_id, t_student_id,t_teacher_id,score,description,record_date,note  FROM t_course_teaching_class_homework_student_score WHERE t_course_teaching_class_homework_score_info_id in "
			+ " (select id from t_course_teaching_class_homework_score_info "
			+ " where t_course_teaching_class_homework_baseinfo_id in"
			+ " (select id from t_course_teaching_class_homework_baseinfo "
			+ " where t_course_teaching_class_id=? and t_course_teaching_class_homework_type_id=?))";
	private final String GET_BY_STUDENT_ID = "SELECT id, t_course_teaching_class_homework_score_info_id, t_teacher_id,t_teacher_id,score,description,record_date,note  FROM t_course_teaching_class_homework_student_score WHERE t_student_id=?";
	private final String GET_BY_TEACHER_ID = "SELECT id, t_course_teaching_class_homework_score_info_id, t_student_id,t_teacher_id,score,description,record_date,note  FROM t_course_teaching_class_homework_student_score WHERE t_teacher_id=?";
	private final String GET_BY_ID = "SELECT t_course_teaching_class_homework_score_info_id, t_student_id,t_teacher_id,score,description,record_date,note FROM t_course_teaching_class_homework_student_score WHERE id=?";

	// DELETE
	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_class_homework_student_score WHERE id=?";
	private String DELETE_BY_SCORE_INFO_ID = "DELETE FROM t_course_teaching_class_homework_student_score WHERE t_course_teaching_class_homework_score_info_id=?";
	private String DELETE_BY_STUDENT_ID = "DELETE FROM t_course_teaching_class_homework_student_score WHERE t_student_id=?";

	// update
	private String UPDATE_BY_ID = "update t_course_teaching_class_homework_student_score set t_course_teaching_class_homework_score_info_id=?, t_student_id=?, t_teacher_id=?, score=? ,description=? ,record_date=?,note=? WHERE id=?";
	private String UPDATE_SCORE_BY_ID = "update t_course_teaching_class_homework_student_score set score=? ,record_date=?  WHERE id=?";
	private String UPDATE_SCORE_AND_DESCRIPTION_BY_ID = "update t_course_teaching_class_homework_student_score set score=?,description=?,record_date=?   WHERE id=?";
	private String UPDATE_DESCRIPTION_BY_ID = "update t_course_teaching_class_homework_student_score set description=? ,record_date=?  WHERE id=?";

	public void update(String id, String t_course_teaching_class_homework_score_info_id, String t_student_id,
			String t_teacher_id, String score, String description, Date recordDate, String note) {
		if (id == null)
			return;

		Object params[] = new Object[] { t_course_teaching_class_homework_score_info_id, t_student_id, t_teacher_id,
				score, description, recordDate, note, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}

	public void updateScore(String id, String score) {
		if (id == null)
			return;

		Object params[] = new Object[] { score, id };
		int types[] = new int[] { Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_SCORE_BY_ID, params, types);
	}

	public void updateDescription(String id, String description, Date recordDate) {
		if (id == null)
			return;

		Object params[] = new Object[] { description, id };
		int types[] = new int[] { Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_DESCRIPTION_BY_ID, params, types);
	}

	public void update(String id, String score, String description, Date recordDate) {
		if (id == null)
			return;

		Object params[] = new Object[] { score, description, recordDate, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_SCORE_AND_DESCRIPTION_BY_ID, params, types);
	}

	/*
	 * 根据用户ID得到用户
	 */
	public CourseTeachingClassHomeworkStudentScore getByID(String id) {

		CourseTeachingClassHomeworkStudentScore score = new CourseTeachingClassHomeworkStudentScore();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				score.setId(id);
				score.setCourseTeachingClassHomeworkScoreInfoId(
						rs.getString("t_course_teaching_class_homework_score_info_id"));
				score.setStudentId(rs.getString("t_student_id"));
				score.setTeacherId(rs.getString("t_teacher_id"));
				score.setScore(rs.getString("score"));
				score.setDescription(rs.getString("description"));
				score.setRecordDate(rs.getTimestamp("record_date"));
				score.setNote(rs.getString("note"));

			}

		});

		if (score.getId() == null)
			return null;
		return score;
	}

	/**
	 * 根据
	 */
	public List<CourseTeachingClassHomeworkStudentScore> getByCourseTeachingClassHomeworkScoreInfoId(
			String t_course_teaching_class_homework_score_info_id) {

		List<CourseTeachingClassHomeworkStudentScore> list = new ArrayList<CourseTeachingClassHomeworkStudentScore>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_HOMEWORK_SCORE_INFO_ID,
				new Object[] { t_course_teaching_class_homework_score_info_id }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkStudentScore score = getByID(rs.getString("id"));
						list.add(score);

					}

				});

		return list;
	}

	/**
	 * 根据
	 */
	public CourseTeachingClassHomeworkStudentScore getByCourseTeachingClassHomeworkScoreInfoIdAndStudentId(
			String t_course_teaching_class_homework_score_info_id, String t_student_id) {

		List<CourseTeachingClassHomeworkStudentScore> list = new ArrayList<CourseTeachingClassHomeworkStudentScore>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_SCORE_INFO_ID_AND_STUDENT_ID,
				new Object[] { t_course_teaching_class_homework_score_info_id, t_student_id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkStudentScore score = getByID(rs.getString("id"));
						list.add(score);

					}

				});

		if (list.size() == 0)
			return null;
		return list.get(0);
	}

	/**
	 * 根据
	 */
	public List<CourseTeachingClassHomeworkStudentScore> getByCourseTeachingClassId(String t_course_teaching_class_id) {

		List<CourseTeachingClassHomeworkStudentScore> list = new ArrayList<CourseTeachingClassHomeworkStudentScore>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkStudentScore score = getByID(rs.getString("id"));
						list.add(score);

					}

				});

		return list;
	}

	/**
	 * 根据
	 */
	public List<CourseTeachingClassHomeworkStudentScore> getByCourseTeachingClassIdAndHomeworkTypeId(
			String t_course_teaching_class_id, String t_course_teaching_class_homework_type_id) {

		List<CourseTeachingClassHomeworkStudentScore> list = new ArrayList<CourseTeachingClassHomeworkStudentScore>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID,
				new Object[] { t_course_teaching_class_id, t_course_teaching_class_homework_type_id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkStudentScore score = getByID(rs.getString("id"));
						list.add(score);

					}

				});

		return list;
	}

	/**
	 * 根据
	 */
	public List<CourseTeachingClassHomeworkStudentScore> getByStudentId(String t_student_id) {

		List<CourseTeachingClassHomeworkStudentScore> list = new ArrayList<CourseTeachingClassHomeworkStudentScore>();

		getJdbcTemplate().query(GET_BY_STUDENT_ID, new Object[] { t_student_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseTeachingClassHomeworkStudentScore score = getByID(rs.getString("id"));
				list.add(score);

			}

		});

		return list;
	}

	/**
	 * 根据
	 */
	public List<CourseTeachingClassHomeworkStudentScore> getByTeacherId(String t_teacher_id) {

		List<CourseTeachingClassHomeworkStudentScore> list = new ArrayList<CourseTeachingClassHomeworkStudentScore>();

		getJdbcTemplate().query(GET_BY_TEACHER_ID, new Object[] { t_teacher_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseTeachingClassHomeworkStudentScore score = getByID(rs.getString("id"));
				list.add(score);

			}

		});

		return list;
	}

	/* 增加用户 */
	public String add(CourseTeachingClassHomeworkStudentScore score) {
		String id = GUID.getGUID();
		score.setId(id);
		Object params[] = new Object[] { score.getId(), score.getCourseTeachingClassHomeworkScoreInfoId(),
				score.getStudentId(), score.getTeacherId(), score.getScore(), score.getDescription(),
				score.getRecordDate(), score.getNote() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR };
		getJdbcTemplate().update(INSERT_STUDENT_SCORE, params, types);
		return id;
	}

	public String add(String t_course_teaching_class_homework_score_info_id, String t_student_id, String t_teacher_id,
			String score, String description, Date recordDate, String note) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_teaching_class_homework_score_info_id, t_student_id, t_teacher_id,
				score, description, recordDate, note };

		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR };

		getJdbcTemplate().update(INSERT_STUDENT_SCORE, params, types);
		return id;
	}

	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	public void deleteByScoreInfoId(String t_course_teaching_class_homework_score_info_id) {
		Object params[] = new Object[] { t_course_teaching_class_homework_score_info_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_SCORE_INFO_ID, params, types);
	}
	
	
	public void deleteByStudentId(String t_student_id) {
		Object params[] = new Object[] { t_student_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_STUDENT_ID, params, types);
	}

	public boolean isStudentScoreExist(String t_course_teaching_class_homework_score_info_id, String t_student_id) {
		return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_TEACHING_CLASS_SCORE_INFO_ID_AND_STUDENT_ID,
				new Object[] { t_course_teaching_class_homework_score_info_id, t_student_id },
				new int[] { Types.VARCHAR, Types.VARCHAR }, Long.class) > 0;
	}

	public long getCount(String t_course_teaching_class_homework_score_info_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_TEACHING_CLASS_SCORE_INFO_ID,
				new Object[] { t_course_teaching_class_homework_score_info_id }, new int[] { Types.VARCHAR },
				Long.class);
	}

	public List<CourseTeachingClassHomeworkStudentScore> PageQuery(
			String t_course_teaching_class_homework_score_info_id, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<CourseTeachingClassHomeworkStudentScore> list = new ArrayList<CourseTeachingClassHomeworkStudentScore>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_SCORE_INFO_ID,
				new Object[] { t_course_teaching_class_homework_score_info_id, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {

						CourseTeachingClassHomeworkStudentScore score = getByID(rs.getString("id"));

						list.add(score);
						// System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}

	public Page<CourseTeachingClassHomeworkStudentScore> getPage(String t_course_teaching_class_homework_baseinfo_id,
			int pageNo, int pageSize) {
		long totalCount = getCount(t_course_teaching_class_homework_baseinfo_id);
		if (totalCount < 1)
			return new Page<CourseTeachingClassHomeworkStudentScore>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassHomeworkStudentScore> data = PageQuery(t_course_teaching_class_homework_baseinfo_id,
				pageNo - 1, pageSize);

		return new Page<CourseTeachingClassHomeworkStudentScore>(startIndex, totalCount, pageSize, data);

	}

}
