package com.java_academy.battleship.business_logic.checkers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.java_academy.battleship.model.Board;
import com.java_academy.battleship.model.CellState;

public class NeighbourChecker {
	
	Board board;
	Map<Integer, CellState>  boardMap;
	int boardXDim;

	public NeighbourChecker(int boardXDim, Board board) {
		this.board = board;
		this.boardXDim = boardXDim;
		boardMap = board.getBoardMap();
	}
	
	public List<Integer> getNeighbours(Integer startPoint) {
		List<Integer> neighbours = new ArrayList<Integer>();
		for(Integer index: getNeighboursToCheckForPoint(startPoint)) {
			if(isNeighbour(startPoint, index) && boardMap.containsKey(index)) {
				neighbours.add(index);
			}
		}
		return neighbours;
	}
	
	public boolean isNeighbour(Integer startPoint, Integer index) {
		if(startPoint % boardXDim == boardXDim - 1) {
			if(index - startPoint == 1 || startPoint - index == boardXDim-1 || index - startPoint == boardXDim+1) {
				return false;
			}
		}
		if(startPoint % boardXDim == 0) {
			if(startPoint - index == 1 || index - startPoint == boardXDim-1 || startPoint - index == boardXDim+1) {
				return false;
			}
		}
		return true;
	}
	
	Integer[] getNeighboursToCheckForPoint(Integer point) {
		Integer[] neighboursToCheck = {point - (boardXDim+1), point - boardXDim, point - (boardXDim-1), 
				   point - 1, point + 1,
				   point + (boardXDim-1), point + boardXDim, point + boardXDim + 1};
		return neighboursToCheck;
	}
}
