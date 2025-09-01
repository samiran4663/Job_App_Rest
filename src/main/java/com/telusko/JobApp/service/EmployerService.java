package com.telusko.JobApp.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.telusko.JobApp.model.Employer;
import com.telusko.JobApp.model.JobPost;
import com.telusko.JobApp.model.User;
import com.telusko.JobApp.repository.EmployerRepo;
import com.telusko.JobApp.repository.JobPostRepo;

@Service
public class EmployerService 
{

	@Autowired
	JobPostRepo jobrepo;
	
	@Autowired
	EmployerRepo emprepo;
	public void register(Employer employer)
	{
		if(!emprepo.existsById(employer.getUsername()))
		{
			employer.setPassword(new BCryptPasswordEncoder(12).encode(employer.getPassword()));
			emprepo.save(employer);
		}
	}
	public void addJob(JobPost job,String username)
	{
		Employer employer=emprepo.findById(username).orElseThrow(()->new RuntimeException("Employer does not exist"));
		
		job.setEmployer(employer);
		
		employer.addJobs(job);
		
		emprepo.save(employer);
		
	}
	public List<User> getApplicants(String username,int job_id)
	{
		JobPost job=jobrepo.findById(job_id).orElseThrow(()->new RuntimeException("Job does not exist"));
		if(job.getEmployer().getUsername().equals(username))
		{
			return job.getApplicants();
		}
		return Collections.emptyList();
	}
	public void deleteJob(int job_id)
	{
		JobPost job=jobrepo.findById(job_id).orElseThrow(()->new RuntimeException("Job does not exist"));
		jobrepo.delete(job);
	}
	public List<JobPost> getJobs(String username)
	{
		Employer employer=emprepo.findById(username).orElseThrow(()-> new RuntimeException("Employer does not exists"));
		return employer.getJobs();
	}
}
