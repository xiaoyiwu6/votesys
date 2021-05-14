package com.xtdx.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtdx.pojo.Player;
import com.xtdx.pojo.SessionTable;
import com.xtdx.pojo.SingleCount;

@Repository
public interface VoteDao {
	//投票
	public int addTicket(SingleCount sc);
	//获得当前场次
	public String getNowSession(int gameNow);
	//统计选手A票数
	public int getVotesContrastA(String nowSession);
	//统计选手B票数
	public int getVotesContrastB(String nowSession);
	//获取正在进行的pk信息
	public SessionTable getPkSession(int gameNow);
	//获取当前pk选手信息
	public List<Player> getPkPlayers(SessionTable st);
	//即时统计选手票数
	public int updateVotesContrast(SessionTable st);
	//检查观众是否已经投票
	public int checkUserAddTicket(SingleCount sc);
	//查找用户类型
	public int getUserTypeByUserId(int userId);
}
