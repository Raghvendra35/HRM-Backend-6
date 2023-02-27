package com.employee.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
public class Qualification 
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int qualificationId;

  private String course, board, passingyear, status;
  private double percentage;
  //@OneToMany(targetEntity = Employee.class)
  //private Employee employee;
  
  
  
public Qualification() {
	super();
	
}
public Qualification(int qualificationId, String course, String board, String passingyear, String status,
		double percentage) {
	super();
	this.qualificationId = qualificationId;
	this.course = course;
	this.board = board;
	this.passingyear = passingyear;
	this.status = status;
	this.percentage = percentage;
	
	
	
}
public int getQualificationId() {
	return qualificationId;
}
public void setQualificationId(int qualificationId) {
	this.qualificationId = qualificationId;
}
public String getCourse() {
	return course;
}
public void setCourse(String course) {
	this.course = course;
}
public String getBoard() {
	return board;
}
public void setBoard(String board) {
	this.board = board;
}
public String getPassingyear() {
	return passingyear;
}
public void setPassingyear(String passingyear) {
	this.passingyear = passingyear;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public double getPercentage() {
	return percentage;
}
public void setPercentage(double percentage) {
	this.percentage = percentage;
}
@Override
public String toString() {
	return "Qualification [qualificationId=" + qualificationId + ", course=" + course + ", board=" + board
			+ ", passingyear=" + passingyear + ", status=" + status + ", percentage=" + percentage + "]";
}




}
