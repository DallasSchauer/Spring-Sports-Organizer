package com.dallasschauer.tournamentorganizer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dallasschauer.tournamentorganizer.entity.TournamentDetails;
import com.dallasschauer.tournamentorganizer.exception.BusinessException;
import com.dallasschauer.tournamentorganizer.exception.EntryNotFoundException;
import com.dallasschauer.tournamentorganizer.repository.TournamentDetailsRepository;

@Service
public class TournamentDetailsService {
	private final TournamentDetailsRepository tdr;
	
	public TournamentDetailsService (TournamentDetailsRepository tdr) {
		this.tdr = tdr;
	}
	
	public List<TournamentDetails> findAll() {
		return tdr.findAll();
	}
	
	public TournamentDetails findById (int id) {
		Optional<TournamentDetails> td = tdr.findById(id);
		if (td.isEmpty()) {
			throw new EntryNotFoundException("No such tournament.");
		}
		return td.get();
	}
	
	public TournamentDetails save (TournamentDetails td) {
		return tdr.save(td);
	}
	
	public TournamentDetails deleteById (int id) {
		TournamentDetails ret = findById(id);
		tdr.deleteById(id);
		return ret;
	}
}
