package com.scms.currency.service.base;

import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年2月7日 下午12:46:09 
 * 类说明 
 */
public interface IPageService extends IBaseService{
	/**
	 * 分页对象
	 * @param p 当前页
	 * @param lenght 长度
	 * @return
	 */
	Page getPage(int p, int lenght);
	/**
	 * 
	 * @param p
	 * @param lenght
	 * @param searchStr
	 * @return
	 */
	Page getSearchPage(int p, int lenght,String searchStr);

}
