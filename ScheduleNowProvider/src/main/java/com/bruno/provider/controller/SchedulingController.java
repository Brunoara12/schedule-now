package com.bruno.provider.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bruno.provider.model.Job;
import com.bruno.provider.service.JobService;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/scheduling")
public class SchedulingController {

	Logger logger = LoggerFactory.getLogger(SchedulingController.class);

	
	@Autowired
	private JobService jobService;
	
	@GetMapping("/")
	public ModelAndView getSchedulePage() {
		
		return new ModelAndView("scheduling");
	}
	
	
	@GetMapping("/jobs")
	public ResponseEntity<List<Job>> getAllJobs() {
	
		List<Job> jobs = jobService.getAllJobs();
		
		return jobs.isEmpty() ? new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT) :
			new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}
	
	@GetMapping("/jobs/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable Long id){
		Job job = jobService.getById(id);
		
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	@PostMapping("/jobs")
	public ResponseEntity<Job> addJob(@RequestBody Job job) {
		jobService.saveJob(job);
		
		return new ResponseEntity<Job>(job, HttpStatus.CREATED);
	}
	
	@PutMapping("/jobs/{id}")
	public ResponseEntity<?> updateJob(@PathVariable Long id, @RequestBody Job job) {
		Job newJob = jobService.update(id, job);
		return newJob != null ? new ResponseEntity<>(job, HttpStatus.OK) : 
			new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
 
	@DeleteMapping("/jobs/{id}")
	public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
		jobService.deleteJob(id);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}