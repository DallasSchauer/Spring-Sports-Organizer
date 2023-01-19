package com.dallasschauer.tournamentorganizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dallasschauer.tournamentorganizer.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	
}
