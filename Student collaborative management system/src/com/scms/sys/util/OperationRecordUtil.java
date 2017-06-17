package com.scms.sys.util;

import javax.servlet.http.HttpServletRequest;

import com.scms.sys.entity.OperationRecord;
import com.scms.user.entity.User;
import com.scms.util.Constant;

/** 
 * @author  航
 * @version 创建时间：2017年2月6日 下午5:25:36 
 * 类说明 
 */
public class OperationRecordUtil{
	
	public static OperationRecord createOperationRecord(String record,HttpServletRequest request){
		User user = null;
		String userStr = null;
		try{
			user = (User) request.getSession().getAttribute(Constant.MANAGER_USER_SESSSION);
			if(user == null || user.getAccount() == null)
				throw new Exception();
			
			userStr = "用户[id:" + user.getId() + "|account:" +user.getAccount() + "] "; 
			record = userStr  + record;
		}catch(Exception e){
			user = null;
		}
		OperationRecord opr = new OperationRecord(user,record);
		return opr;
	} 
}
