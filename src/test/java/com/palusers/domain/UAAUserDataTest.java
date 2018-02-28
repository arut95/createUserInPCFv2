package com.palusers.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.createUser.domain.UAAEmailData;
import com.createUser.domain.UAAUserData;
public class UAAUserDataTest {

	@Test
    public void test() {		
				
		UAAUserData uAAUserData = new UAAUserData();
		uAAUserData.externalId = "externalId";
		uAAUserData.userName = "userName";		
		uAAUserData.emails = getUAAEmailData();
		uAAUserData.active = true;
		uAAUserData.verified = true;		
		uAAUserData.origin = "origin";
		uAAUserData.password = "password";		
		uAAUserData.schemas = getSchema();
		
        assertEquals("externalId",uAAUserData.externalId);
        assertEquals("userName",uAAUserData.userName);
        assertEquals(true,getUAAEmailData().get(0).primary);
        assertEquals("v1",getUAAEmailData().get(0).value);
        assertEquals(true,uAAUserData.active);
        assertEquals(true,uAAUserData.verified);
        assertEquals("origin",uAAUserData.origin);
        assertEquals("password",uAAUserData.password);
        assertEquals("s1",getSchema().get(0).toString());
        
	}
	
	private List<String> getSchema()
	{
		List<String> lstSchemas = new ArrayList<String>();
		lstSchemas.add("s1");
		return lstSchemas;
	}
	
	private List<UAAEmailData> getUAAEmailData()
	{
		List<UAAEmailData> lstUAAEmailData = new ArrayList<UAAEmailData>();
		UAAEmailData u1 = new UAAEmailData();
		u1.value = "v1";
		u1.primary= true;
		lstUAAEmailData.add(u1);
		return lstUAAEmailData;
	}
}
