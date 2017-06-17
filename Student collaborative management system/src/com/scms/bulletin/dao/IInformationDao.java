package com.scms.bulletin.dao;

import java.util.List;

import com.scms.bulletin.entity.Information;
import com.scms.bulletin.entity.InformationAndUser;
import com.scms.bulletin.entity.InformationSendRole;
import com.scms.currency.dao.base.IBaseDao;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年2月18日 下午2:19:09 
 * 类说明 
 */

public interface IInformationDao extends IBaseDao<Information> {
	/**
	 * 
	 * @param everyPage 显示多少数据
	 * @param currentPage 当前页
	 * @return
	 */
	Page getPage(int everyPage, int currentPage);
	/**
	 * 
	 * @param everyPage 显示多少数据
	 * @param currentPage 当前页
	 * @param search 搜索内容
	 * @return
	 */
	Page getPage(int everyPage, int currentPage,String search);
	/**
	 * 通过指定的分页对象获取集合
	 * @param page
	 * @return
	 */
	List<Information> getByPage(Page page);
	/**
	 * 
	 * @param page
	 * @param search
	 * @return
	 */
	List<Information> getByPage(Page page,String search);
	/**
	 * 删除消息和角色的中间表数据
	 * @param information
	 */
	void deleteInforMationSendRole(List<InformationSendRole> roles);
	/**
	 * 删除消息和用户的中间表关系
	 * @param information
	 */
	void deleteInformationAndUser(int informationId);
	/**
	 * 保存一个用户与消息的中间表数据
	 * @param information
	 */
	void createInformationAndUser(InformationAndUser informationAndUser );
	/**
	 * 查看消息成功浏览次数自增
	 * @param informationId
	 */
	void showInformationBrowseInforMationCountAdd(int informationId);
	
	/**
	 * 查看消息成功，修改状态为查看
	 * @param userId
	 * @param informationId 
	 */
	void setInformationShowStateByUser(int userId, int informationId);
	
	/**
	 * 通过用户Id  信息id 查内容 
	 * 返回一个有效的 InformationAndUser
	 * @param userId
	 * @return
	 */
	InformationAndUser getUserAndInformationByUserIdOrInformationId(int userId,int informationId);
	
	/**
	 * 
	 * @param everyPage 显示多少数据
	 * @param currentPage 当前页
	 * @return
	 */
	Page getPage(int everyPage, int currentPage,int userId);
	/**
	 * 
	 * @param everyPage 显示多少数据
	 * @param currentPage 当前页
	 * @param search 搜索内容
	 * @return
	 */
	Page getPage(int everyPage, int currentPage,String search,int userId);
	/**
	 * 通过指定的分页对象获取集合
	 * @param page
	 * @return
	 */
	List<InformationAndUser> getByPage(Page page,int userId);
	/**
	 * 
	 * @param page
	 * @param search
	 * @return
	 */
	List<InformationAndUser> getByPage(Page page,String search,int userId);
	
	/**
	 * 删除中级表数据
	 * @param id
	 */
	void deleteInformationAndUserById(int informationAndUserId);
	
	int getMyInformationCount(int userId);
	
}
