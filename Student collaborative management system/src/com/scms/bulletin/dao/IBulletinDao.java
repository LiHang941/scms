package com.scms.bulletin.dao;

import java.util.List;

import com.scms.bulletin.entity.Bulletin;
import com.scms.bulletin.entity.Category;
import com.scms.currency.dao.base.IBaseDao;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年1月12日 下午10:33:58 
 * 类说明 
 */
public interface IBulletinDao extends IBaseDao<Bulletin> {
	/**
	 * 浏览次数+1
	 * @param bulletin
	 */
	void BrowseInforMationCountAdd(Bulletin bulletin);
	/**
	 * 
	 * @param categoryId  类别id
	 * @param page 
	 */
	List<Bulletin> getByCategory(int categoryId, Page page);
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
	 * @return
	 */
	Page getPage(int everyPage, int currentPage, String search);
	
	List<Bulletin> getByPage(Page page);
	
	List<Bulletin> getByPage(Page page, String search);
	/**
	 * 
	 * @param category
	 * @param everyPage 显示多少数据
	 * @param currentPage 当前页
	 * @return
	 */
	Page findByCategoryIdGetPage(int categoryId, int everyPage, int currentPage);
	/**
	 * 
	 * @param searchStr
	 * @param everyPage
	 * @param currentPage
	 * @return
	 */
	Page getBySearchPage(String searchStr,int everyPage, int currentPage);
	
	List<Bulletin> getBySearchList(String searchStr, Page page);
	
}
