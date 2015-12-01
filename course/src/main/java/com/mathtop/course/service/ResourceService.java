package com.mathtop.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.Page;
import com.mathtop.course.dao.ResourceDao;
import com.mathtop.course.domain.Resource;

@Service
public class ResourceService  {
	
	@Autowired
	protected ResourceDao dao;

	
	public String add(Resource t) {

		return dao.add(t);
	}

	/*
	 * 添加
	 */
	public String add(String uri, String uri_element) {
		return dao.add(uri, uri_element);
	}

	/*
	 * 删除
	 */
	public void delete(String id) {
		dao.delete(id);
	}

	/*
	 * 更新
	 */
	public void update(String id, String uri, String uri_element) {
		dao.update(id, uri, uri_element);
	}

	/*
	 * 查找
	 */
	public List<Resource> select(String uri) {

		return dao.select(uri);
	}

	
	public Resource getByURI(String uri) {

		return dao.getByURI(uri);
	}

	
	public Resource getByID(String id) {

		return dao.getByID(id);
	}

	/*
	 * 返回所有结果
	 */
	public List<Resource> getAll() {

		return dao.getAll();
	}


	public Page<Resource> getPage(int pageNo, int pageSize) {
		return dao.getPage(pageNo, pageSize);

	}

	public Page<Resource> getAllPage() {
		return dao.getAllPage();

	}

	public Page<Resource> select(String name, int pageNo, int pageSize) {
		return dao.select(name, pageNo, pageSize);
	}
	
}
