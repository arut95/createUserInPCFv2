package com.palusers.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import com.createUser.domain.EmailRequest;

public class EmailRequestTest {

	@Test
    public void test() {
		
		EmailRequest emailRequest = new EmailRequest();        
		        
        emailRequest.Recipient = "aurl";
        assertEquals("aurl",emailRequest.Recipient);
        
        emailRequest.HCMRecipient = "aurl";
        assertEquals("aurl",emailRequest.HCMRecipient);
        
        emailRequest.Message = "aurl";
        assertEquals("aurl",emailRequest.Message);
        
        emailRequest.Subject = "aurl";
        assertEquals("aurl",emailRequest.Subject);
        
        emailRequest.isHtml = true;
        assertEquals(true,emailRequest.isHtml);
        
        emailRequest.Track = "aurl";
        assertEquals("aurl",emailRequest.Track);
        
        emailRequest.UniqueId = "aurl";
        assertEquals("aurl",emailRequest.UniqueId);
        
        emailRequest.Username = "aurl";
        assertEquals("aurl",emailRequest.Username);
        
        emailRequest.Password = "aurl";
        assertEquals("aurl",emailRequest.Password);
        
        emailRequest.ApiURL = "aurl";
        assertEquals("aurl",emailRequest.ApiURL);
        
        emailRequest.AppManagerUrl = "aurl";
        assertEquals("aurl",emailRequest.AppManagerUrl);
        
        emailRequest.AccountCreationUrl = "aurl";
        assertEquals("aurl",emailRequest.AccountCreationUrl);
	}
}
