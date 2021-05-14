package com.xtdx.service;

import com.xtdx.dao.UserDao;
import com.xtdx.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service
public class LoginService {

	@Autowired
	private UserDao userDao;

	//返回注册结果
	public int sign(User user){return userDao.addUser(user);}
	
	//判断用户是否登陆成功
	public boolean execute(String account){
		User user = userDao.getUserByAccount(account);
		if(user ==null){
			return false;
		}
		return true;
	}



}
