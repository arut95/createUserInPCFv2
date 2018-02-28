package com.palusers.logger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.createUser.logger.*;

public class LoggerFactoryTest {

	private LoggerFactory loggerFactory;
	@Before
	public void setup() 
	{
		loggerFactory = Mockito.mock(LoggerFactory.class);				
	}
	
	@Test
    public void getLoggerInstance() 
    {	
		FileAppenderLog fLog = new FileAppenderLog();
		when(loggerFactory.getLoggerInstance()).thenReturn(fLog);		
		assertEquals(fLog,loggerFactory.getLoggerInstance());
    }
}
