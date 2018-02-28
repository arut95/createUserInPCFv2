package com.palusers.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.createUser.domain.UserEntity;
import com.createUser.repositories.UserRepository;
import com.createUser.services.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class UserCreationServiceTest {

	private UserCreationService userCreationServiceMock;
	private UserRepository UserRepositoryMock;
	private String employeeemailId = "cts@cognizant.com";
	
	@Before
	public void setup() throws Exception
	{
		UserRepositoryMock = Mockito.mock(UserRepository.class);
		userCreationServiceMock = new UserCreationService(UserRepositoryMock);	
	}
	
	@Test
    public void findbyEmailId() throws Exception
    {	
		when(UserRepositoryMock.findByEmailId(employeeemailId)).thenReturn(getUser());
		UserEntity usrEntity = userCreationServiceMock.findbyEmailId(employeeemailId);
		assertEquals(employeeemailId, usrEntity.getEmployeeemailid());
    }
	
	@Test
    public void saveOrUpdate() throws Exception
    {	
		when(UserRepositoryMock.save(getUser())).thenReturn(getUser());		
		UserEntity usrEntity = userCreationServiceMock.saveOrUpdate(getUser());
		assertEquals(employeeemailId, usrEntity.getEmployeeemailid());
    }
	
	@Test
    public void deleteUser() throws Exception
    {	
		userCreationServiceMock.delete(2222L);
		verify(UserRepositoryMock, times(1)).delete(2222L);	
    }
	
	@Test
    public void updateActStatus() throws Exception
    {	
		LocalDateTime dt = LocalDateTime.now();
		userCreationServiceMock.updateActStatus(employeeemailId,"ACTIVATED",Timestamp.valueOf(dt));
		verify(UserRepositoryMock, times(1)).updateAccountStatus(employeeemailId,"ACTIVATED",Timestamp.valueOf(dt));	
    }
	
	@Test
    public void updateAccountCreationStatus() throws Exception
    {	
		LocalDateTime dt = LocalDateTime.now();
		userCreationServiceMock.updateAccountCreationStatus(employeeemailId,"ACTIVATED",Timestamp.valueOf(dt));
		verify(UserRepositoryMock, times(1)).updateAccountCreationStatus(employeeemailId,"ACTIVATED",Timestamp.valueOf(dt));	
    }
	
	@Test
    public void updateOrgID() throws Exception
    {	
		LocalDateTime dt = LocalDateTime.now();
		userCreationServiceMock.updateOrgID(employeeemailId,"232",Timestamp.valueOf(dt));
		verify(UserRepositoryMock, times(1)).updateOrgID(employeeemailId,"232",Timestamp.valueOf(dt));	
    }
	
	@Test
    public void updateNoofAttempts() throws Exception
    {	
		userCreationServiceMock.updateNoofAttempts(employeeemailId,1,"NOTACTIVATED");
		verify(UserRepositoryMock, times(1)).updateNoofAttempts(employeeemailId,1,"NOTACTIVATED");	
    }	 
	
	@Test
    public void getLongCount() throws Exception
    {	
		userCreationServiceMock.getLongCount(Timestamp.valueOf(LocalDateTime.now().minusDays(2)),Timestamp.valueOf(LocalDateTime.now()));
		verify(UserRepositoryMock, times(1)).Count(Timestamp.valueOf(LocalDateTime.now().minusDays(2)),Timestamp.valueOf(LocalDateTime.now()));	
    }	
	
	private UserEntity getUser()
	{
		UserEntity ue1 = new UserEntity();
		ue1.setId((long) 1);
		ue1.setActivationdate(Timestamp.valueOf(LocalDateTime.now()));
		ue1.setActivationstatus("NOTACTIVATED");
		ue1.setCreationdate(Timestamp.valueOf(LocalDateTime.now()));
		ue1.setDepartment("Horizontal");
		ue1.setEmployeeemailid(employeeemailId);
		ue1.setEmployeeid((long) 12345);
		ue1.setFirstname("cts");
		ue1.setFrameworks("dotnet");
		ue1.setFullstackcompleted("fullstackcompleted");
		ue1.setFullstackskills("fullstackskills");
		ue1.setLastmodifieddate(Timestamp.valueOf(LocalDateTime.now()));
		ue1.setLastname("cts");
		ue1.setLinuxskill("linuxskill");
		ue1.setProficiencylevel("proficiencylevel");
		ue1.setProglanguage("JAVA");
		ue1.setSupervisoremailid("s1@cognizant.com");
		ue1.setUniqueid("2sdddd");
		ue1.setUsercommentstraining("usercommentstraining");
		ue1.setUserthoughtstraning("userthoughtstraning");
		ue1.setPhonenumber("1233");
		return ue1;
	}
}
