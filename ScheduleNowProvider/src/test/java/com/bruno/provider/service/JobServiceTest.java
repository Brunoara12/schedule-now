package com.bruno.provider.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bruno.provider.model.Job;
import com.bruno.provider.repository.JobDao;

@ActiveProfiles("test")
@SpringBootTest( classes = {JobService.class, JobServiceImpl.class, JobMocks.class})
public class JobServiceTest extends AbstractTestNGSpringContextTests{

	@Autowired
	JobDao jobsRepo;
	
	@Autowired
	JobService jobsService;
	
	@BeforeClass
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void givenThreeJobs_whenRetrievingAllJobs_thenReturnListOfJobs() {
		Job job1 = createSampleJob(1L);
		Job job2 = createSampleJob(2L);
		Job job3 = createSampleJob(3L);
		List<Job> jobs = new ArrayList<>();
		jobs.add(job1);
		jobs.add(job2);
		jobs.add(job3);
		Mockito.when(jobsRepo.findAll()).thenReturn(jobs);
		
		List<Job> mockJobs = jobsService.getAllJobs();
		
		assertEquals(mockJobs, jobs);
		
		Mockito.verify(jobsRepo, Mockito.atMost(2)).findAll();
	}
	
	@Test
	public void givenNothing_whenRetrievingAllJobs_thenReturnEmptyList() {
		List<Job> jobs = new ArrayList<>();
		Mockito.when(jobsRepo.findAll()).thenReturn(jobs);
		
		List<Job> mockJobs = jobsService.getAllJobs();
		
		assertEquals(mockJobs, jobs);
		
		Mockito.verify(jobsRepo, Mockito.atMost(2)).findAll();
	}
	
	@Test
	public void givenId_whenFindingJob_thenReturnJob() {
		Job job = createSampleJob(1L);
		Mockito.when(jobsRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(job));
		
		Job mockJob = jobsService.getById(1L);

		assertEquals(mockJob, job);
		
		Mockito.verify(jobsRepo).findById(1L);
	}
	
	@Test
	public void givenInvalidId_whenFindingJob_thenReturnNullOptional() {
		Job job = createSampleJob(2L);
		Mockito.when(jobsRepo.findById(5L)).thenReturn(Optional.empty());
		Mockito.when(jobsRepo.findById(2L)).thenReturn(Optional.of(job));

		Job mockJob1 = jobsService.getById(5L);
		Job mockJob2 = jobsService.getById(2L);

		assertEquals(mockJob1, null);
		assertEquals(mockJob2, job);
		
		Mockito.verify(jobsRepo).findById(2L);
		Mockito.verify(jobsRepo).findById(5L);
	}
	
	@Test
	public void givenJob_whenAddingJob_thenSaveJob() {
		Job job = createSampleJob(1L);
		Mockito.when(jobsRepo.save(Mockito.any())).thenReturn(job);
		
		Job mockJob = jobsService.saveJob(job);
		
		assertEquals(mockJob, job);
		
		Mockito.verify(jobsRepo).save(job);
		
	}
	
	@Test
	public void givenJob_whenUpdatingJob_thenSaveUpdatedJobAndSave() {
		Job job = createSampleJob(1L);
		Job updatedJob= createSampleJob(1L);
		
		updatedJob.setCustomerName(updatedJob.getCustomerName() + " Rebaza");
		updatedJob.setAddress(updatedJob.getAddress() + "reet");
		updatedJob.setEmail("rebaza." + updatedJob.getEmail());
		updatedJob.setPhone("(973)123-4567");
		updatedJob.setDescription(updatedJob.getDescription() + " and Pool Work");

		Mockito.when(jobsRepo.save(Mockito.any())).thenReturn(job, updatedJob);
		Mockito.when(jobsRepo.getById(Mockito.anyLong())).thenReturn(job);

		Job mockJob = jobsService.saveJob(job);
		mockJob.setCustomerName(updatedJob.getCustomerName() + " Rebaza");
		mockJob.setAddress(updatedJob.getAddress() + "reet");
		mockJob.setEmail("rebaza." + updatedJob.getEmail());
		mockJob.setPhone("(973)123-4567");
		mockJob.setDescription(updatedJob.getDescription() + " and Pool Work");
		
		Job mockUpdateJob = jobsService.update(1L, mockJob);
		
		assertNotNull(mockUpdateJob);
		assertEquals(mockUpdateJob.getId(), 1L);
		assertEquals(mockUpdateJob.getCustomerName(), "Bruno Rebaza");
		assertEquals(mockUpdateJob.getAddress(), "127 E 32nd Street");
		assertEquals(mockUpdateJob.getEmail(), "rebaza.bruno@gmail.com");
		assertEquals(mockUpdateJob.getPhone(), "(973)123-4567");
		assertEquals(mockUpdateJob.getDescription(), "Yard Work and Pool Work");

	}
	
	@Test
	public void givenId_whenDeleteJob_ReturnNothingAndDelete() {
		Mockito.doNothing().when(jobsRepo).deleteById(1L);
		jobsService.deleteJob(1L);
		Mockito.verify(jobsRepo).deleteById(1L);
	}
	
	public Job createSampleJob(Long i) {
		Job job = new Job();
		job.setId(i);
		job.setCustomerName("Bruno");
		job.setAddress("127 E 32nd St");
		job.setEmail("bruno@gmail.com");
		job.setPhone("973-123-4567");
		job.setDateTime(null);
		job.setDescription("Yard Work");
		
		return job;
		
	}
}
