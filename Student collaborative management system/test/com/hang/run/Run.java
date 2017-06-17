package com.hang.run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hang.Util.HibernateUtil;
import com.scms.bulletin.entity.Category;
import com.scms.sys.entity.Modular;
import com.scms.sys.entity.SysCategory;
import com.scms.user.entity.Authority;
import com.scms.user.entity.Role;
import com.scms.user.entity.User;
import com.scms.user.util.DesUtils;


/**
 * @author 航
 * @version 创建时间：2017年1月12日 上午11:47:16 类说明
 * 
 * 程序初次启动  必须使用该方法
 */
public class Run {
	
	private ApplicationContext ac;
	@Before
	public void befor(){
	//	ac = new ClassPathXmlApplicationContext("bean.xml");
	}
	@Test
	public void addModule(){
		Session s = HibernateUtil.getSession();
		s.beginTransaction().begin();
		//公告模块
		Modular m =  new Modular();
		m.setName("公告管理");
		m.setImgUrl("images/leftico03.png");
		Map<String,String> map = new HashMap<String,String>();
		map.put("公告列表", "manager/bulletin/list.do");
		map.put("类别管理", "manager/bulletin/category/list.do");
		map.put("消息管理", "manager/bulletin/information/list.do");
		m.setUrl_method(map);
		s.save(m);
		Modular m2 =  new Modular();
		m2.setName("用户管理");
		m2.setImgUrl("images/leftico02.png");
		Map<String,String> userMap = new HashMap<String,String>();
		userMap.put("添加用户", "manager/user/add.do");
		userMap.put("用户列表", "manager/user/list.do");
		userMap.put("角色列表", "manager/user/role/list.do");
		m2.setUrl_method(userMap);
		s.save(m2);
		Modular m3 =  new Modular();
		m3.setName("系统管理");
		m3.setImgUrl("images/leftico04.png");
		Map<String,String> systemMap = new HashMap<String,String>();
		systemMap.put("登录记录", "manager/system/userAccessLog/list.do");
		systemMap.put("操作记录", "manager/system/operationRecord/list.do");
		systemMap.put("系统运行日志", "manager/system/systemLog/list.do");
		systemMap.put("系统默认类别", "manager/system/sysCategory/update.do");
		m3.setUrl_method(systemMap);
		
		s.save(m3);
		s.beginTransaction().commit();
		s.close();
	}
	@Test
	public void addCategory(){
		Session s = HibernateUtil.getSession();
		s.beginTransaction().begin();
		Category c1 = new Category();
		c1.setName("学院新闻");
		Category c2 = new Category();
		c2.setName("通知公告");
		Category c3 = new Category();
		c3.setName("其他");
		
		SysCategory sc =new SysCategory();
		sc.setCategory1(c1);
		sc.setCategory2(c2);
		sc.setCategory3(c3);
		
		
		s.save(c1);
		
		s.save(c2);
	
		s.save(c3);
		
		s.save(sc);
		
		
		
		s.beginTransaction().commit();
		s.close();
	}
	
	@Test
	public void addUser() throws Exception {
		Session s = HibernateUtil.getSession();
		s.beginTransaction().begin();
		User user= new User();
		user.setAccount("admin");
		user.setPwd(new DesUtils().encrypt("admin"));
		List <Authority> authoritys = new ArrayList<Authority> ();
		Authority a1 = new Authority();
		Modular m =  new Modular();
		m.setId(1);
		a1.setModular(m);
		
		Authority a2 = new Authority();
		Modular m2 =  new Modular();
		m2.setId(2);
		a2.setModular(m2);
		
		Authority a3 = new Authority();
		Modular m3 =  new Modular();
		m3.setId(3);
		a3.setModular(m3);
		authoritys.add(a1);
		authoritys.add(a2);
		authoritys.add(a3);
		
		Role role = new Role();
		role.setName("超级管理员");
		role.setAuthoritys(authoritys);
		user.setRole(role);
		s.save(role);
		s.save(user);
		
		s.beginTransaction().commit();
		s.close();
	}
	public static void main(String[] args) throws Exception {
		Run run = new Run();
		run.addCategory();
		run.addModule();
		run.addUser();
	}
}
