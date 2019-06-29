package com.store.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Cart;
import com.store.domain.CartItem;
import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.service.impl.ProductServiceImpl;
import com.store.web.base.BaseServlet;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {
	public String addCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 从session中获取购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");

		if (null == cart) {
			// 如果获取不到就创建一个
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}

		// 如果获取到就直接用
		// 获取到商品id，数量
		String pid = request.getParameter("pid");
		Integer num = Integer.parseInt(request.getParameter("quantity"));

		// 通过商品id查询商品对象
		ProductService service = new ProductServiceImpl();
		Product product = service.findProductByPid(pid);

		// 获取到待购买的商品
		CartItem cartItem = new CartItem();
		cartItem.setNum(num);
		cartItem.setProduct(product);

		// 调用购物车上的方法
		cart.addCartItem(cartItem);
		// 重定向到/jsp/cart.jsp此处不允许转发，否则刷新会重复添加商品（重复提交数据）
		response.sendRedirect("/store01/jsp/cart.jsp");
		return null;
	}
	
	public String removeCartItem(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取pid
		String pid=request.getParameter("pid");
		//获取购物车
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		cart.removeCartItem(pid);
		response.sendRedirect("/store01/jsp/cart.jsp");
		return null;
	}
	
	public String clearCartItem(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		cart.clearCart();
		response.sendRedirect("/store01/jsp/cart.jsp");
		return null;
	}
	
}
