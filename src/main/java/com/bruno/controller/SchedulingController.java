package com.bruno.controller;

import java.sql.Timestamp;
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

import com.bruno.dto.JobDto;
import com.bruno.models.Job;
import com.bruno.service.JobService;

@RestController
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
	
	@PostMapping("/jobs")
	public ResponseEntity<Job> addJob(@RequestBody JobDto jobDto) {
		jobDto.setDate(jobDto.getDate().replace('T', ' ').substring(0,19));
		Job job = new Job();
		job.setAddress(jobDto.getAddress());
		job.setCustomerName(jobDto.getCustomerName());
		job.setDate(Timestamp.valueOf(jobDto.getDate()));
		job.setDescription(jobDto.getDescription());
		job.setEmail(jobDto.getEmail() != null? jobDto.getEmail() : "");
		job.setPhone(jobDto.getPhone());
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
