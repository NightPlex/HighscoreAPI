package com.kuuasema.service;

import java.util.Collection;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.kuuasema.model.Game;
import com.kuuasema.model.Player;
import com.kuuasema.model.template.GameTemplate;


/*
 * Implementation for Custom Game service
 * For validation and so on
 * 
 * */

@Service
@Primary
public class GameServiceImpl implements GameService {

	
	
	//Attach repositories
	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private PlayerRepository playerRepository;
	
	//Validate adding new score to game
	@Override
	public Game addNewScoreToGame(String gameTitle, int score, Player player) {

		
			// try to fetch game object with give gameTitle and Player object
			Game game = gameRepository.findByPlayerAndGameTitle(player, gameTitle);

			
			// If game doesn't exist create a new
			if (game == null) {
				
				//First create a game object
				Game newGame = new Game(gameTitle, score, player);
				
				
				//save it to the database
				gameRepository.save(newGame);
				
				return newGame;
				

			// modify previous game file if score is bigger than previous.	
			} else {

				if (game.getScore() < score) {

					game.setScore(score);
					gameRepository.save(game);
					return game;

				} else {

					return null;

				}
			}


	}

	// Returns top score for given amount. For example "/topscore/5" will list top 5 scores.
	@Override
	public Collection<Game> getTopScores(int amount, String gameTitle) {

		// Java 8 lambda expression using Stream to limit the outcome to N size
		return this.gameRepository.findByGameTitle(gameTitle).stream()
				.sorted((a, b) -> Integer.compare(b.getScore(), a.getScore()))
				.limit(amount)
				.collect(Collectors.toList());
	}

	
	//Validate that game file is correct
	@Override
	public boolean validateSubmitScore(GameTemplate submitScore) { //This validation can be improved as any way as wanted

		if (submitScore.getPlayerId() != null && !submitScore.getGameTitle().isEmpty() && submitScore.getScore() > 0) {
			return true;
		}else {
			return false;
		}
		
		
	}

	//Get scores for all games by player name
	@Override
	public Collection<Game> getScoresByName(String playerName) {
		return this.gameRepository.findByPlayer(playerRepository.findByPlayerName(playerName)).stream()
				.sorted((a, b) -> Integer.compare(b.getScore(), a.getScore()))
				.collect(Collectors.toList());
	}

	//Update score validation. Only higher is updated, else return the same game object
	@Override
	public Game updateScore(Game game, int newScore) {
		
		if (game.getScore() < newScore) {
			
			game.setScore(newScore);
			
			gameRepository.save(game);
			
			return game;
			
		} else {
			
			return game;
			
		}
		
	}

}
