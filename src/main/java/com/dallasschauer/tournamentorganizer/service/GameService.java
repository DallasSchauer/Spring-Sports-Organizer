package com.dallasschauer.tournamentorganizer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.dallasschauer.tournamentorganizer.entity.Game;
import com.dallasschauer.tournamentorganizer.exception.BusinessException;
import com.dallasschauer.tournamentorganizer.exception.EntryNotFoundException;
import com.dallasschauer.tournamentorganizer.model.Seed;
import com.dallasschauer.tournamentorganizer.repository.GameRepository;

@Service
public class GameService {
	private final GameRepository gr;
	
	public GameService (GameRepository gr) {
		this.gr = gr;
	}
	
	public List<Game> findAll() {
		return gr.findAll();
	}
	
	public Game findById (int id) {
		Optional<Game> g = gr.findById(id);
		if (g.isEmpty()) {
			throw new EntryNotFoundException("No such account.");
		}
		return g.get();
	}
	
	public Game save (Game g) {
		return gr.save(g);
	}
	
	public void delete (Game g) {
		gr.delete(g);
	}
	
	public Game deleteById (int id) {
		Game ret = findById(id);
		gr.deleteById(id);
		return ret;
	}
	
	public List<Integer> findTeamsByEventId (int id) {
		List<Integer> ret = gr.findTeamsByEventId(id);
		if (ret.isEmpty()) {
			throw new EntryNotFoundException("No teams found for this event.");
		} 
		return ret;
	}
	
	public List<Game> findFutureGames (int playerId) {
		return gr.findFutureGames(playerId);
	}
	
	public Game createTournamentBracket (int eventId, int numTeams) {
		Game championship = new Game();
		championship.setEventId(eventId);
		
		int i = 0;
		while ((int)Math.pow(2, i) / numTeams <= 0) {
			i += 1;
		}
		
		populateTournamentStructure(championship, 0, i);
		
		return championship;
	}
	
	public void populateTournamentStructure (Game head, int round, int max) {
		if (round == max) {
			return;
		}
		
		gr.save(head);
		
		Game left = new Game();
		left.setParent(head);
		left.setEventId(head.getEventId());
		head.setLeft(left);
		
		Game right = new Game();
		right.setParent(head);
		right.setEventId(head.getEventId());
		head.setRight(right);
		
		populateTournamentStructure(left, round + 1, max);
		populateTournamentStructure(right, round + 1, max);
	}
	
	public int countGameNum (Game head) {
		if (head == null) {
			return 0;
		}
		int count = 1;
		count += countGameNum(head.getLeft());
		count += countGameNum(head.getRight());
		return count;
	}
	
	public void createSeasonSchedule (int eventId) {
		int numGames = gr.findNumGamesFromEventId(eventId);
		List<Integer> teams = gr.findTeamsByEventId(eventId);
		int MAX_GAMES = (numGames / teams.size()) + 1;
		
				
		HashMap<Integer, List<Integer>> GameMap = 
				new HashMap<Integer, List<Integer>>();
		for (Integer i: teams) {
			List<Integer> teamList = new ArrayList<Integer>();
			GameMap.put(i, teamList);
		}
		
		for (int i = 0; i < numGames; i++) {
			ArrayList<Integer> copy = new ArrayList<>(teams);
			
			if (copy.size() % 2 == 1) {
				copy.add(0);
			}
			
			for (int j = 0; j < (teams.size() / 2); j++) {
				Random r = new Random();
				int homeElement = r.nextInt(copy.size());
				int team1 = copy.remove(homeElement);
				
				List<Integer> potentialMatches = getSmallestNumbers(copy
						,GameMap.get(team1));
				
				int team2 = potentialMatches.get(r.nextInt(potentialMatches.size()));
				copy.remove((Object)team2);
				
				Game g = new Game();
				g.setEventId(eventId);
				
				if (i % 2 == 0) {
					g.setHomeTeam(lowestBetween(team1, team2));
					g.setAwayTeam(greatestBetween(team1, team2));
				} else {
					g.setHomeTeam(greatestBetween(team1, team2));
					g.setAwayTeam(lowestBetween(team1, team2));
				}
				
				addGameToMap(GameMap, team1, team2);
				addGameToMap(GameMap, team2, team1);
				g = gr.save(g);
			}
			copy = null;
		}
	}
	
	public int findNumGamesBetweenTwoTeams (int eventId, int team1, int team2) {
		return gr.findNumGamesBetweenTwoTeams(eventId, team1, team2);
	}
	
	public void addGameToMap(HashMap<Integer, List<Integer>> map, int team1,
			int team2) {
		List<Integer> opponents = map.get(team1);
		opponents.add(team2);
		map.put(team1, opponents);
	}
	
	public int findNumGamesInMap (HashMap<Integer, List<Integer>> map, int team1,
			int team2) {
		if (map.containsKey((Integer)team1)) {
			System.out.println("MAP HAS KEY " + team1);
		} else {
			System.out.println("MAP DOESN'T HAVE KEY " + team1);
		}
		List<Integer> opponents = map.get(team1);
		System.out.println("GAMEMAP IN FUNCTION : " + map.toString());
		if (opponents == null) {
			System.out.println("OPPONENTS ARE NULL");
			return 0;
		}
		System.out.println("OPPONENTS LIST IN FUNCTION : " + opponents.toString());
		
		int count = 0;
		
		for (int i: opponents) {
			if (i == team2) {
				count += 1;
			}
		}
		return count;
	}
	
	public int lowestBetween (int a, int b) {
		if (a <= b) {
			return a;
		} else {
			return b;
		}
	}
	
	public int greatestBetween (int a, int b) {
		if (a > b) {
			return a;
		} else {
			return b;
		}
	}
	
	public List<Integer> getSmallestNumbers (List<Integer> opponents,
			List<Integer> opponentsPlayed) {
		HashMap<Integer, Integer> numMatches = new HashMap<Integer, Integer>();
		
		
		int minimum = Integer.MAX_VALUE;
		for (Integer i:opponents) {
			int count = 0;
			numMatches.put(i, count);
			for (Integer j:opponentsPlayed) {
				if (j==i) {
					count += 1;
					numMatches.put(i, count);
				}
			}
			if (count < minimum) {
				minimum = count;
			}
		}
		
		ArrayList<Integer> ret = new ArrayList<Integer>();
		
		for (Integer l:numMatches.keySet()) {
			if (numMatches.get(l) == minimum) {
				ret.add(l);
			}
		}
		
		return ret;
	}
	
	public List<Game> findGamesByEvent (int id) {
		return gr.findGamesByEventId(id);
	}
	
//	public Game populateTournamentBracket (Game championship, List<Seed> teams) {
//		
//		
//		return championship;
//	}
}
