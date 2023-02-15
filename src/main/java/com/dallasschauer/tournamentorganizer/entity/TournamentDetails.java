package com.dallasschauer.tournamentorganizer.entity;

import java.time.LocalTime;
import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name="tournament_details")
public class TournamentDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="event_id")
	private int eventId;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="earliest_time")
	private LocalTime earliestTime;
	
	@Column(name="latest_time")
	private LocalTime latestTime;
	
	@Column(name="tournament_type")
	private int tournamentType;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public LocalTime getEarliestTime() {
		return earliestTime;
	}

	public void setEarliestTime(LocalTime earliestTime) {
		this.earliestTime = earliestTime;
	}

	public LocalTime getLatestTime() {
		return latestTime;
	}

	public void setLatestTime(LocalTime latestTime) {
		this.latestTime = latestTime;
	}

	public int getTournamentType() {
		return tournamentType;
	}

	public void setTournamentType(int tournamentType) {
		this.tournamentType = tournamentType;
	}
}
