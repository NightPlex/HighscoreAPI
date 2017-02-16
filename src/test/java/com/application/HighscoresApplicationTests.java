package com.application;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.application.controller.GameController;
import com.application.controller.MainController;
import com.application.controller.PlayerController;

/*
 * Test if controllers get loaded
 * 
 * 
 * */

@RunWith(SpringRunner.class)
@SpringBootTest
public class HighscoresApplicationTests {

	@Autowired
	private PlayerController playerController;

	@Autowired
	private GameController gameController;

	@Autowired
	private MainController mainController;

	@Test
	public void contextLoads() {

		assertThat(playerController).isNotNull();
		assertThat(gameController).isNotNull();
		assertThat(mainController).isNotNull();

	}

}
