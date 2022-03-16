package com.bruno.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.bruno.models.Job;

@Service
public class JobServiceImpl implements JobService {

	private static final AtomicLong counter = new AtomicLong();
	private static List<Job> jobs;
	
	static {
		// Dummy Jobs
		jobs = populateDummyJobs();
	}
	
	@Override
	public List<Job> getAllJobs() {
		return jobs;
	}
	
	public void addJob(Job job) {
		job.setId(counter.incrementAndGet());
		jobs.add(job);
	}
	
	@Override
	public Job getById(Long id) {
		return jobs.stream()
			.filter(j -> j.getId() == id)
			.findFirst()
			.orElse(null);
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
			.filter(j -> id != j.getId())
			.collect(Collectors.toList());
		
		jobs = newJobs;
	}

	
	public static List<Job> populateDummyJobs() {
		List<Job> jobs = new ArrayList<Job>(
				Arrays.asList(
				new Job(counter.incrementAndGet(), "Bob Green", "12 Main St", "b.green@gmail.com", "201-758-4545", "04/15/2022", "Yard Work"),
				new Job(counter.incrementAndGet(), "Alex Leo", "31 Broadway Ave", "a.leo@gmail.com", "201-959-4522", "04/16/2022", "Tree Cutting"),
				new Job(counter.incrementAndGet(), "Ariel Lazzu", "421 Madison Rd", "", "201-645-8941", "04/25/2022", "Yard Work"),
				new Job(counter.incrementAndGet(), "Jean Nina", "2 Mcconnell Ave", "", "862-112-4576", "04/25/2022", "Pool Work")
				));
		return jobs;
	}

	

	
}
