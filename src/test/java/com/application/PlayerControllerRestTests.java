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

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HighscoresApplication.class)
@WebAppConfiguration
public class PlayerControllerRestTests {

	// define json
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	// Define mockMvc
	private MockMvc mockMvc;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	private WebApplicationContext webApplicationContext;

	// define converter
	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);

	}

	// instantiate mockMvc
	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();

	}

	@Test
	public void playerCreated() throws Exception {
		mockMvc.perform(post("/player").content(this.json(new Player("steven"))).contentType(contentType))
				.andExpect(status().isCreated());
		mockMvc.perform(post("/player").content(this.json(new Player(""))).contentType(contentType))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void deletePlayer() throws Exception {

		MvcResult result = mockMvc
				.perform(post("/player").content(this.json(new Player("steven"))).contentType(contentType))
				.andReturn();
		// format to proper string
		String playerId = result.getResponse().getContentAsString().replaceAll("\"", "");

		String GameJson = json(new GameTemplate("angry", 500, playerId));
		// submit score
		this.mockMvc.perform(post("/game/submitscore").contentType(contentType).content(GameJson))
				.andExpect(status().isCreated());

		// Check if it gets deleted
		mockMvc.perform(delete("/player/" + playerId)).andExpect(status().isOk());

	}

	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

}
