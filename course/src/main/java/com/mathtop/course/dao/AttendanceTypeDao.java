package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.AttendanceType;
import com.mathtop.course.utility.GUID;

@Repository
public class AttendanceTypeDao extends BaseDao<AttendanceType> {
	// insert
		private final String INSERT_PLAN = "INSERT INTO t_attendance_type( id,t_course_teaching_class_id,  name, note) VALUES(?,?,?,?)";

		// select
		private final String GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID = "SELECT id FROM t_attendance_type WHERE t_course_teaching_class_id=?   limit ?,?";
		private final String GET_COUNT_BY_COURSE_TEACHING_CLASS_ID = "SELECT count(*) FROM t_attendance_type WHERE t_course_teaching_class_id=?";
		private final String GET_BY_COURSE_TEACHING_CLASS_ID = "SELECT id,  name, note FROM t_attendance_type WHERE t_course_teaching_class_id=?";
		private final String GET_BY_ID = "SELECT t_course_teaching_class_id, name, note FROM t_attendance_type WHERE id=?";

		// DELETE
		private String DELETE_BY_ID = "DELETE FROM t_attendance_type WHERE id=?";
		private String DELETE_BY_COURSE_TEACHING_CLASS_ID = "DELETE FROM t_attendance_type WHERE t_course_teaching_class_id=?";
		

		// update
		private String UPDATE_BY_ID = "update t_attendance_type set  name=?, note=?  WHERE id=?";

		public void update(String id, String name, String note) {
			if (id == null  )
				return;

			Object params[] = new Object[] {      name,  note,id};
			int types[] = new int[] { Types.VARCHAR,   Types.VARCHAR, Types.VARCHAR};
			getJdbcTemplate().update(UPDATE_BY_ID, params, types);
		}
		
		

		/*
		 * 
		 */
		public AttendanceType getByID(String id) {

			AttendanceType expriment = new AttendanceType();

			getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

				@Override
				public void processRow(ResultSet rs) throws SQLException {
					expriment.setId(id);
					expriment.setT_course_teaching_class_id(rs.getString("t_course_teaching_class_id"));
									
					expriment.setName(rs.getString("name"));
					expriment.setNote(rs.getString("note"));
					
				
				}

			});

			if (expriment.getId() == null)
				return null;
			return expriment;
		}

		/**
		 * 根据教学班得到通知
		 * */
		public List<AttendanceType> getByCourseTeachingClassID(String t_course_teaching_class_id) {

			List<AttendanceType> list = new ArrayList<AttendanceType>();

			getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_id }, new RowCallbackHandler() {

				@Override
				public void processRow(ResultSet rs) throws SQLException {
					AttendanceType expriment = new AttendanceType();
					expriment.setId(rs.getString("id"));
					expriment.setT_course_teaching_class_id(t_course_teaching_class_id);
								
					expriment.setName(rs.getString("name"));
					expriment.setNote(rs.getString("note"));
				
					list.add(expriment);

				}

			});

			return list;
		}

		/* 增加用户 */
		public String add(AttendanceType expriment) {
			String id = GUID.getGUID();
			expriment.setId(id);
			Object params[] = new Object[] { expriment.getId(), expriment.getT_course_teaching_class_id(), 
					expriment.getName(), expriment.getNote()};
			int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
			getJdbcTemplate().update(INSERT_PLAN, params, types);
			return id;
		}

		public String add(String t_course_teaching_class_id, String name, String note) {

			String id = GUID.getGUID();

			Object params[] = new Object[] { id, t_course_teaching_class_id, name,note};
			int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
			getJdbcTemplate().update(INSERT_PLAN, params, types);
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


		long getCount(String t_course_teaching_class_id) {

			return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_id }, new int[] { Types.VARCHAR }, Long.class);
		}

		private List<AttendanceType> PageQuery(String t_course_teaching_class_id, int PageBegin, int PageSize) {

			PageBegin -= 1;
			if (PageBegin < 0)
				PageBegin = 0;

			List<AttendanceType> list = new ArrayList<AttendanceType>();

			getJdbcTemplate().query(GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_id, PageBegin * PageSize, PageSize },
					new RowCallbackHandler() {

						@Override
						public void processRow(ResultSet rs) throws SQLException {
							

							AttendanceType data = getByID(rs.getString("id"));
							

							list.add(data);
							// System.out.println(rs.getString("t_user_id"));
						}

					});
			return list;
		}

		public Page<AttendanceType> getPage(String t_course_teaching_class_id, int pageNo, int pageSize) {
			long totalCount = getCount(t_course_teaching_class_id);
			if (totalCount < 1)
				return new Page<AttendanceType>();

			// 实际查询返回分页对象
			int startIndex = Page.getStartOfPage(pageNo, pageSize);

			List<AttendanceType> data = PageQuery(t_course_teaching_class_id, pageNo - 1, pageSize);

			return new Page<AttendanceType>(startIndex, totalCount, pageSize, data);

		}
		
		public Page<AttendanceType> getPage(String t_course_teaching_class_id) {
			long totalCount = getCount(t_course_teaching_class_id);
			if (totalCount < 1)
				return new Page<AttendanceType>();

			// 实际查询返回分页对象
			int startIndex = Page.getStartOfPage(1, (int)totalCount);

			List<AttendanceType> data = PageQuery(t_course_teaching_class_id, 1, (int)totalCount);

			return new Page<AttendanceType>(startIndex, totalCount, (int)totalCount, data);

		}
}
