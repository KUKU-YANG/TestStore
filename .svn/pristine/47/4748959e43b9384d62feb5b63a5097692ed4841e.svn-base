package com.store.web.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Order;
import com.store.domain.PageModel;
import com.store.service.OrderService;
import com.store.service.impl.OrderServiceImpl;
import com.store.web.base.BaseServlet;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class AdminOrderServlet
 */
@WebServlet("/AdminOrderServlet")
public class AdminOrderServlet extends BaseServlet {
	OrderService service=new OrderServiceImpl();
	PageModel pm = null;
	Integer curNum = null;
	Order order=null;
	public String findAllOrdersWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取订单状态
		String val=request.getParameter("state");
		// 获取当前页
		curNum = Integer.parseInt(request.getParameter("num"));
		if(null==val || "".equals(val)) {
			//获取全部订单
			// 调用service的findAllOrdersWithPage返回PageModel
			pm = service.findAllOrdersWithPage(curNum);
		}else {
			Integer state=Integer.parseInt(val);
			pm = service.findAllOrdersWithPage(curNum,state);			
		}
		// 将page放入request
		request.setAttribute("page", pm);
		// 转发到/admin/product/list.jsp
		return "/admin/order/list.jsp";
	}
	
	public String findOrderByOidWithAjax(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取订单id
		String oid=request.getParameter("oid");
		//查询该订单下所有订单项以及对应的信息，返回集合
		order=service.findOrderByOid(oid);
		//将返回的集合转换为JSON格式字符串
		String json=JSONArray.fromObject(order.getList()).toString();
		//响应到客户端
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().println(json);
		return null;
	}
	
	public String updateOrderByOid(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取订单id
		String oid=request.getParameter("oid");
		//根据订单id查询订单
		order=service.findOrderByOid(oid);
		//设置订单状态
		order.setState(3);
		//修改订单信息
		service.updateOrder(order);
		//重定向到已发货订单页面
		response.sendRedirect("/store01/AdminOrderServlet?method=findAllOrdersWithPage&state=3&num=1");
		return null;
	}
	
}
