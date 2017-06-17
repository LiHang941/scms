package com.scms.user.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @author  航
 * @version 创建时间：2017年2月11日 上午11:17:58 
 * 类说明 
 * 
 * 权限角色
 */
public class Role implements Serializable {
	//角色ID
	private int id;
	//角色名称
	private String name;
	
	private List<Authority> authoritys;
	//默认有效
	private int state = Role_State_true; 
	
	//有效
	public static int Role_State_true = 1;
	//无效
	public static int Role_State_false = -1;
	
	public String getStateStr (){
		String temp = getStateMap().get(this.state);
		if(temp == null){
			temp = "无效";
		}
		return temp;
	}
	
	public static Map<Integer,String>getStateMap (){
		Map<Integer,String> map = new HashMap<Integer,String>();
		map.put(Role_State_true, "有效");
		map.put(Role_State_false, "无效");
		return map;
	}
	/**
	 * 遍历Authoritys 获取如下的字串  [用户模块][...]
	 * @return
	 */
	public String getAuthoritysStr(){
		String temp = "";
		if(this.authoritys == null){
			return "";
		}
		for(Authority a : this.authoritys){
			if(a.getModular() != null && a.getModular().getName() !=null ){
				temp = temp + "[" + a.getModular().getName() + "]";
			}
		}
		
		return temp ;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Authority> getAuthoritys() {
		return authoritys;
	}
	public void setAuthoritys(List<Authority> authoritys) {
		this.authoritys = authoritys;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authoritys == null) ? 0 : authoritys.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + state;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (authoritys == null) {
			if (other.authoritys != null)
				return false;
		} else if (!authoritys.equals(other.authoritys))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (state != other.state)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", authoritys="
				+ authoritys + ", state=" + state + "]";
	}

	
	
}
