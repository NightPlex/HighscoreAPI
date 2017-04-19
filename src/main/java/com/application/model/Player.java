package com.application.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Player {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// Not visible in GET requests
	@JsonIgnore
	private String playerId;

	@Column(unique = true)
	private String playerName;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
	private List<Game> scores;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getplayerName() {
		return playerName;
	}

	public void setplayerName(String playerName) {
		this.playerName = playerName;
	}

	public Player(String playerName) {
		super();

		// Generate unique random playerId for each player.
		this.playerId = UUID.randomUUID().toString();
		this.playerName = playerName;
	}

	public Player() {

	}

}
