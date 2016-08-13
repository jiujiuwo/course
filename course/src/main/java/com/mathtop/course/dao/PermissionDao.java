package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.Permission;
import com.mathtop.course.domain.PermissionViewData;
import com.mathtop.course.utility.GUID;

@Repository
public class PermissionDao extends BaseDao<Permission> {
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private PermissionOperatorDao permissionoperatorDao;

	
	private final String GET_PERMISSION = "SELECT id,name,note,t_permission_operator_id,t_resource_id FROM t_permission order by name limit ?,?";
	private final String GET_COUNT="SELECT count(*) from t_permission";
	private final String GET_BY_ID="select name,note,t_permission_operator_id,t_resource_id from t_permission where id=?";
	private final String INSERT = "INSERT INTO t_permission(id,name,note,t_permission_operator_id,t_resource_id) VALUES(?,?,?,?,?)";
	
	/**
	 * 添加Permission
	 * */
	public String add(String name, String note,String t_permission_operator_id,String t_resource_id) {
		String id = GUID.getGUID();
		Object params[] = new Object[] { id, name, note,t_permission_operator_id ,t_resource_id};
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT, params, types);
		return id;
	}
	
	public Permission GetById(String id){
		
		Permission p = new Permission();
		getJdbcTemplate().query(GET_BY_ID,
				new Object[] {  id},
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						
						p.setId(id);
						p.setName(rs.getString("name"));
						p.setNote(rs.getString("note"));
						p.setPermissionOperatorId(rs.getString("t_permission_operator_id"));
						p.setResourceId(rs.getString("t_resource_id"));
						
						
						
					//	System.out.println(rs.getString("t_user_id"));
					}

				});
		
		if(p.getId()!=null)
			return p;
		return null;
		
	}
	
	private List<Permission> PageQuery(
			int PageBegin, int PageSize) {
		
		PageBegin-=1;
		if(PageBegin<0)
			PageBegin=0;
		
		List<Permission> list = new ArrayList<Permission>();

		getJdbcTemplate().query(GET_PERMISSION,
				new Object[] {  PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Permission p = new Permission();
						p.setId(rs.getString("id"));
						p.setName(rs.getString("name"));
						p.setNote(rs.getString("note"));
						p.setPermissionOperatorId(rs.getString("t_permission_operator_id"));
						p.setResourceId(rs.getString("t_resource_id"));
						
						
						list.add(p);
					//	System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}
	
	long getCount() {

		return getJdbcTemplate().queryForObject(GET_COUNT,
				null, null,
				Long.class);
	}

	public Page<Permission> getPage(
			int pageNo, int pageSize) {

		long totalCount = getCount();
		if (totalCount < 1)
			return new Page<Permission>();
		
	//	System.out.println(t_group_id);

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<Permission> data = PageQuery( pageNo, pageSize);

		return new Page<Permission>(startIndex, totalCount, pageSize,
				data);

	}
	
	public PermissionViewData getPermissionViewDataByPermissionId(String t_permission_id){
		
		Permission p=GetById(t_permission_id);
		if(p==null)
			return null;
		
		PermissionViewData view=new PermissionViewData();
		
		
		view.setPermission(p);
		view.setPermissionoperator(permissionoperatorDao.getByID(p.getPermissionOperatorId()));
		view.setResource(resourceDao.getByID(p.getResourceId()));
		return view;
	}
	
	
	private List<PermissionViewData> PageQueryPermissionViewData(
			int PageBegin, int PageSize) {
		
		List<PermissionViewData> listviewdata=new ArrayList<PermissionViewData>();
		
		List<Permission> list = PageQuery(PageBegin,PageSize);
		for(Permission p:list){
			PermissionViewData view=new PermissionViewData();
			view.setPermission(p);
			view.setPermissionoperator(permissionoperatorDao.getByID(p.getPermissionOperatorId()));
			view.setResource(resourceDao.getByID(p.getResourceId()));
			listviewdata.add(view);
		}
		

		return listviewdata;
		
	}
	
	public Page<PermissionViewData> getPagePermissionViewData(
			int pageNo, int pageSize) {

		long totalCount = getCount();
		if (totalCount < 1)
			return new Page<PermissionViewData>();
		
	//	System.out.println(t_group_id);

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<PermissionViewData> data = PageQueryPermissionViewData( pageNo, pageSize);

		return new Page<PermissionViewData>(startIndex, totalCount, pageSize,
				data);

	}
	
	
	public Page<PermissionViewData> getPagePermissionViewData(
			) {

		long totalCount = getCount();
		if (totalCount < 1)
			return new Page<PermissionViewData>();
		
	//	System.out.println(t_group_id);

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(1, (int)totalCount);

		List<PermissionViewData> data = PageQueryPermissionViewData( 1, (int)totalCount);

		return new Page<PermissionViewData>(startIndex, totalCount,(int) totalCount,
				data);

	}
	
	public List<PermissionViewData> getPermissionViewData(
			) {

		long totalCount = getCount();
		if (totalCount < 1)
			return null;
		
	//	System.out.println(t_group_id);

		// 实际查询返回分页对象
		

		List<PermissionViewData> data = PageQueryPermissionViewData( 1, (int)totalCount);

		return data;

	}
	
	
	
	
}
