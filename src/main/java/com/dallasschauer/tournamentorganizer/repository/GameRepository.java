package com.dallasschauer.tournamentorganizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dallasschauer.tournamentorganizer.entity.Game;

public interface GameRepository extends JpaRepository<Game, Integer>{
	@Query(value="select * from game where event_id=? order by game_time", 
			nativeQuery=true)
	public List<Game> findGamesByEventId (int id);
	
	@Query(value="select * from game where ? in (away_team_id, home_team_id) order by game_time",
			nativeQuery=true)
	public List<Game> findGamesByTeamId (int id);
	
	@Query(value="select * from game where finished=false order by game_time",
			nativeQuery=true)
	public List<Game> findFutureGames();
	
	@Query(value="select team_id from team_participates where event_id=?",
			nativeQuery=true)
	public List<Integer> findTeamsByEventId (int id);
	
	@Query(value="select num_games from league_details where event_id=?",
			nativeQuery=true)
	public int findNumGamesFromEventId (int id);
	
	@Query(value="select COUNT(id) from game where event_id = :eventId and "
			+ "away_team_id in (:team1, :team2) and home_team_id in (:team1, :team2)",
			nativeQuery=true)
	public int findNumGamesBetweenTwoTeams(@Param("eventId") int eventId,
			@Param("team1") int team1,
			@Param("team2") int team2);
}
