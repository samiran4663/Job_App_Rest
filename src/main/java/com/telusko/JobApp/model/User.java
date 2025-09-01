package com.telusko.JobApp.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;



@Entity
@Table(name="users")
public class User 
{

	@Id
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="address")
	private String address;
	@Column(name="email")
	private String email;
	@Column(name="phone_number")
	private String phone_no;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		    name = "user_applied_jobs",
		    joinColumns = @JoinColumn(name = "user_username", referencedColumnName = "username"),
		    inverseJoinColumns = @JoinColumn(name = "job_id", referencedColumnName = "job_id"),
		    uniqueConstraints = @UniqueConstraint(
		        name = "uk_user_job",
		        columnNames = {"user_username","job_id"}
		    )
		)
	private List<JobPost> appliedJobs;
	public User()
	{
		appliedJobs=new ArrayList<JobPost>();
	}
	public List<JobPost> getAppliedJobs() {
		return appliedJobs;
	}
	public void addAppliedJob(JobPost jp) {
		appliedJobs.add(jp);
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
		return "User [username=" + username + ", password=" + password + ", address=" + address + ", email=" + email
				+ ", phone_no=" + phone_no + "]";
	}
	
}
