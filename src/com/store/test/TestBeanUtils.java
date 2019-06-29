package com.store.test;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.junit.Test;

import com.store.domain.User;

public class TestBeanUtils {
	@Test
	public void test01() throws Exception {
		//模拟request.getPatameterMap();
		Map<String, String[]> map=new HashMap<String, String[]>();
		map.put("username", new String[] {"tom"});
		map.put("password", new String[] {"1234"});
		
		User user=new User();
		BeanUtils.populate(user, map);
		System.out.println(user);
	}
	
	@Test
	public void test02() throws Exception {
		//模拟request.getPatameterMap();
		Map<String, String[]> map=new HashMap<String, String[]>();
		map.put("username", new String[] {"tom"});
		map.put("password", new String[] {"1234"});
		map.put("birthday", new String[] {"1992-3-5"}); 
		User user=new User();
		
		//由于BeanUtils找到User.class文件有setBirthday这个方法，要执行，将1992-3-5转化为日期类型
		//BeanUtils不知道这个字符串的时间格式是什么，所以使用以下的时间转化器
		//1.创建时间转化器
		DateConverter dt=new DateConverter();
		//2.设置格式
		dt.setPattern("yyyy-MM-dd");
		//3.注册转换器
		ConvertUtils.register(dt, Date.class);
		
		BeanUtils.populate(user, map);
		System.out.println(user);
	}
	
	
	
}
