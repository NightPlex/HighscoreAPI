package com.application.admincontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.Game;
import com.application.model.Player;
import com.application.service.GameRepository;
import com.application.service.PlayerRepository;


@RestController
@RequestMapping("/admin/api")
public class AccountController {
	
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	//Retrive and delte a game by game id
	@RequestMapping(value = "/{gameId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteGameById(@PathVariable("gameId") Long gameId) {
		
		Game playerGame = gameRepository.findOne(gameId);
		
		if(playerGame != null) {
			gameRepository.delete(playerGame);
			return new ResponseEntity<>(HttpStatus.OK);
		}
				
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/{playerName}", method = RequestMethod.GET)
	public ResponseEntity<?> getIdByName(@PathVariable("playerName") String playerName) {
		
		Player player = playerRepository.findByPlayerName(playerName);
		
		if(player != null) {
			
			String jsonResponse = player.getPlayerId();
			// Save name and userId to database and return HTTP CREATED and
			// return UUID to user
			return new ResponseEntity<>('"'+jsonResponse+'"',
					HttpStatus.CREATED);
		}
				
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	

}
