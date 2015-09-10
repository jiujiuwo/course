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
import com.mathtop.course.dao.CourseTeachingClassHomeworkReplyDao;
import com.mathtop.course.dao.CourseTeachingClassHomeworkStatisticsViewDataDao;
import com.mathtop.course.dao.CourseTeachingClassHomeworkSubmitBaseinfoDao;
import com.mathtop.course.dao.CourseTeachingClassHomeworkSubmitFileDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.dao.TeachingClassStudentDao;
import com.mathtop.course.domain.CourseTeachingClass;
import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfo;
import com.mathtop.course.domain.CourseTeachingClassHomeworkStatisticsViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkSubmitBaseinfo;
import com.mathtop.course.domain.CourseTeachingClassHomeworkSubmitBaseinfoViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkSubmitFile;
import com.mathtop.course.domain.StatisticsData;
import com.mathtop.course.domain.ZipFileName;
import com.mathtop.course.domain.zip;
import com.mathtop.course.utility.GUID;

@Service
public class CourseTeachingClassHomeworkSubmitService {

	@Autowired
	CourseTeachingClassHomeworkSubmitBaseinfoDao submitDao;

	@Autowired
	CourseTeachingClassHomeworkBaseinfoDao homeworkbaseinfoDao;

	@Autowired
	CourseTeachingClassHomeworkSubmitFileDao submitfileDao;

	@Autowired
	TeachingClassStudentDao tachingclassstudentDao;

	@Autowired
	CourseTeachingClassDao courseteachingclassDao;
	
	@Autowired
	CourseTeachingClassHomeworkReplyDao replyDao;
	
	@Autowired
	CourseTeachingClassHomeworkStatisticsViewDataDao courseTeachingClassHomeworkStatisticsViewDataDao;

	public CourseTeachingClassHomeworkSubmitFile getFileByID(String id) {
		return submitfileDao.getByID(id);
	}

	private void AddFiles(HttpServletRequest request, String t_course_teaching_class_homework_submit_baseinfo_id, MultipartFile[] files) {

		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			if(file.isEmpty())
				continue;

			String filename = file.getOriginalFilename();

			String prefix = filename.substring(filename.lastIndexOf(".") + 1);

			ServletContext sc = request.getSession().getServletContext();
			String dir = sc.getRealPath(RealPathConst.RealPath_HomeworkSubmitFile); // 设定文件保存的目录

			prefix = prefix.toLowerCase();

			String idfilename = GUID.getGUID();

			String localfilename = dir + RealPathConst.RealPath_PathSeparator + idfilename + "." + prefix;
			String hreffilename = idfilename + "." + prefix;

			try {
				File localfile = new File(localfilename);

				if (localfile.exists())
					localfile.delete();

				file.transferTo(localfile);

				submitfileDao.add(t_course_teaching_class_homework_submit_baseinfo_id, filename, hreffilename);

			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
	}

	// 增加
	public void Add(HttpServletRequest request, String t_course_teaching_class_homework_baseinfo_id, String t_student_id, String title,
			String content, MultipartFile[] files) {

		String t_course_teaching_class_homework_submit_baseinfo_id = submitDao.add(t_course_teaching_class_homework_baseinfo_id,
				t_student_id, title, content, new Date(), new Date());

		AddFiles(request, t_course_teaching_class_homework_submit_baseinfo_id, files);

	}

	// 删除
	public void DeleteByID(HttpServletRequest request, String t_course_teaching_class_homework_submit_baseinfo_id) {

		// 删除基本信息及其附件文件
		DeleteBaseInfoByID(request, t_course_teaching_class_homework_submit_baseinfo_id);

		// TODO：删除其他数据
	}

	// 删除基本信息及其附件文件
	private void DeleteBaseInfoByID(HttpServletRequest request, String t_course_teaching_class_homework_submit_baseinfo_id) {
		CourseTeachingClassHomeworkSubmitBaseinfo plan = getByID(t_course_teaching_class_homework_submit_baseinfo_id);
		if (plan == null)
			return;

		// 1.删除文件
		DeleteFilesByHomeworkSubmitBaseInfoId(request, t_course_teaching_class_homework_submit_baseinfo_id);

		// 2.删除基本信息
		submitDao.deleteById(t_course_teaching_class_homework_submit_baseinfo_id);
	}

	// 删除文件
	private void DeleteFilesByHomeworkSubmitBaseInfoId(HttpServletRequest request,
			String t_course_teaching_class_homework_submit_baseinfo_id) {
		ServletContext sc = request.getSession().getServletContext();
		String dir = sc.getRealPath(RealPathConst.RealPath_HomeworkSubmitFile); // 设定文件保存的目录

		List<CourseTeachingClassHomeworkSubmitFile> list = submitfileDao
				.getByCourseTeachingClassHomeworkBaseInfoID(t_course_teaching_class_homework_submit_baseinfo_id);
		if (list != null) {

			for (CourseTeachingClassHomeworkSubmitFile homeworkfile : list) {
				String path = dir + RealPathConst.RealPath_PathSeparator + homeworkfile.getFilepath();

				File localfile = new File(path);

				if (localfile.exists())
					localfile.delete();

				submitfileDao.deleteById(homeworkfile.getId());

			}
		}
	}

	public void Update(HttpServletRequest request, String t_course_teaching_class_homework_submit_baseinfo_id, String t_student_id,
			String title, String content, MultipartFile[] files) {

		// 1.删除文件
		DeleteFilesByHomeworkSubmitBaseInfoId(request, t_course_teaching_class_homework_submit_baseinfo_id);

		// 2.更改基本信息
		submitDao.update(t_course_teaching_class_homework_submit_baseinfo_id, title, content, new Date());

		// 3.增加文件
		AddFiles(request, t_course_teaching_class_homework_submit_baseinfo_id, files);
	}

	public CourseTeachingClassHomeworkSubmitBaseinfo getByID(String id) {
		return submitDao.getByID(id);
	}

	public void deleteById(String id) {
		submitDao.deleteById(id);
	}

	public String add(String t_course_teaching_class_homework_baseinfo_id, String t_student_id, String title, String content,
			Date submitdate, Date modifieddate, String filename, String filepath) {
		String t_course_teaching_class_homework_submit_baseinfo_id = submitDao.add(t_course_teaching_class_homework_baseinfo_id,
				t_student_id, title, content, submitdate, modifieddate);
		submitfileDao.add(t_course_teaching_class_homework_submit_baseinfo_id, filename, filepath);
		return t_course_teaching_class_homework_submit_baseinfo_id;
	}

	public Page<CourseTeachingClassHomeworkSubmitBaseinfoViewData> getPage(String t_course_teaching_class_homework_baseinfo_id, int pageNo,
			int pageSize) {
		return submitDao.getPage(t_course_teaching_class_homework_baseinfo_id, pageNo, pageSize);
	}

	public Page<CourseTeachingClassHomeworkSubmitBaseinfoViewData> getPage(String t_course_teaching_class_homework_baseinfo_id,
			String t_student_id, int pageNo, int pageSize) {
		return submitDao.getPage(t_course_teaching_class_homework_baseinfo_id, t_student_id, pageNo, pageSize);
	}
	
	public Page<CourseTeachingClassHomeworkStatisticsViewData> getPageofStatisticsViewData(String t_course_teaching_class_homework_baseinfo_id, int pageNo, int pageSize) {
		return courseTeachingClassHomeworkStatisticsViewDataDao.getPage(t_course_teaching_class_homework_baseinfo_id, pageNo, pageSize);
	}

	public void update(String id, String title, String content, Date modifieddate, String filename, String filepath) {
		submitDao.update(id, title, content, modifieddate);
	}

	/**
	 * 将指定作业进行压缩
	 * */
	public zip zipByHomeworkBaseInfoId(HttpServletRequest request, String t_course_teaching_class_homework_baseinfo_id) {

		Page<CourseTeachingClassHomeworkSubmitBaseinfoViewData> pageAll = submitDao
				.getPageAll(t_course_teaching_class_homework_baseinfo_id);

		ServletContext sc = request.getSession().getServletContext();
		String dir = sc.getRealPath(RealPathConst.RealPath_HomeworkSubmitFile); // 设定文件保存的目录

		// 根据实验名称、班级等设置文件夹名称
		CourseTeachingClassHomeworkBaseinfo baseinfo = homeworkbaseinfoDao.getByID(t_course_teaching_class_homework_baseinfo_id);
		String zipDir = baseinfo.getTitle();

		if (pageAll != null) {
			List<CourseTeachingClassHomeworkSubmitBaseinfoViewData> list = pageAll.getResult();
			List<ZipFileName> filenames = new ArrayList<ZipFileName>();
			for (CourseTeachingClassHomeworkSubmitBaseinfoViewData data : list) {

				List<CourseTeachingClassHomeworkSubmitFile> files = submitfileDao.getByCourseTeachingClassHomeworkBaseInfoID(data
						.getHomeworksubmitbaseinfo().getId());
				for (CourseTeachingClassHomeworkSubmitFile file : files) {
					ZipFileName zipfilename = new ZipFileName();
					zipfilename.setZipDir(zipDir);
					zipfilename.setZipFileName(file.getFilename());

					String path = dir + RealPathConst.RealPath_PathSeparator + file.getFilepath();
					zipfilename.setRealFilePath(path);

					filenames.add(zipfilename);
				}

			}

			zip z = new zip(zipDir);
			z.doCompress(zipDir, filenames);

			return z;
		}

		return null;
	}

	/**
	 * 学生是否允许提交作业
	 * */
	public boolean canSubmit(String t_course_teaching_class_homework_baseinfo_id, String t_student_id) {
		CourseTeachingClassHomeworkBaseinfo baseinfo = homeworkbaseinfoDao.getByID(t_course_teaching_class_homework_baseinfo_id);
		Date begin = baseinfo.getPubdate();
		Date end = baseinfo.getEnddate();
		Date now = new Date();

		if (now.getTime() > begin.getTime() && now.getTime() < end.getTime())
			return true;

		return false;
	}

	/**
	 * 统计数据
	 * */
	public StatisticsData getStatisticsData(String t_course_teaching_class_homework_baseinfo_id) {
		StatisticsData s = new StatisticsData();
		CourseTeachingClassHomeworkBaseinfo baseinfo = homeworkbaseinfoDao.getByID(t_course_teaching_class_homework_baseinfo_id);

		if (baseinfo == null)
			return null;
		
		List<CourseTeachingClassHomeworkSubmitBaseinfo> submitlist=submitDao.getByCourseTeachingClassHomeworkBaseinfoID(t_course_teaching_class_homework_baseinfo_id);
		if(submitlist==null)
			return null;
		
		int nReplyCount=0;
		for(CourseTeachingClassHomeworkSubmitBaseinfo sub:submitlist){
			nReplyCount+=replyDao.getCount(sub.getId(),sub.getT_student_id());
		}

		CourseTeachingClass ctc = courseteachingclassDao.getCourseTeachingClassById(baseinfo.getT_course_teaching_class_id());
		int n = tachingclassstudentDao.getStudentCountByTeachingClassId(ctc.getT_teaching_class_id());
		if (n == 0)
			return null;

		int nsubmit = submitDao.getCount(baseinfo.getId());
		s.setTotalCount(n);
		s.setSubmitCount(nsubmit);
		s.setReplyCount(nReplyCount);

		return s;
	}

	/**
	 * 图表数据
	 * */
	public List<chartDataValue> getSubmitChartDataValue(String t_course_teaching_class_homework_baseinfo_id) {
		CourseTeachingClassHomeworkBaseinfo baseinfo = homeworkbaseinfoDao.getByID(t_course_teaching_class_homework_baseinfo_id);

		if (baseinfo == null)
			return null;

		CourseTeachingClass ctc = courseteachingclassDao.getCourseTeachingClassById(baseinfo.getT_course_teaching_class_id());
		int n = tachingclassstudentDao.getStudentCountByTeachingClassId(ctc.getT_teaching_class_id());
		if (n == 0)
			return null;

		int nsubmit = submitDao.getCount(baseinfo.getId());

		List<chartDataValue> list = new ArrayList<chartDataValue>();

		chartDataValue c1 = new chartDataValue();
		chartDataValue c2 = new chartDataValue();
		c1.setValue(nsubmit);
		c1.setColor("#F38630");

		c1.setValue(n - nsubmit);
		c1.setColor("#E0E4CC");
		list.add(c1);
		list.add(c2);
		return list;
	}

}
