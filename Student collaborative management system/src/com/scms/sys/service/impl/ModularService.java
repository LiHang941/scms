package com.scms.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.scms.sys.dao.impl.ModularDao;
import com.scms.sys.entity.Modular;
import com.scms.sys.service.IModularService;

/** 
 * @author  航
 * @version 创建时间：2017年1月26日 下午6:26:43 
 * 类说明 
 */
@Service
public class ModularService implements IModularService {
	@Resource
	private ModularDao modularDao ;
	private List<Modular> modularList = null ;
	
	@Override
	public List<Modular> getAll() {
		if(modularList != null)
			return modularList;
		
		String hql = "from Modular modular order by modular.id ASC";
		List<Modular> list= modularDao.findByCondition(hql);
		list.toString();
		modularList = list;
		return list;
	}
	
	
	public ModularDao getModularDao() {
		return modularDao;
	}
	public void setModularDao(ModularDao modularDao) {
		this.modularDao = modularDao;
	}
	
}
