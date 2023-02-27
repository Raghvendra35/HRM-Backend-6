package com.employee.controller;

import java.util.List;
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

import com.employee.dto.APIResponse;
import com.employee.entities.*;
import com.employee.request.ProjectRequest;
import com.employee.service.*;

@RestController
@RequestMapping("api/addproject")
@CrossOrigin("*")
public class ProjectDetailsController 
{
	
	@Autowired
    private ProjectDetailsService projectDetailsService;
	
	
	// Add Projects  
	@PostMapping("")
	public ResponseEntity<ProjectDetails> addProject(@RequestBody ProjectRequest projectDetails )
	{
	 try
	    {
		 
	        ProjectDetails pdetails=projectDetailsService.addProjectDetails(projectDetails);
	        return ResponseEntity.of(Optional.of(pdetails));
		}catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	
	}
	
	
	//Get ProjectDetails By Id
	@GetMapping("/getby/{pjId}")
	public ResponseEntity<Optional<ProjectDetails>> findProjectById(@PathVariable ("pjId") int pjId)
	{
	 Optional<ProjectDetails>	pjDetails=projectDetailsService.getProjectDetailsById(pjId);
	 
	 if(pjDetails==null)
	 {
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	 }else
	 {
		 return ResponseEntity.of(Optional.of(pjDetails));
	 }
	}

	//Update Projects
	@PutMapping("/{pjId}")
	public ResponseEntity<ProjectDetails> updateProjectById(@PathVariable ("pjId") int pjId, @RequestBody ProjectDetails projectsDetails)
	{
		try
		{
		projectDetailsService.updateProjects(projectsDetails, pjId);
		return  ResponseEntity.ok(null);
		}catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
    //Delete Project by Id
    @DeleteMapping("/{pjId}")	
	public ResponseEntity<ProjectDetails> deleteProjectById(@PathVariable ("pjId") int pjId)
	{
    	try
    	{
		this.projectDetailsService.deleteProject(pjId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    	}
	
	
	//Get all Projects
	@GetMapping("")
	 public ResponseEntity<List<ProjectDetails>> findAllProject()
	 {
	   List<ProjectDetails> pjDetails=projectDetailsService.findAllProject();
		
	   if(pjDetails.size()<=0)
	   {
		   return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	   }
	  return ResponseEntity.status(HttpStatus.CREATED).body(pjDetails);
	}

	
	//Sorting by properties only
		@GetMapping("/{field}")
		public APIResponse<List<ProjectDetails>> getProjectsWithSort(@PathVariable String field)
		{
		 List<ProjectDetails> allProject=projectDetailsService.findProjectWithSorting(field);
			
			return new APIResponse<>(allProject.size(),allProject);
			
		}
		
		//Pagination only
		@GetMapping("/pagination/{offset}/{pageSize}")
		public APIResponse<Page<ProjectDetails>> getProjectsWithPagination(@PathVariable ("offset") int offset, @PathVariable ("pageSize") int pageSize)
		{
	    Page<ProjectDetails> projectWithpagination=projectDetailsService.findProjectWithPagination(offset, pageSize);
			
			return new APIResponse<>(projectWithpagination.getSize(), projectWithpagination);
			
		}
		
		
		//Pagination and Sorting
		@GetMapping("/pagenumber&{offset}/pagesize&{pageSize}/sortedby&{field}")
		public APIResponse<Page<ProjectDetails>> getProjectsWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field)
		{
	    Page<ProjectDetails> projectWithpagination=projectDetailsService.findProjectWithPaginationAndSorting(offset, pageSize, field);
			
			return new APIResponse<>(projectWithpagination.getSize(), projectWithpagination);
			
		}
		
		
		@GetMapping("/pagination")
		public APIResponse<Page<ProjectDetails>> getAProjectsWithPaginationAndSort(Pageable page)
		{
	    Page<ProjectDetails> projectWithpagination=projectDetailsService.findAProjectWithPaginationAndSorting(page);
			
			return new APIResponse<>(projectWithpagination.getSize(), projectWithpagination);
			
		}
		            //( OR )
		
		@GetMapping("/paginations")
		public Page<ProjectDetails> getAllProjectsWithPaginationAndSort(Pageable page)
		{
	      return projectDetailsService.findAProjectWithPaginationAndSorting(page);
		}	
		
		
		
		
		//Searching
		@GetMapping("search/{keyword}")
		public List<ProjectDetails> seach(@PathVariable ("keyword") String keyword)
		{
		 List<ProjectDetails> list=this.projectDetailsService.searchData(keyword);	
		 return list;
		}
	
	
	
}
