package com.store.service;

import com.store.domain.User;

public interface UserService {
	//注册
	void userRegist(User user) throws Exception;
	//激活
	Boolean userActive(String code) throws Exception;
	//登录
	User userLogin(User u) throws Exception;
	//检测用户名是否存在
	User findByUsername(String username) throws Exception;
	
}
