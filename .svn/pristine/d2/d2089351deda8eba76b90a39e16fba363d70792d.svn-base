package com.store.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.store.dao.UserDao;
import com.store.domain.User;
import com.store.utils.JDBCUtils;

public class UserDaoImpl implements UserDao{
	String sql=null;
	QueryRunner qr=null;
	User user=null;
	@Override
	public void userRegis(User user) throws Exception {
		sql="insert into user values(?,?,?,?,?,?,?,?,?,?)";
		qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params= {user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode()};
		qr.update(sql,params);
	}

	@Override
	public void updateUser(User user) throws Exception {
		sql="update user set username=?,password=?,name=?,email=?,telephone=?,birthday=?,sex=?,state=?,code=? where uid=?";
		qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params= {user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode(),user.getUid()};
		qr.update(sql ,params);
	}

	@Override
	public User userActive(String code) throws Exception {
		sql="select * from user where code=?";
		qr=new QueryRunner(JDBCUtils.getDataSource());
		user=qr.query(sql, new BeanHandler<User>(User.class),code);
		return user;
	}

	@Override
	public User userLogin(User u) throws Exception {
		sql="select * from user where username=? and password=?";
		qr=new QueryRunner(JDBCUtils.getDataSource());
		user=qr.query(sql, new BeanHandler<User>(User.class),u.getUsername(),u.getPassword());
		return user;
	}

	@Override
	public User findByUsername(String username) throws Exception {
		sql="select * from user where username=?";
		qr=new QueryRunner(JDBCUtils.getDataSource());
		user=qr.query(sql, new BeanHandler<User>(User.class),username);
		return user;
	}

}
