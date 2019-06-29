package com.store.utils;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.store.dao.UserDao;
import com.store.domain.User;

public class BeanFactory {
	// XML解析
	public static Object createDao(String name) {
		try {
			// 通过传递过来的name获取application.xml中name对应的class值
			// 获取到Document对象
			SAXReader reader = new SAXReader();
			// 获取application.xml文件的输入流(必须位于src下)
			InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream("application.xml");
			Document document = reader.read(is);
			// 通过Document对象获取根节点beans
			Element rooElement = document.getRootElement();
			// 通过根节点获取根节点下的所有子节点bean，返回集合
			List<Element> list = rooElement.elements();
			// 遍历集合，判断每个元素上的id的值是否和当前的name一致
			for (Element element : list) {
				// element相当于beans节点下的每个bean
				// 获取到当前节点的id属性值,如果一致，获取到当前元素上class属性值
				String id = element.attributeValue("id");
				if (id.equals(name)) {
					String str = element.attributeValue("class");
					// 通过反射创建对象并返回
					Class clazz = Class.forName(str);
					// 利用class值通过反射创建对象返回
					return clazz.newInstance();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		UserDao dao=(UserDao) BeanFactory.createDao("UserDao");
		User user=new User();
		user.setUsername("test");
		user.setPassword("1");
		User userLogin=dao.userLogin(user);
		System.out.println(userLogin);
	}
}
