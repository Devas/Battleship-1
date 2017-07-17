package com.java_academy.battleship.business_logic.checkers;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.java_academy.battleship.model.Ship;
import com.java_academy.battleship.model.ShipCellState;

public class ShipDestroyedChecketTest {

	
	@Test
	public void threeMastShipWithoutDamagedCellsIsAliveTest() {
		Ship ship = new Ship(3);
		ShipCellState[] shipCells = {ShipCellState.ALIVE, ShipCellState.ALIVE, ShipCellState.ALIVE};
		List<ShipCellState> cellStates = Arrays.asList(shipCells);
		ship.setCellStates(cellStates);
		
		ShipDestroyedChecker checker = new ShipDestroyedChecker();
		
		assertTrue(checker.shipIsAlive(ship));
	}
	
	@Test
	public void threeMastShipWithDamagedAllCellsIsNotAliveTest() {
		Ship ship = new Ship(3);
		ShipCellState[] shipCells = {ShipCellState.DESTROYED, ShipCellState.DESTROYED, ShipCellState.DESTROYED};
		List<ShipCellState> cellStates = Arrays.asList(shipCells);
		ship.setCellStates(cellStates);
		
		ShipDestroyedChecker checker = new ShipDestroyedChecker();
		
		assertFalse(checker.shipIsAlive(ship));
	}
	
	@Test
	public void threeMastShipWithDamaged2CellsIsAliveTest() {
		Ship ship = new Ship(3);
		ShipCellState[] shipCells = {ShipCellState.ALIVE, ShipCellState.DESTROYED, ShipCellState.DESTROYED};
		List<ShipCellState> cellStates = Arrays.asList(shipCells);
		ship.setCellStates(cellStates);
		
		ShipDestroyedChecker checker = new ShipDestroyedChecker();
		
		assertTrue(checker.shipIsAlive(ship));
	}
	
	@Test
	public void oneMastShipWithDamagegAllCellsIsNotAliveTest() {
		Ship ship = new Ship(1);
		ShipCellState[] shipCells = {ShipCellState.DESTROYED};
		List<ShipCellState> cellStates = Arrays.asList(shipCells);
		ship.setCellStates(cellStates);
		
		ShipDestroyedChecker checker = new ShipDestroyedChecker();
		
		assertFalse(checker.shipIsAlive(ship));
	}
}
