package com.application.controller;

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

import com.application.model.Game;
import com.application.model.Player;
import com.application.model.template.GameTemplate;
import com.application.service.GameRepository;
import com.application.service.GameService;
import com.application.service.PlayerRepository;

@RestController
@RequestMapping("/game")
public class GameController {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private GameService gameService;

	// Submit score to the database, if score exists only the highest score is
	// stored.
	@RequestMapping(value = "/submitscore", method = RequestMethod.POST)
	public ResponseEntity<?> submitScoreToGamePost(@RequestBody GameTemplate submitScore) {

		// Try to find if Player exists in the database
		Player player = playerRepository.findByPlayerId(submitScore.getPlayerId());

		// Must pass validation
		if (gameService.validateSubmitScore(submitScore)) {

			// if user exists save score to database and return game object
			if (player != null) {
				return new ResponseEntity<>(gameService.addNewScoreToGame(submitScore.getGameTitle(),
						submitScore.getScore(), playerRepository.findByPlayerId(submitScore.getPlayerId())),
						HttpStatus.CREATED);
			} else {

				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}

		} else {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	// Update score in database with playerId and game Title
	@RequestMapping(value = "/updatescore", method = RequestMethod.PUT)
	public ResponseEntity<?> updateScoreByTitleAndId(@RequestBody GameTemplate updateScore) {

		// Must pass validation
		if (gameService.validateSubmitScore(updateScore)) {

			// try to find
			Game game = gameRepository.findByPlayerAndGameTitle(
					playerRepository.findByPlayerId(updateScore.getPlayerId()), updateScore.getGameTitle());

			// if exists update
			if (game != null) {

				return new ResponseEntity<>(gameService.updateScore(game, updateScore.getScore()), HttpStatus.CREATED);
				// else not found
			} else {

				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}

			// invalid format
		} else {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// Delete a game score by title and player id
	@RequestMapping(value = "/delete/{gameTitle}/{playerId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteScoreByIdAndTitle(@PathVariable("gameTitle") String gameTitle,
			@PathVariable("playerId") String playerId) {

		// try to find
		Game game = gameRepository.findByPlayerAndGameTitle(playerRepository.findByPlayerId(playerId), gameTitle);

		if (game == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
		gameRepository.delete(game);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	// Search all games by player name
	@RequestMapping(value = "/topscore/{playerName}", method = RequestMethod.GET)
	public ResponseEntity<Collection<Game>> listTopScoreByName(@PathVariable("playerName") String playerName) {

		Collection<Game> games = gameService.getScoresByName(playerName);

		// Validate
		if (games.isEmpty()) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else {

			return new ResponseEntity<>(games, HttpStatus.OK);
		}

	}

	// Get top scores by giving game title and top amount.
	@RequestMapping(value = "/topscore/{gameTitle}/{amount}", method = RequestMethod.GET)
	public ResponseEntity<Collection<Game>> listTopScoreByAmount(@PathVariable("gameTitle") String gameTitle,
			@PathVariable("amount") int amount) {

		Collection<Game> games = gameService.getTopScores(amount, gameTitle);

		if (games.isEmpty()) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else {

			return new ResponseEntity<>(games, HttpStatus.OK);
		}

	}

}
