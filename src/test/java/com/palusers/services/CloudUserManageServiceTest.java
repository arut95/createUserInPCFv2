package com.palusers.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import com.createUser.cloudAPI.ManageUsers;
import com.createUser.domain.UAAUserData;
import com.createUser.services.CloudUserManageService;

public class CloudUserManageServiceTest {

	private ManageUsers manageUsersMock;
	private CloudUserManageService cloudUserManageServiceMock;
	
	@Before
	public void setup() 
	{
		manageUsersMock = Mockito.mock(ManageUsers.class);
		cloudUserManageServiceMock = new CloudUserManageService(manageUsersMock);		
	}
	
	@Test
    public void getToken() 
    {	
		when(manageUsersMock.getToken("credentials")).thenReturn("token");
		String token = cloudUserManageServiceMock.getToken("credentials");
		assertEquals("token",token);
    }
	
	@Test
    public void createOrg() 
    {	
		when(manageUsersMock.createOrg("token", "OrgName")).thenReturn("orgid");
		String orgid = cloudUserManageServiceMock.createOrg("token", "OrgName");
		assertEquals("orgid",orgid);
    }
	
	
	@Test
    public void deleteOrg() 
    {	
		HttpStatus status = HttpStatus.OK;
		when(manageUsersMock.deleteOrg("token", "orgid")).thenReturn(status);
		HttpStatus statusResult = cloudUserManageServiceMock.deleteOrg("token", "orgid");
		assertEquals(statusResult,status);
    }
	
	@Test
    public void createSpace() 
    {	
		when(manageUsersMock.createSpace("token", "spacename","orgid")).thenReturn("spaceid");
		String result = cloudUserManageServiceMock.createSpace("token", "spacename","orgid");
		assertEquals("spaceid",result);
    }
	
	@Test
    public void associateOrgtoUser() 
    {	
		HttpStatus status = HttpStatus.OK;
		when(manageUsersMock.associateOrgtoUser("token", "emailId","orgid")).thenReturn(status);
		HttpStatus statusResult = cloudUserManageServiceMock.associateOrgtoUser("token", "emailId","orgid");
		assertEquals(statusResult,status);
    }
	
	@Test
    public void associateSpacetoUser() 
    {	
		HttpStatus status = HttpStatus.OK;
		when(manageUsersMock.associateSpacetoUser("token", "emailId","spaceid")).thenReturn(status);
		HttpStatus statusResult = cloudUserManageServiceMock.associateOrgtoUser("token", "emailId","spaceid");
		assertEquals(status,statusResult.OK);
    }
	
	@Test
    public void createUser() 
    {	
		UAAUserData uaaUserData = new UAAUserData();
		when(manageUsersMock.createUser("token",uaaUserData)).thenReturn("userid");
		String result = cloudUserManageServiceMock.createUser("token",uaaUserData);
		assertEquals("userid",result);
    }
	
	@Test
    public void getAuthtokenforOrgCreation() 
    {			
		when(manageUsersMock.getAuthToken("username","password","password")).thenReturn("token");
		String result = cloudUserManageServiceMock.getAuthtokenforOrgCreation("username","password","password");
		assertEquals("token",result);
    }
	
	@Test
    public void deleteSpace() 
    {	
		HttpStatus status = HttpStatus.OK;
		when(manageUsersMock.deleteSpace("token", "spaceid")).thenReturn(status);
		HttpStatus statusResult = cloudUserManageServiceMock.deleteSpace("token", "spaceid");
		assertEquals(statusResult,status);
    }
}
