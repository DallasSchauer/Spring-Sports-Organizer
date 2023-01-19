package com.dallasschauer.tournamentorganizer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dallasschauer.tournamentorganizer.entity.Game;
import com.dallasschauer.tournamentorganizer.service.GameService;

@RestController
@RequestMapping("/api")
public class GameController {
	private final GameService gs;
	
	@Autowired
	public GameController (GameService gs) {
		this.gs = gs;
	}
	
	@RequestMapping("/games")
	public ResponseEntity<?> findAll() {
		try {
			List<Game> ret = gs.findAll();
			return new ResponseEntity<List<Game>>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping("/games/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") int id) {
		try {
			Game ret = gs.findById(id);
			return new ResponseEntity<Game>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/games/new")
	public ResponseEntity<?> save(@RequestBody Game g) {
		try {
			Game ret = gs.save(g);
			return new ResponseEntity<Game>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/games/{id}")
	public ResponseEntity<?> save(@RequestBody Game g, @PathVariable("id") int id) {
		try {
			g.setId(id);
			Game ret = gs.save(g);
			return new ResponseEntity<Game>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/games/{id}")
	public ResponseEntity<?> deleteByid (@PathVariable("id") int id) {
		try {
			Game ret = gs.deleteById(id);
			return new ResponseEntity<Game>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/events/{eventId}/createTournament")
	public ResponseEntity<?> createTournament (@PathVariable("eventId") int eventId) {
		try {
			List<Integer> ints = gs.findTeamsByEventId(eventId);
			Game ret = gs.createTournamentBracket(eventId, ints.size());
			return new ResponseEntity<Game>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/events/{eventId}/createLeague")
	public ResponseEntity<?> createLeague (@PathVariable("eventId") int eventId) {
		try {
			gs.createSeasonSchedule(eventId);
			return new ResponseEntity<String>("Schedule created", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping("/events/{eventId}/findGamesBetweenTeams/{team1}/{team2}")
	public ResponseEntity<?> findNumGamesBetweenTwoTeams(@PathVariable("eventId") int eventId,
			@PathVariable("team1") int team1, @PathVariable("team2") int team2) {
		try {
			int ret = gs.findNumGamesBetweenTwoTeams(eventId, team1, team2);
			return new ResponseEntity<Integer>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
}
