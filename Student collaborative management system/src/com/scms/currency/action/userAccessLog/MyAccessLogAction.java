package com.scms.currency.action.userAccessLog;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.scms.currency.action.base.ManagerBaseAction;
import com.scms.currency.service.IMyAccessLogService;
import com.scms.currency.service.impl.MyAccessLogService;
import com.scms.json.entity.JsonCurrency;
import com.scms.sys.entity.UserAccessLog;
import com.scms.util.date.CurrenyUtil;
import com.scms.util.write.JsonWriterUtil;

/** 
 * @author  航
 * @version 创建时间：2017年2月13日 上午10:37:12 
 * 类说明 
 * 
 * 用户访问记录控制类 
 * 
 */
@Namespace("/manager/currency/myAccessLog")
@ParentPackage("default")
@Scope("prototype")
@Controller
public class MyAccessLogAction extends ManagerBaseAction{
	

	//用户查看自己的登录情况
	private List <UserAccessLog> userAccessLogList ;
	@Resource
	private IMyAccessLogService myAccessService;
	
	@Action(value="myAccessListUI",results={
			@Result(name="success", location="/WEB-INF/jsp/Currency/myAccessLog/list.jsp")
	})
	public String getListUI (){
		page = myAccessService.getPage(page.getP(),MyAccessLogService.MyAccessLog_Manager_List_Length , getSessionUser());
		userAccessLogList = myAccessService.getByUser(getSessionUser(), page); 
		return SUCCESS;
	}
	@Action(value="userAccessLately")
	public void getLately (){
		JsonCurrency jc = new JsonCurrency();
		jc.setDate(CurrenyUtil.dateFormat(myAccessService.getByLately(getSessionUser()).getDate(),2));
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}
	
	
	public List<UserAccessLog> getUserAccessLogList() {
		return userAccessLogList;
	}
	public void setUserAccessLogList(List<UserAccessLog> userAccessLogList) {
		this.userAccessLogList = userAccessLogList;
	}
	public void setMyAccessService(IMyAccessLogService myAccessService) {
		this.myAccessService = myAccessService;
	}
}
