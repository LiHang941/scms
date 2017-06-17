package com.scms.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.scms.currency.service.impl.AbsBaseService;
import com.scms.sys.dao.IOperationRecordDao;
import com.scms.sys.entity.OperationRecord;
import com.scms.sys.service.IOperationRecordService;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年2月6日 下午4:40:33 
 * 类说明 
 */
@Service
public class OperationRecordService extends AbsBaseService implements IOperationRecordService {
	@Resource
	private IOperationRecordDao operationRecordDao;
	
	/**
	 * 管理用户界面的集合长度
	 */
	public static final int OperationRecord_Manager_List_Length = 9;
	
	@Override
	public void addLog(String record, HttpServletRequest request) {
		record  = "[系统模块-操作记录]" + record;
		super.addLog(record, request);
	}
	@Override
	public void add(OperationRecord operationRecord) {
		operationRecordDao.save(operationRecord);
	}

	@Override
	public boolean delete(OperationRecord operationRecord) {
		if(operationRecord.getId() == 0)
			return false;
		OperationRecord temp = operationRecordDao.findById(operationRecord.getId());
		if(temp == null)
			return false;
		operationRecordDao.delete(temp.getId());
		return true;
	}

	@Override
	public List<OperationRecord> getByPage(Page page) {
		return operationRecordDao.getByPage(page);
	}
	
	public Page getPage(int p, int lenght){
		return operationRecordDao.getPage(lenght, p);
	}
	@Override
	public List<OperationRecord> getBySearch(Page page,String searchStr) {
		if(searchStr.equals(""))
			return getByPage(page);
		return operationRecordDao.getByPage(page, searchStr);
	}
	public Page getSearchPage(int p, int lenght,String searchStr){
		if(searchStr.equals(""))
			return getPage(p,lenght);
		return operationRecordDao.getPage(lenght, p,searchStr);
	}

	public void setOperationRecordDao(IOperationRecordDao operationRecordDao) {
		this.operationRecordDao = operationRecordDao;
	}
	
}
