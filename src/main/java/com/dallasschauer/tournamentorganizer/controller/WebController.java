package com.dallasschauer.tournamentorganizer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dallasschauer.tournamentorganizer.entity.Account;
import com.dallasschauer.tournamentorganizer.entity.Event;
import com.dallasschauer.tournamentorganizer.entity.LeagueDetails;
import com.dallasschauer.tournamentorganizer.entity.Player;
import com.dallasschauer.tournamentorganizer.entity.PlayerParticipates;
import com.dallasschauer.tournamentorganizer.entity.Team;
import com.dallasschauer.tournamentorganizer.entity.TournamentDetails;
import com.dallasschauer.tournamentorganizer.model.TotalEventDetails;
import com.dallasschauer.tournamentorganizer.service.EventService;
import com.dallasschauer.tournamentorganizer.service.LeagueDetailsService;
import com.dallasschauer.tournamentorganizer.service.PlayerParticipatesService;
import com.dallasschauer.tournamentorganizer.service.PlayerService;
import com.dallasschauer.tournamentorganizer.service.TeamParticipatesService;
import com.dallasschauer.tournamentorganizer.service.TeamService;
import com.dallasschauer.tournamentorganizer.service.TournamentDetailsService;

@Controller
public class WebController {
		private final TeamService ts;
		private final TeamParticipatesService tps;
		private final EventService es;
		private final PlayerParticipatesService pps;
		private final PlayerService ps;
		private final TournamentDetailsService tds;
		private final LeagueDetailsService lds;
		
		@Autowired
		public WebController (TeamService ts,
				TeamParticipatesService tps,
				EventService es,
				PlayerParticipatesService pps,
				PlayerService ps,
				TournamentDetailsService tds,
				LeagueDetailsService lds) {
			this.ts = ts;
			this.tps = tps;
			this.es = es;
			this.pps = pps;
			this.ps = ps;
			this.tds = tds;
			this.lds = lds;
		}
		
		
	   @RequestMapping(value = "/index")
	   public String index() {
	      return "index";
	   }
	   
	   @RequestMapping(value = "/login")
	   public String login(Model model) {
		   model.addAttribute("player", new Player());
		   return "login";
	   }
	   
	   @PostMapping(value = "/login")
	   public String login
	   (Player player, Model model) {
		   Player res = ps.checkLogin(player.getUsername(), player.getPassword());
		   
		   if (res == null) {
			   return "login";
		   }
		   
		   model.addAttribute("USER_ID", res.getId());
		   return browseEvents(model);
	   }
	   
	   @RequestMapping(value = "/leagues")
	   public String leagues() {
		   return "leagues";
	   }
	   
	   @RequestMapping(value = "/events")
	   public String events() {
		   return "events";
	   }
	   
	   
	   
	   @GetMapping(value = "/teams") 
		public String webTeams(Model model) {
			model.addAttribute("teams", ts.findAllTeamsWithoutBye());
			return "teams";
		}
	   
	   @GetMapping(value ="/teams/{id}")
	   public String teamPage 
	   (@PathVariable("id") int id, Model model) {
		   Team team = ts.findById(id);
		   model.addAttribute("team", team);
		   model.addAttribute("manager", ps.findById(team.getManagerId()));
		   model.addAttribute("players", ps.findPlayersByTeam(id));
		   model.addAttribute("events", es.findEventsByTeam(id));
		   
		   return "team";
	   }
	   
	   @GetMapping(value = "/teams/players/{id}")
	   public String personalTeams
	   (@PathVariable("id") int id, Model model) {
		   model.addAttribute("managedTeams", ts.findTeamsByManager(id));
		   model.addAttribute("playerTeams", ts.findTeamsByPlayer(id));
		   return "personalTeams";
	   }
	   
	   @GetMapping(value = "/players/{id}")
	   public String playerProfile
	   (@PathVariable("id") int id, Model model) {
		   model.addAttribute("player", ps.findById(id));
		   
		   return "playerProfile";
	   }
	   
	   @GetMapping(value = "/navbar")
	   public String webNavBar(Model model) {
		   return "navbar";
	   }
	   
	   @GetMapping(value = "/createAccount") 
	   public String createAccount(Model model) {
		   model.addAttribute("player", new Player());
		   return "createAccount";
	   }
	   
	   @PostMapping(value = "/createAccount")
	   public String createAccountSubmit
	   (@ModelAttribute Player player, Model model) {
		   ps.save(player);
		   return browseEvents(model);
	   }
	   
	   @GetMapping(value = "/createTeam")
	   public String createTeam(Model model) {
		   model.addAttribute("team", new Team());
		   return "createTeam";
	   }
	   
	   @PostMapping(value = "/createTeam")
	   public String createTeamSubmit
	   (@ModelAttribute Team team, Model model) {
		   team.setManagerId(0); // fix later
		   ts.save(team);
		   return browseEvents(model);
	   }
	   
	   @GetMapping(value = "/createEvent")
	   public String createEvent (Model model) {
		   model.addAttribute("event", new TotalEventDetails());
		   return "createEvent";
	   }
	   
	   @PostMapping(value = "/createEvent")
	   public String createEventSubmit
	   (@ModelAttribute TotalEventDetails event,
		Model model) {
		   try {
			   if (event.getEvent_type() != 0) {
				   event.setEvent_type(event.getEvent_type() - 1);
			   }
			   
			   Event e = event.getEventDetails();
			   Event res = es.save(e);
			   if (e.getType() == 0) {
				   LeagueDetails league = event.getLeagueDetails(res.getId());
				   lds.save(league);
			   } else {
				   TournamentDetails tournament = event.getTournamentDetails(res.getId());
				   tds.save(tournament);
			   }
		   } catch (Exception e) {
			   System.out.println(e.getMessage());
			   return "error";
		   }
		   
		   return browseEvents(model);
	   }
	   
	   @GetMapping(value = "/events")
	   public String browseEvents
	   (Model model) {
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
	   (@PathVariable("id") int id, Model model) {
		   model.addAttribute("event", es.findById(id));
		   model.addAttribute("teams", ts.findAllTeamsByEventId(id));
		   return "event";
	   }
	   
	   @PostMapping(value = "/addPlayerToTeam/{player}/{team}")
	   public String addPlayerToTeam
	   (@PathVariable("player") int player,
		@PathVariable("team") int team,
		Model model) {
		   PlayerParticipates pp = new PlayerParticipates();
		   pp.setPlayerId(player);
		   pp.setTeamId(team);
		   PlayerParticipates res = pps.save(pp);
		   return playerProfile(player, model);
	   }
	   
	   
}	
