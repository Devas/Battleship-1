package com.java_academy.battleship.business_logic;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.java_academy.battleship.model.Board;

public class FleetSetterTest {
	private Board board;
	private FleetSetter fleetSetter;
	final static Integer xBoardDim = 10;
	
	@BeforeTest
	public void createRandomizer() {
		board = new Board();
		board.createEmptyBoard();
		fleetSetter = new FleetSetter(10, board);
	}
	
	@Test
	public void getNextIndexVertically() {
		assertEquals(fleetSetter.getNextIndex(0, true), 10);
	}
	
	@Test
	public void getNextIndexHorizontally() {
		assertEquals(fleetSetter.getNextIndex(11, false), 12);
	}
	
	@Test
	public void isNeighbourIfBreakLine() {
		assertFalse(fleetSetter.isNeighbour(9, 10));
		assertFalse(fleetSetter.isNeighbour(10, 9));
	}
	
	@Test
	public void isNeighbourTest() {
		assertTrue(fleetSetter.isNeighbour(23, 12));
		assertTrue(fleetSetter.isNeighbour(23, 13));
		assertTrue(fleetSetter.isNeighbour(23, 14));
		assertTrue(fleetSetter.isNeighbour(23, 22));
		assertTrue(fleetSetter.isNeighbour(23, 24));
		assertTrue(fleetSetter.isNeighbour(23, 32));
		assertTrue(fleetSetter.isNeighbour(23, 33));
		assertTrue(fleetSetter.isNeighbour(23, 34));
	}
	
	@Test
	public void pointHaveEightNeighbours() {
		assertEquals(fleetSetter.getNeighboursToCheckForPoint(15).length, 8);
		assertEquals(fleetSetter.getNeighboursToCheckForPoint(4).length, 8);
	}
	
	@Test
	public void busyNeighbours() {
		Board board = new Board();
		board.createEmptyBoard();
		FleetSetter fleetSetter = new FleetSetter(10, board);
		fleetSetter.setShip(Arrays.asList(9, 19, 29));
		
		assertTrue(fleetSetter.busyNeighbours(18));
		assertFalse(fleetSetter.busyNeighbours(20));
	}
	
	@Test
	public void testPointIsAvailable() {
		Board board = new Board();
		board.createEmptyBoard();
		FleetSetter fleetSetter = new FleetSetter(10, board);
		
		fleetSetter.setShipOnTheBoard(Arrays.asList(9, 19, 29));
		fleetSetter.setShipOnTheBoard(Arrays.asList(83, 84, 85, 86));
		
		assertTrue(fleetSetter.pointIsAvailable(4, 4)); //empty space
		assertTrue(fleetSetter.pointIsAvailable(10, 11)); //new line<index close to ship>
		assertTrue(fleetSetter.pointIsAvailable(49, 39)); //close ship vertically
		assertTrue(fleetSetter.pointIsAvailable(48, 38)); //close ship diagonally
		assertTrue(fleetSetter.pointIsAvailable(63, 64)); //close ship horyzontally
		
		assertFalse(fleetSetter.pointIsAvailable(9, 19)); //on ship
		assertFalse(fleetSetter.pointIsAvailable(94, 95)); //ship neighborhood
		assertFalse(fleetSetter.pointIsAvailable(77, 78)); //ship neighborhood diagonally
	}
}
