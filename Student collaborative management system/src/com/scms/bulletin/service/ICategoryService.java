package com.scms.bulletin.service;

import java.util.List;

import com.scms.bulletin.entity.Category;
import com.scms.currency.service.base.IPageService;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年1月13日 下午6:08:03 
 * 类说明 
 * 
 * 公告类别业务逻辑接口
 */
public interface ICategoryService extends IPageService {
	
	String CategoryListStr = "Category.List.Request";
	
	String CategoryModelStr = "Category.Model.Request";
	
	/**
	 * 查看一个类别
	 * @param id
	 * @return
	 */
	Category findById(int id);
	
	/**
	 * 从集合中查找指定类别
	 * @param id
	 * @param list
	 * @return
	 */
	Category findByList(int id,List<Category> list);
	
	/**
	 * 获取列表
	 * @param page 页码
	 * @param lenght 分页长度
	 * @return
	 */
	List<Category> getByPage(Page page);
	/**
	 * 搜索返回列表
	 * @param page 页码
	 * @param searchStr 搜索字串
	 * @return
	 */
	List<Category> getBySearch(Page page,String searchStr);
	
	/**
	 * 添加一个类别
	 * @param category
	 */
	boolean add(Category category);
	
	
	/**
	 * 更新一个类别
	 * @param category
	 */
	boolean update(Category category);
	
	/**
	 * 获取所有的类别
	 * @return
	 */
	List<Category> getAll();
	/**
	 * 删除
	 * @param category
	 * @return
	 */
	boolean delete(Category category);

	Category findById(Category category);
}
