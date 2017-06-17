package com.scms.util.url;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.scms.sys.entity.UserAccessLog;

/** 
 * @author  航
 * @version 创建时间：2017年2月6日 下午3:44:11 
 * 类说明 
 * 
 * 获取访问者的一些信息
 */
public class GetAddress {
	/**
	 * 获取访问者IP地址
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request){
		return request.getRemoteAddr();
	}
	/**
	 * 封装用户登录记录 
	 * 不会设置用户值
	 * @param request 
	 * @return
	 */
	public static UserAccessLog encapsulationUserAccessLog(HttpServletRequest request){
		UserAccessLog ual = new UserAccessLog();
		ual.setDate(new Date());
		ual.setIp(getIp(request));
		ual.setSystemInfo(getRequestSystemInfo(request));
		return ual;
	}
	   /** 
     * 获取系统版本信息 
     * @param request 
     * @return 
     */  
    public static String getRequestSystemInfo(HttpServletRequest request){  
    	   StringBuilder userAgent = new StringBuilder("[");
           userAgent.append(request.getHeader("User-Agent"));
           userAgent.append("]");
           int indexOfMac = userAgent.indexOf("Mac OS X");
           int indexOfWindows = userAgent.indexOf("Windows NT");
           int indexOfIE = userAgent.indexOf("MSIE");
           int indexOfIE11 = userAgent.indexOf("rv:");
           int indexOfFF = userAgent.indexOf("Firefox");
           int indexOfSogou = userAgent.indexOf("MetaSr");
           int indexOfChrome = userAgent.indexOf("Chrome");
           int indexOfSafari = userAgent.indexOf("Safari");
           boolean isMac = indexOfMac > 0;
           boolean isWindows = indexOfWindows > 0;
           boolean isLinux = userAgent.indexOf("Linux") > 0;
           boolean containIE = indexOfIE > 0 || (isWindows && (indexOfIE11 > 0));
           boolean containFF = indexOfFF > 0;
           boolean containSogou = indexOfSogou > 0;
           boolean containChrome = indexOfChrome > 0;
           boolean containSafari = indexOfSafari > 0;
           String browser = "";
           if (containSogou) {
               if (containIE) {
                   browser = "搜狗" + userAgent.substring(indexOfIE, indexOfIE + "MSIE x.x".length());
               } else if (containChrome) {
                   browser = "搜狗" + userAgent.substring(indexOfChrome, indexOfChrome + "Chrome/xx".length());
               }
           } else if (containChrome) {
               browser = userAgent.substring(indexOfChrome, indexOfChrome + "Chrome/xx".length());
           } else if (containSafari) {
               int indexOfSafariVersion = userAgent.indexOf("Version");
               browser = "Safari "
                       + userAgent.substring(indexOfSafariVersion, indexOfSafariVersion + "Version/x.x.x".length());
           } else if (containFF) {
               browser = userAgent.substring(indexOfFF, indexOfFF + "Firefox/xx".length());
           } else if (containIE) {
               if (indexOfIE11 > 0) {
                   browser = "MSIE 11";
               } else {
                   browser = userAgent.substring(indexOfIE, indexOfIE + "MSIE x.x".length());
               }
           }
           String os = "";
           if (isMac) {
               os = userAgent.substring(indexOfMac, indexOfMac + "MacOS X xxxx".length());
           } else if (isLinux) {
               os = "Linux";
           } else if (isWindows) {
               os = "Windows ";
               String version = userAgent.substring(indexOfWindows + "Windows NT".length(), indexOfWindows
                       + "Windows NTx.x".length());
               if ("5.0".equals(version.trim())) {
                   os += "2000";
               } else if ("5.1".equals(version.trim())) {
                   os += "XP";
               } else if ("5.2".equals(version.trim())) {
                   os += "2003";
               } else if ("6.0".equals(version.trim())) {
                   os += "Vista";
               } else if ("6.1".equals(version.trim())) {
                   os += "7";
               } else if ("6.2".equals(version.trim())) {
                   os += "8";
               } else if ("6.3".equals(version.trim())) {
                   os += "8.1";
               }
           }
         return ("系统：" + os + ",浏览器：" + browser);
     }  
}
