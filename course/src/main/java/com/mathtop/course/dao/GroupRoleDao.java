package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.Group;
import com.mathtop.course.domain.GroupRole;
import com.mathtop.course.domain.GroupRoleViewData;
import com.mathtop.course.domain.Role;
import com.mathtop.course.utility.GUID;

@Repository
public class GroupRoleDao extends BaseDao<GroupRole> {
	
	@Autowired
	protected RoleDao roledao;
	
	@Autowired
	protected GroupDao groupdao;
	
	private final String GET_GROUPROLE_BY_ROLE_ID_NOTPAGED = "SELECT id,t_group_id FROM t_group_role WHERE t_role_id=?";
	private final String GET_GROUPROLE_BY_GROUP_ID_NOTPAGED = "SELECT id,t_role_id FROM t_group_role WHERE t_group_id=?";
	private final String GET_ROLEID_BY_GROUP_ID = "SELECT id,t_role_id FROM t_group_role WHERE t_group_id=? limit ?,?";
	private final String GET_ROLEID_COUNT_BY_GROUP_ID = "SELECT count(*) FROM t_group_role WHERE t_group_id=?";
	private final String INSERT = "INSERT INTO t_group_role(id,t_group_id,t_role_id) VALUES(?,?,?)";
	
	
	private String DELETE_BY_ID = "DELETE FROM t_group_role WHERE id=?";
	
	public String add(String t_group_id,String t_role_id){
		String id = GUID.getGUID();
		Object params[] = new Object[] { id, t_group_id, t_role_id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT, params, types);
		return id;
	}
	
	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}
	
	public List<GroupRole> getGroupRoleByGroupId(String t_group_id){
		List<GroupRole> list = new ArrayList<GroupRole>();

		getJdbcTemplate().query(GET_GROUPROLE_BY_GROUP_ID_NOTPAGED,
				new Object[] { t_group_id},
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						GroupRole ug = new GroupRole();
						ug.setId(rs.getString("id"));
						ug.setT_group_id(t_group_id);
						ug.setT_role_id(rs.getString("t_role_id"));
						list.add(ug);
					//	System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}
	
	public List<GroupRole> getGroupRoleByRoleId(String t_role_id){
		List<GroupRole> list = new ArrayList<GroupRole>();

		getJdbcTemplate().query(GET_GROUPROLE_BY_ROLE_ID_NOTPAGED,
				new Object[] { t_role_id},
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						GroupRole ug = new GroupRole();
						ug.setId(rs.getString("id"));
						ug.setT_group_id(rs.getString("t_group_id"));
						ug.setT_role_id(t_role_id);
						list.add(ug);
					//	System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}
	
	
	private List<GroupRoleViewData> PageQuery(String t_group_id, int PageBegin,
			int PageSize) {
		
		PageBegin-=1;
		if(PageBegin<0)
			PageBegin=0;
		
		List<GroupRoleViewData> list =new ArrayList<GroupRoleViewData>();
		
		Group group=groupdao.getByID(t_group_id);
		
		getJdbcTemplate().query(GET_ROLEID_BY_GROUP_ID,
				new Object[] { t_group_id, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						
						GroupRoleViewData g=new GroupRoleViewData();
						String roleid=rs.getString("t_role_id");
						Role r=roledao.getByID(roleid);
						g.setRole(r);
						g.setGroup(group);
						
						GroupRole grouprole=new GroupRole();
						grouprole.setId(rs.getString("id"));
						grouprole.setT_group_id(group.getId());
						grouprole.setT_role_id(roleid);
						g.setGrouprole(grouprole);
						
						list.add(g);
						
					//	System.out.println(rs.getString("t_user_id"));
					}

				});
		
		return list;
	}
	
	long getCount(String t_group_id) {

		return getJdbcTemplate().queryForObject(GET_ROLEID_COUNT_BY_GROUP_ID,
				new Object[] { t_group_id }, new int[] { Types.VARCHAR },
				Long.class);
	}

	public Page<GroupRoleViewData> getPageByGroupId(String t_group_id,
			int pageNo, int pageSize) {

		long totalCount = getCount(t_group_id);
		if (totalCount < 1)
			return new Page<GroupRoleViewData>();
		
	//	System.out.println(t_group_id);

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<GroupRoleViewData> data = PageQuery(t_group_id, pageNo, pageSize);

		return new Page<GroupRoleViewData>(startIndex, totalCount, pageSize,
				data);

	}

}
