package com.scms.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.scms.currency.service.impl.AbsBaseService;
import com.scms.sys.entity.Modular;
import com.scms.sys.service.IModularService;
import com.scms.user.dao.IRoleDao;
import com.scms.user.entity.Authority;
import com.scms.user.entity.Role;
import com.scms.user.service.IRoleService;
import com.scms.user.util.LazyDataLoadUtils;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年2月11日 上午11:37:49 
 * 类说明 
 */
@Service
public class RoleService extends AbsBaseService implements IRoleService {
	/**
	 * 管理用户界面的集合长度
	 */
	public static final int Role_Manager_List_Length = 9;
	@Resource
	private IRoleDao roleDao ;
	@Resource
	private IModularService modularService;
	
	@Override
	public void addLog(String record, HttpServletRequest request) {
		record = "[用户模块-角色管理]" + record;
		super.addLog(record, request);
	}
	@Override
	public Role findById(Role role) {
		if(role == null || role.getId() == 0)
			return null;
		role = roleDao.findById(role.getId());
		//role 不存在 或者状态为不可用
		if(role == null || role.getState() == Role.Role_State_false)
			return null;
		LazyDataLoadUtils.loadRoleAuthority(role);
		return role;
	}
	
	@Override
	public Role findByName(Role role) {
		if(role == null)
			return null;
		role = roleDao.findByName(role.getName());
		//role 不存在 或者状态为不可用
		if(role == null || role.getState() == Role.Role_State_false)
			return null;
		LazyDataLoadUtils.loadRoleAuthority(role);
		return role;
	}
	
	@Override
	public List<Role> getAll() {
		List<Role> roles = roleDao.getAll();
		return roles;
	}
	@Override
	public boolean save(Role role ,List<Modular> list) {
		if(role == null || role.getName() == null || role.getName().equals(""))
			return false;
		if(list== null)
			list= new ArrayList<Modular>();
		
		if(role.getState() != Role.Role_State_false && role.getState()!= Role.Role_State_true){
			role.setState(Role.Role_State_false);
		}
		
		if(role.getId() == 0){
			return add(role,list);
		}else{
			return update(role, list);
		}
	}
	@Override
	public boolean updateState(Role role) {
		if(role == null || role.getId()==0)
			return false;
		role = roleDao.findById(role.getId());
		if(role == null)
			return false;
		
		role.setState(role.getState() * -1);
		roleDao.update(role);
		return true;
	}
	
	@Override
	public List<Role> getByPage(Page page) {
		List<Role> list= roleDao.getByPage(page);
		for(Role temp : list){
			LazyDataLoadUtils.loadRoleAuthority(temp);
		}
		return list;
	}
	private boolean add(Role role ,List<Modular> list){
		role.setAuthoritys(HandleModularList(list));
		roleDao.save(role);
		return true;
	}
	private boolean update(Role role ,List<Modular> list){
		Role temp = roleDao.findById(role.getId());
		if(temp == null)
			return false;
		roleDao.deleteAuthority(temp);
		temp.setState(role.getState());
		temp.setAuthoritys(HandleModularList(list));
		temp.setName(role.getName());
		roleDao.update(temp);
		return true;
	}
	/**
	 * 处理传入的Modluar 转换成 权限集合
	 * @param list
	 * @return
	 */
	private List<Authority> HandleModularList (List<Modular> list){
		List<Modular> ms = modularService.getAll();
		List<Authority> authoritys = new ArrayList<Authority>();
		for(Modular m : list){
			if(m.getId()!=0){
				for(Modular mss : ms){
					if(mss.getId() == m.getId()){
						Authority a = new Authority();
						a.setModular(mss);
						authoritys.add(a);
						break;
					}
				}
			}
		}
		return authoritys;
	}
	
	@Override
	public Page getPage(int p, int lenght) {
		return roleDao.getPage(lenght, p);
	}

	@Override
	@Deprecated  
	/**
	 * 该方法无用
	 */
	public Page getSearchPage(int p, int lenght, String searchStr) {
		throw new RuntimeException();
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void setModularService(IModularService modularService) {
		this.modularService = modularService;
	}
	
	
}
