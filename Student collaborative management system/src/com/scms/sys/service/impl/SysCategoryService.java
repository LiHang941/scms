package com.scms.sys.service.impl;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.scms.bulletin.entity.Category;
import com.scms.bulletin.service.ICategoryService;
import com.scms.currency.service.impl.AbsBaseService;
import com.scms.sys.dao.ISysCategoryDao;
import com.scms.sys.entity.SysCategory;
import com.scms.sys.service.ISysCategoryService;

/** 
 * @author  航
 * @version 创建时间：2017年1月26日 下午6:05:31 
 * 类说明 
 */
@Service
public class SysCategoryService extends AbsBaseService   implements ISysCategoryService{
	@Resource
	private ISysCategoryDao sysCategoryDao;
	@Resource
	private ICategoryService categoryService;
	
	@Override
	public void addLog(String record, HttpServletRequest request) {
		record = "[系统模块-默认公告]"+record;
		super.addLog(record, request);
	}
	
	@Override
	public SysCategory getSysCategory() {
		return sysCategoryDao.getSysCategory();
	}
	
	@Override
	public boolean update(SysCategory sysCategory) {
		if(sysCategory == null || sysCategory.getCategory1() == null ||sysCategory.getCategory1().getId()==0
				|| sysCategory.getCategory2() == null ||sysCategory.getCategory2().getId()==0
				|| sysCategory.getCategory3() == null ||sysCategory.getCategory3().getId()==0){
			return false;
		}
		SysCategory temp = sysCategoryDao.findById(sysCategory.getId());
		if(temp == null)
			return false;
		
		Category category1 = categoryService.findById(sysCategory.getCategory1());
		Category category2 = categoryService.findById(sysCategory.getCategory2());
		Category category3 = categoryService.findById(sysCategory.getCategory3());
		
		if(category1 == null || category2 == null || category3 ==null){
			return false;
		}
		
		temp.setCategory1(category1);
		temp.setCategory2(category2);
		temp.setCategory3(category3);
		temp.setState(SysCategory.SysCategory_State_true);
		
		sysCategoryDao.update(temp);
		
		return true;
	}
	
	public boolean isSysCategory(Category category){
		if(category == null)
			return false;
		
		SysCategory sysCategory = getSysCategory();
		if(sysCategory == null)
			return false;
		
		return isSysCategory(category,sysCategory);
	}
	
	private boolean isSysCategory(Category category,SysCategory sysCategory){
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		if(sysCategory.getCategory1()!= null)
			flag1 = (sysCategory.getCategory1().getId() == category.getId());
		if(sysCategory.getCategory2()!= null)
			flag2= (sysCategory.getCategory2().getId() == category.getId());
		if(sysCategory.getCategory3()!= null)
			flag3 = (sysCategory.getCategory3().getId() == category.getId());
		return (flag1 || flag2 || flag3);	
	}

	public void setSysCategoryDao(ISysCategoryDao sysCategoryDao) {
		this.sysCategoryDao = sysCategoryDao;
	}

	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	

}
