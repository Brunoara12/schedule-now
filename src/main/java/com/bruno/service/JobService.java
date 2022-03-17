package com.bruno.service;

import java.util.List;

import com.bruno.models.Job;



public interface JobService {

	void saveJob(Job job);
	
	List<Job> getAllJobs();
	
	Job getById(Long id);

	Job update(Long id, Job job);

	void deleteJob(Long id);
}
