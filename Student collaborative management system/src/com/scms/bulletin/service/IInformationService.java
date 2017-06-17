package com.scms.bulletin.service;

import java.util.List;

import com.scms.bulletin.entity.Information;
import com.scms.currency.service.base.IPageService;
import com.scms.user.entity.Role;
import com.scms.user.entity.User;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年2月18日 下午2:09:40 
 * 类说明 
 */
public interface IInformationService extends  IPageService{
	/**
	 * 通过Id获取
	 * @param information
	 * @return
	 */
	Information getInformation(Information information);
	
	/**
	 * 删除
	 * @param information
	 * @return
	 */
	boolean delete(Information information);
	
	/**
	 * 提醒
	 * @param information
	 * @return
	 */
	boolean remind(Information information);
	/**
	 * 获取角色列表
	 * @return
	 */
	List<Role> getRoles ();
	
	/**
	 * 获取列表
	 * @param page 页码
	 * @param lenght 分页长度
	 * @return
	 */
	List<Information> getByPage(Page page);
	/**
	 * 搜索返回列表
	 * @param page 页码
	 * @param searchStr 搜索字串
	 * @return
	 */
	List<Information> getBySearch(Page page,String searchStr);
	/**
	 * 修改
	 * @param information
	 * @param role
	 * @return
	 */
	boolean update(Information information, List<Role> roles);
	/**
	 * 保存
	 * @param information
	 * @param role
	 * @return
	 */
	boolean save(Information information, List<Role> roles,User user);

}
