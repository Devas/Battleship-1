package com.java_academy.battleship.model;

import java.util.List;

public class Ships {
	
	List<Ship> ships;
	
	public Ships(List<Ship> ships) {
		this.ships = ships;
	}

	public List<Ship> getFleet() {
		return ships;
	}
	
	boolean isNukeAvailable() {
		return !ships.isEmpty();
	}
}
