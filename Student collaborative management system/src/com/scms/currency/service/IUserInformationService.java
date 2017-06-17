package com.scms.currency.service;

import com.scms.currency.entity.UserInformation;
import com.scms.currency.service.base.IBaseService;
import com.scms.user.entity.User;

/** 
 * @author  航
 * @version 创建时间：2017年2月16日 下午4:11:04 
 * 类说明 
 */
public interface IUserInformationService extends IBaseService {
	boolean update(User user,UserInformation userInformation);
}
