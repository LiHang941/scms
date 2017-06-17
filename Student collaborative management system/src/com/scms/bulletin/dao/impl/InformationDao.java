package com.scms.bulletin.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Repository;

import com.scms.bulletin.dao.IInformationDao;
import com.scms.bulletin.entity.Information;
import com.scms.bulletin.entity.InformationAndUser;
import com.scms.bulletin.entity.InformationSendRole;
import com.scms.currency.dao.base.impl.BaseDao;
import com.scms.user.entity.Role;
import com.scms.user.entity.User;
import com.scms.util.Page;
import com.scms.util.PageUtil;
import com.scms.util.search.SearchStrPanduan;

/** 
 * @author  航
 * @version 创建时间：2017年2月18日 下午2:19:56 
 * 类说明 
 */
@Repository
public class InformationDao extends BaseDao<Information> implements IInformationDao {
	
	@Override
	public void createInformationAndUser(InformationAndUser informationAndUser) {
		getSession().save(informationAndUser);
	}
	
	@Override
	public void deleteInforMationSendRole(List<InformationSendRole> roles){
		if(roles == null || roles.size () == 0)
			return;
		for(InformationSendRole imsr : roles){
			String hql = "DELETE InformationSendRole WHERE id = ?";
			getSession().createQuery(hql).setInteger(0, imsr.getId()).executeUpdate();
		}
	}
	@Override
	public void deleteInformationAndUser(int informationId){
		String hql = "DELETE InformationAndUser informationAndUser WHERE informationAndUser.information.id = ?";
		getSession().createQuery(hql).setInteger(0, informationId).executeUpdate();
	}
	
	@Override
	public Page getPage(int everyPage, int currentPage){
		String hql = "SELECT count(*) FROM Information information ORDER BY information.date DESC ";
		return super.getConditionPage(everyPage, currentPage, hql);
	}
	@Override
	public List<Information> getByPage(Page page){
		String hql = "FROM Information information ORDER BY information.date DESC ";
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
	public List<Information> getByPage(Page page, String search) {
		return getBySearchHql(search).setMaxResults(page.getEveryPage())
				.setFirstResult(page.getBeginIndex())
				.list();
	}
	private Query getBySearchHql (String search){
		if(SearchStrPanduan.isContainChinese(search)){
			//存在中文字串
			String hql =" FROM Information information WHERE  "
					+ "information.title like ? or "
					+ "information.user.account like ? "
					+ " ORDER BY information.date DESC  ";
			Query query = getSession().createQuery(hql);
			query.setString(0, "%"+search+"%");
			query.setString(1, "%"+search+"%");
			return query;
		}		
		String hql =" FROM Information information WHERE  "
				+ "information.title like ? or "
				+ "information.user.account like ? or "
				+ "information.browseInforMationCount like ? or "
				+ "information.date like ?"
				+ " ORDER BY information.date DESC  ";
		Query query = getSession().createQuery(hql);
		query.setString(0, "%"+search+"%");
		query.setString(1, "%"+search+"%");
		query.setString(2, "%"+search+"%");
		query.setString(3, "%"+search+"%");
		return query ;	
	}
	
	private Query getBySearchHql (String search,int userId){
		if(SearchStrPanduan.isContainChinese(search)){
			//存在中文字串
			String hql =" FROM InformationAndUser informationAndUser  WHERE  "
					+ "informationAndUser.information.title like ? or "
					+ "informationAndUser.information.user.name like ? "
					+ " AND informationAndUser.user.id = ?  "
					+ " ORDER BY informationAndUser.information.date DESC  ";
			Query query = getSession().createQuery(hql);
			query.setString(0, "%"+search+"%");
			query.setString(1, "%"+search+"%");
			query.setInteger(2, userId);
			return query;
		}		
		String hql =" FROM InformationAndUser informationAndUser WHERE  "
				+ "informationAndUser.information.title like ? or "
				+ "informationAndUser.information.user.name like ? or "
				+ "informationAndUser.information.date like ?"
				+ " AND informationAndUser.user.id = ?  "
				+ " ORDER BY informationAndUser.information.date DESC  ";
		Query query = getSession().createQuery(hql);
		query.setString(0, "%"+search+"%");
		query.setString(1, "%"+search+"%");
		query.setString(2, "%"+search+"%");
		query.setInteger(3, userId);
		return query ;	
	}

	@Override
	public void showInformationBrowseInforMationCountAdd(int informationId) {
		try {
			String hql = "UPDATE Information information set information.browseInforMationCount = information.browseInforMationCount+1  WHERE information.id = ?";
			Query query = getSession().createQuery(hql);
			query.setInteger(0, informationId);
			query.executeUpdate();
		} catch (DataAccessResourceFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void setInformationShowStateByUser(int userId, int informationId) {
		try {
			String hql = "UPDATE InformationAndUser informationAndUser set informationAndUser.state = ?  WHERE informationAndUser.information.id = ? AND informationAndUser.user.id = ?";
			Query query = getSession().createQuery(hql);
			query.setInteger(0, InformationAndUser.USER_INFORMATION_STATE_SHOW);
			query.setInteger(1, informationId);
			query.setInteger(2, userId);
			query.executeUpdate();
		} catch (DataAccessResourceFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public InformationAndUser getUserAndInformationByUserIdOrInformationId(int userId,int informationId){
		//用户id 
		//用户是否删除
		//角色是否有效
		String hql = "SELECT informationAndUser FROM InformationAndUser informationAndUser WHERE informationAndUser.user.id = ? AND informationAndUser.information.id = ? AND informationAndUser.user.isDelete = ? AND informationAndUser.user.role.state = ? ";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, userId);
		query.setInteger(1, informationId);
		query.setInteger(2, User.User_Delete_false);
		query.setInteger(3, Role.Role_State_true);
		List<InformationAndUser> roles = query.list();
		if(roles == null || roles.size() ==  0)
			return null;
		return roles.get(0);
	}

	@Override
	public Page getPage(int everyPage, int currentPage, int userId) {
		String hql = "SELECT count(*) FROM InformationAndUser informationAndUser WHERE informationAndUser.user.id = "+userId+" ORDER BY informationAndUser.information.date DESC ";
		return super.getConditionPage(everyPage, currentPage, hql);
	}

	@Override
	public Page getPage(int everyPage, int currentPage, String search,
			int userId) {
		int totalCount  = getBySearchHql(search,userId).list().size();
		Page page = PageUtil.createPage(everyPage, totalCount, currentPage);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InformationAndUser> getByPage(Page page, int userId) {
		String hql = "FROM InformationAndUser informationAndUser WHERE informationAndUser.user.id = "+userId+" ORDER BY informationAndUser.information.date DESC  ";
		return getSession().createQuery(hql)
				.setMaxResults(page.getEveryPage())
				.setFirstResult(page.getBeginIndex())
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InformationAndUser> getByPage(Page page, String search,
			int userId) {
		return getBySearchHql(search,userId).setMaxResults(page.getEveryPage())
				.setFirstResult(page.getBeginIndex())
				.list();
	}
	@Override
	public void deleteInformationAndUserById(int informationAndUserId) {
		String hql = "DELETE InformationAndUser informationAndUser WHERE informationAndUser.id = ? ";
		getSession().createQuery(hql).setInteger(0, informationAndUserId).executeUpdate();
	}
	
	@Override
	public int getMyInformationCount(int userId) {
		String hql = "SELECT COUNT(*) FROM InformationAndUser informationAndUser WHERE informationAndUser.user.id = ? AND informationAndUser.state = ?";
		return Integer.parseInt(getSession()
				.createQuery(hql)
				.setInteger(0, userId)
				.setInteger(1, InformationAndUser.USER_INFORMATION_STATE_NO)
				.uniqueResult().toString());
	}
}
