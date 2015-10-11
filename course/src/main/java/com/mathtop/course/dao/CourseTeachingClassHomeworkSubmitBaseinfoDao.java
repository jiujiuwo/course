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
import com.mathtop.course.domain.CourseTeachingClassHomeworkSubmitBaseinfo;
import com.mathtop.course.domain.CourseTeachingClassHomeworkSubmitBaseinfoViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkSubmitFile;
import com.mathtop.course.domain.StudentViewData;
import com.mathtop.course.utility.GUID;

@Repository
public class CourseTeachingClassHomeworkSubmitBaseinfoDao extends BaseDao<CourseTeachingClassHomeworkSubmitBaseinfo> {

	@Autowired
	CourseTeachingClassHomeworkBaseinfoDao homeworkbaseinfoDao;

	@Autowired
	CourseDao courseDao;

	@Autowired
	StudentViewDataDao studentiewdataDao;

	@Autowired
	CourseTeachingClassHomeworkSubmitFileDao homeworksubmitfileDao;
	
	@Autowired
	CourseTeachingClassHomeworkReplyDao replyDao;

	// insert
	private final String INSERT_PLAN = "INSERT INTO t_course_teaching_class_homework_submit_baseinfo( id,t_course_teaching_class_homework_baseinfo_id, t_student_id,  title, content, submitdate, modifieddate) VALUES(?,?,?,?,?,?,?)";

	// select
	private final String GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID = "SELECT id FROM t_course_teaching_class_homework_submit_baseinfo WHERE t_course_teaching_class_homework_baseinfo_id=?  order by modifieddate DESC limit ?,?";
	private final String GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID_AND_STUDENT_ID = "SELECT id FROM t_course_teaching_class_homework_submit_baseinfo WHERE t_course_teaching_class_homework_baseinfo_id=?  and t_student_id=?  limit ?,?";
	private final String GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID_AND_STUDENT_ID_WITHOUT_PAGED = "SELECT id FROM t_course_teaching_class_homework_submit_baseinfo WHERE t_course_teaching_class_homework_baseinfo_id=?  and t_student_id=?";
	private final String GET_COUNT_BY_COURSE_TEACHING_CLASS_HOMEWORK_BASEINFO_ID = "SELECT count(*) FROM t_course_teaching_class_homework_submit_baseinfo WHERE t_course_teaching_class_homework_baseinfo_id=?";
	private final String GET_COUNT_BY_COURSE_TEACHING_CLASS_ID_AND_STUDENT_ID = "SELECT count(*) FROM t_course_teaching_class_homework_submit_baseinfo WHERE t_course_teaching_class_homework_baseinfo_id=? and t_student_id=?";
	private final String GET_BY_COURSE_TEACHING_CLASS_ID = "SELECT id, t_student_id,  title, content, submitdate, modifieddate FROM t_course_teaching_class_homework_submit_baseinfo WHERE t_course_teaching_class_homework_baseinfo_id=?";
	private final String GET_BY_ID = "SELECT t_course_teaching_class_homework_baseinfo_id, t_student_id,  title, content, submitdate, modifieddate FROM t_course_teaching_class_homework_submit_baseinfo WHERE id=?";
	
	// DELETE
	private String DELETE_BY_ID = "DELETE FROM t_course_teaching_class_homework_submit_baseinfo WHERE id=?";
	private String DELETE_BY_COURSE_TEACHING_CLASS_ID = "DELETE FROM t_course_teaching_class_homework_submit_baseinfo WHERE t_course_teaching_class_homework_baseinfo_id=?";
	private String DELETE_BY_STUDENT_ID = "DELETE FROM t_course_teaching_class_homework_submit_baseinfo WHERE t_student_id=?";

	// update
	private String UPDATE_ALL_BY_ID = "update t_course_teaching_class_homework_submit_baseinfo set t_course_teaching_class_homework_baseinfo_id=?, t_student_id=?, title=?, content=?, submitdate=?, modifieddate=?  WHERE id=?";
	private String UPDATE_SIMPLE_BY_ID = "update t_course_teaching_class_homework_submit_baseinfo set title=?, content=?,  modifieddate=?  WHERE id=?";
	
	
	public void update(String id, String t_course_teaching_class_homework_baseinfo_id, String t_student_id, String title,
			String content, Date submitdate, Date modifieddate) {
		if (id == null || t_student_id == null)
			return;

		Object params[] = new Object[] { t_course_teaching_class_homework_baseinfo_id, t_student_id, title, content, submitdate, modifieddate, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_ALL_BY_ID, params, types);
	}
	
	public void update(String id, String title,
			String content, Date modifieddate) {
		if (id == null )
			return;

		Object params[] = new Object[] { title, content,  modifieddate, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP,  Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_SIMPLE_BY_ID, params, types);
	}

	/*
	 * 根据用户ID得到用户
	 */
	public CourseTeachingClassHomeworkSubmitBaseinfo getByID(String id) {

		CourseTeachingClassHomeworkSubmitBaseinfo submitinfo = new CourseTeachingClassHomeworkSubmitBaseinfo();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				submitinfo.setId(id);
				submitinfo.setT_course_teaching_class_homework_baseinfo_id(rs.getString("t_course_teaching_class_homework_baseinfo_id"));
				submitinfo.setT_student_id(rs.getString("t_student_id"));
				submitinfo.setSubmitdate(rs.getTimestamp("submitdate"));
				submitinfo.setModifieddate(rs.getTimestamp("modifieddate"));
				submitinfo.setTitle(rs.getString("title"));
				submitinfo.setContent(rs.getString("content"));

			}

		});

		if (submitinfo.getId() == null)
			return null;
		return submitinfo;
	}
	
	

	/**
	 * 根据
	 * */
	public List<CourseTeachingClassHomeworkSubmitBaseinfo> getByCourseTeachingClassHomeworkBaseinfoID(String t_course_teaching_class_homework_baseinfo_id) {

		List<CourseTeachingClassHomeworkSubmitBaseinfo> list = new ArrayList<CourseTeachingClassHomeworkSubmitBaseinfo>();

		getJdbcTemplate().query(GET_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_homework_baseinfo_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseTeachingClassHomeworkSubmitBaseinfo submitinfo = new CourseTeachingClassHomeworkSubmitBaseinfo();
				submitinfo.setId(rs.getString("id"));
				submitinfo.setT_course_teaching_class_homework_baseinfo_id(t_course_teaching_class_homework_baseinfo_id);
				submitinfo.setT_student_id(rs.getString("t_student_id"));
				submitinfo.setSubmitdate(rs.getTimestamp("submitdate"));
				submitinfo.setModifieddate(rs.getTimestamp("modifieddate"));
				submitinfo.setTitle(rs.getString("title"));
				submitinfo.setContent(rs.getString("content"));
				list.add(submitinfo);

			}

		});

		return list;
	}

	/**
	 * 得到指定学生、指定作业的提交情况
	 * */
	public List<CourseTeachingClassHomeworkSubmitBaseinfoViewData> getByCourseTeachingClassHomeworkBaseinfoIDAndStudentID(String t_course_teaching_class_id,String t_student_id) {

		
		List<CourseTeachingClassHomeworkSubmitBaseinfoViewData> list = new ArrayList<CourseTeachingClassHomeworkSubmitBaseinfoViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID_AND_STUDENT_ID_WITHOUT_PAGED, new Object[] { t_course_teaching_class_id,t_student_id},
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkSubmitBaseinfoViewData data = new CourseTeachingClassHomeworkSubmitBaseinfoViewData();

						CourseTeachingClassHomeworkSubmitBaseinfo homeworksubmitbaseinfo = getByID(rs.getString("id"));
						data.setHomeworksubmitbaseinfo(homeworksubmitbaseinfo);

						StudentViewData student = studentiewdataDao.getStudentViewDataById(homeworksubmitbaseinfo.getT_student_id());								
						data.setStudent(student);
						
						CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo=homeworkbaseinfoDao.getByID(homeworksubmitbaseinfo.getT_course_teaching_class_homework_baseinfo_id());
						data.setHomeworkbaseinfo(homeworkbaseinfo);

						List<CourseTeachingClassHomeworkSubmitFile> homeworksubmitFileList=homeworksubmitfileDao.getByCourseTeachingClassHomeworkBaseInfoID(homeworksubmitbaseinfo.getId());
						data.setHomeworksubmitFileList(homeworksubmitFileList);
						
						data.setHasReply(replyDao.getCount(homeworksubmitbaseinfo.getId(),t_student_id)>0);

						list.add(data);
						// System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}

	
	/* 增加 */
	public String add(CourseTeachingClassHomeworkSubmitBaseinfo submitinfo) {
		String id = GUID.getGUID();
		submitinfo.setId(id);
		Object params[] = new Object[] { submitinfo.getId(), submitinfo.getT_course_teaching_class_homework_baseinfo_id(), submitinfo.getT_student_id(),
				submitinfo.getTitle(), submitinfo.getContent(), submitinfo.getSubmitdate(), submitinfo.getModifieddate()
				};
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP,
				Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT_PLAN, params, types);
		return id;
	}

	public String add(String t_course_teaching_class_homework_baseinfo_id, String t_student_id, String title, String content, Date submitdate, Date modifieddate) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_course_teaching_class_homework_baseinfo_id, t_student_id, title, content ,submitdate,modifieddate};
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.TIMESTAMP, Types.TIMESTAMP };
		getJdbcTemplate().update(INSERT_PLAN, params, types);
		return id;
	}

	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	public void deleteByCourseTeachingClassHomeworkSumbitBaseInfoId(String t_course_teaching_class_homework_baseinfo_id) {
		Object params[] = new Object[] { t_course_teaching_class_homework_baseinfo_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_COURSE_TEACHING_CLASS_ID, params, types);
	}

	public void deleteByStudentId(String t_student_id) {
		Object params[] = new Object[] { t_student_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_STUDENT_ID, params, types);
	}

	public int getCount(String t_course_teaching_class_homework_baseinfo_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_TEACHING_CLASS_HOMEWORK_BASEINFO_ID, new Object[] { t_course_teaching_class_homework_baseinfo_id }, new int[] { Types.VARCHAR }, Integer.class);
	}

	
	public long getCount(String t_course_teaching_class_homework_baseinfo_id,String t_student_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_TEACHING_CLASS_ID_AND_STUDENT_ID, new Object[] { t_course_teaching_class_homework_baseinfo_id,t_student_id }, new int[] { Types.VARCHAR,Types.VARCHAR }, Long.class);
	}

	public CourseTeachingClassHomeworkSubmitBaseinfoViewData getCourseTeachingClassHomeworkSubmitBaseinfoViewDataById(String id){
		CourseTeachingClassHomeworkSubmitBaseinfoViewData data = new CourseTeachingClassHomeworkSubmitBaseinfoViewData();

		CourseTeachingClassHomeworkSubmitBaseinfo homeworksubmitbaseinfo = getByID(id);
		data.setHomeworksubmitbaseinfo(homeworksubmitbaseinfo);

		StudentViewData student = studentiewdataDao.getStudentViewDataById(homeworksubmitbaseinfo.getT_student_id());								
		data.setStudent(student);
		
		CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo=homeworkbaseinfoDao.getByID(homeworksubmitbaseinfo.getT_course_teaching_class_homework_baseinfo_id());
		data.setHomeworkbaseinfo(homeworkbaseinfo);

		List<CourseTeachingClassHomeworkSubmitFile> homeworksubmitFileList=homeworksubmitfileDao.getByCourseTeachingClassHomeworkBaseInfoID(homeworksubmitbaseinfo.getId());
		data.setHomeworksubmitFileList(homeworksubmitFileList);
		
		data.setHasReply(replyDao.getCount(homeworksubmitbaseinfo.getId(),student.getStudent().getId())>0);
		return data;
	}
	
	private List<CourseTeachingClassHomeworkSubmitBaseinfoViewData> PageQuery(String t_course_teaching_class_id, int PageBegin, int PageSize) {

		
		if (PageBegin < 0)
			PageBegin = 0;

		List<CourseTeachingClassHomeworkSubmitBaseinfoViewData> list = new ArrayList<CourseTeachingClassHomeworkSubmitBaseinfoViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_course_teaching_class_id, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkSubmitBaseinfoViewData data = getCourseTeachingClassHomeworkSubmitBaseinfoViewDataById(rs.getString("id"));

						if(data!=null)
						list.add(data);
						// System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}
	
	private List<CourseTeachingClassHomeworkSubmitBaseinfoViewData> PageQuery(String t_course_teaching_class_id,String t_student_id, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<CourseTeachingClassHomeworkSubmitBaseinfoViewData> list = new ArrayList<CourseTeachingClassHomeworkSubmitBaseinfoViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID_AND_STUDENT_ID, new Object[] { t_course_teaching_class_id,t_student_id, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CourseTeachingClassHomeworkSubmitBaseinfoViewData data = getCourseTeachingClassHomeworkSubmitBaseinfoViewDataById(rs.getString("id"));

						if(data!=null)
						list.add(data);
					}

				});
		return list;
	}

	public Page<CourseTeachingClassHomeworkSubmitBaseinfoViewData> getPage(String t_course_teaching_class_homework_baseinfo_id, int pageNo, int pageSize) {
		long totalCount = getCount(t_course_teaching_class_homework_baseinfo_id);
		if (totalCount < 1)
			return new Page<CourseTeachingClassHomeworkSubmitBaseinfoViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassHomeworkSubmitBaseinfoViewData> data = PageQuery(t_course_teaching_class_homework_baseinfo_id, pageNo - 1, pageSize);

		return new Page<CourseTeachingClassHomeworkSubmitBaseinfoViewData>(startIndex, totalCount, pageSize, data);

	}
	
	public Page<CourseTeachingClassHomeworkSubmitBaseinfoViewData> getPageAll(String t_course_teaching_class_homework_baseinfo_id) {
		long totalCount = getCount(t_course_teaching_class_homework_baseinfo_id);
		if (totalCount < 1)
			return new Page<CourseTeachingClassHomeworkSubmitBaseinfoViewData>();

		// 实际查询返回分页对象
		

		List<CourseTeachingClassHomeworkSubmitBaseinfoViewData> data = PageQuery(t_course_teaching_class_homework_baseinfo_id, 0,(int) totalCount);

		return new Page<CourseTeachingClassHomeworkSubmitBaseinfoViewData>(0, totalCount, (int)totalCount, data);

	}
	
	
	public Page<CourseTeachingClassHomeworkSubmitBaseinfoViewData> getPage(String t_course_teaching_class_homework_baseinfo_id, String t_student_id, int pageNo, int pageSize) {
		long totalCount = getCount(t_course_teaching_class_homework_baseinfo_id,t_student_id);
		if (totalCount < 1)
			return new Page<CourseTeachingClassHomeworkSubmitBaseinfoViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassHomeworkSubmitBaseinfoViewData> data = PageQuery(t_course_teaching_class_homework_baseinfo_id, t_student_id,pageNo - 1, pageSize);

		return new Page<CourseTeachingClassHomeworkSubmitBaseinfoViewData>(startIndex, totalCount, pageSize, data);

	}
	
}
