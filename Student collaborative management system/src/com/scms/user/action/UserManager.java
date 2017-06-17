package com.scms.user.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.scms.currency.action.base.file.ImportExcelAction;
import com.scms.currency.entity.UserInformation;
import com.scms.json.entity.JsonCurrency;
import com.scms.user.entity.Role;
import com.scms.user.entity.User;
import com.scms.user.service.IRoleService;
import com.scms.user.service.IUserService;
import com.scms.user.service.impl.UserService;
import com.scms.user.util.UserUtil;
import com.scms.util.Constant;
import com.scms.util.write.JsonWriterUtil;
import com.scms.util.write.SetContentType;

/**
 * @author 航
 * @version 创建时间：2017年1月24日 下午8:01:53 类说明
 * 
 *          用户管理Action
 */
@Namespace("/manager/user")
@ParentPackage("default")
@InterceptorRefs(@InterceptorRef("userModularCheckStack"))
@Results(value = { @Result(name = "json", type = "dispatcher"),@Result(name = "list", location="list") })
@Scope("prototype")
@Controller
public class UserManager extends ImportExcelAction {
	@Resource
	private IUserService userService;
	@Resource
	private IRoleService roleService;
	
	private User user = new User();
	private UserInformation userInformation;
	private Role role ;
	private List<Role> roleList ;
	private List<User> userList =  new ArrayList<User>();
	private List<String> importMessages =new ArrayList<String>();

	
	//导出类型
	private int type = 1;
	@Action(value = "save")
	// 表单添加方式
	public void save() {
		JsonCurrency jc = new JsonCurrency();
		try {
			// 校验
			if(user.getId() == 0){
				if(!(UserUtil.userAccountCheck(user) ||UserUtil.userPswCheck(user)||UserUtil.userNameCheck(user) )  )
				{
					jc.setState("error");
					jc.setMsg1("账号或用户名或密码不符合要求");
					throw new RuntimeException();
				}
			}else{
				if(!UserUtil.userNameCheck(user)){
					jc.setState("error");
					jc.setMsg1("用户名不符合要求");
					throw new RuntimeException();
				}
			}
			if (userService.save(user, role,userInformation)) {
				jc.setState("success");
				jc.setMsg1("操作成功");
				userService.addLog("[保存,修改]一个用户" , ServletActionContext.getRequest());
			} else {
				jc.setState("error");
				jc.setMsg1("存在相同用户名");
			}
		} catch (RuntimeException e) {
			
		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}

	

	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/jsp/User/manager/user/add.jsp") })
	public String add() {
		roleList = roleService.getAll();
		//GG
		//Modulars = modularService.getAll();
		return SUCCESS;
	}

	@Action(value = "locking")
	// 锁定解锁
	public void isLocking() {
		JsonCurrency jc = new JsonCurrency();
		if (userService.locking(user)) {
			jc.setState("success");
			jc.setMsg1("操作成功");
			userService.addLog("[修改]用户为锁定(解锁): id = " + user.getId(), ServletActionContext.getRequest());
		} else {
			jc.setState("error");
			jc.setMsg1("操作失败");
		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}

	@Action(value = "modifyPassword")
	// 是否强制修改密码
	public void isModifyPassword() {
		JsonCurrency jc = new JsonCurrency();
		if (userService.modifyPassword(user)) {
			jc.setState("success");
			jc.setMsg1("操作成功");
			userService.addLog("[修改]用户状态是否强制修改密码: id=" + user.getId(), ServletActionContext.getRequest());
		} else {
			jc.setState("error");
			jc.setMsg1("操作失败");
		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}

	// 权限修改指定类型方式
	

	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/jsp/User/manager/user/update.jsp") })
	public String update() {
		user = userService.getById(user);
		userInformation = userService.getUserInformation(user);
		if(user == null)
			return "error404";
		roleList = roleService.getAll();
		//GG
	//	Modulars = modularService.getAll();
	//	request.put(IModularService.ModularListStr, modularList);
		return SUCCESS;
	}

	@Action(value = "updatePassword", results = { @Result(name = "success", location = "/WEB-INF/jsp/User/manager/user/updatePassword.jsp") })
	public String updatePassword() {
		user = userService.getById(user);
		if(user == null)
			return "error404";
	//	request.put(IModularService.ModularListStr, modularList);
		return SUCCESS;
	}
	
	@Action(value = "savePassword")
	public void savePassword() {
		JsonCurrency jc = new JsonCurrency();
		try {
			// 校验
			if(user.getId() == 0 || !UserUtil.userPswCheck(user)){
				jc.setState("error");
				jc.setMsg1("密码不符合格式");
				throw new RuntimeException();
			}
			
			if (userService.updatePassword(user)) {
				jc.setState("success");
				jc.setMsg1("操作成功");
				userService.addLog("[修改]指定用户重置密码:id=" + user.getId(), ServletActionContext.getRequest());

			} else {
				jc.setState("error");
				jc.setMsg1("操作失败");
			}
		} catch (Exception e) {

		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}
	@Action(value = "showUserInfo", results = { @Result(name = "success", location = "/WEB-INF/jsp/User/manager/user/showUserInfo.jsp") })
	public String showUserInfo (){
		user = userService.getById(user);
		userInformation = userService.getUserInformation(user);
		return SUCCESS;
	}
	
	@Action(value = "list", results = { @Result(name = "success", location = "/WEB-INF/jsp/User/manager/user/list.jsp") })
	public String list() {
		if (searchStr == null || searchStr.equals("")) {
			page = userService.getPage(page.getP(),
					Constant.MANAGER_LIST_LENGTH);
			userList = userService.getByPage(page);
		} else {
			// 搜索
			page = userService.getSearchPage(page.getP(), Constant.MANAGER_LIST_LENGTH, searchStr);
			userList = userService.getSearch(page, searchStr);
		}
		return SUCCESS;
	}
	
	@Override //默认页面
	public String execute() throws Exception {
		return list();
	}
	
	@Action(value = "delete")
	public void delete() {
		JsonCurrency jc = new JsonCurrency();
		try{
			if (userService.delete(user)) {
				jc.setState("success");
				jc.setMsg1("操作成功");
				userService.addLog("[删除]指定用户:id=" + user.getId(), ServletActionContext.getRequest());

			} else {
				throw new Exception();
			}
		}catch(Exception e){
			jc.setState("error");
			jc.setMsg1("操作失败");
		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}
	@Action (value = "export" )
	public void export() throws IOException{
		HttpServletResponse res = ServletActionContext.getResponse();
		SetContentType.setWriterExcelFileType(res, "用户列表.xls");
		OutputStream os = res.getOutputStream();
		userService.export(page, searchStr, type,os );
		if(os != null)
			os.close();
	}
	@Action (value = "importUser", results = { @Result(name = "input", location = "/WEB-INF/jsp/User/manager/message/ImportMsg.jsp") })
	public String importUser (){
		//1、获取excel文件
		if(excel != null){
			//是否是excel
			if(excelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){
				//2、导入
				importMessages = userService.importExcel(excel, excelFileName);
				userService.addLog("[保存]批量导入用户:FileName = " + excelFileName, ServletActionContext.getRequest());

			}else{
				importMessages.add("文件格式不正确")  ;
			}
		}
		return INPUT;
	}
	
	// ///////////////////////////////////////////////////////////////////////
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}



	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<String> getImportMessages() {
		return importMessages;
	}

	public void setImportMessages(List<String> importMessages) {
		this.importMessages = importMessages;
	}



	public List<Role> getRoleList() {
		return roleList;
	}



	public UserInformation getUserInformation() {
		return userInformation;
	}



	public void setUserInformation(UserInformation userInformation) {
		this.userInformation = userInformation;
	}



	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	
}
