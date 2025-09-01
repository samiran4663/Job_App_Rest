package com.telusko.JobApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.JobApp.model.JobPost;
import com.telusko.JobApp.model.User;
import com.telusko.JobApp.service.EmployerService;

@RestController
@RequestMapping("/employer")
public class EmployerController 
{
	@Autowired
	EmployerService empservice;
	
	@PostMapping("/{username}/job")
	public ResponseEntity<String> addjob(@RequestBody JobPost job,@PathVariable String username)
	{
		empservice.addJob(job, username);
		return ResponseEntity.ok("Job is added successfully by"+username);
	}
	@GetMapping("/{username}/jobs/{job_id}/applicants")
	public ResponseEntity<List<User>> getApplicants(@PathVariable String username,@PathVariable int job_id)
	{
		List<User>applicants= empservice.getApplicants(username, job_id);
		return ResponseEntity.ok(applicants);
	}
	@DeleteMapping("/job/{job_id}")
	public ResponseEntity<String> deleteJob(@PathVariable int job_id)
	{
		empservice.deleteJob(job_id);
		String msg="Job removed successfully";
		return ResponseEntity.ok(msg);
	}
	@GetMapping("{username}/job")
	public ResponseEntity<List<JobPost>> getAlljobs(@PathVariable String username)
	{
		List<JobPost> jobs=empservice.getJobs(username);
		return ResponseEntity.ok(jobs);
	}
}
