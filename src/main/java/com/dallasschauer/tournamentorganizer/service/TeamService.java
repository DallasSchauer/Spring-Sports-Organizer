package com.dallasschauer.tournamentorganizer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dallasschauer.tournamentorganizer.entity.Team;
import com.dallasschauer.tournamentorganizer.exception.BusinessException;
import com.dallasschauer.tournamentorganizer.exception.EntryNotFoundException;
import com.dallasschauer.tournamentorganizer.repository.TeamRepository;

@Service
public class TeamService {
	private final TeamRepository tr;
	
	public TeamService (TeamRepository tr) {
		this.tr = tr;
	}
	
	public List<Team> findAll() {
		return tr.findAll();
	}
	
	public Team findById (int id) {
		Optional<Team> t = tr.findById(id);
		if (t.isEmpty()) {
			throw new EntryNotFoundException("No such account.");
		}
		return t.get();
	}
	
	public Team save (Team t) {
		return tr.save(t);
	}
	
	public void delete (Team t) {
		tr.delete(t);
	}
	
	public Team deleteById (int id) {
		Team ret = findById(id);
		tr.deleteById(id);
		return ret;
	}
	
	public List<Team> findAllTeamsByEventId (int id) {
		List<Integer> teamIds = tr.findAllTeamsInEvent(id);
		if (teamIds.isEmpty()) {
			throw new EntryNotFoundException("No teams for this event.");
		}
		List<Team> ret = new ArrayList<>();
		for (Integer i: teamIds) {
			Optional<Team> t = tr.findById(i);
			if (t.isPresent()) {
				ret.add(t.get());
			}
		}
		return ret;
	}
	
	public List<Team> findTeamsByPlayer (int playerId) {
		return tr.findTeamsByPlayer(playerId);
	}
	
	public List<Team> findTeamsByManager (int managerId) {
		return tr.findTeamsByManager(managerId);
	}
	
	public List<Team> findAllTeamsWithoutBye() {
		return tr.findAllTeamsWithoutBye();
	}
}
