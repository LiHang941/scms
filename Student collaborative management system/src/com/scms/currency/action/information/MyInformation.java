package com.scms.currency.action.information;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.scms.bulletin.entity.Information;
import com.scms.bulletin.entity.InformationAndUser;
import com.scms.currency.action.base.ManagerBaseAction;
import com.scms.currency.service.IInformationAndUserService;
import com.scms.json.entity.JsonCurrency;
import com.scms.util.Constant;
import com.scms.util.write.JsonWriterUtil;

/** 
 * @author  航
 * @version 创建时间：2017年2月24日 上午10:38:24 
 * 类说明 
 */
@Namespace("/manager/currency/myInformation")
@ParentPackage("default")
@Scope("prototype")
@Controller
public class MyInformation extends ManagerBaseAction{
	private Information information;
	private List<InformationAndUser> informationAndUserList ;
	
	@Resource
	private IInformationAndUserService informationAndUserService;
	
	//用户查看信息
	//
	@Action(value="showUI",results={
			@Result(name="success", location="/WEB-INF/jsp/Currency/myInformation/show.jsp")
	})
	public String showInfoUI(){
		information = informationAndUserService.ShowInformationByUserId(getSessionUser().getId(),information);
		if(information == null){
			return "error404";
		}
		return SUCCESS;
	}
	//用户查看消息列表
	@Action(value="listUI",results={
			@Result(name="success", location="/WEB-INF/jsp/Currency/myInformation/list.jsp")
	})
	public String listUI(){
		if(searchStr == null || searchStr.equals("")){
			page = informationAndUserService.getPage(getSessionUser().getId(), page.getP(), Constant.MANAGER_LIST_LENGTH);
			informationAndUserList =  informationAndUserService.getByPage(getSessionUser().getId(), page);
		}else{
			page = informationAndUserService.getSearchPage(getSessionUser().getId(), page.getP(), Constant.MANAGER_LIST_LENGTH, searchStr);
			informationAndUserList =  informationAndUserService.getByPage(getSessionUser().getId(), page);
		}
		return SUCCESS;
	}
	@Action(value="getMyInformationCount")
	public void getMyInformationCount(){
		JsonCurrency jc = new JsonCurrency();
		try{
			int count = informationAndUserService.getMyInformationCount(getSessionUser().getId());
			jc.setMsg1(count+"");
			jc.setState("success");
		}catch(Exception e){
			jc.setState("error");
			JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
			throw e;
		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}
	
	//删除信息
	@Action(value="delete")
	public void delete(){
		JsonCurrency jc = new JsonCurrency();
		try{
			if(informationAndUserService.delete(getSessionUser().getId(),information)){
				jc.setMsg1("操作成功");
				jc.setState("success");
				informationAndUserService.addLog("[删除]用户id="+getSessionUser().getId()+"删除收件箱的一个消息:"+information.getId(), ServletActionContext.getRequest());
			}
		}catch(Exception e){
			jc.setMsg1("出现一个系统错误,操作失败");
			jc.setState("error");
			JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
			throw e;
		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}
	//

	public Information getInformation() {
		return information;
	}

	public void setInformation(Information information) {
		this.information = information;
	}

	public List<InformationAndUser> getInformationAndUserList() {
		return informationAndUserList;
	}

	public void setInformationAndUserList(
			List<InformationAndUser> informationAndUserList) {
		this.informationAndUserList = informationAndUserList;
	}
	
	
}
