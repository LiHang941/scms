package com.scms.bulletin.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scms.user.entity.User;

/** 
 * @author  航
 * @version 创建时间：2017年2月17日 下午3:42:12 
 * 类说明 
 * 
 * 信息实体
 */
public class Information implements Serializable {
	public Information() {
		
	}
	public Information(String title, String content, User user, Date date,
			List<InformationSendRole> roles, int state) {
		this.title = title;
		this.content = content;
		this.user = user;
		this.date = date;
		this.roles = roles;
		this.state = state;
	}


	private int id;
	
	//  标题 .
	private String title;
	//  内容 
	private String content;
	//发布用户 .
	private User user;
	//发布时间 .
	private Date date = new Date();
	//发送给哪些角色
	private List<InformationSendRole> roles ;
	//浏览消息次数 .
	private long browseInforMationCount ;
	//状态    默认为一般状态  通知状态   .
	private int state = INFORMATION_STATE_NOTICE;
	/**
	 * 紧急状态
	 */
	public static int INFORMATION_STATE_URGENT = 111;
	/**
	 * 通知状态  （一般状态）
	 */
	public static int INFORMATION_STATE_NOTICE = 222;
	/**
	 * 销毁状态 （过期状态）
	 */
	public static int INFORMATION_STATE_DESTORY = 333;
	
	/**
	 * 将状态通过醒目的字串方式获取出来
	 * @return 
	 */
	public String getStateStr (){
		String temp = getStateMap().get(this.state);
		if(temp == null){
			temp = "通知";
		}
		return temp;
	}
	
	public static Map<Integer,String> getStateMap(){
		Map<Integer,String> map = new HashMap<Integer,String>();
		map.put(INFORMATION_STATE_DESTORY, "过期");
		map.put(INFORMATION_STATE_URGENT, "紧急");
		map.put(INFORMATION_STATE_NOTICE, "通知");
		return map;
	}
	
	///////////////////////////////////////////////////////
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<InformationSendRole> getRoles() {
		return roles;
	}
	public void setRoles(List<InformationSendRole> roles) {
		this.roles = roles;
	}
	public long getBrowseInforMationCount() {
		return browseInforMationCount;
	}
	public void setBrowseInforMationCount(long browseInforMationCount) {
		this.browseInforMationCount = browseInforMationCount;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
