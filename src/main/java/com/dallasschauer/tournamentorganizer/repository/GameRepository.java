package com.dallasschauer.tournamentorganizer.repository;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Pair;

import com.dallasschauer.tournamentorganizer.entity.Game;

public interface GameRepository extends JpaRepository<Game, Integer>{
	@Query(value="select * from game where event_id=? order by game_time", 
			nativeQuery=true)
	public List<Game> findGamesByEventId (int id);
	
	@Query(value="select * from game where ? in (away_team_id, home_team_id) order by game_time",
			nativeQuery=true)
	public List<Game> findGamesByTeamId (int id);
	
	@Query(value="select * from game where finished=false and event_id in "
			+ " (select event_id from team_participates where team_id in "
			+ " (select team_id from player_participates where player_id=?))"
			+ " order by game_time",
			nativeQuery=true)
	public List<Game> findFutureGames(int id);
	
	@Query(value="select team_id from team_participates where event_id=?",
			nativeQuery=true)
	public List<Integer> findTeamsByEventId (int id);
	
	@Query(value="select num_games from league_details where event_id=?",
			nativeQuery=true)
	public Integer findNumGamesFromEventId (int id);
	
	@Query(value="select COUNT(id) from game where event_id=? and finished=false",
			nativeQuery=true)
	public Integer findNumUnfinishedGamesFromEvent (int id);
	
	@Query(value="select COUNT(id) from game where event_id = :eventId and "
			+ "away_team_id in (:team1, :team2) and home_team_id in (:team1, :team2)",
			nativeQuery=true)
	public Integer findNumGamesBetweenTwoTeams(@Param("eventId") int eventId,
			@Param("team1") int team1,
			@Param("team2") int team2);
	
	@Modifying
	@Query(value="delete from game where event_id=?", nativeQuery=true)
	public void deleteEventGames(int id);
	
	@Query(value="select winner_id, count(id) from game where event_id=? and finished=true"
			+ " group by winner_id order by count(id) desc", nativeQuery=true)
	public List<Tuple> getTeamsWithWins(int id);
	
	@Query(value="select id, 0 from team where id in (select team_id from team_participates"
			+ " where event_id=:id) and id not in (select winner_id from game where event_id=:id)",
			nativeQuery=true)
	public List<Tuple> getTeamsWithNoWins(int id);
	
	@Query(value="select * from game where event_id=? order by round ascending, home_seed ascending", nativeQuery=true)
	public List<Game> findTournamentGames(int id);
	
	@Query(value="select max(round) from game where event_id=?", nativeQuery=true)
	public int findMaxRound(int id);
}
