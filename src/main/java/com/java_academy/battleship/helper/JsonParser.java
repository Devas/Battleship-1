package com.java_academy.battleship.helper;

import com.google.gson.Gson;
import com.java_academy.battleship.model.Player;

public class JsonParser {
	
	private Gson gson = new Gson();

	public String parsePlayerToJson(Player player) {
		String pl = gson.toJson(player);
		return pl;
	}
	
	public Player parsePlayerFromJson(String jsonString) {
		return gson.fromJson(jsonString, Player.class);
	}
}
