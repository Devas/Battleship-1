package com.java_academy.battleship.business_logic.randomFleet;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.java_academy.battleship.business_logic.FleetBuilder;
import com.java_academy.battleship.business_logic.randomFleet.Randomizer;
import com.java_academy.battleship.model.Board;
import com.java_academy.battleship.model.Ship;
import com.java_academy.battleship.model.Ships;

public class RandomizerTest {
	
	private Board board;
	private Randomizer rand;
	private List<Ship> shipsList;
	private Ships ships;
	final static Integer xBoardDim = 10;
	
	@BeforeTest
	public void createRandomizer() {
		board = new Board();
		board.createEmptyBoard();
		shipsList = FleetBuilder.getNonLocalizedShips();
		ships = new Ships(shipsList);
		rand = new Randomizer(xBoardDim, ships, board);
	}
	
	@Test(timeOut = 1000)
	public void setFleetBelowOneSecond() {
		Randomizer rand = new Randomizer(10, ships, board);
		rand.setFleet();
	}
	
	@Test
	public void getNextIndexVertically() {
		assertEquals(rand.getNextIndex(0, true), 10);
	}
	
	@Test
	public void getNextIndexHorizontally() {
		assertEquals(rand.getNextIndex(11, false), 12);
	}
	
	@Test
	public void isNeighbourIfBreakLine() {
		assertFalse(rand.isNeighbour(9, 10));
		assertFalse(rand.isNeighbour(10, 9));
	}
	
	@Test
	public void isNeighbourTest() {
		assertTrue(rand.isNeighbour(23, 12));
		assertTrue(rand.isNeighbour(23, 13));
		assertTrue(rand.isNeighbour(23, 14));
		assertTrue(rand.isNeighbour(23, 22));
		assertTrue(rand.isNeighbour(23, 24));
		assertTrue(rand.isNeighbour(23, 32));
		assertTrue(rand.isNeighbour(23, 33));
		assertTrue(rand.isNeighbour(23, 34));
	}
	
	@Test
	public void pointHaveEightNeighbours() {
		assertEquals(rand.getNeighboursToCheckForPoint(15).length, 8);
		assertEquals(rand.getNeighboursToCheckForPoint(4).length, 8);
	}
	
	@Test
	public void busyNeighbours() {
		Board board = new Board();
		board.createEmptyBoard();
		Randomizer rand = new Randomizer(10, ships, board);
		rand.setShip(Arrays.asList(9, 19, 29));
		
		assertTrue(rand.busyNeighbours(18));
		assertFalse(rand.busyNeighbours(20));
	}
	
	@Test
	public void testPointIsAvailable() {
		Board board = new Board();
		board.createEmptyBoard();
		Randomizer rand = new Randomizer(10, ships, board);
		
		rand.setShipOnTheBoard(Arrays.asList(9, 19, 29));
		rand.setShipOnTheBoard(Arrays.asList(83, 84, 85, 86));
		
		assertTrue(rand.pointIsAvailable(4, 4)); //empty space
		assertTrue(rand.pointIsAvailable(10, 11)); //new line<index close to ship>
		assertTrue(rand.pointIsAvailable(49, 39)); //close ship vertically
		assertTrue(rand.pointIsAvailable(48, 38)); //close ship diagonally
		assertTrue(rand.pointIsAvailable(63, 64)); //close ship horyzontally
		
		assertFalse(rand.pointIsAvailable(9, 19)); //on ship
		assertFalse(rand.pointIsAvailable(94, 95)); //ship neighborhood
		assertFalse(rand.pointIsAvailable(77, 78)); //ship neighborhood diagonally
		
		
		
	}
}
