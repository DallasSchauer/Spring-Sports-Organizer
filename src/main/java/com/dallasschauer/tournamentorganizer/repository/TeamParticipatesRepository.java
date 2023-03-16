package com.dallasschauer.tournamentorganizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dallasschauer.tournamentorganizer.entity.TeamParticipates;

public interface TeamParticipatesRepository extends JpaRepository<TeamParticipates, Integer> {
	@Query(value="select * from team_participates where event_id=?",
			nativeQuery=true)
	List<TeamParticipates> findByEventId(int eventId);
	
	@Query(value="select * from team_participates where team_id=?",
			nativeQuery=true)
	List<TeamParticipates> findByTeamId(int teamId);
	
	@Query(value="select * from team_participates where event_id=? and team_id=?",
			nativeQuery=true)
	List<TeamParticipates> findIdByEventAndTeam (int eventId, int teamId);
	
	@Query(value="select min(dob) from player where id in (select player_id from"
			+ " player_participates where team_id=?)", nativeQuery=true)
	java.sql.Date findOldestPlayer (int teamId);
	
	@Query(value="select team_id from team_participates where event_id=? and seed=?",
			nativeQuery=true)
	Integer findTeamFromEventAndSeed(int eventId, int seed);
	
}
