package com.dallasschauer.tournamentorganizer.entity;

import java.sql.Time;
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
	private Time earliestTime;
	
	@Column(name="latest_time")
	private Time latestTime;
	
	@Column(name="tournament_type")
	private int tournamentType;
}
