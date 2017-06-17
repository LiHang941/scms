package com.scms.currency.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.scms.bulletin.dao.IInformationDao;
import com.scms.bulletin.entity.Information;
import com.scms.bulletin.entity.InformationAndUser;
import com.scms.currency.service.IInformationAndUserService;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年2月24日 下午1:01:57 
 * 类说明 
 */
@Service
public class InformationAndUserService extends AbsBaseService implements IInformationAndUserService {
	@Resource
	private IInformationDao informationDao;
	
	@Override
	public void addLog(String record, HttpServletRequest request) {
		record = "[通用模块-收件箱]"+record;
		super.addLog(record, request);
	}
	
	@Override
	public Information ShowInformationByUserId(int userId,Information information) {
		if(information == null || information.getId()<= 0)
			return null;
		//用过用户的id查找到Role
		InformationAndUser informationAndUser = informationDao.getUserAndInformationByUserIdOrInformationId(userId,information.getId());
		//映射不存在  信息不存在  用户不存在  用户角色不存在
		if(informationAndUser == null || informationAndUser.getInformation() == null|| informationAndUser.getUser() == null ||informationAndUser.getUser().getRole() == null)
			//无权限
			return null;
		//List<InformationSendRole> isrList =  informationAndUser.getInformation().getRoles();
		//处理懒加载数据
		handleLazy(informationAndUser.getInformation());
		//判断信息接收的RoleList 是否有角色
		/*if(isrList!= null){
			for (InformationSendRole isr : isrList) {
				if(isr.getRole().getId() == informationAndUser.getUser().getRole().getId()){
					//浏览次数+1
					informationDao.showInformationBrowseInforMationCountAdd(informationAndUser.getInformation().getId());
					//有该角色  并设置查看的状态为查看
					informationDao.setInformationShowStateByUser(userId,informationAndUser.getInformation().getId());
					//有权限
					return informationAndUser.getInformation();
				}
			}
		}*/
		//浏览次数+1
		informationDao.showInformationBrowseInforMationCountAdd(informationAndUser.getInformation().getId());
		//有该角色  并设置查看的状态为查看
		informationDao.setInformationShowStateByUser(userId,informationAndUser.getInformation().getId());
	
		return informationAndUser.getInformation();
	}
	@Override
	public List<InformationAndUser> getByPage(int userId, Page page) {
		List<InformationAndUser> list = informationDao.getByPage(page, userId);
		//处理懒加载数据
		handleLazy(list);
		return list;
	}
	@Override
	public List<InformationAndUser> getBySearch(int userId, Page page, String searchStr) {
		List<InformationAndUser> list = informationDao.getByPage(page, searchStr, userId);
		//处理懒加载数据
		handleLazy(list);
		return list;
	}
	@Override
	public Page getPage(int userId, int p, int lenght) {
		return informationDao.getPage(lenght, p, userId);
	}
	@Override
	public Page getSearchPage(int userId,int p, int lenght, String searchStr) {
		return informationDao.getPage(lenght, p, searchStr, userId);
	}
	
	@Override
	public boolean delete(int userId, Information information) {
		if(information == null || information.getId()<= 0)
			return false;
		InformationAndUser informationAndUser = informationDao.getUserAndInformationByUserIdOrInformationId(userId,information.getId());
		if(informationAndUser == null)
			return false;
		informationDao.deleteInformationAndUserById(informationAndUser.getId());
		return true;
	}
	
	@Override
	public int getMyInformationCount(int userId) {
		return informationDao.getMyInformationCount(userId);
	}
	
	/**
	 * 处理懒加载数据
	 * @param list
	 */
	private void handleLazy (List<InformationAndUser> list){
		for(InformationAndUser iau : list){
			//显示发送用户名
			iau.getInformation().getUser().getName();
			//显示消息标题
			iau.getInformation().setTitle((iau.getInformation().getTitle()));
		}
	}
	/**
	 * 处理懒加载数据
	 * @param info
	 */
	private void handleLazy (Information info){
			//显示发送用户名
		info.getUser().getName();
			//显示消息标题
		info.getTitle();
		//内容
		info.getContent();
		//消息
		info.getBrowseInforMationCount();
		//时间
		info.getDate();
	}
	
}
