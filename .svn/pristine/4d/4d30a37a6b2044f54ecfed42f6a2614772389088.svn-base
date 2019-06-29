package com.store.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.PageModel;
import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.service.impl.ProductServiceImpl;
import com.store.web.base.BaseServlet;

/**
 * 商品
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends BaseServlet {
	public String findProductByPid(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取商品pid
		String pid=request.getParameter("pid");
		//根据id查询商品信息
		ProductService service=new ProductServiceImpl();
		Product product=service.findProductByPid(pid);
		//将商品信息放入request
		request.setAttribute("product", product);
		//转发到product_info
		return "/jsp/product_info.jsp";
	}
	
	public String findProductsByCidWithPage(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取cid，num
		String cid=request.getParameter("cid");
		String num=request.getParameter("num");
		Integer curNum=Integer.parseInt(num);
		//调用service，以分页形式查询当前类下商品信息
		ProductService service=new ProductServiceImpl();
		PageModel pm=service.findProductsByCidWithPage(cid,curNum);
		//将PageModel放入request
		request.setAttribute("page", pm);
		//转到product_list.jsp
		return "/jsp/product_list.jsp";
	}
}
