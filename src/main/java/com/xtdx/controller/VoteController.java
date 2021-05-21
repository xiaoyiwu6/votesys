package com.xtdx.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.xtdx.encryption.RSA;
import com.xtdx.pojo.*;
import com.xtdx.service.PlayerService;
import com.xtdx.service.SessionService;
import com.xtdx.service.UserService;
import com.xtdx.utils.QRCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.xtdx.service.VoteService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
public class VoteController {
	@Autowired
	private VoteService voteService;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private UserService userService;
	@Autowired
	private PlayerService playerService;

	//进行投票
	@RequestMapping("/votePlayer")
	@ResponseBody
	public String votePlayer(SessionCount sessionCount){
		//检查是否投过票
		SessionCount checkSess = voteService.selectSessionCount(sessionCount);
		if(checkSess!=null){
			return "0";
		}
		//明文选票
		String plainBallot = sessionCount.toString();
		//签名票据
		KeyPairs keyPairs = userService.selectKeyPairsByUserId(sessionCount.getUserId());
		String signBallot = RSA.sign(plainBallot,keyPairs.getRsaPriKey());
		//设置类
		sessionCount.setBallot(signBallot);
		//插入
		int i = voteService.insertSessionCount(sessionCount);
		if(i>0){
			//更新票数
			sessionService.updateCount(sessionCount.getSessionId(),sessionCount.getPlayerId());
			return "1";
		}
		return "0";
	}

	//验证投票页面
	@RequestMapping("/checkBallot")
	public String checkBallot(Model model, HttpSession httpSession){
		Session curSession = sessionService.getCurSession();
		User user = (User) httpSession.getAttribute("curUser");
		SessionCount sessionCount = userService.getSessionCountByUserIdAndSessionId(user.getUserId(),curSession.getSessionId());
		System.out.println(user.toString());
		if(sessionCount!=null){
			Player player = playerService.getPlayerById(sessionCount.getPlayerId());
			model.addAttribute("player",player);
			System.out.println(sessionCount.toString());
			System.out.println(player.toString());
		}
		model.addAttribute("sessionCount",sessionCount);
		return "checkBallot";
	}

	//验证投票
	@RequestMapping("/checkBallot/check")
	@ResponseBody
	public String check(@RequestParam("ballot") MultipartFile ballot, @RequestParam("sessionId") int sessionId, @RequestParam("userId")int userId, @RequestParam("playerId" )int playerId) {
		try{
			SessionCount sessionCount = new SessionCount();
			sessionCount.setSessionId(sessionId);
			sessionCount.setPlayerId(playerId);
			sessionCount.setUserId(userId);
			//处理成临时文件
			String fileNameBallot = ballot.getOriginalFilename();
			//获取文件后缀
			String prefix = fileNameBallot.substring(fileNameBallot.lastIndexOf("."));
			//防止重复名
			final File excelFile = File.createTempFile(String.valueOf(UUID.randomUUID()),prefix);
			//转为File类型
			ballot.transferTo(excelFile);
			//读取文件内容
			String sign = QRCodeUtil.readQRCodeBaseBufferedImage(excelFile);
			//获取私钥
			KeyPairs keyPairs = userService.selectKeyPairsByUserId(sessionCount.getUserId());
			//验证结果
			boolean result = RSA.verify(sessionCount.toString(),keyPairs.getRsaPubKey(),sign);

			//程序结束，删除临时文件
			excelFile.delete();
			System.out.println("验证结果-->"+result);

			if(result){
				return "1";
			}else{
				return "0";
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		return "0";
	}
	



	
}
