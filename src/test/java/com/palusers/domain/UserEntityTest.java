package com.palusers.domain;

import static org.junit.Assert.assertEquals;

import com.createUser.domain.UserEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Test;

public class UserEntityTest {

	@Test
    public void test() {
		
		LocalDateTime dt = LocalDateTime.now();
		UserEntity userEntity = new UserEntity();        
		        
		userEntity.setActivationdate(Timestamp.valueOf(dt));
        assertEquals(Timestamp.valueOf(dt),userEntity.getActivationdate());
        
        userEntity.setFirstname("firstname");
        assertEquals("firstname",userEntity.getFirstname());

        userEntity.setLastname("lastname");
        assertEquals("lastname",userEntity.getLastname());
        
        userEntity.setEmployeeid(1L);
        assertEquals((Long)1L,userEntity.getEmployeeid());
        
        userEntity.setSupervisoremailid("supervisoremailid");
        assertEquals("supervisoremailid",userEntity.getSupervisoremailid());
        
        userEntity.setDepartment("department");
        assertEquals("department",userEntity.getDepartment());
        
        userEntity.setProglanguage("proglanguage");
        assertEquals("proglanguage",userEntity.getProglanguage());
        
        userEntity.setPhonenumber("phonenumber");
        assertEquals("phonenumber",userEntity.getPhonenumber());        
        
        userEntity.setTimezone("timezone");
        assertEquals("timezone",userEntity.getTimezone());
        
        userEntity.setUniqueid("uniqueid");
        assertEquals("uniqueid",userEntity.getUniqueid());
        
        userEntity.setActivationstatus("activationstatus");
        assertEquals("activationstatus",userEntity.getActivationstatus());
        
        userEntity.setActivationdate(Timestamp.valueOf(dt));
        assertEquals(Timestamp.valueOf(dt),userEntity.getActivationdate());
        
        userEntity.setCreationdate(Timestamp.valueOf(dt));
        assertEquals(Timestamp.valueOf(dt),userEntity.getCreationdate());
        
        userEntity.setLastmodifieddate(Timestamp.valueOf(dt));;
        assertEquals(Timestamp.valueOf(dt),userEntity.getLastmodifieddate());
        
        userEntity.setNumberofattempts(1);
        assertEquals((int)1,(int)userEntity.getNumberofattempts());
        
        userEntity.setOrgid("orgid");
        assertEquals("orgid",userEntity.getOrgid());
        
        userEntity.setProficiencylevel("proficiencylevel");
        assertEquals("proficiencylevel",userEntity.getProficiencylevel());
        
        userEntity.setFrameworks("frameworks");
        assertEquals("frameworks",userEntity.getFrameworks());
        
        userEntity.setLinuxskill("linuxskill");
        assertEquals("linuxskill",userEntity.getLinuxskill());
        
        userEntity.setFullstackcompleted("fullstackcompleted");
        assertEquals("fullstackcompleted",userEntity.getFullstackcompleted());
        
        userEntity.setFullstackskills("fullstackskills");
        assertEquals("fullstackskills",userEntity.getFullstackskills());
        
        userEntity.setUserthoughtstraning("userthoughtstraning");
        assertEquals("userthoughtstraning",userEntity.getUserthoughtstraning());
        
        userEntity.setUsercommentstraining("usercommentstraining");
        assertEquals("usercommentstraining",userEntity.getUsercommentstraining());
	}
}
