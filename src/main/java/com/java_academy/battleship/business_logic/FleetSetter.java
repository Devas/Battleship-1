package com.java_academy.battleship.business_logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.java_academy.battleship.model.Board;
import com.java_academy.battleship.model.CellState;
import com.java_academy.battleship.model.Ship;

public class FleetSetter {
	
	Board board;
	Map<Integer, CellState>  boardMap;
	int boardXDim;
	
	public FleetSetter(int boardXDim, Board board) {
		this.board = board;
		this.boardXDim = boardXDim;
		boardMap = board.getEmptyBoard();
	}

	public boolean setIfPossible(int startPoint, Ship ship, Boolean isVertical) {
		List<Integer> shipIndexes = new ArrayList<Integer>();
		int cnt = 0;
		int lastIndex = startPoint;
		int nextIndex = startPoint;
		for(int i = 0 ; i < ship.getHp() ; i++) {
			if(pointIsAvailable(nextIndex, lastIndex)) {
				cnt++;
				if(i > 0) {
					lastIndex = nextIndex;
				}
				shipIndexes.add(lastIndex);
				nextIndex = getNextIndex(nextIndex, isVertical);
			}	
		}
		if(cnt == ship.getHp()) {
			setShipOnTheBoard(shipIndexes);
			return true;
		}
		return false;
	}
	
	void setShipOnTheBoard(List<Integer> shipIndexes) {
		System.out.println("Create a ship: " + shipIndexes);
		setShip(shipIndexes);
		markNeighbours(shipIndexes);
		board.showBoard();
	}
	
	void setShip(List<Integer> shipIndexes) {
		for(Integer index: shipIndexes) {
			boardMap.put(index, CellState.TAKEN);
		}
	}
	
	void markNeighbours(List<Integer> shipIndexes) {
		for(Integer index: shipIndexes) {
			for(Integer neighbour: getNeighbours(index)) {
				if(!boardMap.get(neighbour).equals(CellState.TAKEN)) {
					boardMap.put(neighbour, CellState.BUSY);
				}
			}
		}
	}

	boolean pointIsAvailable(Integer point, Integer ancestor) {
		return pointIsOnTheBoard(point) 
				&& pointNeighborhoodIsEmpty(point)
				&& earlierPointIsNeighbor(point, ancestor);
	}

	boolean busyNeighbours(Integer startPoint) {
		List<Integer> neighbours = getNeighbours(startPoint);
		boolean flag = false;
		
		for(Integer neighbour: neighbours) {
			if(boardMap.get(neighbour).equals(CellState.TAKEN)) {
				flag = true;
			}
		}
		
		return flag;
	}
	
	private List<Integer> getNeighbours(Integer startPoint) {
		List<Integer> neighbours = new ArrayList<Integer>();
		for(Integer index: getNeighboursToCheckForPoint(startPoint)) {
			if(isNeighbour(startPoint, index) && boardMap.containsKey(index)) {
				neighbours.add(index);
			}
		}
		return neighbours;
	}
	
	Integer[] getNeighboursToCheckForPoint(Integer point) {
		Integer[] neighboursToCheck = {point - (boardXDim+1), point - boardXDim, point - (boardXDim-1), 
				   point - 1, point + 1,
				   point + (boardXDim-1), point + boardXDim, point + boardXDim + 1};
		return neighboursToCheck;
	}
	
	private boolean pointIsOnTheBoard(Integer point) {
		return boardMap.containsKey(point);
	}
	
	private boolean pointNeighborhoodIsEmpty(Integer index){
		return boardMap.get(index).equals(CellState.EMPTY) && !busyNeighbours(index);
	}
	
	private boolean earlierPointIsNeighbor(Integer point, Integer ancestor){
		return isNeighbour(point, ancestor);
	}

	boolean isNeighbour(Integer startPoint, Integer index) {
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

	int getNextIndex(int index, boolean isVertical) {
		if(isVertical) {
			return index + 10;
		} else {
			return index + 1;
		}
	}
}
