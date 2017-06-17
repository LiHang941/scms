package com.hang.Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.scms.bulletin.entity.Bulletin;
import com.scms.bulletin.entity.Category;
import com.scms.sys.entity.Modular;
import com.scms.sys.entity.SysCategory;
import com.scms.user.entity.Authority;
import com.scms.user.entity.Role;
import com.scms.user.entity.User;


/**
 * Hibernate工具类
 * 	<li> 获得线程session
 * 	<li> 获得普通Session
 * */
public class HibernateUtil {
	private static SessionFactory sf ;
	
	static {
		sf = new Configuration() 
			.configure()
			.addClass(User.class)
			.addClass(Modular.class)
			.addClass(Authority.class)
			.addClass(Bulletin.class)
			.addClass(Category.class)
			.addClass(SysCategory.class)
			.addClass(Role.class)
			.buildSessionFactory();
	}
	/**
	 * 获得一个线程方式的Session <br>
	 * 配置文件需要配置Thread开启的线程
	 * 
	 * */
	public static Session getThreadSession (){
		return sf.getCurrentSession();
	}
	/**
	 * 获得一个普通的Session 
	 * */
	public static Session getSession (){
		return sf.openSession();
	}
}