 package com.employee.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class LeaveEmployee
{
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
     private int leaveId;
     private LocalDate fromDate;
     private LocalDate toDate;
     private String reasonToLeave;
	
    
    
     @OneToOne(targetEntity = Employee.class)
     private Employee employee;

     @OneToOne(targetEntity = LeaveImage.class)
     private LeaveImage leaveImage;

//     @OneToMany(targetEntity = LeaveImage.class)
//     private List<LeaveEmployee> imageId;  
//     
}
