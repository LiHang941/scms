package com.scms.bulletin.service;

import java.util.List;

import com.scms.bulletin.entity.Bulletin;
import com.scms.bulletin.entity.Category;
import com.scms.currency.service.base.IPageService;
import com.scms.sys.entity.SysCategory;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年1月13日 下午2:50:11 
 * 类说明 
 * 
 * <br>公告业务逻辑层
 * 
 * 	添加公告
 * 	修改公告
 * 	
 */
public interface IBulletinService extends IPageService {
	/**
	 * 通过bulletin.getId()查询bulletin
	 * 如果获取失败返回null
	 * @param bulletin
	 * @return
	 */
	Bulletin getById(Bulletin bulletin);
	/**
	 * 添加一个公告
	 * @param bulletin
	 */
	boolean add(Bulletin bulletin);
	/**
	 * 修改一个公告
	 * @param bulletin
	 */
	boolean update(Bulletin bulletin);
	/**
	 * 删除一个公告
	 * @param bulletin
	 */
	boolean delete(Bulletin bulletin);
	
	
	/**
	 * 获取主页显示的公告集合
	 * @param sysCategory
	 * @return
	 */
	List<Bulletin>  getIndexBulletinList(SysCategory sysCategory);
	
	/**
	 * 查看一个公告
	 * @param id
	 * @return
	 */
	Bulletin Show(Bulletin bulletin);
	/**
	 * 修改状态 (加锁解锁)
	 * @return
	 */
	boolean state(Bulletin bulletin);

	/**
	 * 获取列表
	 * @param page 页码
	 * @param lenght 分页长度
	 * @return
	 */
	List<Bulletin> getByPage(Page page);
	/**
	 * 搜索返回列表
	 * @param page 页码
	 * @param searchStr 搜索字串
	 * @return
	 */
	List<Bulletin> getBySearch(Page page,String searchStr);
	/**
	 * 返回分页对象
	 * @param category  一个类别
	 * @param p	当前页码
	 * @param lenght 长度
	 * @return
	 */
	Page getByCategoryPage(Category category, int p,int lenght);
	/**
	 * 返回指定类别的公告集合
	 * @param category
	 * @param page
	 * @return
	 */
	List<Bulletin> getByCategoryList(Category category, Page page);
	
	/**
	 * 返回搜索的内容分页对象
	 * @param searchStr
	 * @param qiantaiBulletinListLenght
	 * @return
	 */
	Page getBySearchPage(String searchStr,int p, int lenght);
	
	/**
	 * 
	 * @param searchStr
	 * @param page
	 * @return
	 */
	List<Bulletin> getBySearchList(String searchStr, Page page);
	
}
