package com.scms.bulletin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.scms.bulletin.dao.IInformationDao;
import com.scms.bulletin.entity.Information;
import com.scms.bulletin.entity.InformationAndUser;
import com.scms.bulletin.entity.InformationSendRole;
import com.scms.bulletin.service.IInformationService;
import com.scms.currency.service.impl.AbsBaseService;
import com.scms.user.dao.IRoleDao;
import com.scms.user.entity.Role;
import com.scms.user.entity.User;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年2月18日 下午2:16:44 
 * 类说明 
 */
@Service
public class InformationService extends AbsBaseService implements IInformationService {
	@Resource
	private IInformationDao informationDao;
	@Resource
	private IRoleDao roleDao ; 
	
	@Override
	public void addLog(String record, HttpServletRequest request) {
		record = "[公告管理-消息模块]" + record ;
		super.addLog(record, request);
	}
	
	@Override
	public Information getInformation(Information information) {
		if(information == null || information.getId()<=0){
			return null;
		}
		return  informationDao.findById(information.getId());
	}
	
	@Override
	public List<Role> getRoles() {
		List<Role> roles  = roleDao.getAll();
		roles.toString();
		return roles;
	}
	@Override
	public Page getPage(int p, int lenght) {
		return informationDao.getPage(lenght, p);
	}

	@Override
	public Page getSearchPage(int p, int lenght, String searchStr) {
		return informationDao.getPage(lenght,p,searchStr);
	}
	@Override
	public boolean save (Information information ,List<Role> roles,User user){
		Information temp = new Information();
		temp.setRoles(handleRoles(roles));
		temp.setTitle(information.getTitle());
		temp.setContent(information.getContent());
		temp.setBrowseInforMationCount(0);
		temp.setUser(user);
		temp.setDate(new Date());
		if(!(information.getState() == Information.INFORMATION_STATE_NOTICE || information.getState() == Information.INFORMATION_STATE_URGENT)){
			information.setState(Information.INFORMATION_STATE_DESTORY);
		}
		temp.setState(information.getState());
		//
		informationDao.save(temp);
		//生成中间表数据
		if(temp.getRoles() != null){
			//创建中间表数据
				List<User> users = roleDao.getUserByRoles(temp.getRoles());
				if(users!=null){
					for(User userModel : users){
						InformationAndUser ifa = new InformationAndUser();
						//设置消息
						ifa.setInformation(temp);
						//设置用户
						ifa.setUser(userModel);
						//设置状态
						ifa.setState(InformationAndUser.USER_INFORMATION_STATE_NO);
						informationDao.createInformationAndUser(ifa);
					}
				}
				
				
		}
		return true;
	}
	@Override
	public boolean update(Information information ,List<Role> roles){
		if(information == null || information.getId()<=0){
			return false;
		}
		Information temp = informationDao.findById(information.getId());
		
		if(temp == null)
			return false;
		//删除中间表数据
		informationDao.deleteInforMationSendRole(temp.getRoles());
		//
		informationDao.deleteInformationAndUser(temp.getId());
		
		temp.setRoles(handleRoles(roles));
		temp.setTitle(information.getTitle());
		temp.setContent(information.getContent());
		temp.setDate(new Date());
		
		if(!(information.getState() == Information.INFORMATION_STATE_NOTICE || information.getState() == Information.INFORMATION_STATE_URGENT)){
			information.setState(Information.INFORMATION_STATE_DESTORY);
		}
		temp.setState(information.getState());
		
		//更新
		informationDao.update(temp);
		//生成中间表数据
		if(temp.getRoles() != null){
			//创建中间表数据
			for(InformationSendRole isr : temp.getRoles()){
				List<User> users = roleDao.getUserByRole(isr.getRole().getId());
				for(User userModel : users){
					InformationAndUser ifa = new InformationAndUser();
					//设置消息
					ifa.setInformation(temp);
					//设置用户
					ifa.setUser(userModel);
					//设置状态
					ifa.setState(InformationAndUser.USER_INFORMATION_STATE_NO);
					informationDao.createInformationAndUser(ifa);
				}
			}
		}
		return true;
	}
	
	private List<InformationSendRole> handleRoles(List<Role> roles){
		//判断Roles是否可用
		if(roles == null || roles.size() == 0)
			return new ArrayList<InformationSendRole>();
		//使用Set是为了剔除重复的Role
		Set<Role> roleSet = new HashSet<Role>(); 
		//剔除重复的Role
		for(Role role : roles){
			Role temp = roleDao.findById(role.getId());
			if(temp!=null){
				roleSet.add(roleDao.findById(role.getId())); 
			}
		}
		//转换成
		//将Set转换成List
		List<InformationSendRole> temp = new ArrayList<InformationSendRole>();
		for(Role role : roleSet){
			InformationSendRole isr = new InformationSendRole(role);
			temp.add(isr);
		}
		return temp;
	}
	
	@Override
	public boolean delete(Information information){
		if(information == null || information.getId() <= 0)
			return false;
		information = informationDao.findById(information.getId());
		if(information == null)
			return false;
		//删除中间表 informationAndUser
		informationDao.deleteInformationAndUser(information.getId());
		//删除中间表InforMationSendRole
		informationDao.deleteInforMationSendRole(information.getRoles());
		//删除实体
		informationDao.delete(information.getId());
		return true;
	}
	@Override
	public boolean remind(Information information) {
		if(information == null || information.getId() <= 0)
			return false;
		information = informationDao.findById(information.getId());
		if(information == null)
			return false;
		//删除中间表 informationAndUser
		informationDao.deleteInformationAndUser(information.getId());
		if(information.getRoles() != null){
			//创建中间表数据
			for(InformationSendRole isr : information.getRoles()){
				List<User> users = roleDao.getUserByRole(isr.getRole().getId());
				for(User user : users){
					InformationAndUser ifa = new InformationAndUser();
					ifa.setInformation(information);
					ifa.setUser(user);
					ifa.setState(InformationAndUser.USER_INFORMATION_STATE_NO);
					informationDao.createInformationAndUser(ifa);
				}
			}
		}
		return true;
	}
	
	
	
	@Override
	public List<Information> getByPage(Page page) {
		return informationDao.getByPage(page);
	}
	@Override
	public List<Information> getBySearch(Page page, String searchStr) {
		return informationDao.getByPage(page, searchStr);
	}

}
