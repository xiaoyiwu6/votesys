package com.xtdx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtdx.dao.UserDao;
import com.xtdx.pojo.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public User getUser(String keywords) {
		return userDao.getUser(keywords);
	}

	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}

	public boolean deleteUser(int userId) {
		return userDao.deleteUser(userId);
	}

	//根据用户名得到用户
	public User getUserByAccount(String account){return userDao.getUserByAccount(account);}
}
