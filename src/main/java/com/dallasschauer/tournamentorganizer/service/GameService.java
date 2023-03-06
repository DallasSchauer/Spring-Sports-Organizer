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
	
	public List<Game> findFutureGames (int playerId) {
		return gr.findFutureGames(playerId);
	}
	
	
	public Game createTournament (int eventId, List<Seed> teams) {		
		Game championship = createTournamentBracket(eventId, teams.size());
		
		int i = 0;
		while ((int)Math.pow(2, i) / teams.size() <= 0) {
			i += 1;
		}
		
		populateTournament(championship, teams, i);	
		
		
		return championship;
	}
	
	public Game createTournamentBracket (int eventId, int numTeams) {
		Game championship = new Game();
		championship.setEventId(eventId);
		
		int i = 0;
		while ((int)Math.pow(2, i) / numTeams <= 0) {
			i += 1;
		}
		
		populateTournamentStructure(championship, 0, i-1);
		clearAllAdvancements(championship);
		
		//List<Integer> nums = seeding(i-1);
		//System.out.println("LIST OF NUMBERS : \n" + nums.toString());
		
		
		return championship;
	}
	
	public void populateTournamentStructure (Game head, int round, int max) {
		if (round == max) {
			return;
		}
		
		gr.save(head);
		
		Game left = new Game();
		left.setEventId(head.getEventId());
		left.setParent(head);
		left.setParentId(head.getId());
		left.setEventId(head.getEventId());
		head.setLeft(left);
		
		Game right = new Game();
		right.setEventId(head.getEventId());
		right.setParent(head);
		right.setParentId(head.getId());
		right.setEventId(head.getEventId());
		head.setRight(right);
		
		populateTournamentStructure(left, round + 1, max);
		populateTournamentStructure(right, round + 1, max);
	}
	
	
	
	// THIS ALGORITHM AND ITS HELPER FUNCTION WERE CREATED BY STACK OVERFLOW
	// USER PHIL HOLDEN:
	// https://stackoverflow.com/questions/8355264/tournament-bracket-placement-algorithm
//	public List<Integer> seeding(int rounds) {
//		List<Integer> nums = new ArrayList<Integer>();
//		nums.add(1);
//		nums.add(2);
//		
//		System.out.println(nums.toString());
//		
//		for (int i = 0; i < rounds; i++) {
//			nums = nextRound(nums);
//			System.out.println(nums.toString());
//		}
//		return nums;
//	}
//	
//	public List<Integer> nextRound(List<Integer> nums) {
//		List<Integer> ret = new ArrayList<Integer>();
//		int len = nums.size()*2 + 1;
//		
//		for (int n: nums) {
//			ret.add(n);
//			ret.add(len-n);
//		}
//		
//		return ret;
//	}
	
	public void populateTournament(Game head, List<Seed> seeds, int round) {
		if (head == null) {
			return;
		}
		
		head.setHomeTeam(seeds.get(0).getId());
		head.setHomeSeed(seeds.get(0).getSeed());
		head.setAwayTeam(seeds.get(1).getId());
		head.setAwaySeed(seeds.get(1).getSeed());
	
		helper(head.getLeft(), seeds, 1);
		helper(head.getRight(), seeds, 1);
	}
	
	public void helper(Game head, List<Seed> seeds, int round) {
		if (head == null) {
			return;
		}
		
		int opponent = 0;
		
		if (head.getParent().getLeft() == head) {
			head.setHomeTeam(head.getParent().getHomeTeam());
			head.setHomeSeed(head.getParent().getHomeSeed());
			opponent = head.getParent().getHomeSeed();
		}
		if (head.getParent().getRight() == head) {
			head.setHomeTeam(head.getParent().getAwayTeam());
			head.setHomeSeed(head.getParent().getAwaySeed());
			opponent = head.getParent().getAwaySeed();
		}
		
		int target = (int)Math.pow(2, round+1) - (opponent - 1) - 1;
		
		if (target >= seeds.size()) {
			head.setAwayTeam(0);
		} else {
			head.setAwayTeam(seeds.get(target).getId());
			head.setAwaySeed(seeds.get(target).getSeed());
		}
		
		gr.save(head);
		
		helper(head.getLeft(), seeds, round+1);
		helper(head.getRight(), seeds, round+1);
		
	}
	
	public void clearAllAdvancements (Game head) {
		if (head == null) {
			System.out.println(head.getId() + " IS NULL.");
			return;
		}
		
		if (head.getLeft() == null && head.getRight() == null) {
			System.out.println(head.getId() + " IS A LEAF.");
			if (head.getHomeTeam() != 0 && head.getAwayTeam() == 0) {
				if (head.getParentId() != 0) {
					head.getParent().setHomeTeam(head.getHomeTeam());
					head.getParent().setHomeSeed(head.getHomeSeed());
					gr.save(head.getParent());
				}
			}
			gr.save(head);
			
			return;
		}
		
		System.out.println(head.getId() + " IS NOT A LEAF.");
		
		head.setHomeTeam(0);
		head.setHomeSeed(0);
		head.setAwayTeam(0);
		head.setAwaySeed(0);
		
		gr.save(head);
		clearAllAdvancements(head.getLeft());
		clearAllAdvancements(head.getRight());
	}
	
	// OLD METHOD: Did not seed correctly, had to replace.
	
//	public void populateTournament (Game head, List<Seed> seeds, int maxTeams) {
//		if (head.getLeft() == null && head.getRight() == null) {
//			if (seeds.size() == 0) {
//				return;
//			}
//			
//			int count = 0;
//			while (true) {
//				if ((int)Math.pow(2, count+1) / maxTeams > 0) {
//					break;
//				}
//				count++;
//			}
//			
//			int powerOfTwo = (int)Math.pow(2, count);
//			
//			Seed highestSeed = seeds.remove(0);
//			head.setHomeTeam(highestSeed.getId());
//			head.setHomeSeed(highestSeed.getSeed());
//			
//			
//			if (seeds.size() < powerOfTwo) {
//				Seed awayTeam = seeds.remove(seeds.size()-1);
//				head.setAwayTeam(awayTeam.getId());
//				head.setAwaySeed(awayTeam.getSeed());
//			} else {
//				Game parent = gr.getById(head.getParentId());
//				if (parent.getId() != 0) {
//					if (parent.getHomeTeam() == 0) {
//						parent.setHomeTeam(highestSeed.getId());
//						parent.setHomeSeed(highestSeed.getSeed());
//					} else if (parent.getAwayTeam() == 0){
//						parent.setAwayTeam(highestSeed.getId());
//						parent.setAwaySeed(highestSeed.getSeed());
//					}
//				}
//			}
//			
//			gr.save(head);
//			return;
//		}
//		
//		populateTournament(head.getLeft(), seeds, maxTeams);
//		populateTournament(head.getRight(), seeds, maxTeams);
//	}
	
	
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
