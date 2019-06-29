package com.store.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.User;
import com.store.service.UserService;
import com.store.service.impl.UserServiceImpl;
import com.store.utils.CookUtils;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(filterName="/LoginFilter",urlPatterns="/*")
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// 强转
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		// 如果是登录页面直接放行
		String servletPath = request.getServletPath();
		if (servletPath.startsWith("/UserServlet")) {
			String method = request.getParameter("method");
			if ("loginUI".equals(method)) {
				chain.doFilter(request, response);
				return;
			}
		}
		// 获取用户登录信息，判断session中有没有此用户
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		// 如果已经登录，session中有此用户，放行，不需要自动登录
		if (loginUser != null) {
			chain.doFilter(request, response);
			return;
		}
		// 获得自动登录cookie信息
		Cookie userCookie = CookUtils.getCookieByName("autoLoginCookie", request.getCookies());
		// 判断自动登录cookie是否存在，如果没有cookie，不需要自动登录
		System.out.println(userCookie);
		if (userCookie == null) {
			chain.doFilter(request, response);
			return;
		}
		// 通过用户cookie中记录信息，查询用户
		String[] u = userCookie.getValue().split("#");
		String username = u[0];
		String password = u[1];
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		UserService service = new UserServiceImpl();
		try {
			loginUser = service.userLogin(user);

			if (loginUser == null) {
				chain.doFilter(request, response);
				
			}
			// 自动登录，将用户放入session，方便下一次未过期前用
			request.getSession().setAttribute("loginUser", loginUser);
			chain.doFilter(request, response);

		} catch (Exception e) {
			System.out.println("自动登录异常");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
