package com.telusko.JobApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telusko.JobApp.model.JobPost;

@Repository
public interface JobPostRepo extends JpaRepository<JobPost, Integer> {

	public List<JobPost> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase( String titleKeyword,
		    String descriptionKeyword);
	
}
