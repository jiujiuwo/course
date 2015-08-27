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
import com.mathtop.course.dao.CourseTeachingClassForumReplyDao;
import com.mathtop.course.dao.CourseTeachingClassForumTopicDao;
import com.mathtop.course.dao.CourseTeachingClassForumTopicFileDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.CourseTeachingClassForumTopicFile;
import com.mathtop.course.domain.CourseTeachingClassForumTopicViewData;
import com.mathtop.course.utility.GUID;

@Service
public class CourseTeachingClassForumTopicService {

	@Autowired
	CourseTeachingClassForumTopicDao forumtopicdao;

	@Autowired
	CourseTeachingClassForumReplyDao forumreplyDao;

	@Autowired
	CourseTeachingClassForumTopicFileDao forumTopicFileDao;

	@Autowired
	CourseTeachingClassForumReplyService replyService;

	private void AddFiles(HttpServletRequest request, String t_forum_reply_id, MultipartFile[] files) {

		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			if(file.isEmpty())
				continue;

			String filename = file.getOriginalFilename();

			String prefix = filename.substring(filename.lastIndexOf(".") + 1);

			ServletContext sc = request.getSession().getServletContext();
			String dir = sc.getRealPath(RealPathConst.RealPath_ForumTopicFile); // 设定文件保存的目录

			prefix = prefix.toLowerCase();

			String idfilename = GUID.getGUID();

			String localfilename = dir + "\\" + idfilename + "." + prefix;
			String hreffilename = idfilename + "." + prefix;

			try {
				File localfile = new File(localfilename);

				if (localfile.exists())
					localfile.delete();

				file.transferTo(localfile);

				forumTopicFileDao.add(t_forum_reply_id, filename, hreffilename);

			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
	}

	// 删除文件
	private void DeleteFilesByForumTopicId(HttpServletRequest request, String t_forum_topic_id) {
		ServletContext sc = request.getSession().getServletContext();
		String dir = sc.getRealPath(RealPathConst.RealPath_ForumTopicFile); // 设定文件保存的目录

		List<CourseTeachingClassForumTopicFile> list = forumTopicFileDao.getByForumTopicID(t_forum_topic_id);
		if (list != null) {

			for (CourseTeachingClassForumTopicFile homeworkfile : list) {
				String path = dir + "\\" + homeworkfile.getFilepath();

				File localfile = new File(path);

				if (localfile.exists())
					localfile.delete();

				forumTopicFileDao.deleteById(homeworkfile.getId());

			}
		}
	}

	public void deleteById(HttpServletRequest request, String t_forum_topic_id) {
		// 1.删除文件
		DeleteFilesByForumTopicId(request, t_forum_topic_id);

		// 2.删除回复
		replyService.deleteByForumTopicId(request, t_forum_topic_id);
				
		// 3.删除主题
		forumtopicdao.deleteById(t_forum_topic_id);

		
	}

	public String add(HttpServletRequest request, String t_course_teaching_class_id, String t_user_id, String title, String content,
			String created_date, String last_modified_date, MultipartFile[] files) {
		String t_forum_topic_id = forumtopicdao
				.add(t_course_teaching_class_id, t_user_id, title, content, created_date, last_modified_date);
		AddFiles(request, t_forum_topic_id, files);
		return t_forum_topic_id;
	}

	public String add(HttpServletRequest request, String t_course_teaching_class_id, String t_user_id, String title, String content,
			Date created_date, Date last_modified_date, MultipartFile[] files) {
		
		String t_forum_topic_id = forumtopicdao
				.add(t_course_teaching_class_id, t_user_id, title, content, created_date, last_modified_date);
		AddFiles(request, t_forum_topic_id, files);
		return t_forum_topic_id;
	}

	
	public CourseTeachingClassForumTopicFile getFileByID(String id) {
		return forumTopicFileDao.getByID(id);
	}
	
	public Page<CourseTeachingClassForumTopicViewData> getPage(String t_course_teaching_class_id, int pageNo, int pageSize) {
		return forumtopicdao.getPage(t_course_teaching_class_id, pageNo, pageSize);
	}

	public CourseTeachingClassForumTopicViewData getForumTopicViewDataById(String id) {
		return forumtopicdao.getForumTopicViewDataById(id);
	}

	public void update(HttpServletRequest request, String t_forum_topic_id, String t_user_id, String title, String content,
			String last_modified_date, MultipartFile[] files) {

		// 1.删除文件
		DeleteFilesByForumTopicId(request, t_forum_topic_id);

		// 2.更改基本信息
		forumtopicdao.update(t_forum_topic_id, t_user_id, title, content, last_modified_date);

		// 3.增加文件
		AddFiles(request, t_forum_topic_id, files);

	}

	public void update(HttpServletRequest request, String t_forum_topic_id, String t_user_id, String title, String content,
			Date last_modified_date, MultipartFile[] files) {

		// 1.删除文件
		DeleteFilesByForumTopicId(request, t_forum_topic_id);

		// 2.更改基本信息
		forumtopicdao.update(t_forum_topic_id, t_user_id, title, content, last_modified_date);

		// 3.增加文件
		AddFiles(request, t_forum_topic_id, files);
	}

}
