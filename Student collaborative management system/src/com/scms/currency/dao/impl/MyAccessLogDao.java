package com.scms.currency.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.scms.currency.dao.IMyAccessLogDao;
import com.scms.currency.dao.base.impl.BaseDao;
import com.scms.sys.entity.UserAccessLog;
import com.scms.user.entity.User;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年2月13日 下午3:06:14 
 * 类说明 
 */
@Repository
public class MyAccessLogDao extends BaseDao<UserAccessLog> implements IMyAccessLogDao {

	@SuppressWarnings("unchecked")
	@Override
	public UserAccessLog getByLately(User user) {
		List<UserAccessLog> list = getSession()
		.createQuery("FROM UserAccessLog userAccessLog WHERE userAccessLog.user.id = ? ORDER BY userAccessLog.date DESC ")
		.setParameter(0, user.getId()).list();
		
		if(list.size() > 1 ){
			//获取最近一天的
			return list.get(1);
		}
			//如果只登录一次就显示当前的
		if(list.size() == 1){
			return list.get(0);
		}
		
		UserAccessLog ual = new UserAccessLog();
		ual.setDate(new Date());
		return ual;
	}

	@Override
	public List<UserAccessLog> getByUser(User user, Page page) {
		String hql = "FROM UserAccessLog userAccessLog WHERE userAccessLog.user.id = "+user.getId()+" ORDER BY userAccessLog.date DESC ";
		return super.findByCondition(hql,page);
	}

	@Override
	public Page getPage(User user, int everyPage, int currentPage) {
		String hql = "SELECT count(*) FROM UserAccessLog userAccessLog WHERE userAccessLog.user.id = "+user.getId()+" ORDER BY userAccessLog.date DESC ";
		Page page = super.getConditionPage(everyPage, currentPage, hql);
		return page;
	}
	
}
