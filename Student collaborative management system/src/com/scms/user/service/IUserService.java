package com.scms.user.service;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import com.scms.currency.entity.UserInformation;
import com.scms.currency.service.base.IPageService;
import com.scms.user.entity.Role;
import com.scms.user.entity.User;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年1月13日 下午12:18:10 
 * 类说明 
 * 
 * 用户业务逻辑层
 */
public interface IUserService extends IPageService {
	
	/**
	 * 添加
	 * @param user
	 */
	boolean save(User user,Role role,UserInformation userInformation);
	
	/**
	 * 删除
	 * 
	 * 删除  处理与用户有关联的数据  关联数据置为默认
	 * @param user
	 * @return
	 */
	boolean delete(User user);
	
	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	boolean updatePassword(User user);
	
	/**
	 * 锁定
	 * @param user
	 * @return
	 */
	boolean locking (User user);
	/**
	 * 是否强制修改密码
	 * @param user
	 * @return
	 */
	boolean modifyPassword (User user);
	
	/**
	 * 登录
	 * @return
	 */
	User islogin(User user);
	/**
	 * 通过指定的用户获取详细资料
	 * @param user
	 * @return
	 */
	UserInformation getUserInformation(User user);
	/**
	 * 获取指定页码的User集合
	 * @param page
	 * @return
	 */
	List<User> getByPage(Page page);
	
	/**
	 * 通过指定的搜索字符串搜索内容
	 * @param page
	 * @param SearchStr
	 * @return
	 */
	List<User> getSearch(Page page,String SearchStr);
	
	
	
	
	/**
	 * 通过Id查用户
	 * @param user
	 * @return
	 */
	User getById(User user);
	/**
	 * 加载全部数据
	 * @param user
	 * @return
	 */
	User getLoginUserId(User user);
	/**
	 * 将User 导出到Excel03版本
	 * @param page 当前页码
	 * @param searchStr  搜索字符串  and 选中字串
	 * @param type 导出方式  1 按条件导出，2,全部导出 3.选中内容
	 * @param res
	 */
	void export(Page page,String searchStr,int type,OutputStream pw);
	
	/**
	 * 加密
	 * @param user
	 */
	void jiami (User user);
	/**
	 * 导入Excel 
	 * @param userExcel
	 * @param userExcelFileName
	 * @param message 状态消息
	 */
	List<String> importExcel(File userExcel, String userExcelFileName);
	
}
