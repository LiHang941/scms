package com.scms.bulletin.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.scms.bulletin.entity.Complaint;
import com.scms.bulletin.service.IComplaintService;
import com.scms.currency.action.base.ManagerBaseAction;
import com.scms.currency.exception.ServiceException;

/** 
 * @author  航
 * @version 创建时间：2017年3月3日 下午12:02:00 
 * 类说明 
 */
@Namespace("/")
@ParentPackage("default")
@Results(value={
	@Result(name="invalid.token",location="/WEB-INF/jsp/Reception/complaint/complaint.jsp")
})
@InterceptorRefs(@InterceptorRef("complaintSaveStack")) 
@Scope("prototype")
@Controller
public class ComplaintAction extends ManagerBaseAction {
	private Complaint complaint;
	private String errorMsg ;
	@Resource
	private IComplaintService complaintService;
	
	@Action(value="complaint",results={
			@Result(name="success", location="/WEB-INF/jsp/Reception/complaint/complaint.jsp")
	})
	//显示投诉页面
	public String show2(){
		return SUCCESS;
	}
	
	@Action(value="saveComplaint",results={
			@Result(name="success", location="/WEB-INF/jsp/Reception/complaint/success.jsp"),
			@Result(name="input", location="/WEB-INF/jsp/Reception/complaint/complaint.jsp")
	})
	//显示投诉页面
	public String save(){
		try {
			complaintService.saveComplaint(complaint);
		} catch (ServiceException e) {
			errorMsg = e.getMessage();
			return INPUT;
		}catch(Exception e){
			errorMsg = "出现未知错误";
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
