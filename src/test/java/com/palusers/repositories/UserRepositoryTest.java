package com.palusers.repositories;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.createUser.domain.UserEntity;
import com.createUser.repositories.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class UserRepositoryTest {

	private UserRepository userRepository;
	@Before
	public void setup() 
	{
		userRepository = Mockito.mock(UserRepository.class);				
	}
	
	@Test
    public void findByEmailId() 
    {	
		when(userRepository.findByEmailId("id")).thenReturn(getuserEntity());
		UserEntity userEntity = userRepository.findByEmailId("id");
		assertEquals("firstname",userEntity.getFirstname());
    }
    
	@Test
    public void getUserForActivation() 
    {	
		when(userRepository.getUserForActivation("NOTACTIVATED",1)).thenReturn(getuserEntitys());
		List<UserEntity> lstuserEntity = userRepository.getUserForActivation("NOTACTIVATED",1);
		assertEquals("firstname",lstuserEntity.get(0).getFirstname());
    }
	
	@Test
    public void updateAccountStatus() 
    {	
		LocalDateTime dt = LocalDateTime.now();
		userRepository.updateAccountStatus("eid","SENT",Timestamp.valueOf(dt));
		verify(userRepository, times(1)).updateAccountStatus("eid","SENT",Timestamp.valueOf(dt));	
    } 
	
	@Test
    public void updateOrgID() 
    {	
		LocalDateTime dt = LocalDateTime.now();
		userRepository.updateOrgID("eid","orgid",Timestamp.valueOf(dt));
		verify(userRepository, times(1)).updateOrgID("eid","orgid",Timestamp.valueOf(dt));	
    } 
	
	@Test
    public void updateAccountCreationStatus() 
    {	
		LocalDateTime dt = LocalDateTime.now();
		userRepository.updateAccountCreationStatus("eid","NOTACTIVATED",Timestamp.valueOf(dt));
		verify(userRepository, times(1)).updateAccountCreationStatus("eid","NOTACTIVATED",Timestamp.valueOf(dt));	
    } 
	
	@Test
    public void updateNoofAttempts() 
    {			
		userRepository.updateNoofAttempts("eid",1,"NOTACTIVATED");
		verify(userRepository, times(1)).updateNoofAttempts("eid",1,"NOTACTIVATED");	
    } 
	
	@Test
    public void deleteActStatus() 
    {			
		LocalDateTime dt = LocalDateTime.now();
		userRepository.deleteActStatus("NOTACTIVATED",Timestamp.valueOf(dt));
		verify(userRepository, times(1)).deleteActStatus("NOTACTIVATED",Timestamp.valueOf(dt));	
    } 
	
	@Test
    public void getLongCount() throws Exception
    {	
		userRepository.Count(Timestamp.valueOf(LocalDateTime.now().minusDays(2)),Timestamp.valueOf(LocalDateTime.now()));
		verify(userRepository, times(1)).Count(Timestamp.valueOf(LocalDateTime.now().minusDays(2)),Timestamp.valueOf(LocalDateTime.now()));	
    }	
	
	private UserEntity getuserEntity()
	{
		UserEntity u1 = new UserEntity();
		u1.setFirstname("firstname");
		return u1;		
	}
	
	private List<UserEntity> getuserEntitys()
	{
		List<UserEntity> lstUsers = new ArrayList<UserEntity>();
		UserEntity u1 = new UserEntity();
		u1.setFirstname("firstname");
		lstUsers.add(u1);
		return lstUsers;
	}
}
