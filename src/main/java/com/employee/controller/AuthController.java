package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.payload.JwtAuthResponse;
import com.employee.security.CustomUserDetailsService;
import com.employee.security.JwtTokenHelper;
import com.blog.exceptions.APIException;
import com.employee.payload.JwtAuthRequest;
import com.employee.service.EmployeeService;

//Step 7
@RestController
public class AuthController 
{
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private CustomUserDetailsService customUserDetailService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private EmployeeService employeeService;
	
	
	@PostMapping()
	public ResponseEntity<JwtAuthResponse>  createToken(@RequestBody JwtAuthRequest request)
	{
		
		System.out.println(request.getUsername());
		System.out.println(request.getPassword());
		
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetaials=this.customUserDetailService.loadUserByUsername(request.getUsername());
		
		String token=this.jwtTokenHelper.generateToken(userDetaials);
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
		
	}

	
	
	
	
	public void authenticate(String username, String password)
	{
	 UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);
	 try
	 {
		this.authenticationManager.authenticate(authenticationToken); 
	 }catch(BadCredentialsException e)
	 {
		 System.out.println("Invalid Username and Password !!! ");
		    throw new APIException("Invalid usename and password");
	 }
	}
	
//  Register new user API
//	@PostMapping("/register")
//	public ResponseEnityt<?> registerEmployee()
//	{
//		
//	}
	
	
	
}











