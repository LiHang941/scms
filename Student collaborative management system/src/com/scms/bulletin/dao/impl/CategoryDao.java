package com.scms.bulletin.dao.impl;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.scms.bulletin.dao.ICategoryDao;
import com.scms.bulletin.entity.Category;
import com.scms.currency.dao.base.impl.BaseDao;
import com.scms.util.Page;
import com.scms.util.PageUtil;

/**
 * @author 航
 * @version 创建时间：2017年1月11日 下午8:26:09 类说明
 */
@Repository
public class CategoryDao extends BaseDao<Category> implements ICategoryDao{

	@Override
	public Page getPage(int everyPage, int currentPage){
		String hql = "SELECT count(*) FROM Category category ORDER BY category.id ASC";
		return super.getConditionPage(everyPage, currentPage, hql);
	}
	@Override
	public List<Category> getByPage(Page page){
		String hql = "FROM Category category ORDER BY category.id ASC ";
		return super.findByCondition(hql, page);
	}
	@Override
	public Page getPage(int everyPage, int currentPage, String search) {
		int totalCount  =  getBySearchHql(search).list().size();
		Page page = PageUtil.createPage(everyPage, totalCount, currentPage);
		return page;
	}
	@Override
	public List<Category> getByPage(Page page, String search) {
		return getBySearchHql(search)
				.setMaxResults(page.getEveryPage())
				.setFirstResult(page.getBeginIndex()).list();
	}
	private Query getBySearchHql (String search){
		String hql =" FROM Category category WHERE category.id like ? or "
				+ "category.name like ?  ORDER BY category.id ASC ";
		Query query = getSession().createQuery(hql);
		query.setString(0, "%"+search+"%");
		query.setString(1, "%"+search+"%");
		return query ;	
	}
	

	
	
}