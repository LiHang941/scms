package com.scms.user.service;

import java.util.List;

import com.scms.currency.service.base.IPageService;
import com.scms.sys.entity.Modular;
import com.scms.user.entity.Role;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年2月11日 上午11:37:24 
 * 类说明 
 */
public interface IRoleService extends IPageService {
	
	Role findById (Role role);
	Role findByName(Role role);
	
	List<Role> getAll();
	/**
	 * 保存于更新通用方法   
	 * 校验入口
	 * 
	 * 如果存在id  则是更新
	 * 如果不存在ID 则是添加
	 * @param role 角色对象
	 * @param list 模块列表
	 * @return
	 */
	boolean save(Role role,List<Modular> list);
	/**
	 * 修改一个角色对象是否有效
	 * @param role 角色对象
	 * @return
	 */
	boolean updateState (Role role);
	
	
	/**
	 * 获取集合
	 * @param page
	 * @return
	 */
	List<Role> getByPage(Page page) ;
	
	
}
