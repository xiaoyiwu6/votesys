package com.xtdx.controller;

import com.xtdx.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.xtdx.service.LoginService;


import com.xtdx.service.*;
import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//登录控制

@Controller
@SessionAttributes("curUser")
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserService userService;
	
	//用户注册
	@RequestMapping("/signUp")
	@ResponseBody
	public String sign(User user,Model model){
		int i = loginService.sign(user);
		if(i>0){
			model.addAttribute("deal",true);
			return "login";
		}
		return "sign_up";
	}

	//用于访问登陆页面，面向管理员和选民
		@RequestMapping("/login")
		public String login(){
			return "login";
		}

	@RequestMapping("submit")
	public String login_submit(User user, HttpSession session){
		//System.out.println(user.toString());
		boolean result = loginService.execute(user.getAccount());
		if(result){
			session.setAttribute("curUser",user);
			return "index";
		}
		return "login";
	}
}
