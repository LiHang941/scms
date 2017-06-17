package com.scms.user.dao;

import java.util.List;

import com.scms.bulletin.entity.InformationSendRole;
import com.scms.currency.dao.base.IBaseDao;
import com.scms.user.entity.Role;
import com.scms.user.entity.User;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年2月11日 上午11:30:04 
 * 类说明 
 */
public interface IRoleDao extends IBaseDao<Role> {
	/**
	 * 
	 * @param everyPage 显示多少数据
	 * @param currentPage 当前页
	 * @return
	 */
	Page getPage(int everyPage, int currentPage);
	
	/**
	 * 通过指定的分页对象获取集合
	 * @param page
	 * @return
	 */
	List<Role> getByPage(Page page);
	/**
	 * 通过名称查Role
	 * @param name
	 * @return
	 */
	Role findByName(String name);
	/**
	 * 删除权限
	 */
	void deleteAuthority(Role role);
	/**
	 * 获取指定角色的未删除用户
	 * @param roleId
	 * @return
	 */
	List<User> getUserByRole(int roleId);
	
	
	/**
	 * 通过
	 * @param roles
	 * @return
	 */
	List<User> getUserByRoles (List<InformationSendRole> roles);
	
	
}
