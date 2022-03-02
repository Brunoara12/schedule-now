package com.bruno.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruno.models.Job;
import com.bruno.service.JobService;

@Controller
@RequestMapping("/scheduling")
public class SchedulingController {

	Logger logger = LoggerFactory.getLogger(SchedulingController.class);

	
	@Autowired
	private JobService jobService;
	
	@GetMapping("/")
	public String getSchedulePage() {
		
		return "scheduling";
	}
	
	
	@GetMapping("/jobs")
	public ResponseEntity<List<Job>> getAllJobs() {
	
		List<Job> jobs = jobService.getAllJobs();
		
		return jobs.isEmpty() ? new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT) :
			new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}
 
}
