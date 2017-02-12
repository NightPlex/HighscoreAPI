package com.kuuasema.service;

import java.util.Collection;

import com.kuuasema.model.Game;
import com.kuuasema.model.Player;
import com.kuuasema.model.template.GameTemplate;

/*
 * Interface for custom Game Service
 * 
 * */

public interface GameService {

	Game addNewScoreToGame(String gameName, int score, Player player);
	
	Collection<Game> getTopScores(int amount, String gameTitle);
	
	Collection<Game> getScoresByName(String playerName);
	
	boolean validateSubmitScore(GameTemplate submitScore);
	
	Game updateScore(Game game, int newScore);
}
