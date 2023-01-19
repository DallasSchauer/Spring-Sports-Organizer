package com.dallasschauer.tournamentorganizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dallasschauer.tournamentorganizer.entity.PlayerParticipates;

public interface PlayerParticipatesRepository extends JpaRepository<PlayerParticipates, Integer> {
	@Query(value="select dob from player where id=?", nativeQuery=true)
	java.sql.Date findDateOfBirth(int id);
	
	@Query(value="select max_age from event where id=?", nativeQuery=true)
	int findMaxAgeOfEvent(int id);
	
	@Query(value="select * from player_participates where player_id=?",
			nativeQuery=true)
	List<PlayerParticipates> findByPlayerId(int playerId);
	
	@Query(value="select * from player_participates where team_id=?",
			nativeQuery=true)
	List<PlayerParticipates> findByTeamId(int teamId);
	
	@Query(value="select * from team_participates where team_id=? and player_id=?",
			nativeQuery=true)
	int findIdByTeamAndPlayer (int teamId, int playerId);
}
