package com.scms.util.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * @author  航
 * @version 创建时间：2017年1月19日 下午5:18:40 
 * 类说明 
 * 
 * 通用工具包
 */
public class CurrenyUtil {
	public static final String dateFormatStr1 = "yyyy-MM-dd";
	public static final String dateFormatStr2 = "yyyy-MM-dd HH:mm:ss"; 
	public static boolean isDate(String str){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormatStr1);
		
		sdf.setLenient(false);
		try{
			sdf.parse(str);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	/**
	 * 将一个满足上述
	 * @param str
	 * @return
	 */
	public static String dateStrFormat(String str){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormatStr1);
		sdf.setLenient(false);
		try{
			Date date = sdf.parse(str);
			return sdf.format(date);
		}catch(Exception e){
			return null;
		}
	}
	/**
	 * 获取当前时间默认字串格式  yyyy-MM-dd
	 * @return
	 */
	public static String getDateStr(){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormatStr1);
		return sdf.format(new Date());
	}
	
	public static String dateFormat (Date date,int type){
		try{
			if(type == 1){
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormatStr1);
				return sdf.format(date);
			}
			
			if(type == 2){
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormatStr2);
				return sdf.format(date);
			}
		}catch(Exception e){
			
		}
		return "";
	}
}
