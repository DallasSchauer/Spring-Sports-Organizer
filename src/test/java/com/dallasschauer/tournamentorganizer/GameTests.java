package com.dallasschauer.tournamentorganizer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dallasschauer.tournamentorganizer.entity.Game;
import com.dallasschauer.tournamentorganizer.service.GameService;

@SpringBootTest
public class GameTests {
	@Autowired
	private GameService gs;
	
	@Test
	void checkBracketConstruction() {
		int numTeams = 6;
		
		int i = 0;
		while (((int)Math.pow(2, i) / numTeams) <= 0) {
			i += 1;
		}
		
		int games = (int)Math.pow(2, i) - 1;
		
		Game head = gs.createTournamentBracket(1, numTeams);
		
		int resultNum = gs.countGameNum(head);
		assertEquals(games, resultNum);
	}
}
