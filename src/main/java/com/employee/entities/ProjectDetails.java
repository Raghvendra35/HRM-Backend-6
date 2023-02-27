package com.employee.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class ProjectDetails 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int projectId;
	private String projectName;
	private String clientName;
	private String teamLeader;
	private String developingTechnology;
	private String databaseTechnology;
	private LocalDate fromDate, toDate;
	
	
	@OneToOne(targetEntity = Employee.class)
	private Employee employee;


	public ProjectDetails() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ProjectDetails(int projectId, String projectName, String clientName, String teamLeader,
			String developingTechnology, String databaseTechnology, LocalDate fromDate, LocalDate toDate,
			Employee employee) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.clientName = clientName;
		this.teamLeader = teamLeader;
		this.developingTechnology = developingTechnology;
		this.databaseTechnology = databaseTechnology;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.employee = employee;
	}


	public int getProjectId() {
		return projectId;
	}


	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getClientName() {
		return clientName;
	}


	public void setClientName(String clientName) {
		this.clientName = clientName;
	}


	public String getTeamLeader() {
		return teamLeader;
	}


	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
	}


	public String getDevelopingTechnology() {
		return developingTechnology;
	}


	public void setDevelopingTechnology(String developingTechnology) {
		this.developingTechnology = developingTechnology;
	}


	public String getDatabaseTechnology() {
		return databaseTechnology;
	}


	public void setDatabaseTechnology(String databaseTechnology) {
		this.databaseTechnology = databaseTechnology;
	}


	public LocalDate getFromDate() {
		return fromDate;
	}


	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}


	public LocalDate getToDate() {
		return toDate;
	}


	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	
	

}
