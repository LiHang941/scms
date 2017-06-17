package com.scms.currency.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.scms.currency.service.IMyHomeService;

/** 
 * @author  航
 * @version 创建时间：2017年3月1日 下午12:51:20 
 * 类说明 
 */
@Service
public class MyHomeService extends AbsBaseService implements IMyHomeService{
	@Override
	public void addLog(String record, HttpServletRequest request) {
		record = "[通用模块-个人信息]"+record;
		super.addLog(record, request);
	}
}
