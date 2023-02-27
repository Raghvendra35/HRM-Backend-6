package com.employee.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.employee.dao.EmailService;
import com.employee.entities.EmailDetails;
import com.employee.entities.Employee;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceEmp implements EmailService
{
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;
	
	
	@Override
	public String sendSimpleMail(EmailDetails emailDetails) 
	{
		try
		{
			SimpleMailMessage mailMessage=new SimpleMailMessage();
			
			mailMessage.setFrom(sender);
			mailMessage.setTo(emailDetails.getRecipient());
			mailMessage.setText(emailDetails.getMsgBody());
			mailMessage.setSubject(emailDetails.getSubject());
			System.out.println("Inside Sevice method !!!");
			System.out.println(emailDetails.getSubject());
			//Send Mail
			System.out.println(mailMessage);
			javaMailSender.send(mailMessage);
			
			System.out.println("Inside Sevice method !!!");
			return "Email has been sent Successfully !!!";	
		}catch(Exception e)
		{
			return null;
		}
		
	}

	
	
	
	
	//Send Email with Attachment
	@Override
	public String sendMailWithAttachment(EmailDetails emailDetails) 
	{
		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
	   try	
	   { 
		  //Setting multipart as true for attachments  
		  mimeMessageHelper=new MimeMessageHelper(mimeMessage, true); 
		  
		  mimeMessageHelper.setFrom(sender);
		  mimeMessageHelper.setTo(emailDetails.getRecipient());
		  mimeMessageHelper.setText(emailDetails.getMsgBody());
		  mimeMessageHelper.setSubject(emailDetails.getSubject());
		  
		  //Adding the Attachment
		  FileSystemResource file=new FileSystemResource(new File(emailDetails.getAttachment()));
		  
		  mimeMessageHelper.addAttachment(file.getFilename(), file);
		  
		  //Sending The Mail
		  javaMailSender.send(mimeMessage);
		  return "Mail has been sent Successfully !!!";
		  
	   }catch(Exception e)
	   {
		   e.printStackTrace();
	   }
   		
		return null;
	}

	
	
	
	//Send Mail
	public String mailSend(Employee emp)
	{
		System.out.println("+++++++++++++++++++++++========================");
    String email=emp.getEmailId();
    String profile=emp.getDesignation();
    
		return sender;
		
	}
}























