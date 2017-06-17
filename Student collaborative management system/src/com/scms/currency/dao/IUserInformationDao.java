package com.scms.currency.dao;

import com.scms.currency.dao.base.IBaseDao;
import com.scms.currency.entity.UserInformation;

/** 
 * @author  航
 * @version 创建时间：2017年2月16日 上午11:45:15 
 * 类说明 
 */
public interface IUserInformationDao extends IBaseDao<UserInformation> {
	UserInformation getByUser(int userId);
	/**
	 * User ID
	 * @param userId
	 */
	void deleteByUser (int userId);
}
