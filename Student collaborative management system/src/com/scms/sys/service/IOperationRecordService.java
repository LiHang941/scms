package com.scms.sys.service;

import java.util.List;

import com.scms.currency.service.base.IPageService;
import com.scms.sys.entity.OperationRecord;
import com.scms.util.Page;


/** 
 * @author  航
 * @version 创建时间：2017年2月6日 下午4:39:58 
 * 类说明 
 */
public interface IOperationRecordService extends IPageService {
	/**
	 * 添加日志到
	 * @param operationRecords
	 * @return
	 */
	void add(OperationRecord operationRecord);
	/**
	 * 删除一个日志
	 * @param operationRecords
	 * @return
	 */
	boolean delete (OperationRecord operationRecord);
	
	/**
	 * 获取列表
	 * @param page 页码
	 * @param lenght 分页长度
	 * @return
	 */
	List<OperationRecord> getByPage(Page page);
	/**
	 * 搜索返回列表
	 * @param page 页码
	 * @param searchStr 搜索字串
	 * @return
	 */
	List<OperationRecord> getBySearch(Page page,String searchStr);
}
