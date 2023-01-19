package com.dallasschauer.tournamentorganizer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dallasschauer.tournamentorganizer.entity.Account;
import com.dallasschauer.tournamentorganizer.exception.BusinessException;
import com.dallasschauer.tournamentorganizer.exception.EntryNotFoundException;
import com.dallasschauer.tournamentorganizer.repository.AccountRepository;

@Service
public class AccountService {
	private final AccountRepository ar;
	
	public AccountService (AccountRepository ar) {
		this.ar = ar;
	}
	
	public List<Account> findAll() {
		return ar.findAll();
	}
	
	public Account findById (int id) {
		Optional<Account> a = ar.findById(id);
		if (a.isEmpty()) {
			throw new EntryNotFoundException("No such account.");
		} 
		return a.get();
	}
	
	public Account save (Account a) {
		return ar.save(a);
	}
	
	public void delete (Account a) {
		ar.delete(a);
	}
	
	public Account deleteById (int id) {
		Account ret = findById(id);
		ar.deleteById(id);
		return ret;
	}
}
