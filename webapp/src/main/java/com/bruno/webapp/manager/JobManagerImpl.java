package com.bruno.webapp.manager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bruno.webapp.model.Job;

@Service
public class JobManagerImpl implements JobManager {
	
	Logger logger = LoggerFactory.getLogger(JobManagerImpl.class);
	private final String RESOURCE_URI = "/scheduling/jobs";
	
	@Value("${service.url}")
	private String serviceBaseUrl;
	
	@Autowired
	private RestTemplate restTemplate;

	
	@Override
	public ResponseEntity<Job> saveJob(Job job) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		logger.info(requestUri);
		return restTemplate.postForEntity(requestUri, job, Job.class);
	}
	

	@Override
	public ResponseEntity<List<Job>> getAllJobs() {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		ResponseEntity<Job[]> jobs = restTemplate.getForEntity(
				requestUri,
				Job[].class);
		
		List<Job> jobsL = Arrays.stream(jobs.getBody())
				.collect(Collectors.toList());
		
		System.out.println(requestUri);
		System.out.println("HELLOO");
		System.out.println(jobs.getBody()[0].toString());
		return new ResponseEntity<List<Job>>(jobsL, HttpStatus.OK);
	}
	
	
	@Override
	public ResponseEntity<Job> getById(Long id) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		return restTemplate.getForEntity(requestUri + "/{id}", Job.class, Long.toString(id));
	}

	@Override
	public ResponseEntity<Job> updateJob(Long id, Job job) {		
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		return restTemplate.exchange(requestUri + "/{id}", 
				HttpMethod.PUT,
				new HttpEntity<>(job),
				Job.class,
				Long.toString(id));

	}
	
	@Override
	public void deleteJob(Long id) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		restTemplate.delete(requestUri + "/{id}", Long.toString(id));
	}

	
}