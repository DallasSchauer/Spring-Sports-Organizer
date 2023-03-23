package com.dallasschauer.tournamentorganizer.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Tuple;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dallasschauer.tournamentorganizer.entity.Event;
import com.dallasschauer.tournamentorganizer.entity.Game;
import com.dallasschauer.tournamentorganizer.entity.LeagueDetails;
import com.dallasschauer.tournamentorganizer.entity.Player;
import com.dallasschauer.tournamentorganizer.entity.PlayerParticipates;
import com.dallasschauer.tournamentorganizer.entity.Team;
import com.dallasschauer.tournamentorganizer.entity.TeamParticipates;
import com.dallasschauer.tournamentorganizer.entity.TournamentDetails;
import com.dallasschauer.tournamentorganizer.model.Seed;
import com.dallasschauer.tournamentorganizer.model.Standing;
import com.dallasschauer.tournamentorganizer.model.TotalEventDetails;
import com.dallasschauer.tournamentorganizer.service.EventService;
import com.dallasschauer.tournamentorganizer.service.GameService;
import com.dallasschauer.tournamentorganizer.service.LeagueDetailsService;
import com.dallasschauer.tournamentorganizer.service.PlayerParticipatesService;
import com.dallasschauer.tournamentorganizer.service.PlayerService;
import com.dallasschauer.tournamentorganizer.service.TeamParticipatesService;
import com.dallasschauer.tournamentorganizer.service.TeamService;
import com.dallasschauer.tournamentorganizer.service.TournamentDetailsService;
import com.dallasschauer.tournamentorganizer.utils.Utils;

@Controller
public class WebController {
		private final TeamService ts;
		private final TeamParticipatesService tps;
		private final EventService es;
		private final PlayerParticipatesService pps;
		private final PlayerService ps;
		private final TournamentDetailsService tds;
		private final LeagueDetailsService lds;
		private final GameService gs;
		
		@Autowired
		public WebController (TeamService ts,
				TeamParticipatesService tps,
				EventService es,
				PlayerParticipatesService pps,
				PlayerService ps,
				TournamentDetailsService tds,
				LeagueDetailsService lds,
				GameService gs) {
			this.ts = ts;
			this.tps = tps;
			this.es = es;
			this.pps = pps;
			this.ps = ps;
			this.tds = tds;
			this.lds = lds;
			this.gs = gs;
		}
		
		
	   @RequestMapping(value = "/index")
	   public String index(HttpSession session) {
	      return "index";
	   }
	   
	   @RequestMapping(value = "/login")
	   public String login(HttpSession session, Model model, String message) {
		   model.addAttribute("player", new Player());
		   model.addAttribute("message", message);
		   return "login";
	   }
	   
	   @PostMapping(value = "/login")
	   public String login
	   (HttpSession session, Player player, Model model) {
		   Player res = ps.checkLogin(player.getUsername(), player.getPassword());
		   
		   if (res == null) {
			   return login(session, model, "There was an error. Try again.");
		   }
		   
		   session.setAttribute("USER_ID", res.getId());
		   return home(session, model);
	   }
	   
	   @GetMapping(value = "/logout")
	   public String logout
	   (HttpSession session, Model model) {
		   session.removeAttribute("USER_ID");
		   model.addAttribute("player", new Player());
		   return "login";
	   }
	   
	   @GetMapping(value = "/home")
	   public String home
	   (HttpSession session, Model model) {
		   if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   }
		   
		   List<Event> events = es.findEventsByPlayer((int)session.getAttribute("USER_ID"));
		   List<Integer> ids = new ArrayList<Integer>();
		   for (Event e : events) {
			   ids.add(e.getId());
		   }
		   
		   
		   model.addAttribute("events", events);
		   model.addAttribute("ids", ids);
		   model.addAttribute("games", gs.findFutureGames((int)session.getAttribute("USER_ID")));
		   
		   return "home";
	   }
	   
	   @RequestMapping(value = "/leagues")
	   public String leagues(HttpSession session, Model model) {
		   if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   }
		   
		   return "leagues";
	   }
	   
	   @GetMapping(value = "/teams") 
		public String webTeams(HttpSession session, Model model) {
		   	if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   	}
		   
		   	model.addAttribute("teams", ts.findAllTeamsWithoutBye());
			return "teams";
		}
	   
	   @GetMapping(value ="/teams/{id}")
	   public String teamPage 
	   (HttpSession session, @PathVariable("id") int id,
			   Model model) {
		   if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   }
		   
		   Team team = ts.findById(id);
		   model.addAttribute("team", team);
		   model.addAttribute("manager", ps.findById(team.getManagerId()));
		   model.addAttribute("players", ps.findPlayersByTeam(id));
		   model.addAttribute("events", es.findEventsByTeam(id));
		   
		   return "team";
	   }
	   
	   @GetMapping(value = "/teams/players/{id}")
	   public String personalTeams
	   (HttpSession session, @PathVariable("id") int id,
			   Model model) {
		   if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   }
		
		   model.addAttribute("managedTeams", ts.findTeamsByManager(id));
		   
		   model.addAttribute("playerTeams", ts.findTeamsByPlayer(id));
		   
		   return "personalTeams";
	   }
	   
	   @GetMapping(value = "/players/{id}")
	   public String playerProfile
	   (HttpSession session, @PathVariable("id") int id,
			   Model model) {
		   if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   }
		   
		   model.addAttribute("player", ps.findById(id));
		   
		   return "playerProfile";
	   }
	   
	   @GetMapping(value = "/navbar")
	   public String webNavBar(HttpSession session, 
			   Model model) {
		   return "navbar";
	   }
	   
	   @GetMapping(value = "/createAccount") 
	   public String createAccount(
			   HttpSession session, Model model) {
		   model.addAttribute("player", new Player());
		   return "createAccount";
	   }
	   
	   @PostMapping(value = "/createAccount")
	   public String createAccountSubmit
	   (HttpSession session, @ModelAttribute Player player,
			   Model model) {
		   Player res = ps.save(player);
		   session.setAttribute("USER_ID", res.getId());
		   return home(session, model);
	   }
	   
	   @GetMapping(value = "/editAccount") 
	   public String editAccount(
			   HttpSession session, Model model) {
		   model.addAttribute("player", 
				   ps.findById((int)session.getAttribute("USER_ID")));
		   
		   return "editAccount";
	   }
	   
	   @PostMapping(value = "/editAccount")
	   public String editAccountSubmit
	   (HttpSession session, @ModelAttribute Player player,
			   Model model) {
		   if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   }
		   
		   ps.save(player);
		   return home(session, model);
	   }
	   
	   @GetMapping(value = "/createTeam")
	   public String createTeam(HttpSession session,
			   Model model) {
		   if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   }
		   
		   model.addAttribute("team", new Team());
		   return "createTeam";
	   }
	   
	   @PostMapping(value = "/createTeam")
	   public String createTeamSubmit
	   (HttpSession session, @ModelAttribute Team team, 
			   Model model) {
		   if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   }
		   
		   team.setManagerId((int)session.getAttribute("USER_ID"));
		   ts.save(team);
		   return browseEvents(session, model);
	   }
	   
	   @GetMapping(value = "/editTeam/{id}")
	   public String editTeam(HttpSession session,
			   Model model, @PathVariable("id") int id) {
		   if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   }
		   
		   Team t = ts.findById(id);
		   
		   if ((int)session.getAttribute("USER_ID") != t.getManagerId()) {
			   model.addAttribute("message", "You are not able to edit this game.");
			   return "error";
		   }
		   
		   model.addAttribute("team", t);
		   return "editTeam";
	   }
	   
	   @PostMapping(value = "/editTeam")
	   public String editTeamSubmit
	   (HttpSession session, @ModelAttribute Team team, 
			   Model model) {
		   if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   }
		   
		   team.setManagerId((int)session.getAttribute("USER_ID"));
		   ts.save(team);
		   return browseEvents(session, model);
	   }
	   
	   @GetMapping(value = "/createEvent")
	   public String createEvent (HttpSession session, 
			   Model model) {
		   if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   }
		   
		   model.addAttribute("event", new TotalEventDetails());
		   return "createEvent";
	   }
	   
	   @PostMapping(value = "/createEvent")
	   public String createEventSubmit
	   (HttpSession session, 
		@ModelAttribute TotalEventDetails event,
		Model model) {
		   if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   }
		   
		   try {
			   if (event.getEvent_type() != 0) {
				   event.setEvent_type(event.getEvent_type() - 1);
			   }
			   
			   Event e = event.getEventDetails();
			   e.setCommissioner((int)session.getAttribute("USER_ID"));
			   Event res = es.save(e);
			   if (e.getType() == 0) {
				   LeagueDetails league = event.getLeagueDetails(res.getId());
				   lds.save(league);
				   return individualEvents(session, e.getId(), model);
			   } else {
				   TournamentDetails tournament = event.getTournamentDetails(res.getId());
				   tds.save(tournament);
				   return individualEvents(session, e.getId(), model);
			   }
		   } catch (Exception e) {
			   System.out.println(e.getMessage());
			   return "error";
		   }
	   }
	   
	   @GetMapping(value = "/events")
	   public String browseEvents
	   (HttpSession session, Model model) {
		   if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   }
		   
		   model.addAttribute("tennisEvents", es.findEventBySport(0));
		   model.addAttribute("baseballEvents", es.findEventBySport(1));
		   model.addAttribute("basketballEvents", es.findEventBySport(2));
		   model.addAttribute("footballEvents", es.findEventBySport(3));
		   model.addAttribute("hockeyEvents", es.findEventBySport(4));
		   model.addAttribute("soccerEvents", es.findEventBySport(5));
		   model.addAttribute("otherEvents", es.findEventBySport(6));
		   return "browseEvents";
	   }
	   
	   @GetMapping(value = "/events/{id}")
	   public String individualEvents
	   (HttpSession session,
			   @PathVariable("id") int id, Model model) {
		   if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   }
		   
		   model.addAttribute("event", es.findById(id));
		   List<Game> games = gs.findGamesByEvent(id);
		   model.addAttribute("numGames", games.size());
		   model.addAttribute("games", games);
		   model.addAttribute("unfinished", 
				   gs.findNumUnfinishedGamesFromEvent(id));
		   
		   List<Tuple> teams = gs.getTeamsWithWins(id);
		   teams.addAll(gs.getTeamsWithNoWins(id));
		   
		   List<Standing> standings = new ArrayList<Standing>();
		   for (Tuple t: teams) {
			   Team res = ts.findById((Integer)t.get(0));
			   Standing s = new Standing();
			   s.setId((Integer)t.get(0));
			   s.setName(res.getName());
			   s.setWins(((Number)t.get(1)).intValue());
			   standings.add(s);
		   }
		   
		   if (standings.size() <= 2) {
			   standings.add(0, new Standing());
		   }
		   
		   System.out.println("STANDINGS SIZE " + standings.size());
		   
		   int count = 1;
		   List<Seed> seeds = new ArrayList<Seed>();
		   seeds.add(new Seed(0, "BYE", 0));
		   
		   if (es.findById(id).getType() == 0) {
			   for (Standing st : standings) {
				   Seed newSeed = new Seed();
				   newSeed.setId(st.getId());
				   newSeed.setName(st.getName());
				   newSeed.setSeed(count);
				   count++;
				   seeds.add(newSeed);
			   }
			   model.addAttribute("seeds", seeds);
		   } else if (games.size() > 0) {
			   Game championship = gs.findChampionship(id);
			   Team champion = ts.findById(championship.getWinner());
			   model.addAttribute("championship", championship);
			   model.addAttribute("champion", champion);
			   
			   for (int i = 1; i < standings.size(); i++) {
				   Team t = ts.findById(tps.findSeedByEventAndTeam(id, i));
				   Seed newSeed = new Seed();
				   
				   System.out.println("SEED COUNT " + i);
				   
				   newSeed.setId(t.getId());
				   newSeed.setName(t.getName());
				   newSeed.setSeed(count);
				   count++;
				   seeds.add(newSeed);
			   }
			   
			   model.addAttribute("seeds", seeds);
		   }
			   
		   
		   if (es.findById(id).getType() == 1 && games.size() > 0) {
			   
			   List<Game> tourneyGames = gs.findTournamentGames(id);
			   int numRounds = gs.findMaxRound(id);
			   
			   
			   List<List<Game>> rounds = new ArrayList<List<Game>>();
			   
			   for (int i = 0; i < numRounds; i++) {
				   rounds.add(new ArrayList<Game>());
			   }
			   
			   for (Game g:tourneyGames) {
				   (rounds.get(g.getRound()-1)).add(g);
			   }
			   
			   model.addAttribute("rounds", rounds);
		   }
			   
		   model.addAttribute("teams", standings);
		   
		   return "event";
	   }
	   
	   @GetMapping(value = "/events/{id}/generateSchedule")
	   public String generateSchedule
	   (HttpSession session,
			   @PathVariable("id") int id, Model model) {
		   if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   }
		   
		   gs.createSeasonSchedule(id);
		   
		   return individualEvents(session, id, model);
	   }
	   
	   @GetMapping(value = "/events/{id}/generateTournament")
	   public String generateTournamentFromLeague
	   (HttpSession session,
			   @PathVariable("id") int id,
			   Model model) {
		   if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   }
		   
		   Event league = es.findById(id);
		   
		   Event e = new Event();
		   
		   e.setName(league.getName() + " TOURNAMENT");
		   e.setCommissioner((int)session.getAttribute("USER_ID"));
		   e.setMaxAge(league.getMaxAge());
		   e.setMaxTeams(league.getMaxTeams());
		   e.setSport(league.getSport());
		   e.setType(1);
		   e.setAvgHours(league.getAvgHours());
		   
		   Event tournament = es.save(e);
		   
		   List<Tuple> teams = gs.getTeamsWithWins(id);
		   teams.addAll(gs.getTeamsWithNoWins(id));
		   
		   List<Standing> standings = new ArrayList<Standing>();
		   for (Tuple t: teams) {
			   Team res = ts.findById((Integer)t.get(0));
			   Standing s = new Standing();
			   s.setId((Integer)t.get(0));
			   s.setName(res.getName());
			   s.setWins(((Number)t.get(1)).intValue());
			   standings.add(s);
		   }
		   
		   
		   
		   int count = 1;
		   List<Seed> seeds = new ArrayList<Seed>();
		   
		   for (Standing st : standings) {
			   Seed newSeed = new Seed();
			   newSeed.setId(st.getId());
			   
			   newSeed.setSeed(count);
			   count++;
			   seeds.add(newSeed);
			   System.out.println("NEW SEED " + newSeed.getId() + " " + 
					   newSeed.getName() + " " + newSeed.getSeed());
			   TeamParticipates tp = new TeamParticipates();
			   tp.setEventId(tournament.getId());
			   tp.setTeamId(st.getId());
			   tp.setSeed(count-1);
			   tps.save(tp);
		   }
		   
		   model.addAttribute("fromLeague", true);
			   
		   Game championship = gs.createTournament(tournament.getId(), seeds);
		   
		   return individualEvents(session, tournament.getId(), model);
	   }
	   
	   @GetMapping(value = "/createTournament/{id}")
	   public String createTournament
	   (HttpSession session,
			   @PathVariable("id") int id,
			   Model model) {
		   if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   }
		   
		   
		   Event tournament = es.findById(id);
		   
		   List<Tuple> teams = gs.getTeamsWithWins(id);
		   teams.addAll(gs.getTeamsWithNoWins(id));
		   
		   if (model.getAttribute("fromLeague") == null) {
			   Collections.shuffle(teams);
		   }
		   
		   
		   int count = 1;
		   List<Seed> seeds = new ArrayList<Seed>();
		   for (Tuple t : teams) {
			   Seed newSeed = new Seed();
			   newSeed.setId((int)t.get(0));
			   newSeed.setSeed(count);
			   
			   List<TeamParticipates> tp = tps.findByEventAndTeam(id, (int)t.get(0));
			   (tp.get(0)).setSeed(count);
			   tps.save(tp.get(0));
			   
			   count++;
			   seeds.add(newSeed);
			   
			   
		   }
		   
		   Game championship = gs.createTournament(tournament.getId(), seeds);
		   
		   return individualEvents(session, tournament.getId(), model);
	   }
	   
	   @GetMapping(value = "/games/{id}")
	   public String individualGames
	   (HttpSession session,
			   @PathVariable("id") int id,
			   Model model) {
		   if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   }
		   
		   Game g = gs.findById(id);
		   Event e = es.findById(g.getEventId());
		   Team t1 = ts.findById(g.getAwayTeam());
		   Team t2 = ts.findById(g.getHomeTeam());
		   Player manager1 = ps.findById(t1.getManagerId());
		   Player manager2 = ps.findById(t2.getManagerId());
		   List<Player> p1 = ps.findPlayersByTeam(t1.getId());
		   List<Player> p2 = ps.findPlayersByTeam(t2.getId());
		   
		   model.addAttribute("game", g);
		   model.addAttribute("team1", t1);
		   model.addAttribute("team2", t2);
		   model.addAttribute("event", e);
		   model.addAttribute("manager1", manager1);
		   model.addAttribute("manager2", manager2);
		   model.addAttribute("p1", p1);
		   model.addAttribute("p2", p2);
		   
		   return "game";
	   }
	   
	   @GetMapping(value = "/editGame/{id}")
	   public String editGame
	   (HttpSession session,
			   @PathVariable("id") int id,
			   Model model) {
		   if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   }
		   
		   Game g = gs.findById(id);
		   Event e = es.findById(g.getEventId());
		   
		   if ((int)session.getAttribute("USER_ID") != e.getCommissioner()) {
			   model.addAttribute("message", "You are not able to edit this game.");
			   return "error";
		   }
		   
		   Team awayTeam = ts.findById(g.getAwayTeam());
		   Team homeTeam = ts.findById(g.getHomeTeam());
		   
		   model.addAttribute("game", g);
		   model.addAttribute("awayTeam", awayTeam);
		   model.addAttribute("homeTeam", homeTeam);
		   
		   return "editGame";
	   }
	   
	   @PostMapping(value = "/editGame")
	   public String editGameSubmit
	   (HttpSession session,
			   @ModelAttribute Game game,
			   Model model) {
		   
		   if (session.getAttribute("USER_ID") == null) {
			   return login(session, model, "");
		   }
		   
		   Game g = gs.findById(game.getId());
		   Event e = es.findById(game.getEventId());
		   
		   g.setAwayScore(game.getAwayScore());
		   g.setHomeScore(game.getHomeScore());
		   g.setFinished(true);
		   
		   int winnerSeed = 0;
		   int loserTeam = 0;
		   int loserSeed = 0;
		   
		   if (g.getAwayScore() > g.getHomeScore()) {
			   g.setWinner(g.getAwayTeam());
			   winnerSeed = g.getAwaySeed();
			   loserTeam = g.getHomeTeam();
			   loserSeed = g.getHomeSeed();
		   } else if (g.getHomeScore() > g.getAwayScore()) {
			   g.setWinner(g.getHomeTeam());
			   winnerSeed = g.getHomeSeed();
			   loserTeam = g.getAwayTeam();
			   loserSeed = g.getAwaySeed();
		   }
		   
		   
		   if (e.getType() == 1) {
			   if (g.getParentId() != null) {
				   Game parent = gs.findById(g.getParentId());
				   
				   if (parent.getHomeTeam() == 0 && parent.getAwayTeam() == 0) {
					   parent.setHomeTeam(g.getWinner());
					   parent.setHomeSeed(winnerSeed);
				   } else if (parent.getHomeTeam() == g.getWinner()
						   || parent.getAwayTeam() == g.getWinner()) {
				   } else if (parent.getHomeTeam() == loserTeam
						   && parent.getAwayTeam() == 0) {
					   gs.eraseParents(parent.getId(), loserTeam);
					   parent.setHomeTeam(g.getWinner());
					   parent.setHomeSeed(winnerSeed);
				   } else if (parent.getAwayTeam() == 0 
						   && parent.getHomeSeed() < winnerSeed) {
					   parent.setAwayTeam(g.getWinner());
					   parent.setAwaySeed(winnerSeed);
				   } else if (parent.getAwayTeam() == 0
						   && parent.getHomeSeed() > winnerSeed) {
					   parent.setAwayTeam(parent.getHomeTeam());
					   parent.setAwaySeed(parent.getHomeSeed());
					   parent.setHomeTeam(g.getWinner());
					   parent.setHomeSeed(winnerSeed);
				   } else if ((parent.getHomeTeam() != 0 && parent.getAwayTeam() != 0)
						   && (parent.getAwaySeed() < winnerSeed)
						   && (parent.getHomeTeam() == loserTeam)) {
					   gs.eraseParents(parent.getId(), loserTeam);
					   parent.setHomeTeam(parent.getAwayTeam());
					   parent.setHomeSeed(parent.getAwaySeed());
					   parent.setAwayTeam(g.getWinner());
					   parent.setAwaySeed(winnerSeed);
				   } else if ((parent.getHomeTeam() != 0 && parent.getAwayTeam() != 0)
						   && (parent.getAwaySeed() > winnerSeed)
						   && (parent.getHomeTeam() == loserTeam)) {
					   gs.eraseParents(parent.getId(), loserTeam);
					   parent.setHomeTeam(g.getWinner());
					   parent.setHomeSeed(winnerSeed);
				   } else if ((parent.getHomeTeam() != 0 && parent.getAwayTeam() != 0)
						   && (parent.getHomeSeed() < winnerSeed)
						   && (parent.getAwayTeam() == loserTeam)) {
					   gs.eraseParents(parent.getId(), loserTeam);
					   parent.setAwayTeam(g.getWinner());
					   parent.setAwaySeed(winnerSeed);
				   } else if ((parent.getHomeTeam() != 0 && parent.getAwayTeam() != 0)
						   && (parent.getHomeSeed() > winnerSeed)
						   && (parent.getAwayTeam() == loserTeam)) {
					   gs.eraseParents(parent.getId(), loserTeam);
					   parent.setAwayTeam(parent.getHomeTeam());
					   parent.setAwaySeed(parent.getHomeSeed());
					   parent.setHomeTeam(g.getWinner());
					   parent.setHomeSeed(winnerSeed);
				   } else {
					   model.addAttribute("message", "Code coverage not 100%");
					   return "error";
				   }
				   
				   gs.save(parent);
			   }
		   }
		   
		   gs.save(g);
		   
		   
		   return individualGames(session, game.getId(), model);
	   }
	  
	   
	   @GetMapping(value = "/addPlayerToTeam/{player}/{team}")
	   public String addPlayerToTeam
	   (HttpSession session,
		@PathVariable("player") int player,
		@PathVariable("team") int team,
		Model model) {
		   try {
			   Player p = ps.findById(player);
			   Team t = ts.findById(team);
			   
			   if (Utils.findAge(p.getDob()) > t.getMaxAge()) {
				   model.addAttribute("message", "Player is too old for this team.");
				   return "error";
			   }
			   
			   if (Utils.findAge(p.getDob()) < t.getMinAge()) {
				   model.addAttribute("message", "Player is too young for this team.");
				   return "error";
			   }
			   
			   PlayerParticipates pp = new PlayerParticipates();
			   pp.setPlayerId(player);
			   pp.setTeamId(team);
			   PlayerParticipates res = pps.save(pp);
		   } catch (Exception e) {
			   System.out.println(e.getMessage());
			   model.addAttribute("message", "ERROR: " +
					   e.getMessage());
			   return "error";
		   }
		   
		   return teamPage(session, team, model);
	   }
	   
	   @GetMapping(value = "/addTeamToEvent/{event}/{manager}")
	   public String addTeamToEvent
	   (HttpSession session,
		@PathVariable("event") int event,
		@PathVariable("manager") int manager,
		Model model) {
		   model.addAttribute("participation", new TeamParticipates());
		   model.addAttribute("managedTeams", ts.findTeamsByManager(manager));
		   model.addAttribute("event", es.findById(event));
		   
		   return "addTeamToEvent";
	   }
	   
	   @PostMapping(value = "/addTeamToEvent")
	   public String addTeamToEvent
	   (HttpSession session,
		TeamParticipates tp,
		Model model) {
		   try {
			   Team t = ts.findById(tp.getTeamId());
			   Event e = es.findById(tp.getEventId());
			   
			   if (t.getSport() != e.getSport()) {
				   model.addAttribute("message", "Team and event sports do not match.");
				   return "error";
			   }
			   if (tps.findOldestAge(t.getId()) > e.getMaxAge()) {
				   model.addAttribute("message", "Player on your team is too old for event.");
				   return "error";
			   }
			   
			   
			   tps.save(tp);
		   } catch (Exception e) {
			   System.out.println(e.getMessage());
			   return "error";
		   }
		   return individualEvents(session, tp.getEventId(), model);
	   }
	   
	   
}	
