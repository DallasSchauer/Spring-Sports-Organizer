package com.dallasschauer.tournamentorganizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dallasschauer.tournamentorganizer.service.TeamParticipatesService;
import com.dallasschauer.tournamentorganizer.service.TeamService;

@Controller
public class WebController {
		private final TeamService ts;
		private final TeamParticipatesService tps;
		
		@Autowired
		public WebController (TeamService ts,
				TeamParticipatesService tps) {
			this.ts = ts;
			this.tps =tps;
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
			model.addAttribute("teams", ts.findAll());
			return "teams";
		}
}	
