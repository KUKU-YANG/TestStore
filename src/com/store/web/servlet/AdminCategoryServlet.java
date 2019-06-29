package com.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Category;
import com.store.service.CategoryService;
import com.store.service.impl.CategoryServiceImpl;
import com.store.utils.MyBeanUtils;
import com.store.utils.UUIDUtils;
import com.store.web.base.BaseServlet;

/**
 * Servlet implementation class AdminCategoryServlet
 */
@WebServlet("/AdminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {
	CategoryService service=new CategoryServiceImpl();
	List<Category> list=null;
	Category category=null;
	public String getAllCats(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取全部分类信息
		list=service.getAllCats();
		//将全部分类放入request
		request.setAttribute("allCats", list);
		//转发到/admin/category/list.jsp
		return "/admin/category/list.jsp";
	}
	public String addCategoryUI(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return "/admin/category/add.jsp";
	}
	public String addCategory(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取分类名称
		String cname=request.getParameter("cname");
		//创建该分类的cid
		String cid=UUIDUtils.getId();
		category=new Category(cid, cname);
		//调用service的addCategory
		service.addCategory(category);
		
		//重定向到getAllCats
		response.sendRedirect("/store01/AdminCategoryServlet?method=getAllCats");
		return null;
	}
	
	public String editUI(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String cid=request.getParameter("cid");
		category=service.findById(cid);
		request.setAttribute("category", category);
		return "/admin/category/edit.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//接收参数
		category=MyBeanUtils.populate(Category.class, request.getParameterMap());
		//调用service的edit()
		service.edit(category);
		//重定向到getAllCats
		response.sendRedirect("/store01/AdminCategoryServlet?method=getAllCats");
		return null;
	}
	
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String cid=request.getParameter("cid");
		//其实不应该删除整个分类，应该多设置一个‘是否可见’的属性，然后修改该属性
		service.delete(cid);
		//重定向到getAllCats
		response.sendRedirect("/store01/AdminCategoryServlet?method=getAllCats");
		return null;
	}
	

	
}
