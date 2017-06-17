package com.scms.currency.action.base.file;

import java.io.File;

import com.scms.currency.action.base.ManagerBaseAction;

/** 
 * @author  航
 * @version 创建时间：2017年2月7日 上午11:55:30 
 * 类说明 
 */
public abstract class ImportExcelAction extends ManagerBaseAction{
	protected File excel;
	protected String excelContentType;
	protected String excelFileName;
	public File getExcel() {
		return excel;
	}
	public void setExcel(File excel) {
		this.excel = excel;
	}
	public String getExcelContentType() {
		return excelContentType;
	}
	public void setExcelContentType(String excelContentType) {
		this.excelContentType = excelContentType;
	}
	public String getExcelFileName() {
		return excelFileName;
	}
	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}
	
	
}
