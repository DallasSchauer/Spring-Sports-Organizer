package com.dallasschauer.tournamentorganizer.model;

public class Seed {
	private int teamId;
	private int seed;
	
	public Seed(int teamId, int seed) {
		super();
		this.teamId = teamId;
		this.seed = seed;
	}
	
	public Seed() {
		super();
	}
	
	
	public int getTeamId() {
		return teamId;
	}
	
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	
	public int getSeed() {
		return seed;
	}
	
	public void setSeed(int seed) {
		this.seed = seed;
	}
}
