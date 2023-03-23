package com.dallasschauer.tournamentorganizer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.persistence.Tuple;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dallasschauer.tournamentorganizer.entity.Game;
import com.dallasschauer.tournamentorganizer.exception.BusinessException;
import com.dallasschauer.tournamentorganizer.exception.EntryNotFoundException;
import com.dallasschauer.tournamentorganizer.model.Seed;
import com.dallasschauer.tournamentorganizer.repository.GameRepository;

@Transactional
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
	
	public Game findChampionship (int eventId) {
		List<Game> games = gr.findGamesByEventId(eventId);
		
		if (games.size() == 0) {
			throw new EntryNotFoundException();
		}
		
		Game ret = games.get(0);
		
		while (ret.getParentId() != null) {
			ret = gr.getById(ret.getParentId());
		}
		
		return ret;
	}
	
	public List<Game> findFutureGames (int playerId) {
		return gr.findFutureGames(playerId);
	}
	
	public List<Game> findTournamentGames (int eventId) {
		return gr.findTournamentGames(eventId);
	}
	
	public Integer findMaxRound (int eventId) {
		return gr.findMaxRound(eventId);
	}
	
	public void eraseParents (int parentId, int replaced) {
		Optional<Game> res = gr.findById(parentId);
		
		if (res.isEmpty()) {
			return;
		}
		
		Game g = res.get();
		
		while (g != null) {
			System.out.println("WE ARE ON GAME : " + g.getId());
			if (g.getHomeTeam() == replaced) {
				System.out.println("CASE 1");
				g.setHomeScore(0);
				g.setAwayScore(0);
				g.setAwayTeam(0);
				g.setAwaySeed(0);
				g.setWinner(0);
				g.setFinished(false);
				
			} else if (g.getAwayTeam() == replaced) {
				System.out.println("CASE 2");
				g.setHomeScore(0);
				g.setAwayScore(0);
				g.setAwayTeam(0);
				g.setAwaySeed(0);
				g.setWinner(0);
				g.setFinished(false);
			} else {
				System.out.println("CASE 3");
				return;
			}
		
			gr.save(g);
			System.out.println("SAVED");
			
			if (g.getParentId() == null) {
				return;
			}
			
			System.out.println("PARENT ID NOT NULL: " + g.getParentId());
		
			res = gr.findById(g.getParentId());
			if (res.isEmpty()) {
				return;
			}
			g = res.get();
			
			System.out.println("SETTING TO GAME : " + g.getId());
		}
	}
	
	public Game createTournament (int eventId, List<Seed> teams) {		
		Game tournament = createTournamentBracket(eventId, teams);
		
		// populateTournament(championship, teams, i);	
		
		return tournament;
	}
	
	public Game createTournamentBracket (int eventId, List<Seed> teams) {
		Game championship = new Game();
		
		championship.setEventId(eventId);
		
		championship.setHomeTeam(teams.get(0).getId());
		championship.setHomeSeed(teams.get(0).getSeed());
		championship.setAwayTeam(teams.get(1).getId());
		championship.setAwaySeed(teams.get(1).getSeed());
		
		int i = 0;
		while ((int)Math.pow(2, i) / teams.size() <= 0) {
			i += 1;
		}
		
		Game c = gr.save(championship);
		
		populateTournamentStructure(c, 1, i, teams);
		
		if (teams.size() > 2) {
			championship.setHomeTeam(0);
			championship.setHomeSeed(0);
			championship.setAwayTeam(0);
			championship.setAwaySeed(0);
		}
			
		gr.save(c);
		
		return championship;
	}
	
	public void populateTournamentStructure (Game head, int round, 
			int max, List<Seed> teams) {

		head.setRound(max-round+1);
		
		if (round == max) {
			if (head.getAwayTeam() == 0) {
				Optional<Game> p = gr.findById(head.getParentId());
				Game parent = p.get();
				parent.setHomeTeam(head.getHomeTeam());
				parent.setHomeSeed(head.getHomeSeed());
				head.setFinished(true);
				
				gr.save(parent);
			}
			
			gr.save(head);
			return;
		}
		
		
		Game left = new Game();
		left.setEventId(head.getEventId());
		left.setParent(head);
		left.setParentId(head.getId());
		
		int leftOpponent = head.getHomeSeed();
		left.setHomeTeam(head.getHomeTeam());
		left.setHomeSeed(head.getHomeSeed());
		int leftTarget = (int)Math.pow(2,  round+1) - (leftOpponent - 1) -1;
		
		if (leftTarget >= teams.size()) {
			left.setAwayTeam(0);
		} else {
			left.setAwayTeam(teams.get(leftTarget).getId());
			left.setAwaySeed(teams.get(leftTarget).getSeed());
		}
		
		head.setLeft(left);
		
		Game right = new Game();
		right.setEventId(head.getEventId());
		right.setParent(head);
		right.setParentId(head.getId());
		
		int rightOpponent = head.getAwaySeed();
		right.setHomeTeam(head.getAwayTeam());
		right.setHomeSeed(head.getAwaySeed());
		int rightTarget = (int)Math.pow(2,  round+1) - (rightOpponent - 1) -1;
		
		if (rightTarget >= teams.size()) {
			right.setAwayTeam(0);
		} else {
			right.setAwayTeam(teams.get(rightTarget).getId());
			right.setAwaySeed(teams.get(rightTarget).getSeed());
		}
		
		head.setRight(right);
		
		left = gr.save(left);
		right = gr.save(right);
		
		if ((head.getLeft() != null) || (head.getRight() != null)) {
			head.setHomeTeam(0);
			head.setHomeSeed(0);
			head.setAwayTeam(0);
			head.setAwaySeed(0);
		}
		
		populateTournamentStructure(left, round + 1, max, teams);
		populateTournamentStructure(right, round + 1, max, teams);
		
		gr.save(head);
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
	
	public int findNumGamesFromEventId(int eventId) {
		Integer ret = gr.findNumGamesFromEventId(eventId);
		if (ret == null) {
			return 0;
		} else {
			return ret.intValue(); 
		}
	}
	
	public int findNumUnfinishedGamesFromEvent(int eventId) {
		Integer ret = gr.findNumUnfinishedGamesFromEvent(eventId);
		if (ret == null) {
			return 0;
		} else {
			return ret.intValue();
		}
	}
	
	public void createSeasonSchedule (int eventId) {
		deleteGamesByEvent(eventId);
		
		int numGames = findNumGamesFromEventId(eventId);
		List<Integer> teams = gr.findTeamsByEventId(eventId);
		
				
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
	
	public Integer findNumGamesBetweenTwoTeams (int eventId, int team1, int team2) {
		Integer ret = gr.findNumGamesBetweenTwoTeams(eventId, team1, team2);
		if (ret == null) {
			return 0;
		} else {
			return ret;
		} 
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
	
	public void deleteGamesByEvent (int id) {
		gr.deleteEventGames(id);
	}
	
	public List<Tuple> getTeamsWithWins(int eventId) {
		return gr.getTeamsWithWins(eventId);
	}
	
	public List<Tuple> getTeamsWithNoWins(int eventId) {
		return gr.getTeamsWithNoWins(eventId);
	}
}
