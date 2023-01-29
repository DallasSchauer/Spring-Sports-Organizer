package com.dallasschauer.tournamentorganizer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dallasschauer.tournamentorganizer.utils.Utils;
import com.dallasschauer.tournamentorganizer.entity.PlayerParticipates;
import com.dallasschauer.tournamentorganizer.entity.Team;
import com.dallasschauer.tournamentorganizer.exception.BusinessException;
import com.dallasschauer.tournamentorganizer.exception.EntryNotFoundException;
import com.dallasschauer.tournamentorganizer.repository.PlayerParticipatesRepository;

@Service
public class PlayerParticipatesService {
	private final PlayerParticipatesRepository ppr;
	
	public PlayerParticipatesService (PlayerParticipatesRepository ppr) {
		this.ppr = ppr;
	}
	
	public List<PlayerParticipates> findAll() {
		return ppr.findAll();
	}
	
	public PlayerParticipates findById (int id) {
		Optional<PlayerParticipates> p = ppr.findById(id);
		if (p.isEmpty()) {
			throw new EntryNotFoundException("No such player/team entry.");
		}
		return p.get();
	}
	
	public PlayerParticipates save (PlayerParticipates p) {
		Integer playerAge = Utils.findAge(ppr.findDateOfBirth(p.getPlayerId()));
		Integer maxAge = ppr.findMaxAgeOfEvent(p.getTeamId());
		if (playerAge == null || maxAge == null) {
			throw new EntryNotFoundException("Either player or event does not exist.");
		}
		if (playerAge > maxAge) {
			throw new BusinessException("Player is too old for team.");
		}
		return ppr.save(p);
	}
	
	public void delete (PlayerParticipates p) {
		ppr.delete(p);
	}
	
	public PlayerParticipates deleteById (int id) {
		PlayerParticipates ret = findById(id);
		ppr.deleteById(id);
		return ret;
	}
	
	public List<PlayerParticipates> findTeamByPlayer (int id) {
		return ppr.findByPlayerId(id);
	}
	
	public List<PlayerParticipates> findByTeam (int id) {
		return ppr.findByTeamId(id);
	}
}
