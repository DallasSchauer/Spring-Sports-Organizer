package com.dallasschauer.tournamentorganizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dallasschauer.tournamentorganizer.entity.TournamentDetails;

public interface TournamentDetailsRepository extends JpaRepository<TournamentDetails, Integer> {
	
}
