package com.playground.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
	
@Controller public class LogoutController {
		
	@GetMapping("/logout")
	public String getLogout() { 
		return "redirect:/login"; 
	} 
	
	@PostMapping("/logout")
	public String postLogout() { 
		return "redirect:/login"; 
	} 
}
