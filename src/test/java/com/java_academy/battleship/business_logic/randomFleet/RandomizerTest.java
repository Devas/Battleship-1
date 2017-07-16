package com.java_academy.battleship.business_logic.randomFleet;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.java_academy.battleship.business_logic.FleetBuilder;
import com.java_academy.battleship.business_logic.FleetSetter;
import com.java_academy.battleship.business_logic.randomFleet.Randomizer;
import com.java_academy.battleship.model.Board;
import com.java_academy.battleship.model.Ship;
import com.java_academy.battleship.model.Ships;

public class RandomizerTest {
	
	private Board board;
	private Randomizer rand;
	private List<Ship> shipsList;
	private Ships ships;
	private FleetSetter fleetSetter;
	final static Integer xBoardDim = 10;
	
	@BeforeTest
	public void createRandomizer() {
		board = new Board();
		board.createEmptyBoard();
		shipsList = FleetBuilder.getNonLocalizedShips();
		ships = new Ships(shipsList);
		fleetSetter = new FleetSetter(10, board);
		rand = new Randomizer(ships, fleetSetter);
	}
	
	@Test(timeOut = 1000)
	public void setFleetBelowOneSecond() {
		Randomizer rand = new Randomizer(ships, fleetSetter);
		rand.setFleet();
	}
	
	@Test
	public void randomIntegerInScopeTest10000Times() {
		for(int i = 0; i < 100000 ; i++) {
			assertTrue(rand.randomIntegerInScope(0,99) <= 99);
			assertTrue(rand.randomIntegerInScope(0,99) >= 0);
		}
	}
}
