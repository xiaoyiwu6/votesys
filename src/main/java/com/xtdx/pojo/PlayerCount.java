package com.xtdx.pojo;

/**
 * @program: online-voting-system
 * @description: 整合player和计票
 * @author: LEO
 * @create: 2021-05-18 03:15
 **/
public class PlayerCount {
    private Player player;
    private SessionPlayer sessionPlayer;

    @Override
    public String toString() {
        return "playerCount{" +
                "player=" + player +
                ", sessionPlayer=" + sessionPlayer +
                '}';
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public SessionPlayer getSessionPlayer() {
        return sessionPlayer;
    }

    public void setSessionPlayer(SessionPlayer sessionPlayer) {
        this.sessionPlayer = sessionPlayer;
    }

    public PlayerCount() {
    }

    public PlayerCount(Player player, SessionPlayer sessionPlayer) {
        this.player = player;
        this.sessionPlayer = sessionPlayer;
    }
}
