package com.xtdx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtdx.pojo.User;
import com.xtdx.service.*;


//用户控制
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
//	@RequestMapping("/userManagement")
//	public String userManagement() {
//		return "userManagement";
//	}
//	
	
	@RequestMapping("/addUser")
	public String addUser() {
		return "RandomData";
	}
	@RequestMapping("/userManagement")
	public String userList(Model model){
		//获取员工数据
		List<User> list= userService.getUsers();
		model.addAttribute("users",list);
		return "userManagement";
	}
	
	@RequestMapping("/user/del")
	@ResponseBody
	public String delete(int id) {
		//删除操作
		boolean i = userService.deleteUser(id);
		if(i==true) {
			return "1";
		}else {
			return "0";
		}
	}
	@RequestMapping("/back1")
	public String back1() {
		return "login_succ";
	}
}
