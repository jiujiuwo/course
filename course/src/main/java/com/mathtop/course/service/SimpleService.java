package com.mathtop.course.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mathtop.course.dao.Page;
import com.mathtop.course.dao.SimpleDao;
import com.mathtop.course.domain.Simple;

@Service
public abstract class SimpleService<TDao extends SimpleDao<TDomain>, TDomain extends Simple> {

	
	
	public abstract TDao getBaseDao();

	public String Add(Simple t) {
		if (t != null) {
			return getBaseDao().add(t);
		}
		return null;
	}

	public String Add(String name, String note) {
		if (name != null && note != null) {
			return getBaseDao().add(name, note);
		}
		return null;
	}

	public void DELETE(String id) {
		if (id != null) {
			getBaseDao().DELETE(id);
		}
	}

	public void update(String id, String name, String note) {
		if (id != null) {
			getBaseDao().update(id, name, note);
		}
	}

	public Page<TDomain> select(String name, int pageNo, int pageSize) {

		return getBaseDao().select(name, pageNo, pageSize);
	}

	/**
	 * 根据用户名/密码查询 User对象
	 * 
	 * @param userName
	 *            用户名
	 * @return User
	 */
	public TDomain getByID(String id) {
		return getBaseDao().getByID(id);
	}

	/**
	 * 根据用户名/密码查询 User对象
	 * 
	 * @param userName
	 *            用户名
	 * @return User
	 */
	public TDomain getByName(String name) {

		if (null == name || name.isEmpty())
			return null;

		return getBaseDao().getByName(name);
	}

	public List<TDomain> getAll() {
		return getBaseDao().getAll();
	}

	/**
	 * 
	 * 
	 * @param boardId
	 * @return
	 */
	public Page<TDomain> getPage(int pageNo, int pageSize) {
		return getBaseDao().getPage(pageNo, pageSize);
	}
	
	public Page<TDomain> getAllPage() {
		return getBaseDao().getAllPage();
	}
}
