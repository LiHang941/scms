package com.scms.currency.service;

import java.util.List;

import com.scms.currency.service.base.IBaseService;
import com.scms.sys.entity.UserAccessLog;
import com.scms.user.entity.User;
import com.scms.util.Page;
/** 
 * @author  航
 * @version 创建时间：2017年2月13日 上午10:43:22 
 * 类说明 
 */
public interface IMyAccessLogService extends IBaseService  {
	/**
	 * 获取最近的一次登录记录
	 * @param user
	 * @return
	 */
	UserAccessLog getByLately(User user);
	/**
	 * 获取分页对象
	 * @param p
	 * @param lenght
	 * @param user
	 * @return
	 */
	Page getPage(int p, int lenght,User user);
	
	/**
	 * 该用户的登录记录
	 * @param user
	 * @param page
	 * @return
	 */
	List<UserAccessLog> getByUser (User user,Page page);
}
