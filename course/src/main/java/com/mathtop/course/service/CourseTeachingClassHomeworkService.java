package com.mathtop.course.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mathtop.course.cons.RealPathConst;
import com.mathtop.course.dao.CourseTeachingClassHomeworkBaseinfoDao;
import com.mathtop.course.dao.CourseTeachingClassHomeworkFileDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfo;
import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfoViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkFile;
import com.mathtop.course.utility.DateTimeSql;
import com.mathtop.course.utility.GUID;

@Service
public class CourseTeachingClassHomeworkService {

	@Autowired
	CourseTeachingClassHomeworkBaseinfoDao homeworkbaseinfodao;

	@Autowired
	CourseTeachingClassHomeworkFileDao homeworkfileDao;

	public CourseTeachingClassHomeworkFile getFileByID(String id) {
		return homeworkfileDao.getByID(id);
	}

	private void AddFiles(HttpServletRequest request, String t_course_teaching_class_homework_baseinfo_id, MultipartFile[] files) {

		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			if(file.isEmpty())
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
	public void Add(HttpServletRequest request, String t_course_teaching_class_id, String t_course_teaching_class_homeworktype_id,
			String t_teacher_id, String filetype, String filenameformat, Integer filecount, String title, String content, String enddate,
			MultipartFile[] files) {

		String t_course_teaching_class_homework_baseinfo_id = homeworkbaseinfodao.add(t_course_teaching_class_id, t_teacher_id,
				t_course_teaching_class_homeworktype_id, filetype, filenameformat, filecount, title, content, new Date(),
				DateTimeSql.GetDateTimeNotIncludingSecond(enddate));

		AddFiles(request, t_course_teaching_class_homework_baseinfo_id, files);

	}

	// 删除
	public void DeleteByID(HttpServletRequest request, String t_course_teaching_class_homework_baseinfo_id) {

		// 删除基本信息及其附件文件
		DeleteBaseInfoByID(request, t_course_teaching_class_homework_baseinfo_id);

		// TODO：删除其他数据
	}

	// 删除基本信息及其附件文件
	private void DeleteBaseInfoByID(HttpServletRequest request, String t_course_teaching_class_homework_baseinfo_id) {
		CourseTeachingClassHomeworkBaseinfo plan = getByID(t_course_teaching_class_homework_baseinfo_id);
		if (plan == null)
			return;

		// 1.删除文件
		DeleteFilesByHomeworkBaseInfoId(request, t_course_teaching_class_homework_baseinfo_id);

		// 2.删除基本信息
		homeworkbaseinfodao.deleteById(t_course_teaching_class_homework_baseinfo_id);
	}

	// 删除文件
	private void DeleteFilesByHomeworkBaseInfoId(HttpServletRequest request, String t_course_teaching_class_homework_baseinfo_id) {
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

	public void Update(HttpServletRequest request, String t_course_teaching_class_homework_baseinfo_id, String t_teacher_id,
			String filetype, String filenameformat, Integer filecount, String title, String content, String enddate, MultipartFile[] files) {

		// 1.删除文件
		DeleteFilesByHomeworkBaseInfoId(request, t_course_teaching_class_homework_baseinfo_id);

		// 2.更改基本信息
		homeworkbaseinfodao.update(t_course_teaching_class_homework_baseinfo_id, t_teacher_id, filetype, filenameformat, filecount, title,
				content, DateTimeSql.GetDateTimeNotIncludingSecond(enddate));

		// 3.增加文件
		AddFiles(request, t_course_teaching_class_homework_baseinfo_id, files);
	}
	
	public void Update(HttpServletRequest request, String t_course_teaching_class_homework_baseinfo_id, String t_teacher_id,
			String title, String content, String enddate) {

		

		// 2.更改基本信息
		homeworkbaseinfodao.update(t_course_teaching_class_homework_baseinfo_id, t_teacher_id,  title,
				content, DateTimeSql.GetDateTimeNotIncludingSecond(enddate));

		
	}

	public CourseTeachingClassHomeworkBaseinfo getByID(String id) {
		return homeworkbaseinfodao.getByID(id);
	}

	public CourseTeachingClassHomeworkBaseinfoViewData getCourseTeachingClassHomeworkBaseinfoViewDataByID(String id) {
		return homeworkbaseinfodao.getCourseTeachingClassHomeworkBaseinfoViewDataByID(id);
	}

	public Page<CourseTeachingClassHomeworkBaseinfoViewData> getPage(String t_course_teaching_class_id, String t_course_teaching_class_homeworktype_id,int pageNo, int pageSize) {
		return homeworkbaseinfodao.getPage(t_course_teaching_class_id, t_course_teaching_class_homeworktype_id,pageNo, pageSize);
	}

	public void update(String id, String t_course_teaching_class_id, String t_teacher_id, String t_item_kinds_id, String filetype,
			String filenameformat, Integer filecount, String title, String content, Date pubdate, Date enddate, String filename,
			String filepath) {
		homeworkbaseinfodao.update(id, t_course_teaching_class_id, t_teacher_id, t_item_kinds_id, filetype, filenameformat, filecount,
				title, content, pubdate, enddate);
		// homeworkfileDao.update(t_item_kinds_id,
		// t_course_teaching_class_homework_baseinfo_id, filename, filepath);
	}

}
