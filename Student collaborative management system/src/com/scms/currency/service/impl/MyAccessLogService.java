package com.scms.currency.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.scms.currency.dao.IMyAccessLogDao;
import com.scms.currency.service.IMyAccessLogService;
import com.scms.sys.entity.UserAccessLog;
import com.scms.user.entity.User;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年2月13日 上午10:45:19 
 * 类说明 
 *  用户访问记录
 */
@Service
public class MyAccessLogService extends AbsBaseService implements IMyAccessLogService{
	@Resource
	private IMyAccessLogDao myAccessLogDao ;
	/**
	 * 管理用户界面的集合长度
	 */
	public static final int MyAccessLog_Manager_List_Length = 9;
	@Override
	public Page getPage(int p, int lenght,User user) {
		return myAccessLogDao.getPage(user, lenght, p);
	}
	@Override
	public List<UserAccessLog> getByUser (User user,Page page){
		return myAccessLogDao.getByUser(user, page);
	}
	@Override
	public UserAccessLog getByLately(User user) {
		return myAccessLogDao.getByLately(user);
	}
	public void setMyAccessLogDao(IMyAccessLogDao myAccessLogDao) {
		this.myAccessLogDao = myAccessLogDao;
	}
	

}
