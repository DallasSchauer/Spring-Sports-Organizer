package com.dallasschauer.tournamentorganizer.entity;

import javax.persistence.*;

@Entity
@Table(name="event")
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="commissioner")
	private int commissioner;
	
	@Column(name="max_age")
	private int maxAge;
	
	@Column(name="max_teams")
	private int maxTeams;
	
	@Column(name="sport")
	private int sport;
	
	@Column(name="event_type")
	private int type;
	
	@Column(name="avg_hours")
	private double avgHours;
	
	public Event() {
		super();
	}

	public Event(int id, String name, int maxAge, int maxTeams, int sport, int type, double avgHours) {
		super();
		this.id = id;
		this.name = name;
		this.maxAge = maxAge;
		this.maxTeams = maxTeams;
		this.sport = sport;
		this.type = type;
		this.avgHours = avgHours;
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
	
	public int getCommissioner() {
		return commissioner;
	}
	
	public void setCommissioner(int commissioner) {
		this.commissioner = commissioner;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public int getMaxTeams() {
		return maxTeams;
	}

	public void setMaxTeams(int maxTeams) {
		this.maxTeams = maxTeams;
	}

	public int getSport() {
		return sport;
	}

	public void setSport(int sport) {
		this.sport = sport;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getAvgHours() {
		return avgHours;
	}

	public void setAvgHours(double avgHours) {
		this.avgHours = avgHours;
	}
}
