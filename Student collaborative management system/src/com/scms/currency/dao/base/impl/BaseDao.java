package com.scms.currency.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.scms.currency.dao.base.IBaseDao;
import com.scms.util.Page;
import com.scms.util.PageUtil;
import com.scms.util.data.GeneralQueryHelper;

/**
 * @author 航
 * @version 创建时间：2017年1月11日 下午8:26:09 类说明
 */
public abstract class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T> {
	
	private String className;
	// 当前操作的实际的bean类型
	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public BaseDao() {
		Type type = this.getClass().getGenericSuperclass();
		// 转换为参数化类型
		ParameterizedType pt = (ParameterizedType) type; // BaseDao<Employee>
		// 得到实际类型
		Type types[] = pt.getActualTypeArguments();
		// 获取实际类型
		clazz = (Class<T>) types[0];
		className = clazz.getSimpleName();// 例如：Employee
	}
	
	@Override
	public GeneralQueryHelper<T> getByQueryHelper(
			GeneralQueryHelper<T> generalQueryHelper) {
		Query query = getSession().createQuery(generalQueryHelper.getQueryListHql());
		List<Object> parameter = generalQueryHelper.getParameters();
		for(int i=0 ;i<parameter.size();i++){
			query.setParameter(i, parameter.get(i));
		}
		//判断是否分页
		if(generalQueryHelper.isPage()){
			Query pageQuery = getSession().createQuery(generalQueryHelper.getQueryCountHql());
			List<Object> pageParameter = generalQueryHelper.getParameters();
			for(int i=0 ;i<pageParameter.size();i++){
				pageQuery.setParameter(i, pageParameter.get(i));
			}
			int totalCount = Integer.parseInt(pageQuery.uniqueResult().toString());  //记录数
			Page page = PageUtil.createPage(generalQueryHelper.getPageLenght(), totalCount, generalQueryHelper.getIndexPage());
			//分页的情况
			generalQueryHelper.setPage(page);
			query.setMaxResults(page.getEveryPage()).setFirstResult(page.getBeginIndex());
			generalQueryHelper.setList(query.list());
		}else{
			//不分页的情况
			generalQueryHelper.setList(query.list());
		}
		
		return generalQueryHelper;
	}
	
	@Override
	public void save(T t) {
		getSession().save(t);
	}

	@Override
	public void update(T t) {
		getSession().update(t);

	}

	@Override
	public void delete(int id) {
		getSession()
				.createQuery("Delete from "+className+" where id= " + id)
				.executeUpdate();
	}
	@Override
	public void delete(T t) {
		getSession().delete(t);
	}
	@Override
	public void execute(String hql) {
		getSession()
		.createQuery(hql)
		.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		return getSession()
				.createQuery("from " + className).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByCondition(String hql) {
		return getSession().createQuery(hql)
				.list();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByCondition(String hql, Page page) {
		return getSession().createQuery(hql)
				.setMaxResults(page.getEveryPage())
				.setFirstResult(page.getBeginIndex())
				.list();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public T findById(int id) {
		return (T) getSession().get(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T findById(Serializable id) {
		return (T) getSession().get(clazz, id);
	}
	
	@Override
	public int getAllCount() {
		return Integer.parseInt(getSession()
				.createQuery("select count(*) from " + className)
				.uniqueResult().toString());
	}
	@Override
	public int getConditionCount(String hql) {
		return Integer.parseInt(getSession()
				.createQuery(hql)
				.uniqueResult().toString());
	}
	
	@Override
	public Page getDefaultPage(int everyPage, int currentPage) {
		int totalCount = getAllCount();  //记录数
		Page page = PageUtil.createPage(everyPage, totalCount, currentPage);
		return page;
	}
	
	@Override
	public Page getConditionPage(int everyPage, int currentPage, String hql) {
		int totalCount = getConditionCount(hql);
		Page page = PageUtil.createPage(everyPage, totalCount, currentPage);
		return page;
	}
	@Resource(name="sessionFactory")
	public void setBaseDaoSessionFactory(SessionFactory  sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
}
