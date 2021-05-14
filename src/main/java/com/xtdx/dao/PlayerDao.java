package com.xtdx.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtdx.pojo.Player;

@Repository
public interface PlayerDao {
	public List<Player> getAllPlayers();//返回所有选手
	public Player getPlayerById(int playerId);//根据id返回选手
	public int addPlayer(Player player1);//添加选手
	public int updatePlayer(Player player1);//更新选手信息
	public int deletePlayer(int playerId);//删除选手
	public List<Player> getPlayers();//返回选手全部信息
	public int getPlayerNum();//返回选手总数
	public boolean updatePlayerNum(int playerId);//使选择的选手比赛轮数+1
	public int getPlayerNumMin();//获得当前比赛的轮数
	public List<Player> byNumGetPlayer(int num);//利用num寻找选手信息，进行匹配
	public Player getPlayerByPlayerId(int playerId);//利用选手id查找选手
	public int getPlayerNumByNum(int num);//利用选手参赛场次num查询本轮比赛总选手数
	public int getStateByPlayerId(int playerId);//查询选手是否被淘汰
	public boolean updateStateByPlayerId(int playerId);//修改被淘汰选手状态
}
