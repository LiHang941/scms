package com.scms.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.scms.currency.service.impl.AbsBaseService;
import com.scms.sys.dao.IUserAccessLogDao;
import com.scms.sys.entity.UserAccessLog;
import com.scms.sys.service.IUserAccessLogService;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年2月5日 下午4:48:54 
 * 类说明 
 * 
 */
@Service
public class UserAccessLogService extends AbsBaseService implements IUserAccessLogService {
	@Resource
	private IUserAccessLogDao userAccessLogDao;
	
	/**
	 * 管理用户界面的集合长度
	 */
	public static final int UserAccessLog_Manager_List_Length = 9;
	
	@Override
	public void addLog(String record, HttpServletRequest request) {
		record = "[系统模块-用户访问记录]" + record;
		super.addLog(record, request);
	}
	@Override
	public void add(UserAccessLog ual) {
		userAccessLogDao.save(ual);
	}

	@Override
	public boolean delete(UserAccessLog ual) {
		if(ual.getId() == 0)
			return false;
		UserAccessLog temp = userAccessLogDao.findById(ual.getId());
		if(temp == null)
			return false;
		userAccessLogDao.delete(temp.getId());
		return true;
	}

	@Override
	public List<UserAccessLog> getByPage(Page page) {
		return userAccessLogDao.getByPage(page);
	}
	
	public Page getPage(int p, int lenght){
		return userAccessLogDao.getPage(lenght, p);
	}
	@Override
	public List<UserAccessLog> getBySearch(Page page,String searchStr) {
		if(searchStr.equals(""))
			return getByPage(page);
		return userAccessLogDao.getByPage(page, searchStr);
	}
	public Page getSearchPage(int p, int lenght,String searchStr){
		if(searchStr.equals(""))
			return getPage(p,lenght);
		return userAccessLogDao.getPage(lenght, p,searchStr);
	}
	public void setUserAccessLogDao(IUserAccessLogDao userAccessLogDao) {
		this.userAccessLogDao = userAccessLogDao;
	}
	
}
