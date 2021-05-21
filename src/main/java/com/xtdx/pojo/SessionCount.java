package com.xtdx.pojo;

/**
 * @program: online-voting-system
 * @description: 记录票据
 * @author: LEO
 * @create: 2021-05-16 05:38
 **/
public class SessionCount {
    private int sessionId;
    private int playerId;
    private int userId;
    private String ballot;

    public SessionCount() {
    }

    @Override
    public String toString() {
        return "SessionCount{" +
                "sessionId=" + sessionId +
                ", playerId=" + playerId +
                ", userId=" + userId +
                '}';
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBallot() {
        return ballot;
    }

    public void setBallot(String ballot) {
        this.ballot = ballot;
    }

    public SessionCount(int sessionId, int playerId, int userId, String ballot) {
        this.sessionId = sessionId;
        this.playerId = playerId;
        this.userId = userId;
        this.ballot = ballot;
    }
}
