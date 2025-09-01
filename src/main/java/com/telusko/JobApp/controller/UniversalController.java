package com.telusko.JobApp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.JobApp.model.Employer;
import com.telusko.JobApp.model.User;
import com.telusko.JobApp.service.EmployerService;
import com.telusko.JobApp.service.JwtService;
import com.telusko.JobApp.service.UserService;


@RestController
@RequestMapping("/auth")
public class UniversalController 
{
	@Autowired
	UserService userservice;
	
	@Autowired
	EmployerService empservice;
	
	@Autowired
	JwtService jwtservice;
	
	@Autowired
	AuthenticationManager authenticationManager; 
	
	@PostMapping("/register/user")
	public void registerUser(@RequestBody User user)
	{
		userservice.register(user);
	}
	
	@PostMapping("/register/Employer")
	public void registerEmployer(@RequestBody Employer employer)
	{
		empservice.register(employer);
	}
	@PostMapping("/login")
	public ResponseEntity<String> loginUsers(@RequestBody LoginRequest request)
	{
		try {
		    Authentication authentication = authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
		    );
		    if (authentication.isAuthenticated()) {
		        String token = jwtservice.generateToken(request.getUsername());
		        return ResponseEntity.ok(token);
		    }
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");

	}

}
class LoginRequest 
{
    private String username;
    private String password;

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
}

