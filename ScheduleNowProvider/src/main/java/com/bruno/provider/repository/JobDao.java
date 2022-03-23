package com.bruno.provider.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bruno.provider.model.Job;

public interface JobDao extends JpaRepository<Job, Long> {
}
