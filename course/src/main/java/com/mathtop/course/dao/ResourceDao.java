package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.Resource;
import com.mathtop.course.utility.GUID;

@Repository
public class ResourceDao extends BaseDao<Resource> {
	

	private String INSERT = "INSERT INTO t_resource(id,uri,uri_element) VALUES(?,?,?)";
	private String GET_BY_URI = "SELECT id,uri_element FROM t_resource WHERE uri=?";
	private String GET_BY_ID = "SELECT uri,uri_element FROM t_resource WHERE id=?";
	private String GET_ALL = "SELECT id,uri,uri_element FROM t_resource";
	private String GET_PAGED = "select a.id,a.uri,a.uri_element from t_resource as a inner join (select id from t_resource limit ?, ?) as b on a.id = b.id ";
	private String GET_COUNT = "select count(*) from t_resource";
	private String DELETE_BY_ID = "DELETE FROM t_resource WHERE id=?";
	private String UPDATE_BY_ID = "update t_resource set uri=?,uri_element=? WHERE id=?";
	private String SELECT_BY_URI = "select id,uri,uri_element FROM t_resource WHERE uri like ?";

	

	

	/* 增加用户 */
	public String add(Resource t) {

		if (t == null)
			return "";

		String newid = GUID.getGUID();
		t.setId(newid);
		Object params[] = new Object[] { t.getId(), t.getUri(), t.getUriElement() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT, params, types);
		return newid;
	}

	/*
	 * 添加
	 */
	public String add(String uri, String uri_element) {
		String newid = GUID.getGUID();
		Object params[] = new Object[] { newid, uri.trim(), uri_element };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT, params, types);
		return newid;
	}

	/*
	 * 删除
	 */
	public void delete(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	/*
	 * 更新
	 */
	public void update(String id, String uri, String uri_element) {
		if (id == null || uri == null)
			return;

		Object params[] = new Object[] { uri.trim(), uri_element, id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}

	/*
	 * 查找
	 */
	public List<Resource> select(String uri) {

		if (uri == null || uri.length() == 0)
			return getAll();

		uri = "%" + uri.trim() + "%";

		List<Resource> list = new ArrayList<Resource>();
		Object params[] = new Object[] { uri };
		int types[] = new int[] { Types.VARCHAR };

		getJdbcTemplate().query(SELECT_BY_URI, params, types,
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Resource d = new Resource();
						d.setId(rs.getString("id"));
						d.setUri(rs.getString("uri"));
						d.setUriElement(rs.getString("uri_element"));
						list.add(d);

					}

				});

		return list;
	}

	/*
	 * 根据用户名得到用户
	 */
	public Resource getByURI(String uri) {

		Resource d = new Resource();

		getJdbcTemplate().query(GET_BY_URI, new Object[] { uri },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						d.setId(rs.getString("id"));
						d.setUri(uri);
						d.setUriElement(rs.getString("uri_element"));

					}

				});

		// System.out.println(user.getUser_password());
		return d;
	}

	/*
	 * 根据用户ID得到用户
	 */
	public Resource getByID(String id) {

		Resource s = new Resource();

		getJdbcTemplate().query(GET_BY_ID, new Object[] { id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						s.setId(id);
						s.setUri(rs.getString("uri"));
						s.setUriElement(rs.getString("uri_element"));

					}

				});

		return s;
	}

	/*
	 * 返回所有结果
	 */
	public List<Resource> getAll() {

		List<Resource> list = new ArrayList<Resource>();
		getJdbcTemplate().query(GET_ALL, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Resource s = new Resource();
				s.setId(rs.getString("id"));
				s.setUri(rs.getString("uri"));
				s.setUriElement(rs.getString("uri_element"));
				list.add(s);
			}
		});

		return list;
	}

	/*
	 * 得到学院总数
	 */
	long getCount() {
		return getJdbcTemplate().queryForObject(GET_COUNT, Long.class);
	}

	/*
	 * 分页查询
	 * 
	 * @PageBegin 开始页面，从0开始
	 */
	public List<Resource> PageQuery(int PageBegin, int PageSize) {

		List<Resource> list = new ArrayList<Resource>();
		getJdbcTemplate().query(GET_PAGED,
				new Object[] { PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Resource s = new Resource();
						s.setId(rs.getString("id"));
						s.setUri(rs.getString("uri"));
						s.setUriElement(rs.getString("uri_element"));
						list.add(s);

					}

				});

		return list;
	}

	/**
	 * 获取学院
	 * 
	 * @param pageNo
	 *            页号，从1开始。
	 * @param pageSize
	 *            每页的记录数
	 * @return 包含分页信息的Page对象
	 */
	public Page<Resource> getPage(int pageNo, int pageSize) {
		long totalCount = getCount();
		if (totalCount < 1)
			return new Page<Resource>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<Resource> data = PageQuery(pageNo - 1, pageSize);

		return new Page<Resource>(startIndex, totalCount, pageSize, data);

	}

	public Page<Resource> getAllPage() {
		long totalCount = getCount();
		if (totalCount < 1)
			return new Page<Resource>();

		List<Resource> data = getAll();

		return new Page<Resource>(0, totalCount, (int) totalCount, data);

	}

	public Page<Resource> select(String name, int pageNo, int pageSize) {
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		List<Resource> data = select(name);
		return new Page<Resource>(startIndex, data.size(), pageSize, data);
	}

}
