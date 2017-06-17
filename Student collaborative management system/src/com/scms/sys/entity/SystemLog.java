package com.scms.sys.entity;

import java.io.File;
import java.util.Date;

/** 
 * @author  航
 * @version 创建时间：2017年2月7日 下午4:37:06 
 * 类说明 
 *  该类不保存数据库
 *  就是将一个Log文件封装成对象
 */
public class SystemLog {
	
	private Date date;
	
	private File file ;
	

	public SystemLog(File file) {
		this.file = file;
		this.date = new Date(file.lastModified());
	}
	public SystemLog(){
		
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	

	
}
