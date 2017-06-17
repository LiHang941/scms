package com.scms.currency.exception;
/** 
 * @author  航
 * @version 创建时间：2017年3月3日 上午11:19:48 
 * 类说明 
 */
public class ServiceException extends SysException {

	public ServiceException() {
		super("业务操作错误！");
	}

	public ServiceException(String message) {
		super(message);
	}

}
