package com.java_academy.battleship.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.java_academy.battleship.business_logic.FleetBuilder;
import com.java_academy.battleship.business_logic.FleetSetter;
import com.java_academy.battleship.business_logic.randomFleet.Randomizer;
import com.java_academy.battleship.model.Board;
import com.java_academy.battleship.model.CellState;
import com.java_academy.battleship.model.Ship;
import com.java_academy.battleship.model.Ships;

import javafx.scene.layout.Pane;

public class Model {
	
	View view;
	private final Map<Integer, CellState> shipPositions;
	
	public Model(View view) {
		this.view = view;
		//TODO wstrzykiwać te informacje
		Board board = new Board();
		board.createEmptyBoard();
		List<Ship> shipsList = FleetBuilder.getNonLocalizedShips();
		Ships ships = new Ships(shipsList);
		FleetSetter fleetSetter = new FleetSetter(10, board);
		Randomizer rand = new Randomizer(ships, fleetSetter);
		rand.setFleet();
		
		shipPositions = fleetSetter.getBoardMap();
	}
	
	void processShot(Pane pane) {
		int source = Integer.valueOf(pane.toString().replaceAll("\\D+", ""));
        
		if (shipPositions.get(source).equals(CellState.TAKEN)){
			view.shipHitted(pane);
		} else {
			view.dismissShot(pane);
		}
	}

}
