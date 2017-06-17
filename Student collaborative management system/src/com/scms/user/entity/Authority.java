package com.scms.user.entity;

import com.scms.sys.entity.Modular;

/** 
 * @author  航
 * @version 创建时间：2017年1月11日 下午9:13:20 
 * 类说明 
 * 
 * 用户权限类   用户  级联 该类
 */
public class Authority {
//	1.	Id
	private int id;
//	2.	####与模块的交互的权限 
//	3.	模块名  模块表id
	private Modular modular;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Modular getModular() {
		return modular;
	}
	public void setModular(Modular modular) {
		this.modular = modular;
	}

	
	
	@Override
	public String toString() {
		return "Authority [id=" + id + ", modular=" + modular + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Authority other = (Authority) obj;
		if (modular == null) {
			if (other.modular != null)
				return false;
		} else if (!(modular.getId()==other.modular.getId()))
			return false;
		return true;
	}
	
}
