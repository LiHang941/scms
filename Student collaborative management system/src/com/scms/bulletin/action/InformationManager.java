package com.scms.bulletin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.scms.bulletin.entity.Information;
import com.scms.bulletin.service.IInformationService;
import com.scms.currency.action.base.ManagerBaseAction;
import com.scms.json.entity.JsonCurrency;
import com.scms.user.entity.Role;
import com.scms.util.Constant;
import com.scms.util.write.JsonWriterUtil;

/** 
 * @author  航
 * @version 创建时间：2017年2月17日 下午4:48:13 
 * 类说明 
 */
@Namespace("/manager/bulletin/information")
@ParentPackage("default")
@InterceptorRefs(@InterceptorRef("bulletinModularCheckStack")) 
@Scope("prototype")
@Controller
public class InformationManager extends ManagerBaseAction{
	@Resource
	private IInformationService informationService;
	
	private Information information ; 
	private List<Information> informationList ;
	private List<Role> roles;
	@Action(value="list",results={
			@Result(name="success", location="/WEB-INF/jsp/Bulletin/manager/information/list.jsp")
	})
	public String listUI(){
		if(searchStr == null || searchStr.equals("")){
			page = informationService.getPage(page.getP(), Constant.MANAGER_LIST_LENGTH);
			informationList = informationService.getByPage(page);
		}else{
			page = informationService.getSearchPage(page.getP(), Constant.MANAGER_LIST_LENGTH,searchStr);
			informationList = informationService.getBySearch(page,searchStr);
		}
		return SUCCESS;
	}
	@Action(value="show",results={
			@Result(name="success", location="/WEB-INF/jsp/Bulletin/manager/information/show.jsp")
	})
	public String showUI(){
		information = informationService.getInformation(information);
		if(information == null)
			return "error404";
		return SUCCESS;
	}
	
	@Action(value="add",results={
			@Result(name="success", location="/WEB-INF/jsp/Bulletin/manager/information/add.jsp")
	})
	public String addUI(){
		ServletActionContext.getRequest().setAttribute("INFORMATION_STATE_MAP", Information.getStateMap());
		roles = informationService.getRoles();
		//设置role map 用于前台显示role列表
		Map <Integer ,String> roleMap = new HashMap<Integer,String>();
		for(Role temp :roles){
			roleMap.put(temp.getId(), temp.getName());
		}
		ServletActionContext.getRequest().setAttribute("ROLE_MAP", roleMap);
		return SUCCESS;
	}
	@Action(value="update",results={
			@Result(name="success", location="/WEB-INF/jsp/Bulletin/manager/information/update.jsp")
	})
	public String updateUI (){
		//查询出消息对象
		information = informationService.getInformation(information);
		if(information == null)
			//跳转到列表
			return listUI();
		ServletActionContext.getRequest().setAttribute("INFORMATION_STATE_MAP", Information.getStateMap());
		roles = informationService.getRoles();
		return SUCCESS;
	}
	
	@Action(value="delete")
	public void delete (){
		JsonCurrency jc = new JsonCurrency();
		try{
			if(informationService.delete(information)){
				jc.setState("success");
				jc.setMsg1("操作成功");
				informationService.addLog("[删除]删除一个消息: id=" + information.getId(), ServletActionContext.getRequest());
			}
		}catch(Exception e){
			jc = new JsonCurrency();
			jc.setMsg1("出现了一个错误,请检查内容,或者联系管理员.");
			JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
			throw e;
		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}
	@Action(value="remind")
	//提醒    就是让用户重新获得该消息  
	public void remind(){
		JsonCurrency jc = new JsonCurrency();
		try{
			if(informationService.remind(information)){
				jc.setState("success");
				jc.setMsg1("操作成功");
				informationService.addLog("[修改]提醒一个消息: id=" + information.getId(), ServletActionContext.getRequest());
			}
		}catch(Exception e){
			jc = new JsonCurrency();
			jc.setMsg1("出现了一个错误,请检查内容,或者联系管理员.");
			JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
			throw e;
		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}
	@Action(value="save")
	public void save(){
		JsonCurrency jc = new JsonCurrency();
		try{
			if(information.getId() <= 0){
				if(informationService.save(information, roles, getSessionUser())){
					jc.setState("success");
					jc.setMsg1("操作成功");
					informationService.addLog("[保存]保存一个消息 ", ServletActionContext.getRequest());
				}else{
					jc.setMsg1("保存失败,请检查内容");
				}
			}else{
				if(informationService.update(information, roles)){
					jc.setState("success");
					jc.setMsg1("操作成功");
					informationService.addLog("[修改]更新一个消息 id= "+information.getId(), ServletActionContext.getRequest());
				}else{
					jc.setMsg1("更新失败,请检查内容");
				}
			}
		}catch(Exception e){
			jc = new JsonCurrency();
			jc.setMsg1("出现了一个错误,请检查内容,或者联系管理员.");
			JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
			e.printStackTrace();
			throw e;
		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}
	
	public Information getInformation() {
		return information;
	}


	public void setInformation(Information information) {
		this.information = information;
	}


	public List<Information> getInformationList() {
		return informationList;
	}


	public void setInformationList(List<Information> informationList) {
		this.informationList = informationList;
	}


	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
