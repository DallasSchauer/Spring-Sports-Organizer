package com.dallasschauer.tournamentorganizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dallasschauer.tournamentorganizer.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Integer>{
	@Query(value="select team_id from team_participates where event_id=?",
			nativeQuery=true)
	List<Integer> findAllTeamsInEvent(int eventId);
	
	@Query(value="select * from team where id in (select team_id from player_participates where " +
			" player_id=?)", nativeQuery=true)
	List<Team> findTeamsByPlayer(int playerId);
	
	@Query(value="select * from team where manager_id=?",
			nativeQuery=true)
	List<Team> findTeamsByManager(int managerId);
}
