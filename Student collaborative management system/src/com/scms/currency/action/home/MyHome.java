package com.scms.currency.action.home;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.scms.currency.action.base.ManagerBaseAction;
import com.scms.currency.entity.UserInformation;
import com.scms.currency.service.IMyHomeService;
import com.scms.currency.service.IUserInformationService;
import com.scms.json.entity.JsonCurrency;
import com.scms.user.entity.User;
import com.scms.user.service.IUserService;
import com.scms.user.util.UserUtil;
import com.scms.util.Constant;
import com.scms.util.write.JsonWriterUtil;

/** 
 * @author  航
 * @version 创建时间：2017年2月16日 下午4:00:06 
 * 类说明 
 */
@Namespace("/manager/currency/myHome")
@ParentPackage("default")
@Scope("prototype")
@Controller
public class MyHome extends ManagerBaseAction{
	private User myUser ;
	private UserInformation userInformation;
	@Resource
	private IUserService userService;
	@Resource
	private IUserInformationService userInformationService;
	@Resource
	private IMyHomeService myHomeService;
	
	
	
	@Action(value="indexUI",results={
			@Result(name="success", location="/WEB-INF/jsp/Currency/myHome/index.jsp")
	})
	//个人空间  （个人资料）
	public String index (){
		myUser = getSessionUser();
		userInformation = userService.getUserInformation(getSessionUser());
		return SUCCESS;
	}
	@Action(value="update",results={
			@Result(name="success", location="/WEB-INF/jsp/Currency/myHome/update.jsp")
	})
	public String update(){
		myUser = getSessionUser();
		userInformation = userService.getUserInformation(getSessionUser());
		return SUCCESS;
	}
	@Action(value="save")
	public void save(){
		JsonCurrency jc = new JsonCurrency();
			try {
				if(!UserUtil.userNameCheck(myUser)){
					jc.setMsg1("用户名不符合要求");
					throw new RuntimeException();
				}
				User temp = new User();
				temp.setId(getSessionUser().getId());
				temp.setName(myUser.getName());
				if(userInformationService.update(temp, userInformation)){
					jc.setState("success");
					jc.setMsg1("操作成功");
					ServletActionContext.getRequest().getSession().setAttribute(Constant.MANAGER_USER_SESSSION, userService.getLoginUserId(getSessionUser()));
					myHomeService.addLog("[修改]用户修改资料,id="+getSessionUser().getId(), ServletActionContext.getRequest());
				}else{
					jc.setMsg1("用户不存在");
				}
			} catch (Exception e) {
				jc.setState("error");
				jc.setMsg1("出现错误,提交失败!");
				JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
				throw e;
			}
		
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}
	
	public User getMyUser() {
		return myUser;
	}
	public void setMyUser(User myUser) {
		this.myUser = myUser;
	}
	public UserInformation getUserInformation() {
		return userInformation;
	}
	public void setUserInformation(UserInformation userInformation) {
		this.userInformation = userInformation;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public void setUserInformationService(
			IUserInformationService userInformationService) {
		this.userInformationService = userInformationService;
	}
}
