package com.scms.currency.service.impl;

import com.scms.currency.dao.base.IBaseDao;
import com.scms.currency.service.base.IPageService;

/** 
 * @author  航
 * @version 创建时间：2017年2月24日 上午10:58:34 
 * 类说明 
 */
public abstract class AbsPageService<T> implements IPageService {
	
	private IBaseDao<T> baseDao ;
	public void delete(int id) {
		baseDao.delete(id);
	}
	public void save(T t) {
		baseDao.save(t);
	}
	
	public void update(T t) {
		baseDao.update(t);
	}

	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	
}
