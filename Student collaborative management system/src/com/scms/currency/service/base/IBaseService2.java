package com.scms.currency.service.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.scms.util.data.GeneralQueryHelper;

/** 
 * @author  航
 * @version 创建时间：2017年3月3日 上午10:20:37 
 * 类说明 
 */
public interface IBaseService2<T> {
	/**
	 * 添加一个操作记录到日志中 
	 * @param record 操作内容
	 * @param request 
	 */
	void addLog(String record, HttpServletRequest request);
	
	void save (T t);
	
	void delete(T t);
	
	void update(T t);
	
	GeneralQueryHelper<T> getList(GeneralQueryHelper<T> generalQueryHelper);
	
	List<T> getAll(T t);
}
