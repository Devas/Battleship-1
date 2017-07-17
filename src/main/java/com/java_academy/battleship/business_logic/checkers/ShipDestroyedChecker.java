package com.java_academy.battleship.business_logic.checkers;

import com.java_academy.battleship.model.Ship;
import com.java_academy.battleship.model.ShipCellState;

public class ShipDestroyedChecker {

	boolean shipIsAlive(Ship ship) {
		for(ShipCellState state: ship.getCellStates()) {
			if(state.equals(ShipCellState.ALIVE)) {
				return true;
			}
		}
		return false;
	}
}
