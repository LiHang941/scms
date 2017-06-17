package com.scms.sys.dao;

import java.util.List;

import com.scms.currency.dao.base.IBaseDao;
import com.scms.sys.entity.UserAccessLog;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年2月5日 下午4:38:30 
 * 类说明 
 */
public interface IUserAccessLogDao extends IBaseDao<UserAccessLog>{
	/**
	 * 
	 * @param everyPage 显示多少数据
	 * @param currentPage 当前页
	 * @return
	 */
	Page getPage(int everyPage, int currentPage);
	/**
	 * 
	 * @param everyPage 显示多少数据
	 * @param currentPage 当前页
	 * @param search 搜索内容
	 * @return
	 */
	Page getPage(int everyPage, int currentPage,String search);
	/**
	 * 通过指定的分页对象获取集合
	 * @param page
	 * @return
	 */
	List<UserAccessLog> getByPage(Page page);
	/**
	 * 
	 * @param page
	 * @param search
	 * @return
	 */
	List<UserAccessLog> getByPage(Page page,String search);
}
