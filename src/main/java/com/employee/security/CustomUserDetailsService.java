package com.employee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.blog.exceptions.ResourceNotFoundException;
import com.employee.dao.EmployeeRepository;
import com.employee.entities.Employee;

@Component
public class CustomUserDetailsService implements UserDetailsService

{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
	 
	Employee employee=this.employeeRepository.findByEmailId(username).orElseThrow(()-> new ResourceNotFoundException("User","Email :"+ username, 0));
		return employee;
	}

}
