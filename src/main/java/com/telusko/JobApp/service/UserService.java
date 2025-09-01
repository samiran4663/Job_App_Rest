package com.telusko.JobApp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.telusko.JobApp.model.JobPost;
import com.telusko.JobApp.model.User;
import com.telusko.JobApp.repository.JobPostRepo;
import com.telusko.JobApp.repository.UserRepo;

@Service
public class UserService 
{

	@Autowired
	UserRepo userrepo;
	
	@Autowired
	JobPostRepo jobrepo;
	public void register(User user)
	{
		if(!userrepo.existsById(user.getUsername()))
		{
			user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
			userrepo.save(user);
		}
	}
	public List<JobPost> getAllJobs()
	{
		return jobrepo.findAll();
	}
	
	public JobPost getJob(int job_id)
	{
		return jobrepo.findById(job_id).orElseGet(()->new JobPost());
	}
	
	public List<JobPost> searchbykeyword(String keyword)
	{
		return jobrepo.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword,keyword);
	}
	
	public String applyjob(String username,int job_id)
	{
		User user=userrepo.findById(username).orElseThrow(()->new RuntimeException("User not found"));
		JobPost job=jobrepo.findById(job_id).orElseThrow(()->new RuntimeException("Job does not exist"));
		if(user.getAppliedJobs().contains(job))
		{
			return "User " + username + " already applied to job " + job_id;
		}
		else if(!user.getAppliedJobs().contains(job) && job.getEnddate().after(new Date()))
		{
			
			user.addAppliedJob(job);
			job.setApplicants(user);
			userrepo.save(user);
			return "User "+username +" successfully applied for the job "+job_id;
		}
		return "Application Date is over";
	}
	public List<JobPost> appliedJobs(String username)
	{
		User user=userrepo.findById(username).orElseThrow(()-> new RuntimeException("User not found"));
		return user.getAppliedJobs();
		
	}
}
