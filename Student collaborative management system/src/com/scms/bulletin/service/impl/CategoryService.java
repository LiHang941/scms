package com.scms.bulletin.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.scms.bulletin.dao.ICategoryDao;
import com.scms.bulletin.entity.Category;
import com.scms.bulletin.service.ICategoryService;
import com.scms.currency.service.impl.AbsBaseService;
import com.scms.sys.service.ISysCategoryService;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年1月13日 下午7:28:29 
 * 类说明 
 */
@Service
public class CategoryService extends AbsBaseService implements ICategoryService {
	@Resource
	private ICategoryDao categoryDao ;
	@Resource
	private ISysCategoryService sysCategoryService ;
	
	/**
	 * 管理界面显示的数目
	 */
	public static final int Category_Page_Manager_Length = 9;
	
	@Override
	public void addLog(String record, HttpServletRequest request) {
		record = "[公告管理-类别模块]" + record ;
		super.addLog(record, request);
	}
	@Override
	public Category findById(int id) {
	
		Category category = categoryDao.findById(id);
		
		//这个类别不存在  跳转到默认类别
		if(category == null){
			List<Category> list = categoryDao.getAll();
			if(list != null && list.size()>=1)
				category = list.get(0);
		}
		//不排除空的可能
		return category;
	}
	
	@Override
	public Category findById(Category category) {
		if(category == null)
			return null;
		Category temp = categoryDao.findById(category.getId());
		
		//这个类别不存在  跳转到默认类别
		if(temp == null){
			List<Category> list = categoryDao.getAll();
			if(list != null && list.size()>=1)
				temp = list.get(0);
		}
		//不排除空的可能
		return temp;
	}
	
	@Override
	public Category findByList(int id, List<Category> list) {
		Category category = null;
		for(Category category2 :list){
			if(category2.getId()==id){
				return category2;
			}
		}
		if(list.size()>=1){
			category = list.get(0);
		}
		return category;
	}

	@Override
	public boolean add(Category category) {
		if(category == null ||  category.getName()== null ||  category.getName().equals(""))
			return false;
		categoryDao.save(category);
		return true;
	}

	@Override
	public boolean delete(Category category) {
		if(category.getId() == 0)
			return false;
		Category temp = categoryDao.findById(category.getId());
		//这个类别不存在  跳转到默认类别
		if(temp == null)
			return false;
		//是否是系统默认的公告类别
		if(sysCategoryService.isSysCategory(category))
			return false;
		
		categoryDao.delete(temp.getId());
		return true;
	}

	@Override
	public boolean update(Category category) {
		if(category == null || category.getId() == 0 || category.getName()== null ||  category.getName().equals(""))
			return false;
		Category temp  = categoryDao.findById(category.getId());
		//这个类别不存在  跳转到默认类别
		if(temp == null)
			return false;
		
		temp.setName(category.getName());
		categoryDao.update(temp);
		return true;
	}

	@Override
	public List<Category> getAll() {
		List<Category> list=  categoryDao.getAll();
		list.toString();
		return list;
	}

	

	@Override
	public Page getPage(int p, int lenght) {
		return categoryDao.getPage(lenght, p);
	}

	@Override
	public Page getSearchPage(int p, int lenght, String searchStr) {
		return categoryDao.getPage(lenght, p,searchStr);
	}
	
	@Override
	public List<Category> getByPage(Page page) {
		return categoryDao.getByPage(page);
	}

	@Override
	public List<Category> getBySearch(Page page, String searchStr) {
		if(searchStr.equals(""))
			return getByPage(page);
		return categoryDao.getByPage(page, searchStr);

	}
	
}
