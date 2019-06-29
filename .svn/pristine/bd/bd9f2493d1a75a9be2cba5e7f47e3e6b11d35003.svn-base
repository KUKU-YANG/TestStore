package com.store.service.impl;

import java.util.List;

import com.store.dao.CategoryDao;
import com.store.domain.Category;
import com.store.service.CategoryService;
import com.store.utils.BeanFactory;
import com.store.utils.JedisUtils;

import redis.clients.jedis.Jedis;

public class CategoryServiceImpl implements CategoryService{
	//解耦获取CategoryDao对象
	CategoryDao dao=(CategoryDao) BeanFactory.createDao("CategoryDao");
	Category category=null;
	Jedis jedis=null;
	@Override
	public List<Category> getAllCats() throws Exception{
		List<Category> list=dao.getAllCats();
		return list;
	}

	@Override
	public void addCategory(Category category) throws Exception {
		dao.addCategory(category);
		//更新redis，由于CategoryServlet，只需要删掉旧的key即可
		jedis=JedisUtils.getJedis();
		jedis.del("allCats");
		jedis.close();
	}

	@Override
	public Category findById(String cid) throws Exception {
		category=dao.findById(cid);
		return category;
	}

	@Override
	public void edit(Category category) throws Exception {
		dao.edit(category);
		jedis=JedisUtils.getJedis();
		jedis.del("allCats");
		jedis.close();
	}

	@Override
	public void delete(String cid) throws Exception {
		dao.delete(cid);
		jedis=JedisUtils.getJedis();
		jedis.del("allCats");
		jedis.close();
	}

}
