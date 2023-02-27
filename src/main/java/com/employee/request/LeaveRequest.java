package com.employee.request;

import java.time.LocalDate;

public class LeaveRequest 
{
	    
	     private LocalDate fromDate;
	     private LocalDate toDate;
	     private String reasonToLeave;
		 private int employeeId;
		
		
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
		public String getReasonToLeave() {
			return reasonToLeave;
		}
		public void setReasonToLeave(String reasonToLeave) {
			this.reasonToLeave = reasonToLeave;
		}
		public int getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(int employeeId) {
			this.employeeId = employeeId;
		} 

}
