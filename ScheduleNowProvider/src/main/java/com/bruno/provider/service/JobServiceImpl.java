package com.bruno.provider.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.provider.model.Job;
import com.bruno.provider.repository.JobDao;

@Service
public class JobServiceImpl implements JobService{
	@Autowired
	private JobDao jobsRepo;

	
	@Override
	public void saveJob(Job job) {
		jobsRepo.save(job);
	}
	

	@Override
	public List<Job> getAllJobs() {
		return jobsRepo.findAll();
	}
	
	
	@Override
	public Job getById(Long id) {
		Optional<Job> job = jobsRepo.findById(id);
		return job.isPresent() ? job.get() : null;
	}

	@Override
	public Job update(Long id, Job job) {		
		return jobsRepo.save(job);
	}
	
	@Override
	public void deleteJob(Long id) {
		jobsRepo.deleteById(id);
	}

}
