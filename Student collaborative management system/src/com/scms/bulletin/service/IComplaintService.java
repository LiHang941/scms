package com.scms.bulletin.service;

import java.util.List;

import com.scms.bulletin.entity.Complaint;
import com.scms.currency.exception.ServiceException;
import com.scms.currency.service.base.IBaseService2;

/** 
 * @author  航
 * @version 创建时间：2017年3月3日 上午11:09:59 
 * 类说明 
 */
public interface IComplaintService extends IBaseService2<Complaint> {

	void saveComplaint(Complaint complaint) throws ServiceException;

	Complaint findByComplaintId(Complaint complaint);

	List<String> getAllProblemContent();

	void handle(Complaint complaint);

}
