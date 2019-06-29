package com.store.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Test;

import com.store.domain.OrderItem;
import com.store.domain.Product;
import com.store.utils.JDBCUtils;

public class TestMapListHandler {

	@Test
	public void testForeachMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("111", "aaa");
		map.put("222", "bbb");
		map.put("333", "ccc");
		map.put("444", "ddd");
		map.put("555", "eee");
		// 增强for循环遍历map
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "---" + entry.getValue());
		}
	}

	// 根据订单id查询查询每笔订单下的订单项以及对应的商品信息
	// SQL分析
	// 笛卡儿积
	// select * from orderitem,product
	// 在笛卡尔积的基础上筛选有意义的数据
	// select * from orderitrm o,product p where o.pid=p.pid
	// 最终结果
	// select * from orderitem o,product p where o.pid=p.pid and o.oid=?
	@Test
	public void test() throws Exception {
		List<OrderItem> lt=new ArrayList<OrderItem>();
		String sql = "select * from orderitem o,product p where o.pid=p.pid and o.oid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		// 由于返回的数据来自多个表，多行数据MapListHandler封装返回的数据
		// 返回的list的元素是map
		List<Map<String, Object>> list = qr.query(sql, new MapListHandler(), "1708C673BE084E948601E5FAFA0761E3");
		// 遍历list
		for (Map<String, Object> map : list) {
			// 增强for循环输出map的内容
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				System.out.print(entry.getKey() + ":" + entry.getValue() + " ");
			}
			System.out.println();
			// 由于BeanUtils日期方法传递参数有问题,手动向BeanUtils注册一个时间类型转换器
			// 1_创建时间类型的转换器
			DateConverter dt = new DateConverter();
			// 2_设置转换的格式
			dt.setPattern("yyyy-MM-dd");
			// 3_注册转换器
			ConvertUtils.register(dt, java.util.Date.class);

			// 将map中各自的数据填充到各自的对象上
			Product product = new Product();
			BeanUtils.populate(product, map);
			OrderItem orderItem = new OrderItem();
			BeanUtils.populate(orderItem, map);
			orderItem.setProduct(product);
			
			//将所有lt的订单项放入lt订单
			lt.add(orderItem);
		}
	}
}
