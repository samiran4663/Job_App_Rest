package com.telusko.JobApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.JobApp.model.JobPost;
import com.telusko.JobApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	UserService userservice;
	
	
	@PostMapping("/apply")
	public ResponseEntity<String> applyjob(@RequestParam String username, @RequestParam int job_id)
	{
		String msg=userservice.applyjob(username, job_id);
		return ResponseEntity.ok(msg);
	}
	@GetMapping("/jobs")
	public ResponseEntity<List<JobPost>> getAlljobs()
	{
		List<JobPost> jobs=userservice.getAllJobs();
		return ResponseEntity.ok(jobs);
	}
	@GetMapping("/job/id/{job_id}")
	public ResponseEntity<JobPost> getjob(@PathVariable int job_id)
	{
		return ResponseEntity.ok(userservice.getJob(job_id));
	}
	@GetMapping("/job/search/{keyword}")
	public ResponseEntity<List<JobPost>> searchbyKeyword(@PathVariable String keyword)
	{
		return ResponseEntity.ok(userservice.searchbykeyword(keyword));
	}
	@GetMapping("/jobs/{username}")
	public ResponseEntity<List<JobPost>> getappliedJobs(@PathVariable String username)
	{
		List<JobPost>appliedJobs=userservice.appliedJobs(username);
		
		return ResponseEntity.ok(appliedJobs);
	}
}
