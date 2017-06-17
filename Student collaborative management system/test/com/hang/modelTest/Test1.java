package com.hang.modelTest;

import java.util.Date;

import org.hibernate.Session;
import org.junit.Test;

import com.hang.Util.HibernateUtil;
import com.scms.bulletin.entity.Bulletin;
import com.scms.bulletin.entity.Category;
import com.scms.user.entity.User;

/**
 * @author 航
 * @version 创建时间：2017年1月12日 上午11:47:16 类说明
 */
public class Test1 {

	@Test
	public void test() {
		/************** User - Modular -  Authority ******************/
		
		/***************** Modular -  Authority 没有级联操作*******/
		
			Session s = HibernateUtil.getSession();
			s.beginTransaction().begin();

			/*Modular m2 = new Modular();
			m2.setName("用户模块");
			m2.setUrl_Modular("/manager/user/");
			Modular m3 = new Modular();
			m3.setName("公告模块");
			m3.setUrl_Modular("/manager/bulletin/");
			s.save(m2);
			s.save(m3);*/
			
			Category ca1 =  new Category();
			ca1.setName("学院新闻");

			Category ca2 =  new Category();
			ca1.setName("通知公告");
			
			Category ca3 =  new Category();
			ca1.setName("其他");
			
			s.save(ca1);
			s.save(ca2);
			s.save(ca3);
			
			s.beginTransaction().commit();
			s.close();
		
	}
	@Test
	public void test2(){
		/********公告 - 类别 - 用户*******/
		
			
			
			Bulletin bulletin = new Bulletin();
			//设置用户
			User user = new User();
			user.setId(1);
			//设置类别 
			
			Category category =  new Category();
			category.setId(1);
			
			
			bulletin.setCategory(category);
			bulletin.setUser(user);
			bulletin.setDate(new Date());
			bulletin.setTitle("dasdsadsadsadsa");
			bulletin.setContent("asdsadsadsadsadsadsadsadsa");
			
			for(int i=0;i<=1;i++){
				Session s = HibernateUtil.getSession();
				s.beginTransaction().begin();
				s.save(bulletin);
				s.beginTransaction().commit();
				s.close();
			}
			
	}
	@Test
	public  void test3 (){
		String hql  = "SELect user from User user WHERE user.id like '%admin%' or user.userName like'%admin%' or user.id in (select user2.id from User user2 join user2.authoritys authority WHERE authority.modular.name like '%admin%' and authority.isAuthority = '-1')  order by user.id ASC";
		Session s = HibernateUtil.getSession();
		s.beginTransaction().begin();
		System.out.println(s.createQuery(hql).list().toString());
		s.beginTransaction().commit();
		s.close();

	}
}
