package com.scms.user.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.scms.currency.dao.base.impl.BaseDao;
import com.scms.user.dao.IUserDao;
import com.scms.user.entity.User;
import com.scms.user.util.DesUtils;
import com.scms.util.Page;
import com.scms.util.PageUtil;
import com.scms.util.search.SearchStrPanduan;

/**
 * @author 航
 * @version 创建时间：2017年1月13日 下午12:12:08 类说明
 */

@Repository
public class UserDao extends BaseDao<User> implements IUserDao {

	private String defaultHql = "FROM User user WHERE  user.isDelete = "
			+ User.User_Delete_false + " ORDER BY user.date DESC";

	@Override
	public List<User> getAll() {
		String hql = "FROM User user WHERE  user.isDelete = "
				+ User.User_Delete_false + " ORDER BY user.date DESC";
		List<User> users = super.findByCondition(hql);
		return users;
	}

	@Override
	public void save(User user) {
		try {
			DesUtils deu = new DesUtils();
			// 加密
			user.setPwd(deu.encrypt(user.getPwd()));
		} catch (Exception e) {
			throw new RuntimeException("加密失败:" + user.toString());
		}
		super.save(user);
	}

	@Override
	public List<User> getByPage(Page page) {
		String hql = "FROM User user WHERE user.isDelete = "
				+ User.User_Delete_false + " ORDER BY user.date DESC";
		List<User> users = super.findByCondition(hql, page);
		return users;
	}

	@Override
	public List<User> getByPage(Page page, String searchStr) {
		Query query = getUserSearchHql(searchStr);
		query.setMaxResults(page.getEveryPage());
		query.setFirstResult(page.getBeginIndex());
		return query.list();
	}
	@Override
	public List<User> getByUserIds (char [] chs) throws Exception{
		StringBuilder sb = new StringBuilder ();
		for(int i=0;i<chs.length ;i++){
			Integer.parseInt(chs[i] + "");
			if(i == chs.length -1){
				sb.append("'");
				sb.append(chs[i]);
				sb.append("'");
				continue;
			}
			sb.append("'");
			sb.append(chs[i]);
			sb.append("'");
			sb.append(",");
		}
		String hql = "SELECT user FROM User user WHERE user.id in ("+sb.toString()+") user.isDelete = "+User.User_Delete_false+" ORDER BY user.id ASC";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	public Page getPage(int p, int length) {
		String hql = "SELECT count(*) FROM User user WHERE user.isDelete = "
				+ User.User_Delete_false + " ORDER BY user.date DESC";
		Page page = super.getConditionPage(length, p, hql);
		return page;
	}

	@Override
	public Page getPage(int everyPage, int currentPage, String searchStr) {
		int totalCount  =  getUserSearchHql(searchStr).list().size();
		Page page = PageUtil.createPage(everyPage, totalCount, currentPage);
		return page;
	}

	@Override
	public User findByUserAccount(String account) {
		if (account == null || account.equals("")) {
			return null;
		}
		String hql = "FROM User user WHERE user.account = ? and user.isDelete = ?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, account);
		query.setInteger(1, User.User_Delete_false);
		List<User> users = query.list();
		if (users.size() != 0)
			return users.get(0);
		return null;
	}
	private Query getUserSearchHql(String searchStr) {
		
		if(SearchStrPanduan.isContainChinese(searchStr)){
			//存在中文字串
			String hql = "FROM User user WHERE  user.account like ? "
					+ "or user.name like ? OR user.role.name like ?  ";
			hql = hql + "and user.isDelete = "+User.User_Delete_false+" ORDER BY user.date DESC";
				Query query = getSession().createQuery(hql);
				query.setString(0, "%"+searchStr+"%");
				query.setString(1, "%"+searchStr+"%");
				query.setString(2, "%"+searchStr+"%");
				return query;
		}		
		// 过滤一般字串
		String hql = "FROM User user WHERE  user.account like ? "
						+ "or user.name like ? OR user.role.name like ? OR user.date like ? ";
				hql = hql + "and user.isDelete = "+User.User_Delete_false+" ORDER BY user.date DESC";
		Query query = getSession().createQuery(hql);
		query.setString(0, "%"+searchStr+"%");
		query.setString(1, "%"+searchStr+"%");
		query.setString(2, "%"+searchStr+"%");
		query.setString(3, "%"+searchStr+"%");
		return query;
	}
}
