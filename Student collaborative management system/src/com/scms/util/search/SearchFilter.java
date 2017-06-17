package com.scms.util.search;
/** 
 * @author  航
 * @version 创建时间：2017年1月29日 下午3:11:33 
 * 类说明 
 * 过滤常见的注入
 */
public class SearchFilter {
	/**
	 * 过滤 如下  ( ).(').(#).(*).(/)
	 * @param str
	 * @return
	 */
	public static String searchFilter1(String str){
		if(str == null)
			return str;
		return str.replace(" ", "").replace("'", "").replace("#", "").replace("*", "").replace("/", "");
	}
	
	/**
	 * 过滤 如下  ( )
	 * @param str
	 * @return
	 */
	public static String sFilter(String str){
		if(str == null)
			return str;
		return str.replace(" ", "");
	}
}
