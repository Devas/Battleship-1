package com.java_academy.battleship.business_logic.randomFleet;

import java.util.List;

import com.java_academy.battleship.business_logic.FleetBuilder;
import com.java_academy.battleship.business_logic.FleetSetter;
import com.java_academy.battleship.model.Board;
import com.java_academy.battleship.model.Ship;
import com.java_academy.battleship.model.Ships;

public class TempMainForAlgo {
 
	public static void main(String[] args) {
		Board board = new Board();
		board.createEmptyBoard();
		List<Ship> shipsList = FleetBuilder.getNonLocalizedShips();
		Ships ships = new Ships(shipsList);
		FleetSetter fleetSetter = new FleetSetter(10, board);
		Randomizer rand = new Randomizer(ships, fleetSetter);
		rand.setFleet();
	}
}

