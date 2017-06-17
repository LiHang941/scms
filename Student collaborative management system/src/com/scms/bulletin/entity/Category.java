package com.scms.bulletin.entity;
/** 
 * @author  航
 * @version 创建时间：2017年1月11日 下午9:29:55 
 * 类说明 
 * 
 * 公告类别
 */
public class Category {
//	1.	Id
	private int id;
//	2.	类别名称
	private String name;
	
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
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

}
