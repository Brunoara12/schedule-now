package com.bruno.provider.service;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.bruno.provider.repository.JobDao;

@TestConfiguration
public class JobMocks {

	@Bean
	@Primary
	public JobDao jobRepo() {
		return Mockito.mock(JobDao.class);
	}
}
