package com.dallasschauer.tournamentorganizer.entity;

import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="game")
@JsonIgnoreProperties({"parent", "left", "right"})
public class Game {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="event_id")
	private int eventId;
	
	@Column(name="game_time")
	private Timestamp gameTime;
	
	@Column(name="away_team_id")
	private int awayTeam;
	
	@Column(name="away_seed")
	private int awaySeed;
	
	@Column(name="home_team_id")
	private int homeTeam;
	
	@Column(name="home_seed")
	private int homeSeed;
	
	@Column(name="away_score")
	private int awayScore;
	
	@Column(name="home_score")
	private int homeScore;
	
	@Column(name="finished")
	private boolean finished;
	
	@Column(name="winner_id")
	private int winner;
	
	@Column(name="parent")
	private Integer parentId;
	
	@Column(name="round")
	private int round;
	
	@Transient
	private Game parent;

	@Transient
	private Game left;
	
	@Transient
	private Game right;

	public Game(int id, int eventId, Timestamp gameTime, int awayTeam, int homeTeam, int awayScore, int homeScore,
			boolean finished, int winner, Integer parentId, Game parent, Game left, Game right, int round) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.gameTime = gameTime;
		this.awayTeam = awayTeam;
		this.homeTeam = homeTeam;
		this.awayScore = awayScore;
		this.homeScore = homeScore;
		this.finished = finished;
		this.winner = winner;
		this.parentId = parentId;
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.round = round;
	}
	
	public Game() {
		super();
		this.gameTime = null;
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

	public Timestamp getGameTime() {
		return gameTime;
	}

	public void setGameTime(Timestamp gameTime) {
		this.gameTime = gameTime;
	}

	public int getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(int awayTeam) {
		this.awayTeam = awayTeam;
	}

	public int getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(int homeTeam) {
		this.homeTeam = homeTeam;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}
	
	public Integer getParentId() {
		return parentId;
	}
	
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	public Game getParent() {
		return parent;
	}

	public void setParent(Game parent) {
		this.parent = parent;
	}

	public Game getLeft() {
		return left;
	}

	public void setLeft(Game left) {
		this.left = left;
	}

	public Game getRight() {
		return right;
	}

	public void setRight(Game right) {
		this.right = right;
	}

	public int getAwaySeed() {
		return awaySeed;
	}

	public void setAwaySeed(int awaySeed) {
		this.awaySeed = awaySeed;
	}

	public int getHomeSeed() {
		return homeSeed;
	}

	public void setHomeSeed(int homeSeed) {
		this.homeSeed = homeSeed;
	}
	
	public int getRound() {
		return round;
	}
	
	public void setRound(int round) {
		this.round = round;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", eventId=" + eventId + ", gameTime=" + gameTime + ", awayTeam=" + awayTeam
				+ ", awaySeed=" + awaySeed + ", homeTeam=" + homeTeam + ", homeSeed=" + homeSeed + ", awayScore="
				+ awayScore + ", homeScore=" + homeScore + ", finished=" + finished + ", winner=" + winner
				+ ", parentId=" + parentId + ", parent=" + parent + ", left=" + left + ", right=" + right + ", getId()="
				+ getId() + ", getEventId()=" + getEventId() + ", getGameTime()=" + getGameTime() + ", getAwayTeam()="
				+ getAwayTeam() + ", getHomeTeam()=" + getHomeTeam() + ", getAwayScore()=" + getAwayScore()
				+ ", getHomeScore()=" + getHomeScore() + ", isFinished()=" + isFinished() + ", getWinner()="
				+ getWinner() + ", getParentId()=" + getParentId() + ", getParent()=" + getParent() + ", getLeft()="
				+ getLeft() + ", getRight()=" + getRight() + ", getAwaySeed()=" + getAwaySeed() + ", getHomeSeed()="
				+ getHomeSeed() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}
