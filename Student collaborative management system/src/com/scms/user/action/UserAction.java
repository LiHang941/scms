package com.scms.user.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.scms.sys.entity.UserAccessLog;
import com.scms.sys.service.IUserAccessLogService;
import com.scms.user.entity.User;
import com.scms.user.service.IUserService;
import com.scms.user.util.UserUtil;
import com.scms.util.Constant;
import com.scms.util.url.GetAddress;

/**
 * @author 航
 * @version 创建时间：2017年1月13日 下午12:30:19 类说明
 */

@Namespace("/")
@ParentPackage("default")
@Scope("prototype")
@Controller
public class UserAction extends ActionSupport  {
	@Resource
	private IUserService userService;
	@Resource
	private IUserAccessLogService userAccessLogService;
	
	// 用户
	private User user;
	@Action(value="loginUI",results={
			@Result(name="success", location="/WEB-INF/jsp/Reception/login.jsp")
	})
	public String loginUI() {
		return "success";
	}
	@Action(value="login",results={
		@Result(name="login", location="/WEB-INF/jsp/User/check.jsp")
	})
	public String login() {
		String text = "success";
		if(UserUtil.userAccountCheck(user) && UserUtil.userPswCheck(user)){
			userService.jiami(user);  //加密
			User uu = userService.islogin(user);
			if (uu == null || !uu.getPwd().equals(user.getPwd())) {
				text = "用户名或密码错误";
			} else {
				User temp = userService.getLoginUserId(uu) ;
				//用户为锁定状态
				if(temp.getIsLocking() == User.Locking_true){
					text= "该用户已经锁定，请联系管理员";
				}else{
					//
					ServletActionContext.getRequest().getSession().setAttribute(Constant.MANAGER_USER_SESSSION, temp);
					UserAccessLog ual = GetAddress.encapsulationUserAccessLog(ServletActionContext.getRequest());
					ual.setUser(temp);
					userAccessLogService.add(ual);
				}
			}
		}else{
			text= "请输入正确的用户名和密码";
		}
		
		ServletActionContext.getRequest().setAttribute(Constant.QIANTAI_LOGIN_MSG, text);

		return "login";
	}
	//用户退出
	@Action(value="leave",results={
			 @Result(name = "leave",type="redirectAction", location = "index") 
		})
	public String leave (){
		ServletActionContext.getRequest().getSession().removeAttribute(Constant.MANAGER_USER_SESSSION);
		return "leave";
	}
	//////////////////////////////////////////////////////////////
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setUserAccessLogService(IUserAccessLogService userAccessLogService) {
		this.userAccessLogService = userAccessLogService;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
