package com.java_academy.battleship.business_logic.attacks;

import java.util.Map;

import com.java_academy.battleship.business_logic.Attack;
import com.java_academy.battleship.model.Board;
import com.java_academy.battleship.model.CellState;

public class NormalAttack implements Attack {
	
	Board board;
	Map<Integer, CellState>  boardMap;
	int boardXDim;
	
	public NormalAttack(int boardXDim, Board board) {
		this.board = board;
		this.boardXDim = boardXDim;
		boardMap = board.getBoardMap();
	}

	@Override
	public boolean attack(Integer index) {
		// TODO Auto-generated method stub
		
		
		return true;
	}
}
