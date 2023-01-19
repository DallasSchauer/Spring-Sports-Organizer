package com.dallasschauer.tournamentorganizer.entity;

import javax.persistence.*;

@Entity
@Table(name="team")
public class Team {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="manager_id")
	private int managerId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="sport")
	private int sport;
	
	@Column(name="min_age")
	private int minAge;
	
	@Column(name="max_age")
	private int maxAge;

	public Team() {
		super();
	}

	public Team(int id, int managerId, String name, int sport, int minAge, int maxAge) {
		super();
		this.id = id;
		this.managerId = managerId;
		this.name = name;
		this.sport = sport;
		this.minAge = minAge;
		this.maxAge = maxAge;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSport() {
		return sport;
	}

	public void setSport(int sport) {
		this.sport = sport;
	}

	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}
}
