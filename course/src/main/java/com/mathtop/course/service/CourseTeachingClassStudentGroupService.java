package com.mathtop.course.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.CourseTeachingClassStudentGroupDao;
import com.mathtop.course.domain.CourseTeachingClassStudentGroup;
import com.mathtop.course.domain.CourseTeachingClassStudentGroupStatisticsViewData;
import com.mathtop.course.domain.CourseTeachingClassStudentGroupViewData;
import com.mathtop.course.domain.Group;
import com.mathtop.course.domain.Student;
import com.mathtop.course.domain.StudentViewData;
import com.mathtop.course.domain.UserGroup;

@Service
public class CourseTeachingClassStudentGroupService {
	@Autowired
	CourseTeachingClassStudentGroupDao courseTeachingClassStudentGroupDao;

	@Autowired
	GroupService groupService;

	@Autowired
	UserGroupService userGroupService;

	@Autowired
	GroupRoleService groupRoleService;

	@Autowired
	private StudentService studentService;

	/**
	 * 添加
	 */
	public void add(String t_course_teaching_class_id, String t_group_id) {

		if (t_course_teaching_class_id == null || t_group_id == null)
			return;

		int show_index = courseTeachingClassStudentGroupDao
				.getShowIndexMaxByCourseTeachingClassId(t_course_teaching_class_id);
		show_index += 10;
		if (courseTeachingClassStudentGroupDao.IsGroupExist(t_course_teaching_class_id, t_group_id)) {

			courseTeachingClassStudentGroupDao.update(t_course_teaching_class_id, t_group_id, show_index);

		} else
			courseTeachingClassStudentGroupDao.add(t_course_teaching_class_id, t_group_id, show_index);

	}

	/**
	 * 添加
	 */
	public void addGroup(String t_course_teaching_class_id, String group_name, String group_note) {
		if (t_course_teaching_class_id == null || group_name == null)
			return;

		String t_group_id = groupService.add(group_name, group_note);
		if (t_group_id == null)
			return;

		add(t_course_teaching_class_id, t_group_id);

	}

	public void deleteById(String id) {
		courseTeachingClassStudentGroupDao.deleteById(id);
	}

	public void deleteByCourseTeachingClassId(String t_course_teaching_class_id) {
		courseTeachingClassStudentGroupDao.deleteByCourseTeachingClassId(t_course_teaching_class_id);
	}

	/**
	 * 根据组删除
	 */
	public void deleteByGroupId(String t_group_id) {
		courseTeachingClassStudentGroupDao.deleteByGroupId(t_group_id);
	}

	/**
	 * 删除
	 */
	public void deleteGroup(String t_course_teaching_class_id, String t_course_teaching_class_student_group_id) {
		if (t_course_teaching_class_id == null || t_course_teaching_class_student_group_id == null)
			return;

		CourseTeachingClassStudentGroup data = courseTeachingClassStudentGroupDao
				.getById(t_course_teaching_class_student_group_id);

		if (data == null)
			return;

		if (!data.getCourseTeachingClassId().equals(t_course_teaching_class_id))
			return;

		groupService.deleteById(data.getGroupId());

	}

	public void ShowIndexMoveUp(String t_course_teaching_class_id, String t_group_id) {

		// 1.得到该学生
		CourseTeachingClassStudentGroup stu = courseTeachingClassStudentGroupDao
				.getByTeachingClassIdAndGroupId(t_course_teaching_class_id, t_group_id);
		if (stu == null)
			return;

		// 2.得到前面一个学生
		int show_index_max = courseTeachingClassStudentGroupDao
				.getShowIndexMaxLessthanByCourseTeachingClassId(t_course_teaching_class_id, t_group_id);
		CourseTeachingClassStudentGroup pre = courseTeachingClassStudentGroupDao
				.getByTeachingClassIdAndShowIndex(t_course_teaching_class_id, show_index_max);
		if (pre == null)
			return;

		int temp = stu.getShowIndex();
		courseTeachingClassStudentGroupDao.update(stu.getId(), show_index_max);
		courseTeachingClassStudentGroupDao.update(pre.getId(), temp);

	}

	public void ShowIndexMoveDown(String t_course_teaching_class_id, String t_group_id) {
		// 1.得到该学生
		CourseTeachingClassStudentGroup stu = courseTeachingClassStudentGroupDao
				.getByTeachingClassIdAndGroupId(t_course_teaching_class_id, t_group_id);
		if (stu == null)
			return;

		// 2.得到后面一个学生
		int show_index_min = courseTeachingClassStudentGroupDao
				.getShowIndexMinMorethanByCourseTeachingClassId(t_course_teaching_class_id, t_group_id);
		CourseTeachingClassStudentGroup next = courseTeachingClassStudentGroupDao
				.getByTeachingClassIdAndShowIndex(t_course_teaching_class_id, show_index_min);
		if (next == null)
			return;

		int temp = stu.getShowIndex();
		courseTeachingClassStudentGroupDao.update(stu.getId(), show_index_min);
		courseTeachingClassStudentGroupDao.update(next.getId(), temp);

	}
	
	private CourseTeachingClassStudentGroupViewData getViewData(CourseTeachingClassStudentGroup g){
		if(g==null)
			return null;
		
		CourseTeachingClassStudentGroupViewData data = new CourseTeachingClassStudentGroupViewData();
		data.setCourseTeachingClassStudentGroup(g);

		String t_group_id = g.getGroupId();
		Group group = groupService.getByID(t_group_id);
		data.setGroup(group);

		int nUserCount = (int) userGroupService.getCount(t_group_id);
		List<UserGroup> listUser = userGroupService.getUserGroupByGroupId(t_group_id);
		if (listUser != null && nUserCount > 0 && listUser.size() == nUserCount) {
			StudentViewData[] studentViewDatas = new StudentViewData[nUserCount];

			for (int i = 0; i < nUserCount; i++)
				studentViewDatas[i] = studentService.getStudentViewByUserId(listUser.get(i).getUserId());

			data.setStudentViewDatas(studentViewDatas);
		}
		return data;
	}
	
	public CourseTeachingClassStudentGroupViewData getViewDataByStudentId(String t_course_teaching_class_id,String t_student_id){
		Student student=studentService.getStudentByStudentId(t_student_id);
		if(student==null)
			return null;
		
		
		CourseTeachingClassStudentGroup courseTeachingClassStudentGroup =courseTeachingClassStudentGroupDao.getByTeachingClassIdAndUserId(t_course_teaching_class_id, student.getUserId());
		
		if(courseTeachingClassStudentGroup==null)
			return null;
		return getViewData(courseTeachingClassStudentGroup);
		

	}

	public List<CourseTeachingClassStudentGroupViewData> getViewData(String t_course_teaching_class_id) {
		if (t_course_teaching_class_id == null)
			return null;

		List<CourseTeachingClassStudentGroup> list = courseTeachingClassStudentGroupDao
				.getByCourseTeachingClassId(t_course_teaching_class_id);
		if (list == null)
			return null;
		if (list.size() == 0)
			return null;

		List<CourseTeachingClassStudentGroupViewData> result = new ArrayList<>();
		for (CourseTeachingClassStudentGroup g : list) {
			CourseTeachingClassStudentGroupViewData data = getViewData(g);
			if(data!=null)
			result.add(data);
		}

		return result;
	}

	/**
	 * 得到所有未分组的学生
	 */
	public List<StudentViewData> getNotGroupedStudent(String t_course_teaching_class_id) {
		return studentService.getNotGroupedStudent(t_course_teaching_class_id);
	}

	/**
	 * 得到特定分组的学生
	 */
	public List<StudentViewData> getGroupedStudent(String t_course_teaching_class_id, String t_group_id) {
		CourseTeachingClassStudentGroup g = courseTeachingClassStudentGroupDao
				.getByTeachingClassIdAndGroupId(t_course_teaching_class_id, t_group_id);

		if (g == null)
			return null;

		List<UserGroup> list = userGroupService.getUserGroupByGroupId(g.getGroupId());

		List<StudentViewData> result = new ArrayList<>();
		for (UserGroup u : list) {
			result.add(studentService.getStudentViewByUserId(u.getUserId()));
		}
		return result;

	}

	public void AddStudent2Group(String t_course_teaching_class_id, String t_group_id, String[] userids) {
		if (userids == null || userids.length == 0)
			return;
		if (t_course_teaching_class_id == null)
			return;
		if (t_group_id == null)
			return;
		for (String s : userids) {
			if (s != null && s.length() > 0) {

				userGroupService.add(t_group_id, s);
			}
		}

	}

	public void DeleteStudentFromGroup(String t_course_teaching_class_id, String t_group_id, String t_user_id) {
		if (t_user_id == null)
			return;
		if (t_course_teaching_class_id == null)
			return;
		if (t_group_id == null)
			return;

		userGroupService.deleteByGroupIdAndUserId(t_group_id, t_user_id);

	}
	
	/**
	 * 获得分组数量
	 * */
	public int getGroupCountByCourseTeachingClassId(String t_course_teaching_class_id) {
		return courseTeachingClassStudentGroupDao.getGroupCountByCourseTeachingClassId(t_course_teaching_class_id);
	}
	
	/**
	 * 是否分组
	 * */
	public boolean isGrouped(String t_course_teaching_class_id){
		return courseTeachingClassStudentGroupDao.getGroupCountByCourseTeachingClassId(t_course_teaching_class_id)>0;
	}
	
	public CourseTeachingClassStudentGroupStatisticsViewData getGroupInfo(String t_course_teaching_class_id){
		if(t_course_teaching_class_id==null)
			return null;
		
		CourseTeachingClassStudentGroupStatisticsViewData data=new CourseTeachingClassStudentGroupStatisticsViewData();
		data.setnGroupCount(courseTeachingClassStudentGroupDao.getGroupCountByCourseTeachingClassId(t_course_teaching_class_id));
		return data;
		
		
	}
}
