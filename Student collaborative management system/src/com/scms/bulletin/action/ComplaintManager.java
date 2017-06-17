package com.scms.bulletin.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.scms.bulletin.entity.Complaint;
import com.scms.bulletin.service.IComplaintService;
import com.scms.currency.action.base.ManagerBaseAction;
import com.scms.json.entity.JsonCurrency;
import com.scms.util.Constant;
import com.scms.util.data.GeneralQueryHelper;
import com.scms.util.write.JsonWriterUtil;

/** 
 * @author  航
 * @version 创建时间：2017年3月6日 上午11:37:01 
 * 类说明 
 */
@Namespace("/manager/bulletin/complaint")
@ParentPackage("default")
@InterceptorRefs(@InterceptorRef("bulletinModularCheckStack")) 
@Scope("prototype")
@Controller
public class ComplaintManager extends ManagerBaseAction {
	@Resource
	private IComplaintService complaintService ;
	
	private List<Complaint> complaintList ;
	
	private Complaint complaint;
	@Action(value="listUI",results={
			@Result(name="success", location="/WEB-INF/jsp/Bulletin/manager/complaint/list.jsp")
	})
	public String listUI(){
		GeneralQueryHelper<Complaint> cqh = new GeneralQueryHelper<Complaint>(Complaint.class,"complaint");
		//设置分页
		cqh.setPageProperty(page.getP(), Constant.MANAGER_LIST_LENGTH);
		if(complaint!=null){
			if(StringUtils.isNotBlank(complaint.getProblemContent())){
				cqh.addAndCondition("complaint.problemContent = ? ",complaint.getProblemContent());
			}
		}
		
		if(StringUtils.isNotBlank(searchStr)){
			cqh.addOrCondition("complaint.title like ? OR complaint.problemContent like ? ","%"+searchStr+"%" ,"%"+searchStr+"%");
		}
		
		//安装状态降序
		cqh.addOrderByProperty("complaint.state",GeneralQueryHelper.ORDER_BY_ASC);
		cqh = complaintService.getList(cqh);
		page = cqh.getPage();
		complaintList = cqh.getList();
		
		ServletActionContext.getRequest().setAttribute("COMPLAINT_STATE_MAP", Complaint.STATE_MAP);
		ServletActionContext.getRequest().setAttribute("COMPLAINT_ProblemContent_LIST", complaintService.getAllProblemContent());
		return SUCCESS;
	}
	@Action(value="showUI",results={
			@Result(name="success", location="/WEB-INF/jsp/Bulletin/manager/complaint/show.jsp")
	})
	public String showUI(){
		complaint = complaintService.findByComplaintId(complaint);
		if(complaint == null )
			return "error404";
		return SUCCESS;
	}
	@Action(value="delete") //ajax
	public void delete(){
		JsonCurrency jc = new JsonCurrency();
		try {
			complaintService.delete(complaint);
			jc.setState("success");
			jc.setMsg1("操作成功");
		} catch (Exception e) {
			jc = new JsonCurrency();
			jc.setMsg1("操作失败,请检查内容或者联系管理员.");
			JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
			throw e;
		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}
	@Action(value="handle")
	//处理 ajax
	public void handle(){
		JsonCurrency jc = new JsonCurrency();
		try {
			complaintService.handle(complaint);
			jc.setState("success");
			jc.setMsg1("操作成功");
		} catch (Exception e) {
			jc = new JsonCurrency();
			jc.setMsg1("操作失败,请检查内容或者联系管理员.");
			JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
			throw e;
		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}
	
	
	
	public List<Complaint> getComplaintList() {
		return complaintList;
	}

	public void setComplaintList(List<Complaint> complaintList) {
		this.complaintList = complaintList;
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
}
