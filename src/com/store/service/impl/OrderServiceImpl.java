package com.store.service.impl;

import java.sql.Connection;
import java.util.List;

import com.store.dao.OrderDao;
import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.domain.PageModel;
import com.store.domain.User;
import com.store.service.OrderService;
import com.store.utils.BeanFactory;
import com.store.utils.JDBCUtils;

public class OrderServiceImpl implements OrderService {
	// 解耦获取OrderDao对象
	OrderDao dao = (OrderDao) BeanFactory.createDao("OrderDao");

	@Override
	public void saveOrder(Order order) throws Exception {
		// 保存订单和订单下所有的订单项
		Connection conn = null;
		try {
			// 获取连接
			conn = JDBCUtils.getConnection();
			// 开启事务
			conn.setAutoCommit(false);
			// 保存订单
			dao.saveOrde(conn, order);
			// 保存订单项
			for (OrderItem item : order.getList()) {
				dao.saveOrderItem(conn, item);
			}
			// 提交事务
			conn.commit();
		} catch (Exception e) {
			// 回滚
			conn.rollback();
		}
	}

	@Override
	public PageModel findMyOrdersWithPage(User user, Integer curNum) throws Exception {
		// 创建PageModel对象，计算并且携带分页参数
		Integer totalRecords = dao.getTotalRecords(user);
		PageModel pm = new PageModel(curNum, totalRecords, 3);
		// 关联集合
		List<Order> list = dao.findMyOrdersWithPage(user, pm.getStartIndex(), pm.getPageSize());
		pm.setList(list);
		// 关联url
		pm.setUrl("OrderServlet?method=findMyOrdersWithPage");
		return pm;
	}

	@Override
	public PageModel findAllOrdersWithPage(Integer curNum) throws Exception {
		// 创建PageModel对象，计算并且携带分页参数
		Integer totalRecords = dao.getAllTotalRecords();
		PageModel pm = new PageModel(curNum, totalRecords, 4);
		// 关联集合
		List<Order> list = dao.findAllOrdersWithPage(pm.getStartIndex(), pm.getPageSize());
		pm.setList(list);
		// 关联url
		pm.setUrl("AdminOrderServlet?method=findAllOrdersWithPage");
		return pm;
	}

	@Override
	public PageModel findAllOrdersWithPage(Integer curNum, Integer state) throws Exception {
		// 创建PageModel对象，计算并且携带分页参数
		Integer totalRecords = dao.getTotalRecordsWithState(state);
		PageModel pm = new PageModel(curNum, totalRecords, 4);
		// 关联集合
		List<Order> list = dao.findAllOrdersWithPage(pm.getStartIndex(), pm.getPageSize(),state);
		pm.setList(list);
		// 关联url
		pm.setUrl("AdminOrderServlet?method=findAllOrdersWithPage&state="+state);
		return pm;
	}
	
	@Override
	public Order findOrderByOid(String oid) throws Exception {
		Order order = dao.findOrderByOid(oid);
		return order;
	}

	@Override
	public void updateOrder(Order order) throws Exception {
		dao.updateOrder(order);
	}

}
