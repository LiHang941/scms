package com.scms.user.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.scms.currency.dao.IUserInformationDao;
import com.scms.currency.entity.UserInformation;
import com.scms.currency.service.impl.AbsBaseService;
import com.scms.user.dao.IUserDao;
import com.scms.user.entity.Role;
import com.scms.user.entity.User;
import com.scms.user.service.IRoleService;
import com.scms.user.service.IUserService;
import com.scms.user.util.DesUtils;
import com.scms.user.util.LazyDataLoadUtils;
import com.scms.user.util.UserUtil;
import com.scms.util.Constant;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年1月13日 下午12:18:10 
 * 类说明 
 * 
 * 用户业务逻辑层
 */
@Service
public class UserService extends AbsBaseService implements IUserService {
	@Resource
	private IUserDao userDao;
	@Resource
	private IRoleService roleService;
	@Resource
	private IUserInformationDao userInformationDao;
	
	@Override
	public void addLog(String record, HttpServletRequest request) {
		record = "[用户模块]" + record;
		super.addLog(record, request);
	}
	
	@Override
	public boolean save(User user,Role role,UserInformation userInformation) {
		role = roleService.findById(role);
		if(role == null)
			return false;
		if(userInformation == null){
			userInformation = new UserInformation(true);
		}
		user.setRole(role);
		if(user.getId() == 0){
			return add(user,userInformation);
		}else{
			return update(user,userInformation);
		}
	}
	

	@Override
	public boolean delete(User user) {
		User u =  userDao.findById(user.getId());
		if(u == null)
			return false;
		u.setIsDelete(User.User_Delete_true);
		userDao.update(u);
		return true;
	}
	private boolean add(User user,UserInformation userInformation){
		//判断用户的用户名是否使用
		if(userDao.findByUserAccount(user.getAccount()) != null)
			return false;
		User temp  =  new User();
		temp.setAccount(user.getAccount());
		temp.setName(user.getName());
		temp.setPwd(user.getPwd());
		temp.setIsModifyPassword(user.getIsModifyPassword());
		temp.setRole(user.getRole());
		userDao.save(temp);
		userInformation.setUser(temp);
		userInformationDao.save(userInformation);
		return true;
	}

	private  boolean update(User user,UserInformation userInformation) {
		/// 更新  一个用户     不能修改密码   
		///修改用户名  权限  ...
		User temp = userDao.findById(user.getId());
		if(temp == null || temp.getIsDelete() == User.User_Delete_true)
			return false;
		temp.setName(user.getName());
		temp.setRole(user.getRole());
		userDao.update(temp);
		userInformationDao.deleteByUser(temp.getId());
		userInformation.setUser(temp);
		userInformationDao.save(userInformation);
		return true;
	}
	
	@Override
	public boolean updatePassword(User user) {
		User temp = userDao.findById(user.getId());
		if(temp == null || temp.getIsDelete() == User.User_Delete_true)
			return  false;
		jiami(user);
		temp.setPwd(user.getPwd());
		//设置为否
		temp.setIsModifyPassword(User.Modify_Password_false);
		userDao.update(temp);
		return true;
	}
	@Override
	public boolean locking(User user) {
		User u =  userDao.findById(user.getId());
		if(u == null || u.getIsDelete() == User.User_Delete_true)
			return false;
		u.setIsLocking(u.getIsLocking() * -1);
		userDao.update(u);
		return true;
	}

	@Override
	public boolean modifyPassword(User user) {
		User u =  userDao.findById(user.getId());
		if(u == null || u.getIsDelete() == User.User_Delete_true)
			return false;
		u.setIsModifyPassword(u.getIsModifyPassword() * -1);
		userDao.update(u);
		return true;
	}
	@Override
	public User getLoginUserId(User user) {
		User uu =  userDao.findById(user.getId());
		uu.toString();
		return uu;
	}
	@Override
	public User islogin(User user) {
		return userDao.findByUserAccount(user.getAccount());
	}
	@Override
	public UserInformation getUserInformation(User user) {
		if(user == null){
			return null;
		}
		UserInformation uif = userInformationDao.getByUser(user.getId());
		if(uif == null)
			uif = new UserInformation(true);
		return uif;
	}

	@Override
	public List<User> getByPage(Page page) {
		List<User> users = userDao.getByPage(page);
		for(User temp :users){
			LazyDataLoadUtils.loadUserAndRole(temp);
		}
		return users;
	}

	@Override
	public List<User> getSearch(Page page, String SearchStr) {
		List<User> list = userDao.getByPage(page,SearchStr);
		for(User temp :list){
			LazyDataLoadUtils.loadUserAndRole(temp);
		}
		return list;
	}

	@Override
	public Page getPage(int p, int length) {
		Page page = userDao.getPage(p, length);
		return page;
	}
	
	@Override
	public Page getSearchPage(int p, int length, String searchStr) {
		Page page = userDao.getPage(p, length, searchStr);
		return page;
	}
	
	
	@Override
	public void export(Page page,String searchStr,int type,OutputStream pw) {
		List<User> userList = new ArrayList<User>();
		
		if(type == 1){
			//按照条件导出
			page = getSearchPage(page.getP(),Constant.MANAGER_LIST_LENGTH , searchStr);
			userList = getSearch(page, searchStr);
			//导出
		}
		if(type == 2){
			//全部导出
			userList = userDao.getAll();
		}
		if(type == 3){
			try{
				userList = userDao.getByUserIds (searchStr.toCharArray());
			}catch(Exception e){
				userList = new ArrayList<User>();
			}
		}
			//导出
		UserUtil.exportUserExcel(userList, pw);
	}
	@Override
	public User getById(User user) {
		User uu =  userDao.findById(user.getId());
		LazyDataLoadUtils.loadUserAndRole(user);
		return uu;
	}
	@Override
	public  void jiami (User user){
		try{
			DesUtils deu = new DesUtils();
			//加密
			user.setPwd(deu.encrypt(user.getPwd()));
		}catch(Exception e){
			throw new RuntimeException("加密失败:" + user.toString());
		}
	}
	
	
	
	@Override
	public List<String> importExcel(File userExcel, String userExcelFileName) {
		List<String> messages = new ArrayList<String>();
		int successSize = 0;
		int errorSize = 0;
		try {
			FileInputStream fileInputStream = new FileInputStream(userExcel);
			boolean is03Excel = userExcelFileName.matches("^.+\\.(?i)(xls)$");
			//1、读取工作簿
			Workbook workbook = is03Excel ? new HSSFWorkbook(fileInputStream):new XSSFWorkbook(fileInputStream);
			//2、读取工作表
			Sheet sheet = workbook.getSheetAt(0);
			//3、读取行
			if(sheet.getPhysicalNumberOfRows() > 2){
				User user = null;
				for(int k = 2; k < sheet.getPhysicalNumberOfRows(); k++){
					//4、读取单元格
					Row row = sheet.getRow(k);
					user = new User();
					//用户名
					Cell cell0 = row.getCell(1);
					user.setAccount(cell0.getStringCellValue());
					
					//默认用户密码为 123456
					user.setPwd("123456");
					
					//默认用户强制修改密码
					user.setIsModifyPassword(User.Modify_Password_true);
					Cell cell1 = row.getCell(2);
					user.setName(cell1.getStringCellValue());
					Role role = new Role();
					Cell cell4 = row.getCell(4);
					role.setName(cell4.getStringCellValue());
					role = roleService.findByName(role);
					//5、保存用户
					if(save(user,role,null)){
						messages.add("用户名为:" + user.getAccount() +"导入成功");
						successSize ++ ;
					}else{
						messages.add("用户名为:" + user.getAccount() +"导入失败");
						errorSize ++ ;
					}
				}
			}
			workbook.close();
			fileInputStream.close();
			messages.add(0, "成功:" + successSize  + " 失败:" + errorSize);
			
		} catch (Exception e) {
			e.printStackTrace();
			messages.add("您导入的不是一个正确格式的EXCEL文档");
			
		}
		return messages;
	}

///////////////////////////////////////////////////////////////
	public void setUserInformationDao(IUserInformationDao userInformationDao) {
		this.userInformationDao = userInformationDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	
}
