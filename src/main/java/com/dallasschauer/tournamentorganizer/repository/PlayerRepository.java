package com.dallasschauer.tournamentorganizer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dallasschauer.tournamentorganizer.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer>{
	@Query(value="select * from player where id in (select player_id from"
			+ " player_participates where team_id=?)", nativeQuery=true)
	public List<Player> findPlayersByTeam(int id);
	
	public Optional<Player> findPlayerByUsername(String username);
}
