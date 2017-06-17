package com.scms.user.util;

import com.scms.user.entity.Role;
import com.scms.user.entity.User;

/** 
 * @author  航
 * @version 创建时间：2017年2月22日 下午12:14:07 
 * 类说明 
 * 
 * 懒加载数据加载
 */
public class LazyDataLoadUtils {
	/**
	 * 加载user.Role
	 * @param user
	 */
	public static void loadUserAndRole (User user){
		if(user == null || user.getRole() == null )
			return;
		user.getRole().getId();
		user.getRole().getName();
		user.getRole().getState();
	}
	/**
	 * 加载user的所有关联数据
	 * @param user
	 */
	public static void loadUserAll (User user){
		if(user == null)
			return;
		user.toString();
	}
	
	public static void loadRoleAuthority (Role role){
		if(role!=null){
			role.getAuthoritysStr();
		}
	}
	
}
