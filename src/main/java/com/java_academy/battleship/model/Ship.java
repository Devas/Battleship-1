package com.java_academy.battleship.model;

import java.util.ArrayList;
import java.util.List;

public class Ship {
	
	private int hp;
	//TODO change for cell
	//private List<Cell> cells;
	private List<Integer> indexes;
	
	public Ship(int hp) {
		this.hp = hp;
		indexes = new ArrayList<Integer>();
	}

	boolean isAlive() {
		if(hp > 0) {
			return true;
		}
		return false;
	}

	public int getHp() {
		return hp;
	}
	
	public void addIndex(Integer index) {
		indexes.add(index);
	}

}
