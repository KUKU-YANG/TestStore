package com.store.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.store.dao.CategoryDao;
import com.store.domain.Category;
import com.store.utils.JDBCUtils;

public class CategoryDaoImpl implements CategoryDao{
	String sql=null;
	QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
	Category category=null;
	@Override
	public List<Category> getAllCats() throws Exception{
		sql="select * from category";
		List<Category> list=qr.query(sql, new BeanListHandler<Category>(Category.class));
		return list;
	}

	@Override
	public void addCategory(Category category) throws Exception {
		sql="insert into category values(?,?)";
		qr.update(sql,category.getCid(),category.getCname());
	}

	@Override
	public Category findById(String cid) throws Exception {
		sql="select * from category where cid=?";
		category=qr.query(sql, new BeanHandler<Category>(Category.class),cid);
		return category;
	}

	@Override
	public void edit(Category category) throws Exception {
		sql="update category set cname=? where cid=?";
		qr.update(sql,category.getCname(),category.getCid());
	}

	@Override
	public void delete(String cid) throws Exception {
		sql="delete from category where cid=?";
		qr.update(sql,cid);
	}

}
