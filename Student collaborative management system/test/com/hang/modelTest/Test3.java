package com.hang.modelTest;

import org.junit.Test;

import com.scms.user.entity.User;
import com.scms.util.PageUtil;


/**
 * @author 航
 * @version 创建时间：2017年1月12日 上午11:47:16 类说明
 */
public class Test3 {

	@Test
	public void test() {
		int a = 5;
		int count = 10;
		int len = 20;
	System.out.println(	PageUtil.createPage(len, count, a));
		
	}
	@Test
	public void test2(){
		System.out.println(new User().getId());
	}
}
