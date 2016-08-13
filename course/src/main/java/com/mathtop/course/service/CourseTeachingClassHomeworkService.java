package com.mathtop.course.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mathtop.course.cons.RealPathConst;
import com.mathtop.course.dao.CourseTeachingClassDao;
import com.mathtop.course.dao.CourseTeachingClassHomeworkBaseinfoDao;
import com.mathtop.course.dao.CourseTeachingClassHomeworkDelayedDao;
import com.mathtop.course.dao.CourseTeachingClassHomeworkFileDao;
import com.mathtop.course.dao.CourseTeachingClassHomeworkSubmitBaseinfoDao;
import com.mathtop.course.dao.CourseTeachingClassHomeworkTypeDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.dao.TeacherViewDataDao;
import com.mathtop.course.domain.CourseTeachingClass;
import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfo;
import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfoStudentViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfoViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkDelayed;
import com.mathtop.course.domain.CourseTeachingClassHomeworkFile;
import com.mathtop.course.domain.CourseTeachingClassHomeworkType;
import com.mathtop.course.domain.CourseTeachingClassStudentGroupViewData;
import com.mathtop.course.domain.StudentViewData;
import com.mathtop.course.domain.TeacherViewData;
import com.mathtop.course.utility.DateTimeSql;
import com.mathtop.course.utility.GUID;

@Service
public class CourseTeachingClassHomeworkService {

	@Autowired
	CourseTeachingClassDao courseteachingclassDao;

	@Autowired
	TeacherViewDataDao teacherviewdataDao;

	@Autowired
	CourseTeachingClassHomeworkTypeDao courseTeachingClassHomeworkTypeDao;

	@Autowired
	CourseTeachingClassHomeworkBaseinfoDao homeworkbaseinfodao;

	@Autowired
	CourseTeachingClassHomeworkFileDao homeworkfileDao;

	@Autowired
	CourseTeachingClassHomeworkSubmitBaseinfoDao submitDao;

	@Autowired
	CourseTeachingClassHomeworkDelayedDao courseTeachingClassHomeworkDelayedDao;

	@Autowired
	CourseTeachingClassHomeworkSubmitService courseTeachingClassHomeworkSubmitService;

	@Autowired
	CourseTeachingClassStudentGroupService courseTeachingClassStudentGroupService;

	public CourseTeachingClassHomeworkFile getFileByID(String id) {
		return homeworkfileDao.getByID(id);
	}

	private void addFiles(HttpServletRequest request, String t_course_teaching_class_homework_baseinfo_id,
			MultipartFile[] files) {

		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			if (file.isEmpty())
				continue;

			String filename = file.getOriginalFilename();

			String prefix = filename.substring(filename.lastIndexOf(".") + 1);

			ServletContext sc = request.getSession().getServletContext();
			String dir = sc.getRealPath(RealPathConst.RealPath_HomeworkFile); // 设定文件保存的目录

			prefix = prefix.toLowerCase();

			String idfilename = GUID.getGUID();

			String localfilename = dir + RealPathConst.RealPath_PathSeparator + idfilename + "." + prefix;
			String hreffilename = idfilename + "." + prefix;

			try {
				File localfile = new File(localfilename);

				if (localfile.exists())
					localfile.delete();

				file.transferTo(localfile);

				homeworkfileDao.add(t_course_teaching_class_homework_baseinfo_id, filename, hreffilename);

			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
	}

	// 增加
	public void add(HttpServletRequest request, String t_course_teaching_class_id,
			String t_course_teaching_class_homeworktype_id, String t_teacher_id, Integer flag, String file_requirement,
			String title, String content, String enddate, MultipartFile[] files) {

		String t_course_teaching_class_homework_baseinfo_id = homeworkbaseinfodao.add(t_course_teaching_class_id,
				t_teacher_id, t_course_teaching_class_homeworktype_id, flag, file_requirement, title, content,
				new Date(), DateTimeSql.GetDateTimeNotIncludingSecond(enddate));

		addFiles(request, t_course_teaching_class_homework_baseinfo_id, files);

	}

	/**
	 * 增加迟交作业
	 */
	public void addDelayed(HttpServletRequest request, String t_course_teaching_class_homework_baseinfo_id,
			String t_teacher_id, String enddate) {

		courseTeachingClassHomeworkDelayedDao.add(t_course_teaching_class_homework_baseinfo_id, t_teacher_id,
				new Date(), DateTimeSql.GetDateTimeNotIncludingSecond(enddate));

	}

	/**
	 * 删除id迟交作业
	 */
	public void deleteDelayedByID(HttpServletRequest request, String t_course_teaching_class_homework_delayed_id) {
		courseTeachingClassHomeworkDelayedDao.deleteById(t_course_teaching_class_homework_delayed_id);
	}

	/**
	 * 根据t_course_teaching_class_homework_baseinfo_id删除迟交作业
	 */
	public void deleteDelayedByCourseTeachingClassHomeworkBaseinfoId(HttpServletRequest request,
			String t_course_teaching_class_homework_baseinfo_id) {
		courseTeachingClassHomeworkDelayedDao
				.deleteByCourseTeachingClassHomeworkBaseInfoId(t_course_teaching_class_homework_baseinfo_id);
	}

	public void deleteDelayedByTeacherId(HttpServletRequest request, String t_teacher_id) {
		courseTeachingClassHomeworkDelayedDao.deleteByTeacherId(t_teacher_id);
	}

	public void updateDelayed(HttpServletRequest request, String t_course_teaching_class_homework_delayed_id,
			String t_course_teaching_class_homework_baseinfo_id, String t_teacher_id, String enddate) {

		courseTeachingClassHomeworkDelayedDao.update(t_course_teaching_class_homework_delayed_id,
				t_course_teaching_class_homework_baseinfo_id, t_teacher_id, new Date(),
				DateTimeSql.GetDateTimeNotIncludingSecond(enddate));

	}

	public void updateDelayed(HttpServletRequest request, String t_course_teaching_class_homework_delayed_id,
			String t_teacher_id, String enddate) {

		courseTeachingClassHomeworkDelayedDao.update(t_course_teaching_class_homework_delayed_id, t_teacher_id,
				new Date(), DateTimeSql.GetDateTimeNotIncludingSecond(enddate));

	}

	public void updateDelayed(HttpServletRequest request, String t_course_teaching_class_homework_delayed_id,
			String enddate) {

		courseTeachingClassHomeworkDelayedDao.update(t_course_teaching_class_homework_delayed_id,
				DateTimeSql.GetDateTimeNotIncludingSecond(enddate));

	}

	// 删除
	public void deleteByID(HttpServletRequest request, String t_course_teaching_class_homework_baseinfo_id) {

		// 删除迟交作业
		deleteDelayedByCourseTeachingClassHomeworkBaseinfoId(request, t_course_teaching_class_homework_baseinfo_id);

		// 删除提交作业
		courseTeachingClassHomeworkSubmitService.deleteByCourseTeachingClassHomeworkBaseinfoID(request,
				t_course_teaching_class_homework_baseinfo_id);

		// 删除基本信息及其附件文件
		deleteBaseInfoByID(request, t_course_teaching_class_homework_baseinfo_id);
	}

	public void deleteByTeacherId(HttpServletRequest request, String t_teacher_id) {
		List<CourseTeachingClassHomeworkBaseinfo> list = homeworkbaseinfodao.getByTeacherID(t_teacher_id);
		if (list == null)
			return;
		for (CourseTeachingClassHomeworkBaseinfo h : list) {
			deleteByID(request, h.getId());
		}
	}

	public void deleteByCourseTeachingClassId(HttpServletRequest request, String t_course_teaching_class_id) {
		List<CourseTeachingClassHomeworkBaseinfo> list = homeworkbaseinfodao
				.getByCourseTeachingClassID(t_course_teaching_class_id);
		if (list == null)
			return;
		for (CourseTeachingClassHomeworkBaseinfo h : list) {
			deleteByID(request, h.getId());
		}
	}

	public void deleteByCourseTeachingClassHomeworkTypeId(HttpServletRequest request,
			String t_course_teaching_class_homework_type_id) {
		List<CourseTeachingClassHomeworkBaseinfo> list = homeworkbaseinfodao
				.getByHomeWorkTypeID(t_course_teaching_class_homework_type_id);
		if (list == null)
			return;
		for (CourseTeachingClassHomeworkBaseinfo h : list) {
			deleteByID(request, h.getId());
		}
	}

	// 删除基本信息及其附件文件
	private void deleteBaseInfoByID(HttpServletRequest request, String t_course_teaching_class_homework_baseinfo_id) {
		CourseTeachingClassHomeworkBaseinfo plan = getByID(t_course_teaching_class_homework_baseinfo_id);
		if (plan == null)
			return;

		// 1.删除文件
		deleteFilesByHomeworkBaseInfoId(request, t_course_teaching_class_homework_baseinfo_id);

		// 2.删除基本信息
		homeworkbaseinfodao.deleteById(t_course_teaching_class_homework_baseinfo_id);
	}

	// 删除文件
	private void deleteFilesByHomeworkBaseInfoId(HttpServletRequest request,
			String t_course_teaching_class_homework_baseinfo_id) {
		ServletContext sc = request.getSession().getServletContext();
		String dir = sc.getRealPath(RealPathConst.RealPath_HomeworkFile); // 设定文件保存的目录

		List<CourseTeachingClassHomeworkFile> list = homeworkfileDao
				.getByCourseTeachingClassHomeworkBaseInfoID(t_course_teaching_class_homework_baseinfo_id);
		if (list != null) {

			for (CourseTeachingClassHomeworkFile homeworkfile : list) {
				String path = dir + RealPathConst.RealPath_PathSeparator + homeworkfile.getFilepath();

				File localfile = new File(path);

				if (localfile.exists())
					localfile.delete();

				homeworkfileDao.deleteById(homeworkfile.getId());

			}
		}
	}

	public void Update(HttpServletRequest request, String t_course_teaching_class_homework_baseinfo_id,
			String t_teacher_id, Integer flag, String file_requirement, String title, String content, String enddate,
			MultipartFile[] files) {

		// 1.删除文件
		deleteFilesByHomeworkBaseInfoId(request, t_course_teaching_class_homework_baseinfo_id);

		// 2.更改基本信息
		homeworkbaseinfodao.update(t_course_teaching_class_homework_baseinfo_id, t_teacher_id, flag, file_requirement,
				title, content, DateTimeSql.GetDateTimeNotIncludingSecond(enddate));

		// 3.增加文件
		addFiles(request, t_course_teaching_class_homework_baseinfo_id, files);
	}

	public void update(HttpServletRequest request, String t_course_teaching_class_homework_baseinfo_id,
			String t_teacher_id, String title, String content, String enddate) {

		// 2.更改基本信息
		homeworkbaseinfodao.update(t_course_teaching_class_homework_baseinfo_id, t_teacher_id, title, content,
				DateTimeSql.GetDateTimeNotIncludingSecond(enddate));

	}

	public CourseTeachingClassHomeworkBaseinfo getByID(String id) {
		return homeworkbaseinfodao.getByID(id);
	}

	public List<CourseTeachingClassHomeworkBaseinfo> getByCourseTeachingClassID(String t_course_teaching_class_id) {
		return homeworkbaseinfodao.getByCourseTeachingClassID(t_course_teaching_class_id);
	}

	public Page<CourseTeachingClassHomeworkBaseinfoStudentViewData> getPage(String t_course_teaching_class_id,
			String t_course_teaching_class_homeworktype_id, String t_student_id, int pageNo, int pageSize) {

		Page<CourseTeachingClassHomeworkBaseinfoViewData> page = getPage(t_course_teaching_class_id,
				t_course_teaching_class_homeworktype_id, pageNo, pageSize);
		List<CourseTeachingClassHomeworkBaseinfoViewData> listtemp = page.getResult();

		Page<CourseTeachingClassHomeworkBaseinfoStudentViewData> result = null;
		if (listtemp != null) {
			List<CourseTeachingClassHomeworkBaseinfoStudentViewData> list = new ArrayList<CourseTeachingClassHomeworkBaseinfoStudentViewData>();
			for (CourseTeachingClassHomeworkBaseinfoViewData d : listtemp) {
				CourseTeachingClassHomeworkBaseinfoStudentViewData s = getCourseTeachingClassHomeworkBaseinfoStudentViewData(
						d, t_student_id);
				if (s != null)
					list.add(s);
			}

			result = new Page<CourseTeachingClassHomeworkBaseinfoStudentViewData>(page.getStart(), page.getTotalCount(),
					page.getPageSize(), list);
		}

		return result;
	}

	public CourseTeachingClassHomeworkBaseinfoStudentViewData getCourseTeachingClassHomeworkBaseinfoStudentViewData(
			CourseTeachingClassHomeworkBaseinfoViewData data, String t_student_id) {

		if (data == null || t_student_id == null)
			return null;

		String t_course_teaching_class_id = data.getCourseteachingclass().getId();

		CourseTeachingClassHomeworkBaseinfoStudentViewData s = new CourseTeachingClassHomeworkBaseinfoStudentViewData(
				data);

		String t_course_teaching_class_homework_baseinfo_id = data.getHomeworkbaseinfo().getId();
		s.setStudentSubmitted(isStudentSubmitHomework(data, t_course_teaching_class_id,
				t_course_teaching_class_homework_baseinfo_id, t_student_id));
		return s;

	}

	/**
	 * 学生是否提交了作业？如果是个人作业，则只检查个人，否则检查小组其它人是否提交
	 */
	private boolean isStudentSubmitHomework(CourseTeachingClassHomeworkBaseinfoViewData data,
			String t_course_teaching_class_id, String t_course_teaching_class_homework_baseinfo_id,
			String t_student_id) {

		if (data == null)
			return false;

		if (data.getHomeworkbaseinfo().isFlagGroup()) {
			CourseTeachingClassStudentGroupViewData g = courseTeachingClassStudentGroupService
					.getViewDataByStudentId(t_course_teaching_class_id, t_student_id);
			if (g == null)
				return false;
			for (StudentViewData s : g.getStudentViewDatas()) {
				if (submitDao.getCount(t_course_teaching_class_homework_baseinfo_id, s.getStudent().getId()) > 0)
					return true;
			}
			return false;

		} else
			return submitDao.getCount(t_course_teaching_class_homework_baseinfo_id, t_student_id) > 0;
	}

	public void update(String id, String t_course_teaching_class_id, String t_teacher_id, String t_item_kinds_id,
			Integer flag, String file_requirement, String title, String content, Date pubdate, Date enddate,
			String filename, String filepath) {
		homeworkbaseinfodao.update(id, t_course_teaching_class_id, t_teacher_id, t_item_kinds_id, flag,
				file_requirement, title, content, pubdate, enddate);
		// homeworkfileDao.update(t_item_kinds_id,
		// t_course_teaching_class_homework_baseinfo_id, filename, filepath);
	}

	public CourseTeachingClassHomeworkBaseinfoViewData getCourseTeachingClassHomeworkBaseinfoViewDataByID(String id) {
		CourseTeachingClassHomeworkBaseinfoViewData data = new CourseTeachingClassHomeworkBaseinfoViewData();

		CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo = getByID(id);

		data.setHomeworkbaseinfo(homeworkbaseinfo);

		CourseTeachingClass courseteachingclass = courseteachingclassDao
				.getCourseTeachingClassById(homeworkbaseinfo.getCourseTeachingClassId());
		data.setCourseteachingclass(courseteachingclass);

		TeacherViewData teacherviewdata = teacherviewdataDao
				.getTeacherViewDataByTeacherId(homeworkbaseinfo.getTeacherId());
		data.setTeacher(teacherviewdata);

		CourseTeachingClassHomeworkType homeworkType = courseTeachingClassHomeworkTypeDao
				.getByID(homeworkbaseinfo.getCourseTeachingClassHomeworkTypeId());
		data.setHomeworkType(homeworkType);

		List<CourseTeachingClassHomeworkFile> homeworkFileList = homeworkfileDao
				.getByCourseTeachingClassHomeworkBaseInfoID(homeworkbaseinfo.getId());
		data.setHomeworkFileList(homeworkFileList);

		List<CourseTeachingClassHomeworkDelayed> homeworkDelayedList = courseTeachingClassHomeworkDelayedDao
				.getByCourseTeachingClassHomeworkBaseInfoID(id);
		data.setHomeworkDelayedList(homeworkDelayedList);
		
		
		return data;
	}

	private List<CourseTeachingClassHomeworkBaseinfoViewData> PageQuery(String t_course_teaching_class_id,
			String t_course_teaching_class_homeworktype_id, int PageBegin, int PageSize) {

		List<String> lstIds = homeworkbaseinfodao.PageQuery(t_course_teaching_class_id,
				t_course_teaching_class_homeworktype_id, PageBegin, PageSize);
		if (lstIds == null)
			return null;

		List<CourseTeachingClassHomeworkBaseinfoViewData> list = new ArrayList<CourseTeachingClassHomeworkBaseinfoViewData>();

		for (String id : lstIds) {

			CourseTeachingClassHomeworkBaseinfoViewData data = getCourseTeachingClassHomeworkBaseinfoViewDataByID(id);

			if (data != null)
				list.add(data);
			// System.out.println(rs.getString("t_user_id"));

		}
		return list;

	}

	public Page<CourseTeachingClassHomeworkBaseinfoViewData> getPage(String t_course_teaching_class_id,
			String t_course_teaching_class_homeworktype_id, int pageNo, int pageSize) {
		long totalCount = homeworkbaseinfodao.getCount(t_course_teaching_class_id,
				t_course_teaching_class_homeworktype_id);
		if (totalCount < 1)
			return new Page<CourseTeachingClassHomeworkBaseinfoViewData>();

		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List<CourseTeachingClassHomeworkBaseinfoViewData> data = PageQuery(t_course_teaching_class_id,
				t_course_teaching_class_homeworktype_id, pageNo - 1, pageSize);

		return new Page<CourseTeachingClassHomeworkBaseinfoViewData>(startIndex, totalCount, pageSize, data);

	}

}
