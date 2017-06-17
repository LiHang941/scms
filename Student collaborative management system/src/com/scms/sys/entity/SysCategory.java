package com.scms.sys.entity;

import com.scms.bulletin.entity.Category;

/** 
 * @author  航
 * @version 创建时间：2017年1月26日 下午5:14:14 
 * 类说明 
 * 
 * 主页默认显示的Category
 */
public class SysCategory {
	private int id;
	private Category category1;
	private Category category2;
	private Category category3;
	//1 表示当前使用的是       -1 表示未使用
	private int State = SysCategory_State_true;
	
	public static int SysCategory_State_true = 1;
	public static int SysCategory_State_false = -1;
	
	public Category getCategory1() {
		return category1;
	}
	public void setCategory1(Category category1) {
		this.category1 = category1;
	}
	public Category getCategory2() {
		return category2;
	}
	public void setCategory2(Category category2) {
		this.category2 = category2;
	}
	public Category getCategory3() {
		return category3;
	}
	public void setCategory3(Category category3) {
		this.category3 = category3;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getState() {
		return State;
	}
	public void setState(int state) {
		State = state;
	}
	@Override
	public String toString() {
		return "SysCategory [id=" + id + ", category1=" + category1
				+ ", category2=" + category2 + ", category3=" + category3
				+ ", State=" + State + "]";
	}
	
}
