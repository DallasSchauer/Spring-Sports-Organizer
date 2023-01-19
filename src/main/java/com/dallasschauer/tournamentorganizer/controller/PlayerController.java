package com.dallasschauer.tournamentorganizer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dallasschauer.tournamentorganizer.entity.Player;
import com.dallasschauer.tournamentorganizer.entity.PlayerParticipates;
import com.dallasschauer.tournamentorganizer.service.PlayerParticipatesService;
import com.dallasschauer.tournamentorganizer.service.PlayerService;

@RestController
@RequestMapping("/api")
public class PlayerController {
	private final PlayerService ps;
	private final PlayerParticipatesService pps;
	
	@Autowired
	public PlayerController (PlayerService ps, PlayerParticipatesService pps) {
		this.ps = ps;
		this.pps = pps;
	}
	
	@RequestMapping("/players")
	public ResponseEntity<?> findAll() {
		try {
			List<Player> ret = ps.findAll();
			return new ResponseEntity<List<Player>>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping("/players/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") int id) {
		try {
			Player ret = ps.findById(id);
			return new ResponseEntity<Player>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/players/new")
	public ResponseEntity<?> save(@RequestBody Player p) {
		try {
			Player ret = ps.save(p);
			return new ResponseEntity<Player>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/players/{id}")
	public ResponseEntity<?> save(@RequestBody Player p, @PathVariable("id") int id) {
		try {
			p.setId(id);
			Player ret = ps.save(p);
			return new ResponseEntity<Player>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/players/{id}")
	public ResponseEntity<?> deleteByid (@PathVariable("id") int id) {
		try {
			Player ret = ps.deleteById(id);
			return new ResponseEntity<Player>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/players/{id}/addToTeam/{teamId}")
	public ResponseEntity<?> addPlayerToTeam (@PathVariable("id") int id,
			@PathVariable("teamId") int teamId) {
		try {
			PlayerParticipates pp = new PlayerParticipates();
			pp.setPlayerId(id);
			pp.setTeamId(teamId);
			PlayerParticipates ret = pps.save(pp);
			return new ResponseEntity<PlayerParticipates>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
}
