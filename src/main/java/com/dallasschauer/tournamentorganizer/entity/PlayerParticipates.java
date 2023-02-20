package com.dallasschauer.tournamentorganizer.entity;

import javax.persistence.*;

@Entity
@Table(name="player_participates")
public class PlayerParticipates {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="team_id")
	private int teamId;
	
	@Column(name="player_id")
	private int playerId;
	
	public PlayerParticipates() {
		super();
	}

	public PlayerParticipates(int id, int teamId, int playerId) {
		super();
		this.id = id;
		this.teamId = teamId;
		this.playerId = playerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
}
