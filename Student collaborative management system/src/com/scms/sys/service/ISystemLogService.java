package com.scms.sys.service;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.scms.currency.service.base.IBaseService;
import com.scms.sys.entity.SystemLog;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年2月5日 下午4:47:28 
 * 类说明 
 */
public interface ISystemLogService extends IBaseService {
	/**
	 * 获取列表
	 * @param page 页码
	 * @param lenght 分页长度
	 * @return
	 */
	List<SystemLog> getByPage(Page page);
	
	/**
	 * 分页对象
	 * @param p 当前页
	 * @param lenght 长度
	 * @return
	 */
	Page getPage(int p, int lenght,String path);
	
	
	
	void down (String fileName,HttpServletResponse response);
}
