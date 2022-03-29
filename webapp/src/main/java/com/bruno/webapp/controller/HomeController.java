package com.bruno.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bruno.webapp.model.User;

@Controller
public class HomeController {

	
	@GetMapping("/")
	public ModelAndView getHomePage() {
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("index");
		
		return mv;	
	}
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "redirect:/";
	}

	@PostMapping("/login")
	public ModelAndView submit(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
		if(user.getUsername().length() >= 3 && user.getPassword().length() >= 5) {
			redirectAttributes.addFlashAttribute("username", user.getUsername());
			return new ModelAndView("redirect:/scheduling/");
		} else {
			redirectAttributes.addFlashAttribute("validation", "Username or password is not long enough");
			return new ModelAndView("redirect:/");
		}
	}
}