//package com.dallasschauer.tournamentorganizer.controller;
//
//import java.util.List;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.dallasschauer.tournamentorganizer.model.TotalEventDetails;
//import com.dallasschauer.tournamentorganizer.service.TotalDetailsService;
//
//@RequestMapping("/api")
//public class TotalEventDetailsController {
//	private final TotalDetailsService teds;
//	
//	public TotalEventDetailsController (TotalDetailsService teds) {
//		this.teds = teds;
//	}
//	
//	@RequestMapping("/events/leagues")
//	public ResponseEntity<?> findAllLeagues() {
//		try { 
//			List<TotalEventDetails> ret = teds.findAllLeagues();
//			return new ResponseEntity<List<TotalEventDetails>>(ret, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
//		}
//	}
//		
//	@RequestMapping("/events/tournaments")
//	public ResponseEntity<?> findAllTournaments() {
//		try {
//			List<TotalEventDetails> ret = teds.findAllTournaments();
//			return new ResponseEntity<List<TotalEventDetails>>(ret, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
//		}
//	}
//}
