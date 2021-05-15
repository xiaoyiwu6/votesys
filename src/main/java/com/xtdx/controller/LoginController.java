package com.xtdx.controller;

import com.xtdx.pojo.User;
import com.xtdx.utils.CheckRole;
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
@SessionAttributes(value = "curUser")
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserService userService;

	//首页
	@RequestMapping("/index")
	public String index(HttpSession session){
		if(CheckRole.checkLoginAndRole(session)>=0){
			return "index";
		}else{
			return "redirect:login.do";
		}
	}
	//用户注册
	@RequestMapping("/signIndex")
	public String signIndex(){ return "sign_up";}
	@RequestMapping("/signUp")
	public String sign(User user,Model model){
		int i = loginService.sign(user);
		if(i>0){
			System.out.println(user.getLevel());
			model.addAttribute("deal","1");
			return "redirect:login.do";
		}else{
			model.addAttribute("deal","0");
			return "sign_up";
		}
	}

	//用于访问登陆页面，面向管理员和选民
		@RequestMapping("/login")
		public String login(HttpSession session){
		if(CheckRole.checkLoginAndRole(session)>=0){
			return "redirect:index.do";
		}
		return "login";
		}

	@RequestMapping("submit")
	public String login_submit(User user, Model model){
		//System.out.println(user.toString());
		boolean result = loginService.execute(user.getAccount());
		User data = userService.getUserByAccount(user.getAccount());
		if(result){
			model.addAttribute("curUser",data);
			model.addAttribute("isLogin","1");
			return "index";
		}else{
			model.addAttribute("deal","0");
			return "redirect:login";

		}
	}
}
