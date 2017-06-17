package com.scms.bulletin.dao;

import java.util.List;

import com.scms.bulletin.entity.Category;
import com.scms.currency.dao.base.IBaseDao;
import com.scms.sys.entity.OperationRecord;
import com.scms.user.entity.User;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年1月12日 下午10:27:45 
 * 类说明  
 * 
 * 
 */
public interface ICategoryDao extends IBaseDao<Category> {
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
	List<Category> getByPage(Page page);
	/**
	 * 
	 * @param page
	 * @param search
	 * @return
	 */
	List<Category> getByPage(Page page,String search);
}
