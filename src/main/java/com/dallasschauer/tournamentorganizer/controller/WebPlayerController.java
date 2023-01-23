package com.dallasschauer.tournamentorganizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dallasschauer.tournamentorganizer.entity.Team;

import com.dallasschauer.tournamentorganizer.service.PlayerParticipatesService;
import com.dallasschauer.tournamentorganizer.service.TeamParticipatesService;
import com.dallasschauer.tournamentorganizer.service.TeamService;

@Controller
public class WebPlayerController {
	private final TeamService ts;
	private final TeamParticipatesService tps;
	private final PlayerParticipatesService pps;
	
	@Autowired
	public WebPlayerController (TeamService ts,
			TeamParticipatesService tps,
			PlayerParticipatesService pps) {
		this.ts = ts;
		this.tps = tps;
		this.pps = pps;
	}
	
	@GetMapping(value = "/player/teams/{id}")
	public String playerFindTeams(@PathVariable("id") int id,
			Model model) {
		model.addAttribute("teams", ts.findTeamsByPlayer(id));
		return "teams";
	}
}
