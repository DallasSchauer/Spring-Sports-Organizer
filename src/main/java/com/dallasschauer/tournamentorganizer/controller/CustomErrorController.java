package com.dallasschauer.tournamentorganizer.controller;

import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CustomErrorController implements ErrorController {
	
	private static final String PATH = "error";
	
	public String getErrorPath() {
		return PATH;
	}
	
	@RequestMapping(value = "/error")
	   public String error(HttpSession session, 
			   Model model) {
		   return "error";
	   }
}
