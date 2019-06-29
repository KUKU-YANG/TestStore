package com.store.web.servlet;

import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Cart;
import com.store.domain.CartItem;
import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.domain.PageModel;
import com.store.domain.User;
import com.store.service.OrderService;
import com.store.service.impl.OrderServiceImpl;
import com.store.utils.PaymentUtil;
import com.store.utils.UUIDUtils;
import com.store.web.base.BaseServlet;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	OrderService service = new OrderServiceImpl();
	Order order = null;

	public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 确认用户登录状态
		User user = (User) request.getSession().getAttribute("loginUser");
		if (null == user) {
			request.setAttribute("msg", "亲，请登录后再下单哦~");
			return "/jsp/info.jsp";
		}
		// 获取购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// 创建订单对象，为订单对象赋值
		order = new Order();
		order.setOid(UUIDUtils.getId());
		order.setOrdertime(new Date());
		order.setTotal(cart.getTotal());
		order.setState(1);
		order.setUser(user);
		// 遍历购物项的同时，创建订单项，并赋值
		for (CartItem item : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setItemid(UUIDUtils.getId());
			orderItem.setQuantity(item.getNum());
			orderItem.setTotal(item.getSubTotal());
			orderItem.setProduct(item.getProduct());
			orderItem.setOrder(order);
			// 将订单项加入到订单中
			order.getList().add(orderItem);
		}
		// 调用service的saveOrder
		service.saveOrder(order);
		// 清空购物车
		cart.clearCart();
		// 将订单放入request
		request.setAttribute("order", order);
		// 转发到/jsp/order_info.jsp
		return "/jsp/order_info.jsp";
	}

	public String findMyOrdersWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取用户信息
		User user = (User) request.getSession().getAttribute("loginUser");
		// 获取当前页
		Integer curNum = Integer.parseInt(request.getParameter("num"));
		// 调用service查询用户订单信息，返回PageModel
		// PageModel：分页参数，url，当前页的订单（集合），每个订单的订单项，订单项对应的商品信息
		PageModel pm = service.findMyOrdersWithPage(user, curNum);
		// 将PageModel放入request
		request.setAttribute("page", pm);
		// 转发到/jsp/order_list.jsp
		return "/jsp/order_list.jsp";
	}

	public String findOrderByOid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取订单oid
		String oid = request.getParameter("oid");
		// 调用service，根据oid查询订单信息
		order = service.findOrderByOid(oid);
		// 将订单信息存入request
		request.setAttribute("order", order);
		// 转发到/jsp/order_info.jsp
		return "/jsp/order_info.jsp";
	}

	public String payOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oid = request.getParameter("oid");
		String address = request.getParameter("address");
		String name = request.getParameter("name");
		String telephone = request.getParameter("telephone");
		String pd_FrpId = request.getParameter("pd_FrpId");
		order = service.findOrderByOid(oid);
		order.setName(name);
		order.setAddress(address);
		order.setTelephone(telephone);
		order.setState(2);
		service.updateOrder(order);
		// 向request中放入提示信息
		request.setAttribute("msg", "支付成功！订单号：" + oid + "金额：" + order.getTotal());
		// 转发到/jsp/info.jsp
		return "/jsp/info.jsp";
	}

}
