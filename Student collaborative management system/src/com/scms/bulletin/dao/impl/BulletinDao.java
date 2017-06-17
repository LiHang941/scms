package com.scms.bulletin.dao.impl;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Repository;

import com.scms.bulletin.dao.IBulletinDao;
import com.scms.bulletin.entity.Bulletin;
import com.scms.currency.dao.base.impl.BaseDao;
import com.scms.util.Page;
import com.scms.util.PageUtil;
import com.scms.util.search.SearchStrPanduan;

/**
 * @author 航
 * @version 创建时间：2017年1月11日 下午8:26:09 类说明
 */
@Repository
public class BulletinDao extends BaseDao<Bulletin> implements IBulletinDao {
	@Override
	public void BrowseInforMationCountAdd(Bulletin bulletin) {
		try {
			String updateHql = "UPDATE Bulletin bulletin set bulletin.browseInforMationCount = bulletin.browseInforMationCount+1  WHERE bulletin.id = ?";
			Query query = getSession().createQuery(updateHql);
			//query.setLong(0, (bulletin.getBrowseInforMationCount()+1));
			query.setInteger(0, bulletin.getId());
			query.executeUpdate();
		} catch (DataAccessResourceFailureException e) {
			e.printStackTrace();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Bulletin> getByCategory(int categoryId,Page page){
		String hql = "FROM Bulletin bulletin WHERE bulletin.category.id = ? AND bulletin.state = ? ORDER BY bulletin.date DESC";
		Query query = getSession()
				.createQuery(hql)
				.setInteger(0, categoryId).setInteger(1, Bulletin.BULLETIN_STATE_TRUE)
				.setMaxResults(page.getEveryPage())
				.setFirstResult(page.getBeginIndex());
		return query.list();
	}
	
	@Override
	public Page getPage(int everyPage, int currentPage){
		String hql = "SELECT count(*) FROM Bulletin bulletin ORDER BY bulletin.date DESC";
		return super.getConditionPage(everyPage, currentPage, hql);
	}
	
	@Override
	public List<Bulletin> getByPage(Page page){
		String hql = "FROM Bulletin bulletin ORDER BY bulletin.date DESC";
		return super.findByCondition(hql, page);
	}
	
	@Override
	public Page getPage(int everyPage, int currentPage, String search) {
		int totalCount  = getBySearchHql(search).list().size();
		Page page = PageUtil.createPage(everyPage, totalCount, currentPage);
		return page;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bulletin> getByPage(Page page, String search) {
		return getBySearchHql(search)
				.setMaxResults(page.getEveryPage())
				.setFirstResult(page.getBeginIndex())
				.list();
	}

	private Query getBySearchHql(String search) {
		if(SearchStrPanduan.isContainChinese(search)){
			//存在中文字串
			String hql = "FROM Bulletin bulletin WHERE bulletin.title like ? or "
					+ "bulletin.user.account like ? or "
					+ "bulletin.category.name like ? "
					+ "ORDER BY bulletin.date DESC";
				Query query = getSession().createQuery(hql);
				query.setParameter(0, "%"+search+"%");
				query.setParameter(1,"%"+search+"%");
				query.setParameter(2,"%"+search+"%");
				return query;
		}		
		String hql = "FROM Bulletin bulletin WHERE bulletin.title like ? or "
				+ "bulletin.user.account like ? or "
				+ "bulletin.category.name like ? or "
				+ "bulletin.date like ? or "
				+ "bulletin.browseInforMationCount like ? "
				+ "ORDER BY bulletin.date DESC";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, "%"+search+"%");
		query.setParameter(1,"%"+search+"%");
		query.setParameter(2,"%"+search+"%");
		query.setString(3,"%"+search+"%");
		query.setString(4,"%"+search+"%");
		return query;
	}
	@Override
	public Page findByCategoryIdGetPage(int categoryId, int everyPage,
			int currentPage) {
		String hql = "SELECT count(*) FROM Bulletin bulletin WHERE bulletin.category.id = "+categoryId+" AND bulletin.state = "+Bulletin.BULLETIN_STATE_TRUE+" ORDER BY bulletin.date DESC";
		return super.getConditionPage(everyPage, currentPage, hql);
	}
	
	@Override
	public Page getBySearchPage(String searchStr, int everyPage, int currentPage) {
		String hql = "SELECT count(*) FROM Bulletin bulletin WHERE  bulletin.state = ? AND bulletin.title like ?  ORDER BY bulletin.date DESC";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, Bulletin.BULLETIN_STATE_TRUE);
		query.setParameter(1, "%"+searchStr+"%");
		Page page = PageUtil.createPage(everyPage, Integer.parseInt(query.uniqueResult().toString()), currentPage);
		return page;
	}
	@Override
	public List<Bulletin> getBySearchList(String searchStr, Page page) {
		String hql = " FROM Bulletin bulletin WHERE  bulletin.state = ? AND bulletin.title like ?  ORDER BY bulletin.date DESC";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, Bulletin.BULLETIN_STATE_TRUE);
		query.setParameter(1, "%"+searchStr+"%");
		query.setMaxResults(page.getEveryPage());
		query.setFirstResult(page.getBeginIndex());
		return query.list();
	}
}
