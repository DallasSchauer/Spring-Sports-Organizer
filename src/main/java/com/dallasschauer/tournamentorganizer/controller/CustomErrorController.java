package com.dallasschauer.tournamentorganizer.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CustomErrorController implements ErrorController {
	
	private static final String PATH = "error";
	
	public String getErrorPath() {
		return PATH;
	}
	
	@RequestMapping(value = "/error")
	   public String error() {
		   return "error";
	   }
}
