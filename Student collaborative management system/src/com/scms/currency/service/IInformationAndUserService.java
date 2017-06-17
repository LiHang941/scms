package com.scms.currency.service;

import java.util.List;

import com.scms.bulletin.entity.Information;
import com.scms.bulletin.entity.InformationAndUser;
import com.scms.currency.service.base.IBaseService;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年2月24日 下午1:01:07 
 * 类说明 
 *  
 */
public interface IInformationAndUserService extends IBaseService {
	/**
	 * 通过用户id 和 infomation id查询用户是否有资格查看该内容
	 * 没有资格返回null
	 * @param userId
	 * @param information
	 * @return
	 */
	Information ShowInformationByUserId(int userId, Information information);
	
	/**
	 * 
	 * @param userId
	 * @param page
	 * @return
	 */
	List<InformationAndUser> getByPage(int userId,Page page);
	/**
	 * 
	 * @param userId
	 * @param page
	 * @param searchStr
	 * @return
	 */
	List<InformationAndUser> getBySearch(int userId,Page page,String searchStr);
	/**
	 * 
	 * @param userId
	 * @param p
	 * @param lenght
	 * @return
	 */
	Page getPage(int userId,int p, int lenght);
	/**
	 * 
	 * @param userId
	 * @param p
	 * @param lenght
	 * @param searchStr
	 * @return
	 */
	Page getSearchPage(int userId,int p, int lenght,String searchStr);
	/**
	 * 删除
	 * @param userId
	 * @param informationId
	 */
	boolean delete(int userId, Information information);
	/**
	 * 获取该用户未读取的消息
	 * @param userId
	 * @return
	 */
	int getMyInformationCount(int userId);
}
