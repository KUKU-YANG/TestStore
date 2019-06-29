package com.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Category;
import com.store.domain.Product;
import com.store.service.CategoryService;
import com.store.service.ProductService;
import com.store.service.impl.CategoryServiceImpl;
import com.store.service.impl.ProductServiceImpl;
import com.store.web.base.BaseServlet;

/**
 * 首页
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends BaseServlet {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * 废案
		 * //调用service获取分类信息，返回集合 CategoryService service=new CategoryServiceImpl();
		 * List<Category> list=service.getAllCats(); //将集合放入request
		 * request.setAttribute("allCats", list);
		 */
		
		//调用service的查询最新/最热商品
		ProductService service=new ProductServiceImpl();
		List<Product> list01=service.findHots();
		List<Product> list02=service.findNews();
		
		//将2个集合都放入request
		request.setAttribute("hots", list01);
		request.setAttribute("news", list02);
		
		//转发到真实首页
		return "/jsp/index.jsp";
	}
}
