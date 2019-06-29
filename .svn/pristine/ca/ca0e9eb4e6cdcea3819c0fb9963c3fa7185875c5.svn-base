package com.store.web.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Category;
import com.store.service.CategoryService;
import com.store.service.impl.CategoryServiceImpl;
import com.store.utils.JedisUtils;
import com.store.web.base.BaseServlet;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

/**
 * 分类
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {
	public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 在redis中获取全部分类
		Jedis jedis = JedisUtils.getJedis();
		String json = jedis.get("allCats");
		
		if (null == json || "".equals(json)) {
			// 调用service获取全部分类
			CategoryService service = new CategoryServiceImpl();
			List<Category> list = service.getAllCats();
			// 将分类转化为json
			json = JSONArray.fromObject(list).toString();
			System.out.println("此时redis中没有数据");
			//将获取的json数据存放在redis中
			jedis.set("allCats",json);
		}
		// 将json数据响应到客户端
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
		jedis.close(); 
		return null;
	}
}
