package com.application.service;

import java.util.Collection;

import com.application.model.Game;
import com.application.model.Player;
import com.application.model.template.GameTemplate;

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
