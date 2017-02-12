package com.kuuasema.service;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.kuuasema.model.Player;

/*
 * In memory database to store Player objects(unique players)
 * 
 * */

public interface PlayerRepository extends CrudRepository<Player, Long>{
	
	Player findByPlayerId(UUID playerId);
	
	Player findByPlayerName(String playerName);
	

}
