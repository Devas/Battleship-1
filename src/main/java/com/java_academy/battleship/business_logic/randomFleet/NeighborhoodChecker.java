package com.java_academy.battleship.business_logic.randomFleet;

import java.util.Map;

import com.java_academy.battleship.model.CellState;

public class NeighborhoodChecker {
	
	private Map<Integer, CellState>  indexesOnBoard;
	
	public NeighborhoodChecker(Map<Integer, CellState> indexesOnBoard) {
		this.indexesOnBoard = indexesOnBoard;
	}
}
