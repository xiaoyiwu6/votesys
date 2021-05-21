package com.xtdx.service;

import java.util.List;

import com.xtdx.pojo.Player;
import com.xtdx.pojo.SessionCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtdx.dao.VoteDao;
import com.xtdx.pojo.SessionTable;
import com.xtdx.pojo.SingleCount;

@Service
public class VoteService {
	@Autowired
	private VoteDao voteDao;	
	public int addTicket(SingleCount sc){
		return voteDao.addTicket(sc);
	}
	public String getNowSession(int gameNow){
		return voteDao.getNowSession(gameNow);
	}
	public int getVotesContrastA(String nowSession){
		return voteDao.getVotesContrastA(nowSession);
	}
	public int getVotesContrastB(String nowSession){
		return voteDao.getVotesContrastB(nowSession);
	}
	public SessionTable getPkSession(int gameNow){
		return voteDao.getPkSession(gameNow);
	}
	public List<Player> getPkPlayers(SessionTable st){
		return voteDao.getPkPlayers(st);
	}
	public int updateVotesContrast(SessionTable st){
		return voteDao.updateVotesContrast(st);
	}
	public int checkUserAddTicket(SingleCount sc){
		return voteDao.checkUserAddTicket(sc);
	}
	public int getUserTypeByUserId(int userId){
		return voteDao.getUserTypeByUserId(userId);
	}
	public int insertSessionCount(SessionCount sessionCount){return voteDao.insertSessionCount(sessionCount);}
	public SessionCount selectSessionCount(SessionCount sessionCount){return voteDao.selectSessionCount(sessionCount);}


}
