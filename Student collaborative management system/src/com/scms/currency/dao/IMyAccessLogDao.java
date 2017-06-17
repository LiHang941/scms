package com.scms.currency.dao;

import java.util.List;

import com.scms.currency.dao.base.IBaseDao;
import com.scms.sys.entity.UserAccessLog;
import com.scms.user.entity.User;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年2月13日 下午3:05:19 
 * 类说明 
 * 
 * 用户
 */
public interface IMyAccessLogDao extends IBaseDao<UserAccessLog> {
	/**
	 * 获取该用户最近的登录记录
	 * @param user
	 * @return
	 */
	UserAccessLog getByLately(User user);
	
	/**
	 * 获取该用户的登录记录集合  并分页
	 * @param user
	 * @param page 分页对象
	 * @return
	 */
	List<UserAccessLog> getByUser (User user,Page page);
	
	/**
	 * 
	 * @param everyPage 显示多少数据
	 * @param currentPage 当前页
	 * @return
	 */
	Page getPage(User user , int everyPage, int currentPage);
}
