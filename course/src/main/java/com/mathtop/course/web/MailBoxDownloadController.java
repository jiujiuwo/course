package com.mathtop.course.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mathtop.course.cons.RealPathConst;
import com.mathtop.course.domain.MailBoxReceivedFile;
import com.mathtop.course.domain.MailBoxSendFile;
import com.mathtop.course.service.MailBoxReceivedService;
import com.mathtop.course.service.MailBoxSendService;

/**
 * 下载课程-作业附件
 * */

@Controller
@RequestMapping("/mailboxfile")
public class MailBoxDownloadController extends CourseTeachingClassBaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private MailBoxSendService sendService;
	
	@Autowired
	private MailBoxReceivedService receivedService;
	

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/downloadsend-{t_mail_box_send_id}")
	public ResponseEntity<byte[]> downloadsend(HttpServletRequest request, @PathVariable String t_mail_box_send_id)
			throws IOException {

		MailBoxSendFile plan  =sendService.getFileByID(t_mail_box_send_id);
		if (plan == null)
			return null;

		ServletContext sc = request.getSession().getServletContext();
		String dir = sc.getRealPath(RealPathConst.RealPath_MailBoxFile); // 设定文件保存的目录
		String filename = plan.getFilename();
		String path = dir + RealPathConst.RealPath_PathSeparator + plan.getFilepath();
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();
		String fileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}
	
	
	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/downloadreceived-{t_mail_box_received_id}")
	public ResponseEntity<byte[]> downloadreceived(HttpServletRequest request, @PathVariable String t_mail_box_received_id)
			throws IOException {

		MailBoxReceivedFile plan  =receivedService.getFileByID(t_mail_box_received_id);
		if (plan == null)
			return null;

		ServletContext sc = request.getSession().getServletContext();
		String dir = sc.getRealPath(RealPathConst.RealPath_MailBoxFile); // 设定文件保存的目录
		String filename = plan.getFilename();
		String path = dir + RealPathConst.RealPath_PathSeparator + plan.getFilepath();
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();
		String fileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}

}
