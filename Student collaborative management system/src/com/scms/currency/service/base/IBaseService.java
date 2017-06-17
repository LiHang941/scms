package com.scms.currency.service.base;

import javax.servlet.http.HttpServletRequest;
/** 
 * @author  航
 * @version 创建时间：2017年2月7日 下午12:22:54 
 * 类说明 
 * 
 * 建议使用IBaseService 2
 */
@Deprecated
public interface IBaseService {
	/**
	 * 添加一个操作记录到日志中 
	 * @param record 操作内容
	 * @param request 
	 */
	void addLog(String record, HttpServletRequest request);
	

	
}
