package com.bruno.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bruno.models.Job;


@Service
public interface JobService {

	public void saveJob(Job job);
	
	public List<Job> getAllJobs();
	
	public Job getById(Long id);

	public Job update(Long id, Job job);

	public void deleteJob(Long id);
}
