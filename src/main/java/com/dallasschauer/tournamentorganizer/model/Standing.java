package com.dallasschauer.tournamentorganizer.model;

import java.math.BigInteger;

public class Standing {
	public Standing() {
		super();
	}
	
	public Standing(int id, String name, int wins) {
		super();
		this.id = id;
		this.name = name;
		this.wins = wins;
	}
	
	private int id;
	private String name;
	private int wins;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getWins() {
		return wins;
	}
	
	public void setWins(int wins) {
		this.wins = wins;
	}
}
