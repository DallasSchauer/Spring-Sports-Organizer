package com.dallasschauer.tournamentorganizer.model;

import java.sql.Date;
import java.time.LocalTime;

import javax.persistence.*;

import org.springframework.stereotype.Component;

import com.dallasschauer.tournamentorganizer.entity.Event;
import com.dallasschauer.tournamentorganizer.entity.LeagueDetails;
import com.dallasschauer.tournamentorganizer.entity.TournamentDetails;

public class TotalEventDetails {
	

	// Event fields
	private int id;
	private String name;
	private int max_age;
	private int max_teams;
	private int sport;
	private int event_type;
	private double avg_hours;
	
	// LeagueDetails fields
	private Date start_date;
	private Date end_date;
	private int num_games;
	private long days;
	private String earliest_time;
	private String latest_time;
	private boolean tournament_at_end;
	
	// TournamentDetails fields
	private int tournament_type;

	
	public TotalEventDetails() {
		super();
	}

	public TotalEventDetails(int id, String name, int max_age, int max_teams, int sport, int event_type,
			double avg_hours, Date start_date, Date end_date, int num_games, long days, String earliest_time,
			String latest_time, boolean tournament_at_end, int tournament_type) {
		super();
		this.id = id;
		this.name = name;
		this.max_age = max_age;
		this.max_teams = max_teams;
		this.sport = sport;
		this.event_type = event_type;
		this.avg_hours = avg_hours;
		this.start_date = start_date;
		this.end_date = end_date;
		this.num_games = num_games;
		this.days = days;
		this.earliest_time = earliest_time;
		this.latest_time = latest_time;
		this.tournament_at_end = tournament_at_end;
		this.tournament_type = tournament_type;
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

	public int getMax_age() {
		return max_age;
	}

	public void setMax_age(int max_age) {
		this.max_age = max_age;
	}

	public int getMax_teams() {
		return max_teams;
	}

	public void setMax_teams(int max_teams) {
		this.max_teams = max_teams;
	}

	public int getSport() {
		return sport;
	}

	public void setSport(int sport) {
		this.sport = sport;
	}

	public int getEvent_type() {
		return event_type;
	}

	public void setEvent_type(int event_type) {
		this.event_type = event_type;
	}

	public double getAvg_hours() {
		return avg_hours;
	}

	public void setAvg_hours(double avg_hours) {
		this.avg_hours = avg_hours;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getNum_games() {
		return num_games;
	}

	public void setNum_games(int num_games) {
		this.num_games = num_games;
	}

	public long getDays() {
		return days;
	}

	public void setDays(long days) {
		this.days = days;
	}

	public String getEarliest_time() {
		return earliest_time;
	}

	public void setEarliest_time(String earliest_time) {
		this.earliest_time = earliest_time;
	}

	public String getLatest_time() {
		return latest_time;
	}

	public void setLatest_time(String latest_time) {
		this.latest_time = latest_time;
	}

	public boolean isTournament_at_end() {
		return tournament_at_end;
	}

	public void setTournament_at_end(boolean tournament_at_end) {
		this.tournament_at_end = tournament_at_end;
	}

	public int getTournament_type() {
		return tournament_type;
	}

	public void setTournament_type(int tournament_type) {
		this.tournament_type = tournament_type;
	}
	
	public Event getEventDetails () {
		Event ret = new Event();
		ret.setName(getName());
		ret.setMaxAge(getMax_age());
		ret.setMaxTeams(getMax_teams());
		ret.setSport(getSport());
		ret.setType(getEvent_type());
		ret.setAvgHours(getAvg_hours());
		return ret;
	}
	
	public TournamentDetails getTournamentDetails (int eventId) {
		TournamentDetails ret = new TournamentDetails();
		ret.setEventId(eventId);
		ret.setStartDate(this.getStart_date());
		ret.setEndDate(this.getEnd_date());
		ret.setEarliestTime(this.getEarliest_time());
		ret.setLatestTime(this.getLatest_time());
		ret.setTournamentType(this.getTournament_type());
		return ret;
	}
	
	public LeagueDetails getLeagueDetails (int eventId) {
		LeagueDetails ret = new LeagueDetails();
		ret.setEventId(eventId);
		ret.setStartDate(this.getStart_date());
		ret.setEndDate(this.getEnd_date());
		ret.setNumGames(this.getNum_games());
		ret.setEarliestTime(this.getEarliest_time());
		ret.setLatestTime(this.getLatest_time());
		ret.setTournamentAtEnd(this.isTournament_at_end());
		return ret;
	}
}
