package com.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.APIResponse;
import com.employee.entities.AddSalary;
import com.employee.entities.LeaveEmployee;
import com.employee.entities.ProjectDetails;
import com.employee.request.SalaryRequest;
import com.employee.service.AddSalaryService;

@RestController
@RequestMapping("api/addsalary")
public class AddSalaryController 
{
    @Autowired
	private AddSalaryService addSalaryService;
    
    
    
    //Add salary handler
    @PostMapping("")
    public ResponseEntity<AddSalary> salaryAdd(@RequestBody SalaryRequest addSalary)
    {
    	try
    	{
        AddSalary salary=this.addSalaryService.saveSalary(addSalary);
		return ResponseEntity.of(Optional.of(salary));
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }
    
    
    
    //Get Salary By Id
    @GetMapping("/{salaryId}")
    public ResponseEntity<Optional<AddSalary>> getSalary(@PathVariable ("salaryId") int salaryId)
    {
    	    	
    	 Optional<AddSalary> salary=this.addSalaryService.getSalaryById(salaryId);
    	
    	if(salary==null)
    	{
    	 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
    	}else
    	{
    	 return ResponseEntity.of(Optional.of(salary));
    	}
    	
    }
    
    
    
    
   
    // Get All Salary Details
//    @GetMapping("/addsalary")
//    public ResponseEntity<List<AddSalary>> showSalary()
//    {
//     try
//     {
//        List<AddSalary> list=this.addSalaryService.getAllSalaryDetails();
//    	return ResponseEntity.of(Optional.of(list));
//     }catch(Exception e)
//     {
//    	e.printStackTrace(); 
//    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//     }
//    
//    }
//    
    
    //Update Salary
   	@PutMapping("/{salaryId}")
   	public ResponseEntity<AddSalary> updateSalaryById(@RequestBody AddSalary addSalary, @PathVariable ("salaryId") int salaryId)
   	{
   		try
   		{
   		this.addSalaryService.updateSalary(addSalary, salaryId);
   		return  ResponseEntity.ok(null);
   		}catch(Exception e)
   		{
   			e.printStackTrace();
   			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
   		}
   	}
   	
   	
       //Delete Leave by Id
       @DeleteMapping("/{salaryId}")	
   	public ResponseEntity<AddSalary> deleteSalaryById(@PathVariable ("salaryId") int salaryId)
   	{
       	try
       	{
   		this.addSalaryService.deleteSalary(salaryId);
   		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
       	}catch(Exception e)
       	{
       		e.printStackTrace();
       		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       	}
 	
   	}
    
       // Get All Salary Details
       @GetMapping("")
       public ResponseEntity<List<AddSalary>> showSalary()
       {
        try
        {
           List<AddSalary> list=this.addSalaryService.getAllSalaryDetails();
       	return ResponseEntity.of(Optional.of(list));
        }catch(Exception e)
        {
       	e.printStackTrace(); 
       	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
       
       }
       
       
       @GetMapping("/pagination")
       public  APIResponse<Page<AddSalary>> getSalaryWithPagination(Pageable page)
       {
    	 Page<AddSalary>  pageSalary=addSalaryService.findAllSalaryWithPagination(page);
    	 
    	 return new APIResponse<>(pageSalary.getSize(), pageSalary);
       }
             // OR    
       
       
       
       @GetMapping("/paginations") // Sort(little bit sort )
       public Page<AddSalary>  getSalaryWithPage(Pageable page)
       {
    	   return this.addSalaryService.findAllSalaryWithPagination(page);
       }
}











