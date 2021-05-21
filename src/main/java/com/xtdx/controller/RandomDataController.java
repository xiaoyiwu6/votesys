package com.xtdx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.xtdx.service.UserService;

@Controller
public class RandomDataController {
	@Autowired
	private UserService uservice;
	
	@RequestMapping("/RandomData")
	public String RandomData(){
		return "RandomData";
	}
	

	@RequestMapping("/back")
	public String back() {
		return "login_succ";
	}
}
