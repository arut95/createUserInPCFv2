package com.palusers.repositories;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.createUser.domain.EmailEntity;
import com.createUser.repositories.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class EmailRepositoryTest {

	private EmailRepository emailRepository;
	@Before
	public void setup() 
	{
		emailRepository = Mockito.mock(EmailRepository.class);				
	}
	
	@Test
    public void getToken() 
    {	
		when(emailRepository.getEmailEntities("NOTSENT")).thenReturn(getEmails());
		List<EmailEntity> lstEmails = emailRepository.getEmailEntities("NOTSENT");
		assertEquals("receipient",lstEmails.get(0).getReceipient());
    }
	
	@Test
    public void updateEmailStatus() 
    {	
		LocalDateTime dt = LocalDateTime.now();
		emailRepository.updateEmailStatus("emailId","SENT",Timestamp.valueOf(dt));
		verify(emailRepository, times(1)).updateEmailStatus("emailId","SENT",Timestamp.valueOf(dt));	
    } 
	
	private List<EmailEntity> getEmails()
	{
		List<EmailEntity> lstEmails = new ArrayList<EmailEntity>();
		EmailEntity e1 = new EmailEntity();
		e1.setReceipient("receipient");
		lstEmails.add(e1);
		return lstEmails;
	}
}
