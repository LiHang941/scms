package com.scms.util.url;

import java.util.Stack;

/** 
 * @author  航
 * @version 创建时间：2017年1月26日 下午3:29:05 
 * 类说明 
 * 栈  先进先出
 */
public class UrlManager {
	private Stack<ScmsUrl> stack = new Stack<ScmsUrl>();
	
	private String url ;
	public UrlManager(){
		
	}
	/**
	 * 获取返回标签的url   也就是上次访问的url
	 * @return
	 */
	public String getLastUrl(){
		if(stack.empty()){
			//url栈一个都没有
			//返回一个通用的     
			//本次返回主页
			return ScmsUrl.getInstance().getUrl();
		}
		//返回该url  并移除
		return stack.pop().getUrl();
	}
	
	/**
	 * 压入栈
	 * @param url
	 */
	public void push(String url){
		if(url == null)
			return ;
		//判断最后一个url是否和当前url相同
		if(! stack.empty()){
			//获取栈顶的对象
			ScmsUrl temp = stack.peek();
			if(temp != null){
				if(temp.getUrl().equals(url)){
					return;
				}
			}else{
				//移除null
				stack.pop();
			}
		}
		//处理url  如果处理通过返回对象，处理失败返回null
		ScmsUrl temp  = ScmsUrl.getInstance(url);
		if(temp!= null){
			stack.push(temp);
		}
	}
	public String getUrl() {
		return getLastUrl();
	}
	public void setUrl(String url) {
		push(url);
	}
	
	
}
