package com.scms.user.action.role;

import java.util.ArrayList;
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
import com.scms.sys.entity.Modular;
import com.scms.sys.service.IModularService;
import com.scms.user.entity.Role;
import com.scms.user.service.IRoleService;
import com.scms.user.service.impl.RoleService;
import com.scms.util.write.JsonWriterUtil;

/** 
 * @author  航
 * @version 创建时间：2017年2月11日 下午1:33:59 
 * 类说明 
 * 
 * 
 */
@Namespace("/manager/user/role")
@ParentPackage("default")
@InterceptorRefs(@InterceptorRef("userModularCheckStack"))
@Scope("prototype")
@Controller
public class RoleManager extends ManagerBaseAction{
	private Role role ;
	private List<Role> roleList;
	private List<Modular> Modulars = new ArrayList<Modular>();
	@Resource
	private IRoleService roleService;
	@Resource
	private IModularService modularService;
	
	@Action(value = "list", results = { 
			@Result(name = "list", location = "/WEB-INF/jsp/User/manager/role/list.jsp") })
	public String roleListUI(){
		page = roleService.getPage(page.getP(), RoleService.Role_Manager_List_Length);
		roleList = roleService.getByPage(page);
		return "list";
	}
	//修改角色的可用状态
	@Action(value="state")
	public void updateState (){
		JsonCurrency jc = new JsonCurrency();
		try {
			if(roleService.updateState(role)){
				jc.setState("success");
				jc.setMsg1("操作成功");
				roleService.addLog("[修改]修改角色可用状态id="+role.getId(), ServletActionContext.getRequest());
			}
		} catch (Exception e) {
			jc.setState("error");
			jc.setMsg1("出现系统错误,操作失败");
			JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
			throw e;
		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/WEB-INF/jsp/User/manager/role/add.jsp") })
	public String add(){
		Modulars = modularService.getAll();
		ServletActionContext.getRequest().setAttribute("ROLE_STATE_MAP", Role.getStateMap());
		return "success";
	}

	@Action(value = "update", results = { 
			@Result(name = "success", location = "/WEB-INF/jsp/User/manager/role/update.jsp") })
	public String update(){
		role = roleService.findById(role);
		if(role == null)
			//抛出一个不存在的异常  暂时没有配置
			return "success";
		Modulars = modularService.getAll();
		ServletActionContext.getRequest().setAttribute("ROLE_STATE_MAP", Role.getStateMap());
		return "success";
	}
	//添加修改
	@Action(value = "save")
	public void save(){
		JsonCurrency jc = new JsonCurrency();
		
		try {
			if(roleService.save(role, Modulars)){
				jc.setState("success");
				jc.setMsg1("操作成功");
				roleService.addLog("[修改OR保存]角色", ServletActionContext.getRequest());
			}else{
				jc.setMsg1("请检查内容");
			}
		} catch (Exception e) {
			jc.setState("error");
			jc.setMsg1("出现系统错误,操作失败");
			JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
			throw e;
		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public List<Modular> getModulars() {
		return Modulars;
	}
	public void setModulars(List<Modular> modulars) {
		Modulars = modulars;
	}
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	public void setModularService(IModularService modularService) {
		this.modularService = modularService;
	}
	
}
