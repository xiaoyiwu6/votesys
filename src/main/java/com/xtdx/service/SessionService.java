package com.xtdx.service;

import java.util.List;

import com.xtdx.dao.SessionDao;
import com.xtdx.pojo.Player;
import com.xtdx.pojo.Session;
import com.xtdx.pojo.SessionTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
	@Autowired
	private SessionDao sessionDao;
	
	public boolean addSessionTable(SessionTable sessionTable){//创建本次比赛
		return sessionDao.addSessionTable(sessionTable);
	}
	
	public List<SessionTable> getSessionTable(int nowNum){
		return sessionDao.getSessionTable(nowNum);
	}
	public boolean beginGame(String nowSession) {//修改本次比赛比赛状态为1，开始
		return sessionDao.beginGame(nowSession);
	}
	
	public int getgameNow() {//查看比赛状态是否开始
		return sessionDao.getgameNow();
	}
	
	public boolean oversGame(String nowSession) {//修改本次比赛状态为2，结束
		return sessionDao.oversGame(nowSession);
	}
	
	public int getgameNowBynowSession(String nowSession) {//查看本次比赛的状态，主要是开是否结束
		return sessionDao.getgameNowBynowSession(nowSession);
	}
	
	public boolean stopsGame(String nowSession) {//修改本次比赛状态为3，暂停
		return sessionDao.stopsGame(nowSession);
	}
	
	public int getWhetherTheEntry(Player player) {//查询选手是否参赛
		return sessionDao.getWhetherTheEntry(player);
	}
	
	public int getNowField(int wheel) {//查询本轮比赛已有场数
		return sessionDao.getNowField(wheel);
	}
	
	public int nowSessionRepeat(String nowSession) {//查询数据库sessiontable表，传入拼凑出的nowSession对比是否有相同的字段
		return sessionDao.nowSessionRepeat(nowSession);
	}
	
	public int getValueAByNowSession(String nowSession) {//查询选手A的票数
		return sessionDao.getValueAByNowSession(nowSession);
	}
	public int getValueBByNowSession(String nowSession) {//查询选手B的票数
		return sessionDao.getValueBByNowSession(nowSession);
	}
	
	public SessionTable getSessionTableByNowSession(String nowSession) {//查询sessiontable表，传入sessiontable
		return sessionDao.getSessionTableByNowSession(nowSession);
	}
	
	public boolean updateVoteAndWinner(SessionTable sessionTable) {//插入票数和winner
		return sessionDao.updateVoteAndWinner(sessionTable);
	}
	
	public List<SessionTable> getAllSessionTable(){//查看完成比赛的所有信息
		return sessionDao.getAllSessionTable();
	}

	public List<Player> getAllPlayersBySessionId(int sessionId){return sessionDao.getAllPlayersBySessionId(sessionId);}

	public Session getCurSession() {return sessionDao.getCurSession();}

	public List<Session> getAllSession(){return sessionDao.getAllSession();}
}
