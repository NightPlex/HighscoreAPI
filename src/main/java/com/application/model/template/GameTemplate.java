package com.application.model.template;

import java.util.UUID;

/*
 * Template to get game data from JSON responses
 * 
 * */

public class GameTemplate {

	private String gameTitle;
	private int score;
	private UUID playerId;

	@Override
	public String toString() {
		return "SubmitScore [gameTitle=" + gameTitle + ", score=" + score + ", playerId=" + playerId + "]";
	}

	public GameTemplate(String gameTitle, int score, UUID playerId) {
		super();
		this.gameTitle = gameTitle;
		this.score = score;
		this.playerId = playerId;
	}

	public GameTemplate(int score, UUID playerId) {
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

	public UUID getPlayerId() {
		return playerId;
	}

	public void setPlayerId(UUID playerId) {
		this.playerId = playerId;
	}

	public GameTemplate() {
		super();
	}

}
