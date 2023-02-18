package com.dallasschauer.tournamentorganizer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dallasschauer.tournamentorganizer.entity.Player;
import com.dallasschauer.tournamentorganizer.exception.BusinessException;
import com.dallasschauer.tournamentorganizer.exception.EntryNotFoundException;
import com.dallasschauer.tournamentorganizer.repository.PlayerRepository;

@Service
public class PlayerService {
	private final PlayerRepository pr;
	
	public PlayerService (PlayerRepository pr) {
		this.pr = pr;
	}
	
	public List<Player> findAll() {
		return pr.findAll();
	}
	
	public Player findById (int id) {
		Optional<Player> p = pr.findById(id);
		if (p.isEmpty()) {
			throw new EntryNotFoundException("No such account.");
		}
		return p.get();
	}
	
	public Player save (Player p) {
		return pr.save(p);
	}
	
	public void delete (Player p) {
		pr.delete(p);
	}
	
	public Player deleteById (int id) {
		Player ret = findById(id);
		pr.deleteById(id);
		return ret;
	}
	
	public List<Player> findPlayersByTeam(int id) {
		return pr.findPlayersByTeam(id);
	}
	
	public Player checkLogin (String username, String password) {
		Optional<Player> res = pr.findPlayerByUsername(username);
		if (res.isEmpty()) {
			return null;
		} 
		
		Player p = res.get();
		
		if (p.getPassword().equals(password)) {
			return p;
		}
		
		return null;
	}
}
