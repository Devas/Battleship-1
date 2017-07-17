package com.java_academy.battleship.model;

public enum CellState {
	
	EMPTY("   "),
	BUSY(" - "),
	TAKEN(" O ");
	
	private String symbol;
	
	private CellState(String symbol) {
		this.symbol = symbol;
	}
	
	public String toString() {
		return symbol;
	}
}
