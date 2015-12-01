package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.Student;
import com.mathtop.course.utility.GUID;

@Repository
public class StudentDao extends BaseDao<Student> {
	private final String GET_t_natural_class_id_BY_STUDENTID = "SELECT t_natural_class_id FROM t_natural_class_student WHERE t_student_id=?";
	private final String GET_STUDNENT_BY_t_user_id = "SELECT id,student_num FROM t_student WHERE t_user_id=?";
	private final String GET_STUDNENT_BY_ID = "SELECT t_user_id,student_num FROM t_student WHERE id=?";
	private final String GET_STUDNENT_BY_STUDENTNUM = "SELECT id,t_user_id FROM t_student WHERE student_num=?";
	private final String INSERT_STUDNENT = "INSERT INTO t_student(id,t_user_id,student_num) VALUES(?,?,?)";
	private final String INSERT_NATURALCLASSSTUDENT = "INSERT INTO t_natural_class_student(id,t_natural_class_id,t_student_id) VALUES(?,?,?)";
	private final String DELETE_BY_ID = "DELETE FROM t_student WHERE id=?";
	private final String UPDATE_STUDENTNUM_BY_ID = "update t_student set student_num=? WHERE id=?";
	
	public void UpdateStudentNumById(String t_student_id, String student_num) {
		if (t_student_id == null || student_num == null )
			return;	

		Object params[] = new Object[] {student_num, t_student_id };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_STUDENTNUM_BY_ID, params, types);
	}
	
	/*删除
	 * */
	public void deleteById(String t_student_id){		
		Object params[]=new Object[]{t_student_id};
		int types[]=new int[]{Types.VARCHAR};
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}
	
	/**根据 id得到所在自然班id
	 * */
	public String gett_natural_class_idByStudentId(String studentid){
	//	System.out.println( studentid);
		return getJdbcTemplate().queryForObject(
				GET_t_natural_class_id_BY_STUDENTID,
				new Object[] { studentid },
				new int[] { Types.VARCHAR}, String.class);
	}
	
	
	/*根据用户ID得到用户
	 * */
	public Student getStudentByID(String id){
		
		Student stu=new Student();
		
		
		
		getJdbcTemplate().query(GET_STUDNENT_BY_ID, new Object[]{id},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
			
				stu.setId(id);	
				stu.setStudent_num(rs.getString("student_num"));
				stu.setT_user_id(rs.getString("t_user_id"));
			
			}
			
		});
		if(stu.getId()==null)
			return null;
		return stu;
	}
	
	/*根据学号ID得到用户
	 * */
	public Student getStudentByStudentNum(String student_num){
		
		Student stu=new Student();
		
		
		getJdbcTemplate().query(GET_STUDNENT_BY_STUDENTNUM, new Object[]{student_num},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				stu.setId(rs.getString("id"));	
				stu.setStudent_num(student_num);				
				stu.setT_user_id(rs.getString("t_user_id"));
				
				
				
				
			}
			
		});
		
		if(stu.getId()==null)
			return null;
		return stu;
	}
	
	/*根据t_user_id得到用户
	 * */
	public Student getStudentByt_user_id(String t_user_id){
		
		Student stu=new Student();
		
		
		getJdbcTemplate().query(GET_STUDNENT_BY_t_user_id, new Object[]{t_user_id},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				stu.setId(rs.getString("id"));	
				stu.setT_user_id(t_user_id);
				stu.setStudent_num(rs.getString("student_num"));
				stu.setNatural_class_id(gett_natural_class_idByStudentId(stu.getId()));
				
			}
			
		});
		
		if(stu.getId()==null)
			return null;
		return stu;
	}
	
	/*增加*/
	public String add(Student stu){
		String id=GUID.getGUID();
		stu.setId(id);
		Object params[]=new Object[]{id,stu.getT_user_id(),stu.getStudent_num()};
		int types[]=new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		getJdbcTemplate().update(INSERT_STUDNENT, params, types);
		
		
		
		Object params1[]=new Object[]{GUID.getGUID(),stu.getNatural_class_id(),id};
		int types1[]=new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		getJdbcTemplate().update(INSERT_NATURALCLASSSTUDENT, params1, types1);
		
		return id;
	}
	
}
