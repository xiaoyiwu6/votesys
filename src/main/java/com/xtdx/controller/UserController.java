package com.xtdx.controller;

import java.util.List;

import com.xtdx.pojo.Player;
import com.xtdx.pojo.Session;
import com.xtdx.pojo.SessionCount;
import com.xtdx.utils.QRCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtdx.pojo.User;
import com.xtdx.service.*;

import javax.servlet.http.HttpSession;


//用户控制
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private PlayerService playerService;

	@RequestMapping("/addUser")
	public String addUser() {
		return "RandomData";
	}
	@RequestMapping("/userManagement")
	public String userList(Model model){
		//获取员工数据
		List<User> list= userService.getAllUsers();
		model.addAttribute("users",list);
		return "userManagement";
	}

	//登出
	@RequestMapping("/signOut")
	public String signOut(HttpSession session){
		session.removeAttribute("curUser");
		return "redirect:index.do";
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

	//获取用户选票,我的选票界面
	@RequestMapping("/vote")
	public String getVote(HttpSession httpSession, Model model){
		Session curSession = sessionService.getCurSession();
		User user = (User) httpSession.getAttribute("curUser");
		SessionCount sessionCount = userService.getSessionCountByUserIdAndSessionId(user.getUserId(),curSession.getSessionId());
		System.out.println(user.toString());
		if(sessionCount!=null){
			//回显我的选票信息
			Player player = playerService.getPlayerById(sessionCount.getPlayerId());
			model.addAttribute("player",player);
			System.out.println(sessionCount.toString());
			System.out.println(player.toString());

			//回显二维码
			model.addAttribute("qrSrc", QRCodeUtil.getQRCodeBase64(sessionCount.getBallot(),300,300));
		}
		model.addAttribute("sessionCount",sessionCount);
		return "myVote";
	}




}
