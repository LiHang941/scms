package com.scms.bulletin.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** 
 * @author  航
 * @version 创建时间：2017年3月1日 下午5:54:40 
 * 类说明 
 * 
 * 投诉信息实体
 */
public class Complaint implements Serializable {
	private int id;
	//联系人
	private String name;
	//邮箱
	private String email;
	//联系方式
	private String contact;
	//问题内容
	private String problemContent;
	//反馈内容
	private String content;
	//反馈标题
	private String title;
	//受理状态
	private int state;
	//时间
	private Date date;
	/**
	 * 未处理
	 */
	public static Integer state_Untreated;
	/**
	 * 处理
	 */
	public static Integer state_Handle ;
	/**
	 * 过期
	 */
	public static Integer state_expire ;
	
	public static Map<Integer,String> STATE_MAP;
	
	static{
		state_Untreated = 1;
		state_Handle= 2;
		state_expire = 3;
		STATE_MAP = new HashMap<Integer,String>();
		STATE_MAP.put(state_Untreated, "未处理");
		STATE_MAP.put(state_Handle, "已处理");
		STATE_MAP.put(state_expire, "过期");
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getProblemContent() {
		return problemContent;
	}
	public void setProblemContent(String problemContent) {
		this.problemContent = problemContent;
	}
	
}
