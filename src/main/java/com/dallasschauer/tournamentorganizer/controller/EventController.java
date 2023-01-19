package com.dallasschauer.tournamentorganizer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dallasschauer.tournamentorganizer.entity.Event;
import com.dallasschauer.tournamentorganizer.model.TotalEventDetails;
import com.dallasschauer.tournamentorganizer.service.EventService;

@RestController
@RequestMapping("/api")
public class EventController {
	private final EventService es;
	
	@Autowired
	public EventController (EventService es) {
		this.es = es;
	}
	
	@RequestMapping("/events")
	public ResponseEntity<?> findAll() {
		try {
			List<Event> ret = es.findAll();
			return new ResponseEntity<List<Event>>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping("/events/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") int id) {
		try {
			Event ret = es.findById(id);
			return new ResponseEntity<Event>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/events/new")
	public ResponseEntity<?> save(@RequestBody Event e) {
		try {
			Event ret = es.save(e);
			return new ResponseEntity<Event>(ret, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/events/{id}")
	public ResponseEntity<?> save(@RequestBody Event e, @PathVariable("id") int id) {
		try {
			e.setId(id);
			Event ret = es.save(e);
			return new ResponseEntity<Event>(ret, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/events/{id}")
	public ResponseEntity<?> deleteByid (@PathVariable("id") int id) {
		try {
			Event ret = es.deleteById(id);
			return new ResponseEntity<Event>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/events/leagues")
	public ResponseEntity<?> findAllLeagues () {
		try {
			List<Event> ret = es.findAllLeagues();
			return new ResponseEntity<List<Event>>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/events/tournaments")
	public ResponseEntity<?> findAllTournaments () {
		try {
			List<Event> ret = es.findAllTournaments();
			return new ResponseEntity<List<Event>>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
}
