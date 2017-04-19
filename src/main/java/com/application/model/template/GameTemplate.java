package com.application.model.template;

import java.util.UUID;

/*
 * Template to get game data from JSON responses
 * 
 * */

public class GameTemplate {

	private String gameTitle;
	private int score;
	private String playerId;

	@Override
	public String toString() {
		return "SubmitScore [gameTitle=" + gameTitle + ", score=" + score + ", playerId=" + playerId + "]";
	}

	public GameTemplate(String gameTitle, int score, String playerId) {
		super();
		this.gameTitle = gameTitle;
		this.score = score;
		this.playerId = playerId;
	}

	public GameTemplate(int score, String playerId) {
		super();
		this.score = score;
		this.playerId = playerId;
	}

	public String getGameTitle() {
		return gameTitle;
	}

	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public GameTemplate() {
		super();
	}

}
