package com.employee.entities;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="employee")
public class Employee implements UserDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int    employeeId;
	private String  firstName,  lastName;
	private String  contact,  emailId;
	private String  aadharCard, panCard;
	private String  bankName,   accountNumber, cifNumber;
	private String  experience;
	private String  previousCompanyName;
	private String  designation;
	private String  password;
	private String  gender;     
	private LocalDate dateOfBirth;
	
	@OneToMany(cascade =CascadeType.ALL)
	private List<Address> address;

	
	@OneToMany(cascade =CascadeType.ALL)
	private List<Qualification> qualification; 	
	
//    @ManyToMany(cascade = {CascadeType.ALL})
//    @JoinTable(name="employee_files",
//                                   joinColumns= {
//                                		   @JoinColumn(name="employeeId")
//                                		   },
//                                   inverseJoinColumns = {
//                                		   @JoinColumn(name="fileId")
//                                   } )                    	                                      
//                                   
//    private Set<FileStorage> fileStorage=new HashSet<FileStorage>();

	
	@OneToMany(targetEntity = FileStorage.class)
	private List<FileStorage> fileStorage;

	
	
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public String getUsername() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean isAccountNonExpired() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean isEnabled() {
	// TODO Auto-generated method stub
	return false;
}
	
	
}













