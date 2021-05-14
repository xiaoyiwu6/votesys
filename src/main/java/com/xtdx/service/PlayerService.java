package com.xtdx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtdx.dao.PlayerDao;
import com.xtdx.pojo.Player;

@Service
public class PlayerService {
	@Autowired
	private PlayerDao playerDao;
	
	public List<Player> getAllPlayers(){
		return playerDao.getAllPlayers();
	}
	public Player getPlayerById(int playerId){
		return playerDao.getPlayerById(playerId);
	}
	public int addPlayer(Player player1){
		return playerDao.addPlayer(player1);
	}
	public int addEmployee(Player employee){
		return playerDao.addPlayer(employee);
	}
	public List<Player> getAllEmployee(){
		return playerDao.getAllPlayers();
	}

	public int updatePlayer(Player player1){
		return playerDao.updatePlayer(player1);
	}
	public int deletePlayer(int playerId){
		return playerDao.deletePlayer(playerId);
	}
	public List<Player> getPlayers(){//返回选手全部信息
		return playerDao.getPlayers();
	}
	
	public int getPlayerNum() {//返回选手总数
		return playerDao.getPlayerNum();
	}
	
	public boolean updatePlayerNum(int playerId) {
		return playerDao.updatePlayerNum(playerId);
	}
	
	public int getPlayerNumMin() {//获得当前比赛的轮数
		return playerDao.getPlayerNumMin();
	}
	
	public List<Player> byNumGetPlayer(int num){//利用num寻找选手信息，进行匹配
		return playerDao.byNumGetPlayer(num);
	}
	
	public Player getPlayerByPlayerId(int playerId) {//利用选手id查找选手
		return playerDao.getPlayerByPlayerId(playerId);
	}
	
	public int getPlayerNumByNum(int num) {//利用选手参赛场次num查询本轮比赛总选手数
		return playerDao.getPlayerNumByNum(num);
	}
	
	public int getStateByPlayerId(int playerId) {//查询选手是否被淘汰
		return playerDao.getStateByPlayerId(playerId);
	}
	
	public boolean updateStateByPlayerId(int playerId) {//修改被淘汰选手状态
		return playerDao.updateStateByPlayerId(playerId);
	}
}
