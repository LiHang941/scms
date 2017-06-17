package com.scms.user.dao.impl;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.scms.bulletin.entity.InformationSendRole;
import com.scms.currency.dao.base.impl.BaseDao;
import com.scms.user.dao.IRoleDao;
import com.scms.user.entity.Authority;
import com.scms.user.entity.Role;
import com.scms.user.entity.User;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年2月11日 上午11:30:28 
 * 类说明 
 */
@Repository
public class RoleDao extends  BaseDao<Role> implements IRoleDao {
	
	@Override
	public List<Role> getAll() {
		Query query = getSession().createQuery("FROM Role role WHERE role.state = ? ORDER BY role.id" );
		query.setInteger(0, Role.Role_State_true);
		return query.list();
	}
	@Override
	public Role findByName(String name) {
		Session session = getSession();
		Query query = session.createQuery("FROM Role role WHERE role.name = ? AND role.state = ?");
		List <Role> list = query.setString(0, name).setInteger(0, Role.Role_State_true).list();
		if(list== null || list.size() == 0)
			return null;
		return list.get(0);
	}
	@Override
	public Page getPage(int everyPage, int currentPage){
		String hql = "SELECT count(*) FROM Role role WHERE role.state = "+Role.Role_State_true+" ORDER BY role.id ";
		return super.getConditionPage(everyPage, currentPage, hql);
	}
	@Override
	public List<Role> getByPage(Page page){
		String hql = "FROM Role role WHERE role.state = "+Role.Role_State_true+" ORDER BY role.id ";
		return super.findByCondition(hql, page);
	}
	
	@Override
	public void deleteAuthority(Role role) {
		if(role.getAuthoritys() == null)
			return;
		for (Authority au :role.getAuthoritys()) {
			super.execute("Delete from Authority authority where authority.id= " + au.getId());
		}
	}
	@Override
	public List<User> getUserByRole (int roleId){
		String hql  = "FROM User user WHERE user.role.id = ? AND user.isDelete = ?";
		Query  query = getSession().createQuery(hql).setParameter(0, roleId).setParameter(1, User.User_Delete_false);
		return query.list();
	}

	
	@Override
	public List<User> getUserByRoles (List<InformationSendRole> roles) {
		if(roles == null || roles.size() == 0)
			return null;
		StringBuilder sb = new StringBuilder();
		for (Iterator<InformationSendRole> iterator = roles.iterator(); iterator.hasNext();) {
			InformationSendRole informationSendRole = iterator.next();
			sb.append("'");
			sb.append(informationSendRole.getRole().getId());
			sb.append("'");
			sb.append(",");
		}
		String temp = sb.toString().substring(0, sb.toString().length() -1 );
		String hql = "SELECT user FROM User user WHERE user.isDelete = ? AND user.role.state = ? AND user.role.id in( "+temp+" ) ";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, User.User_Delete_false);
		query.setInteger(1, Role.Role_State_true);
		return query.list();
	}
}
