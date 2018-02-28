package com.palusers.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.createUser.domain.UserCreateRequest;

public class UserCreateRequestTest {

	@Test
    public void test() {		
		
		UserCreateRequest userCreateRequest = new UserCreateRequest();        
		        
		userCreateRequest.employeeEmailId ="employeeEmailId";
        assertEquals("employeeEmailId",userCreateRequest.employeeEmailId);
        
        userCreateRequest.uniqueid = "uniqueid";
        assertEquals("uniqueid",userCreateRequest.uniqueid);
	}
}
