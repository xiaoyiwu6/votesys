package com.xtdx.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtdx.pojo.Player;
import com.xtdx.pojo.SessionTable;

@Repository
public interface SessionDao {
	public boolean addSessionTable(SessionTable sessionTable);//向sessiontable表中插入比赛
	public List<SessionTable> getSessionTable(int nowNum);//查询sessiontable表的全部信息
	public int getgameNow();//查看比赛状态是否开始
	public boolean beginGame(String nowSession);//修改本次比赛状态为1，开始
	public boolean oversGame(String nowSession);//修改本次比赛状态为2，结束
	public int getgameNowBynowSession(String nowSession);//查看本次比赛的状态，主要是开是否结束
	public boolean stopsGame(String nowSession);//修改本次比赛状态为3，暂停
	public int getWhetherTheEntry(Player player);//查询选手是否参赛
	public int getNowField(int wheel);//查询本轮比赛已有场数
	public int nowSessionRepeat(String nowSession);//查询数据库sessiontable表，传入拼凑出的nowSession对比是否有相同的字段
	public int getValueAByNowSession(String nowSession);//查询选手A的票数
	public int getValueBByNowSession(String nowSession);//查询选手B的票数
	public SessionTable getSessionTableByNowSession(String nowSession);//查询sessiontable表，传入sessiontable
	public boolean updateVoteAndWinner(SessionTable sessionTable);//插入票数和winner
	public List<SessionTable> getAllSessionTable();//查看完成比赛的所有信息
}
