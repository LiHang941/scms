package com.scms.bulletin.entity;

import com.scms.user.entity.Role;

/** 
 * @author  航
 * @version 创建时间：2017年2月17日 下午4:31:36 
 * 类说明 
 * 
 * 中间表 
 * 
 */
public class InformationSendRole {
	int id;
	private Role role;
	
	public InformationSendRole() {
	}
	public InformationSendRole(Role role) {
		this.role = role;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
