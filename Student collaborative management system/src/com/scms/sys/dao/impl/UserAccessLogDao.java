package com.scms.sys.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.scms.currency.dao.base.impl.BaseDao;
import com.scms.sys.dao.IUserAccessLogDao;
import com.scms.sys.entity.UserAccessLog;
import com.scms.util.Page;
import com.scms.util.PageUtil;
import com.scms.util.search.SearchStrPanduan;

/** 
 * @author  航
 * @version 创建时间：2017年2月5日 下午4:39:05 
 * 类说明 
 */
@Repository
public class UserAccessLogDao extends BaseDao<UserAccessLog> implements IUserAccessLogDao {
	
	
	@Override
	public Page getPage(int everyPage, int currentPage){
		String hql = "SELECT count(*) FROM UserAccessLog ual ORDER BY ual.date DESC ";
		return super.getConditionPage(everyPage, currentPage, hql);
	}
	@Override
	public List<UserAccessLog> getByPage(Page page){
		String hql = "FROM UserAccessLog ual ORDER BY ual.date DESC ";
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
	public List<UserAccessLog> getByPage(Page page, String search) {
		Query query = getBySearchHql(search);
		query.setMaxResults(page.getEveryPage());
		query.setFirstResult(page.getBeginIndex());
		return query.list();
	}
	private Query getBySearchHql (String search){
		if(SearchStrPanduan.isContainChinese(search)){
			//存在中文字串
			String hql =" FROM UserAccessLog ual WHERE ual.user.account like ? or "
					+ "ual.ip like ?  ORDER BY ual.date DESC ";
			Query query = getSession().createQuery(hql);
			query.setParameter(0, "%"+search+"%");
			query.setParameter(1, "%"+search+"%");
			return query;
		}	
		String hql =" FROM UserAccessLog ual WHERE ual.user.account like ? or "
				+ "ual.ip like ? or "
				+ "ual.date like ?  ORDER BY ual.date DESC ";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, "%"+search+"%");
		query.setParameter(1, "%"+search+"%");
		query.setString(2, "%"+search+"%");
		return query ;	
	}
	
}
