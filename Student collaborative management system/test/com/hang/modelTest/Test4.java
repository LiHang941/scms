package com.hang.modelTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import org.hibernate.Session;
import org.junit.Test;

import com.hang.Util.HibernateUtil;
import com.scms.bulletin.entity.Bulletin;
import com.scms.bulletin.json.JsonBulletin;
import com.scms.json.entity.JsonCurrency;
import com.scms.util.Page;
import com.scms.util.data.GeneralQueryHelper;

/**
 * @author 航
 * @version 创建时间：2017年1月12日 上午11:47:16 类说明
 */
public class Test4 {

	@Test
	public void test1(){
		Page page =new Page();
		System.out.println(JSONArray.fromObject(page));
		
	}
	
	
	@Test
	public void test3() {
		Session s = HibernateUtil.getSession();
		s.beginTransaction().begin();
		
		
		Bulletin b =(Bulletin) s.get(Bulletin.class, 0);
		
		System.out.println(b);
		
		s.beginTransaction().commit();
		s.close();
	
	}
	@Test
	public void test2(){
		GeneralQueryHelper<Bulletin> gq = new GeneralQueryHelper<Bulletin>(Bulletin.class,"bulletin");
		System.out.println(gq.getQueryCountHql());
		List<Object> params = new ArrayList<Object>();
		params.add("123");
		params.add(123);
		gq.addAndCondition("bulletin.a = ? AND bulletin.b = ?", params);
		System.out.println(gq.getQueryCountHql());
		gq.addOrCondition("bulletin.c = ? AND bulletin.d = ?",params);
		System.out.println(gq.getQueryCountHql());
		gq.addOrderByProperty("bulletin.a", GeneralQueryHelper.ORDER_BY_ASC);
		System.out.println(gq.getQueryListHql());
		gq.addOrderByProperty("bulletin.b", GeneralQueryHelper.ORDER_BY_ASC);
		System.out.println(gq.getQueryListHql());
	}
}
