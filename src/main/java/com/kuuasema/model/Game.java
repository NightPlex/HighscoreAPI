package com.kuuasema.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


/*
 * Entity to hold game related data.
 * Holds data about all players on mutiple games
 * 
 * 
 * */


@Entity
public class Game {

	//Id for serializing
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	
	@ManyToOne//
    @JoinColumn(name = "playerId")
    private Player player;

	//Name of the game
	private String gameTitle;

	//Highest score
	private int score;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
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

	public Game(String gameTitle, int score, Player player) {
		super();
		this.gameTitle = gameTitle;
		this.score = score;
		this.player = player;
	}

	public Game() {
		super();
	}

	@Override
	public String toString() {
		return "Game [id=" + id  + ", gameTitle=" + gameTitle +
				", score=" + score + "player" + player + "]";
	}

}
