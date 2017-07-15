package com.java_academy.battleship.business_logic.randomFleet;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.java_academy.battleship.business_logic.FleetBuilder;
import com.java_academy.battleship.model.Board;
import com.java_academy.battleship.model.CellState;
import com.java_academy.battleship.model.Ship;
import com.java_academy.battleship.model.Ships;

public class TempMainForAlgo {
 
	public static void main(String[] args) {
		Board board = new Board();
		board.createEmptyBoard();
		List<Ship> shipsList = FleetBuilder.getNonLocalizedShips();
		Ships ships = new Ships(shipsList);
		Randomizer rand = new Randomizer(10, ships, board);
		rand.setFleet();
	}
	
	public static void showBoard(Map<Integer, CellState> board) {
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

