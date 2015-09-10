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
import com.mathtop.course.dao.CourseTeachingClassForumReplyFileDao;
import com.mathtop.course.dao.CourseTeachingClassForumTopicDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.CourseTeachingClassForumReply;
import com.mathtop.course.domain.CourseTeachingClassForumReplyFile;
import com.mathtop.course.domain.CourseTeachingClassForumReplyViewData;
import com.mathtop.course.utility.GUID;

@Service
public class CourseTeachingClassForumReplyService {

	@Autowired
	CourseTeachingClassForumReplyDao forumreplyDao;

	@Autowired
	CourseTeachingClassForumTopicDao forumtopicDao;

	@Autowired
	CourseTeachingClassForumReplyFileDao forumReplyFileDao;
	
	

	private void AddFiles(HttpServletRequest request, String t_forum_reply_id, MultipartFile[] files) {

		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			if(file.isEmpty())
				continue;

			String filename = file.getOriginalFilename();

			String prefix = filename.substring(filename.lastIndexOf(".") + 1);

			ServletContext sc = request.getSession().getServletContext();
			String dir = sc.getRealPath(RealPathConst.RealPath_ForumReplyFile); // 设定文件保存的目录

			prefix = prefix.toLowerCase();

			String idfilename = GUID.getGUID();

			String localfilename = dir + RealPathConst.RealPath_PathSeparator + idfilename + "." + prefix;
			String hreffilename = idfilename + "." + prefix;

			try {
				File localfile = new File(localfilename);

				if (localfile.exists())
					localfile.delete();

				file.transferTo(localfile);

				forumReplyFileDao.add(t_forum_reply_id, filename, hreffilename);

			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
	}

	// 删除文件
	private void DeleteFilesByForumReplyId(HttpServletRequest request, String t_forum_reply_id) {
		ServletContext sc = request.getSession().getServletContext();
		String dir = sc.getRealPath(RealPathConst.RealPath_ForumReplyFile); // 设定文件保存的目录

		List<CourseTeachingClassForumReplyFile> list = forumReplyFileDao.getByForumReplyID(t_forum_reply_id);
		if (list != null) {

			for (CourseTeachingClassForumReplyFile homeworkfile : list) {
				String path = dir + RealPathConst.RealPath_PathSeparator + homeworkfile.getFilepath();

				File localfile = new File(path);

				if (localfile.exists())
					localfile.delete();

				forumReplyFileDao.deleteById(homeworkfile.getId());

			}
		}
	}
	public void deleteByForumTopicId(HttpServletRequest request, String t_forum_topic_id){
		List<CourseTeachingClassForumReply> list=forumreplyDao.getByForumTopicID(t_forum_topic_id);
		for(CourseTeachingClassForumReply f:list){
			deleteById(request,f.getId());
		}
	}

	public void deleteById(HttpServletRequest request, String t_forum_reply_id) {
		// 1.删除文件
		DeleteFilesByForumReplyId(request, t_forum_reply_id);

		forumreplyDao.deleteById(t_forum_reply_id);
	}

	public String add(HttpServletRequest request, String t_forum_topic_id, String t_user_id, String title, String content,
			String created_date, MultipartFile[] files) {
		String t_forum_reply_id = forumreplyDao.add(t_forum_topic_id, t_user_id, title, content, created_date, created_date);

		AddFiles(request, t_forum_reply_id, files);
		return t_forum_reply_id;
	}

	public String add(HttpServletRequest request, String t_forum_topic_id, String t_user_id, String title, String content,
			Date created_date, MultipartFile[] files) {
		String t_forum_reply_id = forumreplyDao.add(t_forum_topic_id, t_user_id, title, content, created_date, created_date);
		AddFiles(request, t_forum_reply_id, files);
		return t_forum_reply_id;
	}
	
	public CourseTeachingClassForumReplyFile getFileByID(String id) {
		return forumReplyFileDao.getByID(id);
	}

	public Page<CourseTeachingClassForumReplyViewData> getPage(String t_forum_topic_id, int pageNo, int pageSize) {
		forumtopicDao.incViewCount(t_forum_topic_id);
		return forumreplyDao.getPage(t_forum_topic_id, pageNo, pageSize);
	}

	public void update(HttpServletRequest request, String t_forum_reply_id, String t_user_id, String title, String content,
			Date last_modified_date, MultipartFile[] files) {

		// 1.删除文件
		DeleteFilesByForumReplyId(request, t_forum_reply_id);

		// 2.更改基本信息
		forumreplyDao.update(t_forum_reply_id, t_user_id, title, content, last_modified_date);

		// 3.增加文件
		AddFiles(request, t_forum_reply_id, files);

	}

	public CourseTeachingClassForumReplyViewData getForumReplyViewDataByID(String id) {
		return forumreplyDao.getForumReplyViewDataByID(id);
	}

}
