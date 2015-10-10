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

import com.mathtop.course.domain.MailBoxReceived;
import com.mathtop.course.domain.MailBoxReceivedFile;
import com.mathtop.course.domain.MailBoxReceivedViewData;
import com.mathtop.course.domain.UserBasicInfoViewData;
import com.mathtop.course.utility.GUID;

@Repository
public class MailBoxReceivedDao extends BaseDao<MailBoxReceived> {

	@Autowired
	UserInfoViewDataDao userDao;

	@Autowired
	MailBoxReceivedFileDao fileDao;

	// insert
	private final String INSERT_MAILBOX = "INSERT INTO t_mail_box_received( id,t_user_id_from, t_user_id_to, state,subject,content,senddate,readdate) VALUES(?,?,?,?,?,?,?,?)";

	// select
	private final String GET_VIEWDATA_BY_USER_ID_TO = "SELECT id FROM t_mail_box_received WHERE t_user_id_to=? order by senddate desc limit ?,?";
	private final String GET_VIEWDATA_NOT_READ_BY_USER_ID_TO = "SELECT id FROM t_mail_box_received WHERE t_user_id_to=? and state not like '%r%' order by senddate desc limit ?,?";
	
	private final String GET_COUNT_BY_USER_ID_TO = "SELECT count(*) FROM t_mail_box_received WHERE t_user_id_to=?";
	private final String GET_COUNT_NOT_READ_BY_USER_ID_TO = "SELECT count(*) FROM t_mail_box_received WHERE t_user_id_to=? and state not like '%r%'";
	private final String GET_COUNT_BY_USER_ID_FROM_AND_USER_ID_TO = "SELECT count(*) FROM t_mail_box_received WHERE t_user_id_to=? and t_user_id_to=?";
	
	private final String GET_BY_ID = "SELECT t_user_id_from, t_user_id_to, state,subject,content,senddate ,readdate FROM t_mail_box_received WHERE id=?";
	private final String GET_BY_USER_FROM_ID = "SELECT id, t_user_id_to, state,subject,content,senddate ,readdate FROM t_mail_box_received WHERE t_user_id_from=?";
	private final String GET_BY_USER_TO_ID = "SELECT id, t_user_id_from, state,subject,content,senddate ,readdate FROM t_mail_box_received WHERE t_user_id_to=?";

	// DELETE
	private String DELETE_BY_ID = "DELETE FROM t_mail_box_received WHERE id=?";
	private String DELETE_BY_USER_ID_FROM = "DELETE FROM t_mail_box_received WHERE t_user_id_from=?";
	private String DELETE_BY_USER_ID_TO = "DELETE FROM t_mail_box_received WHERE t_user_id_to=?";

	// update
	private String UPDATE_BY_ID = "update t_mail_box_received set t_user_id_from=?, t_user_id_to=?, state=?, subject=?,content=?,senddate=? ,readdate=? WHERE id=?";
	private String UPDATE_STATE_BY_ID = "update t_mail_box_received set  state=?,readdate=? WHERE id=?";

	public void update(String id, String t_user_id_from, String t_user_id_to, String state, String subject, String content, Date senddate,
			Date readdate) {
		if (id == null || t_user_id_from == null || t_user_id_to == null)
			return;

		Object params[] = new Object[] { t_user_id_from, t_user_id_to, state, subject, content, senddate, readdate, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP,
				Types.TIMESTAMP, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}

	public void update(String id, String state) {
		if (id == null)
			return;

		Object params[] = new Object[] { state, new Date(), id };
		int types[] = new int[] { Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_STATE_BY_ID, params, types);
	}

	/*
	 * 根据用户ID得到用户
	 */
	public MailBoxReceived getByID(String id) {

		MailBoxReceived received = new MailBoxReceived();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				received.setId(id);
				received.setT_user_id_from(rs.getString("t_user_id_from"));
				received.setT_user_id_to(rs.getString("t_user_id_to"));
				received.setState(rs.getString("state"));
				received.setSenddate(rs.getTimestamp("senddate"));
				received.setReaddate(rs.getTimestamp("readdate"));
				received.setSubject(rs.getString("subject"));
				received.setContent(rs.getString("content"));

			}

		});

		if (received.getId() == null)
			return null;
		return received;
	}

	/*
	 * 根据用户ID得到用户
	 */
	public MailBoxReceived getByUserIdFrom(String t_user_id_from) {

		MailBoxReceived received = new MailBoxReceived();

		getJdbcTemplate().query(GET_BY_USER_FROM_ID, new Object[] { t_user_id_from }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				received.setId(rs.getString("id"));
				received.setT_user_id_from(t_user_id_from);
				received.setT_user_id_to(rs.getString("t_user_id_to"));
				received.setState(rs.getString("state"));
				received.setSenddate(rs.getTimestamp("senddate"));
				received.setReaddate(rs.getTimestamp("readdate"));
				received.setSubject(rs.getString("subject"));
				received.setContent(rs.getString("content"));

			}

		});

		if (received.getId() == null)
			return null;
		return received;
	}

	/*
	 * 根据用户ID得到用户
	 */
	public MailBoxReceived getByUserIdTo(String t_user_id_to) {

		MailBoxReceived received = new MailBoxReceived();

		getJdbcTemplate().query(GET_BY_USER_TO_ID, new Object[] { t_user_id_to }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				received.setId(rs.getString("id"));
				received.setT_user_id_from(rs.getString("t_user_id_from"));
				received.setT_user_id_to(t_user_id_to);
				received.setState(rs.getString("t_course_teaching_class_homework_type_id"));
				received.setSenddate(rs.getTimestamp("sendate"));
				received.setReaddate(rs.getTimestamp("readdate"));
				received.setSubject(rs.getString("subject"));
				received.setContent(rs.getString("content"));

			}

		});

		if (received.getId() == null)
			return null;
		return received;
	}

	/* 增加用户 */
	public String add(MailBoxReceived received) {
		String id = GUID.getGUID();
		received.setId(id);
		Object params[] = new Object[] { received.getId(), received.getT_user_id_from(), received.getT_user_id_to(), received.getState(),
				received.getSubject(), received.getContent(), new Date(), new Date() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.TIMESTAMP, Types.TIMESTAMP };
		getJdbcTemplate().update(INSERT_MAILBOX, params, types);
		return id;
	}

	public String add(String t_user_id_from, String t_user_id_to, String state, String subject, String content) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_user_id_from, t_user_id_to, state, subject, content, new Date(), new Date() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.TIMESTAMP, Types.TIMESTAMP };
		getJdbcTemplate().update(INSERT_MAILBOX, params, types);
		return id;
	}

	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	public void deleteByUserIdFrom(String t_user_id_from) {
		Object params[] = new Object[] { t_user_id_from };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_USER_ID_FROM, params, types);
	}

	public void deleteByUserIdTo(String t_user_id_to) {
		Object params[] = new Object[] { t_user_id_to };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_USER_ID_TO, params, types);
	}

	/**
	 * 所有邮件数目
	 * */
	long getCount(String t_user_id_to) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_USER_ID_TO, new Object[] { t_user_id_to }, new int[] { Types.VARCHAR },
				Long.class);
	}	
	
	/**
	 * 没有读取的邮件数目
	 * */
	long getCountNotRead(String t_user_id_to) {

		return getJdbcTemplate().queryForObject(GET_COUNT_NOT_READ_BY_USER_ID_TO, new Object[] { t_user_id_to }, new int[] { Types.VARCHAR },
				Long.class);
	}
	

	/**
	 * 从指定发件人发送过来的邮件数目
	 * */
	long getCount(String t_user_id_from, String t_user_id_to) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_USER_ID_FROM_AND_USER_ID_TO, new Object[] { t_user_id_from, t_user_id_to },
				new int[] { Types.VARCHAR, Types.VARCHAR }, Long.class);
	}
	
		
	
	
	public MailBoxReceivedViewData getMailBoxReceivedViewDataByID(String id) {
		MailBoxReceivedViewData data = new MailBoxReceivedViewData();

		MailBoxReceived received = getByID(id);
		data.setReceived(received);

		UserBasicInfoViewData userFrom = userDao.getUserBasicInfoViewDataByt_user_id(received.getT_user_id_from());
		data.setUserFrom(userFrom);

		UserBasicInfoViewData userTo = userDao.getUserBasicInfoViewDataByt_user_id(received.getT_user_id_to());
		data.setUserTo(userTo);

		List<MailBoxReceivedFile> receivedfile = fileDao.getByMailBoxReceivedID(received.getId());
		data.setReceivedfile(receivedfile);

		return data;
	}

	private List<MailBoxReceivedViewData> PageQuery(String t_user_to, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<MailBoxReceivedViewData> list = new ArrayList<MailBoxReceivedViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_USER_ID_TO, new Object[] { t_user_to, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						MailBoxReceivedViewData data = getMailBoxReceivedViewDataByID(rs.getString("id"));
						if (data != null)
							list.add(data);
						// System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}
	
	private List<MailBoxReceivedViewData> PageQueryNotRead(String t_user_to, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<MailBoxReceivedViewData> list = new ArrayList<MailBoxReceivedViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_NOT_READ_BY_USER_ID_TO, new Object[] { t_user_to, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						MailBoxReceivedViewData data = getMailBoxReceivedViewDataByID(rs.getString("id"));
						if (data != null)
							list.add(data);
						// System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}

	public Page<MailBoxReceivedViewData> getPage(String t_user_to, int pageNo, int pageSize) {
		long totalCount = getCount(t_user_to);
		if (totalCount < 1)
			return new Page<MailBoxReceivedViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<MailBoxReceivedViewData> data = PageQuery(t_user_to, pageNo - 1, pageSize);

		return new Page<MailBoxReceivedViewData>(startIndex, totalCount, pageSize, data);

	}
	
	public Page<MailBoxReceivedViewData> getPageNotRead(String t_user_to, int pageNo, int pageSize) {
		long totalCount = getCountNotRead(t_user_to);
		if (totalCount < 1)
			return new Page<MailBoxReceivedViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<MailBoxReceivedViewData> data = PageQueryNotRead(t_user_to, pageNo - 1, pageSize);

		return new Page<MailBoxReceivedViewData>(startIndex, totalCount, pageSize, data);

	}
}
