package com.bruno.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.dao.JobDao;
import com.bruno.models.Job;

@Service
@Transactional
public class JobServiceImpl implements JobService {
	
	@Autowired
	private JobDao jobsRepo;

	
	@Override
	public void saveJob(Job job) {
		jobsRepo.save(job);
	}
	

	@Override
	public List<Job> getAllJobs() {
		return jobsRepo.findAllJobs();
	}
	
	
	@Override
	public Job getById(Long id) {
		return jobsRepo.findById(id);
	}

	@Override
	public Job update(Long id, Job job) {		
		return jobsRepo.update(id,job);
	}
	
	@Override
	public void deleteJob(Long id) {
		jobsRepo.deleteById(id);
	}

	
}
