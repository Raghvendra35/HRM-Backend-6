package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.employee.dao.EmployeeRepository;
import com.employee.dao.ProjectDetailsRepository;
import com.employee.entities.*;
import com.employee.request.ProjectRequest;

@Controller
public class ProjectDetailsService
{

	@Autowired
	private ProjectDetailsRepository projectDetailsRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	// Project Save
	public ProjectDetails addProjectDetails(ProjectRequest projectRequest)
	{
		Employee employee=employeeRepository.findById(projectRequest.getEmployeeId()).get();
		
		ProjectDetails projectDetails=new ProjectDetails();
		
		projectDetails.setEmployee(employee);
		projectDetails.setProjectName(projectRequest.getProjectName());
		projectDetails.setClientName(projectRequest.getClientName());
		projectDetails.setTeamLeader(projectRequest.getTeamLeader());
		projectDetails.setDevelopingTechnology(projectRequest.getDevelopingTechnology());
		projectDetails.setDatabaseTechnology(projectRequest.getDatabaseTechnology());
		projectDetails.setFromDate(projectRequest.getFromDate());
		projectDetails.setToDate(projectRequest.getToDate());
		
		ProjectDetails pdetails=this.projectDetailsRepository.save(projectDetails);
		return pdetails;
		
	}

	
	//Get ProjectDetails by Id
		public Optional<ProjectDetails> getProjectDetailsById(int pId)
	{
		Optional<ProjectDetails> projectDetails=null;
		try
		{
	    projectDetails=this.projectDetailsRepository.findById(pId);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return projectDetails;
       		
	}
	
	
   //Update The Project
	public void updateProjects(ProjectDetails pjDetails, int pId)
	{
		pjDetails.setProjectId(pId);
		projectDetailsRepository.save(pjDetails);
		
	}
	
	
	//Delete The Project 
	public void deleteProject(int pId)
	{
		projectDetailsRepository.deleteById(pId);
		
	}

	
	
	//Get All projects 
	public List<ProjectDetails> findAllProject()
	{
		
	  List<ProjectDetails> list=(List<ProjectDetails>) projectDetailsRepository.findAll();
		 
	  return list;
		
	}

	
	
	//it will sort dynamically using any fields	
	public List<ProjectDetails> findProjectWithSorting(String field)
	{
		//this is only sorting
//		projectDetailsRepository.findAll(Sort.by(field));
		
	return projectDetailsRepository.findAll(Sort.by(Sort.Direction.ASC,field));
		
	}
	
// this is only for pagination
	public Page<ProjectDetails> findProjectWithPagination(int offset, int pageSize)
	{
     Page<ProjectDetails> project=projectDetailsRepository.findAll(PageRequest.of(offset, pageSize));
		 
		 return project;
	}
	
	
// this is pagination and sorting (here we have to do something manually)
    public Page<ProjectDetails> findProjectWithPaginationAndSorting(int offset, int pageSize, String fields)
	{
	Page<ProjectDetails> project=projectDetailsRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(fields)));                                      
			 
	return project;
	
	}
    
    
   // Pagination and Sorting (Sort Way)
    public Page<ProjectDetails> findAProjectWithPaginationAndSorting(Pageable page) 
    {
  		return projectDetailsRepository.findAll(page);
	}
	
    
    
    
    //Search
    public List<ProjectDetails> searchData(String keyword)
    {
    	if(keyword!= null)
    	{
    		projectDetailsRepository.searchRecords(keyword);
    	}
    	return this.projectDetailsRepository.searchRecords(keyword);
    }
}











