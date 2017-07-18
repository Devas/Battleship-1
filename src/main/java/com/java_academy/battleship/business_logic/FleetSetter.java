package com.java_academy.battleship.business_logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.java_academy.battleship.business_logic.checkers.NeighbourChecker;
import com.java_academy.battleship.model.Board;
import com.java_academy.battleship.model.CellState;
import com.java_academy.battleship.model.Ship;

public class FleetSetter {
	
	Logger logger = Logger.getLogger("stdout");
	Board board;
	Map<Integer, CellState>  boardMap;
	int boardXDim;
	NeighbourChecker neighbourChecker;
	
	public FleetSetter(int boardXDim, Board board) {
		this.board = board;
		this.boardXDim = boardXDim;
		boardMap = board.getBoardMap();
		neighbourChecker = new NeighbourChecker(boardXDim, board);
	}

	public boolean setIfPossible(int startPoint, Ship ship, Boolean isVertical) {
		List<Integer> shipIndexes = new ArrayList<Integer>();
		int cnt = 0;
		int lastIndex = startPoint;
		int nextIndex = startPoint;
		for(int i = 0 ; i < ship.getMastAmount() ; i++) {
			if(pointIsAvailable(nextIndex, lastIndex)) {
				cnt++;
				if(i > 0) {
					lastIndex = nextIndex;
				}
				shipIndexes.add(lastIndex);
				nextIndex = getNextIndex(nextIndex, isVertical);
			}	
		}
		if(cnt == ship.getMastAmount()) {
			setShipOnTheBoard(shipIndexes);
			return true;
		}
		return false;
	}
	
	void setShipOnTheBoard(List<Integer> shipIndexes) {
		logger.info("Randomizer create a ship: " + shipIndexes);
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
			for(Integer neighbour: neighbourChecker.getNeighbours(index)) {
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
		List<Integer> neighbours = neighbourChecker.getNeighbours(startPoint);
		boolean flag = false;
		
		for(Integer neighbour: neighbours) {
			if(boardMap.get(neighbour).equals(CellState.TAKEN)) {
				flag = true;
			}
		}
		
		return flag;
	}
	
	private boolean pointIsOnTheBoard(Integer point) {
		return boardMap.containsKey(point);
	}
	
	private boolean pointNeighborhoodIsEmpty(Integer index){
		return boardMap.get(index).equals(CellState.EMPTY) && !busyNeighbours(index);
	}
	
	private boolean earlierPointIsNeighbor(Integer point, Integer ancestor){
		return neighbourChecker.isNeighbour(point, ancestor);
	}

	int getNextIndex(int index, boolean isVertical) {
		if(isVertical) {
			return index + 10;
		} else {
			return index + 1;
		}
	}
	
	public Map<Integer, CellState> getBoardMap() {
		return boardMap;
	}
}
