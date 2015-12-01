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
import com.mathtop.course.dao.CourseTeachingClassReferenceDao;
import com.mathtop.course.dao.CourseTeachingClassReferenceFileDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.CourseTeachingClassReference;
import com.mathtop.course.domain.CourseTeachingClassReferenceFile;
import com.mathtop.course.domain.CourseTeachingClassReferenceViewData;
import com.mathtop.course.utility.GUID;

@Service
public class CourseTeachingClassReferenceService {

	@Autowired
	CourseTeachingClassReferenceDao referencedao;

	@Autowired
	CourseTeachingClassReferenceFileDao fileDao;

	public CourseTeachingClassReferenceFile getFileByID(String id) {
		return fileDao.getByID(id);
	}

	private void addFiles(HttpServletRequest request, String t_course_teaching_class_homework_baseinfo_id, MultipartFile[] files) {

		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			if (file.isEmpty())
				continue;

			String filename = file.getOriginalFilename();

			String prefix = filename.substring(filename.lastIndexOf(".") + 1);

			ServletContext sc = request.getSession().getServletContext();
			String dir = sc.getRealPath(RealPathConst.RealPath_ReferenceFile); // 设定文件保存的目录

			prefix = prefix.toLowerCase();

			String idfilename = GUID.getGUID();

			String localfilename = dir + RealPathConst.RealPath_PathSeparator + idfilename + "." + prefix;
			String hreffilename = idfilename + "." + prefix;

			try {
				File localfile = new File(localfilename);

				if (localfile.exists())
					localfile.delete();

				file.transferTo(localfile);

				fileDao.add(t_course_teaching_class_homework_baseinfo_id, filename, hreffilename);

			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
	}

	// 增加
	public void add(HttpServletRequest request, String t_course_teaching_class_id, String t_course_teaching_class_homeworktype_id,
			String t_teacher_id, String title, String content, MultipartFile[] files) {

		String t_course_teaching_class_homework_baseinfo_id = referencedao.add(t_course_teaching_class_id, t_teacher_id,
				t_course_teaching_class_homeworktype_id, title, content, new Date(), new Date());

		addFiles(request, t_course_teaching_class_homework_baseinfo_id, files);

	}

	// 删除
	public void deleteByID(HttpServletRequest request, String t_course_teaching_class_reference_id) {

		// 删除基本信息及其附件文件
		deleteBaseInfoByID(request, t_course_teaching_class_reference_id);

		// TODO：删除其他数据
	}

	// 删除基本信息及其附件文件
	private void deleteBaseInfoByID(HttpServletRequest request, String t_course_teaching_class_reference_id) {
		CourseTeachingClassReference plan = getByID(t_course_teaching_class_reference_id);
		if (plan == null)
			return;

		// 1.删除文件
		deleteFilesByReferenceId(request, t_course_teaching_class_reference_id);

		// 2.删除基本信息
		referencedao.deleteById(t_course_teaching_class_reference_id);
	}

	/**
	 * 根据t_course_teaching_class_id删除
	 */
	public void deleteByCourseTeachingClassId(HttpServletRequest request, String t_course_teaching_class_id) {
		List<CourseTeachingClassReference> list = referencedao.getByCourseTeachingClassID(t_course_teaching_class_id);
		if (list == null)
			return;
		for (CourseTeachingClassReference r : list) {
			deleteByID(request, r.getId());
		}
	}

	/**
	 * 根据t_teacher_id删除
	 */
	public void deleteByTeacherId(HttpServletRequest request, String t_teacher_id) {
		List<CourseTeachingClassReference> list = referencedao.getByTeacherID(t_teacher_id);
		if (list == null)
			return;
		for (CourseTeachingClassReference r : list) {
			deleteByID(request, r.getId());
		}
	}

	/**
	 * 根据t_course_teaching_class_id、t_teacher_id删除
	 */
	public void deleteByCourseTeachingClassIdAndTeacherId(HttpServletRequest request, String t_course_teaching_class_id,
			String t_teacher_id) {
		List<CourseTeachingClassReference> list = referencedao.getByCourseTeachingClassIDAndTeacherId(t_course_teaching_class_id,
				t_teacher_id);
		if (list == null)
			return;
		for (CourseTeachingClassReference r : list) {
			deleteByID(request, r.getId());
		}
	}

	/**
	 * 根据t_course_teaching_class_reference_type_id删除
	 */
	public void deleteByReferenceTypeId(HttpServletRequest request, String t_course_teaching_class_reference_type_id) {
		List<CourseTeachingClassReference> list = referencedao.getByReferenceTypeId(t_course_teaching_class_reference_type_id);
		if (list == null)
			return;
		for (CourseTeachingClassReference r : list) {
			deleteByID(request, r.getId());
		}
	}

	/**
	 * 根据t_course_teaching_class_id、t_teacher_id、t_course_teaching_class_reference_type_id删除
	 */
	public void deleteByCourseTeachingClassIdAndTeacherIdAndReferenceTypeId(HttpServletRequest request, String t_course_teaching_class_id,
			String t_teacher_id, String t_course_teaching_class_reference_type_id) {
		List<CourseTeachingClassReference> list = referencedao.getByCourseTeachingClassIDAndTeacherIdAndReferenceTypeId(
				t_course_teaching_class_id, t_teacher_id, t_course_teaching_class_reference_type_id);
		if (list == null)
			return;
		for (CourseTeachingClassReference r : list) {
			deleteByID(request, r.getId());
		}
	}

	// 删除文件
	private void deleteFilesByReferenceId(HttpServletRequest request, String t_course_teaching_class_reference_id) {
		ServletContext sc = request.getSession().getServletContext();
		String dir = sc.getRealPath(RealPathConst.RealPath_ReferenceFile); // 设定文件保存的目录

		List<CourseTeachingClassReferenceFile> list = fileDao
				.getByCourseTeachingClassHomeworkBaseInfoID(t_course_teaching_class_reference_id);
		if (list != null) {

			for (CourseTeachingClassReferenceFile homeworkfile : list) {
				String path = dir + RealPathConst.RealPath_PathSeparator + homeworkfile.getFilepath();

				File localfile = new File(path);

				if (localfile.exists())
					localfile.delete();

				fileDao.deleteById(homeworkfile.getId());

			}
		}
	}

	public void update(HttpServletRequest request, String t_course_teaching_class_homework_baseinfo_id, String t_teacher_id, String title,
			String content, MultipartFile[] files) {

		// 1.删除文件
		deleteFilesByReferenceId(request, t_course_teaching_class_homework_baseinfo_id);

		// 2.更改基本信息
		referencedao.update(t_course_teaching_class_homework_baseinfo_id, t_teacher_id, title, content, new Date());

		// 3.增加文件
		addFiles(request, t_course_teaching_class_homework_baseinfo_id, files);
	}

	public void Update(HttpServletRequest request, String t_course_teaching_class_homework_baseinfo_id, String t_teacher_id, String title,
			String content) {

		// 2.更改基本信息
		referencedao.update(t_course_teaching_class_homework_baseinfo_id, t_teacher_id, title, content, new Date());

	}

	public CourseTeachingClassReference getByID(String id) {
		return referencedao.getByID(id);
	}

	public CourseTeachingClassReferenceViewData getCourseTeachingClassHomeworkBaseinfoViewDataByID(String id) {
		return referencedao.getCourseTeachingClassHomeworkBaseinfoViewDataByID(id);
	}

	private String getFileLength(HttpServletRequest request, String filepath) {

		ServletContext sc = request.getSession().getServletContext();
		String dir = sc.getRealPath(RealPathConst.RealPath_ReferenceFile); // 设定文件保存的目录

		String localfilename = dir + RealPathConst.RealPath_PathSeparator + filepath;
		File file = new File(localfilename);

		String strLen = "";
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
		if (file.exists() && file.isFile()) {
			long n = file.length();
			if (n < 1024)
				strLen = n + "字节";
			else if (n < 1024 * 1024) {
				double d = n;
				strLen = df.format(d / 1024) + "K";
			} else if (n < 1024 * 1024 * 1024) {
				double d = n;
				strLen = df.format(d / (1024 * 1024)) + "M";
			} else if (n < 1024 * 1024 * 1024 * 1024) {
				double d = n;
				strLen = df.format(d / (1024 * 1024 * 1024)) + "G";
			}
		}

		return strLen;

	}

	/**
	 * 获得每个文件长度
	 */
	private void getFileLength(HttpServletRequest request, Page<CourseTeachingClassReferenceViewData> page) {
		if (page == null)
			return;

		List<CourseTeachingClassReferenceViewData> list = page.getResult();
		if (list == null)
			return;

		for (CourseTeachingClassReferenceViewData data : list) {
			List<CourseTeachingClassReferenceFile> fileList = data.getFileList();
			if (fileList == null)
				continue;
			for (CourseTeachingClassReferenceFile f : fileList) {
				f.setFilelength(getFileLength(request, f.getFilepath()));
			}
		}

	}

	public Page<CourseTeachingClassReferenceViewData> getPage(HttpServletRequest request, String t_course_teaching_class_id,
			String t_course_teaching_class_homeworktype_id, int pageNo, int pageSize) {
		Page<CourseTeachingClassReferenceViewData> page = referencedao.getPage(t_course_teaching_class_id,
				t_course_teaching_class_homeworktype_id, pageNo, pageSize);
		getFileLength(request, page);
		return page;
	}

}
