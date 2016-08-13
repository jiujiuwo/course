package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.mathtop.course.domain.Simple;
import com.mathtop.course.utility.GUID;

/*
 * 对象只能包含id,name,note三个成员
 * */

public class SimpleDao<TDomain extends Simple>  extends BaseDao<TDomain>{
	
	private Class<TDomain> classTDomain;
	
	
	
	private String INSERT = "INSERT INTO @(id,name,note) VALUES(?,?,?)";
	private String GET_BY_NAME = "SELECT id,note FROM @ WHERE name=?";
	private String GET_BY_ID = "SELECT name,note FROM @ WHERE id=?";
	private String GET_ALL = "SELECT id,name,note FROM @";
	private String GET_PAGED = "select a.id,a.name,a.note from @ as a inner join (select id from @ limit ?, ?) as b on a.id = b.id ";
	private String GET_COUNT = "select count(*) from @";
	private String DELETE_BY_ID = "DELETE FROM @ WHERE id=?";
	private String UPDATE_BY_ID = "update @ set name=?,note=? WHERE id=?";
	private String SELECT_BY_NAME = "select id,name,note FROM @ WHERE name like ?";
	
	private String tableName;
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {		
		this.tableName = tableName;		
		INSERT=INSERT.replaceAll("@", tableName);
		GET_BY_NAME=GET_BY_NAME.replaceAll("@", tableName);
		GET_BY_ID=GET_BY_ID.replaceAll("@", tableName);
		GET_ALL=GET_ALL.replaceAll("@", tableName);
		GET_PAGED=GET_PAGED.replaceAll("@", tableName);
		GET_COUNT=GET_COUNT.replaceAll("@", tableName);
		DELETE_BY_ID=DELETE_BY_ID.replaceAll("@", tableName);
		UPDATE_BY_ID=UPDATE_BY_ID.replaceAll("@", tableName);
		SELECT_BY_NAME=SELECT_BY_NAME.replaceAll("@", tableName);		
	}

	SimpleDao(Class<TDomain> classTDomain,String tablename){
		this.classTDomain=classTDomain;
		setTableName(tablename);
	}
	
	private TDomain getnewInstance(){
		try {
			return classTDomain.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	
	
	
	/*增加用户*/
	public String add(Simple t){
		
		if(t==null)
			return "";
		
		String newid=GUID.getGUID();
		t.setId(newid);
		Object params[]=new Object[]{t.getId(),t.getName(),t.getNote()};
		int types[]=new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		getJdbcTemplate().update(INSERT, params, types);
		return newid;
	}
	
	/*添加
	 * */
	public String add(String name,String note){		
		String newid=GUID.getGUID();
		Object params[]=new Object[]{newid,name.trim(),note};
		int types[]=new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		getJdbcTemplate().update(INSERT, params, types);
		return newid;
	}
	
	/*删除
	 * */
	public void deleteById(String id){		
		Object params[]=new Object[]{id};
		int types[]=new int[]{Types.VARCHAR};
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}
	
	/*更新
	 * */
	public void update(String id,String name,String note){
		if(id==null || name==null)
			return;		
		
		Object params[]=new Object[]{name.trim(),note,id};
		int types[]=new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}
	
	/*查找
	 * */
	public List<TDomain> select(String name){
		
		if( name==null || name.length()==0)
			return getAll();	
		
		name="%"+name.trim()+"%";
		
		List<TDomain> list=new ArrayList<TDomain>();
		Object params[]=new Object[]{name};
		int types[]=new int[]{Types.VARCHAR};
		
		getJdbcTemplate().query(SELECT_BY_NAME, params,types,new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				TDomain d= getnewInstance();
				d.setId(rs.getString("id"));	
				d.setName(rs.getString("name"));
				d.setNote(rs.getString("note"));
				list.add(d);
				
			}
			
		});
		
		return list;
	}
	
	
	
	/*根据用户名得到用户
	 * */
	public TDomain getByName(String userName){

		TDomain d= getnewInstance();
		
		
		getJdbcTemplate().query(GET_BY_NAME, new Object[]{userName},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				d.setId(rs.getString("id"));				
				d.setName(userName);
				d.setNote(rs.getString("note"));
				
			}
			
		});
		
		
	
		
	//	System.out.println(user.getUser_password());
		if(d.getId()==null)
			return null;
		
		return d;
	}
	
	/*根据用户ID得到用户
	 * */
	public TDomain getByID(String id){
		
		TDomain s= getnewInstance();
		
		
		getJdbcTemplate().query(GET_BY_ID, new Object[]{id},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				s.setId(id);	
				s.setName(rs.getString("name"));
				s.setNote(rs.getString("note"));
				
			}
			
		});
		
		return s;
	}
	
	
	/*
	 * 返回所有结果
	 * */
	public List<TDomain> getAll(){
		
		List<TDomain> list=new ArrayList<TDomain>();
		getJdbcTemplate().query(GET_ALL, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				TDomain s= getnewInstance();
				s.setId(rs.getString("id"));	
				s.setName(rs.getString("name"));
				s.setNote(rs.getString("note"));
				list.add(s);				
			}			
		});
		
		return list;
	}

	
	
	/*
	 * 得到学院总数
	 * */
	long getCount(){
		return getJdbcTemplate().queryForObject(GET_COUNT,Long.class);
	}
	
	
	/*
	 * 分页查询
	 * @PageBegin 开始页面，从0开始
	 * */
	public List<TDomain> PageQuery(int PageBegin,int PageSize){
		
		
		
		List<TDomain> list=new ArrayList<TDomain>();
		getJdbcTemplate().query(GET_PAGED, new Object[]{PageBegin*PageSize,PageSize},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				TDomain s= getnewInstance();
				s.setId(rs.getString("id"));	
				s.setName(rs.getString("name"));
				s.setNote(rs.getString("note"));
				list.add(s);
				
			}
			
		});
		
		
		
		return list;
	}
	
	
	/**
	 * 获取学院
	 * @param pageNo 页号，从1开始。
	 * @param pageSize 每页的记录数
	 * @return 包含分页信息的Page对象
	 */
	public Page<TDomain> getPage(int pageNo,int pageSize) {
		long totalCount=getCount();
		if (totalCount < 1)
			return new Page<TDomain>();
		
		
		//实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		
		List<TDomain> data=PageQuery(pageNo-1,pageSize);
		
		return new Page<TDomain>(startIndex, totalCount, pageSize,data );
		
	}
	
	public Page<TDomain> getAllPage() {
		long totalCount=getCount();
		if (totalCount < 1)
			return new Page<TDomain>();
		
		
		List<TDomain> data=getAll();
		
		return new Page<TDomain>(0, totalCount, (int)totalCount,data );
		
	}
	
	public Page<TDomain> select(String name,int pageNo,int pageSize){
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		 List<TDomain> data=select(name);
		 return new Page<TDomain>(startIndex, data.size(), pageSize,data );
	   }
}
