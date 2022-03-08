package com.bruno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bruno.models.User;

@Controller
public class HomeController {

	
	@GetMapping("/")
	public ModelAndView getHomePage() {
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("index");
		
		return mv;	
	}
	
	@GetMapping("/login")
	public ModelAndView getLoginPage() {
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("index");
		
		return mv;
	}

	@PostMapping("/login")
	public ModelAndView submit(@ModelAttribute User user) {
		
		ModelAndView mv = new ModelAndView();
		if(user.getUsername().length() >= 3 && user.getPassword().length() >= 5) {
			mv.setViewName("scheduling");
			mv.addObject("username",user.getUsername());
		} else {
			mv.setViewName("index");
			mv.addObject("validation", "Username or password is not long enough");
		}
		
		return mv;
	}
}
