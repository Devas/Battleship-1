package com.java_academy.battleship.business_logic.randomFleet;

import java.util.Random;

import org.apache.log4j.Logger;

import com.java_academy.battleship.business_logic.FleetSetter;
import com.java_academy.battleship.model.Ship;
import com.java_academy.battleship.model.Ships;

/**
 * Class for random ship location 
 * @author Artur
 * */
public class Randomizer {

	Logger logger = Logger.getLogger("stdout");
	Ships ships;
	Random rand = new Random();
	FleetSetter fleetSetter;
	
	public Randomizer(Ships ships, FleetSetter fleetSetter) {
		this.ships = ships;
		this.fleetSetter = fleetSetter;
		logger.info("Player XX used randomizer.");
	}
	
	public void setFleet() {
		for(Ship ship: ships.getFleet()) {
			setShipOnBoard(ship);
		}
	}
	
	void setShipOnBoard(Ship ship) {
		boolean isVertical;
		int startPoint;
		do {
			startPoint = findStartPoint();
			isVertical = randomDirection();
		} while (!fleetSetter.setIfPossible(startPoint, ship, isVertical));
	}


	private int findStartPoint() {
		return randomIntegerInScope(0, 99);
	}
	
	Integer randomIntegerInScope(int lowerRange, int upperRange) {
		return rand.nextInt(upperRange - lowerRange + 1) + lowerRange;
	}
	
	Boolean randomDirection() {
		return rand.nextBoolean();
	}
}
