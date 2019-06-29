package com.store.service.impl;

import com.store.dao.UserDao;
import com.store.domain.User;
import com.store.service.UserService;
import com.store.utils.BeanFactory;

public class UserServiceImpl implements UserService{
	//解耦获取UserDao对象
	UserDao dao=(UserDao) BeanFactory.createDao("UserDao");
	User user=null;
	@Override
	public void userRegist(User user) throws Exception{
		dao.userRegis(user);
	}

	@Override
	public Boolean userActive(String code) throws Exception {
		
		//通过激活码查询到用户
		user=dao.userActive(code);
		if(null!=user) {
			//修改权限
			user.setState(1);
			//清空激活码
			user.setCode(null);
			//更新数据库
			dao.updateUser(user);
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public User userLogin(User u) throws Exception {
		
		user=dao.userLogin(u);
		if(null==user) {
			throw new RuntimeException("密码不正确！");
		}else if(user.getState()==0){
			throw new RuntimeException("用户未激活！");
		}else {			
			return user;
		}
	}

	@Override
	public User findByUsername(String username) throws Exception{
		user=dao.findByUsername(username);
		return user;
	}

}
