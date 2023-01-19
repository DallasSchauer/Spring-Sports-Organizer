package com.dallasschauer.tournamentorganizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dallasschauer.tournamentorganizer.entity.LeagueDetails;

public interface LeagueDetailsRepository extends JpaRepository<LeagueDetails, Integer> {
	
}
