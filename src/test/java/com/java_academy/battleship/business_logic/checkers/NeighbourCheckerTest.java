package com.java_academy.battleship.business_logic.checkers;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.java_academy.battleship.business_logic.checkers.NeighbourChecker;
import com.java_academy.battleship.model.Board;

public class NeighbourCheckerTest {
	
	private Board board;
	private NeighbourChecker neighbourChecker;
	final static Integer xBoardDim = 10;
	
	@BeforeTest
	public void createRandomizer() {
		board = new Board();
		board.createEmptyBoard();
		neighbourChecker = new NeighbourChecker(10, board);
	}

	@Test
	public void isNeighbourIfBreakLine() {
		assertFalse(neighbourChecker.isNeighbour(9, 10));
		assertFalse(neighbourChecker.isNeighbour(10, 9));
	}
	
	@Test
	public void isNeighbourTest() {
		assertTrue(neighbourChecker.isNeighbour(23, 12));
		assertTrue(neighbourChecker.isNeighbour(23, 13));
		assertTrue(neighbourChecker.isNeighbour(23, 14));
		assertTrue(neighbourChecker.isNeighbour(23, 22));
		assertTrue(neighbourChecker.isNeighbour(23, 24));
		assertTrue(neighbourChecker.isNeighbour(23, 32));
		assertTrue(neighbourChecker.isNeighbour(23, 33));
		assertTrue(neighbourChecker.isNeighbour(23, 34));
	}
	
	@Test
	public void pointHaveEightNeighbours() {
		assertEquals(neighbourChecker.getNeighboursToCheckForPoint(15).length, 8);
		assertEquals(neighbourChecker.getNeighboursToCheckForPoint(4).length, 8);
	}
}
