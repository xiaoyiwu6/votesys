package com.xtdx.pojo;

public class Player {
	private int playerId;
	private String playername;
	private int state;
	private int num;
	private String picAddress;
	private String dateOfBirth;
	private String smallImg;
	private int sex;
	public String getSmallImg() {
		return smallImg;
	}
	public void setSmallImg(String smallImg) {
		this.smallImg = smallImg;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public String getPlayername() {
		return playername;
	}
	public void setPlayername(String playername) {
		this.playername = playername;
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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Player(int playerId, String playername, int state, int num, String picAddress, String dateOfBirth, int sex) {
		super();
		this.playerId = playerId;
		this.playername = playername;
		this.state = state;
		this.num = num;
		this.picAddress = picAddress;
		this.dateOfBirth = dateOfBirth;
		this.sex = sex;
	}
	public Player() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
