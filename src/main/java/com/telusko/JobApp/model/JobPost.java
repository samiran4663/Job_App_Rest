package com.telusko.JobApp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Converter
class StringListConverter implements AttributeConverter<List<String>, String>
{

	@Override
	public String convertToDatabaseColumn(List<String> attribute) {
		// TODO Auto-generated method stub
		return (attribute==null || attribute.isEmpty()) ? null:String.join(",", attribute);
	}

	@Override
	public List<String> convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		return (dbData==null || dbData.isEmpty())?null:new ArrayList<String>(List.of(dbData.split(",")));
	}
	
}
@Entity
public class JobPost 
{

	@Id
	private int job_id;
	private String organization;
	private String title;
	private String description;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date enddate;
	

	@Convert(converter = StringListConverter.class)
	private List<String> techstack;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "appliedJobs", fetch = FetchType.LAZY)
	private List<User> applicants;
	
	// Many side One employer many jobs and the relationship is stored on many side i.e owner of the relationship
	
	//JoinColumn used mainly in the owner side of the relation , so in the jobPost table  we have one attribute employer_username(fk)
	//referencing to username(pk) attribute in employer table
	// in jpa 'fk' is placed in the owning side of the relation
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)   
    @JoinColumn(name = "employer_username",referencedColumnName = "username") 
	private Employer employer;
	
	
	public JobPost() {
		applicants=new ArrayList<>();
	}
	
	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	
	public Employer getEmployer() {
		return employer;
	}
	public void setEmployer(Employer employer) {
		this.employer = employer;
	}
	
	public List<User> getApplicants() {
		return applicants;
	}
	public void setApplicants(User user) {
		applicants.add(user);
	}
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getTechstack() {
		return techstack;
	}
	public void setTechstack(List<String> techstack) {
		this.techstack = techstack;
	}

	@Override
	public String toString() {
		return "JobPost [job_id=" + job_id + ", organization=" + organization + ", title=" + title + ", description="
				+ description + ", enddate=" + enddate + ", techstack=" + techstack + "]";
	}

	
	
	
	
}
