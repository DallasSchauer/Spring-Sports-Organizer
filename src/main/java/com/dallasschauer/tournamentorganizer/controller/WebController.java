package com.dallasschauer.tournamentorganizer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dallasschauer.tournamentorganizer.entity.Account;
import com.dallasschauer.tournamentorganizer.entity.PlayerParticipates;
import com.dallasschauer.tournamentorganizer.entity.Team;
import com.dallasschauer.tournamentorganizer.service.EventService;
import com.dallasschauer.tournamentorganizer.service.PlayerParticipatesService;
import com.dallasschauer.tournamentorganizer.service.TeamParticipatesService;
import com.dallasschauer.tournamentorganizer.service.TeamService;

@Controller
public class WebController {
		private final TeamService ts;
		private final TeamParticipatesService tps;
		private final EventService es;
		private final PlayerParticipatesService pps;
		
		@Autowired
		public WebController (TeamService ts,
				TeamParticipatesService tps,
				EventService es,
				PlayerParticipatesService pps) {
			this.ts = ts;
			this.tps = tps;
			this.es = es;
			this.pps = pps;
		}
	
	
	   @RequestMapping(value = "/index")
	   public String index() {
	      return "index";
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
	   
	   @GetMapping(value = "/navbar")
	   public String webNavBar(Model model) {
		   return "navbar";
	   }
	   
	   @GetMapping(value = "/createAccount") 
	   public String createAccount(Model model) {
		   model.addAttribute("account", new Account());
		   return "createAccount";
	   }
	   
	   @PostMapping(value = "/createAccount")
	   public String createAccountSubmit
	   (@ModelAttribute Account account, Model model) {
		   model.addAttribute("account", account);
		   return "teams";
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
		   return "browseEvents";
	   }
	   
	   @GetMapping(value = "/teams/{id}")
	   public String personalTeams
	   (@PathVariable("id") int id, Model model) {
		   model.addAttribute("managedTeams", ts.findTeamsByManager(id));
		   model.addAttribute("playerTeams", ts.findTeamsByPlayer(id));
		   return "personalTeams";
	   }
}	
