package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.CourseTeachingClassHomeworkScoreInfo;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassHomeworkScoreInfoDao extends BaseDao<CourseTeachingClassHomeworkScoreInfo> {

	// insert
	private final String INSERT_PLAN = "INSERT INTO "
			+ " t_course_teaching_class_homework_score_info( id,t_course_teaching_class_homework_baseinfo_id, t_score_marking_type_id, t_score_show_type_id) "
			+ " VALUES(?,?,?,?)";

	private final String INSERT_PLAN_WITH_MARKING_TYPE_ID_NULL_AND_SHOW_TYPE_ID_NULL = "INSERT INTO "
			+ " t_course_teaching_class_homework_score_info( id,t_course_teaching_class_homework_baseinfo_id,t_score_marking_type_id, t_score_show_type_id) "
			+ " VALUES(?,?,null,null)";

	// select
	private final String GET_PAGE_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID = "SELECT A.id,t_course_teaching_class_homework_baseinfo_id, t_score_marking_type_id, t_score_show_type_id "
			+ " FROM t_course_teaching_class_homework_score_info A "
			+ " left outer join t_course_teaching_class_homework_baseinfo B on A.t_course_teaching_class_homework_baseinfo_id=B.id "
			+ " where B.t_course_teaching_class_id=? and B.t_course_teaching_class_homework_type_id=? "
			+ " order by pubdate desc   limit ?,?";
	
	private final String GET_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID_ORDER_BY_ASC = "SELECT A.id,t_course_teaching_class_homework_baseinfo_id, t_score_marking_type_id, t_score_show_type_id "
			+ " FROM t_course_teaching_class_homework_score_info A "
			+ " left outer join t_course_teaching_class_homework_baseinfo B on A.t_course_teaching_class_homework_baseinfo_id=B.id "
			+ " where B.t_course_teaching_class_id=? and B.t_course_teaching_class_homework_type_id=?"
			+ " order by pubdate asc";
	
	private final String GET_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID_ORDER_BY_DESC = "SELECT A.id,t_course_teaching_class_homework_baseinfo_id, t_score_marking_type_id, t_score_show_type_id "
			+ " FROM t_course_teaching_class_homework_score_info A "
			+ " left outer join t_course_teaching_class_homework_baseinfo B on A.t_course_teaching_class_homework_baseinfo_id=B.id "
			+ " where B.t_course_teaching_class_id=? and B.t_course_teaching_class_homework_type_id=?"
			+ " order by pubdate desc";
	
	private final String GET_COUNT_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID = "SELECT count(*) FROM t_course_teaching_class_homework_score_info where t_course_teaching_class_homework_baseinfo_id in(select id from t_course_teaching_class_homework_baseinfo where t_course_teaching_class_id=? and t_course_teaching_class_homework_type_id=?)";
	private final String GET_BY_COURSE_TEACHING_CLASS_HOMEWORK_BASEINFO_ID = "SELECT id, t_score_marking_type_id, t_score_show_type_id  FROM t_course_teaching_class_homework_score_info WHERE t_course_teaching_class_homework_baseinfo_id=?";
	private final String GET_BY_SCORE_MARKING_TYPE_ID = "SELECT id, t_course_teaching_class_homework_baseinfo_id, t_score_show_type_id  FROM t_course_teaching_class_homework_score_info WHERE t_score_marking_type_id=?";
	private final String GET_BY_SCORE_SHOW_TYPE_ID = "SELECT id, t_course_teaching_class_homework_baseinfo_id,t_score_marking_type_id  FROM t_course_teaching_class_homework_score_info WHERE t_score_show_type_id=?";
	private final String GET_BY_ID = "SELECT t_course_teaching_class_homework_baseinfo_id, t_score_marking_type_id, t_score_show_type_id FROM t_course_teaching_class_homework_score_info WHERE id=?";

	// DELETE
	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_class_homework_score WHERE id=?";
	private String DELETE_BY_COURSE_TEACHING_CLASS_HOMEWOKR_BASEINFO_ID = "DELETE FROM t_course_teaching_class_homework_score_info WHERE t_course_teaching_class_homework_baseinfo_id=?";
	private String DELETE_BY_SCORE_MARKING_TYPE_ID = "DELETE FROM t_course_teaching_class_homework_score_info WHERE t_score_marking_type_id=?";
	private String DELETE_BY_SCORE_SHOW_TYPE_ID = "DELETE FROM t_course_teaching_class_homework_score_info WHERE t_score_show_type_id=?";

	// update
	private String UPDATE_BY_ID = "update t_course_teaching_class_homework_score_info set t_course_teaching_class_homework_baseinfo_id=?, t_score_marking_type_id=?, t_score_show_type_id=?  WHERE id=?";
	private String UPDATE_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID = "update t_course_teaching_class_homework_score_info set t_score_marking_type_id=?, t_score_show_type_id=?  WHERE t_course_teaching_class_homework_baseinfo_id in(select id from t_course_teaching_class_homework_baseinfo where t_course_teaching_class_id=? and t_course_teaching_class_homework_type_id=?)";

	public void update(String id, String t_course_teaching_class_homework_baseinfo_id, String t_score_marking_type_id,
			String t_score_show_type_id) {
		if (id == null)
			return;

		Object params[] = new Object[] { t_course_teaching_class_homework_baseinfo_id, t_score_marking_type_id,
				t_score_show_type_id, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}

	public void updateByCourseTeachingClassIdAndHomeworkTypeId(String t_course_teaching_class_id,
			String t_course_teaching_class_homeworktype_id, String t_score_marking_type_id,
			String t_score_show_type_id) {

		Object params[] = new Object[] { t_score_marking_type_id, t_score_show_type_id, t_course_teaching_class_id,
				t_course_teaching_class_homeworktype_id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID, params, types);
	}

	/*
	 * 根据ID得到用户
	 */
	public CourseTeachingClassHomeworkScoreInfo getByID(String id) {

		CourseTeachingClassHomeworkScoreInfo expriment = new CourseTeachingClassHomeworkScoreInfo();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				expriment.setId(id);
				expriment.setCourseTeachingClassHomeworkBaseinfoId(
						rs.getString("t_course_teaching_class_homework_baseinfo_id"));
				expriment.setScoreMarkingTypeId(rs.getString("t_score_marking_type_id"));
				expriment.setScoreShowTypeId(rs.getString("t_score_show_type_id"));

			}

		});

		if (expriment.getId() == null)
			return null;
		return expriment;
	}

	/**
	 * 根据
	 */
	public CourseTeachingClassHomeworkScoreInfo getByCourseTeachingClassHomeworkBaseinfoID(
			String t_course_teaching_class_homework_baseinfo_id) {

		List<CourseTeachingClassHomeworkScoreInfo> list = new ArrayList<CourseTeachingClassHomeworkScoreInfo>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_HOMEWORK_BASEINFO_ID,
				new Object[] { t_course_teaching_class_homework_baseinfo_id }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkScoreInfo expriment = new CourseTeachingClassHomeworkScoreInfo();
						expriment.setId(rs.getString("id"));
						expriment
								.setCourseTeachingClassHomeworkBaseinfoId(t_course_teaching_class_homework_baseinfo_id);
						expriment.setScoreMarkingTypeId(rs.getString("t_score_marking_type_id"));
						expriment.setScoreShowTypeId(rs.getString("t_score_show_type_id"));

						list.add(expriment);

					}

				});

		if (list.size() == 0)
			return null;
		return list.get(0);
	}

	/**
	 * 根据
	 */
	public List<CourseTeachingClassHomeworkScoreInfo> getByScoreMarkingTypeID(String t_score_marking_type_id) {

		List<CourseTeachingClassHomeworkScoreInfo> list = new ArrayList<CourseTeachingClassHomeworkScoreInfo>();

		getJdbcTemplate().query(GET_BY_SCORE_MARKING_TYPE_ID, new Object[] { t_score_marking_type_id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkScoreInfo expriment = new CourseTeachingClassHomeworkScoreInfo();
						expriment.setId(rs.getString("id"));
						expriment.setCourseTeachingClassHomeworkBaseinfoId(rs.getString("t_score_marking_type_id"));
						expriment.setScoreMarkingTypeId(t_score_marking_type_id);
						expriment.setScoreShowTypeId(rs.getString("t_score_show_type_id"));

						list.add(expriment);

					}

				});

		return list;
	}

	/**
	 * 根据
	 */
	public List<CourseTeachingClassHomeworkScoreInfo> getByScoreShowTypeID(String t_score_show_type_id) {

		List<CourseTeachingClassHomeworkScoreInfo> list = new ArrayList<CourseTeachingClassHomeworkScoreInfo>();

		getJdbcTemplate().query(GET_BY_SCORE_SHOW_TYPE_ID, new Object[] { t_score_show_type_id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkScoreInfo expriment = new CourseTeachingClassHomeworkScoreInfo();
						expriment.setId(rs.getString("id"));
						expriment.setCourseTeachingClassHomeworkBaseinfoId(rs.getString("t_score_marking_type_id"));
						expriment.setScoreMarkingTypeId(rs.getString("t_score_marking_type_id"));
						expriment.setScoreShowTypeId(t_score_show_type_id);

						list.add(expriment);

					}

				});

		return list;
	}

	/**
	 * 根据
	 */
	public List<CourseTeachingClassHomeworkScoreInfo> PageQuery(String t_course_teaching_class_id,
			String t_course_teaching_class_homeworktype_id, int PageBegin, int PageSize) {

		List<CourseTeachingClassHomeworkScoreInfo> list = new ArrayList<CourseTeachingClassHomeworkScoreInfo>();

		getJdbcTemplate().query(
				GET_PAGE_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID, new Object[] { t_course_teaching_class_id,
						t_course_teaching_class_homeworktype_id, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkScoreInfo expriment = new CourseTeachingClassHomeworkScoreInfo();
						expriment.setId(rs.getString("id"));
						expriment.setCourseTeachingClassHomeworkBaseinfoId(rs.getString("t_score_marking_type_id"));
						expriment.setScoreMarkingTypeId(rs.getString("t_score_marking_type_id"));
						expriment.setScoreShowTypeId(rs.getString("t_score_show_type_id"));

						list.add(expriment);

					}

				});

		return list;
	}

	/**
	 * 根据
	 */
	public List<CourseTeachingClassHomeworkScoreInfo> getAllOrderByAsc(String t_course_teaching_class_id,
			String t_course_teaching_class_homeworktype_id) {

		List<CourseTeachingClassHomeworkScoreInfo> list = new ArrayList<CourseTeachingClassHomeworkScoreInfo>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID_ORDER_BY_ASC,
				new Object[] { t_course_teaching_class_id, t_course_teaching_class_homeworktype_id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkScoreInfo expriment = new CourseTeachingClassHomeworkScoreInfo();
						expriment.setId(rs.getString("id"));
						expriment.setCourseTeachingClassHomeworkBaseinfoId(rs.getString("t_score_marking_type_id"));
						expriment.setScoreMarkingTypeId(rs.getString("t_score_marking_type_id"));
						expriment.setScoreShowTypeId(rs.getString("t_score_show_type_id"));

						list.add(expriment);

					}

				});

		return list;
	}
	
	/**
	 * 根据
	 */
	public List<CourseTeachingClassHomeworkScoreInfo> getAllOrderByDesc(String t_course_teaching_class_id,
			String t_course_teaching_class_homeworktype_id) {

		List<CourseTeachingClassHomeworkScoreInfo> list = new ArrayList<CourseTeachingClassHomeworkScoreInfo>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID_ORDER_BY_DESC,
				new Object[] { t_course_teaching_class_id, t_course_teaching_class_homeworktype_id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkScoreInfo expriment = new CourseTeachingClassHomeworkScoreInfo();
						expriment.setId(rs.getString("id"));
						expriment.setCourseTeachingClassHomeworkBaseinfoId(rs.getString("t_score_marking_type_id"));
						expriment.setScoreMarkingTypeId(rs.getString("t_score_marking_type_id"));
						expriment.setScoreShowTypeId(rs.getString("t_score_show_type_id"));

						list.add(expriment);

					}

				});

		return list;
	}

	/* 增加用户 */
	public String add(CourseTeachingClassHomeworkScoreInfo score) {
		String id = GUID.getGUID();
		score.setId(id);
		Object params[] = new Object[] { score.getId(), score.getCourseTeachingClassHomeworkBaseinfoId(),
				score.getScoreMarkingTypeId(), score.getScoreShowTypeId() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT_PLAN, params, types);
		return id;
	}

	public String add(String t_course_teaching_class_homework_baseinfo_id, String t_score_marking_type_id,
			String t_score_show_type_id) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_teaching_class_homework_baseinfo_id, t_score_marking_type_id,
				t_score_show_type_id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };

		getJdbcTemplate().update(INSERT_PLAN, params, types);
		return id;
	}

	public String add(String t_course_teaching_class_homework_baseinfo_id) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_teaching_class_homework_baseinfo_id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR };

		getJdbcTemplate().update(INSERT_PLAN_WITH_MARKING_TYPE_ID_NULL_AND_SHOW_TYPE_ID_NULL, params, types);
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
		getJdbcTemplate().update(DELETE_BY_COURSE_TEACHING_CLASS_HOMEWOKR_BASEINFO_ID, params, types);
	}

	public void deleteByScoreMarkingTypeId(String t_score_marking_type_id) {
		Object params[] = new Object[] { t_score_marking_type_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_SCORE_MARKING_TYPE_ID, params, types);
	}

	public void deleteByScoreShowTypeId(String t_score_show_type_id) {
		Object params[] = new Object[] { t_score_show_type_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_SCORE_SHOW_TYPE_ID, params, types);
	}

	public long getCount(String t_course_teaching_class_id, String t_course_teaching_class_homeworktype_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_TEACHING_CLASS_ID_AND_HOMEWORK_TYPE_ID,
				new Object[] { t_course_teaching_class_id, t_course_teaching_class_homeworktype_id },
				new int[] { Types.VARCHAR, Types.VARCHAR }, Long.class);
	}

}
