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

import com.mathtop.course.domain.MailBoxSend;
import com.mathtop.course.domain.MailBoxSendFile;
import com.mathtop.course.domain.MailBoxSendViewData;
import com.mathtop.course.domain.UserBasicInfoViewData;
import com.mathtop.course.utility.GUID;

@Repository
public class MailBoxSendDao extends BaseDao<MailBoxSend> {

	@Autowired
	UserInfoViewDataDao userDao;
	
	@Autowired
	MailBoxSendFileDao fileDao;

	// insert
	private final String INSERT_MAILBOX = "INSERT INTO t_mail_box_send( id,t_user_id_from, t_user_id_to, state,subject,content,senddate) VALUES(?,?,?,?,?,?,?)";

	// select
	private final String GET_VIEWDATA_BY_USER_ID_FROM = "SELECT id FROM t_mail_box_send WHERE t_user_id_from=? order by senddate desc limit ?,?";
	private final String GET_COUNT_BY_USER_ID_FROM = "SELECT count(*) FROM t_mail_box_send WHERE t_user_id_from=?";
	private final String GET_COUNT_BY_USER_ID_FROM_AND_USER_ID_TO = "SELECT count(*) FROM t_mail_box_send WHERE t_user_id_from=? and t_user_id_to=?";
	private final String GET_BY_ID = "SELECT t_user_id_from, t_user_id_to, state,subject,content,senddate FROM t_mail_box_send WHERE id=?";
	private final String GET_BY_USER_FROM_ID = "SELECT id, t_user_id_to, state,subject,content,senddate FROM t_mail_box_send WHERE t_user_id_from=?";
	private final String GET_BY_USER_TO_ID = "SELECT id, t_user_id_from, state,subject,content,senddate FROM t_mail_box_send WHERE t_user_id_to=?";

	// DELETE
	private String DELETE_BY_ID = "DELETE FROM t_mail_box_send WHERE id=?";
	private String DELETE_BY_USER_ID_FROM = "DELETE FROM t_mail_box_send WHERE t_user_id_from=?";
	private String DELETE_BY_USER_ID_TO = "DELETE FROM t_mail_box_send WHERE t_user_id_to=?";

	// update
	private String UPDATE_BY_ID = "update t_mail_box_send set t_user_id_from=?, t_user_id_to=?, state=?, subject=?,content=?,senddate=?  WHERE id=?";
	

	public void update(String id, String t_user_id_from, String t_user_id_to, String state,
			String subject, String content, Date sendate) {
		if (id == null || t_user_id_from == null || t_user_id_to == null)
			return;

		Object params[] = new Object[] { t_user_id_from, t_user_id_to, state, subject,
				content, sendate, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR,  Types.TIMESTAMP, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}

	

	/*
	 * 根据用户ID得到用户
	 */
	public MailBoxSend getByID(String id) {

		MailBoxSend send = new MailBoxSend();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				send.setId(id);
				send.setT_user_id_from(rs.getString("t_user_id_from"));
				send.setT_user_id_to(rs.getString("t_user_id_to"));
				send.setState(rs.getString("state"));
				send.setSenddate(rs.getTimestamp("senddate"));
				send.setSubject(rs.getString("subject"));
				send.setContent(rs.getString("content"));		

			}

		});

		if (send.getId() == null)
			return null;
		return send;
	}
	
	/*
	 * 根据用户ID得到用户
	 */
	public List<MailBoxSend> getByUserIdFrom(String t_user_id_from) {

		List<MailBoxSend> list=new ArrayList<MailBoxSend>();
		
		

		getJdbcTemplate().query(GET_BY_USER_FROM_ID, new Object[] { t_user_id_from }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				MailBoxSend send = new MailBoxSend();
				send.setId(rs.getString("id"));
				send.setT_user_id_from(t_user_id_from);
				send.setT_user_id_to(rs.getString("t_user_id_to"));
				send.setState(rs.getString("state"));
				send.setSenddate(rs.getTimestamp("senddate"));
				send.setSubject(rs.getString("subject"));
				send.setContent(rs.getString("content"));
				list.add(send);

			}

		});

		return list;
	}
	
	/*
	 * 根据用户ID得到用户
	 */
	public List<MailBoxSend> getByUserIdTo(String t_user_id_to) {

		List<MailBoxSend> list=new ArrayList<MailBoxSend>();

		getJdbcTemplate().query(GET_BY_USER_TO_ID, new Object[] { t_user_id_to }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				MailBoxSend send = new MailBoxSend();
				send.setId(rs.getString("id"));
				send.setT_user_id_from(rs.getString("t_user_id_from"));
				send.setT_user_id_to(t_user_id_to);
				send.setState(rs.getString("t_course_teaching_class_homework_type_id"));
				send.setSenddate(rs.getTimestamp("sendate"));
				send.setSubject(rs.getString("subject"));
				send.setContent(rs.getString("content"));		
				list.add(send);

			}

		});

		return list;
	}

	

	/* 增加用户 */
	public String add(MailBoxSend send) {
		String id = GUID.getGUID();
		send.setId(id);
		Object params[] = new Object[] {send.getId(),send.getT_user_id_from(),send.getT_user_id_to(),send.getState(),send.getSubject(),send.getContent(),new Date()};
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,  Types.TIMESTAMP };
		getJdbcTemplate().update(INSERT_MAILBOX, params, types);
		return id;
	}

	public String add(String t_user_id_from, String t_user_id_to, String state,
			String subject, String content) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_user_id_from, t_user_id_to, state, subject,
				content, new Date() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,  Types.TIMESTAMP };
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

	long getCount(String t_user_id_from) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_USER_ID_FROM,
				new Object[] { t_user_id_from },
				new int[] { Types.VARCHAR}, Long.class);
	}
	
	long getCount(String t_user_id_from,String t_user_id_to) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_USER_ID_FROM_AND_USER_ID_TO,
				new Object[] { t_user_id_from,t_user_id_to },
				new int[] { Types.VARCHAR,Types.VARCHAR}, Long.class);
	}

	public MailBoxSendViewData getMailBoxReceivedViewDataByID(String id) {
		
		
		MailBoxSendViewData data = new MailBoxSendViewData();

		MailBoxSend send = getByID(id);
		data.setSend(send);

		UserBasicInfoViewData userFrom=userDao.getUserBasicInfoViewDataByt_user_id(send.getT_user_id_from());
		data.setUserFrom(userFrom);
		
		UserBasicInfoViewData userTo=userDao.getUserBasicInfoViewDataByt_user_id(send.getT_user_id_to());
		data.setUserTo(userTo);

		List<MailBoxSendFile> sendfile = fileDao.getByMailBoxSendID(send.getId());				
		data.setSendfile(sendfile);
		
		

		return data;
	}

	private List<MailBoxSendViewData> PageQuery(String t_user_from, 
			int PageBegin, int PageSize) {

		
		
		

		List<MailBoxSendViewData> list = new ArrayList<MailBoxSendViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_USER_ID_FROM,
				new Object[] { t_user_from, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						MailBoxSendViewData data = getMailBoxReceivedViewDataByID(rs.getString("id"));
						if (data != null)
							list.add(data);
						// System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}

	public Page<MailBoxSendViewData> getPage(String t_user_from,
			int pageNo, int pageSize) {
		long totalCount = getCount(t_user_from);
		if (totalCount < 1)
			return new Page<MailBoxSendViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<MailBoxSendViewData> data = PageQuery(t_user_from,  pageNo - 1,
				pageSize);

		
		return new Page<MailBoxSendViewData>(startIndex, totalCount, pageSize, data);

	}
}
