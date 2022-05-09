package com.bruno.provider.service;

import java.util.List;

import com.bruno.provider.model.Job;

public interface JobService {

	Job saveJob(Job job);
	
	List<Job> getAllJobs();
	
	Job getById(Long id);

	Job update(Long id, Job job);

	void deleteJob(Long id);
}
