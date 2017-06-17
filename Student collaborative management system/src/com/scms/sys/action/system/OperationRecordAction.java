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
import com.scms.sys.entity.OperationRecord;
import com.scms.sys.service.IOperationRecordService;
import com.scms.sys.service.impl.UserAccessLogService;
import com.scms.util.write.JsonWriterUtil;

/** 
 * @author  航
 * @version 创建时间：2017年2月6日 下午5:11:57 
 * 类说明 
 */
@Namespace("/manager/system/operationRecord")
@ParentPackage("default")
@InterceptorRefs(@InterceptorRef("sysModularCheckStack"))
@Scope("prototype")
@Controller
public class OperationRecordAction extends ManagerBaseAction {
	@Resource
	private IOperationRecordService operationRecordService;
	
	private List<OperationRecord> operationRecordList = null;
	private OperationRecord operationRecord;
	
	//访问日志记录列表
		@Action(value = "list", results = { @Result(name = "success", location = "/WEB-INF/jsp/System/manager/operationRecord/list.jsp") })
		public String UserAccessLogListUI (){
			if(searchStr == null){
				page = operationRecordService.getPage(page.getP(),UserAccessLogService.UserAccessLog_Manager_List_Length);
				operationRecordList = operationRecordService.getByPage(page);
			}else{
				page = operationRecordService.getSearchPage(page.getP(),UserAccessLogService.UserAccessLog_Manager_List_Length , searchStr);
				operationRecordList = operationRecordService.getBySearch(page, searchStr);
			}
			return "success";
		}

		@Action(value = "delete")
		public void delete() {
			JsonCurrency jc = new JsonCurrency();
			try{
				if (operationRecordService.delete(operationRecord)) {
					jc.setState("success");
					jc.setMsg1("操作成功");
					operationRecordService.addLog("[删除]删除操作记录id=" + operationRecord.getId(), ServletActionContext.getRequest());
				} else {
					throw new Exception();
				}
			}catch(Exception e){
				jc.setState("error");
				jc.setMsg1("操作失败");
			}
			JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
		}
	
	
	public List<OperationRecord> getOperationRecordList() {
		return operationRecordList;
	}
	public void setOperationRecordList(List<OperationRecord> operationRecordList) {
		this.operationRecordList = operationRecordList;
	}
	public OperationRecord getOperationRecord() {
		return operationRecord;
	}
	public void setOperationRecord(OperationRecord operationRecord) {
		this.operationRecord = operationRecord;
	}
	
	public void setOperationRecordService(
			IOperationRecordService operationRecordService) {
		this.operationRecordService = operationRecordService;
	}
	
	
}
