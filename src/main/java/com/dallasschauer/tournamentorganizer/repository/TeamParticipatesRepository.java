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
	int findIdByEventAndTeam (int eventId, int teamId);
}
