package com.scms.sys.service;

import com.scms.bulletin.entity.Category;
import com.scms.currency.service.base.IBaseService;
import com.scms.sys.entity.SysCategory;

/** 
 * @author  航
 * @version 创建时间：2017年1月13日 下午6:09:08 
 * 类说明 
 * 
 * 模块
 */
public interface ISysCategoryService extends IBaseService  {
	String SysCategoryModelStr = "SysCategory.Model.Request";
	/**
	 * 获取系统类别
	 * @return
	 */
	SysCategory getSysCategory();
	/**
	 * 
	 * @param sysCategory
	 * @return
	 */
	boolean update(SysCategory sysCategory);
	/**
	 * 判断该类别是否是系统类别
	 * @param category
	 * @return
	 */
	boolean isSysCategory(Category category);
}
