package com.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employee.entities.ProjectDetails;

public interface ProjectDetailsRepository extends JpaRepository<ProjectDetails,Integer> 
{

	
	//Search query
	@Query("SELECT pd from ProjectDetails as pd where CONCAT(pd.projectName,pd.teamLeader,pd.clientName) LIKE %?1%")
	public List<ProjectDetails> searchRecords(String keyword);
	
}
