package com.scms.sys.action;



import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年2月5日 下午4:18:45 
 * 类说明 
 * 系统设置模块
 * 主页类别显示
 * 访问记录
 * 操作记录(增删改)
 * 查看系统运行日志  WEB-INF/log下的文件
 */
@Namespace("/manager/system/")
@ParentPackage("default")
@InterceptorRefs(@InterceptorRef("sysModularCheckStack"))
@Scope("prototype")
@Controller
public class SystemAction extends ActionSupport {
	
	
}
