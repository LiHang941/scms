package com.scms.bulletin.json;


import net.sf.json.JSONArray;

import com.scms.bulletin.entity.Bulletin;

/** 
 * @author  航
 * @version 创建时间：2017年1月14日 下午5:16:03 
 * 类说明 
 */
public class JsonBulletin {
	private int id;
	private String category;
	private String title;
	private String date;
	private String content;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public static JsonBulletin formBulletin(Bulletin bulletin){
		JsonBulletin jb = new JsonBulletin();
		jb.id = bulletin.getId();
		jb.category = bulletin.getCategory().getName();
		jb.date = bulletin.getDate().toString();
		jb.title = bulletin.getTitle();
		jb.content = bulletin.getContent();
		return jb;
	}
	
	public  String JSONString (){
		return JSONArray.fromObject(this).toString();
	}
}
