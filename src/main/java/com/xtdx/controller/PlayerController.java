package com.xtdx.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xtdx.pojo.Player;
import com.xtdx.service.PlayerService;



@Controller
public class PlayerController {
	@Autowired
	private PlayerService playerservice;
	
	@RequestMapping("/PlayerManagement")
	public String PlayerManagement(Model model){
		List<Player> players=playerservice.getAllPlayers();
		model.addAttribute("players",players);
		return "PlayerManagement";
	}
	
	@RequestMapping("/addPlayer")
	public String addPlayer(){
		return "addPlayer";
	}
	@RequestMapping("/addPlayer/do")
	@ResponseBody
	public String add(HttpServletRequest request,@RequestParam("bigImg") MultipartFile bigImg,
			@RequestParam("smallImg") MultipartFile smallImg,
			@RequestParam("state") int state,@RequestParam("dateOfBirth")  String dateOfBirth,
			@RequestParam("num")int num,@RequestParam("sex")int sex,
			@RequestParam("playername")String playername)throws IOException {
		//获取文件名
		String filenamebig = bigImg.getOriginalFilename();
		String filenamesmall =smallImg.getOriginalFilename();
		//获取文件保存到服务器上的地址
		String bigAddress=request.getSession().getServletContext().getRealPath("/upload/")+filenamebig;
		String smallImgAddress=request.getSession().getServletContext().getRealPath("/upload/")+filenamesmall;
		File f = new File(bigAddress);
		//判断upload文件夹是否存在，如果不存在则创建
		if(!f.getParentFile().exists()){
		f.getParentFile().mkdirs();
		}
		File f2= new File(smallImgAddress);
		//将上传的文件传输到指定路径
		bigImg.transferTo(f);
		smallImg.transferTo(f2);
		
		Player p=new Player();
		p.setDateOfBirth(dateOfBirth);
		p.setNum(num);
		p.setPlayername(playername);
		p.setSex(sex);
		p.setState(state);
		p.setSmallImg("/OnlineVoteSystem/upload/"+filenamesmall);
		p.setPicAddress("/OnlineVoteSystem/upload/"+filenamebig);
		int i=playerservice.addPlayer(p);
		if(i>0){
			return "1";
		}else{
			return "0";
		}
	}
	
	@RequestMapping("/editPlayer/{playerId}")
	public String editPlayer(@PathVariable("playerId") int playerId,Model model){
		Player p=playerservice.getPlayerById(playerId);
		model.addAttribute("player",p);
		return "editPlayer";
	}
	
	@RequestMapping("/editPlayer/update")
	@ResponseBody
	public String updatePlayer(HttpServletRequest request,@RequestParam("bigImg") MultipartFile bigImg,
			@RequestParam("smallImg") MultipartFile smallImg,
			@RequestParam("state") int state,@RequestParam("dateOfBirth")  String dateOfBirth,
			@RequestParam("num")int num,@RequestParam("sex")int sex,
			@RequestParam("playernName")String playerName,
			@RequestParam("playerId")int playerId)throws IOException {
		Player p=new Player();
		p.setDateOfBirth(dateOfBirth);
		p.setNum(num);
		p.setPlayername(playerName);
		p.setSex(sex);
		p.setState(state);
		p.setPlayerId(playerId);
		String s=bigImg.toString(); 
		String s1=smallImg.getName();
		String filenamebig = bigImg.getOriginalFilename();
		String filenamesmall =smallImg.getOriginalFilename();
		if(!filenamebig.isEmpty()){
			//String filenamebig = bigImg.getOriginalFilename();
			String bigAddress=request.getSession().getServletContext().getRealPath("/upload/")+filenamebig;
			File f = new File(bigAddress);
			if(!f.getParentFile().exists()){
				f.getParentFile().mkdirs();
				}
			bigImg.transferTo(f);
			p.setPicAddress("/OnlineVoteSystem/upload/"+filenamebig);
		}
		if(!filenamesmall.isEmpty()){
			String smallImgAddress=request.getSession().getServletContext().getRealPath("/upload/")+filenamesmall;
			File f2= new File(smallImgAddress);
			if(!f2.getParentFile().exists()){
				f2.getParentFile().mkdirs();
				}
			smallImg.transferTo(f2);
			p.setSmallImg("/OnlineVoteSystem/upload/"+filenamesmall);
		}		
		int i=playerservice.updatePlayer(p);
		if(i>0){
			return "1";
		}else{
			return "0";
		}
	}
	@RequestMapping("/delPlayer")
	@ResponseBody
	public String delete(int playerId){
		int i=playerservice.deletePlayer(playerId);
		if(i>0){
			return "1";
		}else{
			return "0";
		}
	}
	
}
