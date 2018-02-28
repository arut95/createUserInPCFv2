package com.palusers.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.createUser.domain.EmailEntity;
import com.createUser.repositories.EmailRepository;
import com.createUser.services.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class EmailPersistServiceTest {

	private EmailRepository emailRepositoryMock;
	private EmailPersistService emailPersistServiceMock;
	private String employeeemailId = "cts@cognizant.com";
	
	@Before
	public void setup() throws Exception
	{
		emailRepositoryMock = Mockito.mock(EmailRepository.class);
		emailPersistServiceMock = new EmailPersistService(emailRepositoryMock);		
	}
	
	@Test
    public void saveOrUpdate() throws Exception
    {	
		when(emailRepositoryMock.save(getEmail())).thenReturn(getEmail());
		EmailEntity emailEntity = emailPersistServiceMock.saveOrUpdate(getEmail());
		assertEquals(employeeemailId, emailEntity.getReceipient());
    }
	
	private EmailEntity getEmail()
	{
		EmailEntity em1 = new EmailEntity();
		em1.setId(1L);
		em1.setMessage("message");
		em1.setReceipient(employeeemailId);
		em1.setHcmreceipient("hcmreceipient");
		return em1;
	}
	
	@Test
    public void updateEmailStatus() throws Exception
    {	
		LocalDateTime dt = LocalDateTime.now();
		emailRepositoryMock.updateEmailStatus(employeeemailId,"ACTIVATED",Timestamp.valueOf(dt));
		verify(emailRepositoryMock, times(1)).updateEmailStatus(employeeemailId,"ACTIVATED",Timestamp.valueOf(dt));	
    }
}
