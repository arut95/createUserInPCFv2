package com.palusers.synchronousUserCreation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.createUser.CloudCFApplication;
import com.createUser.cloudAPI.ManageUsers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudCFApplication.class)
public class deleteUsersAndOrgsTest {

	private ManageUsers manageUsersMock;
	
	@Before
	public void setup() 
	{
		manageUsersMock = Mockito.mock(ManageUsers.class);		
	}
	
	@Test
    public void deleteAccount() 
    {	
		manageUsersMock.deleteUsersAndOrgs("token");
		verify(manageUsersMock, times(1)).deleteUsersAndOrgs("token");
    }
	
	@Test
	public void whenDelete_UsersAndOrgsTest_requestIsCorrect()
	{
		when(manageUsersMock.deleteUsersAndOrgs("token")).thenReturn("deleted");
		String deletedOrgs=manageUsersMock.deleteUsersAndOrgs("token");
		assertEquals("deleted",deletedOrgs);
	}
	  
    @Test
    public void whenDelete_UsersAndOrgsTest_requestIsNotCorrect() throws Exception{
    	when(manageUsersMock.deleteUsersAndOrgs("token")).thenReturn("");
		String deletedOrgs=manageUsersMock.deleteUsersAndOrgs("token");
		assertNotEquals("deleted",deletedOrgs);	
    }
}
