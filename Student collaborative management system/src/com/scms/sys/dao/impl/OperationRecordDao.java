package com.scms.sys.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.scms.currency.dao.base.impl.BaseDao;
import com.scms.sys.dao.IOperationRecordDao;
import com.scms.sys.entity.OperationRecord;
import com.scms.util.Page;
import com.scms.util.PageUtil;
import com.scms.util.search.SearchStrPanduan;

/** 
 * @author  航
 * @version 创建时间：2017年2月6日 下午4:38:06 
 * 类说明 
 */
@Repository
public class OperationRecordDao extends BaseDao<OperationRecord> implements
		IOperationRecordDao {
	@Override
	public Page getPage(int everyPage, int currentPage){
		String hql = "SELECT count(*) FROM OperationRecord operationRecord ORDER BY operationRecord.date DESC ";
		return super.getConditionPage(everyPage, currentPage, hql);
	}
	@Override
	public List<OperationRecord> getByPage(Page page){
		String hql = "FROM OperationRecord operationRecord ORDER BY operationRecord.date DESC ";
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
	public List<OperationRecord> getByPage(Page page, String search) {
		Query query = getBySearchHql(search);
		query.setMaxResults(page.getEveryPage());
		query.setFirstResult(page.getBeginIndex());
		return query.list();
	}
	private Query getBySearchHql (String search){
		if(SearchStrPanduan.isContainChinese(search)){
			//存在中文字串
			String hql =" FROM OperationRecord operationRecord WHERE operationRecord.user.account like ? or "
					+ "operationRecord.record like ? "
					+ " ORDER BY operationRecord.date DESC  ";
			Query query = getSession().createQuery(hql);
			query.setParameter(0, "%"+search+"%");
			query.setParameter(1, "%"+search+"%");
			return query;
		}		
		String hql =" FROM OperationRecord operationRecord WHERE operationRecord.user.account like ? or "
				+ "operationRecord.record like ? or "
				+ "operationRecord.date like ?  ORDER BY operationRecord.date DESC  ";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, "%"+search+"%");
		query.setParameter(1, "%"+search+"%");
		query.setString(2, "%"+search+"%");
		return query ;	
	}
	
}
