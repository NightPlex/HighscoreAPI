package com.kuuasema;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kuuasema.model.Game;
import com.kuuasema.model.Player;
import com.kuuasema.service.GameRepository;
import com.kuuasema.service.PlayerRepository;

/*
 * Test game repository database
 * 
 * */


@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTest {
	
	@Autowired
	private GameRepository gameRepository;
	
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Test
	public void createAndDeleteNewGame() {
		
		//Add new player
		Player player = new Player("game");
		
		playerRepository.save(player);
		assertThat(playerRepository.findByPlayerId(player.getPlayerId()));
		
		//Add new score to database
		
		Game game = new Game("angrybirds", 50, player);
		gameRepository.save(game);
		assertThat(gameRepository.findByPlayer(player)).isNotNull();
		
		//delete from database
		playerRepository.delete(player);
		
	}
	
	@Test
	public void createAndDeletePlayer() {
		
		//Add new player
		Player player = new Player("player");
		
		playerRepository.save(player);
		assertThat(playerRepository.findByPlayerId(player.getPlayerId()));
		
		//delete player from database
		playerRepository.delete(player);
		assertThat(playerRepository.findByPlayerId(player.getPlayerId())).isNull();
		
	}

	

}
