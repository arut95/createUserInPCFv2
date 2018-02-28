package com.palusers.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.createUser.domain.EmailRequest;
import com.createUser.services.*;

public class EmailCreationTest {

	private EmailCreation emailCreationMock;
	private EmailPersistService emailPersistServiceMock;	
	
	@Before
	public void setup() throws Exception
	{
		emailPersistServiceMock = Mockito.mock(EmailPersistService.class);
		emailCreationMock = new EmailCreation(emailPersistServiceMock);		
	}
	
	@Test
    public void sendRegistrationEmail() throws Exception
    {			
		boolean result = emailCreationMock.sendRegistrationEmail(getEmailRequest());
		assertEquals(true, result);	
    }
	

	@Test
    public void sendAccountEmail() throws Exception
    {			
		boolean result = emailCreationMock.sendAccountEmail(getEmailRequest());
		assertEquals(true, result);	
    }
	
	@Test
    public void sendErrorMail() throws Exception
    {			
		boolean result = emailCreationMock.sendErrorMail(getEmailRequest());
		assertEquals(true, result);	
    }
	
	@Test
    public void sendSupportTeamMail() throws Exception
    {			
		boolean result = emailCreationMock.sendSupportTeamMail(getEmailRequest());
		assertEquals(true, result);	
    }
	
	@Test
    public void sendLimitMail() throws Exception
    {			
		boolean result = emailCreationMock.sendLimitEmail(getEmailRequest());
		assertEquals(true, result);	
    }
	
	private EmailRequest getEmailRequest()
	{
		EmailRequest emq1 = new EmailRequest();
		emq1.Message = "msg";
		emq1.Username ="user";
		emq1.Recipient = "r1";
		emq1.HCMRecipient ="h1";
		emq1.Password ="p1";
		emq1.Subject ="s1";	
		emq1.Track ="t1";
		emq1.LearningUrl="l1";
		emq1.UniqueId="u1";				
		return emq1;
	}
}
