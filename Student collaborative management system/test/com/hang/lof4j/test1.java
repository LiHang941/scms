package com.hang.lof4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/** 
 * @author  航
 * @version 创建时间：2017年1月30日 下午1:11:21 
 * 类说明 
 */
public class test1 {
	@Test
	public void test2() {
		Log log = LogFactory.getLog(getClass());
		try {
			int i = 1/0;
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		log.debug("debug 级别日志");
		log.info("info 级别日志");
		log.warn("warn 级别日志");
		log.error("error 级别日志");
		log.fatal("fatal 级别日志");
	}
}
