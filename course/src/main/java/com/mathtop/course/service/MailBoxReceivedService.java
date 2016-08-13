package com.mathtop.course.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.cons.RealPathConst;
import com.mathtop.course.dao.MailBoxReceivedDao;
import com.mathtop.course.dao.MailBoxReceivedFileDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.MailBoxReceived;
import com.mathtop.course.domain.MailBoxReceivedFile;
import com.mathtop.course.domain.MailBoxReceivedViewData;
import com.mathtop.course.domain.MailBoxState;

@Service
public class MailBoxReceivedService {

	@Autowired
	MailBoxReceivedDao maiboxdao;

	@Autowired
	MailBoxReceivedFileDao fileDao;

	public MailBoxReceivedFile getFileByID(String id) {
		return fileDao.getByID(id);
	}

	private void addFiles(HttpServletRequest request, String t_mail_box_id, List<MailBoxFileName> list) {
		ServletContext sc = request.getSession().getServletContext();
		String dir = sc.getRealPath(RealPathConst.RealPath_MailBoxFile); // 设定文件保存的目录
		
		for(MailBoxFileName f:list){
			
			 File srcFile = new File(dir + RealPathConst.RealPath_PathSeparator +f.getIDFileName());
			 File destFile = new File(dir + RealPathConst.RealPath_PathSeparator +f.getID0FileName());
			try {
				FileUtils.copyFile(srcFile, destFile);
				fileDao.add(t_mail_box_id, f.getOriginalFilename(), f.getID0FileName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}

	// 增加
	public void add(HttpServletRequest request, String t_user_id_from, String t_user_id_to,
			  String subject, String content, 
			  List<MailBoxFileName> list) {
		
		MailBoxState state=new MailBoxState();

		String t_mail_box_send_id = maiboxdao.add(t_user_id_from, t_user_id_to,
				state.getState(),  subject, content);

		addFiles(request, t_mail_box_send_id, list);

	}

	// 删除
	public void deleteByID(HttpServletRequest request, String t_mail_box_send_id) {

		// 删除基本信息及其附件文件
		deleteReceivedByID(request, t_mail_box_send_id);

		// TODO：删除其他数据
	}

	// 删除基本信息及其附件文件
	private void deleteReceivedByID(HttpServletRequest request, String t_mail_box_send_id) {
		MailBoxReceived plan = getByID(t_mail_box_send_id);
		if (plan == null)
			return;

		// 1.删除文件
		deleteFilesByMailBoxReceivedId(request, t_mail_box_send_id);

		// 2.删除基本信息
		maiboxdao.deleteById(t_mail_box_send_id);
	}

	// 删除文件
	private void deleteFilesByMailBoxReceivedId(HttpServletRequest request, String t_mail_box_Received_id) {
		ServletContext sc = request.getSession().getServletContext();
		String dir = sc.getRealPath(RealPathConst.RealPath_MailBoxFile); // 设定文件保存的目录

		List<MailBoxReceivedFile> list = fileDao
				.getByMailBoxReceivedID(t_mail_box_Received_id);
		if (list != null) {

			for (MailBoxReceivedFile homeworkfile : list) {
				String path = dir + RealPathConst.RealPath_PathSeparator + homeworkfile.getFilepath();

				File localfile = new File(path);

				if (localfile.exists())
					localfile.delete();

				fileDao.deleteById(homeworkfile.getId());

			}
		}
	}
	
	/**
	 * 根据t_user_id删除邮件
	 * */
	public void deleteByUserId(HttpServletRequest request, String t_user_id){
		deleteFromUserByUserId(request,t_user_id);
		deleteToUserByUserId(request,t_user_id);
	}
	
	/**
	 * 根据t_user_id删除邮件
	 * */
	public void deleteFromUserByUserId(HttpServletRequest request, String t_user_id){
		List<MailBoxReceived> listfrom=maiboxdao.getByUserIdFrom(t_user_id);
		if(listfrom!=null){
			for(MailBoxReceived s:listfrom){
				deleteByID(request,s.getId());
			}
		}
		
		
	}
	
	/**
	 * 根据t_user_id删除邮件
	 * */
	public void deleteToUserByUserId(HttpServletRequest request, String t_user_id){
		
		
		List<MailBoxReceived> listto=maiboxdao.getByUserIdTo(t_user_id);
		if(listto!=null){
			for(MailBoxReceived s:listto){
				deleteByID(request,s.getId());
			}
		}
	}

	
	public void setRead(String t_mail_box_Received_id){
		MailBoxState state=new MailBoxState();
		state.setRead();
		maiboxdao.update(t_mail_box_Received_id,state.getState());
	}


	public MailBoxReceived getByID(String id) {
		return maiboxdao.getByID(id);
	}

	public MailBoxReceivedViewData getMailBoxSendViewDataByID(String id) {
		return maiboxdao.getMailBoxReceivedViewDataByID(id);
	}

	public Page<MailBoxReceivedViewData> getPage(String t_user_id_to, int pageNo, int pageSize) {
		return maiboxdao.getPage(t_user_id_to,  pageNo, pageSize);
	}
	
	public Page<MailBoxReceivedViewData> getPageNotRead(String t_user_id_to, int pageNo, int pageSize) {
		return maiboxdao.getPageNotRead(t_user_id_to,  pageNo, pageSize);
	}
	
	public boolean hasMailNotReaded(String t_user_id_to){
		return maiboxdao.getCountNotRead(t_user_id_to)>0;
	}


}
