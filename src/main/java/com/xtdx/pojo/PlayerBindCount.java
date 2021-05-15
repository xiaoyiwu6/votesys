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
    private int count;

    @Override
    public String toString() {
        return "PlayerBindCount{" +
                "player=" + player +
                ", sessionId=" + sessionId +
                ", count=" + count +
                '}';
    }

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

    public PlayerBindCount(Player player, int sessionId, int count) {
        this.player = player;
        this.sessionId = sessionId;
        this.count = count;
    }
}
