package com.kuuasema.service;

import java.util.Collection;



import org.springframework.data.repository.CrudRepository;

import com.kuuasema.model.Game;
import com.kuuasema.model.Player;

/*
 * 
 * In memory database with H2.
 * Can be easily changed to work with Mysql with JpaRepository.
 * 
 * 
 * */


public interface GameRepository extends CrudRepository<Game, Long> {
	
	Collection<Game> findByPlayer(Player player);
	
	Game findByPlayerAndGameTitle(Player player, String gameTitle);
	
	Collection<Game> findByGameTitle(String gameTitle);
	

}
