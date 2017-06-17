package com.hang.modelTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.Session;
import org.junit.Test;

import com.hang.Util.HibernateUtil;
import com.scms.bulletin.service.impl.BulletinService;
import com.scms.currency.entity.Modular;
import com.scms.user.entity.Authority;
import com.scms.user.entity.User;

/**
 * @author 航
 * @version 创建时间：2017年1月12日 上午11:47:16 类说明
 */
public class Test2 {

	@Test
	public void test() {
		/************** User - Modular -  Authority ******************/
		
		/***************** Modular -  Authority 没有级联操作*******/
		
			Session s = HibernateUtil.getSession();
			s.beginTransaction().begin();

			User user = new User();
			user.setUserName("admin");
			user.setPwd("admin");
			Set<Authority> authoritys = new HashSet<Authority>();
			Authority a2 = new Authority();
			Modular m2 = new Modular();
			m2.setName("模块4");
			a2.setModular(m2);
			Authority a3 = new Authority();
			Modular m3 = new Modular();
			m3.setName("模块5");
			a3.setModular(m3);
			authoritys.add(a2);
			authoritys.add(a3);
			user.setAuthoritys(authoritys);
			s.save(m2);
			s.save(m3);
			s.save(user);
			s.beginTransaction().commit();
			s.close();
		
	}
	@Test
	public void test2(){
		/********公告 - 类别 - 用户*******/
		
			Session s = HibernateUtil.getSession();
			s.beginTransaction().begin();
			
			
			String i = s.createQuery("select count(*) from User")
			.uniqueResult().toString();
			
			
			
			/*s.createQuery("from User where ?")
			.setParameter(1, hql).list();
			*/System.out.println(i);
			
			s.beginTransaction().commit();
			s.close();
		
		
		
	}
	
	@Test
	public void test3(){
		
		/*	Session s = HibernateUtil.getSession();
			s.beginTransaction().begin();
			System.out.println(new BulletinDao().findById(2,s));
			
			s.beginTransaction().commit();
			s.close();
		
		*/
		
	}
	
	@Test
	public void test4() throws ParseException{
		
	//	String str = "123%%''!!//##";
		
	//	System.out.println(StringEscapeUtils.escapeSql(str));
		
		
		String da = "2014-10-15 123123";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		sdf.setLenient(false);
		
		
		System.out.println(sdf.parse(da));
	}
	
	
	@Test
	public void test5() throws ParseException{
		
	//	String str = "123%%''!!//##";
		
	//	System.out.println(StringEscapeUtils.escapeSql(str));
		
		
	Object [] cc = new Object[3];
	cc[0] = 0;
	cc[1] = "2014-10-11";
	cc[2] = "2016-10-11";
	
	System.out.println(new BulletinService().getHqlSearhStr("asdsada'++';s", cc));;
			
	
	}
}
