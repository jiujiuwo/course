package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.MailBoxSend;
import com.mathtop.course.domain.MailBoxSendFile;
import com.mathtop.course.domain.MailBoxSendFileViewData;
import com.mathtop.course.utility.GUID;

@Repository
public class MailBoxSendFileDao extends BaseDao<MailBoxSendFile> {

	

	@Autowired
	MailBoxSendDao mailBoxSendDao;

	// insert
	private final String INSERT_MAILBOX_FILE = "INSERT INTO t_mail_box_send_file( id,t_mail_box_send_id,filename,filepath) VALUES(?,?,?,?)";

	// select
	private final String GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID = "SELECT id FROM t_mail_box_send_file WHERE t_mail_box_send_id=?   limit ?,?";
	private final String GET_COUNT_BY_COURSE_TEACHING_CLASS_ID = "SELECT count(*) FROM t_mail_box_send_file WHERE t_mail_box_send_id=?";
	private final String GET_BY_MAIL_BOX_ID = "SELECT id,  filename,filepath FROM t_mail_box_send_file WHERE t_mail_box_send_id=?";
	private final String GET_BY_ID = "SELECT t_mail_box_send_id, filename,filepath FROM t_mail_box_send_file WHERE id=?";

	// DELETE
	private String DELETE_BY_ID = "DELETE FROM t_mail_box_send_file WHERE id=?";
	private String DELETE_BY_MAILBOX_ID = "DELETE FROM t_mail_box_send_file WHERE t_mail_box_send_id=?";
	

	// update
	private String UPDATE_BY_ID = "update t_mail_box_send_file set t_mail_box_send_id=?  filename=?,filepath=? WHERE id=?";

	public void update(String id, String t_mail_box_send_id,  String filename, String filepath) {
		if (id == null || t_mail_box_send_id == null || filename == null || filepath == null)
			return;

		Object params[] = new Object[] {  t_mail_box_send_id,	 filename,  filepath, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}

	/*
	 * 根据用户ID得到用户
	 */
	public MailBoxSendFile getByID(String id) {

		MailBoxSendFile file = new MailBoxSendFile();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				file.setId(id);
				file.setT_mail_box_send_id(rs.getString("t_mail_box_send_id"));
				
				file.setFilename(rs.getString("filename"));
				file.setFilepath(rs.getString("filepath"));

			}

		});

		if (file.getId() == null)
			return null;
		return file;
	}

	/**
	 * 根据课程-作业基本信息得到对应的文件列表
	 * */
	public List<MailBoxSendFile> getByMailBoxSendID(String t_mail_box_send_id) {
		

		List<MailBoxSendFile> list = new ArrayList<MailBoxSendFile>();

		getJdbcTemplate().query(GET_BY_MAIL_BOX_ID, new Object[] { t_mail_box_send_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				MailBoxSendFile file = new MailBoxSendFile();
				file.setId(rs.getString("id"));				
				file.setT_mail_box_send_id(t_mail_box_send_id);				
				file.setFilename(rs.getString("filename"));
				file.setFilepath(rs.getString("filepath"));
				list.add(file);

			}

		});

		return list;
	}

	/* 增加用户 */
	public String add(MailBoxSendFile file) {
		String id = GUID.getGUID();
		file.setId(id);
		Object params[] = new Object[] { file.getId(), file.getT_mail_box_send_id(),file.getFilename(), file.getFilepath() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
		getJdbcTemplate().update(INSERT_MAILBOX_FILE, params, types);
		return id;
	}

	public String add(String t_mail_box_send_id, String filename, String filepath) {

		String id = GUID.getGUID();

		Object params[] = new Object[] { id, t_mail_box_send_id,  filename, filepath };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT_MAILBOX_FILE, params, types);
		return id;
	}

	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	public void deleteByMailBoxId(String t_mail_box_send_id) {
		Object params[] = new Object[] { t_mail_box_send_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_MAILBOX_ID, params, types);
	}

	

	long getCount(String t_mail_box_send_id) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_mail_box_send_id }, new int[] { Types.VARCHAR }, Long.class);
	}

	private List<MailBoxSendFileViewData> PageQuery(String t_mail_box_send_id, int PageBegin, int PageSize) {

		PageBegin -= 1;
		if (PageBegin < 0)
			PageBegin = 0;

		List<MailBoxSendFileViewData> list = new ArrayList<MailBoxSendFileViewData>();

		getJdbcTemplate().query(GET_VIEWDATA_BY_COURSE_TEACHING_CLASS_ID, new Object[] { t_mail_box_send_id, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						MailBoxSendFileViewData data = new MailBoxSendFileViewData();

						MailBoxSendFile file = getByID(rs.getString("id"));
						data.setFile(file);

						MailBoxSend send = mailBoxSendDao.getByID(file.getT_mail_box_send_id());
						data.setSend(send);

						

						list.add(data);
						// System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}

	public Page<MailBoxSendFileViewData> getPage(String t_mail_box_send_id, int pageNo, int pageSize) {
		long totalCount = getCount(t_mail_box_send_id);
		if (totalCount < 1)
			return new Page<MailBoxSendFileViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<MailBoxSendFileViewData> data = PageQuery(t_mail_box_send_id, pageNo - 1, pageSize);

		return new Page<MailBoxSendFileViewData>(startIndex, totalCount, pageSize, data);

	}
}
