package com.dallasschauer.tournamentorganizer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dallasschauer.tournamentorganizer.entity.TeamParticipates;
import com.dallasschauer.tournamentorganizer.exception.BusinessException;
import com.dallasschauer.tournamentorganizer.exception.EntryNotFoundException;
import com.dallasschauer.tournamentorganizer.repository.TeamParticipatesRepository;
import com.dallasschauer.tournamentorganizer.utils.Utils;

@Service
public class TeamParticipatesService {
	final private TeamParticipatesRepository tpr;
	
	public TeamParticipatesService (TeamParticipatesRepository tpr) {
		this.tpr = tpr;
	}
	
	public List<TeamParticipates> findAll() {
		return tpr.findAll();
	}
	
	public TeamParticipates findById (int id) {
		Optional<TeamParticipates> tp = tpr.findById(id);
		if (tp.isEmpty()) {
			throw new EntryNotFoundException("No such account.");
		}
		return tp.get();
	}
	
	public TeamParticipates save (TeamParticipates tp) {
		return tpr.save(tp);
	}
	
	public TeamParticipates deleteById (int id) {
		TeamParticipates ret = findById(id);
		tpr.deleteById(id);
		return ret;
	}
	
	public int findOldestAge (int id) {
		java.sql.Date dob = tpr.findOldestPlayer(id);
		if (dob == null) {
			return 0;
		}
		return Utils.findAge(dob);
	}
	
	public List<TeamParticipates> findByEventAndTeam (int eventId, int teamId) {
		return tpr.findIdByEventAndTeam(eventId, teamId);
	}
	
	public Integer findSeedByEventAndTeam (int eventId, int teamId) {
		if (tpr.findTeamFromEventAndSeed(eventId, teamId) == null) {
			return null;
		}
		return tpr.findTeamFromEventAndSeed(eventId, teamId);
	}
}
