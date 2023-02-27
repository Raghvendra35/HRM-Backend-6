package com.employee.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dao.EmailService;
import com.employee.dto.APIResponse;
import com.employee.entities.*;
import com.employee.request.EmployeeDropdownResponse;
import com.employee.service.EmailServiceEmp;
//import com.employee.entities.Address;
//import com.employee.entities.Qualification;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping("api/employee")
@CrossOrigin("*")
public class EmployeeController 
{
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmailServiceEmp emailService;
	
	//@Autowired
    //private Address address;
    //@Autowired
	//private Qualification qualification;
	
	Employee empl;
	
	
	
	
	
	
	
	//Get single Employee URL/handler
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Employee>> getEmployee(@PathVariable ("id") int id)
	{
	 Optional<Employee> employee=employeeService.getEmployeeById(id);
	  
	    if(employee==null)
	    {
	     return	ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	 
	    return ResponseEntity.of(Optional.of(employee));
		
    	}
	
	
	
	
	
	
	
	

	
	//Save employee
	@PostMapping("/save")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee)
	 {
			
		try
	    	{
		     
			 empl=employeeService.addNewEmployee(employee);
		     
			 this.emailService.mailSend(empl);
			 
			 return ResponseEntity.of(Optional.of(empl));
		
		   }catch(Exception e)
		   {
		    e.printStackTrace();
	     	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		   }
	 }

	
	//int employeeId=0;
	

	
	
	
	
	
	//Save employee and Get Id
	@PostMapping("/saveemployeeId")
	public ResponseEntity<Integer> getEmployeeId(@RequestBody Employee employee)
	 {
				
		try
	    	{
		      int employeeId=employeeService.addEmployeeAndGetId(employee);
		     System.out.print("+++++++++++++++++++++++++++++");
		    // System.out.println(employeeId);
	         
		    // getId(employeeId);
		     return ResponseEntity.of(Optional.of(employeeId));
		
		   }catch(Exception e)
		   {
		    e.printStackTrace();
	     	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		   }
	 }

	
//	public int getId(int id)
//	{
//		this.employeeId=id;
//		System.out.print("Inside getId() method ===============================================");
//		System.out.print(this.employeeId);
//		return this.employeeId;
//	}
//	
//	
	
	
	
	
	
	// Update Employee By Id
	@PutMapping("/{employeeId}")
	public ResponseEntity updateEmployee(@PathVariable ("employeeId") int employeeId, @RequestBody Employee empl)
	{
		try
		{
		this.employeeService.updateEmployeeById(employeeId, empl);
		
       return ResponseEntity.ok(null);
		}catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	
	
	
	
	//Delete Employee
	@DeleteMapping("/{employeeId}")
	   public ResponseEntity<Void> deleteBook(@PathVariable("employeeId") int empId)
	   {
		   try
		   {
		   this.employeeService.deleteEmployeeById(empId);
		
		   return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		   }catch(Exception e)
		   {
			   e.printStackTrace();
			   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  
		   }
		   
	   }
	
	
	
	
	
	
	// Get All employee
	@GetMapping()
	public ResponseEntity<List<Employee>> getAllEmployee()
	{
	  List<Employee> list=employeeService.getAllEmployee();
	  
	   if(list.size()<=0)
	   {
		   return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	   }
	   
     	   return ResponseEntity.status(HttpStatus.CREATED).body(list);
		
	}
	
	
	
	
	
	//Get All pagination and sorting
	@GetMapping("/paginations")
	public APIResponse<Page<Employee>> getAllEmpoyeeWithPagination(Pageable page)
	{
	 Page<Employee> empl=employeeService.findEmployeeByPagination(page);
		return new APIResponse<>(empl.getSize(),empl);
	}
	
	
	
	
	
	
	
	@GetMapping("/dropdown")
	public List<Map<String, Object>> getEmployeeDropdown() 
	{
	       return employeeService.getNameAndEmail();
	}


	
	
	
	
	//Pagination and Sorting 
   @GetMapping("/pagination")
   public Page<Employee> findEmployeeWithPage(Pageable page)
   {
	   return this.employeeService.getEmployeeWithPage(page);
   }

   
   //Search keyword
   @GetMapping("/search/{keyword}")
   public List<Employee> searchData(@PathVariable ("keyword") String keyword)
   {
	List<Employee> list=this.employeeService.searchKeyword(keyword);
	return list;
	   
   }
   
   
   
   //lopgin 
   @PostMapping("login")
   public int  loginHere(@RequestBody Employee employee)
   {
	      String email=employee.getEmailId();
	      String password= employee.getPassword();
    	int status=this.employeeService.login(email, password);
	    return status; 
   }
}












