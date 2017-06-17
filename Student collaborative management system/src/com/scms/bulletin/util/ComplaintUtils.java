package com.scms.bulletin.util;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.scms.bulletin.entity.Complaint;

/** 
 * @author  航
 * @version 创建时间：2017年3月3日 上午11:28:07 
 * 类说明 
 */
public class ComplaintUtils {
	
	public static String filterContent(String content){
		return content.replaceAll("<", "[").replaceAll(">", "]");
	}
	
	public static Complaint filterComplaint(Complaint complaint){
		if(complaint == null)
			return complaint;
		if(!StringUtils.isBlank(complaint.getName())){
			complaint.setName(filterContent(complaint.getName()));
		}
		if(!StringUtils.isBlank(complaint.getEmail())){
			complaint.setEmail(filterContent(complaint.getEmail()));
		}	
		if(!StringUtils.isBlank(complaint.getContent())){
			complaint.setContent(filterContent(complaint.getContent()));
		}	
		if(!StringUtils.isBlank(complaint.getContact())){
			complaint.setContact(filterContent(complaint.getContact()));
		}	
		if(!StringUtils.isBlank(complaint.getTitle())){
			complaint.setTitle(filterContent(complaint.getTitle()));
		}	
		if(!StringUtils.isBlank(complaint.getProblemContent())){
			complaint.setProblemContent(filterContent(complaint.getProblemContent()));
		}	
		return complaint;
	}
	
	public static boolean isEmail(String emailStr){
		if(StringUtils.isBlank(emailStr)){
			return false;
		}
		//长度2-20  任何字符
		String reg = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		// 字符串是否与正则表达式相匹配
		boolean rs;
		try {
			// 忽略大小写的写法
			// Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
			rs = Pattern.matches(reg, emailStr); 
		} catch (Exception e) {
			return false;
		}
	    
		return rs;
	}
}
