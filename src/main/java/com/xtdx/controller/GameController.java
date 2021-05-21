package com.xtdx.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.xtdx.encryption.ECC;
import com.xtdx.encryption.RSA;
import com.xtdx.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.xtdx.service.*;

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
	
	@RequestMapping(value = "/beginGame", method = RequestMethod.POST)
	@ResponseBody
	public String beginGame(@RequestParam("sessionId") int sessionId) {
		System.out.println(sessionId);
		Session session = sessionService.getCurSession();
		//当前没有正在进行的活动
		if(session==null){
			Date now = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String start = ft.format(now);
			sessionService.beginSessionById(start,sessionId);
			return "1";
		}else{//有正在进行的活动
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
	//修改活动名
	@RequestMapping("/addSession/updateName")
	public String updateSessionName(String sessionName, int sessionId){
		sessionService.updateSessionNameBySessionId(sessionName,sessionId);
		return "redirect:playerContent.do";
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

	//结束当前活动
	@RequestMapping("/endSession")
	public String endCurSession(){
		Date now = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String end = ft.format(now);
		//插入结束日期
		sessionService.endCurSession(end);
		//清楚候选人状态
		playerService.cleanPlayerState();
		return "redirect:index.do";
	}

	@RequestMapping("/allSessions")
	public String allSessions(){

		return "allSessions";
	}

	//结束并计票
	@RequestMapping("/getCount")
	public String getCount(Model model) throws Exception {
		//获取所有参与本轮投票活动的候选人
		List<PlayerCount> playerCounts = playerService.selectAllPlayerCount();
//		//获取迭代器
//		Iterable<PlayerCount> playerCountIterable = (Iterable<PlayerCount>) playerCounts.iterator();
//		Iterator<PlayerCount> iterator = playerCountIterable.iterator();
//		//对所有候选人的票选进行计票验票
//		while(iterator.hasNext()){
//			//获取必要数据
//			SessionPlayer sessionPlayer = iterator.next().getSessionPlayer();
//			String RSAEncryptedStr = sessionPlayer.getRSAEncryptedStr();
//			String RSApublicKey = sessionPlayer.getRSAPublicKey();
//			String RSASign = sessionPlayer.getRSABallot();
//			String ECCEncryptedStr = sessionPlayer.getECCEncryptedStr();
//			String ECCpublicKey = sessionPlayer.getECCPublicKey();
//			String ECCSign = sessionPlayer.getECCBallot();
//			//RSA校验
//			boolean RSA_RESULT = RSA.verify(RSAEncryptedStr,RSApublicKey,RSASign);
//			//ECC校验
//			boolean ECC_RESULT = ECC.verify(ECCEncryptedStr,ECCpublicKey,ECCSign);
//			//检验是否通过并纳入计票
//			if(RSA_RESULT && ECC_RESULT){
//				sessionPlayer.setCount(sessionPlayer.getCount()+1);
//			}
//		}

		model.addAttribute("playerCounts",playerCounts);
		return "getCount";
	}
}
