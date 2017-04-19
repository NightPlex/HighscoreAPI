package com.application;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.application.HighscoresApplication;
import com.application.model.Player;
import com.application.model.template.GameTemplate;
import com.application.service.PlayerRepository;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HighscoresApplication.class)
@WebAppConfiguration
public class GameControllerRestTests {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private PlayerRepository playerRepository;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);

	}

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();

	}

	@Test
	public void showTopScore() throws Exception {
		MvcResult result = mockMvc
				.perform(post("/player").content(this.json(new Player("steven1"))).contentType(contentType))
				.andReturn();
		// format to proper string
		String playerId = result.getResponse().getContentAsString().replaceAll("\"", "");
		String gameTitle = "angrybirds";
		// set json
		String GameJson = json(new GameTemplate(gameTitle, 500, playerId));
		// submit score
		this.mockMvc.perform(post("/game/submitscore").contentType(contentType).content(GameJson))
				.andExpect(status().isCreated());

		// by game title
		this.mockMvc.perform(get("/game/topscore/" + gameTitle + "/10")).andExpect(status().isOk());

		// by name
		this.mockMvc
				.perform(get(
						"/game/topscore/" + playerRepository.findByPlayerId(playerId).getplayerName()))
				.andExpect(status().isOk());

	}

	@Test
	public void updateScore() throws Exception {
		MvcResult result = mockMvc
				.perform(post("/player").content(this.json(new Player("steven2"))).contentType(contentType))
				.andReturn();
		// format to proper string
		String playerId = result.getResponse().getContentAsString().replaceAll("\"", "");
		String gameTitle = "angrybirds";
		// set json
		String gameJson = json(new GameTemplate(gameTitle, 500, playerId));
		// submit score
		this.mockMvc.perform(post("/game/submitscore").contentType(contentType).content(gameJson))
				.andExpect(status().isCreated());
		// update

		String updatedGameJson = json(new GameTemplate(gameTitle, 800, playerId));

		this.mockMvc.perform(put("/game/updatescore").contentType(contentType).content(updatedGameJson))
				.andExpect(status().isCreated());

	}

	@Test
	public void deleteScore() throws Exception {
		MvcResult result = mockMvc
				.perform(post("/player").content(this.json(new Player("steven3"))).contentType(contentType))
				.andReturn();
		// format to proper string
		String playerId = result.getResponse().getContentAsString().replaceAll("\"", "");
		String gameTitle = "angrybirds";
		// set json
		String GameJson = json(new GameTemplate(gameTitle, 500, playerId));
		// submit score
		this.mockMvc.perform(post("/game/submitscore").contentType(contentType).content(GameJson))
				.andExpect(status().isCreated());
		// delete score
		this.mockMvc.perform(delete("/game/delete/" + gameTitle + "/" + playerId)).andExpect(status().isOk());

	}

	@Test
	public void createNewScore() throws Exception {
		MvcResult result = mockMvc
				.perform(post("/player").content(this.json(new Player("steven4"))).contentType(contentType))
				.andReturn();
		// format to proper string
		String playerId = result.getResponse().getContentAsString().replaceAll("\"", "");

		String GameJson = json(new GameTemplate("angrybirds", 500, playerId));

		this.mockMvc.perform(post("/game/submitscore").contentType(contentType).content(GameJson))
				.andExpect(status().isCreated());
	}

	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

}
