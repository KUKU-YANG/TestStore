package com.store.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.store.dao.OrderDao;
import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.domain.Product;
import com.store.domain.User;
import com.store.utils.JDBCUtils;

public class OrderDaoImpl implements OrderDao{
	QueryRunner runner=new QueryRunner();
	QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
	String sql=null;
	
	@Override
	public void saveOrde(Connection conn, Order order) throws Exception {
		sql="insert into orders values(?,?,?,?,?,?,?,?)";
		Object[] params= {order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid()};
		runner.update(conn, sql,params);
	}

	@Override
	public void saveOrderItem(Connection conn, OrderItem item) throws Exception{
		sql="insert into orderitem values(?,?,?,?,?)";
		Object[] params= {item.getItemid(),item.getQuantity(),item.getTotal(),item.getProduct().getPid(),item.getOrder().getOid()};
		runner.update(conn, sql,params);
	}

	@Override
	public Integer getTotalRecords(User user) throws Exception {
		sql="select count(*) from orders where uid=?";
		Long totalRecords=(Long) qr.query(sql, new ScalarHandler(),user.getUid());
		return totalRecords.intValue();
	}

	@Override
	public Integer getAllTotalRecords() throws Exception {
		sql="select count(*) from orders";
		Long totalRecords=(Long) qr.query(sql, new ScalarHandler());
		return totalRecords.intValue();
	}
	
	@Override
	public Integer getTotalRecordsWithState(Integer state) throws Exception {
		sql="select count(*) from orders where state=?";
		Long totalRecords=(Long) qr.query(sql, new ScalarHandler(),state);
		return totalRecords.intValue();
	}
	
	@Override
	public List<Order> findMyOrdersWithPage(User user, int startIndex, int pageSize) throws Exception {
		sql="select * from orders where uid=? limit ?,?";
		List<Order> list=qr.query(sql, new BeanListHandler<Order>(Order.class),user.getUid(),startIndex,pageSize);
		//遍历所有订单
		for (Order order : list) {
			//获取每笔订单的oid	查询每笔订单下的订单项以及对应的商品信息
			String oid=order.getOid();
			sql="select * from orderitem o,product p where o.pid=p.pid and o.oid=?";
			List<Map<String, Object>> lt=qr.query(sql, new MapListHandler(),oid);
			//遍历list
			for (Map<String, Object> map : lt) {
				// 由于BeanUtils日期方法传递参数有问题,手动向BeanUtils注册一个时间类型转换器
				// 1_创建时间类型的转换器
				DateConverter dt = new DateConverter();
				// 2_设置转换的格式
				dt.setPattern("yyyy-MM-dd");
				// 3_注册转换器
				ConvertUtils.register(dt, java.util.Date.class);
			 
				// 将map中各自的数据填充到各自的对象上
				Product product=new Product();
				BeanUtils.populate(product, map);
				OrderItem orderItem=new OrderItem(); 
				BeanUtils.populate(orderItem, map);
				orderItem.setProduct(product);
				
				//将订单项放入订单
				order.getList().add(orderItem);
			}
		}
		return list;
	}

	@Override
	public List<Order> findAllOrdersWithPage(int startIndex, int pageSize) throws Exception {
		sql="select * from orders limit ?,?";
		List<Order> list=qr.query(sql, new BeanListHandler<Order>(Order.class),startIndex,pageSize);
		return list;
	}
	
	@Override
	public List<Order> findAllOrdersWithPage(int startIndex, int pageSize, Integer state) throws Exception {
		sql="select * from orders where state=? limit ?,?";
		List<Order> list=qr.query(sql, new BeanListHandler<Order>(Order.class),state,startIndex,pageSize);
		return list;
	}
	
	@Override
	public Order findOrderByOid(String oid) throws Exception {
		sql="select * from orders where oid=?";
		Order order=qr.query(sql, new BeanHandler<Order>(Order.class),oid);
		//根据订单id查询订单下所有订单项的信息
		sql="select * from orderitem o,product p where o.pid=p.pid and o.oid=?";
		List<Map<String, Object>> list=qr.query(sql, new MapListHandler(),oid);
		for (Map<String, Object> map : list) {
			// 由于BeanUtils日期方法传递参数有问题,手动向BeanUtils注册一个时间类型转换器
			// 1_创建时间类型的转换器
			DateConverter dt = new DateConverter();
			// 2_设置转换的格式
			dt.setPattern("yyyy-MM-dd");
			// 3_注册转换器
			ConvertUtils.register(dt, java.util.Date.class);
		 
			// 将map中各自的数据填充到各自的对象上
			Product product=new Product();
			BeanUtils.populate(product, map);
			OrderItem orderItem=new OrderItem(); 
			BeanUtils.populate(orderItem, map);
			orderItem.setProduct(product);
			
			//将订单项放入订单
			order.getList().add(orderItem);
		}
		return order;
	}

	@Override
	public void updateOrder(Order order) throws Exception {
		sql="update orders set ordertime=?,total=?,state=?,address=?,name=?,telephone=? where oid=?";
		Object[] params= {order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid()};
		qr.update(sql,params);
	}

}
