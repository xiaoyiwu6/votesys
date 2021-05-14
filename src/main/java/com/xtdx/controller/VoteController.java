package com.xtdx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xtdx.pojo.Player;
import com.xtdx.pojo.SessionTable;
import com.xtdx.pojo.SingleCount;
import com.xtdx.service.VoteService;

@Controller
public class VoteController {
	@Autowired
	private VoteService voteservice;
	
	@RequestMapping("/vote/{userId}")
	public String Vote(Model model,@PathVariable("userId") int userId){
		//gameNow 为投票状态,1表示开启投票
		int gameNow=1;
		SessionTable st=voteservice.getPkSession(gameNow);
		List<Player> players=voteservice.getPkPlayers(st);
		int type=voteservice.getUserTypeByUserId(userId);
		model.addAttribute("players",players);
		model.addAttribute("userId",userId);
		model.addAttribute("type",type);
		return "vote";
	}
	@RequestMapping("/vote/addTicket")
	@ResponseBody
	public String AddTicket(SingleCount sc){
		//gameNow 为投票状态,1表示开启投票
		int gameNow=1;
		//取得场次
		String nowSession=voteservice.getNowSession(gameNow);
		if(nowSession!=null){
			sc.setNowSession(nowSession);
			//检查观众是否已经投票
			int ck=voteservice.checkUserAddTicket(sc);
			//已经投过票则返回0
			if(ck>0){
				return "0";
			}
			int i=voteservice.addTicket(sc);
			if(i>0){
				return "1";
			}
		}
		return "0";	
	}
	@RequestMapping("/vote/checkTicket")
	@ResponseBody
	public String checkTicket(){
		//gameNow 为投票状态,1表示开启投票
		int gameNow=1;
		//取得投票状态为开启的场次
		String nowSession=voteservice.getNowSession(gameNow);
		//判断投票状态,如果有开启的场次就进行展示
		if(nowSession!=null){
			int votesContrastA=voteservice.getVotesContrastA(nowSession);
			int votesContrastB=voteservice.getVotesContrastB(nowSession);
			SessionTable st=new SessionTable();
			st.setVotesContrastA(votesContrastA);
			st.setVotesContrastB(votesContrastB);
			String jsonString=JSON.toJSONString(st);
			return jsonString;
		}
		return "0";
		
	}
	@RequestMapping("/changeToVote")
	@ResponseBody
	public String ChangeToVote(int userId){
		//gameNow 为投票状态,1表示开启投票
		int gameNow=1;
		//取得投票状态为开启的场次
		String nowSession=voteservice.getNowSession(gameNow);
		//判断投票状态,如果有开启的场次就进行展示
		if(nowSession!=null){
			return "1";	
		}
		return "0";
	}
	
}
