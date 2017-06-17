package com.scms.util.search;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * @author  航
 * @version 创建时间：2017年2月22日 下午3:19:06 
 * 类说明 
 * 
 * 搜索字串判断
 */
public class SearchStrPanduan {
	/**
	 * 判断内容是否包含中文字串
	 * @param str  内容
	 * @return true 包含  false 反之
	 */
	public static boolean isContainChinese(String str) {
		if(str == null || str.equals(""))
			return false;
        try {
			Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
			Matcher m = p.matcher(str);
			if (m.find()) {
			    return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return false;
    }
}
