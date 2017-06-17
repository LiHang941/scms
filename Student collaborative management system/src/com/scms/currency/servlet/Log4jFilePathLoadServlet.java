package com.scms.currency.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

import com.scms.bulletin.entity.Bulletin;
import com.scms.user.entity.Role;

/** 
 * @author  航
 * @version 创建时间：2017年2月4日 下午3:25:39 
 * 类说明 
 * 
 * 日志加载 初始化路径地址    将日志文件保存在WEB-INF/log/   下
 */
public class Log4jFilePathLoadServlet extends HttpServlet{
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String prefix = config.getServletContext().getRealPath("/");
        String file = config.getInitParameter("log4j").replace("/", File.separator);
        String filePath = prefix + file;
        Properties props = new Properties();
        try {
        	//
            FileInputStream istream = new FileInputStream(filePath);
            //加载配置文件
            props.load(istream);
            istream.close();
            //toPrint(props.getProperty("log4j.appender.file.File"));
            String logFile = prefix + props.getProperty("log4j.appender.R.File").replace("/", File.separator);//设置路径
            props.setProperty("log4j.appender.R.File",logFile);
            PropertyConfigurator.configure(props);//装入log4j配置信息
            System.out.println("log file path :["+logFile+"]");
        } catch (IOException e) {
            System.out.println("Could not read configuration file [" + filePath + "].");
            System.out.println("Ignoring configuration file [" + filePath + "].");
            return;
        }
        //加载
        loadEntity(config);
	}
	
	private void loadEntity(ServletConfig config){
		//取得Application对象   
        ServletContext application=config.getServletContext();   
        //设置各个状态  Role
        application.setAttribute("ROLE_STATE_TRUE", Role.Role_State_true);
        application.setAttribute("ROLE_STATE_FALSE", Role.Role_State_false);
        //bulletin
        application.setAttribute("BULLETIN_STATE_FALSE", Bulletin.BULLETIN_STATE_FALSE);
        application.setAttribute("BULLETIN_STATE_TRUE", Bulletin.BULLETIN_STATE_TRUE);
		
	}
}
