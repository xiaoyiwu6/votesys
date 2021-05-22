package com.xtdx.controller;

import com.xtdx.pojo.PlayerBindCount;
import com.xtdx.pojo.Session;
import com.xtdx.pojo.User;
import com.xtdx.utils.CheckRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.xtdx.service.LoginService;


import com.xtdx.service.*;

import javax.servlet.http.HttpSession;
import java.util.List;

//登录控制

@Controller
@SessionAttributes(value = "curUser")
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private SessionService sessionService;

	//首页
	@RequestMapping("/index")
	public String index(HttpSession session,Model model){
		if(CheckRole.checkLoginAndRole(session)>=0){
			//查询当前是否有正在进行的投票
			Session curSession = sessionService.getCurSession();
			//System.out.println(curSession.toString());
			if(curSession!=null){
				List<PlayerBindCount> curPlayersAndCount = sessionService.selectPlayerBindCount(curSession.getSessionId());
				model.addAttribute("curSession",curSession);
				model.addAttribute("curPlayers",curPlayersAndCount);
			}
			return "index";
		}else{
			return "redirect:login.do";
		}
	}
	//用户注册
	@RequestMapping("/signIndex")
	public String signIndex(){ return "sign_up";}
	@RequestMapping("/signUp")
	@ResponseBody
	public String sign(User user,Model model) throws Exception {
		System.out.println(user.toString());
		//注册
		int i = loginService.sign(user);
		int userId = userService.getUserByAccount(user.getAccount()).getUserId();
		user.setUserId(userId);
		//派生密钥
		userService.genKeyPairs(user.getUserId());
		if(i>0){
			System.out.println(user.getLevel());
			model.addAttribute("deal","1");
			return "1";
		}else{
			model.addAttribute("deal","0");
			return "0";
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
	@ResponseBody
	public String login_submit(User user, Model model){
		System.out.println(user.toString());
		boolean result = loginService.execute(user);
		User data = userService.getUserByAccount(user.getAccount());
		if(result){

			model.addAttribute("curUser",data);
			model.addAttribute("isLogin","1");
			return "1";
		}else{
			model.addAttribute("deal","0");
			return "0";
		}
	}
}
