package com.dallasschauer.tournamentorganizer.entity;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name="player")
public class Player {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="dob")
	private Date dob;
	
	@Column(name="preferred_position")
	private String position;

	public Player() {
		super();
	}

	public Player(int id, String name, Date dob, String position) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.position = position;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
