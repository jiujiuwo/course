package com.mathtop.course.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mathtop.course.cons.RealPathConst;
import com.mathtop.course.dao.MailBoxSendDao;
import com.mathtop.course.dao.MailBoxSendFileDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.MailBoxSend;
import com.mathtop.course.domain.MailBoxSendFile;
import com.mathtop.course.domain.MailBoxSendViewData;
import com.mathtop.course.domain.MailBoxState;
import com.mathtop.course.utility.GUID;

@Service
public class MailBoxSendService {

	@Autowired
	MailBoxSendDao maiboxdao;

	@Autowired
	MailBoxSendFileDao fileDao;
	
	
	
	

	public MailBoxSendFile getFileByID(String id) {
		return fileDao.getByID(id);
	}

	private List<MailBoxFileName> addFiles(HttpServletRequest request, String t_mail_box_id, MultipartFile[] files) {

		List<MailBoxFileName> list=new ArrayList<MailBoxFileName>();
		
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			if (file.isEmpty())
				continue;

			String filename = file.getOriginalFilename();

			String prefix = filename.substring(filename.lastIndexOf(".") + 1);

			ServletContext sc = request.getSession().getServletContext();
			String dir = sc.getRealPath(RealPathConst.RealPath_MailBoxFile); // 设定文件保存的目录

			prefix = prefix.toLowerCase();

			String idfilename = GUID.getGUID();

			String localfilename = dir + RealPathConst.RealPath_PathSeparator + idfilename + "." + prefix;
			String hreffilename = idfilename + "." + prefix;
			

			try {
				File localfile = new File(localfilename);

				if (localfile.exists())
					localfile.delete();

				file.transferTo(localfile);

				fileDao.add(t_mail_box_id, filename, hreffilename);
				
				MailBoxFileName mfn=new MailBoxFileName();
				mfn.setOriginalFilename(filename);
				mfn.setIDFileName(hreffilename);
				mfn.setID0FileName(GUID.getGUID()+ "." + prefix);
				list.add(mfn);

				

			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
		
		return list;
	}

	// 增加
	public List<MailBoxFileName> add(HttpServletRequest request, String t_user_id_from, String t_user_id_to,
			  String subject, String content, 
			MultipartFile[] files) {
		
		MailBoxState state=new MailBoxState();

		String t_mail_box_send_id = maiboxdao.add(t_user_id_from, t_user_id_to,
				state.getState(),  subject, content);

		return addFiles(request, t_mail_box_send_id, files);

	}

	// 删除
	public void deleteByID(HttpServletRequest request, String t_mail_box_send_id) {

		// 删除基本信息及其附件文件
		deleteBaseInfoByID(request, t_mail_box_send_id);

		// TODO：删除其他数据
	}

	// 删除基本信息及其附件文件
	private void deleteBaseInfoByID(HttpServletRequest request, String t_mail_box_send_id) {
		MailBoxSend plan = getByID(t_mail_box_send_id);
		if (plan == null)
			return;

		// 1.删除文件
		deleteFilesByMailBoxSendId(request, t_mail_box_send_id);

		// 2.删除基本信息
		maiboxdao.deleteById(t_mail_box_send_id);
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
		List<MailBoxSend> listfrom=maiboxdao.getByUserIdFrom(t_user_id);
		if(listfrom!=null){
			for(MailBoxSend s:listfrom){
				deleteByID(request,s.getId());
			}
		}
		
		
	}
	
	/**
	 * 根据t_user_id删除邮件
	 * */
	public void deleteToUserByUserId(HttpServletRequest request, String t_user_id){
		
		
		List<MailBoxSend> listto=maiboxdao.getByUserIdTo(t_user_id);
		if(listto!=null){
			for(MailBoxSend s:listto){
				deleteByID(request,s.getId());
			}
		}
	}

	// 删除文件
	private void deleteFilesByMailBoxSendId(HttpServletRequest request, String t_mail_box_send_id) {
		ServletContext sc = request.getSession().getServletContext();
		String dir = sc.getRealPath(RealPathConst.RealPath_MailBoxFile); // 设定文件保存的目录

		List<MailBoxSendFile> list = fileDao
				.getByMailBoxSendID(t_mail_box_send_id);
		if (list != null) {

			for (MailBoxSendFile homeworkfile : list) {
				String path = dir + RealPathConst.RealPath_PathSeparator + homeworkfile.getFilepath();

				File localfile = new File(path);

				if (localfile.exists())
					localfile.delete();

				fileDao.deleteById(homeworkfile.getId());

			}
		}
	}


	public MailBoxSend getByID(String id) {
		return maiboxdao.getByID(id);
	}

	public MailBoxSendViewData getMailBoxSendViewDataByID(String id) {
		return maiboxdao.getMailBoxReceivedViewDataByID(id);
	}

	public Page<MailBoxSendViewData> getPage(String t_user_id_from,			 int pageNo, int pageSize) {
		return maiboxdao.getPage(t_user_id_from,  pageNo, pageSize);
	}


}
