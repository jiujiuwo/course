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
import com.mathtop.course.domain.PermissionOperator;
import com.mathtop.course.domain.Role;
import com.mathtop.course.domain.RolePermission;
import com.mathtop.course.domain.RolePermissionViewData;
import com.mathtop.course.utility.GUID;

@Repository
public class RolePermissionDao extends BaseDao<RolePermission> {
	
	@Autowired
	protected PermissionOperatorDao permissionoperatordao;

	@Autowired
	protected PermissionDao permissiondao;

	@Autowired
	protected RoleDao roledao;
	private final String GET_ROLEPERMISSION_BY_ROLE_ID_NOTPAGED = "SELECT id,t_permission_id FROM t_role_permission WHERE t_role_id=?";
	private final String GET_PERMISSION_BY_ROLE_ID="SELECT id,t_permission_id FROM t_role_permission where t_role_id=? limit ?,?";
	private final String GET_PERMISSION = "SELECT id,t_role_id,t_permission_id FROM t_role_permission limit ?,?";
	private final String GET_COUNT_BY_ROLEID="SELECT count(*) from t_role_permission where t_role_id=?";
	private final String GET_COUNT="SELECT count(*) from t_role_permission";
	private final String INSERT = "INSERT INTO t_role_permission(id,t_role_id,t_permission_id) VALUES(?,?,?)";
	private final String DELETE_BY_ID = "DELETE FROM t_role_permission WHERE id=?";
	private final String DELETE_BY_PERMISSION_ID = "DELETE FROM t_role_permission WHERE t_permission_id=?";
	private final String DELETE_BY_ROLE_ID = "DELETE FROM t_role_permission WHERE t_role_id=?";
	
	/**
	 * 添加Permission
	 * */
	public String add(String t_role_id, String t_permission_id) {
		String id = GUID.getGUID();
		Object params[] = new Object[] { id, t_role_id, t_permission_id};
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR,Types.VARCHAR };
		getJdbcTemplate().update(INSERT, params, types);
		return id;
	}
	/*删除
	 * */
	public void deleteByID(String id){		
		Object params[]=new Object[]{id};
		int types[]=new int[]{Types.VARCHAR};
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}
	
	/*删除
	 * */
	public void deleteByRoleId(String t_role_id){		
		Object params[]=new Object[]{t_role_id};
		int types[]=new int[]{Types.VARCHAR};
		getJdbcTemplate().update(DELETE_BY_ROLE_ID, params, types);
	}
	
	/*删除
	 * */
	public void deletePermissionId(String t_permission_id){		
		Object params[]=new Object[]{t_permission_id};
		int types[]=new int[]{Types.VARCHAR};
		getJdbcTemplate().update(DELETE_BY_PERMISSION_ID, params, types);
	}
	
	
	public List<RolePermission> getRolePermissionByRoleId(String t_role_id){
		List<RolePermission> list = new ArrayList<RolePermission>();

		getJdbcTemplate().query(GET_ROLEPERMISSION_BY_ROLE_ID_NOTPAGED,
				new Object[] { t_role_id},
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						RolePermission ug = new RolePermission();
						ug.setId(rs.getString("id"));
						ug.setT_permission_id(rs.getString("t_permission_id"));
						ug.setT_role_id(t_role_id);
						list.add(ug);
					//	System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}
	
	private List<RolePermission> PageQuery(
			int PageBegin, int PageSize) {
		
		PageBegin-=1;
		if(PageBegin<0)
			PageBegin=0;
		
		List<RolePermission> list = new ArrayList<RolePermission>();

		getJdbcTemplate().query(GET_PERMISSION,
				new Object[] {  PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						RolePermission p = new RolePermission();
						p.setId(rs.getString("id"));
						p.setT_permission_id(rs.getString("t_permission_id"));
						p.setT_role_id(rs.getString("t_role_id"));
						
					
						
						
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

	public Page<RolePermission> getPage(
			int pageNo, int pageSize) {

		long totalCount = getCount();
		if (totalCount < 1)
			return new Page<RolePermission>();
		
	//	System.out.println(t_group_id);

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<RolePermission> data = PageQuery( pageNo, pageSize);

		return new Page<RolePermission>(startIndex, totalCount, pageSize,
				data);

	}
	
	
	private List<RolePermissionViewData> PageQueryRolePermissionViewData(String roleId,int PageBegin, int PageSize) {
		
		PageBegin-=1;
		if(PageBegin<0)
			PageBegin=0;
		
		List<RolePermissionViewData> list = new ArrayList<RolePermissionViewData>();

		getJdbcTemplate().query(GET_PERMISSION_BY_ROLE_ID,
				new Object[] { roleId, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						RolePermissionViewData p = new RolePermissionViewData();
						
						String id=rs.getString("id");
						String permissionid=rs.getString("t_permission_id");
						String roleid=roleId;
						
						//rolepermission
						RolePermission rolepermission=new RolePermission();
						rolepermission.setId(id);
						rolepermission.setT_permission_id(permissionid);
						rolepermission.setT_role_id(roleid);
						p.setRolepermission(rolepermission);
						
						//permission
						Permission permission=permissiondao.GetById(permissionid);
						p.setPermission(permission);
						
						//role
						Role role=roledao.getByID(roleid);
						p.setRole(role);
						
						PermissionOperator permissionoperator=permissionoperatordao.getByID(permission.getT_permission_operator_id());
						p.setPermissionoperator(permissionoperator);
						
						
					
						
						
						list.add(p);
					//	System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}
	
	long getRolePermissionViewDataCount(String roleId) {

		return getJdbcTemplate().queryForObject(GET_COUNT_BY_ROLEID,
				new Object[] { roleId }, new int[] { Types.VARCHAR },
				Long.class);
	}

	public Page<RolePermissionViewData> getPageRolePermissionViewData(String roleId,
			int pageNo, int pageSize) {

		long totalCount = getRolePermissionViewDataCount(roleId);
		if (totalCount < 1)
			return new Page<RolePermissionViewData>();
		
	//	System.out.println(t_group_id);

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<RolePermissionViewData> data = PageQueryRolePermissionViewData( roleId,pageNo, pageSize);

		return new Page<RolePermissionViewData>(startIndex, totalCount, pageSize,
				data);

	}
	
}
