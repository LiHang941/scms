package com.scms.currency.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.scms.currency.dao.IUserInformationDao;
import com.scms.currency.entity.UserInformation;
import com.scms.currency.service.IUserInformationService;
import com.scms.user.dao.IUserDao;
import com.scms.user.entity.User;

/** 
 * @author  航
 * @version 创建时间：2017年2月16日 下午4:12:00 
 * 类说明 
 */
@Service
public class UserInformationService extends AbsBaseService implements IUserInformationService {
	@Resource
	private IUserDao userDao;
	@Resource
	private IUserInformationDao userInformationDao;
	@Override
	public void addLog(String record, HttpServletRequest request) {
		
	}

	@Override
	public boolean update(User user, UserInformation userInformation) {
		if(user == null || user.getId() == 0) 
			return false;
		
		User temp  = userDao.findById(user.getId());
		if(temp == null)
			return false;
		
		if(userInformation == null)
			userInformation = new UserInformation(true);
		
		temp.setName(user.getName());
		userDao.update(temp);
		userInformationDao.deleteByUser(user.getId());
		userInformation.setUser(temp);
		userInformationDao.save(userInformation);
		return true;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	public void setUserInformationDao(IUserInformationDao userInformationDao) {
		this.userInformationDao = userInformationDao;
	}

}
