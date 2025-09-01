package com.telusko.JobApp.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Employer 
{

	@Id
	private String username;
	private String password;
	private String email;
	private String phone_no;
	@OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<JobPost> jobs;
	
	
	public Employer()
	{
		jobs=new ArrayList<JobPost>();
	}
	
	public List<JobPost> getJobs() {
		return jobs;
	}
	public void addJobs(JobPost job) {
		jobs.add(job);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	@Override
	public String toString() {
		return "Employer [username=" + username + ", password=" + password + ", email=" + email + ", phone_no="
				+ phone_no + "]";
	}
	
}
