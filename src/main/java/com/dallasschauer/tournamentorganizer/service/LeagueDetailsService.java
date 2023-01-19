package com.dallasschauer.tournamentorganizer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dallasschauer.tournamentorganizer.entity.LeagueDetails;
import com.dallasschauer.tournamentorganizer.exception.BusinessException;
import com.dallasschauer.tournamentorganizer.exception.EntryNotFoundException;
import com.dallasschauer.tournamentorganizer.repository.LeagueDetailsRepository;

@Service
public class LeagueDetailsService {
	private final LeagueDetailsRepository ldr;
	
	public LeagueDetailsService (LeagueDetailsRepository ldr) {
		this.ldr = ldr;
	}
	
	public List<LeagueDetails> findAll() {
		return ldr.findAll();
	}
	
	public LeagueDetails findById (int id) {
		Optional<LeagueDetails> l = ldr.findById(id);
		if (l.isEmpty()) {
			throw new EntryNotFoundException("No such league.");
		}
		return l.get();
	}
	
	public LeagueDetails save (LeagueDetails ld) {
		return ldr.save(ld);
	}
	
	public void delete (LeagueDetails ld) {
		ldr.delete(ld);
	}
	
	public LeagueDetails deleteById (int id) {
		LeagueDetails ret = findById(id);
		ldr.deleteById(id);
		return ret;
	}
}
