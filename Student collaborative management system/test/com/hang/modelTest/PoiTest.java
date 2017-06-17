package com.hang.modelTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/** 
 * @author  航
 * @version 创建时间：2017年2月2日 下午3:19:56 
 * 类说明 
 */
public class PoiTest {
	public static void main(String[] args) throws IOException {
		writer();
		read();
		/*writer2();
		read2();*/
	}

	private static void writer2() throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet= wb.createSheet();
		XSSFRow row = sheet.createRow(3);
		XSSFCell cell = row.createCell(3);
		cell.setCellValue("asdsadas ");
		wb.write(new FileOutputStream(new File("D:\\12307.xlsx")));
	}
	private static void read2() throws IOException, FileNotFoundException {
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream (new File("D:\\12307.xlsx")));
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row = sheet.getRow(3);
		XSSFCell cell = row.getCell(3);
		System.out.println(cell.getStringCellValue());
	}
	
	private static void read() throws IOException, FileNotFoundException {
		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream (new File("D:\\123.xls")));
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = sheet.getRow(3);
		HSSFCell cell = row.getCell(3);
		System.out.println(cell.getStringCellValue());
	}

	private static void writer() throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet= wb.createSheet();
		HSSFRow row = sheet.createRow(3);
		HSSFCell cell = row.createCell(3);
		cell.setCellValue("asdsadas ");
		wb.write(new File("D:\\123.xls"));
	}
}
