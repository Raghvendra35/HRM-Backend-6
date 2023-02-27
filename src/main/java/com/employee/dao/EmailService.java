package com.employee.dao;

import com.employee.entities.EmailDetails;

public interface EmailService
{

	
	//Only mail
	String sendSimpleMail(EmailDetails emailDetails);
	
	
	//Send mail with attachment
	String sendMailWithAttachment(EmailDetails emailDetails);
}
