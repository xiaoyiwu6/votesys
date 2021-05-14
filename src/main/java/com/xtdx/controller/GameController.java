package com.xtdx.controller;

import java.util.List;
import java.util.Random;

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
	
	@RequestMapping("/playerContent")
	public String userList(){
		return "games";
	}
	
	@RequestMapping("/generateTheGameTable")
	public String sessiontables(Model model) {
		//获取选手信息并保存在session中
				//List<player> list= playerService.getPlayers();
				//model.addAttribute("players",list);
				//获取选手总数
				//int num=playerService.getPlayerNum();
				
				//获取当前比赛的轮数
				int nowNum=playerService.getPlayerNumMin();
				//根据轮数查询本轮选手总数
				int num=playerService.getPlayerNumByNum(nowNum);
				
				//进行本轮选手的匹配
				if(num%2==1) {
					//随机到的一位选手进行轮空晋级
					//生成随机数,a,b为随机选出的选手
					Random a=new Random();
					int b;
					b=a.nextInt(num)+1;
					//System.out.println(a+":"+b);
					//int a=(int)(Math.random()*num);
					
					//寻找所有轮数为nowNum,且为被淘汰的选手
					List<Player> liss=playerService.byNumGetPlayer(nowNum);
					//利用liss生成存储选手id的数组，然后用b在数组中取id让选出的选手晋级
					int[] nowIds=new int[num];
					int f=0;
					for (Player pla : liss) {
//						System.out.println(pla.getPlayername()+"sss");
						nowIds[f]=pla.getPlayerId();
						f++;
						}
//					System.out.println(nowIds[b]);
					boolean s=playerService.updatePlayerNum(nowIds[b]);
					//创建一个存储本轮选手id的数组
					int[] nowId=new int[num];
					if(s==true) {
						//获取当前比赛的轮数
						//int nowNum=playerService.getPlayerNumMin();
						//寻找所有轮数为nowNum的选手
						List<Player> lis=playerService.byNumGetPlayer(nowNum);
						//将选手信息保存到session中
						//model.addAttribute("players",lis);
						
						//为nowId存入选手id
						int j=0;
						for (Player pla : lis) {
//							System.out.println(pla.getPlayername()+"sss");
							nowId[j]=pla.getPlayerId();
							j++;
							}
						
//						for(int k=0;k<nowId.length;k++) {
//							System.out.println(nowId[k]);
//						}
						//拼凑nowSession字段
						for(int g=0;g<nowId.length/2;g++) {
							String nowSess;
							nowSess=Integer.toString(nowNum+1);
							if(g<10) {
								nowSess+="0";
								nowSess+="0";
								nowSess+=Integer.toString(g+1);
							}else if(g>=10&&g<100) {
								nowSess+="0";
								nowSess+=Integer.toString(g/10);
								nowSess+=Integer.toString(g%10+1);
							}else if(g>=100&&g<999) {
								nowSess+=Integer.toString(g/100);
								nowSess+=Integer.toString((g%100)/10);
								nowSess+=Integer.toString(g%10+1);
							}else {
								System.out.println("请扩充数据库字段nowSession");
							}
							//查询数据库sessiontable表，传入拼凑出的nowSession对比是否有相同的字段
							int nowSessionRepeat=sessionService.nowSessionRepeat(nowSess);
							if(nowSessionRepeat>0) {
								return "games";
							}else {
								//创建sessionTable对象，调用addsessionTable方法
								SessionTable ses=new SessionTable();
								ses.setWheel(nowNum+1);
								ses.setPlayerA(nowId[g*2]);
								ses.setPlayerB(nowId[g*2+1]);
								ses.setNowSession(nowSess);
								sessionService.addSessionTable(ses);
							}
						}
						//读取sessiontable表，将sessiontable表中的内容存储到session中
						List<SessionTable> lists=sessionService.getSessionTable(nowNum+1);
						model.addAttribute("sessions",lists);
						return "games";
					}else {
						return "games";
					}
				}else {
					//寻找所有轮数为nowNum且未被淘汰的选手
					List<Player> lis=playerService.byNumGetPlayer(nowNum);
					//将选手信息保存到session中
//					model.addAttribute("players",lis);
					
					//创建一个存储本轮选手id的数组
					int[] nowIds=new int[num];
					
					//为nowId存入选手id
					int j=0;
					for (Player pla : lis) {
						//System.out.println(pla.getPlayername()+":"+pla.getPlayerId()+":"+j);
						nowIds[j]=pla.getPlayerId();
						j++;
						}
					
//					for(int k=0;k<nowId.length;k++) {
//						System.out.println(nowId[k]);
//					}
					//拼凑nowSession字段
					for(int g=0;g<nowIds.length/2;g++) {
						String nowSess;
						nowSess=Integer.toString(nowNum+1);
						if(g<10) {
							nowSess+="0";
							nowSess+="0";
							nowSess+=Integer.toString(g+1);
						}else if(g>=10&&g<100) {
							nowSess+="0";
							nowSess+=Integer.toString(g/10);
							nowSess+=Integer.toString(g%10+1);
						}else if(g>=100&&g<999) {
							nowSess+=Integer.toString(g/100);
							nowSess+=Integer.toString((g%100)/10);
							nowSess+=Integer.toString(g%10+1);
						}else {
							System.out.println("请扩充数据库字段nowSession");
						}
						//查询数据库sessiontable表，传入拼凑出的nowSession对比是否有相同的字段
						int nowSessionRepeat=sessionService.nowSessionRepeat(nowSess);
						if(nowSessionRepeat>0) {
							return "games";
						}else {
							//创建sessionTable对象，调用addsessionTable方法
							SessionTable ses=new SessionTable();
							ses.setWheel(nowNum+1);
							ses.setPlayerA(nowIds[g*2]);
							ses.setPlayerB(nowIds[g*2+1]);
							ses.setNowSession(nowSess);
							sessionService.addSessionTable(ses);
						}
					}
					//读取sessiontable表，将sessiontable表中的内容存储到session中
					List<SessionTable> lists=sessionService.getSessionTable(nowNum+1);
					model.addAttribute("sessions",lists);
					return "games";
				}
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
	public String addSession() {
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
