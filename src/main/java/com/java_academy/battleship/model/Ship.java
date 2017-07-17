package com.java_academy.battleship.model;

import java.util.List;

public class Ship {
	
	private int mastAmount;
	//TODO change for cell
	//private List<Cell> cells;
	private List<Integer> indexes;
	private List<ShipCellState> cellStates;

	public Ship(int mastAmount) {
		this.mastAmount = mastAmount;
	}

	boolean isAlive() {
		if(mastAmount > 0) {
			return true;
		}
		return false;
	}

	public int getMastAmount() {
		return mastAmount;
	}
	
	public List<ShipCellState> getCellStates() {
		return cellStates;
	}

	public void setCellStates(List<ShipCellState> cellStates) {
		this.cellStates = cellStates;
	}

	public List<Integer> getIndexes() {
		return indexes;
	}
	
	public void setIndexes(List<Integer> indexes) {
		this.indexes = indexes;
	}

}
