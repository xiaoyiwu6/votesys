package com.xtdx.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xtdx.encryption.RSA;
import com.xtdx.pojo.KeyPairs;
import com.xtdx.pojo.Player;
import com.xtdx.pojo.SessionCount;
import com.xtdx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xtdx.service.PlayerService;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@Controller
public class PlayerController {
	@Autowired
	private PlayerService playerservice;
	@Autowired
	private UserService userService;
	
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

	@RequestMapping("/editPlayer/{playerId}")
	public String editPlayer(@PathVariable("playerId") int playerId,Model model){
		Player p=playerservice.getPlayerById(playerId);
		model.addAttribute("player",p);
		return "editPlayer";
	}

	//测试用
	@RequestMapping("/editPlayer/update2")
	@ResponseBody
	public String test(HttpServletRequest request,@RequestParam("bigImg") MultipartFile bigImg,
					 @RequestParam("smallImg") MultipartFile smallImg,
					 @RequestParam("state") int state,@RequestParam("dateOfBirth")  String dateOfBirth,
					 @RequestParam("num")int num,@RequestParam("sex")int sex,
					 @RequestParam("playerName")String playerName,
					 @RequestParam("playerId")int playerId,
					   @RequestParam("slogan") String slogan,
					   @RequestParam("info") String info)throws IOException {
		if(request instanceof MultipartHttpServletRequest){
			System.out.println("request is MultipartHttpServletRequest");
		}
		return "1";
	}
	
	@RequestMapping("/editPlayer/update")
	@ResponseBody
	public String updatePlayer(HttpServletRequest request,@RequestParam("bigImg") MultipartFile bigImg,
			@RequestParam("smallImg") MultipartFile smallImg,
			@RequestParam("state") int state,@RequestParam("dateOfBirth")  String dateOfBirth,
			@RequestParam("num")int num,@RequestParam("sex")int sex,
			@RequestParam("playerName")String playerName,
			@RequestParam("playerId")int playerId,
			@RequestParam("slogan") String slogan,
			@RequestParam("info") String info) {
		Player p= new Player();
		p.setDateOfBirth(dateOfBirth);
		p.setNum(num);
		p.setPlayerName(playerName);
		p.setSex(sex);
		p.setState(state);
		p.setPlayerId(playerId);
		p.setSlogan(slogan);
		p.setInfo(info);
		System.out.println(p.toString());
		String fileNameBig = bigImg.getOriginalFilename();
		String fileNameSmall =smallImg.getOriginalFilename();
		if(!fileNameBig.isEmpty()){
			//String fileNameBig = bigImg.getOriginalFilename();
			String bigAddress=request.getSession().getServletContext().getRealPath("/")+"/upload/"+fileNameBig;
			File f = new File(bigAddress);
			System.out.println("Big Image Path --> "+f.getAbsolutePath());
			if(!f.getParentFile().exists()){
				f.getParentFile().mkdirs();
				}
			try{
				bigImg.transferTo(f);
			}catch (Exception e){
				e.printStackTrace();
			}
			p.setPicAddress("/springmvc/upload/"+fileNameBig);
		}
		if(!fileNameSmall.isEmpty()){
			String smallImgAddress=request.getSession().getServletContext().getRealPath("/")+"/upload/"+fileNameSmall;
			File f2= new File(smallImgAddress);
			System.out.println("Small Image Path --> "+f2.getAbsolutePath());
			if(!f2.getParentFile().exists()){
				f2.getParentFile().mkdirs();
				}
			try {
				smallImg.transferTo(f2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			p.setSmallImg("/springmvc/upload/"+fileNameSmall);
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
	//添加候选人
	@RequestMapping("/addPlayer/add")
	@ResponseBody
	public String add(Player player){
		int i=playerservice.addPlayer(player);
		if(i>0){
			return "1";
		}else{
			return "0";
		}
	}
	//对候选人选票进行验票
	@RequestMapping("/getCount/checkAndCount")
	@ResponseBody
	public String checkAndCount(@RequestParam("sessionId") int sessionId, @RequestParam("playerId") int playerId){
//		System.out.println(sessionId);
//		System.out.println(playerId);
		//计数器
		int count=0;
		List<SessionCount> sessionCounts = playerservice.checkAndCount(sessionId, playerId);
		Iterator<SessionCount> iterator = sessionCounts.iterator();
		//循环
		while (iterator.hasNext()){
			//获取当前选票
			SessionCount temple = iterator.next();
			//获取相应选民私钥
			KeyPairs keyPairs = userService.selectKeyPairsByUserId(temple.getUserId());
			if(RSA.verify(temple.toString(),keyPairs.getRsaPubKey(),temple.getBallot())){
				count++;
			}
		}
		//更新选票
		playerservice.updatePlayerCount(sessionId,playerId,count);
		return  String.valueOf(count);
	}
}
