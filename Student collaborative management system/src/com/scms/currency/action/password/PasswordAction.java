package com.scms.currency.action.password;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.scms.currency.service.IPasswordService;
import com.scms.currency.service.impl.AbsBaseService;
import com.scms.json.entity.JsonCurrency;
import com.scms.user.entity.User;
import com.scms.user.service.IUserService;
import com.scms.user.util.UserUtil;
import com.scms.util.Constant;
import com.scms.util.write.JsonWriterUtil;

/** 
 * @author  航
 * @version 创建时间：2017年2月7日 下午4:22:49 
 * 类说明 
 * 用户通用
 *   主要通过Session 中的User 进行操作   权限 ：需要用户登录
 *   
 * 个人空间
 * 
 * 用户登录日志
 */
@Namespace("/manager/currency/setPassword")
@ParentPackage("default")
@Scope("prototype")
@Controller
public class PasswordAction extends ActionSupport implements SessionAware{
	//Session中的User
	private User sessionUser ;
	//传递的参数
	private User user;
	@Resource
	private IPasswordService passwordService;
	
	private Map<String,Object> session ;
	public PasswordAction(){
		this.sessionUser = (User) ServletActionContext.getRequest().getSession().getAttribute(Constant.MANAGER_USER_SESSSION);
	}
	@Resource
	private IUserService userService;
	
	@Action(value="setPasswordUI",results={
			@Result(name="success", location="/WEB-INF/jsp/Currency/setPassWord/updatePassword.jsp")
	})
	public String setPasswordUI (){
		if(sessionUser == null)
			return "index";
		return SUCCESS;
	}
	@Action(value="savePassword")
	public void savePassword(){
		JsonCurrency jc = new JsonCurrency();
		try {
			user.setId(sessionUser.getId());
			// 校验
			if(!UserUtil.userPswCheck(user)){
				jc.setState("error");
				jc.setMsg1("密码不符合格式");
				throw new RuntimeException();
			}
			if (userService.updatePassword(user)) {
				jc.setState("success");
				jc.setMsg1("操作成功");
				jc.setUrl1("manager/main.do");
				//设置Session
				session.put(Constant.MANAGER_USER_SESSSION, userService.getLoginUserId(sessionUser));
				passwordService.addLog("[修改]用户重置密码:id=" + sessionUser.getId(), ServletActionContext.getRequest());
			} else {
				jc.setState("error");
				jc.setMsg1("操作失败");
			}
		} catch (RuntimeException e) {

		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getSessionUser() {
		return sessionUser;
	}
	public void setSessionUser(User sessionUser) {
		this.sessionUser = sessionUser;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
}
