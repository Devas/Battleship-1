package com.java_academy.battleship.helper;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.JsonSyntaxException;
import com.java_academy.battleship.model.Player;

public class JsonParserTest {
	
	private JsonParser jsonParser;

	@BeforeTest
	public void createParser() {
		jsonParser = new JsonParser();
	}
	
	@Test
	public void parsePlayerToJsonTest() {
		Player player = new Player("Artur", 0, 3);
		String playerInJson = jsonParser.parsePlayerToJson(player);
		
		assertEquals(playerInJson, "{\"nickname\":\"Artur\",\"score\":0,\"nukeCounter\":3}");
	}
	
	@Test
	public void parsePlayerFromJsonTest() {
		Player player = new Player("Artur", 0, 3);
		Player player2 = jsonParser.parsePlayerFromJson("{\"nickname\":\"Artur\",\"score\":0,\"nukeCounter\":3}");
		assertEquals(player.toString(), player2.toString());
		assertEquals(player2, player);
	}
	
	@Test(expectedExceptions=JsonSyntaxException.class)
	public void shouldThrowExceptionDuringParsing() {
		String testString = "aaaa";
		Player player = jsonParser.parsePlayerFromJson(testString);
	}
	
}
