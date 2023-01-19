package com.dallasschauer.tournamentorganizer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.dallasschauer.tournamentorganizer.entity.Team;
import com.dallasschauer.tournamentorganizer.entity.TeamParticipates;
import com.dallasschauer.tournamentorganizer.service.TeamParticipatesService;
import com.dallasschauer.tournamentorganizer.service.TeamService;

@RestController
public class TeamController {
	private final TeamService ts;
	private final TeamParticipatesService tps;
	
	@Autowired
	public TeamController (TeamService ts, TeamParticipatesService tps) {
		this.ts = ts;
		this.tps = tps;
	}
	
	/*
	 * API REQUESTS
	 * =============
	 */
	
	@RequestMapping("/api/teams")
	public ResponseEntity<?> findAll() {
		try {
			List<Team> ret = ts.findAll();
			return new ResponseEntity<List<Team>>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping("/api/teams/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") int id) {
		try {
			Team ret = ts.findById(id);
			return new ResponseEntity<Team>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/api/teams/new")
	public ResponseEntity<?> save(@RequestBody Team t) {
		try {
			Team ret = ts.save(t);
			return new ResponseEntity<Team>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/api/teams/{id}")
	public ResponseEntity<?> save(@RequestBody Team t, @PathVariable("id") int id) {
		try {
			t.setId(id);
			Team ret = ts.save(t);
			return new ResponseEntity<Team>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/api/teams/{id}")
	public ResponseEntity<?> deleteByid (@PathVariable("id") int id) {
		try {
			Team ret = ts.deleteById(id);
			return new ResponseEntity<Team>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/api/teams/{id}/addToEvent/{eventId}")
	public ResponseEntity<?> addTeamToEvent(@PathVariable("id") int id,
			@PathVariable("eventId") int eventId) {
		try {
			TeamParticipates tp = new TeamParticipates();
			tp.setTeamId(id);
			tp.setEventId(eventId);
			TeamParticipates ret = tps.save(tp);
			return new ResponseEntity<TeamParticipates>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/api/teams/event/{id}")
	public ResponseEntity<?> findAllTeamsByEventId(@PathVariable("id") int id) {
		try {
			List<Team> ret = ts.findAllTeamsByEventId(id);
			return new ResponseEntity<List<Team>>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
}
