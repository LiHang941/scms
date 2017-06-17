package com.scms.util.write;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

/** 
 * @author  航
 * @version 创建时间：2017年2月2日 上午11:19:07 
 * 类说明 
 */
public class JsonWriterUtil {
	/**
	 * 将JSON 字串  输出到用户
	 * @param jsonStr  JSON 字串
	 * @param res 
	 */
	public static void writerJson (String jsonStr , HttpServletResponse res){
		try{
			SetContentType.setWriterTextType(res);
			PrintWriter pw = ServletActionContext.getResponse().getWriter();
			pw.write(jsonStr);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
