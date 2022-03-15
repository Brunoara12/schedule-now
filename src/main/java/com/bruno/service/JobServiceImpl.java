package com.bruno.service;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.dao.JobDao;
import com.bruno.models.Job;

@Service
@Transactional
public class JobServiceImpl implements JobService {

	private static List<Job> jobs;
	
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
		OptionalInt index = IntStream.range(0, jobs.size())
			.filter(i -> id == jobs.get(i).getId())
			.findFirst();
		
		if (index.isPresent() == false)
			return null;
		
		jobs.set(index.getAsInt(), job);
		
		return jobs.get(index.getAsInt());
	}
	
	@Override
	public void deleteJob(Long id) {
		List<Job> newJobs = jobs.stream()
			.filter(j -> id.equals(j.getId()))
			.collect(Collectors.toList());
		
		jobs = newJobs;
	}

	
}
