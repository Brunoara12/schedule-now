package com.bruno.webapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bruno.webapp.manager.JobManager;
import com.bruno.webapp.model.Job;

@RestController
@RequestMapping("/scheduling")
public class SchedulingController {

	Logger logger = LoggerFactory.getLogger(SchedulingController.class);

	
	@Autowired
	private JobManager jobManager;
	
	@GetMapping("/")
	public ModelAndView getSchedulePage() {
		
		return new ModelAndView("scheduling");
	}
	
	
	@GetMapping("/jobs")
	public ResponseEntity<List<Job>> getAllJobs() {
		return jobManager.getAllJobs();
	}
	
	@GetMapping("/jobs/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable Long id) {
		return jobManager.getById(id);
	}
	
	@PostMapping("/jobs")
	public ResponseEntity<Job> addJob(@RequestBody Job job) {
		return jobManager.saveJob(job);
	}
	
	@PutMapping("/jobs/{id}")
	public ResponseEntity<?> updateJob(@PathVariable Long id, @RequestBody Job job) {
		return jobManager.updateJob(id, job);
	}
 
	@DeleteMapping("/jobs/{id}")
	public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
		jobManager.deleteJob(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
