package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entities.EmailDetails;
import com.employee.service.EmailServiceEmp;

@RestController
@RequestMapping("api/")
public class EmailController
{

	@Autowired
	private EmailServiceEmp emailService;
	
	
	@PostMapping("sendmail")
	public String sendMail(@RequestBody EmailDetails emailDetails)
	{
       String status=this.emailService.sendSimpleMail(emailDetails);
       System.out.println("Inside Controller method !!!");
		return status;
	}
	
	
	@PostMapping("mailwithattachment")
	public String SendMailWithAttachment(@RequestBody EmailDetails emailDetails)
	{
	  String status=this.emailService.sendMailWithAttachment(emailDetails);
      return status;	 	
	}
	
}