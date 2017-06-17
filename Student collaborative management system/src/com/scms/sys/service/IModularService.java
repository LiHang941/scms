package com.scms.sys.service;

import java.util.List;

import com.scms.sys.entity.Modular;

/** 
 * @author  航
 * @version 创建时间：2017年1月13日 下午6:09:08 
 * 类说明 
 * 
 * 模块
 */
public interface IModularService {
	
	String ModularListStr = "Modular.List.Request";
	
	List<Modular> getAll();
}
