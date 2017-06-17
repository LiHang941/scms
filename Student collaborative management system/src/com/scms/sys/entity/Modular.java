package com.scms.sys.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/** 
 * @author  航
 * @version 创建时间：2017年1月11日 下午9:39:06 
 * 类说明 
 * 
 * 模块类  (系统)
 */
public class Modular implements Serializable{
	
	private int id;
//	1.	模块名 
	private String name;
	//模块的显示图片
	private String imgUrl;
	
//	2.	模块功能   模块功能地址
	private Map<String,String> url_method = new HashMap<String,String>();
	
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
	public Map<String, String> getUrl_method() {
		return url_method;
	}
	public void setUrl_method(Map<String, String> url_method) {
		this.url_method = url_method;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	@Override
	public String toString() {
		return "Modular [id=" + id + ", name=" + name + ", imgUrl=" + imgUrl
				+ ", url_method=" + url_method + "]";
	}
	
}
