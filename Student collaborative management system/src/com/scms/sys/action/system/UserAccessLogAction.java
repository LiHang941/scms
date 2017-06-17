package com.scms.sys.action.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.scms.currency.action.base.ManagerBaseAction;
import com.scms.json.entity.JsonCurrency;
import com.scms.sys.entity.UserAccessLog;
import com.scms.sys.service.IUserAccessLogService;
import com.scms.sys.service.impl.UserAccessLogService;
import com.scms.util.write.JsonWriterUtil;

/** 
 * @author  航
 * @version 创建时间：2017年2月6日 下午2:42:07 
 * 类说明 
 */
@Namespace("/manager/system/userAccessLog")
@ParentPackage("default")
@InterceptorRefs(@InterceptorRef("sysModularCheckStack"))
@Scope("prototype")
@Controller
public class UserAccessLogAction extends ManagerBaseAction {
	@Resource
	private IUserAccessLogService userAccessLogService ;
	private List<UserAccessLog> userAccessLogList = null;
	private UserAccessLog userAccessLog;
	//访问日志记录列表
	@Action(value = "list", results = { @Result(name = "success", location = "/WEB-INF/jsp/System/manager/userAccessLog/list.jsp") })
	public String UserAccessLogListUI (){
		if(getSearchStr() == null){
			page = userAccessLogService.getPage(page.getP(),UserAccessLogService.UserAccessLog_Manager_List_Length);
			userAccessLogList = userAccessLogService.getByPage(page);
		}else{
			page = userAccessLogService.getSearchPage(page.getP(),UserAccessLogService.UserAccessLog_Manager_List_Length , searchStr);
			userAccessLogList = userAccessLogService.getBySearch(page, searchStr);
		}
		return "success";
	}

	@Action(value = "delete")
	public void delete() {
		JsonCurrency jc = new JsonCurrency();
		try{
			if (userAccessLogService.delete(userAccessLog)) {
				jc.setState("success");
				jc.setMsg1("操作成功");
				userAccessLogService.addLog("[删除]用户登录记录" , ServletActionContext.getRequest());
			} else {
				throw new Exception();
			}
		}catch(Exception e){
			jc.setState("error");
			jc.setMsg1("操作失败");
		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}
	
	public UserAccessLog getUserAccessLog() {
		return userAccessLog;
	}
	public void setUserAccessLog(UserAccessLog userAccessLog) {
		this.userAccessLog = userAccessLog;
	}
	public List<UserAccessLog> getUserAccessLogList() {
		return userAccessLogList;
	}
	public void setUserAccessLogList(List<UserAccessLog> userAccessLogList) {
		this.userAccessLogList = userAccessLogList;
	}
	public void setUserAccessLogService(IUserAccessLogService userAccessLogService) {
		this.userAccessLogService = userAccessLogService;
	}
}
