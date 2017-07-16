package com.java_academy.battleship.model;

public class Player {
	
	private String nickname;
	private Integer score;
	private Integer nukeCounter;
	
	public Player(String nickname, Integer score, Integer nukeCounter) {
		super();
		this.nickname = nickname;
		this.score = score;
		this.nukeCounter = nukeCounter;
	}
	
	public String getNickname() {
		return nickname;
	}
	public Integer getScore() {
		return score;
	}
	public Integer getNukeCounter() {
		return nukeCounter;
	}
	
	@Override
	public String toString() {
		return "Player [nickname=" + nickname + ", score=" + score + ", nukeCounter=" + nukeCounter + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (nukeCounter == null) {
			if (other.nukeCounter != null)
				return false;
		} else if (!nukeCounter.equals(other.nukeCounter))
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		return true;
	}
	
	
}
