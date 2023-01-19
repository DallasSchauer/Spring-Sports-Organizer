package com.dallasschauer.tournamentorganizer.exception;

public class EntryNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
	
	public EntryNotFoundException (String message) {
		super(message);
		this.message = message;
	}
	
	public EntryNotFoundException() {
		
	}
}

