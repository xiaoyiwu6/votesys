package com.xtdx.pojo;

public class User{
	private int userId;
	private String account;
	private String password;
	private int level;//0--普通选民；1--管理员

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", account='" + account + '\'' +
				", password='" + password + '\'' +
				", level=" + level +
				'}';
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public User() {
	}

	public User(int userId, String account, String password, int level) {
		this.userId = userId;
		this.account = account;
		this.password = password;
		this.level = level;
	}
}

//重构
//public class User {
//	private int	userId;
//	private String keywords;
//	private int type;
//
//	public int getUserId() {
//		return userId;
//	}
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}
//	public String getKeywords() {
//		return keywords;
//	}
//	public void setKeywords(String keywords) {
//		this.keywords = keywords;
//	}
//	public int getType() {
//		return type;
//	}
//	public void setType(int type) {
//		this.type = type;
//	}
//	public User(int userId, String keywords, int type) {
//		super();
//		this.userId = userId;
//		this.keywords = keywords;
//		this.type = type;
//	}
//	public User() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//}
