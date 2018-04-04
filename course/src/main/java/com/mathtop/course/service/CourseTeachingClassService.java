package com.mathtop.course.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mathtop.course.dao.CourseTeachingClassDao;
import com.mathtop.course.dao.CourseTeachingClassStudentDao;
import com.mathtop.course.dao.CourseTeachingClassTeacherDao;
import com.mathtop.course.dao.CourseTeachingClassViewDataDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.dao.StudentViewDataDao;
import com.mathtop.course.domain.CourseTeachingClass;
import com.mathtop.course.domain.CourseTeachingClassStudent;
import com.mathtop.course.domain.CourseTeachingClassViewData;
import com.mathtop.course.domain.StudentViewData;
import com.mathtop.course.domain.TeacherViewData;
import com.mathtop.course.exception.NaturalClassNotExistException;
import com.mathtop.course.exception.StudentExistException;

@Service
public class CourseTeachingClassService {

	@Autowired
	CourseTeachingClassDao courseTeachingClassDao;

	@Autowired
	CourseTeachingClassTeacherDao courseTeachingClassTeacherDao;

	@Autowired
	CourseTeachingClassStudentDao courseTeachingClassStudentDao;

	@Autowired
	CourseTeachingClassViewDataDao courseTeachingClassViewDataDao;

	@Autowired
	StudentViewDataDao studentViewDataDao;

	@Autowired
	AttendanceService attendanceService;

	@Autowired
	CourseTeachingClassForumTopicService courseTeachingClassForumTopicService;

	@Autowired
	CourseTeachingClassReferenceService courseTeachingClassReferenceService;

	@Autowired
	CourseTeachingClassHomeworkService courseTeachingClassHomeworkService;

	@Autowired
	CourseTeachingClassHomeworkTypeService courseTeachingClassHomeworkTypeService;

	@Autowired
	CourseTeachingClassTeacherService courseTeachingClassTeacherService;

	@Autowired
	StudentService studentService;
	
	@Autowired
	CourseTeachingClassStudentGroupService courseTeachingClassStudentGroupService;

	/**
	 * 从Excel上传学生清单，然后添加到数据库中
	 */
	public void UploadFromExcel(String t_course_teaching_class_id, MultipartFile file) throws Exception {
		if (!file.isEmpty()) {
			String localfilename = file.getOriginalFilename();
			try {
				File localfile = new File(localfilename);

				if (localfile.exists())
					localfile.delete();

				file.transferTo(localfile);

				String prefix = localfilename.substring(localfilename.lastIndexOf(".") + 1);

				prefix = prefix.toLowerCase();

				if (prefix.equals("xls"))
					ProcessExcel97(t_course_teaching_class_id, localfilename);
				else if (prefix.equals("xlsx"))
					ProcessExcel(t_course_teaching_class_id, localfilename);

			} catch (IllegalStateException | IOException e) {
				throw e;

			} catch (StudentExistException e) {
				throw e;
			}
		}

	}

	/**
	 * 从97-03格式的Excel(.xls)中导入学生
	 * 
	 * @throws Exception
	 * 
	 * @throws NaturalClassNotExistException
	 */
	private void ProcessExcel97(String t_course_teaching_class_id, String localfilename) throws Exception {

		try {

			File file = new File(localfilename);
			FileInputStream fis = new FileInputStream(file);
			POIFSFileSystem poifsFileSystem = new POIFSFileSystem(fis);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);

			int nCount = hssfWorkbook.getNumberOfSheets();

			for (int sheetindex = 0; sheetindex < nCount; sheetindex++) {
				HSSFSheet sheet = hssfWorkbook.getSheetAt(sheetindex);

				int rowstart = sheet.getFirstRowNum();
				int rowEnd = sheet.getLastRowNum();

				int schoolIndex = -1;
				int departIndex = -1;
				int classnoIndex = -1;
				int nameIndex = -1;
				int numIndex = -1;

				for (int i = rowstart; i <= rowEnd; i++) {

					HSSFRow row = sheet.getRow(i);
					if (null == row)
						continue;

					int cellStart = row.getFirstCellNum();
					int cellEnd = row.getLastCellNum();

					String school = null;
					String department = null;
					String name = null;
					String student_num = null;
					String naturalclass = null;

					for (int k = cellStart; k <= cellEnd; k++) {
						HSSFCell cell = row.getCell(k);
						if (null == cell)
							continue;

						DataFormatter formatter = new DataFormatter();

						//String strIndexName = cell.getStringCellValue().trim();
						String strIndexName =formatter.formatCellValue(cell);

						if (i == rowstart) {

							if (strIndexName.equals("学院") || strIndexName.equals("院"))
								schoolIndex = k;
							else if (strIndexName.equals("系部") || strIndexName.equals("系") || strIndexName.equals("部"))
								departIndex = k;
							else if (strIndexName.equals("班号") || strIndexName.equals("班级") || strIndexName.equals("班"))
								classnoIndex = k;
							else if (strIndexName.equals("学号"))
								numIndex = k;
							else if (strIndexName.equals("姓名"))
								nameIndex = k;

						} else {

							if (k == schoolIndex)
								school = strIndexName;
							else if (k == departIndex)
								department = strIndexName;
							else if (k == classnoIndex)
								naturalclass = strIndexName;
							else if (k == numIndex)
								student_num = strIndexName;
							else if (k == nameIndex)
								name = strIndexName;

						}

					}

					if (naturalclass != null) {

						AddStudent(t_course_teaching_class_id, school, department, naturalclass, name, student_num);

					}

				}

			}

			fis.close();

		} catch (Exception e) {

			throw e;

		}

	}

	/**
	 * 从07-10格式的Excel(.xlsx)中导入学生
	 * 
	 * @throws Exception
	 * @throws StudentExistException
	 */
	private void ProcessExcel(String t_course_teaching_class_id, String localfilename) throws Exception {

		
		OPCPackage file;

		try {

			file = OPCPackage.open(localfilename);



			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file);

			for (XSSFSheet sheet : xssfWorkbook) {

				int rowstart = sheet.getFirstRowNum();
				int rowEnd = sheet.getLastRowNum();

				int schoolIndex = -1;
				int departIndex = -1;
				int classnoIndex = -1;
				int nameIndex = -1;
				int numIndex = -1;

				for (int i = rowstart; i <= rowEnd; i++) {

					Row row = sheet.getRow(i);
					if (null == row)
						continue;
					int cellStart = row.getFirstCellNum();
					int cellEnd = row.getLastCellNum();

					String school = null;
					String department = null;
					String name = null;
					String student_num = null;
					String naturalclass = null;

					for (int k = cellStart; k <= cellEnd; k++) {
						Cell cell = row.getCell(k);
						if (null == cell)
							continue;

						DataFormatter formatter = new DataFormatter();

						//String strIndexName = cell.getStringCellValue().trim();
						String strIndexName =formatter.formatCellValue(cell);
						

						if (i == rowstart) {

							if (strIndexName.equals("学院") || strIndexName.equals("院"))
								schoolIndex = k;
							else if (strIndexName.equals("系部") || strIndexName.equals("系") || strIndexName.equals("部"))
								departIndex = k;
							else if (strIndexName.equals("班号") || strIndexName.equals("班级") || strIndexName.equals("班"))
								classnoIndex = k;
							else if (strIndexName.equals("学号"))
								numIndex = k;
							else if (strIndexName.equals("姓名"))
								nameIndex = k;

						} else {
							if (k == schoolIndex)
								school = strIndexName;
							else if (k == departIndex)
								department = strIndexName;
							else if (k == classnoIndex)
								naturalclass = strIndexName;
							else if (k == numIndex)
								student_num = strIndexName;
							else if (k == nameIndex)
								name = strIndexName;

						}
					}

					if (naturalclass != null) {

						AddStudent(t_course_teaching_class_id, school, department, naturalclass, name, student_num);

					}

				}

			}

			file.close();

		} catch (InvalidFormatException | IOException e) {
			throw e;
		}

	}

	/**
	 * 添加学生
	 * 
	 * @param t_course_teaching_class_id
	 *            教学班id
	 * 
	 * @return User
	 * @throws Exception 
	 */
	public String AddStudent(String t_course_teaching_class_id, String school, String department, String naturalclass,
			String name, String student_num) throws Exception {

		String t_student_id = studentService.AddStudent(school, department, naturalclass, name, student_num);

		if (t_student_id == null)
			return null;

		int show_index = courseTeachingClassStudentDao
				.getShowIndexMaxByCourseTeachingClassId(t_course_teaching_class_id);
		show_index += 10;
		if (courseTeachingClassStudentDao.IsStudentExist(t_course_teaching_class_id, t_student_id)) {

			courseTeachingClassStudentDao.update(t_course_teaching_class_id, t_student_id, show_index);

		} else
			courseTeachingClassStudentDao.add(t_course_teaching_class_id, t_student_id, show_index);
		
		return t_student_id;

	}

	/**
	 * 
	 * 
	 * @param user
	 */
	public String add(String t_course_id, String name, String t_course_teaching_term_id, String[] teacherid,
			String[] teachingtypetypeId) {

		String t_course_teaching_class_id = courseTeachingClassDao.add(t_course_id, name, t_course_teaching_term_id);

		if (teacherid != null && teacherid.length > 0) {
			for (int i = 0; i < teacherid.length; i++) {
				courseTeachingClassTeacherDao.add(t_course_teaching_class_id, teacherid[i], teachingtypetypeId[i]);
			}
		}

		return t_course_teaching_class_id;

	}

	/**
	 * 将学生加入到教学班中
	 * 
	 * @param user
	 */
	public void add(String teachingclassid, String[] studentid) {

		int show_index = 10;
		for (String stuid : studentid) {
			
			courseTeachingClassStudentDao.add(teachingclassid, stuid, show_index);
			show_index += 10;
		}
	}

	/**
	 * 将教师加入到教学班中
	 * 
	 * @param user
	 */
	public void add(String t_course_teaching_class_id, String[] teacherid, String[] teachingtypeid) {

		// 1.删除旧的该课程所有授课教师信息
		courseTeachingClassTeacherDao.deleteByCourseTeachingClassId(t_course_teaching_class_id);

		if (teacherid == null || teachingtypeid == null)
			return;

		if (teacherid.length != teachingtypeid.length)
			return;

		// 2.增加新的授课教师信息
		for (int i = 0; i < teacherid.length; i++) {
			courseTeachingClassTeacherDao.add(t_course_teaching_class_id, teacherid[i], teachingtypeid[i]);
		}
	}

	/**
	 * 根据t_course_id删除教学-课程信息
	 */
	public void deleteByCourseId(HttpServletRequest request, String t_course_id) {
		List<CourseTeachingClass> listCourses = courseTeachingClassDao.getCourseTeachingClassByCourseId(t_course_id);
		if (listCourses == null)
			return;
		for (CourseTeachingClass c : listCourses) {
			String t_course_teaching_class_id = c.getId();
			deleteById(request, t_course_teaching_class_id);
		}
	}

	/**
	 * 根据t_teaching_class_id删除教学-课程信息
	 */
	public void deleteByCourseTeachingClassIdAndStudentId(HttpServletRequest request, String t_course_teaching_class_id,
			String t_student_id) {
		courseTeachingClassStudentDao.deleteByCourseTeachingClassIdAndStudentId(t_course_teaching_class_id, t_student_id);
	}

	/**
	 * 根据t_course_id、t_teaching_class_id删除教学-课程信息
	 */
	public void deleteByCourseIdAndTeachingClassId(HttpServletRequest request, String t_course_id,
			String t_teaching_class_id) {
		List<CourseTeachingClass> listCourses = courseTeachingClassDao
				.getCourseTeachingClassByCourseIdTeachingClassId(t_course_id, t_teaching_class_id);
		if (listCourses == null)
			return;
		for (CourseTeachingClass c : listCourses) {
			String t_course_teaching_class_id = c.getId();
			deleteById(request, t_course_teaching_class_id);
		}
	}

	/**
	 * 根据t_course_teaching_class_id删除教学-课程信息
	 */
	public void deleteById(HttpServletRequest request, String t_course_teaching_class_id) {
		// 教学班级-教师
		courseTeachingClassTeacherDao.deleteByCourseTeachingClassId(t_course_teaching_class_id);

		// 相关论坛
		courseTeachingClassForumTopicService.deleteByCourseTeachingClassID(request, t_course_teaching_class_id);

		// 课程相关资料
		courseTeachingClassReferenceService.deleteByCourseTeachingClassId(request, t_course_teaching_class_id);

		// 课程相关作业类型
		courseTeachingClassHomeworkService.deleteByCourseTeachingClassId(request, t_course_teaching_class_id);

		// 课程相关作业类型
		courseTeachingClassHomeworkTypeService.deleteByCourseTeachingClassId(request, t_course_teaching_class_id);

		// 课程-教师
		courseTeachingClassTeacherService.deleteByCourseTeachingClassId(request, t_course_teaching_class_id);

		// 课程相关考勤
		attendanceService.deleteByCourseTeachingClassId(t_course_teaching_class_id);
		
		//删除分组
		courseTeachingClassStudentGroupService.deleteByCourseTeachingClassId(t_course_teaching_class_id);

		// 删除自己
		courseTeachingClassDao.deleteById(t_course_teaching_class_id);
	}

	/**
	 * 
	 * 
	 * @param user
	 */
	public String update(String t_course_teaching_class_id, String t_ourse_id, String name,
			String t_course_teaching_term_id, String[] teacherid, String[] teachingtypetypeId) {

		CourseTeachingClass ctc = courseTeachingClassDao.getCourseTeachingClassById(t_course_teaching_class_id);
		if (ctc == null)
			return null;

		// 课程授课信息
		courseTeachingClassDao.update(t_course_teaching_class_id, t_ourse_id, name, t_course_teaching_term_id);

		// 授课教师信息
		add(t_course_teaching_class_id, teacherid, teachingtypetypeId);

		return t_course_teaching_class_id;

	}

	/**
	 * 得到
	 */
	public Page<CourseTeachingClassViewData> getPage(int pageNo, int pageSize) {
		return courseTeachingClassViewDataDao.getPage(pageNo, pageSize);
	}

	/**
	 * 得到课程全体教师视图
	 */
	public Page<TeacherViewData> getTeacherPage(String t_course_teaching_class_id) {
		return courseTeachingClassTeacherDao.getTeacherPage(t_course_teaching_class_id);
	}

	public CourseTeachingClassViewData GetTeachingClassViewDataByCourseTeachingClassId(
			String t_course_teaching_class_id) {
		return courseTeachingClassViewDataDao
				.GetTeachingClassViewDataByCourseTeachingClassId(t_course_teaching_class_id);
	}

	/**
	 * 得到课程全体教师视图 如果是学生的话，则会得到所有同他上课的授课教师 如果是教师的话，则会得到所有同他所教授课程相关的授课教师
	 */
	public Page<TeacherViewData> getAddTeacherPageByUserInfo(String t_user_id) {
		if (t_user_id == null)
			return null;

		List<TeacherViewData> data = new ArrayList<TeacherViewData>();

		// 得到该教师所有的教学班
		List<CourseTeachingClassViewData> listCourseTeachingClass = courseTeachingClassViewDataDao
				.getCourseTeachingClassViewDataByUserId(t_user_id);

		if (listCourseTeachingClass == null)
			return null;

		// 将每个教学班的教师添加到列表中，注意教师不能重复
		for (CourseTeachingClassViewData t : listCourseTeachingClass) {

			for (TeacherViewData temp : t.getTeacher()) {

				boolean bflag = true;
				for (TeacherViewData d : data) {
					if (d.getTeacher().getId() == temp.getTeacher().getId()) {
						bflag = false;
						break;
					}
				}

				if (bflag)
					data.add(temp);
			}
		}
		return new Page<TeacherViewData>(0, data.size(), data.size(), data);
	}

	public Page<StudentViewData> getAddStudentPageByUserInfo(String t_user_id) {
		if (t_user_id == null)
			return null;

		List<StudentViewData> data = new ArrayList<StudentViewData>();

		// 得到该教师所有的教学班,然后将每个教学班的教师添加到列表中
		List<CourseTeachingClassViewData> listCourseTeachingClass = courseTeachingClassViewDataDao
				.getCourseTeachingClassViewDataByUserId(t_user_id);

		if (listCourseTeachingClass == null)
			return null;
		
		

		for (CourseTeachingClassViewData c : listCourseTeachingClass) {

			
			// 得到每个教学班的列表
			List<StudentViewData> listStudentViewData = studentViewDataDao
					.getStudentViewByCourseTeachingClassId(c.getCourseTeachingClass().getId());

			for (StudentViewData s : listStudentViewData) {
				
				boolean bflag = true;
				for (StudentViewData temp : data) {
					if (temp.getStudent().getId() == s.getStudent().getId()) {
						bflag = false;
						break;
					}
				}
				if (bflag)
					data.add(s);
			}
		}
		return new Page<StudentViewData>(0, data.size(), data.size(), data);
	}

	public void ShowIndexMoveUp(String t_course_teaching_class_id, String t_student_id) {
		// 1.得到该学生
		CourseTeachingClassStudent stu = courseTeachingClassStudentDao
				.getTeachingClassStudentByTeachingClassIdAndStudentId(t_course_teaching_class_id, t_student_id);
		if (stu == null)
			return;

		// 2.得到前面一个学生
		int show_index_max = courseTeachingClassStudentDao
				.getShowIndexMaxLessthanByCourseTeachingClassId(t_course_teaching_class_id, t_student_id);
		CourseTeachingClassStudent pre = courseTeachingClassStudentDao
				.getTeachingClassStudentByTeachingClassIdAndShowIndex(t_course_teaching_class_id, show_index_max);
		if (pre == null)
			return;

		int temp = stu.getShowIndex();
		courseTeachingClassStudentDao.update(stu.getId(), show_index_max);
		courseTeachingClassStudentDao.update(pre.getId(), temp);

	}

	public void ShowIndexMoveDown(String t_course_teaching_class_id, String t_student_id) {
		// 1.得到该学生
		CourseTeachingClassStudent stu = courseTeachingClassStudentDao
				.getTeachingClassStudentByTeachingClassIdAndStudentId(t_course_teaching_class_id, t_student_id);
		if (stu == null)
			return;

		// 2.得到后面一个学生
		int show_index_min = courseTeachingClassStudentDao
				.getShowIndexMinMorethanByCourseTeachingClassId(t_course_teaching_class_id, t_student_id);
		CourseTeachingClassStudent next = courseTeachingClassStudentDao
				.getTeachingClassStudentByTeachingClassIdAndShowIndex(t_course_teaching_class_id, show_index_min);
		if (next == null)
			return;

		int temp = stu.getShowIndex();
		courseTeachingClassStudentDao.update(stu.getId(), show_index_min);
		courseTeachingClassStudentDao.update(next.getId(), temp);

	}

}
