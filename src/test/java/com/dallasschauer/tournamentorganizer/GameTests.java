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
	
}
