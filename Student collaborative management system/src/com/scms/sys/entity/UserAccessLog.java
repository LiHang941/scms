package com.scms.sys.entity;

import java.io.Serializable;
import java.util.Date;

import com.scms.user.entity.User;

/** 
 * @author  航
 * @version 创建时间：2017年2月5日 下午4:26:42 
 * 类说明 
 * 用户访问记录实体
 */
public class UserAccessLog implements Serializable {
	private int id;
	//访问时间
	private Date date ;
	//访问ip
	private String ip;
	//访问的系统(设备)信息
	private String systemInfo;
	
	private User user;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getSystemInfo() {
		return systemInfo;
	}
	public void setSystemInfo(String systemInfo) {
		this.systemInfo = systemInfo;
	}
	
	
}
