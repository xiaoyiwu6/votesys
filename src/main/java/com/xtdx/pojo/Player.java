package com.xtdx.pojo;

/**
 * @program: online-voting-system
 * @description: 候选人
 * @author: LEO
 * @create: 2021-05-17 23:42
 **/
public class Player {
    private int playerId;
    private String playerName;
    private int state;
    private int num;
    private String picAddress;
    private String dateOfBirth;
    private String smallImg;
    private int sex;
    private String slogan;
    private String info;

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", playerName='" + playerName + '\'' +
                ", state=" + state +
                ", num=" + num +
                ", picAddress='" + picAddress + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", smallImg='" + smallImg + '\'' +
                ", sex=" + sex +
                ", slogan='" + slogan + '\'' +
                ", info='" + info + '\'' +
                '}';
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Player(int playerId, String playerName, int state, int num, String picAddress, String dateOfBirth, String smallImg, int sex, String slogan, String info) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.state = state;
        this.num = num;
        this.picAddress = picAddress;
        this.dateOfBirth = dateOfBirth;
        this.smallImg = smallImg;
        this.sex = sex;
        this.slogan = slogan;
        this.info = info;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPicAddress() {
        return picAddress;
    }

    public void setPicAddress(String picAddress) {
        this.picAddress = picAddress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSmallImg() {
        return smallImg;
    }

    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public Player() {
    }

}
