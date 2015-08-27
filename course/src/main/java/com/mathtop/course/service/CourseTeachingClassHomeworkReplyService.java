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
import com.mathtop.course.dao.CourseTeachingClassHomeworkReplyDao;
import com.mathtop.course.dao.CourseTeachingClassHomeworkReplyFileDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.CourseTeachingClassHomeworkReply;
import com.mathtop.course.domain.CourseTeachingClassHomeworkReplyFile;
import com.mathtop.course.domain.CourseTeachingClassHomeworkReplyViewData;
import com.mathtop.course.utility.GUID;

@Service
public class CourseTeachingClassHomeworkReplyService {

	@Autowired
	CourseTeachingClassHomeworkReplyDao replydao;

	@Autowired
	CourseTeachingClassHomeworkReplyFileDao replyfiledao;

	public CourseTeachingClassHomeworkReplyFile getFileByID(String id) {
		return replyfiledao.getByID(id);
	}
	
	private void AddFiles(HttpServletRequest request,String t_course_teaching_class_homework_reply_id,MultipartFile[] files){		

		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			if(file.isEmpty())
				continue;

			String filename = file.getOriginalFilename();

			String prefix = filename.substring(filename.lastIndexOf(".") + 1);

			ServletContext sc = request.getSession().getServletContext();
			String dir = sc.getRealPath(RealPathConst.RealPath_HomeworkReplyFile); // 设定文件保存的目录

			prefix = prefix.toLowerCase();

			String idfilename = GUID.getGUID();

			String localfilename = dir + "\\" + idfilename + "." + prefix;
			String hreffilename = idfilename + "." + prefix;

			try {
				File localfile = new File(localfilename);

				if (localfile.exists())
					localfile.delete();

				file.transferTo(localfile);

				
				
				replyfiledao.add(t_course_teaching_class_homework_reply_id, filename, hreffilename);

			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
	}

	// 增加
	public void Add(HttpServletRequest request,String t_course_teaching_class_homework_submit_baseinfo_id ,
			String t_teacher_id, String title, String content,  MultipartFile[] files) {
		

			String t_course_teaching_class_homework_reply_id = replydao.add(t_course_teaching_class_homework_submit_baseinfo_id, t_teacher_id,
					 title, content, new Date(), new Date());

			AddFiles(request,t_course_teaching_class_homework_reply_id,files);
			
			
		
	}

	// 删除
	public void DeleteByID(HttpServletRequest request, String t_course_teaching_class_homework_reply_id) {

		// 删除基本信息及其附件文件
		DeleteBaseInfoByID(request, t_course_teaching_class_homework_reply_id);

		// TODO：删除其他数据
	}

	// 删除基本信息及其附件文件
	private void DeleteBaseInfoByID(HttpServletRequest request, String t_course_teaching_class_homework_reply_id) {
		CourseTeachingClassHomeworkReply plan = getByID(t_course_teaching_class_homework_reply_id);
		if (plan == null)
			return;

		// 1.删除文件
		DeleteFilesByHomeworkBaseInfoId(request, t_course_teaching_class_homework_reply_id);

		// 2.删除基本信息
		replydao.deleteById(t_course_teaching_class_homework_reply_id);
	}

	// 删除文件
	private void DeleteFilesByHomeworkBaseInfoId(HttpServletRequest request, String t_course_teaching_class_homework_reply_id) {
		ServletContext sc = request.getSession().getServletContext();
		String dir = sc.getRealPath(RealPathConst.RealPath_HomeworkReplyFile); // 设定文件保存的目录

		List<CourseTeachingClassHomeworkReplyFile> list = replyfiledao
				.getByCourseTeachingClassHomeworkReplyID(t_course_teaching_class_homework_reply_id);
		if (list != null) {

		

			for (CourseTeachingClassHomeworkReplyFile homeworkfile : list) {
				String path = dir + "\\" + homeworkfile.getFilepath();

				File localfile = new File(path);

				if (localfile.exists())
					localfile.delete();

				replyfiledao.deleteById(homeworkfile.getId());

			}
		}
	}

	public void Update(HttpServletRequest request, String t_course_teaching_class_homework_reply_id, String t_teacher_id, String title,
			String content, MultipartFile[] files) {

		// 1.删除文件
		DeleteFilesByHomeworkBaseInfoId(request, t_course_teaching_class_homework_reply_id);

		// 2.更改基本信息
		replydao.update(t_course_teaching_class_homework_reply_id,  title, content,
				new Date());

		// 3.增加文件
		AddFiles(request,t_course_teaching_class_homework_reply_id,files);
	}

	public CourseTeachingClassHomeworkReply getByID(String id) {
		return replydao.getByID(id);
	}
	
	public CourseTeachingClassHomeworkReplyViewData getCourseTeachingClassHomeworkReplyViewDataByID(String id) {
		return replydao.getCourseTeachingClassHomeworkReplyViewDataByID(id);
	}

	public Page<CourseTeachingClassHomeworkReplyViewData> getPage(String t_course_teaching_class_homework_baseinfo_id, String t_student_id,int pageNo,
			int pageSize) {
		return replydao.getPage(t_course_teaching_class_homework_baseinfo_id, t_student_id,pageNo, pageSize);
	}

	public void update(String id,  String title, String content, Date modifieddate, String filename, String filepath) {
		replydao.update(id,  title, content, modifieddate);
	}

}
