package com.scms.bulletin.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.scms.bulletin.dao.IBulletinDao;
import com.scms.bulletin.dao.ICategoryDao;
import com.scms.bulletin.entity.Bulletin;
import com.scms.bulletin.entity.Category;
import com.scms.bulletin.service.IBulletinService;
import com.scms.currency.service.impl.AbsBaseService;
import com.scms.sys.entity.SysCategory;
import com.scms.sys.service.ISysCategoryService;
import com.scms.util.Constant;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年1月13日 下午4:24:04 
 * 类说明 
 */
@Service
public class BulletinService extends AbsBaseService implements IBulletinService{
	@Resource
	private IBulletinDao bulletinDao;
	@Resource
	private ICategoryDao categoryDao;
	@Resource
	private ISysCategoryService sysCategoryService;
	
	@Override
	public void addLog(String record, HttpServletRequest request) {
		record = "[公告管理-公告模块]" + record ;
		super.addLog(record, request);
	}
	
	@Override
	public Bulletin getById(Bulletin bulletin) {
		if(bulletin == null || bulletin.getId()<=0 )
			return null;
		return  bulletinDao.findById(bulletin.getId());
		
	}
	@Override
	public boolean add(Bulletin bulletin) {
		if(bulletin.getId() != 0)
			return false;
		
		if(bulletin.getUser() == null)
			return false;
		
		bulletinDao.save(bulletin);
		
		return true;
	}

	@Override
	public boolean update(Bulletin bulletin) {
		if(bulletin.getId() == 0){
			return false;
		}
		Category category = categoryDao.findById(bulletin.getCategory().getId());
		if(category == null)
			return false;
		Bulletin b = bulletinDao.findById(bulletin.getId());
		
		if(b == null)
			return false;
		
		b.setTitle(bulletin.getTitle());
		b.setContent(bulletin.getContent());
		b.setCategory(category);
		b.setRecord(bulletin.getRecord());
		
		bulletinDao.update(b);
		
		return true;
	}

	@Override
	public boolean delete(Bulletin bulletin) {
		Bulletin b = bulletinDao.findById(bulletin.getId());
		if(b == null)
			return false;
		bulletinDao.delete(b.getId());
		return true;
	}
	@Override
	public boolean state(Bulletin bulletin) {
		Bulletin b = bulletinDao.findById(bulletin.getId());
		if(b == null)
			return false;
		
		b.setState(b.getState() * (-1));
		bulletinDao.update(b);
		return true;
	}
	

	
	@Override
	public Page getPage(int p, int lenght) {
		return bulletinDao.getPage(lenght, p);
	}
	@Override
	public Page getSearchPage(int p, int lenght, String searchStr) {
		return bulletinDao.getPage(lenght, p,searchStr);
	}
	
	
	@Override
	public Bulletin Show(Bulletin bulletin) {
		bulletin = getById(bulletin);
		if(bulletin != null)
			//浏览次数+1
			bulletinDao.BrowseInforMationCountAdd(bulletin);
		return bulletin;
	}
	@Override
	public List<Bulletin> getByPage(Page page) {
		return bulletinDao.getByPage(page);
	}
	@Override
	public List<Bulletin> getBySearch(Page page, String searchStr) {
		return bulletinDao.getByPage(page, searchStr);
	}
	
	@Override
	public List<Bulletin> getIndexBulletinList(SysCategory sysCategory) {
		Page page = new Page();
		page.setEveryPage(Constant.QIANTAI_INDEX_BULLETIN);
		page.setBeginIndex(0);
		List<Bulletin> list = bulletinDao.getByCategory(sysCategory.getCategory1().getId(),page);
		list.addAll(bulletinDao.getByCategory(sysCategory.getCategory2().getId(),page));
		list.addAll(bulletinDao.getByCategory(sysCategory.getCategory3().getId(),page));
		return list;
	}

	@Override
	public Page getByCategoryPage(Category category, int p, int lenght) {
		if(category == null || category.getId()<=0)
			category =sysCategoryService.getSysCategory().getCategory1();
		return bulletinDao.findByCategoryIdGetPage(category.getId(),lenght, p);
	}

	@Override
	public List<Bulletin> getByCategoryList(Category category, Page page) {
		if(category == null || category.getId()<=0)
			category =sysCategoryService.getSysCategory().getCategory1();
		return bulletinDao.getByCategory(category.getId(), page);
	}
	
	@Override
	public List<Bulletin> getBySearchList(String searchStr, Page page) {
		return bulletinDao.getBySearchList(searchStr,page);
	}
	
	@Override
	public Page getBySearchPage(String searchStr,int p, int lenght) {
		return bulletinDao.getBySearchPage(searchStr,lenght,p);
	}
}
