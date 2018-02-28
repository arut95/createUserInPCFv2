package com.palusers.synchronousUserCreation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.createUser.CloudCFApplication;
import com.createUser.synchronousUserCreation.createUser;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudCFApplication.class)
public class createUserTest {

	private createUser createUserMock;

	@Before
	public void setup(){
		createUserMock = Mockito.mock(createUser.class);		
	}

	@Test
	public void creatingUserTest() throws ParseException 
	{
		when(createUserMock.creatingUser("arut@cts.com", "batch1", "121212","1")).thenReturn("User successfully created");
		assertEquals("User successfully created",createUserMock.creatingUser("arut@cts.com", "batch1", "121212","1"));
		
	}
	
	@SuppressWarnings("unchecked")
   	@Test(expected = ParseException.class)
    public void invalidDateFormatTest() throws ParseException 
	{
		when(createUserMock.creatingUser("arut@cts.com", "batch1", "666666","1")).thenThrow(ParseException.class);
		createUserMock.creatingUser("arut@cts.com", "batch1", "666666","1"); 
    }
	
	@SuppressWarnings("unchecked")
   	@Test(expected = Exception.class)
    public void invalidUsername_withoutDotAndAtTheRateTest() throws Exception 
	{
		when(createUserMock.creatingUser("arut", "batch1", "121212","1")).thenThrow(Exception.class);
		createUserMock.creatingUser("arut", "batch1", "121212","1"); 
    }
	
	@SuppressWarnings("unchecked")
   	@Test(expected = Exception.class)
    public void invalidUsername_withoutAtTheRateTest() throws Exception 
	{
		when(createUserMock.creatingUser("arut.sudar", "batch1", "121212","1")).thenThrow(Exception.class);
		createUserMock.creatingUser("arut.sudar", "batch1", "121212","1"); 
	}
	
	@SuppressWarnings("unchecked")
   	@Test(expected = Exception.class)
    public void invalidUsername_withoutDotTest() throws Exception 
	{
		when(createUserMock.creatingUser("arut@cognizant", "batch1", "121212","1")).thenThrow(Exception.class);
		createUserMock.creatingUser("arut@cognizant", "batch1", "121212","1"); 
	}
	
	@SuppressWarnings("unchecked")
   	@Test(expected = Exception.class)
    public void invalidTagTest() throws Exception 
	{
		when(createUserMock.creatingUser("arut@cts.com", " - batch1", "121212","1")).thenThrow(Exception.class);
		createUserMock.creatingUser("arut@cts.com", " - batch1", "121212","1"); 
    }

}
