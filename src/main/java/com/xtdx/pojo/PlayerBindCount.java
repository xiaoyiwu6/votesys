package com.xtdx.pojo;

/**
 * @program: online-voting-system
 * @description: 将票数和候选人结合的实体类，用于联合查表
 * @author: LEO
 * @create: 2021-05-16 05:42
 **/
public class PlayerBindCount {
    private Player player;
    private int sessionId;
    private int playerId;

    @Override
    public String toString() {
        return "PlayerBindCount{" +
                "player=" + player +
                ", sessionId=" + sessionId +
                ", playerId=" + playerId +
                ", count=" + count +
                '}';
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public PlayerBindCount(Player player, int sessionId, int playerId, int count) {
        this.player = player;
        this.sessionId = sessionId;
        this.playerId = playerId;
        this.count = count;
    }

    private int count;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public PlayerBindCount() {
    }

}
