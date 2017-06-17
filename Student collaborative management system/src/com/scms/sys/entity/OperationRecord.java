package com.scms.sys.entity;

import java.io.Serializable;
import java.util.Date;

import com.scms.user.entity.User;

/** 
 * @author  航
 * @version 创建时间：2017年2月6日 下午4:31:52 
 * 类说明 
 * 
 * 操作记录实体(用户对数据的增加   修改   删除 的记录)
 */
public class OperationRecord implements Serializable{
	private int id;
	private User user ;
	//操作内容内容
	private String record;
	
	private Date date;

	public OperationRecord() {
		this.date = new Date();
	}
	
	public OperationRecord(User user , String record){
		this();
		this.user = user;
		this.record = record;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
