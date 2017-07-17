package com.java_academy.battleship.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Board {

	private Map<Integer, CellState>  board;
	
	public Map<Integer, CellState> getBoardMap() {
		return board;
	}
	
	public void createEmptyBoard() {
		board = new HashMap<Integer, CellState>();
		for(int i = 0; i < 100; i++) {
			board.put(i, CellState.EMPTY);
		}
	}
	
	public void showBoard() {
		int cnt = 0;
		for(Entry<Integer, CellState> element: board.entrySet()) {
			System.out.print(element.getValue() + "|");
			cnt++;
			if(cnt%10 == 0){
				System.out.println();
			}
		}
	}
}
