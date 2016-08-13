package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.CourseTeachingTerm;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingTermDao extends BaseDao<CourseTeachingTerm> {

	// insert
	private final String INSERT = "INSERT INTO t_course_teaching_term(id,teaching_year_begin,teaching_year_end,teaching_term,weeks,week_begin) VALUES(?,?,?,?,?,?)";

	// select *
	private final String GET_ALL = "SELECT * FROM t_course_teaching_term order by teaching_year_begin DESC,teaching_term DESC";
	private final String GET_PAGE = "SELECT * FROM t_course_teaching_term order by teaching_year_begin,teaching_term DESC limit ?,?";

	// select by id
	private final String GET_BY_ID = "SELECT * FROM t_course_teaching_term WHERE id=?";

	private final String GET_COUNT_BY_BEGIN_END_TERM = "SELECT count(*) FROM t_course_teaching_term WHERE teaching_year_begin=? and teaching_year_end=? and teaching_term=?";

	private final String GET_COUNT = "SELECT count(*) FROM t_course_teaching_term";

	// delete by id
	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_term WHERE id=?";

	// update by id
	private String UPDATE_BY_ID = "update t_course_teaching_term set teaching_year_begin=?,teaching_year_end=?,teaching_term,weeks=?,week_begin=? WHERE id=?";

	/**
	 * 增加
	 */
	public String add(int teaching_year_begin, int teaching_year_end, int teaching_term, int weeks, Date week_begin) {
		String id = GUID.getGUID();

		Object params[] = new Object[] { id, teaching_year_begin, teaching_year_end, teaching_term, weeks, week_begin };
		int types[] = new int[] { Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER,
				Types.DATE };
		getJdbcTemplate().update(INSERT, params, types);

		return id;
	}

	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	public boolean isTermExist(int teaching_year_begin, int teaching_year_end, int teaching_term) {
		return getJdbcTemplate().queryForObject(GET_COUNT_BY_BEGIN_END_TERM,
				new Object[] { teaching_year_begin, teaching_year_end, teaching_term },
				new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER }, Long.class) > 0;
	}

	public CourseTeachingTerm getByID(String id) {

		CourseTeachingTerm term = new CourseTeachingTerm();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				term.setId(id);
				term.setTeachingYearBegin(rs.getInt("teaching_year_begin"));
				term.setTeachingYearEnd(rs.getInt("teaching_year_end"));
				term.setTeachingTerm(rs.getInt("teaching_term"));
				term.setWeeks(rs.getInt("weeks"));
				term.setWeekBegin(rs.getDate("week_begin"));

			}

		});

		if (term.getId() == null)
			return null;
		return term;
	}

	public List<CourseTeachingTerm> getAll() {
		List<CourseTeachingTerm> list = new ArrayList<>();

		getJdbcTemplate().query(GET_ALL, new Object[] {}, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseTeachingTerm term = new CourseTeachingTerm();
				term.setId(rs.getString("id"));
				term.setTeachingYearBegin(rs.getInt("teaching_year_begin"));
				term.setTeachingYearEnd(rs.getInt("teaching_year_end"));
				term.setTeachingTerm(rs.getInt("teaching_term"));
				term.setWeeks(rs.getInt("weeks"));
				term.setWeekBegin(rs.getDate("week_begin"));
				list.add(term);

			}

		});

		return list;
	}

	public void update(String id, int teaching_year_begin, int teaching_year_end, int teaching_term, int weeks,
			Date week_begin) {
		if (id == null)
			return;

		Object params[] = new Object[] { teaching_year_begin, teaching_year_end, teaching_term, weeks, week_begin, id };
		int types[] = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.DATE,
				Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}

	/**
	 * 总数
	 * */
	public long getCount() {
		return getJdbcTemplate().queryForObject(GET_COUNT, new Object[] {}, new int[] {}, Long.class);
	}

	
	/**
	 * 分页查询
	 * */
	private List<CourseTeachingTerm> PageQuery(int PageBegin, int PageSize) {

		List<CourseTeachingTerm> list = new ArrayList<CourseTeachingTerm>();

		getJdbcTemplate().query(GET_PAGE, new Object[] { PageBegin * PageSize, PageSize }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseTeachingTerm data = getByID(rs.getString("id"));

				if (data != null)
					list.add(data);
				// System.out.println(rs.getString("t_user_id"));
			}

		});
		return list;
	}

	/**
	 * 分页查询
	 * */
	public Page<CourseTeachingTerm> getPage(int pageNo, int pageSize) {
		long totalCount = getCount();
		if (totalCount < 1)
			return new Page<CourseTeachingTerm>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingTerm> data = PageQuery(pageNo - 1, pageSize);

		return new Page<CourseTeachingTerm>(startIndex, totalCount, pageSize, data);

	}

}
