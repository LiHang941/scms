package com.scms.sys.service;
import java.util.List;

import com.scms.currency.service.base.IPageService;
import com.scms.sys.entity.UserAccessLog;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年2月5日 下午4:47:28 
 * 类说明 
 */
public interface IUserAccessLogService extends IPageService {
	
	
	/**
	 * 添加日志到
	 * @param uals
	 * @return
	 */
	void add(UserAccessLog ual);
	/**
	 * 删除一个日志
	 * @param uals
	 * @return
	 */
	boolean delete (UserAccessLog ual);
	
	/**
	 * 获取列表
	 * @param page 页码
	 * @param lenght 分页长度
	 * @return
	 */
	List<UserAccessLog> getByPage(Page page);
	/**
	 * 搜索返回列表
	 * @param page 页码
	 * @param searchStr 搜索字串
	 * @return
	 */
	List<UserAccessLog> getBySearch(Page page,String searchStr);
	
}
