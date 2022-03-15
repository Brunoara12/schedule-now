package com.bruno.dao;

import java.util.List;

import com.bruno.models.Job;

public interface JobDao {
	Job findById(Long id);
	List<Job> findAllJobs();
	void save(Job job);
}
