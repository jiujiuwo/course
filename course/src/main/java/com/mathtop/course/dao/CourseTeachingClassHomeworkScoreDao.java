package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.CourseTeachingClassHomeworkScore;
import com.mathtop.course.domain.CourseTeachingClassHomeworkScoreViewData;
import com.mathtop.course.domain.ScoreSystemType;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassHomeworkScoreDao extends BaseDao<CourseTeachingClassHomeworkScore> {



	@Autowired
	ScoreSystemTypeDao scoreSystemTypeDao;

	// insert
	private final String INSERT_PLAN = "INSERT INTO t_course_teaching_class_homework_score( id,t_course_teaching_class_homework_baseinfo_id, t_score_system_type_id, score) VALUES(?,?,?,?)";

	// select
	private final String GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID = "SELECT id FROM t_course_teaching_class_homework_score WHERE t_course_teaching_class_homework_baseinfo_id=?   limit ?,?";
	private final String GET_COUNT_BY_COURSE_TEACHING_CLASS_ID = "SELECT count(*) FROM t_course_teaching_class_homework_score WHERE t_course_teaching_class_homework_baseinfo_id=?";
	private final String GET_BY_COURSE_TEACHING_CLASS_ID = "SELECT id, t_score_system_type_id, score  FROM t_course_teaching_class_homework_score WHERE t_course_teaching_class_homework_baseinfo_id=?";
	private final String GET_BY_ID = "SELECT t_course_teaching_class_homework_baseinfo_id, t_score_system_type_id,score FROM t_course_teaching_class_homework_score WHERE id=?";

	// DELETE
	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_class_homework_score WHERE id=?";
	private String DELETE_BY_COURSE_TEACHING_CLASS_ID = "DELETE FROM t_course_teaching_class_homework_score WHERE t_course_teaching_class_id=?";
	

	// update
	private String UPDATE_BY_ID = "update t_course_teaching_class_homework_score set t_course_teaching_class_homework_baseinfo_id=?, t_score_system_type_id=?, score=?   WHERE id=?";
	private String UPDATE_SIMPLE_0_BY_ID = "update t_course_teaching_class_homework_score set score=?   WHERE id=?";
	private String UPDATE_SIMPLE_1_BY_ID = "update t_course_teaching_class_homework_score set t_score_system_type_id=?,score=?   WHERE id=?";

	public void update(String id, String t_course_teaching_class_homework_baseinfo_id, String t_score_system_type_id, String score) {
		if (id == null  )
			return;

		Object params[] = new Object[] {  t_course_teaching_class_homework_baseinfo_id,  t_score_system_type_id, score,   id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}
	
	public void update(String id, String score) {
		if (id == null  )
			return;

		Object params[] = new Object[] {     score,   id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR};
		getJdbcTemplate().update(UPDATE_SIMPLE_0_BY_ID, params, types);
	}
	
	public void update(String id, String t_score_system_type_id, String score) {
		if (id == null  )
			return;

		Object params[] = new Object[] {    t_score_system_type_id, score,   id };
		int types[] = new int[] {  Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
		getJdbcTemplate().update(UPDATE_SIMPLE_1_BY_ID, params, types);
	}

	/*
	 * 根据用户ID得到用户
	 */
	public CourseTeachingClassHomeworkScore getByID(String id) {

		CourseTeachingClassHomeworkScore expriment = new CourseTeachingClassHomeworkScore();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				expriment.setId(id);
				expriment.setT_course_teaching_class_homework_baseinfo_id(rs.getString("t_course_teaching_class_homework_baseinfo_id"));
				expriment.setT_score_system_type_id(rs.getString("t_score_system_type_id"));				
				expriment.setScore(rs.getString("title"));
				
			
			}

		});

		if (expriment.getId() == null)
			return null;
		return expriment;
	}

	/**
	 * 根据教学班得到通知
	 * */
	public List<CourseTeachingClassHomeworkScore> getByCourseTeachingClassID(String t_course_teaching_class_homework_baseinfo_id) {

		List<CourseTeachingClassHomeworkScore> list = new ArrayList<CourseTeachingClassHomeworkScore>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_homework_baseinfo_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseTeachingClassHomeworkScore expriment = new CourseTeachingClassHomeworkScore();
				expriment.setId(rs.getString("id"));
				expriment.setT_course_teaching_class_homework_baseinfo_id(t_course_teaching_class_homework_baseinfo_id);
				expriment.setT_score_system_type_id(rs.getString("t_score_system_type_id"));				
				expriment.setScore(rs.getString("title"));
			
				list.add(expriment);

			}

		});

		return list;
	}

	/* 增加用户 */
	public String add(CourseTeachingClassHomeworkScore expriment) {
		String id = GUID.getGUID();
		expriment.setId(id);
		Object params[] = new Object[] { expriment.getId(), expriment.getT_course_teaching_class_homework_baseinfo_id(), expriment.getT_score_system_type_id(),
				expriment.getScore()
				};
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT_PLAN, params, types);
		return id;
	}

	public String add(String t_course_teaching_class_homework_baseinfo_id, String t_score_system_type_id, String score) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_teaching_class_homework_baseinfo_id, t_score_system_type_id,score};
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
				
		getJdbcTemplate().update(INSERT_PLAN, params, types);
		return id;
	}

	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	public void deleteByCourseTeachingClassHomeworkInfoId(String t_course_teaching_class_homework_baseinfo_id) {
		Object params[] = new Object[] { t_course_teaching_class_homework_baseinfo_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_COURSE_TEACHING_CLASS_ID, params, types);
	}

	
	long getCount(String t_course_teaching_class_homework_baseinfo_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_homework_baseinfo_id }, new int[] { Types.VARCHAR }, Long.class);
	}

	private List<CourseTeachingClassHomeworkScoreViewData> PageQuery(String t_course_teaching_class_id, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<CourseTeachingClassHomeworkScoreViewData> list = new ArrayList<CourseTeachingClassHomeworkScoreViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_id, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkScoreViewData data = new CourseTeachingClassHomeworkScoreViewData();

						CourseTeachingClassHomeworkScore score = getByID(rs.getString("id"));
						data.setScore(score);

						ScoreSystemType systemType = scoreSystemTypeDao.getByID(score.getT_score_system_type_id());
						data.setSystemType(systemType);

						


						list.add(data);
						// System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}

	public Page<CourseTeachingClassHomeworkScoreViewData> getPage(String t_course_teaching_class_homework_baseinfo_id, int pageNo, int pageSize) {
		long totalCount = getCount(t_course_teaching_class_homework_baseinfo_id);
		if (totalCount < 1)
			return new Page<CourseTeachingClassHomeworkScoreViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassHomeworkScoreViewData> data = PageQuery(t_course_teaching_class_homework_baseinfo_id, pageNo - 1, pageSize);

		return new Page<CourseTeachingClassHomeworkScoreViewData>(startIndex, totalCount, pageSize, data);

	}
}
