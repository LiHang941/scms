package com.scms.json.entity;

import com.scms.util.date.CurrenyUtil;

import net.sf.json.JSONObject;

/** 
 * @author  航
 * @version 创建时间：2017年1月26日 下午2:52:01 
 * 类说明 
 * 
 * 通用的JSON格式约定
 * 
 * 生成默认日期
 * 
 * 默认state = error
 * msg1 = 操作失败
 * url1 = index
 */
public class JsonCurrency {
	//状态
	private String state ;
	//时间
	private String date ;
	//跳转的url
	private String url1;
	private String url2;
	private String url3;
	//弹出的消息
	private String msg1 ;
	private String msg2 ;
	
	//执行的Js
	private String js1;
	private String js2;
	
	public JsonCurrency(){
		this.date = CurrenyUtil.getDateStr();
		this.state="error";
		this.msg1="操作失败";
		this.url1 = "index";
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUrl1() {
		return url1;
	}
	public void setUrl1(String url1) {
		this.url1 = url1;
	}
	public String getUrl2() {
		return url2;
	}
	public void setUrl2(String url2) {
		this.url2 = url2;
	}
	public String getUrl3() {
		return url3;
	}
	public void setUrl3(String url3) {
		this.url3 = url3;
	}
	public String getMsg1() {
		return msg1;
	}
	public void setMsg1(String msg1) {
		this.msg1 = msg1;
	}
	public String getMsg2() {
		return msg2;
	}
	public void setMsg2(String msg2) {
		this.msg2 = msg2;
	}
	public String getJs1() {
		return js1;
	}
	public void setJs1(String js1) {
		this.js1 = js1;
	}
	public String getJs2() {
		return js2;
	}
	public void setJs2(String js2) {
		this.js2 = js2;
	}
	
	public String jsonStr (){
		return JSONObject.fromObject(this).toString();
	}
}
