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
import com.scms.sys.entity.SystemLog;
import com.scms.sys.service.ISystemLogService;
import com.scms.sys.service.impl.SystemLogService;
import com.scms.util.date.CurrenyUtil;

/** 
 * @author  航
 * @version 创建时间：2017年2月6日 下午2:42:07 
 * 类说明 
 */
@Namespace("/manager/system/systemLog")
@ParentPackage("default")
@InterceptorRefs(@InterceptorRef("sysModularCheckStack"))
@Scope("prototype")
@Controller
public class SystemLogAction extends ManagerBaseAction {
	
	private List<SystemLog> systemLogList ;
	
	private String fileName = null;
	@Resource
	private ISystemLogService systemLogService;
		//访问日志记录列表
	@Action(value = "list", results = { 
			@Result(name = "success", location = "/WEB-INF/jsp/System/manager/systemLog/list.jsp") }
	)
	public String UserAccessLogListUI (){
		String path = ServletActionContext.getRequest().getRealPath("/");
		page= systemLogService.getPage(page.getP(),SystemLogService.SystemLog_Page_Manager_Length, path);
		systemLogList = systemLogService.getByPage(page);
		return "success";
	}
	@Action(value = "down")
	public void downLog (){
		if(fileName!=null && CurrenyUtil.isDate(fileName)){
			//满足指定格式的字串
			//交给Service输出
			systemLogService.down(fileName, ServletActionContext.getResponse());
			
		}
	}
	public void setSystemLogService(ISystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}
	public List<SystemLog> getSystemLogList() {
		return systemLogList;
	}
	public void setSystemLogList(List<SystemLog> systemLogList) {
		this.systemLogList = systemLogList;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
