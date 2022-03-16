package com.bruno.service;

import java.util.List;

import com.bruno.models.Job;


public interface JobService {

	public List<Job> getAllJobs();
	
	public Job getById(Long id);
	
	public void addJob(Job job);

	public Job update(Long id, Job job);

	public void deleteJob(Long id);
}
