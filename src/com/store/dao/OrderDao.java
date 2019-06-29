package com.store.dao;

import java.sql.Connection;
import java.util.List;

import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.domain.User;

public interface OrderDao {

	void saveOrde(Connection conn,Order order) throws Exception;

	void saveOrderItem(Connection conn, OrderItem item) throws Exception;

	Integer getTotalRecords(User user) throws Exception;

	Integer getAllTotalRecords() throws Exception;

	Integer getTotalRecordsWithState(Integer state) throws Exception;

	List<Order> findMyOrdersWithPage(User user, int startIndex, int pageSize) throws Exception;

	List<Order> findAllOrdersWithPage(int startIndex, int pageSize) throws Exception;

	List<Order> findAllOrdersWithPage(int startIndex, int pageSize, Integer state) throws Exception;

	Order findOrderByOid(String oid) throws Exception;

	void updateOrder(Order order) throws Exception;

}
