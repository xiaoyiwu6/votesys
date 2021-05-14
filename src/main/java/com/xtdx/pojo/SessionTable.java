package com.xtdx.pojo;

public class SessionTable {
	private int wheel;
	private String nowSession;
	private int winner;
	private int votesContrastA;
	private int votesContrastB;
	private int playerA;
	private int playerB;
	private int gameNow;
	private int playerAscore;
	private int playerBscore;
	public int getWheel() {
		return wheel;
	}
	public void setWheel(int wheel) {
		this.wheel = wheel;
	}
	public String getNowSession() {
		return nowSession;
	}
	public void setNowSession(String nowSession) {
		this.nowSession = nowSession;
	}
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		this.winner = winner;
	}
	public int getVotesContrastA() {
		return votesContrastA;
	}
	public void setVotesContrastA(int votesContrastA) {
		this.votesContrastA = votesContrastA;
	}
	public int getVotesContrastB() {
		return votesContrastB;
	}
	public void setVotesContrastB(int votesContrastB) {
		this.votesContrastB = votesContrastB;
	}
	public int getPlayerA() {
		return playerA;
	}
	public void setPlayerA(int playerA) {
		this.playerA = playerA;
	}
	public int getPlayerB() {
		return playerB;
	}
	public void setPlayerB(int playerB) {
		this.playerB = playerB;
	}
	public int getGameNow() {
		return gameNow;
	}
	public void setGameNow(int gameNow) {
		this.gameNow = gameNow;
	}
	public int getPlayerAscore() {
		return playerAscore;
	}
	public void setPlayerAscore(int playerAscore) {
		this.playerAscore = playerAscore;
	}
	public int getPlayerBscore() {
		return playerBscore;
	}
	public void setPlayerBscore(int playerBscore) {
		this.playerBscore = playerBscore;
	}
	public SessionTable(int wheel, String nowSession, int winner, int votesContrastA, int votesContrastB, int playerA,
						int playerB) {
		super();
		this.wheel = wheel;
		this.nowSession = nowSession;
		this.winner = winner;
		this.votesContrastA = votesContrastA;
		this.votesContrastB = votesContrastB;
		this.playerA = playerA;
		this.playerB = playerB;

	}
	public SessionTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
