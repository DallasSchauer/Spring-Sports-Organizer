package com.dallasschauer.tournamentorganizer.model;

import com.dallasschauer.tournamentorganizer.entity.Team;

public class Seed {
	private int id;
	private String name;
	private int seed;
	
	public Seed(int id, String name, int seed) {
		this.id = id;
		this.name = name;
		this.seed = seed;
	}
	
	public Seed() {
		this.id = 0;
		this.name = "";
		this.seed = 0;
	}
	
	
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
	
	public int getSeed() {
		return seed;
	}
	
	public void setSeed(int seed) {
		this.seed = seed;
	}
}
