package com.telusko.JobApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telusko.JobApp.model.Employer;

@Repository
public interface EmployerRepo extends JpaRepository<Employer, String>
{

	
}
