package com.palusers.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.createUser.domain.UserRequest;

public class UserRequestTest {

	@Test
    public void test() {
		
		UserRequest userRequest = new UserRequest();        
		        
		userRequest.firstName = "aurl";
        assertEquals("aurl",userRequest.firstName);
        
        userRequest.lastName = "aurl";
        assertEquals("aurl",userRequest.lastName);
        
        userRequest.employeeId = 1L;
        assertEquals((Long)1L,(Long)userRequest.employeeId);
        
        userRequest.employeeEmailId = "aurl";
        assertEquals("aurl",userRequest.employeeEmailId);
        
        userRequest.phoneNumber = "aurl";
        assertEquals("aurl",userRequest.phoneNumber);
        
        userRequest.uniqueid = "aurl";
        assertEquals("aurl",userRequest.uniqueid);
        
        userRequest.proficiencylevel = "aurl";
        assertEquals("aurl",userRequest.proficiencylevel);
        
        userRequest.frameworks = "aurl";
        assertEquals("aurl",userRequest.frameworks);
        
        userRequest.linuxskill = "aurl";
        assertEquals("aurl",userRequest.linuxskill);
        
        userRequest.fullstackcompleted = "aurl";
        assertEquals("aurl",userRequest.fullstackcompleted);
        
        userRequest.fullstackskills = "aurl";
        assertEquals("aurl",userRequest.fullstackskills);
        
        userRequest.userthoughtstraning = "aurl";
        assertEquals("aurl",userRequest.userthoughtstraning);
       
        userRequest.usercommentstraining = "aurl";
        assertEquals("aurl",userRequest.usercommentstraining);
	}
}
