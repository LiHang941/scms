package com.scms.currency.entity;

import com.scms.user.entity.User;

/** 
 * @author  航
 * @version 创建时间：2017年1月11日 下午9:13:28 
 * 类说明 
 * 
 * 用户个人信息类
 */
public class UserInformation {
//	1.	Id 
	private int id;
//  头像	   暂时不使用
	private String headImages;
//	2.	电话
	private String tel;
//	3.	QQ
	private String qq;
//	4.	邮箱
	private String email;
//	5.	说明
	private String record;
//  6.  用户	
	private User user;
	
	public UserInformation() {
		
	}
	//
	public UserInformation(boolean isNull) {
		if(isNull){
			this.email = "未设置";
			this.tel = "未设置";
			this.qq = "未设置";
			this.record = "未设置";
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public String getHeadImages() {
		return headImages;
	}
	public void setHeadImages(String headImages) {
		this.headImages = headImages;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
