package com.xtdx.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import com.xtdx.pojo.Session;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.xtdx.pojo.Player;
import com.xtdx.pojo.SessionTable;
import com.xtdx.service.*;

import javax.jws.WebParam;

//比赛控制
@Controller
@SessionAttributes("sessions")
public class GameController {
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private SessionService sessionService;

	//投票活动管理页
	@RequestMapping("/playerContent")
	public String userList(Model model){
		List<Session> allSession = sessionService.getAllSession();
		model.addAttribute("allSession",allSession);
		return "games";
	}
	
	@RequestMapping("/queryGameTable")
	public String queryGameTable(Model model) {
		int nowNum=playerService.getPlayerNumMin();
		List<SessionTable> lists=sessionService.getSessionTable(nowNum+1);
		model.addAttribute("sessions",lists);
		return "games";
	}
	
	@RequestMapping("/beginGame")
	@ResponseBody
	public String beginGame(String nowSession) {
		//获取sessiontable中比赛状态是否有开始比赛的
		int j=sessionService.getgameNow();
		//获取当前比赛状态是否是结束的
		int k=sessionService.getgameNowBynowSession(nowSession);
		if(j==0) {
			if(k!=2) {
				//开始比赛
				boolean i = sessionService.beginGame(nowSession);
				if(i==true) {
					return "1";
				}else {
					return "0";
				}
			}else {
				return "0";
			}
		}else {
			return "0";
		}
		
	}
	
	@RequestMapping("/oversGame")
	@ResponseBody
	public String oversGame(String nowSession) {
		//结束比赛
		int nowSessions=sessionService.getgameNowBynowSession(nowSession);
		if(nowSessions==2) {
			return "1";
		}else {
		//查询singlecount表，传入nowSession，返回选手票数。
		int va=sessionService.getValueAByNowSession(nowSession);//选手A的票数
		int vb=sessionService.getValueBByNowSession(nowSession);//选手B的票数
		System.out.println(va+":"+vb);
		if(va!=0||vb!=0) {
			//判断选手票数谁大谁小并录入sessiontable表,票数和winner
			if(va>vb) {
				//获取nowSession的信息
				SessionTable ss=sessionService.getSessionTableByNowSession(nowSession);
				//创建sessionTable对象，调用updateVoteAndWinner方法
				SessionTable ses=new SessionTable();
				ses.setWinner(ss.getPlayerA());
				ses.setVotesContrastA(va);
				ses.setVotesContrastB(vb);
				ses.setNowSession(nowSession);
				//修改票数和获胜者
				sessionService.updateVoteAndWinner(ses);
				
				
				//修改选手参赛场次
				playerService.updatePlayerNum(ss.getPlayerA());
				playerService.updatePlayerNum(ss.getPlayerB());
				//修改淘汰选手比赛状态
				playerService.updateStateByPlayerId(ss.getPlayerB());
				
			}else if(va<vb) {
				//获取nowSession的信息
				SessionTable ss=sessionService.getSessionTableByNowSession(nowSession);
				
				//创建sessionTable对象，调用updateVoteAndWinner方法
				SessionTable ses=new SessionTable();
				ses.setWinner(ss.getPlayerB());
				ses.setVotesContrastA(va);
				ses.setVotesContrastB(vb);
				ses.setNowSession(nowSession);
				//修改票数和获胜者
				sessionService.updateVoteAndWinner(ses);
				
				//修改选手参赛场次
				playerService.updatePlayerNum(ss.getPlayerA());
				playerService.updatePlayerNum(ss.getPlayerB());
				
				//修改淘汰选手比赛状态
				playerService.updateStateByPlayerId(ss.getPlayerA());
				
			}else if(va==vb){
				return "2";
			}
			//修改比赛状态
			boolean i = sessionService.oversGame(nowSession);
			if(i==true) {
				return "1";
			}else {
				return "0";
			}
		}else {
			return "2";
		}
		}
	}
	
	@RequestMapping("/stopsGame")
	@ResponseBody
	public String stopsGame(String nowSession) {
		//获取比赛是否结束
		int j=sessionService.getgameNowBynowSession(nowSession);
		if(j==1) {
			//暂停比赛
			boolean i = sessionService.stopsGame(nowSession);
			if(i==true) {
				return "1";
			}else {
				return "0";
			}
		}else {
			return "0";
		}
	}

	//创建新活动
	@RequestMapping("/addSession")
	public String addSession() {
		return "addSession";
	}
	@RequestMapping("/addSession/update")
	public String addSessionUpdate(String sessionName){
		Date now = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String create = ft.format(now);
		Session session = new Session();
		session.setSessionName(sessionName);
		session.setCreate(create);
		sessionService.insertSession(session);
		return "redirect:addSession.do";

	}

	//添加候选人到活动中
	@RequestMapping("/addSession/addPlayer/{sessionId}")
	public String addPlayerToSession(@PathVariable("sessionId") int sessionId, Model model){
		List<Player> absentPlayers = sessionService.getAbsentPlayersBySessionId(sessionId);
		List<Player> presentPlayers = sessionService.getPresentPlayersBySessionId(sessionId);
		model.addAttribute("absentPlayers",absentPlayers);
		model.addAttribute("presentPlayers",presentPlayers);
		model.addAttribute("sessionId",sessionId);
		return "addPlayerToSession";
	}
	@RequestMapping("/addSession/addPlayer/{sessionId}/{playerId}")
	@ResponseBody
	public void addPlayer(@PathVariable("sessionId") int sessionId,@PathVariable("playerId") int playerId){
		sessionService.insertPlayerToSession(sessionId,playerId);
	}

	//投票
	@RequestMapping("/votePlayer")
	@ResponseBody
	public int vote(@RequestParam("sessionId") int sessionId,
					   @RequestParam("playerId") int playerId,
					   @RequestParam("userId") int userId){
		//TODO: 算法部分待完成以生成ballot
		String ballot = "票据";
		//生成票据
		sessionService.votePlayer(sessionId,playerId,userId,ballot);
		//投票计数
		sessionService.updateCount(sessionId,playerId);
		//查询票数
		return sessionService.selectCount(sessionId,playerId);
	}
}
