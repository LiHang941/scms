package com.scms.bulletin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.scms.bulletin.dao.IComplaintDao;
import com.scms.bulletin.entity.Complaint;
import com.scms.currency.dao.base.impl.BaseDao;

/** 
 * @author  航
 * @version 创建时间：2017年3月1日 下午6:08:30 
 * 类说明 
 */
@Repository
public class ComplaintDao extends BaseDao<Complaint> implements IComplaintDao {

	@Override
	public List<String> getAllProblemContent() {
		String hql = "SELECT DISTINCT complaint.problemContent FROM Complaint complaint";
		return getSession().createQuery(hql).list();
	}
	
}
