package com.scms.sys.config;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.scms.user.entity.Authority;
import com.scms.user.entity.Role;
import com.scms.user.entity.User;

/** 
 * @author  航
 * @version 创建时间：2017年1月27日 下午1:52:37 
 * 类说明 
 * 
 * 封装了一些权限常量     方便拦截器进行权限拦截
 * 
 * 
 */
public class AuthorityManagement {
	
	
	/**
	 * 用户模块
	 */
	public static final String USER_MODULAR_AUTHORTIY = "用户管理";
	
	/**
	 * 公告模块
	 */
	public static final String BULLETIN_MODULAR_AUTHORTIY = "公告管理";
	
	/**
	 * 系统模块
	 */
	public static final String SYS_MODULAR_AUTHORTIY = "系统管理";
	
	/**
	 * 判断用户是否有指定的权限
	 * @param modularName 模块名称
	 * @param u 
	 * @return ture 有权限  false 无权限
	 */
	public static  boolean isAuthority(String modularName,User u){
		try{
			if(u.getRole().getState() == Role.Role_State_false)
				return false;
			List <Authority> set = u.getRole().getAuthoritys();
			for(Authority a : set){
				if(a.getModular().getName().equals(modularName)){
					return true;
				}
			}
		}catch(Exception e){
			
		}
		return false;
	}
	/**
	 * 权限排序  
	 * 
	 * 按照模块ID排序
	 * @return
	 */
	public static List<Authority> authority_sort(List<Authority> authorities){
		if(authorities == null)
			return authorities;
		Comparator<Authority> com = new Comparator<Authority>(){
			@Override
			public int compare(Authority a1, Authority a2) {
				return a1.getModular().getId() - a2.getModular().getId();
			}
		};
		Collections.sort(authorities, com);
		return authorities;
	}
	
}
