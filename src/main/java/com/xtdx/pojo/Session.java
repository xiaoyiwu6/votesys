package com.xtdx.pojo;

/**
 * @program: online-voting-system
 * @description: 投票活动实体类
 * @author: LEO
 * @create: 2021-05-15 16:15
 **/
public class Session {
    private int sessionId;
    private String sessionName;

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                ", sessionName='" + sessionName + '\'' +
                ", state=" + state +
                ", create='" + create + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }

    public Session(int sessionId, String sessionName, int state, String create, String start, String end) {
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.state = state;
        this.create = create;
        this.start = start;
        this.end = end;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    private int state;
    private String create;
    private String start;
    private String end;

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

}
