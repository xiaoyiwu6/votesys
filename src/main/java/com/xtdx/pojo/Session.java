package com.xtdx.pojo;

/**
 * @program: online-voting-system
 * @description: 投票活动实体类
 * @author: LEO
 * @create: 2021-05-15 16:15
 **/
public class Session {
    private int sessionId;
    private int state;
    private String create;
    private String start;
    private String end;

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                ", state=" + state +
                ", create='" + create + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Session() {
    }

    public Session(int sessionId, int state, String create, String start, String end) {
        this.sessionId = sessionId;
        this.state = state;
        this.create = create;
        this.start = start;
        this.end = end;
    }
}
