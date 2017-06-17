package com.hang.newTest;

import static org.junit.Assert.fail;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.scms.sys.dao.IUserAccessLogDao;
import com.scms.user.dao.IUserDao;
import com.scms.util.Page;

/** 
 * @author  航
 * @version 创建时间：2017年2月6日 下午12:33:31 
 * 类说明 
 */
public class SessionFactoyTest {
	ClassPathXmlApplicationContext ctx;

	@Before
	public void loadCtx() {
		ctx = new ClassPathXmlApplicationContext("bean.xml");
	}
	@Test
	public void test() {
		/*SessionFactory sf = (SessionFactory) ctx.getBean("sessionFactory");
		System.out.println(sf);*/
		
		IUserDao userDao = (IUserDao) ctx.getBean("userDao");
		System.out.println(userDao.findByUserName("admin111"));
		System.out.println(userDao.findByUserName("adminaa1"));
		System.out.println(userDao.findByUserName("adminaa2"));
	}
	
	
	@Test
	public void test2() {
		/*SessionFactory sf = (SessionFactory) ctx.getBean("sessionFactory");
		System.out.println(sf);*/
		
		IUserAccessLogDao userDao = (IUserAccessLogDao) ctx.getBean("userAccessLogDao");
		System.out.println(userDao.getByPage(new Page(), "admin111"));
//		System.out.println(userDao.getByPage(new Page()));
	}

}
