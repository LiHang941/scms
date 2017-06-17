package com.scms.bulletin.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.scms.bulletin.dao.IComplaintDao;
import com.scms.bulletin.entity.Complaint;
import com.scms.bulletin.service.IComplaintService;
import com.scms.bulletin.util.ComplaintUtils;
import com.scms.currency.exception.ServiceException;
import com.scms.currency.service.impl.AbsBaseService2;

/** 
 * @author  航
 * @version 创建时间：2017年3月3日 上午11:10:46 
 * 类说明 
 */
@Service
public class ComplaintService extends AbsBaseService2<Complaint> implements IComplaintService{
	private IComplaintDao complaintDao;
	@Override
	public void saveComplaint(Complaint complaint) throws ServiceException {
		if(complaint == null){
			throw new ServiceException("请检查内容");
		}
		if(StringUtils.isBlank(complaint.getEmail())&& StringUtils.isBlank( complaint.getContact())){
			throw new ServiceException("请输入联系方式,方便我们联系");
		}
		if(!StringUtils.isBlank(complaint.getEmail()) && !ComplaintUtils.isEmail(complaint.getEmail())){
			throw new ServiceException("请输入正确的email,方便我们联系");
		}
		complaint = ComplaintUtils.filterComplaint(complaint);
		complaint.setState(Complaint.state_Untreated);
		complaint.setDate(new Date());
		super.save(complaint);
	}
	
	@Override
	public Complaint findByComplaintId(Complaint complaint){
		if(complaint == null || complaint.getId()<=0){
			return null;
		}
		return complaintDao.findById(complaint.getId());
	}
	
	@Override
	public void delete(Complaint complaint){
		 complaint = findByComplaintId( complaint);
		 if( complaint!= null){
			 complaintDao.delete(complaint.getId());
		 }
	}
	@Override
	public List<String> getAllProblemContent(){
		return complaintDao.getAllProblemContent();
	}
	
	@Override
	public void handle(Complaint complaint){
		complaint = findByComplaintId(complaint);
		if(complaint != null){
			complaint.setState(Complaint.state_Handle);
			super.update(complaint);
		}
	}
	
	
	
	@Resource
	public void setComplaintDao(IComplaintDao complaintDao) {
		super.setBaseDao(complaintDao);
		this.complaintDao = complaintDao;
	}
}
