package com.scms.user.dao;

import java.util.List;

import com.scms.currency.dao.base.IBaseDao;
import com.scms.user.entity.User;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年1月12日 下午10:27:45 
 * 类说明  
 * 
 * User对象 与 数据库交互 
 * 
 */
public interface IUserDao extends IBaseDao<User> {
	/**
	 * 通过用户名查找用户
	 * @param userName
	 * @return
	 */
	User findByUserAccount(String account);
	/**
	 * 默认分页
	 * @param p
	 * @param length
	 * @return
	 */
	Page getPage (int p, int length);
	/**
	 * 通过搜索内容进行分页
	 * @param everyPage 显示多少数据
	 * @param lcurrentPage 当前页
	 * @param searchStr 搜索内容
	 * @return
	 */
	Page getPage(int everyPage, int currentPage, String searchStr);
	/**
	 * 获取指定页码的集合
	 * @param page
	 * @return
	 */
	List<User> getByPage(Page page);
	
	/**
	 * 通过指定页码，搜索内容 获取集合
	 * @param page
	 * @param searchStr
	 * @return
	 */
	List<User> getByPage(Page page,String searchStr);
	List<User> getByUserIds(char[] charArray) throws Exception;
}
