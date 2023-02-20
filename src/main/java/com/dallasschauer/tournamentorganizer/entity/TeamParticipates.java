package com.dallasschauer.tournamentorganizer.entity;

import javax.persistence.*;

@Entity
@Table(name="team_participates")
public class TeamParticipates {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="event_id")
	private int eventId;
	
	@Column(name="team_id")
	private int teamId;

	public TeamParticipates() {
		super();
	}

	public TeamParticipates(int id, int eventId, int teamId) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.teamId = teamId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
}
