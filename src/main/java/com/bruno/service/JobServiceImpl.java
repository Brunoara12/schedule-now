package com.bruno.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.bruno.models.Job;

@Service
public class JobServiceImpl implements JobService {

	private static final AtomicLong counter = new AtomicLong();
	private static List<Job> jobs;
	
	static {
		// Dummy Jobs
		jobs = populateDummyJobs();
//		jobs = Arrays.asList(
//				new Job(counter.incrementAndGet(), "Bob Green", "12 Main St", "b.green@gmail.com", "201-758-4545", "04/15/2022", "Yard Work"),
//				new Job(counter.incrementAndGet(), "Alex Leo", "31 Broadway Ave", "a.leo@gmail.com", "201-959-4522", "04/16/2022", "Tree Cutting"),
//				new Job(counter.incrementAndGet(), "Ariel Lazzu", "421 Madison Rd", "", "201-645-8941", "04/25/2022", "Yard Work"),
//				new Job(counter.incrementAndGet(), "Jean Nina", "2 Mcconnell Ave", "", "862-112-4576", "04/25/2022", "Pool Work")
//				);
	}
	
	@Override
	public List<Job> getAllJobs() {
		return jobs;
	}
	
	public static List<Job> populateDummyJobs() {
		List<Job> jobs = Arrays.asList(
				new Job(counter.incrementAndGet(), "Bob Green", "12 Main St", "b.green@gmail.com", "201-758-4545", "04/15/2022", "Yard Work"),
				new Job(counter.incrementAndGet(), "Alex Leo", "31 Broadway Ave", "a.leo@gmail.com", "201-959-4522", "04/16/2022", "Tree Cutting"),
				new Job(counter.incrementAndGet(), "Ariel Lazzu", "421 Madison Rd", "", "201-645-8941", "04/25/2022", "Yard Work"),
				new Job(counter.incrementAndGet(), "Jean Nina", "2 Mcconnell Ave", "", "862-112-4576", "04/25/2022", "Pool Work")
				);
		return jobs;
	}

}
