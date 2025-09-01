package com.telusko.JobApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.telusko.JobApp.model.Employer;

import com.telusko.JobApp.model.User;
import com.telusko.JobApp.repository.EmployerRepo;
import com.telusko.JobApp.repository.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService
{

	@Autowired
	UserRepo userrepo;
	
	@Autowired
	 EmployerRepo emprepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userrepo.findById(username).orElse(null);
		if(user==null)
		{
			Employer employer=emprepo.findById(username).orElse(null);
			if(employer==null)
			{
				throw new UsernameNotFoundException("User not Found");
			}
			else {
				UserDetails empdetails= org.springframework.security.core.userdetails.User
									  .withUsername(employer.getUsername())
									  .password(employer.getPassword())
									  .roles("EMPLOYER")
									  .build();
				return empdetails;
			}
		}
		UserDetails userdetails=org.springframework.security.core.userdetails.User
				.withUsername(user.getUsername())
				.password(user.getPassword())
				.roles("USER")
				.build();
		return userdetails;
		
	}

}
