package com.mathtop.course.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.mathtop.course.dao.DepartmentNaturalClassDao;
import com.mathtop.course.dao.NaturalClassStudentDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.dao.StudentDao;
import com.mathtop.course.dao.StudentViewDataDao;
import com.mathtop.course.dao.UserBasicInfoDao;
import com.mathtop.course.dao.UserContactInfoDao;
import com.mathtop.course.dao.UserContactInfoViewDataDao;
import com.mathtop.course.dao.UserDao;
import com.mathtop.course.dao.UserGroupDao;
import com.mathtop.course.domain.Student;
import com.mathtop.course.domain.StudentViewData;
import com.mathtop.course.domain.User;
import com.mathtop.course.domain.UserBasicInfo;
import com.mathtop.course.domain.UserContactInfo;
import com.mathtop.course.exception.NaturalClassNotExistException;
import com.mathtop.course.exception.StudentExistException;
import com.mathtop.course.utility.DateTimeSql;

@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserContactInfoDao usercontactinfoDao;

	@Autowired
	UserContactInfoViewDataDao usercontactinfoviewDao;

	@Autowired
	private UserBasicInfoDao userbasicinfoDao;

	@Autowired
	private StudentViewDataDao studentviewdataDao;

	@Autowired
	NaturalClassService naturalClassService;

	@Autowired
	UserGroupDao usergroupDao;

	@Autowired
	NaturalClassStudentDao naturalclassstudentDao;

	@Autowired
	DepartmentNaturalClassDao naturalclassschoolDao;

	@Autowired
	SchoolService schoolService;

	@Autowired
	DepartmentService departmentService;

	@Autowired
	CourseTeachingClassDao courseteachingclassDao;

	/**
	 * 从Excel上传学生清单，然后添加到数据库中
	 */
	public void UploadFromExcel(String[] groupId, MultipartFile file) throws Exception {
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
					ProcessExcel97(localfilename, groupId);
				else if (prefix.equals("xlsx"))
					ProcessExcel(localfilename, groupId);

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
	private void ProcessExcel97(String localfilename, String[] groupId) throws Exception {

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

						AddStudent(school, department, naturalclass, name, student_num, groupId);

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
	private void ProcessExcel(String localfilename, String[] groupId) throws Exception {

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

						AddStudent(school, department, naturalclass, name, student_num, groupId);

					}

				}

			}

			file.close();

		} catch (InvalidFormatException | IOException e) {
			throw e;
		}

	}

	/**
	 * 注册一个新用户,如果用户名已经存在此抛出UserExistException的异常
	 * 
	 * @param user
	 */
	public String insert(Student stu) throws StudentExistException {
		Student u = this.getStudentByStudentNum(stu.getStudentNum());

		if (u != null) {

			throw new StudentExistException("学生学号已经存在");
		} else {

			return studentDao.add(stu);
		}
	}

	/**
	 * 根据用户名/密码查询 User对象
	 * 
	 * @param userName
	 *            用户名
	 * @return User
	 */
	public Student getStudentByStudentNum(String student_num) {
		return studentDao.getStudentByStudentNum(student_num);
	}

	public Student getStudentByStudentId(String t_student_id) {
		return studentDao.getStudentByID(t_student_id);
	}

	/**
	 * 更新学生信息,更新内容较为简单，仅包括基本信息
	 */
	public void UpdateStudentInfo(String t_user_id, String user_basic_info_birthday, String user_basic_info_sex,
			String[] contacttypeId, String[] user_contact_value) {

		userbasicinfoDao.UpdateByt_user_id(t_user_id, user_basic_info_birthday, user_basic_info_sex);

		usercontactinfoDao.UpdateContactInfo(t_user_id, contacttypeId, user_contact_value);

	}

	/**
	 * 更新学生信息，更新内容全面
	 */
	public void UpdateStudentInfo(String t_user_id, String user_contact_info_name, String user_basic_info_birthday,
			String user_basic_info_sex, String[] contacttypeId, String[] user_contact_value) {

		userbasicinfoDao.UpdateByt_user_id(t_user_id, user_contact_info_name, user_basic_info_birthday,
				user_basic_info_sex);

		usercontactinfoDao.UpdateContactInfo(t_user_id, contacttypeId, user_contact_value);
	}

	/**
	 * 学生是否存在
	 */
	public String isExist(String t_natural_class_id, String student_name, String student_num) {

		return naturalclassstudentDao.getStudentId(t_natural_class_id, student_num);
	}
	
	
	/**
	 * 根据学号查询学生是否存在
	 * @param student_name
	 * @param student_num
	 * @return 如果学生存在，则返回该学生的id，否则返回null
	 */
	public String isExist(String student_num) {

		Student student= studentDao.getStudentByStudentNum(student_num);
		if(student!=null)
			return student.getId();
		return null;
	}

	/**
	 * 添加学生
	 * 
	 * @param t_natural_class_id
	 *            自然班id
	 * @param user_password
	 *            用户名密码
	 * @param student_num
	 *            学号
	 * @param userBasicInfoName
	 *            姓名
	 * @param user_basic_info_birthday
	 *            生日
	 * @param user_basic_info_sex
	 *            性别
	 * @param contacttypeId
	 *            联系类型id
	 * @param user_contact_value
	 *            联系类型值
	 * @param groupId
	 *            组
	 * 
	 * @return t_student_id
	 */
	public String AddStudent(String t_natural_class_id, String user_password, String student_num,
			String userBasicInfoName, String user_basic_info_birthday, String user_basic_info_sex,
			String[] contacttypeId, String[] user_contact_value, String[] groupId) throws Exception {

		if (t_natural_class_id == null)
			return null;

		// 学生存在则不添加
		String t_student_id = isExist(t_natural_class_id, userBasicInfoName, student_num);

		if (t_student_id != null)
			throw new StudentExistException("学生学号已经存在");

		// user基本信息
		User user = new User();
		user.setUserName(student_num);
		user.setUserPassword(user_password);

		Student stu = new Student();
		stu.setStudentNum(student_num);
		stu.setNaturalClassId(t_natural_class_id);

		UserBasicInfo userbasicinfo = new UserBasicInfo();
		userbasicinfo.setUserBasicInfoName(userBasicInfoName);
		userbasicinfo.setUserBasicInfoBirthday(DateTimeSql.GetDate(user_basic_info_birthday));
		userbasicinfo.setUserBasicInfoSex(Integer.parseInt(user_basic_info_sex));

		// 添加用户
		user.EncoderPassword();
		String t_user_id = userDao.add(user);

		// 添加student
		stu.setUserId(t_user_id);
		t_student_id = studentDao.add(stu);

		// 添加用户基本信息
		userbasicinfo.setUserId(t_user_id);
		userbasicinfoDao.add(userbasicinfo);

		usercontactinfoDao.UpdateContactInfo(t_user_id, contacttypeId, user_contact_value);

		if (groupId != null) {
			for (String gid : groupId) {
				usergroupDao.add(gid, t_user_id);
			}
		}

		return t_student_id;

	}

	/**
	 * 添加学生
	 * 
	 * @throws Exception
	 */

	public String AddStudent(String schoolName, String departmentName, String naturalclassname, String student_name,
			String student_num) throws Exception {

		// 必须有学号
		if (student_num == null || student_num.length() == 0)
			return null;

		// 必须有姓名
		if (student_name == null || student_name.length() == 0)
			return null;

		if (schoolName != null && schoolName.trim().length() == 0)
			schoolName = null;

		if (departmentName != null && departmentName.trim().length() == 0)
			departmentName = null;

		if (naturalclassname != null && naturalclassname.trim().length() == 0)
			naturalclassname = null;

		// 取得班级

		String t_natural_class_id = naturalClassService.getNaturalClassId(schoolName, departmentName, naturalclassname);

		if (t_natural_class_id == null)
			return null;

		// 学生存在则不添加
		String t_student_id = isExist(student_num);
		
		if (t_student_id != null)
			return t_student_id;
		
		t_student_id = isExist(t_natural_class_id, student_name, student_num);

		if (t_student_id != null)
			return t_student_id;
		
		

		try {

			t_student_id = AddStudent(t_natural_class_id, student_num, student_num, student_name, "1980-01-01", "0",
					null, null, null);
		} catch (StudentExistException e) {
			throw e;
		}
		return t_student_id;

	}

	/**
	 * 添加学生
	 * 
	 * @throws Exception
	 */
	public String AddStudent(String schoolName, String departmentName, String naturalclassname, String name,
			String student_num, String[] groupId) throws Exception {

		String t_student_id = AddStudent(schoolName, departmentName, naturalclassname, name, student_num);

		if (t_student_id == null)
			return null;

		Student stu = studentDao.getStudentByID(t_student_id);
		if (stu == null)
			return null;

		if (groupId != null) {
			for (String gid : groupId) {
				usergroupDao.add(gid, stu.getUserId());
			}
		}

		return t_student_id;

	}

	public void updateStudent(Student student, UserBasicInfo userbasicinfo, UserContactInfo[] usercontactinfos,
			String[] groupId) {

		if (student == null)
			return;
		String t_student_id = student.getId();

		if (t_student_id == null)
			return;
		String student_num = student.getStudentNum();

		student = studentDao.getStudentByID(t_student_id);
		String t_user_id = student.getUserId();
		try {

			// 修改学生
			studentDao.UpdateStudentNumById(t_student_id, student_num);

			// 修改用户基本信息
			userbasicinfo.setUserId(t_user_id);
			userbasicinfoDao.UpdateByt_user_id(t_user_id, userbasicinfo);

			// 修改联系方式

			usercontactinfoDao.UpdateContactInfo(t_user_id, usercontactinfos);

			// groupid
			usergroupDao.update(t_user_id, groupId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 根据学号得到学生视图
	 */
	public StudentViewData getStudentViewByStudentNum(String student_num) {
		return studentviewdataDao.getStudentViewDataById(student_num);
	}

	/**
	 * 根据学生id得到学生视图
	 */
	public StudentViewData getStudentViewByStudentId(String t_student_id) {
		return studentviewdataDao.getStudentViewDataById(t_student_id);
	}

	/**
	 * 根据用户id得到学生视图
	 */
	public StudentViewData getStudentViewByUserId(String t_user_id) {
		return studentviewdataDao.getStudentViewDataByUserId(t_user_id);
	}

	/**
	 * 得到学院全体学生视图
	 */
	public Page<StudentViewData> getPageByt_natural_class_id(String t_naturalclass_id, int pageNo, int pageSize) {
		return studentviewdataDao.getPageByt_natural_class_id(t_naturalclass_id, pageNo, pageSize);
	}

	/**
	 * 得到教学班全体学生视图
	 */
	public Page<StudentViewData> getPageByCourseTeachingClassId(String t_course_teaching_class_id, int pageNo,
			int pageSize) {
		return studentviewdataDao.getPageByCourseTeachingClassId(t_course_teaching_class_id, pageNo, pageSize);
	}

	public Page<StudentViewData> getPageByCourseTeachingClassId(String t_course_teaching_class_id) {
		return studentviewdataDao.getPageByCourseTeachingClassId(t_course_teaching_class_id);
	}

	/**
	 * 得到系部全体学生视图
	 */
	public List<StudentViewData> getStudentViewByt_department_id(String deptid) {
		List<StudentViewData> list = new ArrayList<StudentViewData>();
		return list;
	}

	/**
	 * 得到班级全体学生视图
	 */
	public List<StudentViewData> getStudentViewByt_natural_class_id(String t_naturalclass_id) {
		return studentviewdataDao.getStudentViewByt_natural_class_id(t_naturalclass_id);
	}

	/**
	 * 删除学生，需要把相关所有同学生有关信息全部删除，然后才能够删除
	 */
	public void deleteById(String t_student_id) {
		studentDao.deleteById(t_student_id);
	}

	/**
	 * 得到所有未分组的学生
	 */
	public List<StudentViewData> getNotGroupedStudent(String t_course_teaching_class_id) {
		return studentviewdataDao.getNotGroupedStudentViewByCourseTeachingClassId(t_course_teaching_class_id);
	}
}
