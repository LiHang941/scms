package com.scms.currency.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.scms.currency.service.IPasswordService;

/** 
 * @author  航
 * @version 创建时间：2017年3月1日 下午12:58:24 
 * 类说明 
 */
@Service
public class PasswordService extends AbsBaseService implements IPasswordService {
	@Override
	public void addLog(String record, HttpServletRequest request) {
		record = "[通用模块-密码修改]"+record;
		super.addLog(record, request);
	}
}
