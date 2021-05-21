package com.xtdx.pojo;

/**
 * @program: online-voting-system
 * @description: 记录票数
 * @author: LEO
 * @create: 2021-05-16 05:36
 **/
public class SessionPlayer {
    private int sessionId;
    private int playerId;
    private int count;

    @Override
    public String toString() {
        return "SessionPlayer{" +
                "sessionId=" + sessionId +
                ", playerId=" + playerId +
                ", count=" + count +
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public SessionPlayer() {
    }

    public SessionPlayer(int sessionId, int playerId, int count) {
        this.sessionId = sessionId;
        this.playerId = playerId;
        this.count = count;
    }



    public String getRSAEncryptedStr() {
        return null;
    }

    public String getRSAPublicKey() {
        return null;
    }

    public String getRSABallot() {        return null;

    }

    public String getECCEncryptedStr() {        return null;

    }

    public String getECCPublicKey() {        return null;

    }

    public String getECCBallot() {        return null;

    }
}
