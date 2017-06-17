package com.scms.sys.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.scms.currency.service.impl.AbsBaseService;
import com.scms.sys.entity.SystemLog;
import com.scms.sys.service.ISystemLogService;
import com.scms.util.Page;
import com.scms.util.PageUtil;
import com.scms.util.date.CurrenyUtil;
import com.scms.util.write.SetContentType;

/** 
 * @author  航
 * @version 创建时间：2017年2月7日 下午4:49:06 
 * 类说明 
 * 
 * 先分页在获取集合  否则 数据没有初始化 获取不到
 */
@Service
public class SystemLogService extends AbsBaseService implements ISystemLogService {
	
	/**
	 * 界面的现在的文件数目
	 */
	public static final int SystemLog_Page_Manager_Length = 9;
	//Log4j配置文件路径
	private String log4jPath = "WEB-INF" +File.separatorChar+ "classes"+File.separatorChar+"log4j.properties";
	//系统Log文件路径
	private String logFilePath = null;
	
	private String logFileName = null;
	@Override
	public List<SystemLog> getByPage(Page page) {
		if(logFilePath != null && logFileName!=null){
			File filePath = new File(logFilePath);
			//获取该目录满足条件的文件
			File [] fileList = filePath.listFiles(new FilenameFilter(){
				@Override
				public boolean accept(File dir, String name) {
					File file = new File(dir.getPath() +File.separatorChar+ name);
					//是文件夹？
					if(file.isDirectory())
						return false;
					//文件名包含 
					return name.contains(logFileName);
				}
			});
			//先安装时间降序
			Arrays.sort(fileList,new  Comparator<File>(){
				@Override
				public int compare(File o1, File o2) {
					return (int) (o2.lastModified() - o1.lastModified());
				}
			});
			List<SystemLog> list = new ArrayList<SystemLog>();
			
			for(int i=page.getBeginIndex() ; i<page.getEveryPage(); i++){
				if(i<fileList.length){
					SystemLog e = new SystemLog(fileList[i]);
					list.add(e);
				}
			}
			return list;
		}else{
			return new ArrayList<SystemLog>();
		}
	}

	@Override   //要抛异常
	public Page getPage(int p, int lenght,String path) {
		if(logFilePath == null || logFileName==null){
			Properties pr = new Properties();
			try{
				//获取配置文件流
	            FileInputStream istream = new FileInputStream(path + log4jPath);
	            //加载配置文件
	            pr.load(istream);
	            istream.close();
	            File temp = new File(path + pr.getProperty("log4j.appender.R.File"));//设置路径
	            
	            //获取配置文件保存的路径
	            logFilePath = temp.getParent() + File.separatorChar;
	            logFileName = temp.getName();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		Page page = PageUtil.createPage(lenght, getLogCount(logFilePath,logFileName), p);
		return page;
	}
	
	@Override
	public void down(String fileName, HttpServletResponse response) {
		
		if(logFilePath != null && logFileName!=null){
			try{
				File file = new File(logFilePath + logFileName + "." + fileName);
				//是否存在  是否是目录
				if( !file.exists() || file.isDirectory()){
					//文件不存在或者是个目录
					if(fileName.equals(CurrenyUtil.getDateStr())){
						//判断是否是今天的日志
						file = new File(logFilePath + logFileName);
						if(!file.exists() || file.isDirectory()){
							return;
						}
					}else{
						return;
					}
				}
				FileInputStream fis = new FileInputStream(file);
				HttpServletResponse res = ServletActionContext.getResponse();
				SetContentType.setWriterTxtFileType(res, "日志记录.txt");
				OutputStream os = res.getOutputStream();
				int len = 0 ;
				byte [] buf = new byte[4096];
				while((len = fis.read(buf))!=-1){
					os.write(buf, 0, len);
				}
				fis.close();
				os.close();
			}catch(Exception e ){
				e.printStackTrace();
			}
		}
	}
	/**
	 * 获取目录下的Log文件个数
	 * @param logPath
	 * @param logName
	 * @return
	 */
	private int getLogCount(String logPath,final String logName){
		//
		File filePath = new File(logPath);
		//获取该目录满足条件的文件
		File [] fileList = filePath.listFiles(new FilenameFilter(){
			@Override
			public boolean accept(File dir, String name) {
				File file = new File(dir.getPath() +File.separatorChar+ name);
				if(file.isDirectory())
					return false;
				//文件名包含 
				return name.contains(logName);
			}
		});
		return fileList.length;
	}
	
	
	
}
