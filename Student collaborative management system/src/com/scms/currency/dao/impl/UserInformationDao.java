package com.scms.currency.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.scms.currency.dao.IUserInformationDao;
import com.scms.currency.dao.base.impl.BaseDao;
import com.scms.currency.entity.UserInformation;

/** 
 * @author  航
 * @version 创建时间：2017年2月16日 上午11:46:06 
 * 类说明 
 */
@Repository
public class UserInformationDao extends BaseDao<UserInformation> implements IUserInformationDao{

	@SuppressWarnings("unchecked")
	@Override
	public UserInformation getByUser(int userId) {
		List <UserInformation> list = getSession().createQuery("FROM UserInformation userInformation WHERE userInformation.user.id = ?")
		.setInteger(0, userId).list();
		
		if(list == null || list.size() == 0){
			return null;
		}
		return list.get(0);
	}

	@Override
	public void deleteByUser(int userId) {
		getSession().createQuery("DELETE FROM UserInformation userInformation WHERE userInformation.user.id = ?")
		.setInteger(0, userId).executeUpdate();
	}
		
	
}
