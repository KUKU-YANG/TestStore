package com.store.web.servlet;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.User;
import com.store.service.UserService;
import com.store.service.impl.UserServiceImpl;
import com.store.utils.MailUtils;
import com.store.utils.MyBeanUtils;
import com.store.utils.UUIDUtils;
import com.store.web.base.BaseServlet;

/**
 * 用户
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	UserService service=null;
	User user=null;
	public String regisUI(HttpServletRequest request, HttpServletResponse response) {
		return "/jsp/register.jsp";
	}
	public String loginUI(HttpServletRequest request, HttpServletResponse response) {
		return "/jsp/login.jsp";
	}
	

	public String userRegis(HttpServletRequest request, HttpServletResponse response) {
		//接收表单数据
		Map<String, String[]> map = request.getParameterMap();
		user = new User();
		Class clazz = user.getClass();
		//将表单数据赋值给user
		MyBeanUtils.populate(user, map);
		//将其他属性赋值给user
		user.setUid(UUIDUtils.getId());
		user.setState(0);
		user.setCode(UUIDUtils.getCode());
		
		service = new UserServiceImpl();
		try {
			service.userRegist(user);
			MailUtils.sendMail(user.getEmail(), user.getCode());
			request.setAttribute("msg", "注册成功，请激活^_^");
		} catch (Exception e) {
			request.setAttribute("msg", "注册失败，请重新注册T-T");
		}
		return "/jsp/info.jsp";
	}
	
	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String code=request.getParameter("code");
		service=new UserServiceImpl();
		Boolean flag = service.userActive(code);
		if(flag==true) {
			request.setAttribute("msg", "激活成功，请登录");
			return "jsp/login.jsp";
		}else {
			request.setAttribute("msg", "激活失败，请重新激活");
			return "jsp/info.jsp";
		}
	}
	
	public String userLogin(HttpServletRequest request, HttpServletResponse response) {
		User u=new User();
		MyBeanUtils.populate(u, request.getParameterMap());
		service=new UserServiceImpl();
		try {
		user=service.userLogin(u);
		if(user!=null) {
			
			
			String autoLogin=request.getParameter("autoLogin");
			String rememberme=request.getParameter("rememberme");
			if("1".equals(rememberme)) {
				//发送cookie
				Cookie remembermeCookie=new Cookie("remembermeCookie",user.getUsername());
				remembermeCookie.setPath("/");
				remembermeCookie.setMaxAge(60*5);
				response.addCookie(remembermeCookie);
			}else {
				//删除cookie
				Cookie autoLoginCookie=new Cookie("remembermeCookie","");
				autoLoginCookie.setPath("/");
				autoLoginCookie.setMaxAge(0);
				response.addCookie(autoLoginCookie);
			}
			
			if("1".equals(autoLogin)) {
				//发送cookie
				Cookie autoLoginCookie=new Cookie("autoLoginCookie", user.getUsername()+"#"+user.getPassword());
				autoLoginCookie.setPath("/");
				autoLoginCookie.setMaxAge(60*5);
				response.addCookie(autoLoginCookie);
			}else {
				//删除cookie
				Cookie autoLoginCookie=new Cookie("autoLoginCookie","");
				autoLoginCookie.setPath("/");
				autoLoginCookie.setMaxAge(0);
				response.addCookie(autoLoginCookie);
			}
		}
		
		
		
		request.getSession().setAttribute("loginUser", user);
		response.sendRedirect("/store01/index.jsp");
		} catch (Exception e) {
			String msg=e.getMessage();
			request.setAttribute("msg", msg);
			return "/jsp/login.jsp";
		}
		return null;
	}
	
	public String logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//清除session
		request.getSession().invalidate();
		
		//删除cookie
		Cookie autoLoginCookie=new Cookie("autoLoginCookie","");
		autoLoginCookie.setPath("/");
		autoLoginCookie.setMaxAge(0);
		response.addCookie(autoLoginCookie);
		
		//重定向到首页
		response.sendRedirect("/store01/index.jsp");
		return null;
	}
	
	//ajax异步校验用户名
	public void checkUsername(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//接收文本框
		String username=request.getParameter("username");
		//调用service查询
		UserService service=new UserServiceImpl();
		User user=service.findByUsername(username);
		if(user==null) {
			//用户名未被使用
			response.getWriter().println(1);
		}else {
			//用户名已被使用
			response.getWriter().println(2);
		}
	}
	
}
