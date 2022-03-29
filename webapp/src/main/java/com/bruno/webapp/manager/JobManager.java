package com.bruno.webapp.manager;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bruno.webapp.model.Job;

public interface JobManager {

	ResponseEntity<Job> saveJob(Job job);
	
	ResponseEntity<List<Job>> getAllJobs();
	
	ResponseEntity<Job> getById(Long id);

	ResponseEntity<Job> updateJob(Long id, Job job);

	void deleteJob(Long id);
}