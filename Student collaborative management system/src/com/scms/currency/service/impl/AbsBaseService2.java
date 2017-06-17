package com.scms.currency.service.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.scms.currency.dao.base.IBaseDao;
import com.scms.currency.dao.base.impl.BaseDao;
import com.scms.currency.service.base.IBaseService2;
import com.scms.sys.service.IOperationRecordService;
import com.scms.sys.util.OperationRecordUtil;
import com.scms.util.data.GeneralQueryHelper;

/** 
 * @author  航
 * @version 创建时间：2017年3月3日 上午10:25:09 
 * 类说明 
 */
public abstract class AbsBaseService2<T> implements IBaseService2<T>{
	@Resource
	private IOperationRecordService operationRecordService;
	private IBaseDao<T> baseDao;
	@Override
	public void addLog(String record, HttpServletRequest request) {
		operationRecordService.add(OperationRecordUtil.createOperationRecord(record, request));
	}
	@Override
	public void save(T t) {
		baseDao.save(t);
	}
	@Override
	public void delete(T t) {
		baseDao.delete(t);
		
	}
	@Override
	public void update(T t) {
		baseDao.update(t);
	}
	@Override
	public GeneralQueryHelper<T> getList(GeneralQueryHelper<T> generalQueryHelper) {
		return baseDao.getByQueryHelper(generalQueryHelper);
	}
	@Override
	public List<T> getAll(T t) {
		// TODO Auto-generated method stub
		return baseDao.getAll();
	}
	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
}
