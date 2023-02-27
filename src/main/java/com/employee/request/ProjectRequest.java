package com.employee.request;

import java.time.LocalDate;

public class ProjectRequest 
{


	private String projectName;
	private String clientName;
	private String teamLeader;
	private String developingTechnology;
	private String databaseTechnology;
	private LocalDate fromDate, toDate;
	private int  employeeId;
	
	
	
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
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	
	
	
}
