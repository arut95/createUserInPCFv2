package com.createUser.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.createUser.common.ApplicationConstants;
import com.createUser.domain.EmailRequest;
import com.createUser.logger.ILogger;
import com.createUser.logger.LoggerFactory;
import com.createUser.services.EmailCreation;

@RestController
@RequestMapping("/Email")
public class TestEmailController {
	private static ILogger logger;	
	@Autowired
	private EmailCreation emailservice;
	
	public TestEmailController(LoggerFactory loggerFactory)
	{
		logger = loggerFactory.getLoggerInstance();
	}
	
	@RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
	public ResponseEntity<String> sendEmail()
	{
		ResponseEntity<String> res = null;
		try
		{
			EmailRequest emailData = new EmailRequest();
			emailData.Recipient = "jnjarun@gmail.com";
			emailData.Message="hello";
			emailData.Subject="hello";
			logger.info("Sending Email");
			emailservice.sendAccountEmail(emailData);
			logger.info("Email Sent");
			res=  new ResponseEntity<String>(ApplicationConstants.SUCCESS, HttpStatus.OK);			 
			return res;
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
