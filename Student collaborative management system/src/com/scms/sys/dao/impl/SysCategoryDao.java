package com.scms.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.scms.currency.dao.base.impl.BaseDao;
import com.scms.sys.dao.ISysCategoryDao;
import com.scms.sys.entity.SysCategory;

/**
 * @author 航
 * @version 创建时间：2017年1月11日 下午8:26:09 类说明
 */
@Repository
public class SysCategoryDao extends BaseDao<SysCategory> implements ISysCategoryDao {
	
	@Override
	public SysCategory getSysCategory() {
		String hql ="from SysCategory sc where sc.State= 1 ORDER BY sc.id DESC";
		List<SysCategory> sysCategories = super.findByCondition(hql);
		if(sysCategories.size()>0){
			SysCategory ss = sysCategories.get(0);
			ss.toString();
			return ss;
		}
		return null;
	}
}
