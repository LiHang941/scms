package com.scms.user.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/** 
 * @author  航
 * @version 创建时间：2017年1月11日 下午9:07:27 
 * 类说明 
 * 
 * 用户类
 */

public class User {
	//id
	private int id ;
	//（账号）
	private String account ;
	//用户名
	private String name;
	//密码
	private String pwd ;
	//权限  1对多  1个用户多个权限
	private Role role;
	//是否强制修改密码 -1 不强制 1强制
	private int isModifyPassword = -1;
	//账号是否锁定 -1未锁定 1锁定  //标识后不能登录
	private int isLocking = -1;
	//账号是否删除   -1 未删除  1 删除  
	private int isDelete = User_Delete_false;
	//账号修改日期
	private Date date = new Date();
	
	/**
	 * 强制修改密码
	 */
	public static int Modify_Password_true = 1;
	/**
	 * 不强制修改密码
	 */
	public static int Modify_Password_false =  -1;
	
	/**
	 * 未锁定
	 */
	public static int Locking_false =  -1;
	/**
	 * 锁定
	 */
	public static int Locking_true =  1;
	
	/**
	 * 未删除
	 */
	public static int User_Delete_false =  -1;
	/**
	 * 删除
	 */
	public static int User_Delete_true =  1;
	
	
	/**
	 * 获取是否锁定的通用表达字串
	 * @return
	 */
	public String getLockingStr (){
		String temp = getLockingMap().get(this.isLocking);
		if(temp == null){
			temp = "锁定";
		}
		return temp;
	}
	/**
	 * 获取是否强制修改密码的通用表达字串
	 * @return
	 */
	public String getModifyPasswordStr (){
		String temp = getModifyPasswordMap().get(this.isModifyPassword);
		if(temp == null){
			temp = "否";
		}
		return temp;
	}
	
	public static Map<Integer,String> getLockingMap(){
		Map<Integer,String> map = new HashMap<Integer,String>();
		map.put(Locking_false, "未锁定");
		map.put(Locking_true, "锁定");
		return map;
	}
	
	public static Map<Integer,String> getModifyPasswordMap(){
		Map<Integer,String> map = new HashMap<Integer,String>();
		map.put(Modify_Password_true, "是");
		map.put(Modify_Password_false, "否");
		return map;
	}
	
	//////////////////////////////////////////////////////////////////////////
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public int getIsModifyPassword() {
		return isModifyPassword;
	}
	public void setIsModifyPassword(int isModifyPassword) {
		this.isModifyPassword = isModifyPassword;
	}
	public int getIsLocking() {
		return isLocking;
	}
	public void setIsLocking(int isLocking) {
		this.isLocking = isLocking;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", name=" + name
				+ ", pwd=" + pwd + ", role=" + role + ", isModifyPassword="
				+ isModifyPassword + ", isLocking=" + isLocking + ", isDelete="
				+ isDelete + ", date=" + date + "]";
	}
	
}
