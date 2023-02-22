package com.dallasschauer.tournamentorganizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dallasschauer.tournamentorganizer.entity.Event;
import java.util.List;
import com.dallasschauer.tournamentorganizer.model.TotalEventDetails;

public interface EventRepository extends JpaRepository<Event, Integer>{
	@Query(value="select * from event where event_type=0", nativeQuery=true)
	List<Event> findAllLeagues();
	
	@Query(value="select * from event where event_type=1", nativeQuery=true)
	List<Event> findAllTournaments();
	
	@Query(value="select * from event where sport=?", nativeQuery=true)
	List<Event> findEventBySport(int i);
	
	@Query(value="select * from event where id in (select event_id from team_participates"
			+ " where team_id=?)", nativeQuery=true)
	List<Event> findEventsByTeamId(int id);
	
	@Query(value="select * from event where id in (select event_id from team_participates "
			+ " where team_id in (select team_id from player_participates where player_id=?))",
			nativeQuery=true)
	List<Event> findEventByPlayer(int id);

}
