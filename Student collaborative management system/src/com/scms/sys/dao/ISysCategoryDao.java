package com.scms.sys.dao;

import com.scms.currency.dao.base.IBaseDao;
import com.scms.sys.entity.SysCategory;

/** 
 * @author  航
 * @version 创建时间：2017年1月26日 下午8:25:08 
 * 类说明 
 */
public interface ISysCategoryDao extends IBaseDao<SysCategory> {
	SysCategory getSysCategory();
}
