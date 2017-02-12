package com.kuuasema.controller;

import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kuuasema.model.Game;
import com.kuuasema.model.Player;
import com.kuuasema.service.GameRepository;
import com.kuuasema.service.PlayerRepository;

@RestController
@RequestMapping("/player")
public class PlayerController {

	//Wire needed repositories
	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private GameRepository gameRepository;


	// REST service to register to the service
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> userRegister(@RequestBody Player player) { 

		// Check that player name contains at least 1 character and name is unique
		if (player.getplayerName().isEmpty() ||
				playerRepository.findByPlayerName(player.getplayerName()) != null) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
		} else {
			// Save name and userId to database and return HTTP CREATED and
			// return UUID to user
			return new ResponseEntity<>(playerRepository.save(new Player(player.getplayerName())).getPlayerId(),
					HttpStatus.CREATED);
		}

	}

	// Request all scores submitted by player
	@RequestMapping(path = "/{playerId}", method = RequestMethod.GET)
	public ResponseEntity<?> findByplayerId(@PathVariable("playerId") UUID playerId) {
		
		//Try to fetch games
		Collection<Game> games = gameRepository.findByPlayer(playerRepository.findByPlayerId(playerId));

		if (games.isEmpty()) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else {

			return new ResponseEntity<>(games, HttpStatus.OK);
		}

	}

	// Delete player and all its games by id
	@RequestMapping(path = "/delete/{playerId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteByplayerId(@PathVariable("playerId") UUID playerId) {

		//Try to find
		Player player = playerRepository.findByPlayerId(playerId);

		if (player == null) {

			System.out.println("not found");

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else {
			playerRepository.delete(player);
			return new ResponseEntity<>(HttpStatus.OK);
		}

	}

}
