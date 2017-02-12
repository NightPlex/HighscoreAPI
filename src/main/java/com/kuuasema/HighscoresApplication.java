package com.kuuasema;

import java.util.Random;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kuuasema.model.Game;
import com.kuuasema.model.Player;
import com.kuuasema.service.GameRepository;
import com.kuuasema.service.PlayerRepository;

@SpringBootApplication
public class HighscoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(HighscoresApplication.class, args);
	}

	//Generate data at application start
	@Bean
	public CommandLineRunner studentDemo(GameRepository gameRepository, PlayerRepository playerRepository) {
		return (args) -> {
				Random rand = new Random();
		
			   	Player player1 = new Player("Peter");
			   	Player player2 = new Player("NightPlex92");
			   	Player player3 = new Player("PeterMan");
			   	Player player4 = new Player("whitehat555");
			   	Player player5 = new Player("madman44");
			 	Player player6 = new Player("idontknow");
			 	Player player7 = new Player("whytest3");
				
				playerRepository.save(player1);
				playerRepository.save(player2);
				playerRepository.save(player3);
				playerRepository.save(player4);
				playerRepository.save(player5);
				playerRepository.save(player5);
				playerRepository.save(player6);
				playerRepository.save(player7);
				gameRepository.save(new Game("angrybirds", rand.nextInt(100000), player1));
				gameRepository.save(new Game("angrybirds", rand.nextInt(100000), player2));
				gameRepository.save(new Game("angrybirds", rand.nextInt(100000), player3));
				gameRepository.save(new Game("angrybirds", rand.nextInt(100000), player4));
				gameRepository.save(new Game("angrybirds", rand.nextInt(100000), player5));
				gameRepository.save(new Game("angrybirds", rand.nextInt(100000), player6));
				gameRepository.save(new Game("angrybirds", rand.nextInt(100000), player7));
				

			
			
		};
	}
}
