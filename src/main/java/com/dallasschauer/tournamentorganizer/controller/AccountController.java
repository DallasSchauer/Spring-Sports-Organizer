package com.dallasschauer.tournamentorganizer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dallasschauer.tournamentorganizer.entity.Account;
import com.dallasschauer.tournamentorganizer.service.AccountService;

@RestController
@RequestMapping("/api")
public class AccountController {
	private final AccountService as;
	
	@Autowired
	public AccountController (AccountService as) {
		this.as = as;
	}
	
	@RequestMapping("/accounts")
	public ResponseEntity<?> findAll() {
		try {
			List<Account> ret = as.findAll();
			return new ResponseEntity<List<Account>>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping("/accounts/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") int id) {
		try {
			Account ret = as.findById(id);
			return new ResponseEntity<Account>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/accounts/new")
	public ResponseEntity<?> save(@RequestBody Account a) {
		try {
			Account ret = as.save(a);
			return new ResponseEntity<Account>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/accounts/{id}")
	public ResponseEntity<?> save(@RequestBody Account a, @PathVariable("id") int id) {
		try {
			a.setId(id);
			Account ret = as.save(a);
			return new ResponseEntity<Account>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/accounts/{id}")
	public ResponseEntity<?> deleteByid (@PathVariable("id") int id) {
		try {
			Account ret = as.deleteById(id);
			return new ResponseEntity<Account>(ret, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
}
