package com.employee.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.employee.entities.Employee;
import com.employee.request.EmployeeDropdownResponse;
import com.employee.dao.EmployeeRepository;


@Controller
public class EmployeeService 
{
    @Autowired
	private EmployeeRepository employeeRepository;
    
   
  
    
    // get All Employees
      public List<Employee> getAllEmployee()
	  {
	   
     List<Employee> list=(List<Employee>) this.employeeRepository.findAll();
	 return list;
	  }

      
      
      
        
    // Save Employee
    public Employee addNewEmployee(Employee empl)
    {
    	System.out.println(empl.getFirstName());
    	
    	
        Employee emp=(Employee) this.employeeRepository.save(empl);
		return emp;
    	
    }
    
    
    

    
    
    
    // Save Employee and Return Id
    public int addEmployeeAndGetId(Employee empl)
    {
    	System.out.println(empl.getFirstName());
    	
    	
        Employee emp=(Employee) this.employeeRepository.save(empl);
        int id=emp.getEmployeeId();
     
        return emp.getEmployeeId();
    	
    }
    

    
    
    //Update Employee
    public void updateEmployeeById(int id, Employee emp)
    {
    	emp.setEmployeeId(id);
    	employeeRepository.save(emp);
    }
    
    
    
    
    
    //Delete Employee
    public void deleteEmployeeById(int empId)
    {
    	employeeRepository.deleteById(empId);
    }
    
    
    public Page<Employee> findEmployeeByPagination(Pageable page)
    {
    	
    	return employeeRepository.findAll(page);
    }
    
    
    
    
    
    
    // Get Employee By Id
    public Optional<Employee> getEmployeeById(int employeeId)
    {
    	Optional<Employee> employee=null;
    	try
    	{
    		employee=this.employeeRepository.findById(employeeId);	
    		return employee;
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return employee;
    	}
    
    
    
    
    
    
    // Get Employee By Id
    public List<Map<String, Object>> getNameAndEmail()
    {
    	    return  this.employeeRepository.findNameAndEmail();
    }

   //Pagination and Sorting
    public Page<Employee> getEmployeeWithPage(Pageable page)
    {
    	return this.employeeRepository.findAll(page);
    }
    
    

  //Search
    public List<Employee> searchKeyword(String keyword)
    {
    	if(keyword!= null)
    	{
    		this.employeeRepository.search(keyword);
    	}
           return this.employeeRepository.search(keyword);    	
    }
    

    
    //login Checking
    public int login(String email, String password)
    {
      int id=this.employeeRepository.loginPage(email, password);
    	return id;
    }
}

















