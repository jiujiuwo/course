package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.Department;
import com.mathtop.course.domain.Group;
import com.mathtop.course.domain.UserGroup;
import com.mathtop.course.domain.UserGroupViewData;
import com.mathtop.course.domain.School;
import com.mathtop.course.domain.Student;
import com.mathtop.course.domain.Teacher;
import com.mathtop.course.domain.UserBasicInfo;
import com.mathtop.course.utility.GUID;

@Repository
public class UserGroupDao extends BaseDao<UserGroupDao> {

	@Autowired
	UserBasicInfoDao userbasicinfoDao;
	
	@Autowired
	GroupDao groupDao;

	@Autowired
	TeacherDao teacherDao;

	@Autowired
	StudentDao studentDao;

	@Autowired
	DepartmentTeacherDao departmentteacherDao;

	@Autowired
	DepartmentDao departmentDao;

	@Autowired
	SchoolDao schoolDao;

	@Autowired
	SchoolDepartmentDao schooldepartmentDao;

	@Autowired
	NaturalClassStudentDao naturalclassstudentDao;

	private final String GET_GROUP_BY_USER_ID_NOTPAGED = "SELECT t_group_id FROM t_user_group WHERE t_user_id=?";
	private final String GET_USERGROUP_BY_USER_ID_NOTPAGED = "SELECT id,t_group_id FROM t_user_group WHERE t_user_id=?";
	private final String GET_USERGROUP_BY_GROUP_ID_NOTPAGED = "SELECT id,t_user_id FROM t_user_group WHERE t_group_id=?";
	private final String GET_USERGROUP_BY_GROUP_ID = "SELECT id,t_user_id FROM t_user_group WHERE t_group_id=? limit ?,?";
	private final String GET_t_user_id_COUNT_BY_GROUP_ID = "SELECT count(*) FROM t_user_group WHERE t_group_id=?";
	private final String GET_GROUPID_COUNT_BY_USER_ID = "SELECT count(*) FROM t_user_group WHERE t_user_id=?";
	
	private final String DELETE_USER_GROUP_BY_ID = "DELETE FROM t_user_group WHERE id=?";
	private final String DELETE_USER_GROUP_BY_USER_ID = "DELETE FROM t_user_group WHERE t_user_id=?";
	private final String DELETE_USER_GROUP_BY_GROUP_ID = "DELETE FROM t_user_group WHERE t_group_id=?";
	
	private final String INSERT_USER_GROUP = "INSERT INTO t_user_group(id,t_group_id,t_user_id) VALUES(?,?,?)";

	/**
	 * 添加用户-组
	 * */
	public String add(String t_group_id, String t_user_id) {
		String id = GUID.getGUID();
		Object params[] = new Object[] { id, t_group_id, t_user_id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(INSERT_USER_GROUP, params, types);
		return id;
	}
	
	
	public void update( String t_user_id,String[] groupId) {
		if(groupId==null)
			return;
		
		deleteByUserId(t_user_id);
		
		for(String g:groupId){
			add(g,t_user_id);
		}
	}
	
	
	/*
	 * 
	 */
	public void deleteById(String id) {

		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR};
		getJdbcTemplate().update(DELETE_USER_GROUP_BY_ID, params, types);
		
	}
	
	/*
	 * 
	 */
	public void deleteByUserId(String t_user_id) {

		Object params[] = new Object[] { t_user_id };
		int types[] = new int[] { Types.VARCHAR};
		getJdbcTemplate().update(DELETE_USER_GROUP_BY_USER_ID, params, types);
		
	}
	
	
	/*
	 * 
	 */
	public void deleteByGroupId(String t_group_id) {

		Object params[] = new Object[] { t_group_id };
		int types[] = new int[] { Types.VARCHAR};
		getJdbcTemplate().update(DELETE_USER_GROUP_BY_GROUP_ID, params, types);
		
	}
	
	/**
	 * 根据人得到所有组
	 * */
	public List<Group> getGroupByt_user_id(String t_user_id) {
		
		
		
		List<Group> list = new ArrayList<Group>();

		getJdbcTemplate().query(GET_GROUP_BY_USER_ID_NOTPAGED,
				new Object[] { t_user_id},
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Group g =  groupDao.getByID(rs.getString("t_group_id"));
						list.add(g);
					//	System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}
	
	
	/**
	 * 根据人得到所有组
	 * */
	public List<UserGroup> getUserGroupByt_user_id(String t_user_id) {
		
		
		
		List<UserGroup> list = new ArrayList<UserGroup>();

		getJdbcTemplate().query(GET_USERGROUP_BY_USER_ID_NOTPAGED,
				new Object[] { t_user_id},
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						UserGroup ug = new UserGroup();
						ug.setId(rs.getString("id"));
						ug.setT_group_id(rs.getString("t_group_id"));
						ug.setT_user_id(t_user_id);
						list.add(ug);
					//	System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}
	
	/**
	 * 根据组得到所有人
	 * */
	public List<UserGroup> getUserGroupByGroupId(String t_group_id) {
		
		
		
		List<UserGroup> list = new ArrayList<UserGroup>();

		getJdbcTemplate().query(GET_USERGROUP_BY_GROUP_ID_NOTPAGED,
				new Object[] { t_group_id},
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						UserGroup ug = new UserGroup();
						ug.setId(rs.getString("id"));
						ug.setT_group_id(t_group_id);
						ug.setT_user_id(rs.getString("t_user_id"));
						list.add(ug);
					//	System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}

	private List<UserGroup> getUserGroupByGroupId(String t_group_id,
			int PageBegin, int PageSize) {
		
		PageBegin-=1;
		if(PageBegin<0)
			PageBegin=0;
		
		List<UserGroup> list = new ArrayList<UserGroup>();

		getJdbcTemplate().query(GET_USERGROUP_BY_GROUP_ID,
				new Object[] { t_group_id, PageBegin * PageSize, PageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						UserGroup ug = new UserGroup();
						ug.setId(rs.getString("id"));
						ug.setT_group_id(t_group_id);
						ug.setT_user_id(rs.getString("t_user_id"));
						list.add(ug);
					//	System.out.println(rs.getString("t_user_id"));
					}

				});
		return list;
	}

	/*
	 * 
	 */
	private List<UserGroupViewData> PageQuery(String t_group_id, int PageBegin,
			int PageSize) {

		List<UserGroupViewData> list = new ArrayList<UserGroupViewData>();

		List<UserGroup> paget_user_ids = getUserGroupByGroupId(t_group_id, PageBegin,
				PageSize);

		if (paget_user_ids != null) {
			
		//	System.out.println(paget_user_ids.size());
			
			for (UserGroup ug : paget_user_ids) {
				
			//	System.out.println(ug.getT_user_id());

				UserGroupViewData gpi = new UserGroupViewData();

				// 基本信息
				UserBasicInfo ubi = userbasicinfoDao
						.getUserBasicInfoByt_user_id(ug.getT_user_id());
				gpi.setUserbasicinfo(ubi);

				Teacher t = teacherDao.getTeacherByt_user_id(ug.getT_user_id());
				
				
				
				if (t != null) {
					// 说明是教师
					
					gpi.setTeacher(t);

					Department dept = departmentteacherDao
							.getDepartmentByTeacherId(t.getId());
					if (dept != null) {
							gpi.setDepartment(dept);
							School s = schooldepartmentDao.getSchoolByt_department_id(dept.getId());
							
								gpi.setSchool(s);
							
						}

					

				} else {
					// 说明是学生
					Student stu = studentDao.getStudentByt_user_id(ug
							.getT_user_id());
					
				//	System.out.println(stu);
				//	System.out.println(stu.getStudent_num());
					
					if (stu != null) {
						
						gpi.setStudent(stu);

						
							Department dept = naturalclassstudentDao
									.getDepartmentByStudentId(stu.getId());

							gpi.setDepartment(dept);

							School s = naturalclassstudentDao
									.getSchoolByStudentId(stu.getId());
							gpi.setSchool(s);

						

					}

				}

				list.add(gpi);

			}

		}
		return list;
	}

	long getCount(String t_group_id) {

		return getJdbcTemplate().queryForObject(GET_t_user_id_COUNT_BY_GROUP_ID,
				new Object[] { t_group_id }, new int[] { Types.VARCHAR },
				Long.class);
	}

	public Page<UserGroupViewData> getPageByGroupId(String t_group_id,
			int pageNo, int pageSize) {

		long totalCount = getCount(t_group_id);
		if (totalCount < 1)
			return new Page<UserGroupViewData>();
		
	//	System.out.println(t_group_id);

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<UserGroupViewData> data = PageQuery(t_group_id, pageNo, pageSize);

		return new Page<UserGroupViewData>(startIndex, totalCount, pageSize,
				data);

	}
	
	
	long getGroupCountByt_user_id(String t_user_id) {

		return getJdbcTemplate().queryForObject(GET_GROUPID_COUNT_BY_USER_ID,
				new Object[] { t_user_id }, new int[] { Types.VARCHAR },
				Long.class);
	}
	
	public Page<Group> getGroupPageByt_user_id(String t_user_id
			) {

		long totalCount = getGroupCountByt_user_id(t_user_id);
		if (totalCount < 1)
			return new Page<Group>();
		
	//	System.out.println(t_group_id);

		// 实际查询返回分页对象
		int startIndex = 0;

		List<Group> data = getGroupByt_user_id(t_user_id);

		return new Page<Group>(startIndex, totalCount, (int)totalCount,
				data);

	}
}
