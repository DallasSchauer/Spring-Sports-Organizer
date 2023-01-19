package com.dallasschauer.tournamentorganizer.utils;

import java.time.LocalDate;
import java.time.Period;

public class Utils {
	public static int findAge (java.sql.Date date) {
		LocalDate now = LocalDate.now();
		LocalDate birth = date.toLocalDate();
		return Period.between(birth, now).getYears();
	}
}
