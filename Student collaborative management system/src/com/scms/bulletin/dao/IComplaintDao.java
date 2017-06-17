package com.scms.bulletin.dao;

import java.util.List;

import com.scms.bulletin.entity.Complaint;
import com.scms.currency.dao.base.IBaseDao;

/** 
 * @author  航
 * @version 创建时间：2017年3月1日 下午6:07:23 
 * 类说明 
 */
public interface IComplaintDao extends IBaseDao<Complaint> {

	List<String> getAllProblemContent();
	
}
