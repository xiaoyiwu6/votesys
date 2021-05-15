package com.xtdx.controller;

import java.util.List;

import com.xtdx.pojo.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.xtdx.pojo.Player;
import com.xtdx.pojo.SessionTable;
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
	
	@RequestMapping("/addSession")//跳转到addSession页面
	public String addSession(Model model) {
		Session curSession = sessionService.getCurSession();
		List<Player> players = sessionService.getAllPlayersBySessionId(curSession.getSessionId());
		model.addAttribute("players",players);

		return "addSession";
	}
	
	@RequestMapping("/addSessions")//在addSession页面，添加两选手id进行匹配
	@ResponseBody
	public String addSessions(SessionTable sessionTable) {
		//获取选手id,判断选手是否存在
		Player pa=playerService.getPlayerByPlayerId(sessionTable.getPlayerA());
		Player pb=playerService.getPlayerByPlayerId(sessionTable.getPlayerB());
		
		//返回选手是否参与本轮比赛了，0则没有，大于0则参与了比赛
		int aa=sessionService.getWhetherTheEntry(pa);
		int bb=sessionService.getWhetherTheEntry(pb);
		//返回选手是否被淘汰
		int at=playerService.getStateByPlayerId(sessionTable.getPlayerA());
		int bt=playerService.getStateByPlayerId(sessionTable.getPlayerB());
		if(pa==null||pb==null) {//判断选手存在否
			return "0";
		}else {
			if(pa.getNum()!=pb.getNum()) {//判断选手是否在同一轮
				return "0";
			}else {
				if(aa>0||bb>0) {//判断选手是否已经安排了比赛
					return "0";
				}else {
					if(at==1||bt==1) {
						return "0";
					}else {//通过判断可以插入数据进入数据库
						//查询数据库本轮比赛是已有几场
						int nowField=sessionService.getNowField(pa.getNum()+1);
						//拼凑nowSession字段
						String nowSess;
						nowSess=Integer.toString(pa.getNum()+1);
						if(nowField<10) {
							nowSess+="0";
							nowSess+="0";
							nowSess+=Integer.toString(nowField+1);
						}else if(nowField>=10&&nowField<100) {
							nowSess+="0";
							nowSess+=Integer.toString(nowField/10);
							nowSess+=Integer.toString(nowField%10+1);
						}else if(nowField>=100&&nowField<999) {
							nowSess+=Integer.toString(nowField/100);
							nowSess+=Integer.toString((nowField%100)/10);
							nowSess+=Integer.toString(nowField%10+1);
						}else {
							System.out.println("请扩充数据库字段nowSession");
						}
						//创建sessionTable对象，调用addsessionTable方法
						SessionTable ses=new SessionTable();
						ses.setWheel(pa.getNum()+1);
						ses.setPlayerA(pa.getPlayerId());
						ses.setPlayerB(pb.getPlayerId());
						ses.setNowSession(nowSess);
						sessionService.addSessionTable(ses);
						return "1";
					}
				}
			}
		}
	}
	@RequestMapping("/queryGameTablePass")//跳转到queryGameTablePass页面
	public String queryGameTablePass(Model model) {
		List<SessionTable> ses=sessionService.getAllSessionTable();
		model.addAttribute("ses",ses);
		return "queryGameTablePass";
	}
	
}
