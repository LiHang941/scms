package com.scms.currency.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.scms.currency.service.base.IBaseService;
import com.scms.sys.service.IOperationRecordService;
import com.scms.sys.util.OperationRecordUtil;

/** 
 * @author  航
 * @version 创建时间：2017年2月7日 下午12:24:13 
 * 类说明 
 * 
 * 抽取公共方法  比如 添加操作日志
 */
public abstract class AbsBaseService implements IBaseService{
	@Resource
	private IOperationRecordService operationRecordService;
	@Override
	public void addLog(String record, HttpServletRequest request) {
		operationRecordService.add(OperationRecordUtil.createOperationRecord(record, request));
	}
	
	public void setOperationRecordService(
			IOperationRecordService operationRecordService) {
		this.operationRecordService = operationRecordService;
	}
	

}
