package com.dallasschauer.tournamentorganizer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dallasschauer.tournamentorganizer.entity.Event;
import com.dallasschauer.tournamentorganizer.model.TotalEventDetails;
import com.dallasschauer.tournamentorganizer.exception.BusinessException;
import com.dallasschauer.tournamentorganizer.exception.EntryNotFoundException;
import com.dallasschauer.tournamentorganizer.repository.EventRepository;

@Service
public class EventService {
	private final EventRepository er;
	
	public EventService (EventRepository er) {
		this.er = er;
	}
	
	public List<Event> findAll() {
		return er.findAll();
	}
	
	public Event findById (int id) {
		Optional<Event> e = er.findById(id);
		if (e.isEmpty()) {
			throw new EntryNotFoundException("No such event.");
		}
		return e.get();
	}
	
	public Event save (Event e) {
		return er.save(e);
	}
	
	public void delete (Event e) {
		er.delete(e);
	}
	
	public Event deleteById (int id) {
		Event ret = findById(id);
		er.deleteById(id);
		return ret;
	}
	
	public List<Event> findAllLeagues() {
		return er.findAllLeagues();
	}
	
	public List<Event> findAllTournaments() {
		return er.findAllTournaments();
	}
	
	public List<Event> findEventBySport(int i) {
		return er.findEventBySport(i);
	}
}
