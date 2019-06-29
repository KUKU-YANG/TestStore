package com.store.service;

import com.store.domain.Order;
import com.store.domain.PageModel;
import com.store.domain.User;

public interface OrderService {

	void saveOrder(Order order) throws Exception;

	PageModel findMyOrdersWithPage(User user, Integer curNum) throws Exception;

	PageModel findAllOrdersWithPage(Integer curNum) throws Exception;

	PageModel findAllOrdersWithPage(Integer curNum, Integer state) throws Exception;

	Order findOrderByOid(String oid) throws Exception;

	void updateOrder(Order order) throws Exception;

}
