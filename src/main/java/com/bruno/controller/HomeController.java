package com.bruno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	
	@GetMapping("/")
	public ModelAndView getHomePage() {
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("index");
		
		return mv;	}
	
	@GetMapping("/login")
	public ModelAndView getLoginPage() {
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("index");
		
		return mv;
	}

	@PostMapping("/login")
	public ModelAndView submit(@RequestParam("username") String username, 
			@RequestParam("password") String password) {
		
		ModelAndView mv = new ModelAndView();
		if(username.length() >= 3 && password.length() >= 5) {
			mv.setViewName("scheduling");
			mv.addObject("username",username);
		} else {
			mv.setViewName("index");
			mv.addObject("validation", "Username or password is not long enough");
		}
		
		return mv;
	}
}
