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
}
