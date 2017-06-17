package com.scms.bulletin.entity;

import java.io.Serializable;

import com.scms.user.entity.User;
/** 
 * @author  航
 * @version 创建时间：2017年2月17日 下午3:57:05 
 * 类说明
 * 
 *  信息与接收数据用户的关系
 */
public class InformationAndUser implements Serializable {
	private int id;
	private Information information;
	private User user;
	private int state = USER_INFORMATION_STATE_NO;
	
	/**
	 * 状态:用户查看了该信息
	 */
	public static int USER_INFORMATION_STATE_SHOW = 111;
	/**
	 * 状态 :用户没有查看该信息
	 */
	public  static int USER_INFORMATION_STATE_NO = 123;
	
	public String getStateStr (){
		String temp = "未查看";
		if(this.state == USER_INFORMATION_STATE_SHOW)
			temp = "已读";
		return temp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Information getInformation() {
		return information;
	}

	public void setInformation(Information information) {
		this.information = information;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
}
