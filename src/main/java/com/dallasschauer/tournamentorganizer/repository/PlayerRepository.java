package com.dallasschauer.tournamentorganizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dallasschauer.tournamentorganizer.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer>{

}
