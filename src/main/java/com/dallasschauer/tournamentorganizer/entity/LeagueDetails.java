package com.dallasschauer.tournamentorganizer.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.*;

@Entity
@Table(name="league_details")
public class LeagueDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="event_id")
	private int eventId;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="num_games")
	private int numGames;
	
	@Column(name="days")
	private long days;
	
	@Column(name="earliest_time")
	private Time earliestTime;
	
	@Column(name="latest_time")
	private Time latestTime;
	
	@Column(name="tournament_at_end")
	private boolean tournamentAtEnd;

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

	public int getNumGames() {
		return numGames;
	}

	public void setNumGames(int numGames) {
		this.numGames = numGames;
	}

	public long getDays() {
		return days;
	}

	public void setDays(long days) {
		this.days = days;
	}

	public Time getEarliestTime() {
		return earliestTime;
	}

	public void setEarliestTime(Time earliestTime) {
		this.earliestTime = earliestTime;
	}

	public Time getLatestTime() {
		return latestTime;
	}

	public void setLatestTime(Time latestTime) {
		this.latestTime = latestTime;
	}

	public boolean isTournamentAtEnd() {
		return tournamentAtEnd;
	}

	public void setTournamentAtEnd(boolean tournamentAtEnd) {
		this.tournamentAtEnd = tournamentAtEnd;
	}
}
