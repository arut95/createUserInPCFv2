package com.palusers.domain;

import static org.junit.Assert.assertEquals;

import com.createUser.domain.EmailStatus;

import org.junit.Test;

public class EmailStatusTest {
	public static final String SUCCESS = "SUCCESS";
	public static final String ERROR = "ERROR";
	 
	@Test
    public void test() {		
		
		
		EmailStatus emailStatus = new EmailStatus("to","subject","body");
		emailStatus.error("errorMessage");
		
        assertEquals("to",emailStatus.getTo());
        assertEquals("subject",emailStatus.getSubject());
        assertEquals("body",emailStatus.getBody());
        
        assertEquals(ERROR,emailStatus.getStatus());
        assertEquals("errorMessage",emailStatus.getErrorMessage());
	}
}
