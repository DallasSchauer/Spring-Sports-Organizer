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
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="name")
	private String name;
	
	@Column(name="dob")
	private Date dob;
	
	@Column(name="favorite_sport")
	private int favoriteSport;

	public Player() {
		super();
	}

	public Player(int id, String name, Date dob, int favoriteSport) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.favoriteSport = favoriteSport;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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

	public int getFavoriteSport() {
		return favoriteSport;
	}

	public void setPosition(int favoriteSport) {
		this.favoriteSport = favoriteSport;
	}
}
