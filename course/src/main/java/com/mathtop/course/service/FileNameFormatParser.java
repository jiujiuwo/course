package com.mathtop.course.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mathtop.course.dao.StudentViewDataDao;
import com.mathtop.course.dao.CourseTeachingClassViewDataDao;
import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfo;
import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfoViewData;
import com.mathtop.course.domain.CourseTeachingClassViewData;
import com.mathtop.course.domain.FileNameFormatOperator;
import com.mathtop.course.domain.FileNodePropertyData;
import com.mathtop.course.domain.FileNodePropertyManager;
import com.mathtop.course.domain.FileRequirementManager;
import com.mathtop.course.domain.FileRequirementNode;
import com.mathtop.course.domain.FileSubmitData;
import com.mathtop.course.domain.StudentViewData;
import com.mathtop.course.domain.TeacherViewData;

/**
 * 文件格式
 * 
 * 支持下列操作符 {学院}:学生所在的学院 {系别}:学生所在的系别 {教学班}:学生所在的教学班 {自然班}:学生所在的自然班（行政班）
 * {学号}:学生的学号 {姓名}:学生的姓名 {作业类型}:本次作业的类型 {作业名称}:本次作业的名称 {数字}:数字，从0开始的整数，不能是负数或小数。
 * {?}:一个字符 {*}:任意长度的字符
 */
@Service
public class FileNameFormatParser {

	@Autowired
	CourseTeachingClassHomeworkService courseTeachingClassHomeworkService;

	@Autowired
	StudentViewDataDao studentDao;

	@Autowired
	CourseTeachingClassViewDataDao teachingClassViewDataDao;

	private class TokenValue {
		private String value;
		private String strNext;
		private boolean operator;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getStrNext() {
			return strNext;
		}

		public void setStrNext(String strNext) {
			this.strNext = strNext;
		}

		public boolean isOperator() {
			return operator;
		}

		public void setOperator(boolean operator) {
			this.operator = operator;
		}

		
		
	};

	// 判定操作符是否正确
	private boolean IsRightOperator(String op) {
		for (String s : FileNameFormatOperator.operators) {
			if (s.equals(op))
				return true;
		}
		return false;
	}

	// 返回下一个操作符
	private TokenValue getNextOperator(String strFormat) {

		String value = "";
		boolean flag = false;
		int nSize = strFormat.length();
		for (int i = 0; i < nSize; i++) {
			switch (strFormat.charAt(i)) {
			case '{':
				flag = true;
				value += strFormat.charAt(i);
				break;
			case '}':
				if (flag) {
					value += strFormat.charAt(i);
					TokenValue result = new TokenValue();
					result.setValue(value);
					result.setOperator(true);

					result.setStrNext(strFormat.substring(i + 1));
					return result;
				}
				break;
			default:
				if (flag)
					value += strFormat.charAt(i);
				break;

			}
		}

		return null;
	}

	/**
	 * 是否符合格式 只有支持的操作符才能够识别
	 */
	public boolean IsFileNameFormatRight(String strFormat) {

		TokenValue tv = getNextOperator(strFormat);
		while (tv != null) {
			if (!IsRightOperator(tv.getValue()))
				return false;

			strFormat = tv.getStrNext();
			tv = getNextOperator(strFormat);
		}
		return true;
	}

	private String getValue(String strOperator, CourseTeachingClassHomeworkBaseinfoViewData homeworkviewdata,
			CourseTeachingClassViewData courseteachingclassviewdata, StudentViewData student) {
		String filename = "";
		switch (strOperator) {
		case FileNameFormatOperator.operator_school: // 学院
			filename += student.getSchool().getName();
			break;
		case FileNameFormatOperator.operator_department:
			filename += student.getDepartment().getName();
			break;
		case FileNameFormatOperator.operator_course_name:// 课程名
			filename = courseteachingclassviewdata.getCourse().getName();
			break;
		case FileNameFormatOperator.operator_course_teaching_class_name:// 教学班
			filename += courseteachingclassviewdata.getCourseTeachingClass().getName();
			break;
		case FileNameFormatOperator.operator_course_teaching_class_year:// 学年
			filename += (courseteachingclassviewdata.getTerm().getTeachingYearBegin() + "-"
					+ courseteachingclassviewdata.getTerm().getTeachingYearEnd() + "学年");
			break;
		case FileNameFormatOperator.operator_course_teaching_class_term:// 学期
			filename += ("第" + courseteachingclassviewdata.getTerm().getTeachingTerm() + "学期");
			break;
		case FileNameFormatOperator.operator_course_teaching_class_tercher_name:// 教学班教师
		{
			TeacherViewData[] tvds = courseteachingclassviewdata.getTeacher();
			int i = 0;
			for (TeacherViewData v : tvds)
				if (i++ == 0)
					filename += v.getUserbasicinfo().getUserBasicInfoName();
				else
					filename += ("-" + v.getUserbasicinfo().getUserBasicInfoName());
		}
		case FileNameFormatOperator.operator_natural_class:
			filename += student.getNaturalclass().getName();
			break;
		case FileNameFormatOperator.operator_student_num:
			filename += student.getStudent().getStudentNum();
			break;
		case FileNameFormatOperator.operator_student_name:
			filename += student.getUserbasicinfo().getUserBasicInfoName();
			break;
		case FileNameFormatOperator.operator_homework_type:
			filename += homeworkviewdata.getHomeworkType().getName();
			break;
		case FileNameFormatOperator.operator_homework_name:
			filename += homeworkviewdata.getHomeworkbaseinfo().getTitle();
			break;
		case FileNameFormatOperator.operator_num:
			filename += "1";
			break;
		case FileNameFormatOperator.operator_single_char:
			break;
		case FileNameFormatOperator.operator_multiple_char:
			break;
		}
		return filename;
	}

	// 返回下一个操作符
	private TokenValue getNextToken(String strFormat) {

		String value = "";
		boolean flag = false;
		int nSize = strFormat.length();
		for (int i = 0; i < nSize; i++) {
			switch (strFormat.charAt(i)) {
			case '{':
				if (!flag && !value.isEmpty()) {
					// 将操作符前面的非操作符取出并返回
					TokenValue result = new TokenValue();
					result.setValue(value);

					result.setStrNext(strFormat.substring(i));
					return result;
				}
				flag = true;
				value += strFormat.charAt(i);

				break;
			case '}':
				value += strFormat.charAt(i);
				if (flag) {
					TokenValue result = new TokenValue();
					result.setValue(value);
					result.setOperator(true);

					result.setStrNext(strFormat.substring(i + 1));
					return result;
				}

				break;
			default:
				value += strFormat.charAt(i);

				break;

			}
		}

		return null;
	}

	// 生成正确的示例性的文件名字
	public List<String> getRightExampleFileName(String t_course_teaching_class_homework_baseinfo_id, int nodeID,
			String t_student_id) {

		if (t_course_teaching_class_homework_baseinfo_id == null || nodeID < 0 || t_student_id == null)
			return null;

		// 作业基本信息
		CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo = courseTeachingClassHomeworkService
				.getByID(t_course_teaching_class_homework_baseinfo_id);
		if (homeworkbaseinfo == null)
			return null;

		CourseTeachingClassHomeworkBaseinfoViewData homeworkviewdata = courseTeachingClassHomeworkService
				.getCourseTeachingClassHomeworkBaseinfoViewDataByID(t_course_teaching_class_homework_baseinfo_id);
		if (homeworkviewdata == null)
			return null;

		CourseTeachingClassViewData courseteachingclassviewdata = teachingClassViewDataDao
				.GetTeachingClassViewDataByCourseTeachingClassId(homeworkbaseinfo.getCourseTeachingClassId());

		// 学生信息
		StudentViewData student = studentDao.getStudentViewDataById(t_student_id);
		if (student == null)
			return null;

		// 不需要文件
		if (homeworkbaseinfo.getFileRequirement().isEmpty())
			return null;

		FileRequirementNode data = homeworkbaseinfo.getFileRequirement().getNode(nodeID);

		if (data == null)
			return null;

		List<String> list = new ArrayList<String>();

		String filetype = data.getData().getFileTypeData();
		String strFormat = data.getData().getFilenameRequirementVal();
		String filename = "";

		TokenValue tv = getNextToken(strFormat);
		while (tv != null) {
			if (tv.isOperator()) {// 操作符
				filename += getValue(tv.getValue(), homeworkviewdata, courseteachingclassviewdata, student);
			} else {// 非操作符

				filename += tv.getValue();

			}

			strFormat = tv.getStrNext();
			tv = getNextToken(strFormat);
		}

		// 根据后缀生成不同文件，例如后缀是"*.doc;*.docx"，
		// filename为"A"，则合法的文件名为"A.doc"或者"A.docx"

		String filetypes[] = filetype.split(";");
		for (String t : filetypes) {
			int index = t.lastIndexOf('.');
			if (index < 0) {
				index = 0;
				if (!t.equals("*")) {
					list.add(filename + "." + t);
				}
			} else {
				String s = t.substring(index);
				if (!s.equals(".*")) {
					list.add(filename + s);
				}
			}

		}

		return list;
	}

	private class ContainValue {
		private String next;
		private boolean flag;

		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

		public String getNext() {
			return next;
		}

		public void setNext(String next) {
			this.next = next;
		}
	}

	private int findnum(String strFilename) {
		int i = 0;
		for (i = 0; i < strFilename.length(); i++)
			if (strFilename.charAt(0) >= '0' || strFilename.charAt(0) <= '9')
				return i;
		return -1;
	}

	// 去除开头的数字
	private ContainValue trimnum(String strFilename, boolean bMultiplechar) {
		ContainValue v = new ContainValue();

		if (strFilename.length() == 0) {
			v.setFlag(false);
			return v;
		}
		if (!bMultiplechar) {
			// 不跨越字符
			if (strFilename.charAt(0) < '0' || strFilename.charAt(0) > '9') {
				v.setFlag(false);
				return v;
			}

			int i = 0;
			for (i = 0; i < strFilename.length(); i++)
				if (strFilename.charAt(i) < '0' || strFilename.charAt(i) > '9')
					break;

			v.setFlag(true);
			v.setNext(strFilename.substring(i));
		} else {
			// 跨过任意个字符
			int beginindex = findnum(strFilename);
			if (beginindex == -1) {
				v.setFlag(false);
				return v;
			}

			int i = beginindex;
			for (i = beginindex; i < strFilename.length(); i++)
				if (strFilename.charAt(i) < '0' || strFilename.charAt(i) > '9')
					break;

			v.setFlag(true);
			v.setNext(strFilename.substring(i));
		}
		return v;
	}

	// 去除开头的一个特定字符
	private ContainValue trimsinglechar(String strFilename, char c, boolean bMultiplechar) {
		ContainValue v = new ContainValue();

		if (strFilename.length() == 0) {
			v.setFlag(false);
			return v;
		}

		if (!bMultiplechar) {
			if (strFilename.charAt(0) != c) {
				v.setFlag(false);
				return v;
			}

			v.setFlag(true);
			v.setNext(strFilename.substring(1));
		} else {
			int beginindex = strFilename.indexOf(c);
			if (beginindex == -1) {
				v.setFlag(false);
				return v;
			}

			v.setFlag(true);
			v.setNext(strFilename.substring(beginindex + 1));

		}
		return v;
	}

	// 去除开头的一个字符
	private ContainValue trimsinglechar(String strFilename, boolean bMultiplechar) {
		ContainValue v = new ContainValue();

		if (strFilename.length() == 0) {
			v.setFlag(false);
			return v;
		}

		v.setFlag(true);
		v.setNext(strFilename.substring(1));
		return v;
	}

	// 去除特定前缀
	private ContainValue trimprefix(String strFilename, String strSubFileName, boolean bMultiplechar) {

		ContainValue v = new ContainValue();
		int nsize = strSubFileName.length();

		if (nsize == 0) {
			v.setFlag(true);
			v.setNext(strFilename);
			return v;
		}
		if (bMultiplechar) {
			int beginindex = strFilename.indexOf(strSubFileName);
			if (beginindex == -1) {
				v.setFlag(false);
				return v;
			}

			v.setFlag(true);
			v.setNext(strFilename.substring(beginindex + nsize));

		} else {

			if (strFilename.substring(0, nsize).equals(strSubFileName)) {
				v.setFlag(true);
				v.setNext(strFilename.substring(nsize));

			} else {
				v.setFlag(false);
			}
		}
		return v;
	}

	/**
	 * 文件名是否正确，例如要求按照{班级}_{学号}格式上传，是否符合该格式
	 */
	private boolean IsSubmitFileNamePrefixRight(FileSubmitData fileSubmitData,
			FileRequirementManager fileRequirementManager, CourseTeachingClassHomeworkBaseinfoViewData homeworkviewdata,
			CourseTeachingClassViewData courseteachingclassviewdata, StudentViewData student) {

		
		if (fileSubmitData == null)
			return false;

		if (fileRequirementManager == null)
			return false;

		FileRequirementNode fileRequirementNode = fileRequirementManager.getNode(fileSubmitData.getNodeID());

		if (fileRequirementNode == null)
			return false;
		// 文件名称不受限制
		if (fileRequirementNode.getData().isFileTypeNotLimited())
			return true;
		String strFormat = fileRequirementNode.getData().getFilenameRequirementVal();
		String filename = fileSubmitData.getFileName();
	
		
		
		TokenValue tv = getNextToken(strFormat);
		ContainValue v;
		boolean bMultiplechar = false;
		while (tv != null) {
			if (tv.isOperator()) {// 操作符
				switch (tv.getValue()) {
				case FileNameFormatOperator.operator_num:// 数字
					v = trimnum(filename, bMultiplechar);
					if (v.isFlag())
						filename = v.getNext();
					else
						return false;// 错误，不符合要求
					bMultiplechar = false;

					break;
				case FileNameFormatOperator.operator_single_char:// 单个字母
					v = trimsinglechar(filename, bMultiplechar);
					if (v.isFlag())
						filename = v.getNext();
					else
						return false;// 错误，不符合要求
					bMultiplechar = false;
					break;
				case FileNameFormatOperator.operator_multiple_char:// 多个字符
					bMultiplechar = true;
					break;
				default:
					String s = getValue(tv.getValue(), homeworkviewdata, courseteachingclassviewdata, student);
					
					v = trimprefix(filename, s, bMultiplechar);
					if (v.isFlag())
						filename = v.getNext();
					else
						return false;// 错误，不符合要求
					bMultiplechar = false;
					break;
				}

			} else {// 非操作符
				String s = tv.getValue();
				
				v = trimprefix(filename, s, bMultiplechar);
				if (v.isFlag())
					filename = v.getNext();
				else
					return false;// 错误，不符合要求

				bMultiplechar = false;

			}
			
			

			strFormat = tv.getStrNext();
			tv = getNextToken(strFormat);
		}
		
		//filename现在应该是后缀，判断是否正确
		

		return IsSubmitFileNamePoxfixRight(filename,fileSubmitData,fileRequirementManager);
	}

	/**
	 * 文件后缀是否正确，例如要求doc，用户上传了xls文件则错误
	 */
	private boolean IsSubmitFileNamePoxfixRight(String prefix,FileSubmitData fileSubmitData,
			FileRequirementManager fileRequirementManager) {

		if (fileSubmitData == null)
			return false;

		if (fileRequirementManager == null)
			return false;

		FileRequirementNode fileRequirementNode = fileRequirementManager.getNode(fileSubmitData.getNodeID());

		if (fileRequirementNode == null)
			return false;

		String strFormat = fileRequirementNode.getData().getFileTypeData();
		

		

		prefix = prefix.toLowerCase();
		if(prefix.length()>0 && prefix.charAt(0)=='.')
			prefix=prefix.substring(1);

		String filetypes[] = strFormat.split(";");

		for (String t : filetypes) {

			int index = t.lastIndexOf('.');
			if (index < 0) {
				index = 0;
				if (t.equals("*")) {
					return true;
				} else if (t.equals(prefix))
					return true;
			} else {
				String s = t.substring(index + 1);

				if (s.equals("*"))
					return true;
				else if (s.equals(prefix))
					return true;
			}
		}

		return false;
	}

	/**
	 * */
	private boolean IsSubmitFileNameFormatRight(List<FileSubmitData> lstFileNames,
			FileNodePropertyManager fileNodePropertyManager,
			CourseTeachingClassHomeworkBaseinfoViewData homeworkviewdata,
			CourseTeachingClassViewData courseteachingclassviewdata, StudentViewData student) {

		
		if(lstFileNames==null)
			return false;
		
		
		if (fileNodePropertyManager == null)
			return false;
		

		FileRequirementManager fileRequirementManager = homeworkviewdata.getHomeworkbaseinfo().getFileRequirement();

		for (FileSubmitData fileSubmitData : lstFileNames) {
			
			

			if (!IsSubmitFileNamePrefixRight(fileSubmitData, fileRequirementManager, homeworkviewdata,
					courseteachingclassviewdata, student))
				return false;
		}
		return true;
	}

	private List<FileSubmitData> getFileSubmitDataList(FileNodePropertyManager fileNodePropertyManager,
			MultipartFile[] files) {
		
		List<FileSubmitData> lstFiles = new ArrayList<>();
		
		for (int i = 0; i < files.length; i++) {			
		
			FileSubmitData data = new FileSubmitData();
			data.setFile(files[i]);
			
			FileNodePropertyData fileNodePropertyData = fileNodePropertyManager.getNodeByFileIndex(i);
			
			if (fileNodePropertyData == null)
				return null;
			
			data.setNodeID(fileNodePropertyData.getNodeID());
			lstFiles.add(data);
		}

		return lstFiles;
	}

	/**
	 * 提交的文件个数是否符合要求,符合要求返回true,否则返回false
	 */
	public boolean IsSubmitFileCountRight(HttpServletRequest request, FileNodePropertyManager fileNodePropertyManager,
			MultipartFile[] files, String t_course_teaching_class_homework_baseinfo_id) {

		if (fileNodePropertyManager == null)
			return false;

		// 作业基本信息
		CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo = courseTeachingClassHomeworkService
				.getByID(t_course_teaching_class_homework_baseinfo_id);
		if (homeworkbaseinfo == null)
			return false;

		return homeworkbaseinfo.getFileRequirement().isFileCountRight(fileNodePropertyManager);
	}

	/**
	 * 提交的文件是否有为空的文件,不为空，则发挥true，否则返回false
	 */

	public boolean IsSubmitFileContentIsNotNull(HttpServletRequest request, MultipartFile[] files) {

		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];

			if (file.getOriginalFilename().length() > 0 && file.getSize() == 0)
				return false;

		}

		return true;
	}

	/**
	 * 提交的文件名称是否符合要求,符合要求返回true,否则返回false
	 */
	public boolean IsSubmitFileNameFormatRight(HttpServletRequest request,
			FileNodePropertyManager fileNodePropertyManager, MultipartFile[] files,
			String t_course_teaching_class_homework_baseinfo_id, String t_student_id) {

		if (fileNodePropertyManager == null)
			return false;

		if (t_course_teaching_class_homework_baseinfo_id == null)
			return false;

		if (t_student_id == null)
			return false;

		// 作业基本信息
		CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo = courseTeachingClassHomeworkService
				.getByID(t_course_teaching_class_homework_baseinfo_id);
		if (homeworkbaseinfo == null)
			return false;

		CourseTeachingClassHomeworkBaseinfoViewData homeworkviewdata = courseTeachingClassHomeworkService
				.getCourseTeachingClassHomeworkBaseinfoViewDataByID(t_course_teaching_class_homework_baseinfo_id);
		if (homeworkviewdata == null)
			return false;

		CourseTeachingClassViewData courseteachingclassviewdata = teachingClassViewDataDao
				.GetTeachingClassViewDataByCourseTeachingClassId(homeworkbaseinfo.getCourseTeachingClassId());

		// 学生信息
		StudentViewData student = studentDao.getStudentViewDataById(t_student_id);
		if (student == null)
			return false;
		
		

		// 不需要文件
		if (homeworkbaseinfo.getFileRequirement().isEmpty())
			return true;
		
		

		List<FileSubmitData> lstFiles = getFileSubmitDataList(fileNodePropertyManager, files);

		if (!IsSubmitFileNameFormatRight(lstFiles, fileNodePropertyManager, homeworkviewdata,
				courseteachingclassviewdata, student))
			return false;

		return true;
	}
}
